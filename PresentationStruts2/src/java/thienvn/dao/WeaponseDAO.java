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
import java.util.List;
import thienvn.connection.MyConnection;
import thienvn.dto.WeaponseDTO;
import thienvn.dto.WeaponseDetail;

/**
 *
 * @author thien
 */
public class WeaponseDAO {

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

    public boolean deleteWeaponse(String id) throws ClassNotFoundException, SQLException {
        boolean check = false;
        con = MyConnection.getMyConnection();
        if (con != null) {
            stm = con.prepareStatement("update tblWeapon set Status=? where WeaponID=?");
            stm.setInt(1, 0);
            stm.setString(2, id);
            check = stm.executeUpdate() > 0;
        }
        closeConnection();
        return check;
    }

    public ArrayList<WeaponseDTO> loadWeaponse() throws ClassNotFoundException, SQLException {
        ArrayList<WeaponseDTO> list = new ArrayList<>();
        con = MyConnection.getMyConnection();
        if (con != null) {
            stm = con.prepareStatement("select WeaponID,WeaponName,Image,AttackDame from tblWeapon where Status=?");
            stm.setInt(1, 1);
            rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("WeaponID");
                String name = rs.getString("WeaponName");
                String image = rs.getString("Image");
                int dame = rs.getInt("AttackDame");
                WeaponseDTO dto = new WeaponseDTO(id, name, image, dame);
                list.add(dto);
            }
        }
        closeConnection();
        return list;
    }

    public ArrayList<WeaponseDTO> searchWeaponse(String missionName) throws ClassNotFoundException, SQLException {
        ArrayList<WeaponseDTO> list = new ArrayList<>();
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("select WeaponID,WeaponName,Image,AttackDame from tblWeapon where WeaponName like ? and Status=?");
                stm.setString(1, "%" + missionName + "%");
                stm.setInt(2, 1);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String missionID = rs.getString("WeaponID");
                    String name = rs.getString("WeaponName");
                    String image = rs.getString("Image");
                    int dame = rs.getInt("AttackDame");
                    WeaponseDTO dto = new WeaponseDTO(missionID, name, image, dame);
                    list.add(dto);
                }

            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public ArrayList<WeaponseDetail> getWeaponseDetail(String missionID) throws ClassNotFoundException, SQLException {

        ArrayList<WeaponseDetail> list = new ArrayList<>();
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("select ArmorName,WeaponID,Status from WeaponseDetail where not Status='0' and WeaponID=?");
                stm.setString(1, missionID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String armorname = rs.getString("ArmorName");
                    String weaponseID = rs.getString("WeaponID");
                   int status = rs.getInt("Status");
                    WeaponseDetail dto = new WeaponseDetail(weaponseID, armorname, status+"");
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean updateWeasponseDetail(String weaponID, String username, String name) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("update WeaponseDetail set ArmorName=? where WeaponID=? and ArmorName=?");
                stm.setString(1, name);
                stm.setString(2, weaponID);
                stm.setString(3, username);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<String> loadCombobox() throws ClassNotFoundException, SQLException {
        List<String> list = new ArrayList<String>();
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("select ArmorName from tblArmor where  Status=?");
                stm.setInt(1, 1);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("ArmorName");
                    list.add(username);
                }
                for (String string : list) {
                    System.out.println(string);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean insertWeaponseDetail(String armorname, String weaponseID) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("insert into WeaponseDetail (ArmorName,WeaponID,Status) values(?,?,?)");
                stm.setString(1, armorname);
                stm.setString(2, weaponseID);
                stm.setInt(3, 1);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean insertWeaponse(WeaponseDTO dto) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("insert into tblWeapon (WeaponID,WeaponName,AttackDame,Image,Status) values(?,?,?,?,?)");
                stm.setString(1, dto.getWeaponseID());
                stm.setString(2, dto.getWeaponseName());
                stm.setInt(3, dto.getDame());
                stm.setString(4, dto.getImage());
                stm.setInt(5, 1);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateWeasponse(WeaponseDTO dto) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("update tblWeapon set WeaponName=?,AttackDame=?,Image=? where WeaponID=?");
                stm.setString(1, dto.getWeaponseName());
                stm.setInt(2, dto.getDame());
                stm.setString(3, dto.getImage());
                stm.setString(4, dto.getWeaponseID());
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateWeasponseWithoutImage(WeaponseDTO dto) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("update tblWeapon set WeaponName=?,AttackDame=? where WeaponID=?");
                stm.setString(1, dto.getWeaponseName());
                stm.setInt(2, dto.getDame());

                stm.setString(3, dto.getWeaponseID());
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<String> loadCombobox2(ArrayList<WeaponseDetail> listUser) throws ClassNotFoundException, SQLException {
        List<String> list = new ArrayList<String>();
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("select ArmorName from tblArmor where  Status=?");
                stm.setInt(1, 1);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("ArmorName");
                    if(listUser.isEmpty()){
                         list.add(username);
                    }
                    else{
                        boolean flag=false;
                    for (WeaponseDetail dto : listUser) {
                        if (dto.getArmorname().equals(username)) {
                            flag=true;
                        } 
                    }
                    if(!flag){
                        list.add(username);
                    }
                    }
                }
                for (String string : list) {
                    System.out.println(string);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }
 public boolean deleteWeasponseDetail(String weaponID, String armorname) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("update WeaponseDetail set Status=? where WeaponID=? and ArmorName=?");
                stm.setInt(1, 0);
                stm.setString(2, weaponID);
                stm.setString(3, armorname);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
   public boolean insertIntoWeaponseDetail(String weaponseID, ArrayList<String> armor) throws ClassNotFoundException, SQLException {
       
        boolean flag=true;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                con.setAutoCommit(false);
                for (String string : armor) {
                    stm = con.prepareStatement("insert into WeaponseDetail (ArmorName,WeaponID,Status) values(?,?,?)");
                    stm.setString(1, string);
                    stm.setString(2, weaponseID);
                    stm.setInt(3, 1);
                     stm.executeUpdate();  
                }
                con.commit();
            }
        }catch(Exception e){
            con.rollback();
            flag=false;
        }
        finally {
            closeConnection();
        }
        return flag;
    }
   public boolean deleteWeasponseDetailByWeaponseID(String weaponID) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("update WeaponseDetail set Status=? where WeaponID=?");
                stm.setInt(1, 0);
                stm.setString(2, weaponID);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
}
