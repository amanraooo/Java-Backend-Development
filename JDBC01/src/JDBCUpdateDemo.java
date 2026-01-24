import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCUpdateDemo {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mystd1",
                    "root",
                    "root"
            );

            Statement statement = con.createStatement();
            String query = "UPDATE student SET age = 40 where id =2";
            int update = statement.executeUpdate(query);

            System.out.println("updated " + update + " row(s)");
            System.out.println("----Data updated----");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
