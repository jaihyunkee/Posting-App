import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * <p>
 * Purdue University -- CS18000 -- Summer 2021 -- Project 4
 *
 * @author Purdue CS Jaihyun Kee Xinyi Zhang
 * @version July 21, 2021
 */
public class Server implements Runnable {
    private static final int SERVER_PORT = 5000;
    private static ArrayList<Post> posts = new ArrayList<>();       // having all posts
    private static Socket socket = null;
    private static ArrayList<Account> accounts = new ArrayList<>();     //having all data for user's accounts
    private static ServerSocket serverSocket = null;
    private static File accountFile = new File("Accounts.txt");     //location of account data saved
    private static File postFile = new File("Posts.txt");           //location of posts data saved
    private static Account thisAccount = null;                               //current user account data
    private static ArrayList<Account> loggedIn;

    /**
     * Accept the users here and make a thread for each users and run it
     * getting all data from text files(Accounts.txt Comments.txt Posts.txt)
     *
     * @param args
     */
    public static void main(String[] args) {
        //account Setting
        try {
            getAccountAndPasswordFromFile();        //when server starts get account data from file
            getPostsFromFile();                     //when server starts get posts data from file
            loggedIn = new ArrayList<>();
            serverSocket = new ServerSocket(SERVER_PORT);
            while (true) {
                try {
                    System.out.println("Waiting for client...");
                    socket = serverSocket.accept();         //allows client connect
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Thread temp = new Thread(new Server());       //start each thread
                temp.start();
                System.out.println("Connect!");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getting all password data from file
     */
    public synchronized static void getAccountAndPasswordFromFile() {
        try {
            FileReader fr = new FileReader(accountFile);
            BufferedReader bfr = new BufferedReader(fr);
            String[] accountAndPassword = null;
            while (true) {
                String line = null;
                try {
                    line = bfr.readLine();
                    if (line == null)
                        break;
                    accountAndPassword = line.split("/br/");
                    accounts.add(new Account(accountAndPassword[0], accountAndPassword[1])); //update accounts
                    // with data from files
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getting all post data from file
     */
    public synchronized static void getPostsFromFile() {
        try {
            FileReader fr = new FileReader(postFile);
            BufferedReader bfr = new BufferedReader(fr);
            ArrayList<String> postFromFile = new ArrayList<>();
            while (true) {
                String line = null;
                try {
                    line = bfr.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (line == null)
                    break;
                postFromFile.add(line);
            }
            for (int i = 0; i < postFromFile.size(); i++) {
                String[] singlePost = postFromFile.get(i).split("/br/");
                String title = singlePost[0];
                String name = singlePost[1];
                String textContext = singlePost[2];
                String timestamp = singlePost[3];
                String accountName = singlePost[4];
                posts.add(new Post(title, name, textContext, timestamp, accountName));  //update posts
                // with data from file
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creating new Post and add it to posts ArrayList
     *
     * @param name1
     * @param title1
     * @param textContext1
     * @param accountName
     */
    private synchronized static void creatingPost(String title1, String name1, String textContext1, String accountName) {
        Post post = new Post(title1, name1, textContext1,
                String.valueOf(new Timestamp(System.currentTimeMillis())), accountName);
        posts.add(post);    //add each post to posts array
    }

    /**
     * deletes parameter post from posts array
     *
     * @param post
     */
    private synchronized static void deletingPost(Post post) {
        posts.remove(post);         //delete a post from the posts array
    }

    /**
     * Checking if new account name is already existing in account ArrayList
     *
     * @param account
     * @param accountName
     * @return boolean
     */
    //this method only check if there is a account whose accountName is same as parameter accountName
    //this is not used when we need to compare both accountName and password like when we log in
    public synchronized static boolean alreadyExistingAccount(ArrayList<Account> account, Account accountName) {
        boolean temp = false;
        for (Account value : account) {
            if (value.getAccountName().equals(accountName.getAccountName())) {
                temp = true;
                return temp;
            }
        }
        temp = false;
        return temp;
    }

    /**
     * Printing all posts starting to list from the most recent post
     *
     * @return String
     */
    public synchronized static String listingPosts(ArrayList post) {
        String temp = "";
        int count = 1;
        for (int i = post.size() - 1; i >= 0; i--) {
            temp = temp.concat(String.format("\n[%d]\n%s", count, post.get(i).toString()));
            count++;
        }
        return temp;
    }

    /**
     * When user logs in, check if it is valid accountName
     * by checking it in accounts Array
     *
     * @return boolean
     */
    //this method is used when we need to log in. So it checks both account name and password are same as
    //parameter account
    public synchronized static boolean accountExist(ArrayList<Account> account, Account accountName) {
        for (int i = 0; i < account.size(); i++) {
            if (account.get(i).getAccountName().equals(accountName.getAccountName())
                    && account.get(i).getPassword().equals(accountName.getPassword())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add account data to file Accounts.txt
     *
     * @param account
     */
    //this is used when user make new account
    public synchronized static void addAccountToFile(Account account) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(accountFile, true));
            bw.write(account.getAccountName() + "/br/" + account.getPassword());
            bw.write("\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Replace original account data to new parameter
     * nAccount and save all changes to file
     *
     * @param nAccount
     * @param index
     */
    public synchronized static void editAccount(Account nAccount, int index) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(accountFile, false));
            //this is when we need to delete account
            if (nAccount == null) {
                for (int i = 0; i < accounts.size(); i++) {
                    if (i != index) {
                        bw.write(accounts.get(i).getAccountName() + "/br/" + accounts.get(i).getPassword());
                        bw.write("\n");
                    }
                } //this is when we need to edit account
            } else {
                for (int i = 0; i < accounts.size(); i++) {
                    if (i != index) {
                        bw.write(accounts.get(i).getAccountName() + "/br/" + accounts.get(i).getPassword());
                    } else {
                        bw.write(nAccount.getAccountName() + "/br/" + nAccount.getPassword());
                    }
                    bw.write("\n");
                }
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if String has numerical value in it
     *
     * @param number
     * @return boolean
     */
    //this is used when we need to ensure a String has number value in it
    public synchronized static boolean isNumeric(String number) {
        try {
            int a = Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Replace original post data to new parameter
     * nPost data and save all changes to file
     *
     * @param nPost
     * @param index
     */
    public synchronized static void editPost(Post nPost, int index) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(postFile, false));
            // this is when we need to delete the post
            if (nPost == null) {
                for (int i = 0; i < posts.size(); i++) {
                    if (i != index) {
                        bw.write(posts.get(i).getTitle() + "/br/" + posts.get(i).getName()
                                + "/br/" + posts.get(i).getTextContext() +
                                "/br/" + posts.get(i).getTimestamp()
                                + "/br/" + posts.get(i).getAccountName());
                        bw.write("\n");
                    }
                }   //this is when we need to edit post
            } else {
                for (int i = 0; i < posts.size(); i++) {
                    bw.write(posts.get(i).getTitle() + "/br/" + posts.get(i).getName()
                            + "/br/" + posts.get(i).getTextContext() +
                            "/br/" + posts.get(i).getTimestamp()
                            + "/br/" + posts.get(i).getAccountName());
                    bw.write("\n");
                }
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is a method to run RunLocalTest
     *
     * @param newFileName1
     * @return File
     */
    public synchronized static File changeAccountFileName(String newFileName1) {
        accountFile = new File(newFileName1);
        return accountFile;
    }

    /**
     * This is a method to run RunLocalTest
     *
     * @param newFileName2
     */

    public static File changePostsFileName(String newFileName2) {
        postFile = new File(newFileName2);
        return postFile;
    }

    /**
     * This is a method to run RunLocalTest
     *
     * @return ArrayList<Account></>
     */
    public static java.util.ArrayList<Account> getAccounts() {
        return accounts;
    }

    /**
     * This is a method to run RunLocalTest
     *
     * @return ArrayList<Post></>
     */
    public static ArrayList<Post> getPosts() {
        return posts;
    }

    /**
     * Check if account parameter is already logged in
     *
     * @param account
     */
    public static boolean isLoggedIn(Account account) {
        for (int i = 0; i < loggedIn.size(); i++) {
            if (loggedIn.get(i).getAccountName().equals(account.getAccountName()) &&
                    loggedIn.get(i).getPassword().equals(account.getPassword())) {
                return true;
            }
        }
        return false;
    }

    /**
     * New User runs from here
     */

    @Override
    public void run() {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert ois != null;
        try {
            while (true) {
                int option = (int) ois.readObject();
                if (option == 1) {               //login
                    String name = (String) ois.readObject();
                    String password = (String) ois.readObject();
                    thisAccount = new Account(name, password);
                    if (accountExist(accounts, thisAccount)) {
                        if (!isLoggedIn(thisAccount)) {
                            loggedIn.add(thisAccount);
                            oos.writeObject(true);
                            break;
                        }
                    }
                    oos.writeObject(false);
                    if (isLoggedIn(thisAccount)) {
                        oos.writeObject(true);
                    }
                    oos.writeObject(false);
                } else if (option == 2) {        //creating account
                    String nName = (String) ois.readObject();
                    String nPassword = (String) ois.readObject();
                    Account nAccount = new Account(nName, nPassword);
                    boolean exist = alreadyExistingAccount(accounts, nAccount); //check if account name already
                    oos.writeObject(exist);
                    if (!exist) {
                        accounts.add(nAccount);
                        addAccountToFile(nAccount);
                    }
                } else if (option == 0) {
                    return;
                }
            }


            oos.writeObject(String.format("%s Entered", thisAccount.getAccountName()));
            while (true) {
                String option = (String) ois.readObject();
                if (option.equals("Post")) {       //posting
                    String postOption = (String) ois.readObject();
                    if (postOption.equals("See the list of posts")) {     //listing all posts
                        if (posts.size() != 0) {
                            oos.writeObject(listingPosts(posts));
                        } else
                            oos.writeObject("No one has written any posts!");
                    } else if (postOption.equals("Edit your posts")) {                //editing
                        ArrayList<Post> privatPosts = new ArrayList<>();    //array of posts
                        for (int i = 0; i < posts.size(); i++) {            // which this account has made
                            if (posts.get(i).getAccountName().equals(thisAccount.getAccountName())) {
                                privatPosts.add(posts.get(i));
                            }
                        }

                        oos.writeObject(privatPosts.size() == 0); //check if this user hasn't written any posts
                        if (privatPosts.size() != 0) {
                            int count = 1;
                            String privatPostsToString = "";
                            for (int i = privatPosts.size() - 1; i >= 0; i--) { //listing posts from recent to old
                                privatPostsToString = privatPostsToString.concat(String.format("\n[%d]\n", count));
                                privatPostsToString = privatPostsToString.concat(privatPosts.get(i).toString());
                                count++;
                            }
                            oos.writeObject(privatPostsToString + "\n Which one do you want to edit");
                            String whichPost = (String) ois.readObject();
                            if (!whichPost.equals("") && isNumeric(whichPost)) {
                                boolean optionValid = false;
                                if (Integer.parseInt(whichPost) <= privatPosts.size()
                                        && Integer.parseInt(whichPost) > 0) {
                                    optionValid = true;
                                }
                                oos.writeObject(optionValid);


                                if (optionValid) {
                                    String edit = (String) ois.readObject();
                                    int postPosition = privatPosts.size() - Integer.parseInt(whichPost);
                                    switch (edit) {
                                        case "Edit Title":                           //edit the title of post
                                            oos.writeObject("NEW TITLE:");
                                            String nTitle = (String) ois.readObject();
                                            if (!nTitle.equals("")) {   //check if user's input is empty
                                                //update timestamp and title
                                                posts.get(posts.indexOf
                                                        (privatPosts.get(postPosition))).setTitle(nTitle);
                                                posts.get(posts.indexOf
                                                        (privatPosts.get(postPosition)))
                                                        .setTimestamp(String.valueOf
                                                                (new Timestamp(System.currentTimeMillis())));
                                                //update edited post to file
                                                Post TitleReplacement =
                                                        posts.get(posts.indexOf(privatPosts.get(postPosition)));
                                                editPost(TitleReplacement,
                                                        posts.indexOf(privatPosts.get(postPosition)));
                                                oos.writeObject("Title Edited!");
                                            } else
                                                oos.writeObject("Empty Input!");
                                            break;
                                        case "Edit Author name":          //edit the author name of the post
                                            oos.writeObject("NEW Author Name:");
                                            String nAuthorName = (String) ois.readObject();
                                            if (!nAuthorName.equals("")) {
                                                //edit the author name to posts array and update time
                                                posts.get(posts.indexOf
                                                        (privatPosts.get(postPosition))).setName(nAuthorName);
                                                posts.get(posts.indexOf(privatPosts.get(postPosition)))
                                                        .setTimestamp(String.valueOf
                                                                (new Timestamp(System.currentTimeMillis())));
                                                //update edited post to file
                                                Post authorReplacement = posts.get
                                                        (posts.indexOf(privatPosts.get(postPosition)));
                                                editPost(authorReplacement,
                                                        posts.indexOf(privatPosts.get(postPosition)));
                                                oos.writeObject("Author Name Edited!");
                                            } else
                                                oos.writeObject("Empty Input!");
                                            break;
                                        case "Edit Context":                   //edit the context of the post
                                            oos.writeObject("NEW CONTEXT:");
                                            String nContext = (String) ois.readObject();
                                            if (!nContext.equals("")) {
                                                //edit the context to posts array and update time
                                                posts.get(posts.indexOf
                                                        (privatPosts.get(postPosition))).setTextContext(nContext);
                                                posts.get(posts.indexOf(privatPosts.get(postPosition))).
                                                        setTimestamp(String.valueOf
                                                                (new Timestamp(System.currentTimeMillis())));
                                                //updated edited post to file
                                                Post contextReplacement =
                                                        posts.get(posts.indexOf(privatPosts.get(postPosition)));
                                                editPost(contextReplacement,
                                                        posts.indexOf(privatPosts.get(postPosition)));
                                                oos.writeObject("Context Edited");
                                            } else
                                                oos.writeObject("Empty Input!");
                                            break;
                                    }
                                }
                            }
                        }
                    } else if (postOption.equals("Create a new post")) {           //creating new post
                        String title = (String) ois.readObject();
                        String context = (String) ois.readObject();
                        String authorName = (String) ois.readObject();
                        //check if new title, context and authorName is empty
                        if (!title.equals("") && !context.equals("") && !authorName.equals("")) {
                            //create new post to posting array and update it to file
                            creatingPost(title, authorName, context, thisAccount.getAccountName());
                            BufferedWriter bfr = new BufferedWriter(new FileWriter(postFile, true));
                            String postToFile = title + "/br/" + authorName + "/br/" + context + "/br/" +
                                    new Timestamp(System.currentTimeMillis()) + "/br/" + thisAccount.getAccountName();
                            bfr.write(postToFile);
                            bfr.write("\n");
                            bfr.close();
                            oos.writeObject("Post Created!");
                        } else
                            oos.writeObject("Failed to Create Post (check if you typed empty context)");
                    } else if (postOption.equals("Delete one of your posts")) {    //deleting post
                        boolean empty = false;
                        ArrayList<Post> privatPosts = new ArrayList<>();
                        for (int i = 0; i < posts.size(); i++) { //getting all posts that this user wrote
                            if (posts.get(i).getAccountName().equals(thisAccount.getAccountName())) {
                                privatPosts.add(posts.get(i));
                            }
                        }
                        if (privatPosts.size() == 0) {          //check if this user hasn't written any posts
                            empty = true;
                        }
                        oos.writeObject(empty);
                        if (!empty) {
                            String privatPostsToString = "";
                            int postCount = 1;
                            //printing all posts that user has written
                            for (int i = privatPosts.size() - 1; i >= 0; i--) {
                                privatPostsToString =
                                        privatPostsToString.concat(String.format("\n[%d]\n", postCount));
                                privatPostsToString = privatPostsToString.concat(privatPosts.get(i).toString());
                                postCount++;
                            }
                            //ask which post to delete
                            oos.writeObject(privatPostsToString + "\n Which one do you want to delete");
                            oos.writeObject(privatPosts.size());
                            String whichPost = (String) ois.readObject();
                            //check if user's input is valid
                            if (!whichPost.equals("") && isNumeric(whichPost)
                                    && Integer.parseInt(whichPost) <= privatPosts.size()
                                    && Integer.parseInt(whichPost) > 0) {
                                Post temp = privatPosts.get(privatPosts.size() - Integer.parseInt(whichPost));
                                int a = 0;
                                //find where the post is in posts array
                                for (int i = 0; i < posts.size(); i++) {
                                    if (posts.get(i).getAccountName().equals(temp.getAccountName())
                                            && posts.get(i).getTitle().equals(temp.getTitle())
                                            && posts.get(i).getTextContext().equals(temp.getTextContext()))
                                        a = i;
                                }
                                //delete from posts array and from file
                                deletingPost(privatPosts.get(privatPosts.size() - Integer.parseInt(whichPost)));
                                editPost(null, a);
                            }
                        }
                    }


                } else if (option.equals("Account Setting")) {    //account setting
                    String completionMessage = "";
                    String editOrDelete = (String) ois.readObject();
                    if (editOrDelete.equals("Edit")) {                   //Editing Account
                        String editOption = (String) ois.readObject();
                        if (editOption.equals("Account name")) {                   //Editing account name
                            String nName = (String) ois.readObject();
                            Account nameEdited = new Account(nName, thisAccount.getPassword());
                            if (!alreadyExistingAccount(accounts, nameEdited)) {
                                int a = 0;
                                for (int i = 0; i < accounts.size(); i++) {
                                    if (accounts.get(i).getAccountName().equals(thisAccount.getAccountName()))
                                        a = i;
                                }
                                for (int i = 0; i < posts.size(); i++) {
                                    if (posts.get(i).getAccountName().equals(accounts.get(a).getAccountName())) {
                                        posts.get(i).setAccountName(nName);
                                        editPost(posts.get(i), i);
                                    }
                                }
                                accounts.set(a, nameEdited);
                                editAccount(nameEdited, a);
                                thisAccount.setAccountName(nName);
                                completionMessage = "Account Name Edited!";
                            } else if (thisAccount.getAccountName().equals(nName))
                                completionMessage = "New AccountName is same as your present accountName!";
                            else {
                                completionMessage = "Failed to edit (this account name already exists)";
                            }
                            oos.writeObject(completionMessage);
                        } else if (editOption.equals("Password")) {                //editing password
                            String nPassword = (String) ois.readObject();
                            Account passwordEdited = new Account(thisAccount.getAccountName(), nPassword);
                            if (!accountExist(accounts, passwordEdited)) {
                                int a = 0;
                                for (int i = 0; i < accounts.size(); i++) {
                                    if (accounts.get(i).getAccountName().equals(thisAccount.getAccountName()))
                                        a = i;
                                }
                                accounts.set(a, passwordEdited);
                                editAccount(passwordEdited, a);
                                completionMessage = "Password Edited!";
                            } else if (thisAccount.getAccountName().equals(nPassword))
                                completionMessage = "New password is same as your present password!";
                            else {
                                completionMessage = "Failed to edit (this account name already exists)";
                            }
                            oos.writeObject(completionMessage);
                        }
                    } else if (editOrDelete.equals("Delete")) {             //Deleting Account
                        int yn = (int) ois.readObject();
                        if (yn == 0) {     //Yes
                            int a = 0;
                            int p = 0;
                            for (int i = 0; i < accounts.size(); i++) {
                                if (accounts.get(i).getAccountName().equals(thisAccount.getAccountName()) &&
                                        accounts.get(i).getPassword().equals(thisAccount.getPassword()))
                                    a = i;
                                // delete the postings as well when deleting the account
                                for (int m = 0; m < posts.size(); m++) {
                                    if (accounts.get(i).getAccountName().equals(posts.get(m).getAccountName())) {
                                        p = m;
                                    }
                                }
                            }
                            editPost(null, p);
                            posts.remove(p);
                            editAccount(null, a);
                            accounts.remove(a);
                            loggedIn.remove(thisAccount);
                            break;
                        }

                    }

                } else if (option.equals("Log Out")) {                        //Log out
                    oos.writeObject(String.format("Goodbye %s", thisAccount.getAccountName()));
                    loggedIn.remove(thisAccount);
                    break;
                } else {
                    oos.writeObject("INVALID OPTION!");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            loggedIn.remove(thisAccount);
        }
    }
}
