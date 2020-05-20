package helpers;

import models.PatientModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientHelper {

    private Connection connect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }

    /*
        Craete New Patient Record
     */
    public boolean insert(String fName, String lName, String address, String email) {

        try {
            Connection con = connect();
            if (con == null) {
                return false;
            }

            String query = " insert into patients (first_name,last_name, address,email ) "
                    + " values ( ?, ?, ?,? )";

            PreparedStatement statement = con.prepareStatement(query);

            statement.setString(1, fName);
            statement.setString(2, lName);
            statement.setString(3, address);
            statement.setString(4, email);

            statement.execute();
            con.close();
            return true;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    /*
        Get All Patient Records
     */
    public List<PatientModel> get() {
        try {
            Connection con = connect();

            if (con == null) {
                return null;
            }
            List<PatientModel> patients = new ArrayList<>();
            String query = "select * from patients";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String id = Integer.toString(rs.getInt("id"));
                String fName = rs.getString("first_name");
                String lName = rs.getString("last_name");
                String address = rs.getString("address");
                String Email = rs.getString("email");

                PatientModel patient = new PatientModel(id, fName, lName, address, Email);
                patients.add(patient);
            }
            con.close();
            return patients;

        } catch (Exception e) {
            return null;
        }

    }

    /*
        Update Patient Record
     */
    public boolean update(String ID, PatientModel patient) {
        try {
            Connection con = connect();

            if (con == null) {
                return false;
            }

            String query = "UPDATE patients SET first_name=?,last_name=?,address=?,email=? WHERE id=?";

            PreparedStatement statement = con.prepareStatement(query);

            statement.setString(1, patient.getfName());
            statement.setString(2, patient.getlName());
            statement.setString(3, patient.getAddress());
            statement.setString(4, patient.getEmail());
            statement.setInt(5, Integer.parseInt(ID));

            statement.execute();
            con.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    /*
    Delete Patient Record
     */
    public boolean delete(String id) {
        try {
            Connection con = connect();

            if (con == null) {
                return false;
            }

            String query = "delete from patients where id=?";

            PreparedStatement statement = con.prepareStatement(query);

            statement.setInt(1, Integer.parseInt(id));
            statement.execute();
            con.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /*
    Get Patient Record By ID
     */
    public PatientModel getById(String id) {

        try {
            Connection con = connect();

            if (con == null) {
                return null;
            }

            PatientModel patient = null;
            String query = "select * from patients where id=?";
            PreparedStatement statement = con.prepareStatement(query);

            statement.setInt(1, Integer.parseInt(id));
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String address = rs.getString("address");
                String Email = rs.getString("email");
                patient = new PatientModel(id, address, firstName, lastName, Email);
            }
            con.close();
            return patient;
        } catch (Exception e) {
            return null;
        }
    }

}
