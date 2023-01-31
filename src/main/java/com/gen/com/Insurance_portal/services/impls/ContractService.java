package com.gen.com.Insurance_portal.services.impls;

import com.gen.com.Insurance_portal.common.Helpper;
import com.gen.com.Insurance_portal.common.enums.ContractStatus;
import com.gen.com.Insurance_portal.common.mappers.ClaimsMapper;
import com.gen.com.Insurance_portal.common.mappers.ContractMapper;
import com.gen.com.Insurance_portal.entites.Contract;
import com.gen.com.Insurance_portal.exceptions.MessageException;
import com.gen.com.Insurance_portal.exceptions.NotFoundEntityExceptionByCode;
import com.gen.com.Insurance_portal.models.RequestModels.ParamsModel;
import com.gen.com.Insurance_portal.models.responseModels.ClaimsInfoModel;
import com.gen.com.Insurance_portal.repositories.ContractRepository;
import com.gen.com.Insurance_portal.services.IContractService;
import com.gen.com.Insurance_portal.services.IUserService;
import com.gen.com.Insurance_portal.utils.JwtUtil;
import com.gen.com.Insurance_portal.utils.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ContractService extends AbstractService<Contract> implements IContractService {
    private final ContractRepository contractRepository;
    private final JwtUtil jwtTokenUtil;
    private final IUserService userService;

    public ContractService(ContractRepository contractRepository,
                           JwtUtil jwtTokenUtil, IUserService userService) {
        super(contractRepository);
        this.contractRepository = contractRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @Override
    public Boolean existsByCode(String code) {
        return contractRepository.existsByCode(code);
    }

    @Override
    public Optional<Contract> findByCode(String code) {
        return contractRepository.findByCode(code);
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

            Page<Contract> pageTuts = null;
            if (paramsModel.getFilter() == null) {
                pageTuts = findAll(pagingSort);
            } else {
                if (paramsModel.getFilter().contains(",")) {
                    String[] filters = paramsModel.getFilter().split(",");
                    if (filters[0].equals("code")) {
                        pageTuts = contractRepository.findAllByCode(filters[1], pagingSort);
                    }
                    if (filters[0].equals("status")){
                        pageTuts = contractRepository.findAllByStatus(ContractStatus.valueOf(filters[1]), pagingSort);
                    }
                }else {
                    pageTuts = contractRepository.findAllByCode(paramsModel.getFilter(), pagingSort);
                }
                // code,status,value
            }

            Object responseData;

            if (forUser){
                responseData = pageTuts.getContent().stream()
                        .map(ContractMapper.INSTANCE::ContractToUContractModel)
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

            Page<Contract> pageTuts = null;
            if (paramsModel.getFilter() == null) {
                pageTuts = contractRepository.findAllByCustomerCode(customerCode, pagingSort);
            } else {
                if (paramsModel.getFilter().contains(",")) {
                    String[] filters = paramsModel.getFilter().split(",");
                    if (filters[0].equals("code")) {
                        pageTuts = contractRepository.findAllByCodeAndCustomerCode(filters[1], customerCode, pagingSort);
                    }
                    if (filters[0].equals("status")){
                        pageTuts = contractRepository.findAllByStatusAndCustomerCode(ContractStatus.valueOf(filters[1]), customerCode, pagingSort);
                    }
                }else {
                    pageTuts = contractRepository.findAllByCodeAndCustomerCode(paramsModel.getFilter(), customerCode, pagingSort);
                }
                // code,status,value
            }

            Object responseData = pageTuts.getContent().stream()
                        .map(ContractMapper.INSTANCE::ContractToUContractModel)
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

    @Override
    public void changeStatus(String code, ContractStatus contractStatus) {
        Contract contract = contractRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundEntityExceptionByCode(code, "Contract"));
        contract.setStatus(contractStatus);
        update(contract);
    }

    @Override
    public ClaimsInfoModel getClaimsInfoByContractCode(String code) {
        Contract contract = contractRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundEntityExceptionByCode(code, "Contract"));
        ClaimsInfoModel response = ClaimsMapper.INSTANCE.claimsToClaimsInto(contract.getClaimsInfo());
        return response;
    }
}
