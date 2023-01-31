package com.gen.com.Insurance_portal.services.impls;

import com.gen.com.Insurance_portal.common.mappers.UserMapper;
import com.gen.com.Insurance_portal.entites.Customer;
import com.gen.com.Insurance_portal.entites.Partner;
import com.gen.com.Insurance_portal.exceptions.MessageException;
import com.gen.com.Insurance_portal.exceptions.NotFoundEntityException;
import com.gen.com.Insurance_portal.models.RequestModels.ParamsModel;
import com.gen.com.Insurance_portal.models.responseModels.CustomerResponseModel;
import com.gen.com.Insurance_portal.repositories.CustomerRepository;
import com.gen.com.Insurance_portal.services.ICustomerService;
import com.gen.com.Insurance_portal.utils.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerService extends AbstractService<Customer> implements ICustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        super(customerRepository);
        this.customerRepository = customerRepository;
    }

    @Override
    public Object getList(ParamsModel paramsModel) {
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

            Page<Customer> pageTuts;
            if (paramsModel.getFilter() == null)
                pageTuts = findAll(pagingSort);
            else
                pageTuts = customerRepository.findAllByCustomerCode(paramsModel.getFilter(), pagingSort);

            List<CustomerResponseModel> models = pageTuts.getContent().stream().map((m) -> {
                CustomerResponseModel cr = UserMapper.INSTANCE.userToCustomerResponse(m.getUser());
                cr.setCustomerCode(m.getCustomerCode());
                return cr;
            }).collect(Collectors.toList());

            Map<String, Object> response = new HashMap<>();
            response.put("data", models);
            response.put("currentPage", paramsModel.getPageNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return response;

        } catch (Exception e) {
            throw  new MessageException("Error server.");
        }
    }

    @Override
    public CustomerResponseModel getOne(Long id) {
        Customer customer = this.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(id, "customer"));
        CustomerResponseModel customerResponseModel = UserMapper.INSTANCE.userToCustomerResponse(customer.getUser());
        customerResponseModel.setCustomerCode(customer.getCustomerCode());
        return customerResponseModel;
    }


}
