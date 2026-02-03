import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ScrollInsensitiveDemo {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //load driver class

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mystd", "root", "rohit@8708004879");

            Statement statement=con.
                    createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
            String query ="select * from student";

            ResultSet rs = statement.executeQuery(query);
            System.out.println("fetch data in not live update mode......");
            Thread.sleep(10000);
            rs.beforeFirst();
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("stdName") + " | "+
                                rs.getInt("age"));
            }

            rs.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}