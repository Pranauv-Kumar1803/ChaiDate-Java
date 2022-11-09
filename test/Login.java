package test;

import java.util.Scanner;

public class Login implements Authentication {
    String userName;
    String Password;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        while(password.isEmpty()){
            Scanner in = new Scanner(System.in);
            System.out.println("Field can't be Empty");
            System.out.println("Re-Enter Password : ");
            password = in.next();
        }
        Password = password;
    }

    public void setUserName(String userName) {
        while(userName.isEmpty()){
            Scanner in = new Scanner(System.in);
            System.out.println("Username Can't be Blank");
            System.out.print("Enter A Username : ");
            userName = in.nextLine();
        }
        this.userName = userName;
        System.out.printf("User Name Noted\n");
    }

    @Override
    public void getInTouchWithDatabase() {
        
    }

    @Override
    public void RegisterUser() {

    }

    @Override
    public void LoginUser() {

    }
}

