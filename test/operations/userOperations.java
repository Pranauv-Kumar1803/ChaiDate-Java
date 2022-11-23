package test.operations;

import java.sql.*;

public class userOperations {
    static String url = "jdbc:mysql://localhost:3306/oops";
    static String username = "root";
    static String password = "siddharthav45@sql";

    public static void searchById(int id)
    {
        try{
            Connection con = DriverManager.getConnection(url,username,password);
            Statement stmt = con.createStatement();

            ResultSet set = stmt.executeQuery("select id,name,age,gender from users where id="+id+";");
            while(set.next())
            {
                int sid = set.getInt(1);
                String na = set.getString(2);
                int age = set.getInt(3);
                String g = set.getString(4);

                System.out.println("------------------ PROFILE -----------------");
                System.out.println("Name : "+na);
                System.out.println("Age : "+age);
                System.out.println("User Id : "+sid);
                System.out.println("Gender : "+g);
                System.out.println("--------------------------------------------");

            }
        }catch(SQLException s1) {
            s1.printStackTrace();
        }
    }

    public static void searchByName(String name)
    {
        try{
            Connection con = DriverManager.getConnection(url,username,password);
            Statement stmt = con.createStatement();

            ResultSet set = stmt.executeQuery("select id,name,age,gender from users where name LIKE '%"+name+"%'"+";");
            while(set.next())
            {
                int sid = set.getInt(1);
                String na = set.getString(2);
                int age = set.getInt(3);
                String g = set.getString(4);

                System.out.println("------------------ Search Result -----------------");
                System.out.println("Name : "+na);
                System.out.println("Age : "+age);
                System.out.println("User Id : "+sid);
                System.out.println("Gender : "+g);
                System.out.println("--------------------------------------------");
            }
        }catch(SQLException s1) {
            s1.printStackTrace();
        }
    }

    public static void updateById(int id,String name,String value) {
//        JSONObject j = (JSONObject) new JSONParser().parse(pref);
        try{
            Connection con = DriverManager.getConnection(url,username,password);
            Statement stmt = con.createStatement();

            stmt.executeUpdate("update users set " + name+"='"+ value + "' where id="+id+";");
        }catch(SQLException s1) {
            s1.printStackTrace();
        }
    }

    public static void deleteById(int id)
    {
        try{
            Connection con = DriverManager.getConnection(url,username,password);
            Statement stmt = con.createStatement();

            stmt.executeUpdate("delete from matches where userId="+id+";");
            stmt.executeUpdate("delete from likes where userId="+id+";");
            stmt.executeUpdate("delete from users where id="+id+";");
        }catch(SQLException s1) {
            s1.printStackTrace();
        }
    }
}
