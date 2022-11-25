package test.import_CSV_FILES;

import com.opencsv.CSVReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class importDataIntoDB {
    public static String getJdbcURL() {
        return jdbcURL;
    }

    public static void setJdbcURL(String jdbcURL) {
        importDataIntoDB.jdbcURL = jdbcURL;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        importDataIntoDB.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        importDataIntoDB.password = password;
    }

    public static String getCsvFilePath() {
        return csvFilePath;
    }

    public static void setCsvFilePath(String csvFilePath) {
        importDataIntoDB.csvFilePath = csvFilePath;
    }

    static String jdbcURL = "jdbc:mysql://localhost:3306/oops";
    static String username = "root";
    static String password = "siddharthav45@sql";

    static String csvFilePath = "C:/Users/V SIDDHARTHA/Downloads/data.csv";

    public void upload(String del)
    {
        CSVReader reader = null;
        ArrayList<String[]> a = new ArrayList<>();
        try
        {
            Connection con = DriverManager.getConnection(jdbcURL,username,password);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(del);
            reader = new CSVReader(new FileReader(csvFilePath));
            String [] nextLine;
            while ((nextLine = reader.readNext()) != null)
            {
                a.add(nextLine);
            }
            for(int i=1;i<a.size();i++)
            {
                String[] token = a.get(i);
                String p = token[5];
                JSONObject temp = (JSONObject) new JSONParser().parse(p);
                String social;
                if(token[4].equals(""))
                {
                    social="";
                }
                else social = token[4];
                String s = "insert into users2 values ("+Integer.parseInt(token[0])+",'"+token[1]+"',"+Integer.parseInt(token[2])+",'"+social+"','"+temp+"','"+token[6]+"','"+token[3]+ "');";
                stmt.executeUpdate(s);
            }
        }  catch (IOException ex) {
            System.err.println(ex);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}