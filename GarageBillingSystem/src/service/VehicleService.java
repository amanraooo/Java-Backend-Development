package service;

import config.DBconfig;
import entity.Customer;
import entity.vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleService {

    public void addVehicle(vehicle vehicle) throws SQLException
    {
        Connection conn = DBconfig.getConnection();
        PreparedStatement ps =
                conn.prepareStatement("INSERT INTO vehicles (id, customer_id, number_plate, model) VALUES (?,?,?,?)");
        ps.setInt(1,vehicle.getId());
        ps.setInt(2,vehicle.getCustomerId());
        ps.setString(3, vehicle.getNumber_plate());
        ps.setString(4, vehicle.getModel());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public vehicle getAllCustomersBasedOnVehicleNum(String vehicleNum) throws SQLException {
        vehicle vehicle = new vehicle();
        Connection conn = DBconfig.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * from vehicles where phone = " + vehicleNum);

        while (rs.next()) {
            vehicle = new vehicle(rs.getInt("customer_id"),
                    rs.getInt("id"), rs.getString("number_plate"), rs.getString("model"));
        }

        return vehicle;
    }
}