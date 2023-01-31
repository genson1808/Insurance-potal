package com.gen.com.Insurance_portal.services;

import com.gen.com.Insurance_portal.entites.Customer;
import com.gen.com.Insurance_portal.models.RequestModels.ParamsModel;
import com.gen.com.Insurance_portal.models.responseModels.CustomerResponseModel;

public interface ICustomerService extends IAbstractService<Customer> {
    Object getList(ParamsModel paramsModel);
    CustomerResponseModel getOne(Long id);
}
