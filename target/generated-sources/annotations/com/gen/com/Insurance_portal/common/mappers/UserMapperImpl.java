package com.gen.com.Insurance_portal.common.mappers;

import com.gen.com.Insurance_portal.entites.Role;
import com.gen.com.Insurance_portal.entites.User;
import com.gen.com.Insurance_portal.models.RequestModels.CreateProviderModel;
import com.gen.com.Insurance_portal.models.RequestModels.CreateUserModel;
import com.gen.com.Insurance_portal.models.responseModels.CustomerResponseModel;
import com.gen.com.Insurance_portal.models.responseModels.ResponseCustomerUserInfor;
import com.gen.com.Insurance_portal.models.responseModels.ResponsePartnerUserInfo;
import com.gen.com.Insurance_portal.models.responseModels.ResponseUserInfor;
import com.gen.com.Insurance_portal.models.responseModels.RoleResponseModel;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-25T11:44:08+0700",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_265 (AdoptOpenJDK)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User createUserModelToUser(CreateUserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        User user = new User();

        user.setSurname( userModel.getSurname() );
        user.setGivenName( userModel.getGivenName() );
        user.setUsername( userModel.getUsername() );
        user.setPassword( userModel.getPassword() );
        user.setPhoneNumber( userModel.getPhoneNumber() );
        user.setEmail( userModel.getEmail() );
        user.setGender( userModel.getGender() );
        user.setIdNumber( userModel.getIdNumber() );
        user.setAddress( userModel.getAddress() );

        return user;
    }

    @Override
    public ResponseUserInfor UserToUserInfor(User user) {
        if ( user == null ) {
            return null;
        }

        ResponseUserInfor responseUserInfor = new ResponseUserInfor();

        responseUserInfor.setId( user.getId() );
        responseUserInfor.setUsername( user.getUsername() );
        responseUserInfor.setEmail( user.getEmail() );
        responseUserInfor.setSurname( user.getSurname() );
        responseUserInfor.setGivenName( user.getGivenName() );
        responseUserInfor.setPhoneNumber( user.getPhoneNumber() );
        responseUserInfor.setRole( roleToRoleResponseModel( user.getRole() ) );

        return responseUserInfor;
    }

    @Override
    public ResponseCustomerUserInfor UserToCustomerUserInfor(User user) {
        if ( user == null ) {
            return null;
        }

        ResponseCustomerUserInfor responseCustomerUserInfor = new ResponseCustomerUserInfor();

        responseCustomerUserInfor.setId( user.getId() );
        responseCustomerUserInfor.setUsername( user.getUsername() );
        responseCustomerUserInfor.setEmail( user.getEmail() );
        responseCustomerUserInfor.setSurname( user.getSurname() );
        responseCustomerUserInfor.setGivenName( user.getGivenName() );
        responseCustomerUserInfor.setPhoneNumber( user.getPhoneNumber() );
        responseCustomerUserInfor.setRole( roleToRoleResponseModel( user.getRole() ) );

        return responseCustomerUserInfor;
    }

    @Override
    public ResponsePartnerUserInfo UserToPartnerUserInfor(User user) {
        if ( user == null ) {
            return null;
        }

        ResponsePartnerUserInfo responsePartnerUserInfo = new ResponsePartnerUserInfo();

        responsePartnerUserInfo.setId( user.getId() );
        responsePartnerUserInfo.setUsername( user.getUsername() );
        responsePartnerUserInfo.setEmail( user.getEmail() );
        responsePartnerUserInfo.setSurname( user.getSurname() );
        responsePartnerUserInfo.setGivenName( user.getGivenName() );
        responsePartnerUserInfo.setPhoneNumber( user.getPhoneNumber() );
        responsePartnerUserInfo.setRole( roleToRoleResponseModel( user.getRole() ) );

        return responsePartnerUserInfo;
    }

    @Override
    public User createProviderModelToUser(CreateProviderModel providerModel) {
        if ( providerModel == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( providerModel.getUsername() );
        user.setPassword( providerModel.getPassword() );
        user.setPhoneNumber( providerModel.getPhoneNumber() );
        user.setEmail( providerModel.getEmail() );
        user.setAddress( providerModel.getAddress() );

        return user;
    }

    @Override
    public CustomerResponseModel userToCustomerResponse(User providerModel) {
        if ( providerModel == null ) {
            return null;
        }

        CustomerResponseModel customerResponseModel = new CustomerResponseModel();

        customerResponseModel.setId( providerModel.getId() );
        customerResponseModel.setSurname( providerModel.getSurname() );
        customerResponseModel.setGivenName( providerModel.getGivenName() );
        customerResponseModel.setUsername( providerModel.getUsername() );
        customerResponseModel.setPhoneNumber( providerModel.getPhoneNumber() );
        customerResponseModel.setEmail( providerModel.getEmail() );
        customerResponseModel.setDod( providerModel.getDod() );
        customerResponseModel.setGender( providerModel.getGender() );
        customerResponseModel.setAddress( providerModel.getAddress() );
        customerResponseModel.setCompany( providerModel.getCompany() );
        customerResponseModel.setIsActive( providerModel.getIsActive() );

        return customerResponseModel;
    }

    protected RoleResponseModel roleToRoleResponseModel(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleResponseModel roleResponseModel = new RoleResponseModel();

        roleResponseModel.setName( role.getName() );

        return roleResponseModel;
    }
}
