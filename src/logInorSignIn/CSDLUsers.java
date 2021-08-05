/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logInorSignIn;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Thanh Diệp
 */
public class CSDLUsers {
    private Connection connection;
    public CSDLUsers() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionURL = "jdbc:sqlserver://THANHDIEP\\SQLEXPRESS:1433;databaseName=UserManager;integratedSecurity=true;";
            connection = DriverManager.getConnection(connectionURL);
            System.out.println("Connect the Database SUCCEED!");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("Connect the Database FAIL!");
            System.err.println(e.getMessage());
        }
    }
    //add new DATA
    public boolean addSQL(UserInformation u) {
        String sql = "INSERT INTO UserInformation VALUES(?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, u.getUser());
            ps.setString(2, u.getPass());

            if(ps.executeUpdate()>0) {
                System.out.println("Add data SUCCEED!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error: "+e.toString());
        }finally {
            try {
                connection.close();

            } catch (SQLException e2) {}
        }
        return false;
    }
    
    //update data in SQL
	public boolean updateSQL(UserInformation u) {
        String sql = "UPDATE UserInformation SET PassWord = ? WHERE UserName =?";

        try {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, u.getPass());
                ps.setString(2, u.getUser());


                if(ps.executeUpdate()>0) {
                        System.out.println("Update data SUCCEED!");
                }

                return true;
        } catch (SQLException e) {
                System.err.println("Error: "+e.toString());
        }finally {
            try {
                    connection.close();

            } catch (SQLException e2) {
                    // TODO: handle exception
            }
        }
        return false;
    }
        
    //delete data in SQL
    public boolean deleteSQL(String user) {
        String sql = "DELETE UserInformation WHERE UserName=?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user);

            if(ps.executeUpdate()>0) {
                    System.err.println("DELETE DATA SUCCEED!");
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error: "+e.toString());
        }finally {
            try {
                    connection.close();

            } catch (SQLException e2) {
                    // TODO: handle exception
            }
        }
        return false;
    }
    
    //find by username
    public static ArrayList<UserInformation> findUser(String user){
        ArrayList<UserInformation> list= new ArrayList<UserInformation>();
        Connection c = null;

        try {
            String connectionURL = "jdbc:sqlserver://THANHDIEP\\SQLEXPRESS:1433;databaseName=UserManager;integratedSecurity=true;";
            c = DriverManager.getConnection(connectionURL);

            String sql = "SELECT UserName,PassWord FROM UserInformation WHERE UserName = ?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                    UserInformation u = new UserInformation(rs.getString("UserName"), rs.getString("PassWord"));			
                    list.add(u);
            }
        } catch (SQLException e) {
                System.err.println("Error: "+e.toString());
        }finally {
            try {
                    c.close();
            } catch (SQLException e2) {
                    // TODO: handle exception
            }
        }
        return list;
    }
    public static void main(String[] args) throws SQLException {
        CSDLUsers cs = new CSDLUsers();
        UserInformation u = new UserInformation("Ta Van Toan", "124563");
        UserInformation u1 = new UserInformation("Vu Van Kien", "123456");
//        System.out.println(cs.addSQL(u1));
//        System.out.println(cs.deleteSQL("Vu Van Kien"));
//        System.out.println(cs.updateSQL(u));
        System.out.println("=> "+cs.findUser("Ta Van Toan"));
    }
}
