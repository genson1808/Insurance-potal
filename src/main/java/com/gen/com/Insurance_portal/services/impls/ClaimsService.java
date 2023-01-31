package com.gen.com.Insurance_portal.services.impls;

import com.gen.com.Insurance_portal.common.Helpper;
import com.gen.com.Insurance_portal.common.TwilioHelper;
import com.gen.com.Insurance_portal.common.enums.ClaimsStatus;
import com.gen.com.Insurance_portal.common.mappers.ClaimsMapper;
import com.gen.com.Insurance_portal.entites.*;
import com.gen.com.Insurance_portal.exceptions.ClaimsException;
import com.gen.com.Insurance_portal.exceptions.MessageException;
import com.gen.com.Insurance_portal.exceptions.NotFoundEntityException;
import com.gen.com.Insurance_portal.exceptions.NotFoundEntityExceptionByCode;
import com.gen.com.Insurance_portal.models.RequestModels.ParamsModel;
import com.gen.com.Insurance_portal.models.RequestModels.RequiredClaimsModel;
import com.gen.com.Insurance_portal.models.responseModels.ResponseRequiredClaim;
import com.gen.com.Insurance_portal.repositories.ClaimsRepository;
import com.gen.com.Insurance_portal.services.*;
import com.gen.com.Insurance_portal.utils.JwtUtil;
import com.gen.com.Insurance_portal.utils.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ClaimsService extends AbstractService<Claims> implements IClaimsService {
    private final ClaimsRepository claimsRepository;
    private final IClaimsInfoService claimsInfoService;
    private final IContractService contractService;
    private final IPartnerService partnerService;
    private final JwtUtil jwtTokenUtil;
    private final IUserService userService;

    public ClaimsService(ClaimsRepository claimsRepository, IClaimsInfoService claimsInfoService,
                         IContractService contractService,
                         IPartnerService partnerService, JwtUtil jwtTokenUtil, IUserService userService) {
        super(claimsRepository);
        this.claimsRepository = claimsRepository;
        this.claimsInfoService = claimsInfoService;
        this.contractService = contractService;
        this.partnerService = partnerService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @Override
    public ResponseRequiredClaim requiredClaims(RequiredClaimsModel requiredClaimsModel) {
        Claims claims = ClaimsMapper.INSTANCE.requiredClaimsModelToClaim(requiredClaimsModel);

        Contract contract = contractService.findByCode(requiredClaimsModel.getContractCode())
                .orElseThrow(() -> new NotFoundEntityExceptionByCode(requiredClaimsModel.getContractCode(), "Contract"));

        Customer customer = contract.getCustomer();

        Partner partner = partnerService.findById(requiredClaimsModel.getPartnerId())
                .orElseThrow(() -> new NotFoundEntityException(requiredClaimsModel.getPartnerId(), "Partner"));

        Map<String, String> errors = new HashMap<>();

        ClaimsInfo claimsInfo = claimsInfoService.findClaimsInfoByCustomerIdAndContractId(
                customer.getId(),
                contract.getId())
                .orElseThrow(() -> new MessageException("customerId or contractId invalid!"));

        Double amount = 0D;
        Double repaintFeeAmount = 0D;
        Double bringingFeeAmount = 0D;
        Double rearViewMirrorAmount = 0D;
        Double componentFeeAmount = 0D;
        Double scratchedFeeAmount = 0D;

        if (requiredClaimsModel.getRepaintFee()) {
            if ( claimsInfo.getNumberRepaint() > 0) {
                repaintFeeAmount += Helpper.percentage(
                        Double.valueOf(claimsInfo.getRepaintFee()),
                        requiredClaimsModel.getRepaintFeeAmount());

                claimsInfo.setNumberRepaint(claimsInfo.getNumberRepaint() - 1);
                claims.setNumberRepaint(claims.getNumberRepaint() + 1);

            }else {
                errors.put("repaintFee", "Not eligible for repainting!");
            }
        }

        if (requiredClaimsModel.getBringingFee()) {
            if ( claimsInfo.getNumberBringing() > 0) {
                bringingFeeAmount += Helpper.percentage(
                        Double.valueOf(claimsInfo.getBringingFee()),
                        requiredClaimsModel.getBringingFeeAmount());

                claimsInfo.setNumberBringing(claimsInfo.getNumberBringing() - 1);
                claims.setNumberBringing(claims.getNumberBringing() + 1);

            }else {
                errors.put("bringingFee", "Not eligible to bringing!");
            }
        }

        if (requiredClaimsModel.getRearViewMirror()) {
            if ( claimsInfo.getNumberRearViewMirror() > 0) {
                rearViewMirrorAmount += Helpper.percentage(
                        Double.valueOf(claimsInfo.getRearViewMirror()),
                        requiredClaimsModel.getRearViewMirrorAmount());

                claimsInfo.setNumberRearViewMirror(claimsInfo.getNumberRearViewMirror() - 1);
                claims.setNumberRearViewMirror(claims.getNumberRearViewMirror() + 1);

            }else {
                errors.put("rearViewMirror", "Not eligible mirror warranty!");
            }
        }

        if (requiredClaimsModel.getComponentFee()) {
            if ( claimsInfo.getNumberComponent() > 0) {
                componentFeeAmount += Helpper.percentage(
                        Double.valueOf(claimsInfo.getComponentFee()),
                        requiredClaimsModel.getComponentFeeAmount());

                claimsInfo.setNumberComponent(claimsInfo.getNumberComponent() - 1);
                claims.setNumberComponent(claims.getNumberComponent() + 1);

            }else {
                errors.put("componentFee", "Not eligible car parts warranty!");
            }
        }

        if (requiredClaimsModel.getScratchedFee()) {
            if ( claimsInfo.getNumberScratched() > 0) {
                scratchedFeeAmount += Helpper.percentage(
                        Double.valueOf(claimsInfo.getScratchedFee()),
                        requiredClaimsModel.getScratchedFeeAmount());

                claimsInfo.setNumberScratched(claimsInfo.getNumberScratched() - 1);
                claims.setNumberScratched(claims.getNumberScratched() + 1);

            }else {
                errors.put("scratchedFee", "Ineligible for scratches warranties!");
            }
        }

        amount += repaintFeeAmount + bringingFeeAmount + rearViewMirrorAmount + componentFeeAmount + scratchedFeeAmount;

        if (amount > contract.getProduct().getPriceObj()) {
            errors.put("maximumCompensation", "over compensation limit!");
        }

        if (!errors.isEmpty()) {
            throw new ClaimsException(errors);
        }

        claims.setContract(contract);
        claims.setContractCode(contract.getCode());
        claims.setCustomer(customer);
        claims.setPartner(partner);
        claims.setPartnerCode(partner.getCode());
        claims.setPartnerName(partner.getName());
        claims.setStatus(ClaimsStatus.Pending);
        claims.setInsuranceRepaintFeeAmount(repaintFeeAmount);
        claims.setInsuranceBringingFeeAmount(bringingFeeAmount);
        claims.setInsuranceComponentFeeAmount(componentFeeAmount);
        claims.setInsuranceRearViewMirrorAmount(rearViewMirrorAmount);
        claims.setInsuranceScratchedFeeAmount(scratchedFeeAmount);
        claims.setAmount(amount);
        claims.setCustomerCode(customer.getCustomerCode());
        claimsRepository.save(claims);

        return new ResponseRequiredClaim(amount);
    }

    @Override
    public Object UGetList(ParamsModel paramsModel, String token) {
        try {

            String customerCode = Helpper.getCustomerCode(token, jwtTokenUtil, userService);

            int page = 0;
            if (paramsModel.getPageNumber() >= 1) {
                page = paramsModel.getPageNumber() - 1;
            }
            List<Sort.Order> orders = new ArrayList<Sort.Order>();

            if (paramsModel.getSort()[0].contains(",")) {
                // will sort more than 2 fields
                // sortOrder="field, direction"
                for (String sortOrder : paramsModel.getSort()) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(Utils.getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[field, direction]
                orders.add(new Sort.Order(Utils.getSortDirection(paramsModel.getSort()[1]), paramsModel.getSort()[0]));
            }

            Pageable pagingSort = PageRequest.of(page, paramsModel.getPageSize(), Sort.by(orders));

            Page<Claims> pageTuts;
            if (paramsModel.getFilter() == null){
                pageTuts = claimsRepository.findAllByCustomerCode(customerCode, pagingSort);
            } else {
                pageTuts = claimsRepository
                        .findAllByContractCodeAndCustomerCode(paramsModel.getFilter(), customerCode, pagingSort);
            }

            Object responseData = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("data", responseData);
            response.put("currentPage", paramsModel.getPageNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return response;

        } catch (Exception e) {
            throw  new MessageException("Error server.");
        }
    }

    @Override
    public Object GetList(ParamsModel paramsModel, String token) {
        try {
            int page = 0;
            if (paramsModel.getPageNumber() >= 1) {
                page = paramsModel.getPageNumber() - 1;
            }
            List<Sort.Order> orders = new ArrayList<Sort.Order>();

            if (paramsModel.getSort()[0].contains(",")) {
                // will sort more than 2 fields
                // sortOrder="field, direction"
                for (String sortOrder : paramsModel.getSort()) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(Utils.getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[field, direction]
                orders.add(new Sort.Order(Utils.getSortDirection(paramsModel.getSort()[1]), paramsModel.getSort()[0]));
            }

            Pageable pagingSort = PageRequest.of(page, paramsModel.getPageSize(), Sort.by(orders));

            Page<Claims> pageTuts;
            if (paramsModel.getFilter() == null){
                pageTuts = findAll(pagingSort);
            } else {
                pageTuts = claimsRepository.findAllByContractCode(paramsModel.getFilter(), pagingSort);
            }

            Object responseData = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("data", responseData);
            response.put("currentPage", paramsModel.getPageNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return response;

        } catch (Exception e) {
            throw  new MessageException("Error server.");
        }
    }

    @Override
    public void status(Long id, ClaimsStatus status) {
        Claims claims = claimsRepository.findById(id).orElseThrow(() -> new NotFoundEntityException(id, "Claims"));
        claims.setStatus(status);
        update(claims);
        String content = "Money transfer is successful, " + claims.getContractCode() + " contract claim is completed.";
        TwilioHelper.send("+84" + claims.getEmployeePhoneNumber(), content);
    }

    @Override
    public Claims findByContractCode(String code) {
        return claimsRepository.findClaimsByContractCode(code)
                .orElseThrow(() -> new MessageException("not found claims have contractCode is " + code));
    }

}
