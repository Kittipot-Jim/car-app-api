package th.co.mfec.car.service.customer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import th.co.mfec.car.entity.common.jpa.customer.Customer;
import th.co.mfec.car.model.customer.CustomerRegisterRequest;
import th.co.mfec.car.model.customer.CustomerRegisterResponse;
import th.co.mfec.car.repository.jpa.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CustomerRegisterResponse register(CustomerRegisterRequest customerRegisterRequest) {
        Customer customer = new Customer();
        UUID uuid = UUID.randomUUID();
        String uuidAString = uuid.toString();
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = date.format(dateFormat);

        customer.setCustId(formattedDate + uuidAString);
        customer.setEmail(customerRegisterRequest.getEmail());
        customer.setPassword(passwordEncoder.encode(customerRegisterRequest.getPassword()));
        customer.setCreatedBy(formattedDate + uuidAString);
        customer.setCreatedDate(new Date());
        customer.setUpdatedDate(new Date());
        customer = customerRepository.save(customer);

        CustomerRegisterResponse customerRegisterResponse = new CustomerRegisterResponse();
        customerRegisterResponse.setCustId(customer.getCustId());
        customerRegisterResponse.setEmail(customer.getEmail());

        return customerRegisterResponse;

    }

}
