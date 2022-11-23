package test;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.*;
import java.util.ArrayList;
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
    public int getInTouchWithDatabase(String u,String p) {
        String url = "jdbc:mysql://localhost:3306/oops";
        String username = "root";
        String password = "siddharthav45@sql";
        int id=-1;
        try{
            Connection con = DriverManager.getConnection(url,username,password);
            Statement stmt = con.createStatement();

            String s = "select id,pref from users where name='"+u+"' and pwd='"+p+"';";
            ResultSet rs = stmt.executeQuery(s);

            if (!rs.isBeforeFirst() ) {
                System.out.println("wrong username or password");
                id = LoginUser();
            }
            else
            {
                while(rs.next()){
                    int p1 = rs.getInt(1);
                    id = p1;
                    String str = rs.getString(2);
                    try {
                        JSONObject temp = (JSONObject) new JSONParser().parse(str);
                        workWithData(temp,p1);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
                return id;
            }

        }catch(SQLException s)
        {
            s.printStackTrace();
        }
        return id;

    }

    @Override
    public String RegisterUser() {
        return null;
    }


    @Override
    public int LoginUser() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter Username : ");
        String u = in.next();
        System.out.print("Enter Password : ");
        String p = in.next();
        int id = getInTouchWithDatabase(u,p);
        return id;
    }

    private static void workWithData(JSONObject jsonObject,int userId) throws SQLException, ParseException {

        String url = "jdbc:mysql://localhost:3306/oops";
        String username = "root";
        String password = "siddharthav45@sql";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception e){
            System.out.println(e);
        }

        ArrayList<Integer> list = new ArrayList<>();
        try{
            Connection con = DriverManager.getConnection(url,username,password);
            Statement stmt = con.createStatement();

            ResultSet set = stmt.executeQuery("select  * from users");
            while (set.next()){
                Integer id = set.getInt(1);
                String str = set.getString(6);
                JSONObject temp = (JSONObject) new JSONParser().parse(str);
                int count = 0;
                for(Object key : temp.keySet())
                {
                    String search = (String)key;
                    for(Object userPref : jsonObject.keySet()){
                        if(search.equals(userPref))
                            count++;
                    }
                }

                if(count>=2)
                    list.add(id);
            }

            JSONObject have = new JSONObject();
            for(Integer key : list){
                have.put(key,1);
            }

            String resultingQuery;
            ResultSet queryCheck = stmt.executeQuery("select * from matches");
            while(queryCheck.next()){
                Integer id = queryCheck.getInt(1);
                if(id == userId){
                    resultingQuery = "update matches set matches = '" + have + "' where userID =" + userId + ";";
                    stmt.executeUpdate(resultingQuery);
                    return;
                }
            }
            resultingQuery = "insert into matches values(" + userId + ",'" + have + "');";
            stmt.executeUpdate(resultingQuery);

        }catch(SQLException s1) {
            s1.printStackTrace();
        }
    }

}

