package test;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class MysqlCon{

    static JSONObject jsonObject;

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
        preferences.add("gym");
        System.out.println("2. Geek");
        preferences.add("geek");
        System.out.println("3. Movie Buff");
        preferences.add("movie_buff");
        System.out.println("4. Introvert");
        preferences.add("introvert");
        System.out.println("5. Night Owl");
        preferences.add("night_owl");
        System.out.println("6. Gear Head");
        preferences.add("gear_head");
        System.out.println("7. Foodie");
        preferences.add("foodie");
        System.out.println("8. Traveler");
        preferences.add("traveler");
        System.out.println("9. Book Worm");
        preferences.add("book_worm");
        System.out.println("10. Music");
        preferences.add("music");
        System.out.println("11. Sports");
        preferences.add("sports");

        jsonObject = new JSONObject();
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

        return "insert into data values(0,'" + newPerson.getUserName() + "'," + newPerson.getAge() + ",'" + newPerson.getGender()
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
        String password = "siddharthav45@sql";

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
//



            ArrayList<Integer> list = workWithData(stmt);
            //list contains id's of all users with same prefs >= 2
            System.out.println(Arrays.toString(list.toArray()));




//            System.out.println(qu);
//            ResultSet rs = stmt.executeQuery("select * from cred");


            stmt.executeUpdate(qu);
//

//            while(rs.next()){
//                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
//            }
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }

    private static ArrayList<Integer> workWithData(Statement stmt) throws SQLException, ParseException {

        ResultSet set = stmt.executeQuery("select  * from data");

        ArrayList<Integer> list = new ArrayList<>();
        while (set.next()){

            Integer id = set.getInt(1);
            String str = set.getString(6);
            JSONObject temp = (JSONObject) new JSONParser().parse(str);
            int count = 0;
            for(Object key : temp.keySet())
            {
                String search = (String)key;
//                    System.out.println(search);
                for(Object userPref : jsonObject.keySet()){
                    System.out.println(userPref);
                    if(search.equals(userPref))
                    {
                        count++;
//                            System.out.println((String) userPref);
                    }
                }
            }

            if(count>=2)
                list.add(id);

//                System.out.println(temp);
        }

        return list;


    }
} 