import org.json.simple.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

class MysqlCon{

    public static String register(){
        Register newPerson = new Register();
        Scanner in = new Scanner(System.in);

        System.out.print("Enter A Username : ");
        newPerson.setUserName(in.nextLine());

        System.out.print("Enter Your Age : ");
        newPerson.setAge(in.nextInt());

        System.out.print("Enter Your Gender : M or F : ");
        newPerson.setGender(in.next());

        System.out.print("Enter any Social Handel : ");
        newPerson.setSocialHandle(in.next());

        ArrayList<String> preferences = new ArrayList<String>();

        System.out.println("Choose your Preferences");
        System.out.println("1. Gym");
        preferences.add("Gym");
        System.out.println("2. Geek");
        preferences.add("Geek");
        System.out.println("3. Movie Buff");
        preferences.add("Movie Buff");
        System.out.println("4. Introvert");
        preferences.add("Introvert");
        System.out.println("5. Night Owl");
        preferences.add("Night Owl");
        System.out.println("6. Gear Head");
        preferences.add("Gear Head");
        System.out.println("7. Foodie");
        preferences.add("Foodie");
        System.out.println("8. Traveler");
        preferences.add("Traveler");
        System.out.println("9. Book Worm");
        preferences.add("Book Worm");
        System.out.println("10. Music");
        preferences.add("Music");
        System.out.println("11. Sports");
        preferences.add("Sports");

        JSONObject jsonObject = new JSONObject();
        System.out.println("Enter 1 if you want to Choose the Preference or else 0");
        int preferenceResult;
        for (int i = 0; i < 11; i++) {
            System.out.print(preferences.get(i) + " : ");
            preferenceResult = in.nextInt();
            if (preferenceResult == 1){
                jsonObject.put(preferences.get(i),1);
            }
        }

        System.out.print("Enter A Password : ");
        newPerson.setPassword(in.next());

        System.out.print("Re-Enter Password : ");
        newPerson.setConfirmPassword(in.next());

        while(!newPerson.getPassword().equals(newPerson.getConfirmPassword())){
            System.out.println("Password Mis-Match Try Again");
            System.out.print("Enter A Password : ");
            newPerson.setPassword(in.next());

            System.out.print("Re-Enter Password : ");
            newPerson.setConfirmPassword(in.next());
        }
        System.out.printf("Password Noted\n");

        return "insert into users values(0,'" + newPerson.getUserName() + "'," + newPerson.getAge() + ",'" + newPerson.getGender()
                + "','" + newPerson.getSocialHandle() + "','"+ jsonObject + "','"+ newPerson.getPassword() + "')";

    }

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

        try{
            Connection con = DriverManager.getConnection(url,username,password);
            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("select * from likes");
//            while(rs.next())
//            {
//
//                System.out.println(rs.getInt(1)+" "+rs.getString(2));
//                int p = rs.getInt(1);
//                String j = rs.getString(2);
//            }
            String qu = register();
//            System.out.println(qu);
//            ResultSet rs = stmt.executeQuery("select * from cred");
            stmt.executeUpdate(qu);
//            while(rs.next()){
//                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
//            }
        }catch (SQLException e){
            e.printStackTrace();
        }



    }
} 