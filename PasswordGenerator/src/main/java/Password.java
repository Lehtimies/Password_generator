public class Password {
    private int passwordNumber;
    private String password;

    public Password(int passwordNumber, String password) {
        this.passwordNumber = passwordNumber;
        this.password = password;
    }

    public int getPasswordNumber() {
        return passwordNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPasswordNumber(int passwordNumber) {
        this.passwordNumber = passwordNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
