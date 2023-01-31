package com.gen.com.Insurance_portal.services.impls;

import com.gen.com.Insurance_portal.common.Helpper;
import com.gen.com.Insurance_portal.common.enums.*;
import com.gen.com.Insurance_portal.common.enums.PaymentMethod;
import com.gen.com.Insurance_portal.common.mappers.TransactionHistoryMapper;
import com.gen.com.Insurance_portal.entites.*;
import com.gen.com.Insurance_portal.exceptions.MessageException;
import com.gen.com.Insurance_portal.exceptions.NotFoundEntityException;
import com.gen.com.Insurance_portal.exceptions.NotFoundEntityExceptionByCode;
import com.gen.com.Insurance_portal.models.RequestModels.ParamsModel;
import com.gen.com.Insurance_portal.models.RequestModels.TransactionHistoryModel;
import com.gen.com.Insurance_portal.models.responseModels.TransactionHistoryResponse;
import com.gen.com.Insurance_portal.repositories.TransactionHistoryRepository;
import com.gen.com.Insurance_portal.services.*;
import com.gen.com.Insurance_portal.utils.JwtUtil;
import com.gen.com.Insurance_portal.utils.Utils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionHistoryService extends AbstractService<TransactionHistory> implements ITransactionHistoryService {
    private final TransactionHistoryRepository transactionHistoryRepository;
    private final ICustomerService customerService;
    private final IProductService productService;
    private final IContractService contractService;
    private final JwtUtil jwtTokenUtil;
    private final IUserService userService;
    private final IClaimsInfoService claimsInfoService;

    public TransactionHistoryService(TransactionHistoryRepository transactionHistoryRepository,
                                     CustomerService customerService,
                                     IProductService productService,
                                     IContractService contractService,
                                     JwtUtil jwtTokenUtil, IUserService userService,
                                     IClaimsInfoService claimsInfoService) {
        super(transactionHistoryRepository);
        this.transactionHistoryRepository = transactionHistoryRepository;
        this.customerService = customerService;
        this.productService = productService;
        this.contractService = contractService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
        this.claimsInfoService = claimsInfoService;
    }

    @Override
    public TransactionHistoryResponse create(TransactionHistoryModel transactionHistoryModel) {

        Product product = productService.findById(transactionHistoryModel.getProductId())
                .orElseThrow(() -> new NotFoundEntityException(transactionHistoryModel.getProductId(), "Product"));
//
//        Partner partner = partnerService.findById(transactionHistoryModel.getPartnerId())
//                .orElseThrow(() -> new NotFoundEntityException(transactionHistoryModel.getPartnerId(), "Partner"));

        Customer customer = customerService.findById(transactionHistoryModel.getCustomerId())
                .orElseThrow(() -> new NotFoundEntityException(transactionHistoryModel.getCustomerId(), "Customer"));


        Contract contract = new Contract();
        String contractCode = Helpper.genContractCode(product.getCode());
        while (contractService.existsByCode(contractCode)){
            contractCode = Helpper.genContractCode(product.getCode());
        }
        contract.setCode(contractCode);
        contract.setPhoneNumber(transactionHistoryModel.getPhoneNumber());
        contract.setCustomer(customer);
        contract.setCustomerCode(customer.getCustomerCode());
        contract.setEmail(transactionHistoryModel.getEmail());
        contract.setAddress(transactionHistoryModel.getAddress());
        contract.setBuyerIdNumber(customer.getUser().getIdNumber());
        contract.setGender(customer.getUser().getGender().toString());
        contract.setDob(customer.getUser().getDod());
        contract.setFullName(transactionHistoryModel.getName());
        contract.setPaidAmount(transactionHistoryModel.getPrice());
        contract.setCreatedContractDate(new Date());
        contract.setPurchaseMethod(PurchaseMethod.Paypal);
        contract.setIdNumber(customer.getUser().getIdNumber());
        contract.setNumberPlate(transactionHistoryModel.getNumberPlate());
        contract.setCarMaker(transactionHistoryModel.getCarMaker());
        contract.setProductName(product.getName());
        contract.setProduct(product);
        contract.setProductCode(product.getCode());
        if (Strings.isNotEmpty(transactionHistoryModel.getCarBrandName())){
            contract.setCarBrandName(transactionHistoryModel.getCarBrandName());
        }
        if (Strings.isNotEmpty(transactionHistoryModel.getCarBrandCode())){
            contract.setCarBrandCode(transactionHistoryModel.getCarBrandCode());
        }
        if (Strings.isNotEmpty(transactionHistoryModel.getCarModelCode())){
            contract.setCarModelCode(transactionHistoryModel.getCarModelCode());
        }
        if (Strings.isNotEmpty(transactionHistoryModel.getCarModelTitle())){
            contract.setCarModelTitle(transactionHistoryModel.getCarModelTitle());
        }

        Date activeDate = new Date();
        if (product.getEffectiveDateType().equals(EffectiveDateType.NONE) ||
                product.getEffectiveDateType().equals(EffectiveDateType.T1ToTx)) {
                LocalDate localDate = LocalDate.now().plusDays(product.getEffectiveDateRangeSelectionNumber());
                activeDate = java.util.Date.from(localDate.atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant());

                contract.setStatus(ContractStatus.Inactive);
        }
        if (product.getEffectiveDateType().equals(EffectiveDateType.T1)) {
            LocalDate localDate = LocalDate.now().plusDays(1);
            activeDate = java.util.Date.from(localDate.atStartOfDay()
                    .atZone(ZoneId.systemDefault())
                    .toInstant());
            contract.setStatus(ContractStatus.Inactive);
        }

        LocalDate localDate = LocalDate.now().plusYears(1);
        Date expiredDate = java.util.Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());

        contract.setExtendSuccessDate(activeDate);
        contract.setActiveDate(activeDate);
        contract.setExpiredDate(expiredDate);
        contract.setStatus(ContractStatus.Inactive);
        contract.setEffectiveDate(activeDate);
        Contract contractResult = contractService.save(contract);

        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setTransactionCode(Utils.generateRandomUuid());
        transactionHistory.setTransactionDate(new Date());
        transactionHistory.setPaymentMethod(PaymentMethod.Paypal);
        transactionHistory.setAmount(transactionHistoryModel.getPrice());
        transactionHistory.setCustomerCode(customer.getCustomerCode());
        transactionHistory.setCustomerPhoneNumber(transactionHistoryModel.getPhoneNumber());
        transactionHistory.setCustomer(customer);
        transactionHistory.setProductName(product.getName());
        transactionHistory.setProductCode(product.getCode());
        transactionHistory.setContractCode(contract.getCode());
        transactionHistory.setTransactionStatus(TransactionStatus.Success);
        transactionHistory.setContract(contractResult);
        transactionHistory.setProcessName(TransactionProcessName.BuyNew);
        transactionHistory.setEffectiveDate(contract.getEffectiveDate());
        transactionHistory.setCustomerName(customer.getUser().getSurname() + " " + customer.getUser().getGivenName());
        transactionHistory.setExpiredDate(contract.getExpiredDate());

        transactionHistoryRepository.save(transactionHistory);

        ClaimsInfo claimsInfo = new ClaimsInfo();
        claimsInfo.setCustomer(customer);
        claimsInfo.setCustomerId(customer.getId());
        claimsInfo.setCustomerCode(customer.getCustomerCode());
        claimsInfo.setProduct(product);
        claimsInfo.setProductId(product.getId());
        claimsInfo.setProductCode(product.getCode());
        claimsInfo.setComponentFee(product.getComponentFee());
        claimsInfo.setNumberComponent(product.getNumberComponent());
        claimsInfo.setScratchedFee(product.getScratchedFee());
        claimsInfo.setNumberScratched(product.getNumberScratched());
        claimsInfo.setRepaintFee(product.getRepaintFee());
        claimsInfo.setNumberRepaint(product.getNumberRepaint());
        claimsInfo.setBringingFee(product.getBringingFee());
        claimsInfo.setNumberBringing(product.getNumberBringing());
        claimsInfo.setRearViewMirror(product.getRearViewMirror());
        claimsInfo.setNumberRearViewMirror(product.getNumberRearViewMirror());
        claimsInfo.setContract(contract);
        claimsInfo.setContractId(contract.getId());
        claimsInfo.setContractCode(contract.getCode());
        claimsInfoService.save(claimsInfo);

        TransactionHistoryResponse response = TransactionHistoryMapper.INSTANCE
                .transactionHistoryModelToResponse(transactionHistoryModel);
        response.setContractCode(contractCode);
        return response;
    }

    @Override
    public TransactionHistory findByTransactionCode(String code) {
        return transactionHistoryRepository.findByTransactionCode(code)
                .orElseThrow(() -> new NotFoundEntityExceptionByCode(code, "TransactionHistory"));
    }

    @Override
    public Object getList(ParamsModel paramsModel, Boolean forUser) {
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

            Page<TransactionHistory> pageTuts;
            if (paramsModel.getFilter() == null){
                pageTuts = findAll(pagingSort);
            } else {
                pageTuts = transactionHistoryRepository.findAllByContractCode(paramsModel.getFilter(), pagingSort);
            }

            Object responseData;

            if (forUser){
                responseData = pageTuts.getContent().stream()
                        .map(TransactionHistoryMapper.INSTANCE::TransactionHistoryToTransactionHistoryModel)
                        .collect(Collectors.toList());
            }else {
                responseData = pageTuts.getContent();
            }


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

            Page<TransactionHistory> pageTuts;
            if (paramsModel.getFilter() == null){
                pageTuts = transactionHistoryRepository.findAllByCustomerCode(customerCode, pagingSort);
            } else {
                pageTuts = transactionHistoryRepository
                        .findAllByCustomerCodeAndContractCode(customerCode,paramsModel.getFilter(), pagingSort);
            }

            Object responseData = pageTuts.getContent().stream()
                        .map(TransactionHistoryMapper.INSTANCE::TransactionHistoryToTransactionHistoryModel)
                        .collect(Collectors.toList());



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

}
