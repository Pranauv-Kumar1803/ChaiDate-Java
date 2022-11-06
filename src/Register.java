import org.json.simple.JSONObject;

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
        System.out.printf("Age Noted\n");
    }

    public void setGender(String gender) {
        while(gender.isEmpty()){
            Scanner in = new Scanner(System.in);
            System.out.println("This Field Can't be Empty");
            System.out.print("Enter Your Gender : Male or Female : ");
            gender = in.next();
        }
        this.gender = gender;
        System.out.printf("Gender Noted\n");
    }

    public void setSocialHandle(String socialHandle) {
        while(socialHandle.isEmpty()){
            Scanner in = new Scanner(System.in);
            System.out.print("Filed Can't be Empty");
            System.out.print("Enter any Social Handel : ");
            socialHandle = in.next();
        }
        this.socialHandle = socialHandle;
        System.out.printf("Social Handle Noted\n");
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
}
