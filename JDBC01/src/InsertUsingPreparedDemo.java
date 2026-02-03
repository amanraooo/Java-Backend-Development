import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertUsingPreparedDemo {

    /*1. load the driver class
      2. Get connection from db
      3. create statement
      4. execute query
    * */
    public static void main(String[] args)  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //load driver class

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mystd", "root", "rohit@8708004879");

            String query="INSERT INTO student (id,stdName,age) values (?,?,?)";

            PreparedStatement pstmt=con.prepareStatement(query);

            /*Scanner sc=new Scanner(System.in);
            System.out.println(" enter id for stduent: ");
            int id1=sc.nextInt();*/

            pstmt.setInt(1,102);
            pstmt.setString(2,"Ankit");
            pstmt.setInt(3,13);
            pstmt.executeUpdate();

            pstmt.setInt(1,103);
            pstmt.setString(2,"Raj");
            pstmt.setInt(3,23);
            pstmt.executeUpdate();


            System.out.println("multiple row inserted");
            con.close();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}