import org.json.simple.JSONObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Register extends Login{
    int age;
    String gender;
    String socialHandle;
    String confirmPassword;

    public Register(){

    }
    public Register(int age, String gender, String socialHandle, String confirmPass){
        this.age = age;
        this.gender = gender;
        this.socialHandle = socialHandle;
        this.confirmPassword = confirmPass;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getSocialHandle() {
        return socialHandle;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setAge(int age) {
        while(age <= 0){
            Scanner in = new Scanner(System.in);
            System.out.println("Age Can't be Negative");
            System.out.print("Enter Your Age : ");
            age = in.nextInt();
        }
        this.age = age;
        System.out.print("Age Noted\n");
    }

    public void setGender(String gender) {
        while(gender.isEmpty()){
            Scanner in = new Scanner(System.in);
            System.out.println("This Field Can't be Empty");
            System.out.print("Enter Your Gender : Male or Female : ");
            gender = in.next();
        }
        this.gender = gender;
        System.out.print("Gender Noted\n");
    }

    public void setSocialHandle(String socialHandle) {
        while(socialHandle.isEmpty()){
            Scanner in = new Scanner(System.in);
            System.out.print("Filed Can't be Empty");
            System.out.print("Enter any Social Handel : ");
            socialHandle = in.next();
        }
        this.socialHandle = socialHandle;
        System.out.print("Social Handle Noted\n");
    }

    public void setConfirmPassword(String confirmPassword) {
        while(confirmPassword.isEmpty()){
            Scanner in = new Scanner(System.in);
            System.out.println("Field Can't be Empty");
            System.out.println("Re-Enter confirmPassword to Match");
            confirmPassword = in.next();
        }
        this.confirmPassword = confirmPassword;
    }

    public boolean checkUsernameExists(String u)
    {
        String url = "jdbc:mysql://localhost:3306/oops";
        String username = "root";
        String password = "Darshan2003Bennur";

        try{
            Connection con = DriverManager.getConnection(url,username,password);
            Statement stmt = con.createStatement();

            ResultSet set = stmt.executeQuery("select name from users");
            while(set.next()) {
                if(set.getString(1).equals(u))
                    return true;
            }
        }catch(SQLException s1) {
            s1.printStackTrace();
        }
        return false;
    }
    @Override
    public void setUserName(String userName) {
        while(userName.isEmpty()){
            Scanner in = new Scanner(System.in);
            System.out.println("Username Can't be Blank");
            System.out.print("Enter A Username : ");
            userName = in.nextLine();
        }
        while(checkUsernameExists(userName))
        {
            Scanner in = new Scanner(System.in);
            System.out.println("Username already exists");
            System.out.print("Enter A Username again : ");
            userName = in.nextLine();
        }
        this.userName = userName;
        System.out.print("User Name Noted\n");
    }

    @Override
    public String RegisterUser() {
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

        ArrayList<String> preferences = new ArrayList<>();

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
        System.out.print("Password Noted\n");

        return "insert into users values(0,'" + newPerson.getUserName() + "'," + newPerson.getAge() + ",'" + newPerson.getGender()
                + "','" + newPerson.getSocialHandle() + "','"+ jsonObject + "','"+ newPerson.getPassword() + "')";
    }
}
