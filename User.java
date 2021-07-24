import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * <p>
 * Purdue University -- CS18000 -- Summer 2021 -- Project 4
 *
 * @author Purdue CS Jaihyun Kee Xinyi Zhang
 * @version July 21, 2021
 */
public class User {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 5000;

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

    /**
     * User connects with Server and interact
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.flush();

        //account setting
        while (true) {
            String[] loginCreateAccount = new String[2];
            loginCreateAccount[0] = "Log in";
            loginCreateAccount[1] = "Create an Account";
            String sAccountOption = (String) JOptionPane.showInputDialog(null,
                    "What do you want to do?", "Posting App", JOptionPane.PLAIN_MESSAGE,
                    null, loginCreateAccount, loginCreateAccount);
            int accountOption = 0;
            if (sAccountOption.equals(loginCreateAccount[0])) {
                accountOption = 1;
            } else if (sAccountOption.equals(loginCreateAccount[1])) {
                accountOption = 2;
            }
            oos.writeObject(accountOption);
            if (accountOption == 1) {
                String name = JOptionPane.showInputDialog(null, "Enter your account name:",
                        "Log in", JOptionPane.QUESTION_MESSAGE);
                String password = JOptionPane.showInputDialog(null, "Enter your password",
                        "Log in", JOptionPane.QUESTION_MESSAGE);
                oos.writeObject(name);
                oos.writeObject(password);
                if ((boolean) ois.readObject()) {
                    JOptionPane.showMessageDialog(null, "LOGGED IN!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    break;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Check your username and password again!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (accountOption == 2) {
                String nName = JOptionPane.showInputDialog(null, "Enter your new account name:",
                        "Creating Account", JOptionPane.QUESTION_MESSAGE);
                String nPassword = JOptionPane.showInputDialog(null,
                        "Enter your new account name:",
                        "Creating Account", JOptionPane.QUESTION_MESSAGE);
                if (!nName.equals("") && !nPassword.equals("")) {
                    oos.writeObject(nName);
                    oos.writeObject(nPassword);
                    if ((boolean) ois.readObject()) {
                        JOptionPane.showMessageDialog(null,
                                "This account name is already exist try again with different name!",
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
            String option = (String) JOptionPane.showInputDialog(null,
                    "\n What do you want to do? ", "Posting Application",
                    JOptionPane.PLAIN_MESSAGE, null, generalOptions, generalOptions);
            oos.writeObject(option);
            if (option.equals("Post")) {                     //posting
                String[] postOptions = new String[4];
                postOptions[0] = "See the list of posts";
                postOptions[1] = "Edit your posts";
                postOptions[2] = "Create a new post";
                postOptions[3] = "Delete one of your posts";
                String userChoice = (String) JOptionPane.showInputDialog(null,
                        "What do you want to do?", "Posting", JOptionPane.PLAIN_MESSAGE,
                        null, postOptions, postOptions);
                oos.writeObject(userChoice);
                if (userChoice.equals("See the list of posts")) {            //listing
                    String list = (String) ois.readObject();
                    JOptionPane.showMessageDialog(null, list,
                            "Posts", JOptionPane.INFORMATION_MESSAGE);
                } else if (userChoice.equals("Edit your posts")) {
                    //editing
                    if (!(boolean) ois.readObject()) {                  //check if this user hasn't written any posts
                        String whichEdit = (String) ois.readObject();    //Which one do you want to edit
                        String whichPost = JOptionPane.showInputDialog(null, whichEdit,
                                "Editing Post", JOptionPane.INFORMATION_MESSAGE);
                        oos.writeObject(whichPost);
                        if (!whichPost.equals("") && isNumeric(whichPost)) {
                            if ((boolean) ois.readObject()) {
                                String[] titleAuthorContext = new String[3];
                                titleAuthorContext[0] = "Edit Title";
                                titleAuthorContext[1] = "Edit Author name";
                                titleAuthorContext[2] = "Edit Context";
                                String edit = (String) JOptionPane.showInputDialog(null,
                                        "Which one do you want to edit?", "Edit Post Options",
                                        JOptionPane.INFORMATION_MESSAGE, null,
                                        titleAuthorContext, titleAuthorContext);
                                oos.writeObject(edit);
                                if (edit.equals("Edit Title") || edit.equals("Edit Author name")
                                        || edit.equals("Edit Context")) {
                                    String toEdited = (String) ois.readObject();
                                    String nThing = JOptionPane.showInputDialog(null, toEdited,
                                            "Editing Post", JOptionPane.INFORMATION_MESSAGE);
                                    oos.writeObject(nThing);
                                    String resultOfEdit = (String) ois.readObject();
                                    if (resultOfEdit.equals("Title Edited!")) {
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
                    String title = JOptionPane.showInputDialog(null,
                            "Enter title:",
                            "Creating post", JOptionPane.INFORMATION_MESSAGE);
                    oos.writeObject(title);
                    String context = JOptionPane.showInputDialog(null,
                            "Enter Context of your post:",
                            "Creating post", JOptionPane.INFORMATION_MESSAGE);
                    oos.writeObject(context);
                    String authorName = JOptionPane.showInputDialog(null,
                            "Enter author name:",
                            "Creating post", JOptionPane.INFORMATION_MESSAGE);
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

                        String whichPost = JOptionPane.showInputDialog(null, deletingOption,
                                "Deleting Options", JOptionPane.INFORMATION_MESSAGE);
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
                String editOrDelete = (String) JOptionPane.showInputDialog(null,
                        "Account Setting", "Account Setting",JOptionPane.INFORMATION_MESSAGE,
                        null,accountSettings,accountSettings);
                oos.writeObject(editOrDelete);
                if (editOrDelete.equals("Edit")) {                          //edit account
                    String[] editAccountOrPassword = new String[2];
                    editAccountOrPassword[0] = "Account name";
                    editAccountOrPassword[1] = "Password";
                    String editOption = (String)JOptionPane.showInputDialog(null,
                            "Which one do you want to edit?", "Edit Account",
                            JOptionPane.INFORMATION_MESSAGE,null,editAccountOrPassword,editAccountOrPassword);
                    oos.writeObject(editOption);

                    if (editOption.equals("Account name")) {                   //edit account name
                        String nAccount = "";
                        while (true) {
                            nAccount = JOptionPane.showInputDialog(null,
                                    "Enter your new Account name:", "Edit Account Name",
                                    JOptionPane.INFORMATION_MESSAGE);
                            if (nAccount.equals("")) {
                                JOptionPane.showMessageDialog(null, "INVALID Option!",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            } else
                                break;
                        }
                        oos.writeObject(nAccount);
                        String resultOfAccount = (String) ois.readObject();
                        if(resultOfAccount.equals("Account Name Edited!")){
                            JOptionPane.showMessageDialog(null,resultOfAccount,"Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }else if(resultOfAccount.equals("New AccountName is same as your present accountName!")){
                            JOptionPane.showMessageDialog(null,resultOfAccount,"Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }else if(resultOfAccount.equals("Failed to edit (this account name already exists)")){
                            JOptionPane.showMessageDialog(null,resultOfAccount,"Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (editOption.equals("Password")) {            //edit account password
                        String nPassword = "";
                        while (true) {
                            nPassword = JOptionPane.showInputDialog(null,
                                    "Enter your new Account Password", "Edit Account Password",
                                    JOptionPane.INFORMATION_MESSAGE);
                            if (nPassword.equals("")) {
                                JOptionPane.showMessageDialog(null, "INVALID Option!",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            } else
                                break;
                        }
                        oos.writeObject(nPassword);
                        String resultOfPassword = (String) ois.readObject();
                        if(resultOfPassword.equals("Password Edited!")){
                            JOptionPane.showMessageDialog(null,resultOfPassword,"Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }else if(resultOfPassword.equals("New password is same as your present password!")){
                            JOptionPane.showMessageDialog(null,resultOfPassword,"Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }else if(resultOfPassword.equals("Failed to edit (this account name already exists)")){
                            JOptionPane.showMessageDialog(null,resultOfPassword,"Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }



                    }
                    //Done until this part
                } else if (editOrDelete.equals("Delete")) {                  //delete account
                    int yn = JOptionPane.showConfirmDialog(null,"Are you sure to delete your account?",
                            "Confirmation",JOptionPane.YES_NO_OPTION);
                    oos.writeObject(yn);
                    if (yn == 0) { // yes
                        JOptionPane.showMessageDialog(null,"Deleted!\nSee you again!",
                                "Goodbye",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                }
            } else if (option.equals("Log Out")) {
                String goodbye = (String) ois.readObject();
                JOptionPane.showMessageDialog(null,goodbye,
                        "Goodbye",JOptionPane.INFORMATION_MESSAGE);
                break;
            } else {                                                              //INVALID OPTION
                String invalidMessage = (String) ois.readObject();
                JOptionPane.showMessageDialog(null,invalidMessage,
                        "Goodbye",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
