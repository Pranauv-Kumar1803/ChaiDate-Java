package test;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class userFunc {

    Scanner in = new Scanner(System.in);
    String url = "jdbc:mysql://localhost:3306/oops";
    String username = "root";
    String password = "siddharthav45@sql";
    public void findMatches(int id,String pr)
    {

        List<Integer> matches = new ArrayList<>();

        try{
            Connection con = DriverManager.getConnection(url,username,password);
            Statement stmt = con.createStatement();

            String s = "select * from matches where userId="+id+";";
            ResultSet rs = stmt.executeQuery(s);
            while(rs.next())
            {
                int i = rs.getInt(1);
                String s1 = rs.getString(2);
//                System.out.println(s1);
                JSONObject matchesJson = (JSONObject) new JSONParser().parse(s1);
                for(Object key : matchesJson.keySet())
                {
                    int search = Integer.parseInt(key.toString());
                    matches.add(search);
                }
            }

            ArrayList<Integer> likes= new ArrayList<Integer>();
            System.out.println("Potential matches for you are : ");
            for(int match : matches)
            {
                String str = "select id,name,age,socialHandles from users where id="+match+" and gender='"+pr.toUpperCase()+"';";
                ResultSet rs1 = stmt.executeQuery(str);
                while(rs1.next())
                {
                    int matchId = rs1.getInt(1);
                    if(matchId!=id) {
                        System.out.println("-------------------------------------------");
                        String name = rs1.getString(2);
                        int age = rs1.getInt(3);
                        String social = rs1.getString(4);
                        System.out.println(name);
                        System.out.println(age);
                        System.out.println(social);
                        System.out.println("-------------------------------------------");
                        System.out.print("Do you Like him/her : ");
                        int like = in.nextInt();
                        if (like == 1)
                            likes.add(matchId);
                    }
                }
            }

            String check = "select userId,liked from likes where userId="+id+";";
            ResultSet rs2 = stmt.executeQuery(check);
            if(!rs2.isBeforeFirst())
            {
                JSONObject newjson = new JSONObject();
                for(Integer key : likes)
                    newjson.put(key,1);

//                System.out.println(newjson);
                String insert = "insert into likes values ("+id+",'"+newjson+"');";
                stmt.executeUpdate(insert);
            }
            else
            {
                JSONObject temp = new JSONObject();
                while(rs2.next())
                {
//                    System.out.println("Inside while loop");
                    String jsonobj = rs2.getString(2);
                    temp = (JSONObject) new JSONParser().parse(jsonobj);
                    for(int like : likes)
                    {
                        if(!temp.containsKey(Integer.toString(like)))
                            temp.put(Integer.toString(like),1);
                    }
//                    System.out.println(temp);
                }
                stmt.executeUpdate("update likes set liked = '" + temp + "' where userId = " + id + ";");
            }

//            System.out.println(likes);
            int lFlag=-1;
            int mid=-1;
            ArrayList<Integer> arr = new ArrayList<>();
            for(Integer l:likes)
            {
//                System.out.println(l);
                String q = "select userId,liked from likes where userId = "+l+";";
                ResultSet rs3 = stmt.executeQuery(q);
                while(rs3.next())
                {
//                    System.out.println(rs3.getString(2)+"   "+rs3.getInt(1));
                    JSONObject temp = (JSONObject) new JSONParser().parse(rs3.getString(2));
                    if(temp.containsKey(Integer.toString(id)))
                    {
                        mid = rs3.getInt(1);
                        arr.add(mid);
                    }
                }
            }

            if(arr.size()>1)
            {
                JSONObject t = new JSONObject();
                System.out.println("You two have liked each other !!! Wanna know more? Go visit their Social Handle and enjoy!!");
                for(int maid:arr)
                {
                    String q1= "select name,age,socialHandles,gender from users where id = "+maid+";";
                    ResultSet rs4 = stmt.executeQuery(q1);
                    while(rs4.next())
                    {
                        String sname = rs4.getString(1);
                        int age = rs4.getInt(2);
                        String social = rs4.getString(3);
                        String g = rs4.getString(4);

                        System.out.println("------------------ Person -----------------");
                        System.out.println("Name : "+sname);
                        System.out.println("Age : "+age);
                        System.out.println("Social Handle : "+social);
                        System.out.println("Gender : "+g);
                        System.out.println("--------------------------------------------");
                        t.put(maid,1);
                    }
                    String q11= "insert into matched values ("+id+",'"+t+"');";
//                    System.out.println(q11);
                    stmt.executeUpdate(q11);
                }

            }
            else {
                System.out.println("You are unique!! We Got You Covered! :)");
            }
        }catch(SQLException s)
        {
            s.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void findAlreadyMatched(int id) throws SQLException, ParseException {
        Connection con = DriverManager.getConnection(url,username,password);
        Statement stmt = con.createStatement();
        JSONObject temp = new JSONObject();

        String q1= "select matched from matched where userId = "+id+";";
        ResultSet rs4 = stmt.executeQuery(q1);
        while(rs4.next())
        {
            String json = rs4.getString(1);
            temp = (JSONObject) new JSONParser().parse(json);
        }

        if(temp.size()>=1)
        {
            System.out.println("People You have Been Matched With");
            for(Object key : temp.keySet())
            {
                String search = (String)key;
                int mid = Integer.parseInt(search);
                String query= "select name,age,socialHandles,gender from users where id = "+mid+";";
                ResultSet rs5 = stmt.executeQuery(query);
                while(rs5.next())
                {
                    String sname = rs5.getString(1);
                    int age = rs5.getInt(2);
                    String social = rs5.getString(3);
                    String g = rs5.getString(4);

                    System.out.println("------------------ Person -----------------");
                    System.out.println("Name : "+sname);
                    System.out.println("Age : "+age);
                    System.out.println("Social Handle : "+social);
                    System.out.println("Gender : "+g);
                    System.out.println("--------------------------------------------");
                }
            }
        }
        else{
            System.out.println("Not Matched with anybody before");
        }

    }
}