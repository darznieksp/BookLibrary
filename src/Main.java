
public class Main {

    public static void main(String[] args) {
        DatabaseConnection conn = new DatabaseConnection();
        Menu.terminalMenu(conn.getConnection());

        conn.closeConnection();
    }
}
