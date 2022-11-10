import org.json.simple.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

class MysqlCon{
    public static String Login(){
        String userName,Password, confirmPassword;
        Scanner in = new Scanner(System.in);

        System.out.println("Enter A Username : ");
        userName = in.nextLine();

        System.out.println("Enter A Password : ");
        Password = in.nextLine();

        return "sd";
    }

    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/oops";
        String username = "root";
        String password = "Darshan2003Bennur";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception e){
            System.out.println(e);
        }

        System.out.println("Welcome To Chai Date !! ");
        int response = -1;
        while(true){
            System.out.print("Enter 1 to SignIn or else 0 for SignUp : ");
            response = in.nextInt();
            while(response < 0){
                System.out.println("Invalid Entry");
                System.out.print("Enter 1 to SignIn or else 0 for SignUp : ");
                response = in.nextInt();
            }
            if (response == 1){
                Login l = new Login();
                l.LoginUser();
                System.out.println("Welcome!! Let's Find Your Potential Matches : ");
            }
            else if(response == 0){
                Register r = new Register();
                String s = r.RegisterUser();
                try{
                    Connection con = DriverManager.getConnection(url,username,password);
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(s);
                }catch(SQLException s1) {
                    s1.printStackTrace();
                }
            }
            else
                System.out.println("Invalid Response");
        }

    }
} 