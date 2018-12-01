package telran.java23.serviceprivder.model;

public class AccountUserCredential {
    String login;
    String password;
    public AccountUserCredential(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public AccountUserCredential() {
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }



}
