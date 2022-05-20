package th.co.mfec.car.service.customer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import th.co.mfec.car.constant.StatusCode;
import th.co.mfec.car.entity.common.jpa.customer.Customer;
import th.co.mfec.car.exception.BaseException;
import th.co.mfec.car.model.customer.CustomerAuthenRequest;
import th.co.mfec.car.model.customer.CustomerAuthenResponse;
import th.co.mfec.car.model.customer.CustomerRegisterRequest;
import th.co.mfec.car.model.customer.CustomerRegisterResponse;
import th.co.mfec.car.repository.jpa.CustomerRepository;
import th.co.mfec.car.util.JwtUtil;
import th.co.mfec.car.util.ValidatePassword;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public CustomerRegisterResponse register(CustomerRegisterRequest customerRegisterRequest) {
        Customer customer = new Customer();
        UUID uuid = UUID.randomUUID();
        String uuidAString = uuid.toString();
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = date.format(dateFormat);

        customer.setCustId(formattedDate + uuidAString);
        customer.setEmail(customerRegisterRequest.getEmail());
        if (customerRegisterRequest.getPassword().equals(customerRegisterRequest.getPasswordConfirm())) {
            if (ValidatePassword.isValid(customerRegisterRequest.getPassword())){
                customer.setPassword(passwordEncoder.encode(customerRegisterRequest.getPassword()));
            }
        }
        customer.setCreatedBy(formattedDate + uuidAString);
        customer.setCreatedDate(new Date());
        customer.setUpdatedDate(new Date());
        customer = customerRepository.save(customer);

        CustomerRegisterResponse customerRegisterResponse = new CustomerRegisterResponse();
        customerRegisterResponse.setCustId(customer.getCustId());
        customerRegisterResponse.setEmail(customer.getEmail());

        return customerRegisterResponse;

    }

    public CustomerAuthenResponse authen(CustomerAuthenRequest customerAuthenRequest) {
        Customer customer = customerRepository.findByEmail(customerAuthenRequest.getEmail());
        if(customer == null) {
            throw new BaseException(HttpStatus.UNAUTHORIZED, StatusCode.ERR_CODE_401, StatusCode.ERR_DESC_401);
        }

        if(!passwordEncoder.matches(customerAuthenRequest.getPassword(), customer.getPassword())){
            throw new BaseException(HttpStatus.UNAUTHORIZED, StatusCode.ERR_CODE_401, StatusCode.ERR_DESC_401);
        }
        String token = jwtUtil.generateToken(customer.getEmail());
        CustomerAuthenResponse customerAuthenResponse = new CustomerAuthenResponse();
        customerAuthenResponse.setCustId(customer.getCustId());
        customerAuthenResponse.setEmail(customer.getEmail());
        customerAuthenResponse.setToken(token);
        return customerAuthenResponse;
    }

}
