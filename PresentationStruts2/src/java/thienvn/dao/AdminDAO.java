/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import thienvn.connection.MyConnection;
import thienvn.dto.AvengerDTO;
import thienvn.dto.MissionDTO;

/**
 *
 * @author thien
 */
public class AdminDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public ArrayList<AvengerDTO> loadUser() throws ClassNotFoundException, SQLException {
        ArrayList<AvengerDTO> list = new ArrayList<>();
        con = MyConnection.getMyConnection();
        if (con != null) {
            stm = con.prepareStatement("select UserName,Role,Password,Image from tblUser where Status=? ");
            stm.setString(1, "1");
            rs = stm.executeQuery();
            while (rs.next()) {
                String name = rs.getString("UserName");
                String role = rs.getString("Role");
                String pass = rs.getString("Password");
                String image = rs.getString("Image");
              
                    AvengerDTO dto = new AvengerDTO(name, pass, role, image);
                    list.add(dto);

            }

        }
        closeConnection();
        return list;
    }

    public boolean deleteUser(String name) throws ClassNotFoundException, SQLException {
        boolean check = false;
        con = MyConnection.getMyConnection();
        if (con != null) {
             stm = con.prepareStatement("update tblUser set Status=? where UserName=?");
            stm.setInt(1, 0);
            stm.setString(2, name);
            check = stm.executeUpdate() > 0;
        }
        closeConnection();
        return check;
    }

   

    public boolean insertUser(AvengerDTO dto) throws ClassNotFoundException, SQLException {
        boolean check = false;
        con = MyConnection.getMyConnection();
        if (con != null) {
            stm = con.prepareStatement("insert into tblUser (UserName,Role,Password,Image,Status) values (?,?,?,?,?)");
            stm.setString(1, dto.getName());
            stm.setString(2, dto.getRole());
            stm.setString(3, dto.getPassword());
            stm.setString(4, dto.getImage());
            stm.setInt(5, 1);
            check = stm.executeUpdate() > 0;
        }
        closeConnection();
        return check;
    }

    public boolean updateUser(String role, String name, String imagepath) throws ClassNotFoundException, SQLException {
        boolean check = false;
        con = MyConnection.getMyConnection();
        if (con != null) {
            stm = con.prepareStatement("update tblUser set Role=?,Image=? where UserName=?");
            stm.setString(1, role);
            stm.setString(2, imagepath);
            stm.setString(3, name);
            check = stm.executeUpdate() > 0;
        }
        closeConnection();
        return check;
    }

    public boolean updateUserWithoutImage(String role, String name) throws ClassNotFoundException, SQLException {
        boolean check = false;
        con = MyConnection.getMyConnection();
        if (con != null) {
            stm = con.prepareStatement("update tblUser set Role=? where UserName=?");
            stm.setString(1, role);
            stm.setString(2, name);
            check = stm.executeUpdate() > 0;
        }
        closeConnection();
        return check;
    }

 

  

    public ArrayList<AvengerDTO> searchUserLike(String name) throws ClassNotFoundException, SQLException {
        ArrayList<AvengerDTO> list = new ArrayList<>();
        con = MyConnection.getMyConnection();
        if (con != null) {
            stm = con.prepareStatement("select Image,UserName,Role from tblUser where UserName like ? and Status=?");
            stm.setString(1, "%" + name + "%");
            stm.setString(2,"1");
            rs = stm.executeQuery();
            while (rs.next()) {
                String username = rs.getString("UserName");
                String role = rs.getString("role");
                String image = rs.getString("image");
                AvengerDTO dto = new AvengerDTO(username, role, image);
                list.add(dto);
            }
        }
        closeConnection();
        return list;
    }
    public boolean insertUserWithoutImage(AvengerDTO dto) throws ClassNotFoundException, SQLException {
        boolean check = false;
        con = MyConnection.getMyConnection();
        if (con != null) {
            stm = con.prepareStatement("insert into tblUser (UserName,Role,Password,Image,Status) values (?,?,?,?,?)");
            stm.setString(1, dto.getName());
            stm.setString(2, dto.getRole());
            stm.setString(3, dto.getPassword());
            stm.setString(4, "");
            stm.setInt(5, 1);
            check = stm.executeUpdate() > 0;
        }
        closeConnection();
        return check;
    }
}
