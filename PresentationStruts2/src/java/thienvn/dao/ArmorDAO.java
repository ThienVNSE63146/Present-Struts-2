/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import thienvn.connection.MyConnection;
import thienvn.dto.ArmorDTO;

/**
 *
 * @author thien
 */
public class ArmorDAO implements Serializable {

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

    public ArrayList<ArmorDTO> loadArmor() throws ClassNotFoundException, SQLException {
        ArrayList<ArmorDTO> list = new ArrayList<>();
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("select ArmorName,Image,Status from tblArmor where not Status=?");
                stm.setInt(1, 0);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("ArmorName");
                    String image = rs.getString("Image");
                   int status=rs.getInt("Status");
                   String statusString;
                   if(status==1){
                        statusString="ready";
                   }
                   else{
                        statusString="In Process";
                   }
                    ArmorDTO dto = new ArmorDTO(id, image, statusString);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public ArrayList<ArmorDTO> searchArmorLike(String name) throws ClassNotFoundException, SQLException {
        ArrayList<ArmorDTO> list = new ArrayList<>();
        try {
            con = MyConnection.getMyConnection();

            if (con != null) {
                stm = con.prepareStatement("select ArmorName,Image from tblArmor where ArmorName like ? and Status=?");
                stm.setString(1, "%" + name + "%");
                stm.setString(2, "1");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("ArmorName");
                    String image = rs.getString("Image");
                    ArmorDTO dto = new ArmorDTO(username, image, "1");
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean insertArmorWithoutImage(ArmorDTO dto) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("insert into tblArmor (ArmorName,Image,Status) values (?,?,?)");
                stm.setString(1, dto.getArmorName());
                stm.setString(2, "");
                stm.setInt(3, 1);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deleteArmor(String name) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("update tblArmor set Status=? where ArmorName=?");
                stm.setInt(1, 0);
                stm.setString(2, name);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean insertArmor(ArmorDTO dto) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("insert into tblArmor (ArmorName,Image,Status) values (?,?,?)");
                stm.setString(1, dto.getArmorName());
                stm.setString(2, dto.getImage());
                stm.setInt(3, 1);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateArmor(String name, String imagepath) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("update tblArmor set Image=? where ArmorName=?");
                stm.setString(1, imagepath);
                stm.setString(2, name);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
   
}
