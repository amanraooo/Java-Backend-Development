import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCInsertDemo {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mystd1",
                    "root",
                    "root"
            );

            Statement statement = con.createStatement();
            String query = "INSERT INTO student (id, stdName, age) VALUES (5, 'Rohit', 22)";
            int update = statement.executeUpdate(query);

            System.out.println("Inserted " + update + " row(s)");
            System.out.println("----Data Inserted----");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
