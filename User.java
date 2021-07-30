import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * <p>
 * Purdue University -- CS18000 -- Summer 2021 -- Project 5
 *
 * @author Purdue CS Jaihyun Kee Xinyi Zhang
 * @version July 21, 2021
 */
public class User extends Thread {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 5000;
    private static JTextField nText;
    private static JTextField pText;
    private static JButton lg;
    private static JLabel accountNameLabel;
    private static JLabel passwordLabel;
    private static String name;
    private static String password;
    private static JFrame frame;
    private static boolean loop = false;

    /**
     * Check if String has numerical value in it
     *
     * @param number
     * @return boolean
     */
    private static boolean isNumeric(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean exit() {
        int ver2 = JOptionPane.showConfirmDialog(null, "Do you want to exit?",
                "Verification", JOptionPane.YES_NO_OPTION);
        if (ver2 == JOptionPane.YES_OPTION) {
            return true;
        }
        return false;
    }

    static public class actionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == lg) {
                name = nText.getText();
                password = pText.getText();
                loop = true;
            }

        }
    }

    /**
     * User connects with Server and interact
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new User());
        t.start();
        t.join();
        JOptionPane.showMessageDialog(null, "See you again!",
                "End Program", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();
            //account setting
            while (true) {
                String[] loginCreateAccount = new String[2];
                loginCreateAccount[0] = "Log in";
                loginCreateAccount[1] = "Create an Account";
                String sAccountOption;
                do {
                    sAccountOption = (String) JOptionPane.showInputDialog(null,
                            "What do you want to do?", "Posting App", JOptionPane.PLAIN_MESSAGE,
                            null, loginCreateAccount, loginCreateAccount);
                    if (sAccountOption == null) {
                        if (exit()) {
                            oos.writeObject(0);
                            return;
                        }
                    } else
                        break;
                } while (true);
                int accountOption = 0;
                if (sAccountOption.equals(loginCreateAccount[0])) {
                    accountOption = 1;
                } else if (sAccountOption.equals(loginCreateAccount[1])) {
                    accountOption = 2;
                }
                oos.writeObject(accountOption);
                if (accountOption == 1) {
                    while (true) {
                        frame = new JFrame("Welcome");
                        nText = new JTextField();
                        nText.setBounds(130, 100, 200, 30);
                        pText = new JTextField();
                        pText.setBounds(130, 130, 200, 30);
                        lg = new JButton("Log in");
                        lg.setBounds(150, 160, 80, 30);
                        accountNameLabel = new JLabel("Account Name");
                        accountNameLabel.setBounds(10, 100, 150, 30);
                        passwordLabel = new JLabel("Password");
                        passwordLabel.setBounds(25, 130, 150, 30);
                        frame.add(nText);
                        frame.add(pText);
                        frame.add(lg);
                        frame.add(accountNameLabel);
                        frame.add(passwordLabel);
                        frame.setSize(380, 300);
                        frame.setLayout(null);
                        frame.setLocation(550, 250);
                        lg.addActionListener(new actionListener());
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);
                        while (!loop) {
                            System.out.print("");
                        }
                        loop = false;
                        if (name != null && password != null) {
                            break;
                        }
                    }
                    frame.setVisible(false);
                    oos.writeObject(name);
                    oos.writeObject(password);

                    if ((boolean) ois.readObject()) {
                        JOptionPane.showMessageDialog(null, "LOGGED IN!",
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                        break;
                    } else {
                        if ((boolean) ois.readObject()) {
                            JOptionPane.showMessageDialog(null,
                                    "Someone is using this account!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Check your username and password again!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else if (accountOption == 2) {
                    String nName;
                    String nPassword;
                    do {
                        nName = JOptionPane.showInputDialog(null, "Enter your new account name:",
                                "Creating Account", JOptionPane.PLAIN_MESSAGE);
                        if (nName == null) {
                            if (exit()) {
                                return;
                            }
                        } else
                            break;
                    } while (true);
                    do {
                        nPassword = JOptionPane.showInputDialog(null,
                                "Enter your new password:",
                                "Creating Account", JOptionPane.PLAIN_MESSAGE);
                        if (nPassword == null) {
                            if (exit()) {
                                return;
                            }
                        } else
                            break;
                    } while (true);
                    if (!nName.equals("") && !nPassword.equals("")) {
                        oos.writeObject(nName);
                        oos.writeObject(nPassword);
                        if ((boolean) ois.readObject()) {
                            JOptionPane.showMessageDialog(null,
                                    "This account name already exists, try again with a different name!",
                                    "Already Exist", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Added!", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else
                        JOptionPane.showMessageDialog(null, "You need to type something!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            String welcomeMessage = (String) ois.readObject();
            JOptionPane.showMessageDialog(null, welcomeMessage + "\nWelcome to Posting Program",
                    "Welcome", JOptionPane.INFORMATION_MESSAGE);
            while (true) {
                String[] generalOptions = new String[3];
                generalOptions[0] = "Post";
                generalOptions[1] = "Account Setting";
                generalOptions[2] = "Log Out";
                String option;
                do {
                    option = (String) JOptionPane.showInputDialog(null,
                            "\n What do you want to do? ", "Posting Application",
                            JOptionPane.PLAIN_MESSAGE, null, generalOptions, generalOptions);
                    if (option == null) {
                        if (exit()) {
                            return;
                        }
                    } else
                        break;
                } while (true);
                oos.writeObject(option);
                if (option.equals("Post")) {                     //posting
                    String[] postOptions = new String[4];
                    postOptions[0] = "See the list of posts";
                    postOptions[1] = "Edit your posts";
                    postOptions[2] = "Create a new post";
                    postOptions[3] = "Delete one of your posts";
                    String userChoice;
                    do {
                        userChoice = (String) JOptionPane.showInputDialog(null,
                                "What do you want to do?", "Posting", JOptionPane.PLAIN_MESSAGE,
                                null, postOptions, postOptions);
                        if (userChoice == null) {
                            if (exit()) {
                                return;
                            }
                        } else
                            break;
                    } while (true);
                    oos.writeObject(userChoice);
                    if (userChoice.equals("See the list of posts")) {            //listing
                        String list = (String) ois.readObject();
                        JTextArea ta = new JTextArea(list, 30, 30);
                        JScrollPane js = new JScrollPane(ta);
                        js.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                        JOptionPane.showMessageDialog(null,
                                js, "Posts", JOptionPane.INFORMATION_MESSAGE);
                    } else if (userChoice.equals("Edit your posts")) {
                        //editing
                        if (!(boolean) ois.readObject()) {                  //check if this user hasn't written any posts
                            String whichEdit = (String) ois.readObject();    //Which one do you want to edit
                            String whichPost;
                            do {
                                whichPost = JOptionPane.showInputDialog(null, whichEdit,
                                        "Editing Post", JOptionPane.INFORMATION_MESSAGE);
                                if (whichPost == null) {
                                    if (exit()) {
                                        return;
                                    }
                                } else
                                    break;
                            } while (true);
                            oos.writeObject(whichPost);
                            if (!whichPost.equals("") && isNumeric(whichPost)) {
                                if ((boolean) ois.readObject()) {
                                    String[] titleAuthorContext = new String[3];
                                    titleAuthorContext[0] = "Edit Title";
                                    titleAuthorContext[1] = "Edit Author name";
                                    titleAuthorContext[2] = "Edit Context";
                                    String edit;
                                    do {
                                        edit = (String) JOptionPane.showInputDialog(null,
                                                "Which one do you want to edit?", "Edit Post Options",
                                                JOptionPane.INFORMATION_MESSAGE, null,
                                                titleAuthorContext, titleAuthorContext);
                                        if (edit == null) {
                                            if (exit()) {
                                                return;
                                            }
                                        } else
                                            break;
                                    } while (true);
                                    oos.writeObject(edit);
                                    if (edit.equals("Edit Title") || edit.equals("Edit Author name")
                                            || edit.equals("Edit Context")) {
                                        String nThing;
                                        String toEdited = (String) ois.readObject();
                                        do {
                                            nThing = JOptionPane.showInputDialog(null, toEdited,
                                                    "Editing Post", JOptionPane.INFORMATION_MESSAGE);
                                            if (nThing == null) {
                                                if (exit()) {
                                                    return;
                                                }
                                            } else
                                                break;
                                        } while (true);
                                        oos.writeObject(nThing);
                                        String resultOfEdit = (String) ois.readObject();
                                        if (resultOfEdit.equals("Title Edited!") ||
                                                resultOfEdit.equals("Author Name Edited!") ||
                                                resultOfEdit.equals("Context Edited")) {
                                            JOptionPane.showMessageDialog(null, resultOfEdit,
                                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            JOptionPane.showMessageDialog(null, resultOfEdit,
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } else
                                        JOptionPane.showMessageDialog(null, "INVALID Option!",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                } else
                                    JOptionPane.showMessageDialog(null, "INVALID Option!",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                            } else
                                JOptionPane.showMessageDialog(null, "INVALID Option!",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, "You have not written any posts!",
                                    "Error", JOptionPane.ERROR_MESSAGE);

                    } else if (userChoice.equals("Create a new post")) {      //creating
                        String title;
                        String context;
                        String authorName;
                        do {
                            title = JOptionPane.showInputDialog(null,
                                    "Enter title:",
                                    "Creating post", JOptionPane.INFORMATION_MESSAGE);
                            if (title == null) {
                                if (exit()) {
                                    return;
                                }
                            } else
                                break;
                        } while (true);
                        oos.writeObject(title);
                        do {
                            context = JOptionPane.showInputDialog(null,
                                    "Enter Context of your post:",
                                    "Creating post", JOptionPane.INFORMATION_MESSAGE);
                            if (context == null) {
                                if (exit()) {
                                    return;
                                }
                            } else
                                break;
                        } while (true);
                        oos.writeObject(context);
                        do {
                            authorName = JOptionPane.showInputDialog(null,
                                    "Enter author name:",
                                    "Creating post", JOptionPane.INFORMATION_MESSAGE);
                            if (authorName == null) {
                                if (exit()) {
                                    return;
                                }
                            } else
                                break;
                        } while (true);
                        oos.writeObject(authorName);

                        //whether fail or success to create post message
                        String resultOfCreating = (String) ois.readObject();
                        if (resultOfCreating.equals("Post Created!")) {
                            JOptionPane.showMessageDialog(null, resultOfCreating,
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, resultOfCreating,
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (userChoice.equals("Delete one of your posts")) {      //deleting
                        boolean empty = (boolean) ois.readObject();
                        if (!empty) {
                            String deletingOption = (String) ois.readObject();
                            String whichPost;
                            do {
                                whichPost = JOptionPane.showInputDialog(null, deletingOption,
                                        "Deleting Options", JOptionPane.INFORMATION_MESSAGE);
                                if (whichPost == null) {
                                    if (exit()) {
                                        return;
                                    }
                                } else
                                    break;
                            } while (true);
                            int size = (Integer) ois.readObject();
                            oos.writeObject(whichPost);
                            if (!whichPost.equals("") && isNumeric(whichPost) && Integer.parseInt(whichPost) <= size
                                    && Integer.parseInt(whichPost) > 0) {
                                JOptionPane.showMessageDialog(null, "Deleted", "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } else
                                JOptionPane.showMessageDialog(null, "INVALID Option!",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, "You haven't written any posts!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                    } else
                        JOptionPane.showMessageDialog(null, "INVALID Option!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                } else if (option.equals("Account Setting")) {              //account setting
                    String[] accountSettings = new String[2];
                    accountSettings[0] = "Edit";
                    accountSettings[1] = "Delete";
                    String editOrDelete;
                    do {
                        editOrDelete = (String) JOptionPane.showInputDialog(null,
                                "Account Setting", "Account Setting", JOptionPane.INFORMATION_MESSAGE,
                                null, accountSettings, accountSettings);
                        if (editOrDelete == null) {
                            if (exit()) {
                                return;
                            }
                        } else
                            break;
                    } while (true);
                    oos.writeObject(editOrDelete);
                    if (editOrDelete.equals("Edit")) {                          //edit account
                        String[] editAccountOrPassword = new String[2];
                        editAccountOrPassword[0] = "Account name";
                        editAccountOrPassword[1] = "Password";
                        String editOption;
                        do {
                            editOption = (String) JOptionPane.showInputDialog(null,
                                    "Which one do you want to edit?", "Edit Account",
                                    JOptionPane.INFORMATION_MESSAGE, null, editAccountOrPassword, editAccountOrPassword);
                            if (editOption == null) {
                                if (exit()) {
                                    return;
                                }
                            } else
                                break;
                        } while (true);
                        oos.writeObject(editOption);

                        if (editOption.equals("Account name")) {                   //edit account name
                            String nAccount = "";
                            while (true) {
                                do {
                                    nAccount = JOptionPane.showInputDialog(null,
                                            "Enter your new Account name:", "Edit Account Name",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    if (nAccount == null) {
                                        if (exit()) {
                                            return;
                                        }
                                    } else
                                        break;
                                } while (true);
                                if (nAccount.equals("")) {
                                    JOptionPane.showMessageDialog(null, "INVALID Input!",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                } else
                                    break;
                            }
                            oos.writeObject(nAccount);
                            String resultOfAccount = (String) ois.readObject();
                            if (resultOfAccount.equals("Account Name Edited!")) {
                                JOptionPane.showMessageDialog(null, resultOfAccount, "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } else if (resultOfAccount.equals("New AccountName is same as your present accountName!")) {
                                JOptionPane.showMessageDialog(null, resultOfAccount, "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            } else if (resultOfAccount.equals("Failed to edit (this account name already exists)")) {
                                JOptionPane.showMessageDialog(null, resultOfAccount, "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } else if (editOption.equals("Password")) {            //edit account password
                            String nPassword = "";
                            while (true) {
                                do {
                                    nPassword = JOptionPane.showInputDialog(null,
                                            "Enter your new Account Password", "Edit Account Password",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    if (nPassword == null) {
                                        if (exit()) {
                                            return;
                                        }
                                    } else
                                        break;
                                } while (true);
                                if (nPassword.equals("")) {
                                    JOptionPane.showMessageDialog(null, "Make sure you " +
                                                    "type something!",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                } else
                                    break;
                            }
                            oos.writeObject(nPassword);
                            String resultOfPassword = (String) ois.readObject();
                            if (resultOfPassword.equals("Password Edited!")) {
                                JOptionPane.showMessageDialog(null, resultOfPassword, "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } else if (resultOfPassword.equals("New password is same as your present password!")) {
                                JOptionPane.showMessageDialog(null, resultOfPassword, "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            } else if (resultOfPassword.equals("Failed to edit (this account name already exists)")) {
                                JOptionPane.showMessageDialog(null, resultOfPassword, "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }


                        }
                        //Done until this part
                    } else if (editOrDelete.equals("Delete")) {                  //delete account
                        int yn = JOptionPane.showConfirmDialog(null, "Are you sure to delete your account?",
                                "Confirmation", JOptionPane.YES_NO_OPTION);
                        oos.writeObject(yn);
                        if (yn == 0) { // yes
                            JOptionPane.showMessageDialog(null, "Deleted!\nSee you again!",
                                    "Goodbye", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                    }
                } else if (option.equals("Log Out")) {
                    String goodbye = (String) ois.readObject();
                    JOptionPane.showMessageDialog(null, goodbye,
                            "Goodbye", JOptionPane.INFORMATION_MESSAGE);
                    break;
                } else {                                                              //INVALID OPTION
                    String invalidMessage = (String) ois.readObject();
                    JOptionPane.showMessageDialog(null, invalidMessage,
                            "Goodbye", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }


            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
