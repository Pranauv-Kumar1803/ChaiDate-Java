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

        String url = "jdbc:mysql://localhost:3306/oops";
        String username = "root";
        String password = "Darshan2003Bennur";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception e){
            System.out.println(e);
        }

//        for register
//        Register r = new Register();
//        String s = r.RegisterUser();
//
//        try{
//            Connection con = DriverManager.getConnection(url,username,password);
//            Statement stmt = con.createStatement();
//
//            stmt.executeUpdate(s);
//
//        }catch(SQLException s1)
//        {
//            s1.printStackTrace();
//        }

//        for login
        Login l = new Login();
        System.out.println(l.getUserName());

        l.LoginUser();



    }
} 