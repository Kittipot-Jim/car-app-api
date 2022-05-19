package th.co.mfec.car.controller.customer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.co.mfec.car.model.common.SuccessResponse;
import th.co.mfec.car.model.customer.CustomerRegisterRequest;
import th.co.mfec.car.model.customer.CustomerRegisterResponse;
import th.co.mfec.car.service.customer.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse<CustomerRegisterResponse>> register(
            @Valid @RequestBody CustomerRegisterRequest customerRegisterRequest) {
        SuccessResponse<CustomerRegisterResponse> successResponse = new SuccessResponse<CustomerRegisterResponse>();
        CustomerRegisterResponse customerRegisterResponse = customerService.register(customerRegisterRequest);
        successResponse.setData(customerRegisterResponse);
        return ResponseEntity.ok(successResponse);
    }

}
