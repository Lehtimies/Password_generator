public class Password {
    private int passwordNumber;
    private String password;

    /**
     * Constructor for the Password class
     * @param passwordNumber The number/ID of the password
     * @param password The password
     */
    public Password(int passwordNumber, String password) {
        this.passwordNumber = passwordNumber;
        this.password = password;
    }

    // Getters and Setters
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
