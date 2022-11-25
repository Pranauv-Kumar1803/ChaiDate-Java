package test.import_CSV_FILES;

public class getSQLcommandCSV {
    static String sqlCommand ;
    int idUsers;
    public getSQLcommandCSV() {
    }

    public static String getDeleteCommand()
    {
        sqlCommand="delete from users2";
        return sqlCommand;
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
