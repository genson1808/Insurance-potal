package com.gen.com.Insurance_portal.models.RequestModels;

import com.gen.com.Insurance_portal.common.enums.SysAdminType;
import lombok.Data;


@Data
public class UserModel {
    private SysAdminType userType;
    private Long productProviderId;
    private Long roleId;
    private String phone;
    private String birthDay;
    private String avatarPath;
    private String fullName;
    private String subscriptionTypeString;
    private String customerId;

//    public SysAdminType UserType { get; set; }
//    public Guid? ProductProviderId { get; set; }
//    public List<string> Roles { get; set; }
//    public string Role { get; set; }
//    public List<string> Claims { get; set; }
//    public string PhoneCode { get; set; }
//    public string Phone { get; set; }
//    public string BirthDay { get; set; }
//    public List<CustomerFamilyModel> MemberOfFamily { get; set; }
//    public string AvatarPath { get; set; }
//    public string FullName { get; set; }
//    public List<PolicyModel> ListPolicies { get; set; }
//    public List<TransactionHistoryModel> ListTransactionHistories { get; set; }
//    public string SubscriptionTypeString { get; set; }
//    public string CustomerId { get; set; }
}
