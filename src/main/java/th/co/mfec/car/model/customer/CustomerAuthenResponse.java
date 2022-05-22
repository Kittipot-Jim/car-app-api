package th.co.mfec.car.model.customer;

public class CustomerAuthenResponse {

    private String email;
    private String custId;
    private String token;
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
