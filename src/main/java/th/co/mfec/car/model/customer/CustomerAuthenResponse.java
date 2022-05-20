package th.co.mfec.car.model.customer;

import javax.validation.constraints.NotEmpty;

public class CustomerAuthenResponse {

    @NotEmpty(message = "The email is required.")
    private String email;

    @NotEmpty(message = "The password is required.")
    private String password;

    private String custId;
    private String token;
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getCustId() {
        return custId;
    }
    public void setCustId(String custId) {
        this.custId = custId;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    
}
