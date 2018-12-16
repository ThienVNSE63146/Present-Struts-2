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
import java.util.List;
import thienvn.connection.MyConnection;
import thienvn.dto.AvengerDTO;
import thienvn.dto.MissionDTO;
import thienvn.dto.MissionDetailDTO;
import thienvn.dto.WeaponseDetail;

/**
 *
 * @author thien
 */
public class UserDAO implements Serializable {

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

    public MissionDTO loadMissionThoughtMissionID(String missionID) throws ClassNotFoundException, SQLException {
        MissionDTO dto = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("select MissionID,MissinName,Place from tblMission where Status=? and MissionID=? ");
                stm.setInt(1, 1);
                stm.setString(2, missionID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("MissionID");
                    String name = rs.getString("MissinName");
                    String place = rs.getString("Place");
                    dto = new MissionDTO(id, name, place, 1);
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public MissionDTO loadMissionForVIP(String missionID, ArrayList<MissionDTO> list) throws ClassNotFoundException, SQLException {
        MissionDTO dto = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("select MissionID,MissinName,Place from tblMission where Status=? and MissionID=? ");
                stm.setInt(1, 1);
                stm.setString(2, missionID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("MissionID");
                    String name = rs.getString("MissinName");
                    String place = rs.getString("Place");
                    if (list.isEmpty()) {

                    }
                    boolean flag = false;
                    for (MissionDTO missionDTO : list) {
                        if (missionDTO.getMissionID().equals(id)) {
                            flag = true;
                        }
                    }
                    if (!flag) {
                        dto = new MissionDTO(id, name, place, 1);
                    }

                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public ArrayList<MissionDetailDTO> getMissionDetail(String userName) throws ClassNotFoundException, SQLException {

        ArrayList<MissionDetailDTO> list = new ArrayList<>();
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("select MissionID,Status from MissionDetail where not Status='0' and UserName=?");
                stm.setString(1, userName);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String missionID = rs.getString("MissionID");
                    int status = rs.getInt("Status");
                    String status1;
                    if (status == 1) {
                        status1 = "Ready";
                    } else if (status == 2) {
                        status1 = "In Process";
                    } else {
                        status1 = "Done";
                    }
                    MissionDetailDTO dto = new MissionDetailDTO(missionID, status1);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public ArrayList<WeaponseDetail> getMissionDetailID(String weaponseID) throws ClassNotFoundException, SQLException {
        ArrayList<WeaponseDetail> list = new ArrayList<>();
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("select UserName,Status from MissionDetail where not Status='0' and MissionID=?");
                stm.setString(1, weaponseID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("UserName");
                    int status = rs.getInt("Status");
                    String status1;
                    if (status == 1) {
                        status1 = "Ready";
                    } else if (status == 2) {
                        status1 = "In Process";
                    } else {
                        status1 = "Done";
                    }
                    WeaponseDetail dto = new WeaponseDetail(weaponseID, username, status1);
                    if (list.isEmpty()) {
                        list.add(dto);
                    } else {
                        boolean flag=false;
                        for (WeaponseDetail weaponseDetail : list) {
                            if(weaponseDetail.getArmorname().equalsIgnoreCase(dto.getArmorname())){
                                flag=true;
                            }
                        }
                        if(!flag){
                            list.add(dto);
                        }
                    }
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<String> loadCombobox() throws ClassNotFoundException, SQLException {
        List<String> list = new ArrayList<String>();
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("select UserName from tblUser where  Status=?");
                stm.setInt(1, 1);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("UserName");
                    list.add(username);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public ArrayList<String> getArmorInMissionDetail(String missionID, String username) throws ClassNotFoundException, SQLException {

        ArrayList<String> list = new ArrayList<>();
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("select ArmorName from MissionDetail where not Status='0' and MissionID=? and UserName=?");
                stm.setString(1, missionID);
                stm.setString(2, username);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String armorName = rs.getString("ArmorName");
                    if (armorName == null) {
                    } else {
                        list.add(armorName);
                    }
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public ArrayList<String> loadArmor() throws ClassNotFoundException, SQLException {
        ArrayList<String> list = new ArrayList<>();
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("select ArmorName from tblArmor where Status=?");
                stm.setInt(1, 1);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String armorName = rs.getString("ArmorName");

                    list.add(armorName);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public ArrayList<String> getArmorNameInMissionDetail() throws ClassNotFoundException, SQLException {

        ArrayList<String> list = new ArrayList<>();
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("select ArmorName from MissionDetail where not Status='0'");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String armorName = rs.getString("ArmorName");
                    if (armorName == null) {
                    } else {
                        list.add(armorName);
                    }
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean updateMisison(String missionID, String username, int status) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("update MissionDetail set Status=? where MissionID=? and UserName=?");
                stm.setInt(1, status);
                stm.setString(2, missionID);
                stm.setString(3, username);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateArmorMissionDetail(String missionID, String username, String armor) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("update MissionDetail set ArmorName=? where MissionID=? and UserName=?");
                stm.setString(1, armor);
                stm.setString(2, missionID);
                stm.setString(3, username);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean InsertMissionDetail(String missionID, String username, String armor, int status) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("insert into MissionDetail  (MissionID,UserName,ArmorName,Status) values (?,?,?,?)");
                stm.setString(1, missionID);
                stm.setString(2, username);
                stm.setString(3, armor);
                stm.setInt(4, status);

                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    public void returnArmor(String username,String missionID) throws ClassNotFoundException, SQLException{
           try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("update MissionDetail set ArmorName=? where UserName=? and MissionID=? ");
               stm.setString(1,null);
               stm.setString(2, username);
               stm.setString(3, missionID);
               stm.executeUpdate();
            }
        } finally {
            closeConnection();
        }
    }
    public AvengerDTO loadUser(String username)throws SQLException,ClassNotFoundException{
        AvengerDTO user=null;
        try{
            con=MyConnection.getMyConnection();
            if(con!=null){
                stm=con.prepareStatement("select Role,Password,Image from tblUser where Status=? and UserName=?");
                stm.setInt(1, 1);
                stm.setString(2, username);
                rs=stm.executeQuery();
                if(rs.next()){
                    String Role=rs.getString("Role");
                    String password=rs.getString("Password");
                    String image=rs.getString("Image");
                    user=new AvengerDTO(username, password, Role, image);
                }
            }
        }finally{
            closeConnection();
        }
        return user;
    }
      public boolean updateUser( String name,String password,String imagepath) throws ClassNotFoundException, SQLException {
        boolean check = false;
        con = MyConnection.getMyConnection();
        if (con != null) {
            stm = con.prepareStatement("update tblUser set Password=?,Image=? where UserName=?");
            stm.setString(1, password);
            stm.setString(2, imagepath);
            stm.setString(3, name);
            check = stm.executeUpdate() > 0;
        }
        closeConnection();
        return check;
    }

    public boolean updateUserWithoutImage(String pasword, String name) throws ClassNotFoundException, SQLException {
        boolean check = false;
        con = MyConnection.getMyConnection();
        if (con != null) {
            stm = con.prepareStatement("update tblUser set Password=? where UserName=?");
            stm.setString(1, pasword);
            stm.setString(2, name);
            check = stm.executeUpdate() > 0;
        }
        closeConnection();
        return check;
    }
     public String getPassword(String name) throws ClassNotFoundException, SQLException {
        String pass="";
        con = MyConnection.getMyConnection();
        if (con != null) {
            stm = con.prepareStatement("select Password  from tblUser where UserName=?");
            stm.setString(1, name);
            rs=stm.executeQuery();
            if(rs.next()){
                 pass=rs.getString("Password");
            }
        }
        closeConnection();
        return pass;
    }
}
