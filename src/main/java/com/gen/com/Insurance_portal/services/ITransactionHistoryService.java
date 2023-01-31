package com.gen.com.Insurance_portal.services;

import com.gen.com.Insurance_portal.entites.TransactionHistory;
import com.gen.com.Insurance_portal.models.RequestModels.ParamsModel;
import com.gen.com.Insurance_portal.models.RequestModels.TransactionHistoryModel;
import com.gen.com.Insurance_portal.models.responseModels.TransactionHistoryResponse;


public interface ITransactionHistoryService extends IAbstractService<TransactionHistory> {
    TransactionHistoryResponse create(TransactionHistoryModel transactionHistoryModel);
    TransactionHistory findByTransactionCode(String code);
    Object getList(ParamsModel paramsModel, Boolean forUser);
    Object UGetList(ParamsModel paramsModel, String token);
}
