import java.sql.*;
import java.util.Scanner;

public class Login implements Authentication {
    String userName;
    String Password;

    public Login(){

    }
    public Login(String u,String p)
    {
        this.userName=u;
        this.Password=p;
    }

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
    public void getInTouchWithDatabase(String u,String p) {
        String url = "jdbc:mysql://localhost:3306/oops";
        String username = "root";
        String password = "Darshan2003Bennur";

        try{
            Connection con = DriverManager.getConnection(url,username,password);
            Statement stmt = con.createStatement();

            String s = "select id from users where name='"+u+" ' and pwd='"+p+"';";
//            System.out.println(s);
            ResultSet rs = stmt.executeQuery(s);

            if (!rs.isBeforeFirst() ) {
                System.out.println("wrong username or password");
                LoginUser();
            }
            else
            {
                while(rs.next())
                {
                    int p1 = rs.getInt(1);
                    System.out.println(p1);
                    System.out.println("successfully logged in");
                    return ;
                }
            }

        }catch(SQLException s)
        {
            s.printStackTrace();
        }
    }

    @Override
    public String RegisterUser() {
        return null;
    }


    @Override
    public void LoginUser() {
        Scanner in = new Scanner(System.in);

        System.out.println("enter username");
        String u = in.next();
        System.out.println("enter password");
        String p = in.next();
        getInTouchWithDatabase(u,p);
    }
}

