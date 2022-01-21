/**
 * <p>
 *
 * @author Purdue CS Jaihyun Kee, Xinyi Zhang
 * @version July 21, 2021
 */
public class Account {
    private String accountName;
    private String password;

    /**
     * Constructor for Account
     *
     * @param accountName
     * @param password
     */
    public Account(String accountName, String password) {
        this.accountName = accountName;
        this.password = password;
    }

    /**
     * Returning account name
     *
     * @return String
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Returning account password
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setting account password with parameter String
     *
     * @return String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * editing account password with parameter String
     *
     * @return String
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
