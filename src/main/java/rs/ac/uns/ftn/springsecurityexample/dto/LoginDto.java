package rs.ac.uns.ftn.springsecurityexample.dto;

// DTO za login
public class LoginDto {
	
    private String username;
    private String password;

    public LoginDto() {
        super();
    }

    public LoginDto(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
