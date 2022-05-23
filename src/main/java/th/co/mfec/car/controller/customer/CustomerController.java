package th.co.mfec.car.controller.customer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.co.mfec.car.model.common.SuccessResponse;
import th.co.mfec.car.model.customer.CustomerAuthenRequest;
import th.co.mfec.car.model.customer.CustomerAuthenResponse;
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

    @PostMapping("/authen")
    public ResponseEntity<SuccessResponse<CustomerAuthenResponse>> authen(@Valid @RequestBody CustomerAuthenRequest customerAuthenRequest){
        return ResponseEntity.ok(new SuccessResponse<CustomerAuthenResponse>(customerService.authen(customerAuthenRequest)));
    }
    
    @GetMapping("/refresh-token")
    public ResponseEntity<SuccessResponse<CustomerAuthenResponse>> refreshToken(){
        return ResponseEntity.ok(new SuccessResponse<CustomerAuthenResponse>(customerService.refreshToken()));
    } 
    
    @GetMapping("/test/{id}")
    public ResponseEntity<SuccessResponse<String>> test(@PathVariable String id){
        return ResponseEntity.ok(new SuccessResponse<String>(id));
    }

}
