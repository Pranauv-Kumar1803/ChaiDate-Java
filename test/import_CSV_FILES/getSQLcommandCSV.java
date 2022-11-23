package test.import_CSV_FILES;

public class getSQLcommandCSV {
    static String sqlCommand ;
    int idUsers;
    public getSQLcommandCSV(int idUsers) {
        this.idUsers = idUsers;
    }

    public int getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(int idUsers) {
        this.idUsers = idUsers;
    }

    public static String getSqlCommand() {
        return sqlCommand;
    }

    public static void setSqlCommand(String sqlCommand) {
        getSQLcommandCSV.sqlCommand = sqlCommand;
    }
}
