package test;

import java.util.Scanner;

public class Register extends Login{
    int age;
    String gender;
    String socialHandle;
    String confirmPassword;

    public Register(int age, String gender, String socialHandle, String confirmPass){
        this.age = age;
        this.gender = gender;
        this.socialHandle = socialHandle;
        this.confirmPassword = confirmPass;
    }

    public Register(){

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


}
