package com.gen.com.Insurance_portal.services.impls;

import com.gen.com.Insurance_portal.auth.MyUserDetail;
import com.gen.com.Insurance_portal.auth.MyUserDetailService;
import com.gen.com.Insurance_portal.common.Helpper;
import com.gen.com.Insurance_portal.common.TwilioHelper;
import com.gen.com.Insurance_portal.common.enums.RegisterStatus;
import com.gen.com.Insurance_portal.common.enums.SysAdminType;
import com.gen.com.Insurance_portal.common.mappers.RoleMapper;
import com.gen.com.Insurance_portal.common.mappers.UserMapper;
import com.gen.com.Insurance_portal.entites.*;
import com.gen.com.Insurance_portal.exceptions.MessageException;
import com.gen.com.Insurance_portal.exceptions.NotFoundEntityExceptionByCode;
import com.gen.com.Insurance_portal.exceptions.TokenRefreshException;
import com.gen.com.Insurance_portal.models.RequestModels.*;
import com.gen.com.Insurance_portal.models.responseModels.ResponseCustomerUserInfor;
import com.gen.com.Insurance_portal.models.responseModels.ResponsePartnerUserInfo;
import com.gen.com.Insurance_portal.models.responseModels.ResponseUserInfor;
import com.gen.com.Insurance_portal.models.responseModels.TokenResponse;
import com.gen.com.Insurance_portal.repositories.CustomerRepository;
import com.gen.com.Insurance_portal.services.*;
import com.gen.com.Insurance_portal.services.common.RefreshTokenService;
import com.gen.com.Insurance_portal.utils.JwtUtil;
import com.gen.com.Insurance_portal.utils.Utils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuthService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;
    private final JwtUtil jwtTokenUtil;
    private final IUserService userService;
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailService userDetailService;
    private final IRoleService roleService;
    private final IPartnerService partnerService;
    private final ISysAdminService sysAdminService;
    private final IAuthoritiesService authoritiesService;

    public AuthService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder,
                       RefreshTokenService refreshTokenService, JwtUtil jwtTokenUtil,
                       AuthenticationManager authenticationManager,
                       MyUserDetailService userDetailService, IUserService userService,
                       IRoleService roleService, IPartnerService partnerService,
                       ISysAdminService sysAdminService, IAuthoritiesService authoritiesService) {

        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenService = refreshTokenService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.userService = userService;
        this.roleService = roleService;
        this.partnerService = partnerService;
        this.sysAdminService = sysAdminService;
        this.authoritiesService = authoritiesService;
    }


    @Override
    public TokenResponse authenticate(UsernameAndPasswordAuthentication authenticationModel) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationModel.getUsername(),
                            authenticationModel.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new MessageException("Incorrect username or password");
        }

        final UserDetails userDetails = userDetailService
                .loadUserByUsername(authenticationModel.getUsername());

        User user = userService.findByUsername(authenticationModel.getUsername());

        if (user == null) {
            throw new MessageException("Error userService!");
        }

        if (!user.getIsActive()) {
            throw new MessageException("account can't active.");
        }else if (user.isCancelled() || user.getIsDeleted()) {
            throw new MessageException("bad request!");
        }

        if (user.getRefreshToken() != null){
            refreshTokenService.deleteById(user.getRefreshToken().getId());
        }

        final String token = jwtTokenUtil.generateToken(userDetails, jwtTokenUtil.generateClaims(user));

        RefreshToken refreshToken = refreshTokenService.createRefreshToken();
        userService.save(user);
        refreshToken.setUser(user);
        refreshTokenService.save(refreshToken);

        Object response = null;
        if (user.getCustomer() != null) {
            ResponseCustomerUserInfor userInfor = UserMapper.INSTANCE.UserToCustomerUserInfor(user);
            userInfor.setRole(RoleMapper.INSTANCE.roleToRoleResponse(user.getRole()));
            userInfor.setCustomerId(user.getCustomer().getId());
            response = userInfor;
        }else if (user.getIsPartner()) {
            ResponsePartnerUserInfo userInfor = UserMapper.INSTANCE.UserToPartnerUserInfor(user);
            userInfor.setRole(RoleMapper.INSTANCE.roleToRoleResponse(user.getRole()));
            Partner partner = partnerService.findByCode(user.getPartnerCode())
                    .orElseThrow(() -> new NotFoundEntityExceptionByCode(user.getPartnerCode(), "Partner"));
            userInfor.setPartnerId(partner.getId());
            response = userInfor;
        }
        else {
            ResponseUserInfor userInfor = UserMapper.INSTANCE.UserToUserInfor(user);
            userInfor.setRole(RoleMapper.INSTANCE.roleToRoleResponse(user.getRole()));
            response = userInfor;
        }
        return new TokenResponse(token, refreshToken.getToken(), response);
    }

    @Override
    public TokenResponse refreshToken(String refreshToken) {
        Optional<RefreshToken> rftoken = Optional.of(refreshTokenService.findByToken(refreshToken)
                .map(rfToken -> {
                    refreshTokenService.verifyExpiration(rfToken);
                    return rfToken;
                }).orElseThrow(() -> new TokenRefreshException(refreshToken,
                        "Missing refresh token in database.Please login again")));
        User user = rftoken.get().getUser();
        if (user == null) {
            return null;
        }

        MyUserDetail myUserDetail = new MyUserDetail(user);
        String token = jwtTokenUtil.generateToken(myUserDetail, jwtTokenUtil.generateClaims(user));
        ResponseUserInfor userInfor = UserMapper.INSTANCE.UserToUserInfor(user);

        return new TokenResponse(token, refreshToken, userInfor);
    }

    @Override
    public RegisterStatus registerUser(User user, Boolean isProvider, String partnerCode) {
        if (user == null || Strings.isBlank(user.getPassword())){
            assert user != null;
            throw new MessageException("Can't register user with name " + user.getUsername());
        }

        user.setPhoneNumber("+84" + user.getPhoneNumber());

        Boolean isExist  = userService.isExistUser(user.getUsername(), user.getPhoneNumber(), user.getEmail());

        if (isExist) {
            throw new MessageException("Username or password or email already exists .");
        }

        if (!isProvider){
            user.setIsActive(false);
            Role customerRole = roleService.findByName("CUSTOMER");
            if (customerRole != null) {
                user.setRole(customerRole);
            }
        }else {
            user.setIsActive(true);
            Role partnerRole = roleService.findByName("PARTNER");
            if (partnerRole != null) {
                user.setRole(partnerRole);
            }
            Authorities required_claims = authoritiesService.findByCode("Required_Claims");
            if (required_claims != null) {
                user.getRole().getAuthorities().add(required_claims);
            }
            user.setIsPartner(true);
            if (partnerCode != null) {
                user.setPartnerCode(partnerCode);
            }
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        String code  = String.valueOf(Helpper.genCode());
        user.setPhoneCode(code);
        userService.save(user);

        return RegisterStatus.Succeeded;
    }


    @Override
    public RegisterStatus registerCustomer(CreateUserModel customerModel) {
        User user = UserMapper.INSTANCE.createUserModelToUser(customerModel);
        RegisterStatus result = registerUser(user, false, null);

        if (result == RegisterStatus.Succeeded) {
            Customer customer = new Customer();
            customer.setUser(user);
            customer.setCustomerCode(Utils.generateRandomUuid());
            customerRepository.save(customer);

            TwilioHelper.send(user.getPhoneNumber(), user.getPhoneCode());

            return RegisterStatus.Succeeded;
        }

        return result;
    }

    @Override
    public RegisterStatus registerPartner(CreateProviderModel providerModel) {
        User user = UserMapper.INSTANCE.createProviderModelToUser(providerModel);
        RegisterStatus result = registerUser(user, true, providerModel.getCode());

        if (result == RegisterStatus.Succeeded) {

            Partner provider = new Partner();
            provider.setName(providerModel.getName());
            provider.setCode(providerModel.getCode());
            provider.setPhoneNumber(providerModel.getPhoneNumber());
            provider.setHotline(providerModel.getHotline());
            provider.setIntroductionContent(providerModel.getIntroductionContent());
            provider.setAppellation(providerModel.getAppellation());
            provider.setContact(providerModel.getContact());
            provider.setEmail(providerModel.getEmail());
            provider.setIsActive(true);
            provider.setAvatarImage(providerModel.getAvatarImage());
            partnerService.save(provider);

            SysAdmin sysAdmin = new SysAdmin();
            sysAdmin.setType(SysAdminType.ProductProvider);
            sysAdmin.setIsActive(true);
            sysAdmin.setUser(user);
            sysAdmin.setPartner(provider);
            sysAdminService.save(sysAdmin);

        }

        return result;
    }

    @Override
    public Boolean activeUser(UserModelActive modelActive) {

        modelActive.setPhoneNumber("+84" + modelActive.getPhoneNumber());
        User user = userService.findUserByPhoneNumber(modelActive.getPhoneNumber());

        if (user == null || !user.getPhoneCode().equals(modelActive.getCode())) {
            return false;
        }

        user.setPhoneCode("no have!");
        user.setIsActive(true);
        userService.save(user);

        return true;
    }

    @Override
    public Boolean resendCode(ResendCodeModel resendCodeModel) {
        User user = userService.findUserByPhoneNumber(resendCodeModel.getPhoneNumber());

        if (user == null) {
            return false;
        }

        String code  = String.valueOf(Helpper.genCode());
        user.setPhoneCode(code);
        userService.save(user);

        TwilioHelper.send(resendCodeModel.getPhoneNumber(), code);

        return null;
    }

    @Override
    public Boolean updateInfo(String token, UpdateUserModel userModel) {
        String username = null;

        if (token != null && token.startsWith("Bearer ")){
            token= token.substring(7);
            username = jwtTokenUtil.extractUsername(token);
        }

        User user = userService.findByUsername(username);

        if (user != null){

            if (!Strings.isBlank(userModel.getAddress()) && !userModel.getAddress().equals("")) {
                user.setAddress(userModel.getAddress());
            }

            if (!Strings.isBlank(userModel.getCompany()) && !userModel.getCompany().equals("")) {
                user.setCompany(userModel.getCompany());
            }

            if (userModel.getDod() != null) {
                user.setDod(userModel.getDod());
            }

            if (!Strings.isBlank(userModel.getEmail())) {
                user.setEmail(userModel.getEmail());
            }

            if (userModel.getGender() != null) {
                user.setGender(userModel.getGender());
            }

            if (!Strings.isBlank(userModel.getSurname()) && !userModel.getSurname().equals("")) {
                user.setSurname(userModel.getSurname());
            }

            if (!Strings.isBlank(userModel.getGivenName()) && !userModel.getGivenName().equals("")) {
                user.setGivenName(userModel.getGivenName());
            }

            if (!Strings.isBlank(userModel.getPassword()) && !userModel.getPassword().equals("")) {
                user.setPassword(passwordEncoder.encode(userModel.getPassword()));
            }

            if (userModel.getRoleId() != null) {
                Optional<Role> role = roleService.findById(userModel.getRoleId());
                role.ifPresent(user::setRole);
            }

            userService.update(user);
            return true;
        }

        return false;
    }


}
