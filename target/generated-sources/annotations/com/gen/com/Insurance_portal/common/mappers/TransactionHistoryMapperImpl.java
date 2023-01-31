package com.gen.com.Insurance_portal.common.mappers;

import com.gen.com.Insurance_portal.entites.TransactionHistory;
import com.gen.com.Insurance_portal.models.RequestModels.TransactionHistoryModel;
import com.gen.com.Insurance_portal.models.responseModels.TransactionHistoryResponse;
import com.gen.com.Insurance_portal.models.responseModels.UTransactionHistoryModel;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-25T11:44:08+0700",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_265 (AdoptOpenJDK)"
)
public class TransactionHistoryMapperImpl implements TransactionHistoryMapper {

    @Override
    public UTransactionHistoryModel TransactionHistoryToTransactionHistoryModel(TransactionHistory transactionHistory) {
        if ( transactionHistory == null ) {
            return null;
        }

        UTransactionHistoryModel uTransactionHistoryModel = new UTransactionHistoryModel();

        uTransactionHistoryModel.setContractCode( transactionHistory.getContractCode() );
        uTransactionHistoryModel.setProductName( transactionHistory.getProductName() );
        uTransactionHistoryModel.setEffectiveDate( transactionHistory.getEffectiveDate() );
        uTransactionHistoryModel.setExpiredDate( transactionHistory.getExpiredDate() );
        uTransactionHistoryModel.setTransactionDate( transactionHistory.getTransactionDate() );
        uTransactionHistoryModel.setAmount( transactionHistory.getAmount() );

        return uTransactionHistoryModel;
    }

    @Override
    public TransactionHistoryResponse transactionHistoryModelToResponse(TransactionHistoryModel historyModel) {
        if ( historyModel == null ) {
            return null;
        }

        TransactionHistoryResponse transactionHistoryResponse = new TransactionHistoryResponse();

        transactionHistoryResponse.setName( historyModel.getName() );
        transactionHistoryResponse.setNumberPlate( historyModel.getNumberPlate() );
        transactionHistoryResponse.setCarMaker( historyModel.getCarMaker() );
        transactionHistoryResponse.setEmail( historyModel.getEmail() );
        transactionHistoryResponse.setPhoneNumber( historyModel.getPhoneNumber() );
        transactionHistoryResponse.setAddress( historyModel.getAddress() );
        transactionHistoryResponse.setPrice( historyModel.getPrice() );
        transactionHistoryResponse.setProcessName( historyModel.getProcessName() );
        transactionHistoryResponse.setProductId( historyModel.getProductId() );
        transactionHistoryResponse.setCustomerId( historyModel.getCustomerId() );
        transactionHistoryResponse.setCarBrandCode( historyModel.getCarBrandCode() );
        transactionHistoryResponse.setCarBrandName( historyModel.getCarBrandName() );
        transactionHistoryResponse.setCarModelCode( historyModel.getCarModelCode() );
        transactionHistoryResponse.setCarModelTitle( historyModel.getCarModelTitle() );

        return transactionHistoryResponse;
    }
}
