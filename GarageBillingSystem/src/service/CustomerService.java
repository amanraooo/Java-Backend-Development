package service;

import config.DBconfig;
import entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    public void addCustomer(Customer customer) throws SQLException {
        Connection conn = DBconfig.getConnection();
        PreparedStatement ps =
                conn.prepareStatement("insert into customers (name,phone) values (?,?)");
        ps.setString(1,customer.getName());
        ps.setString(2,customer.getName());
        ps.executeUpdate();
        ps.close();
        conn.close();

    }

    public List<Customer> getAllCustomers() throws SQLException{
        List<Customer> list = new ArrayList<>();
        Connection conn = DBconfig.getConnection();
        Statement st= conn.createStatement();
        ResultSet rs = st.executeQuery("Select * from customers");

        while(rs.next()){
            list.add(new
                    Customer(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("phone")));
        }
        return list;
    }
}
