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
        Scanner sc = new Scanner(System.in);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.flush();

        //account setting
        while (true) {
            System.out.println("What do you want to do\n(1) Log in \n(2) Create an Account ");
            String accountOption = sc.nextLine();
            oos.writeObject(accountOption);
            if (accountOption.equals("1")) {
                System.out.println("Enter your account name:");
                String name = sc.nextLine();
                System.out.println("Enter your password:");
                String password = sc.nextLine();
                oos.writeObject(name);
                oos.writeObject(password);
                if ((boolean) ois.readObject()) {
                    System.out.println("LOGGED IN!");
                    break;
                } else {
                    System.out.println("Check your username and password again!");
                }
            } else if (accountOption.equals("2")) {
                System.out.println("Enter your new account name:");
                String nName = sc.nextLine();
                System.out.println("Enter your new password");
                String nPassword = sc.nextLine();
                if (!nName.equals("") && !nPassword.equals("")) {
                    oos.writeObject(nName);
                    oos.writeObject(nPassword);
                    if ((boolean) ois.readObject()) {
                        System.out.println("This account name is already exist try again with different name!");
                    } else {
                        System.out.println("Added!");
                    }
                } else
                    System.out.println("You need to type something!");
            }
        }

        System.out.println(ois.readObject());
        System.out.println("Welcome to Posting Program");
        while (true) {
            System.out.println(ois.readObject());           //general options
            String option = sc.nextLine();
            oos.writeObject(option);


            if (option.equals("1")) {                     //posting
                System.out.println(ois.readObject());            //printing post options
                String postOption = sc.nextLine();
                oos.writeObject(postOption);


                if (postOption.equals("1")) {            //listing
                    System.out.println(ois.readObject());


                } else if (postOption.equals("2")) {

                    //editing
                    if (!(boolean) ois.readObject()) {
                        System.out.println(ois.readObject());   //Which one do you want to edit
                        String whichPost = sc.nextLine();
                        oos.writeObject(whichPost);
                        if (!whichPost.equals("") && isNumeric(whichPost)) {
                            if ((boolean) ois.readObject()) {
                                System.out.println(ois.readObject());                     //editing options
                                String edit = sc.nextLine();
                                oos.writeObject(edit);
                                if (edit.equals("1") || edit.equals("2") || edit.equals("3")) {
                                    System.out.println(ois.readObject());
                                    String nTitle = sc.nextLine();
                                    oos.writeObject(nTitle);
                                    System.out.println(ois.readObject());
                                } else
                                    System.out.println("INVALID Option!");
                            } else
                                System.out.println("INVALID Option!");
                        } else
                            System.out.println("INVALID Option!");
                    } else
                        System.out.println("You have not written any posts");


                } else if (postOption.equals("3")) {      //creating

                    System.out.println(ois.readObject());
                    String title = sc.nextLine();
                    oos.writeObject(title);

                    System.out.println(ois.readObject());
                    String context = sc.nextLine();
                    oos.writeObject(context);

                    System.out.println(ois.readObject());
                    String authorName = sc.nextLine();
                    oos.writeObject(authorName);


                    System.out.println(ois.readObject()); //whether fail or success to create post message


                } else if (postOption.equals("4")) {      //deleting

                    boolean empty = (boolean) ois.readObject();
                    if (!empty) {
                        System.out.println(ois.readObject());
                        String whichPost = sc.nextLine();
                        int size = (Integer) ois.readObject();
                        oos.writeObject(whichPost);
                        if (!whichPost.equals("") && isNumeric(whichPost) && Integer.parseInt(whichPost) <= size
                                && Integer.parseInt(whichPost) > 0) {

                            System.out.println(ois.readObject());
                        } else
                            System.out.println("INVALID Option!");
                    } else
                        System.out.println("You haven't written any posts!");
                } else
                    System.out.println("INVALID Option!");

            } else if (option.equals("2")) {              //account setting
                System.out.println(ois.readObject());
                String editOrDelete = sc.nextLine();
                oos.writeObject(editOrDelete);


                if (editOrDelete.equals("1")) {                          //edit account
                    System.out.println("(1)Edit Account name" +
                            "\n(2)Edit Password");
                    String editOption = sc.nextLine();
                    oos.writeObject(editOption);
                    if (editOption.equals("1")) {                   //edit account name
                        String nAccount = "";
                        while (true) {
                            System.out.println("Enter your new Account name:");
                            nAccount = sc.nextLine();
                            if (nAccount.equals("")) {
                                System.out.println("Invalid Input!");
                            } else
                                break;
                        }
                        oos.writeObject(nAccount);
                    } else if (editOption.equals("2")) {            //edit account password
                        String nPassword = "";
                        while (true) {
                            System.out.println("Enter your new Account Password");
                            nPassword = sc.nextLine();
                            if (nPassword.equals("")) {
                                System.out.println("Invalid Input!");
                            } else
                                break;
                        }
                        oos.writeObject(nPassword);
                    }


                } else if (editOrDelete.equals("2")) {                  //delete account
                    System.out.println("Are you sure to delete your account?(y/n)");
                    String yn = sc.nextLine();
                    oos.writeObject(yn);
                    if (yn.equalsIgnoreCase("y")) {
                        System.out.println(ois.readObject());
                        System.out.println("See you again!");
                        break;
                    }
                }
                System.out.println(ois.readObject());


            } else if (option.equals("3")) {
                System.out.println(ois.readObject());
                break;
            } else
                System.out.println(ois.readObject());               //INVALID OPTION
        }
    }
}