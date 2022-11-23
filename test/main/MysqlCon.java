package test.main;

import test.Login;
import test.Register;
import test.import_CSV_FILES.importDataIntoDB;
import test.operations.userOperations;
import test.userFunc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class MysqlCon{

    //COMPOSITION

    static Login l;
    static Register r;


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
        String password = "siddharthav45@sql";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception e){
            System.out.println(e);
        }

        System.out.println("Welcome To Chai Date !! ");
        int response = -1;
        while(true){
            System.out.print("Enter 0 for SignUp,  1 to SignIn and, 2 to upload a csv file of users,  -1 to exit the app: ");
            response = in.nextInt();
//            while(response < 0){
//                System.out.println("Invalid Entry");
//                System.out.print("Enter 1 to SignIn or else 0 for SignUp : ");
//                response = in.nextInt();
//            }
            if (response == 1){
                 l = new Login();
                int id = l.LoginUser();
                System.out.println("                                                      ------------------- Dashboard -------------------                                                        ");
                int c = 1;
                while(c!=-1) {
                    System.out.println("press \n1 to view your profile, \n2 to search for users, \n3 to find your potential matches , \n4 for editing your profile, \n5 for deleting your account and \n6 for search by name or search by partial name and -1 to logout");
                    c = in.nextInt();
                    if (c == 1) {
                        System.out.println("Here is your profile :-");
                        userOperations.searchById(id);

                    } else if (c == 2) {
                        System.out.println("enter the id you want to search for :-");
                        int sid = in.nextInt();
                        userOperations.searchById(sid);

                    } else if (c == 3) {
                        System.out.println("Welcome!! Let's Find Your Potential Matches : ");
                        System.out.println(id);

                        System.out.print("Do you want to date a male(M) or a female(F) : ");
                        String pr = in.next();
                        userFunc u = new userFunc();
                        u.findMatches(id,pr);
                    }
                    else if(c==4)
                    {
                        System.out.println("what do you want to update? n for name , s for social handle , p for pwd");
                        char up = in.next().charAt(0);
                        if(up=='n')
                        {
                            System.out.println("enter the new name");
                            String val = in.next();
                            userOperations.updateById(id,"name",val);
                        }

                        else if(up=='s')
                        {
                            System.out.println("enter the new social handle");
                            String val = in.next();
                            userOperations.updateById(id,"socialHandles",val);
                        }
                        else if(up=='p')
                        {
                            System.out.println("enter the new password");
                            String val = in.next();
                            userOperations.updateById(id,"pwd",val);
                        }
                    }
                    else if(c==5)
                    {
                        System.out.println("Are you sure that you want to delete your account? ");
                        System.out.println("Enter your Id to continue");
                        int check = in.nextInt();
                        if(check==id)
                        {
                            userOperations.deleteById(id);
                            break;
                        }
                        else {
                            System.out.println("Wrong Id... Aborting Delete!!!");
                        }
                    }
                    else if(c==6){
                        System.out.println("Enter the name or partial name you want to search for");
                        String name = in.next();
                        System.out.println("The results for the Name "+name+" are as follows");
                        userOperations.searchByName(name);
                    }
                    else {
                        break;
                    }
                }

//                if(flag==-1) break;
            }
            else if(response == 0){
                r = new Register();
                String s = r.RegisterUser();
                try{
                    Connection con = DriverManager.getConnection(url,username,password);
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(s);
                }catch(SQLException s1) {
                    s1.printStackTrace();
                }
            }
            else if(response==-1)
            {
                System.out.println("Closing The App .........................................");
                System.out.println("See You Later!!!!!!   Bye .... ");
                System.out.println(":/");
                break;
            }
            else if(response==2)
            {
                importDataIntoDB u = new importDataIntoDB();
                u.upload();
            }
            else
                System.out.println("Invalid Response");
        }

    }
}