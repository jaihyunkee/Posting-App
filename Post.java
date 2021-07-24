/**
 * <p>
 * Purdue University -- CS18000 -- Summer 2021 -- Project 4
 *
 * @author Purdue CS Jaihyun Kee Xinyi Zhang
 * @version July 19, 2021
 */
public class Post {
    private String title;
    private String name;
    private String textContext;
    private String timestamp;
    private String accountName;

    /**
     * Constructor for Post
     *
     * @param title1
     * @param name1
     * @param textContext1
     * @param accountName1
     */
    public Post(String title1, String name1, String textContext1, String currentTime, String accountName1) {
        this.title = title1;
        this.name = name1;
        this.textContext = textContext1;
        this.timestamp = currentTime;
        this.accountName = accountName1;
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
     * Returning post author name
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Returning post context
     *
     * @return String
     */

    public String getTextContext() {
        return textContext;
    }

    /**
     * Returning post timestamp
     *
     * @return String
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Returning post title
     *
     * @return String
     */

    public String getTitle() {
        return title;
    }

    /**
     * setting account name with parameter String
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setting textContext with parameter String
     *
     * @param textContext
     */
    public void setTextContext(String textContext) {
        this.textContext = textContext;
    }

    /**
     * setting timestamp with parameter timestamp
     *
     * @param timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * setting title with parameter String
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * setting accoutName with parameter String
     *
     * @param accountName
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * Printing post's title, name, timestamp and text context
     *
     * @return String
     */
    @Override
    public String toString() {
        return
                "Title: " + title +
                        "\nName: " + name +
                        "\nTimestamp: " + timestamp +
                        "\nTextContext: " + textContext;
    }
}