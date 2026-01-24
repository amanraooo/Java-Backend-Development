import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCDeleteDemo {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mystd1",
                    "root",
                    "root"
            );

            Statement statement = con.createStatement();
            String query = "DELETE FROM student where id =2";
            int update = statement.executeUpdate(query);

            System.out.println("updated " + update + " row(s)");
            System.out.println("----Data DELETED----");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
