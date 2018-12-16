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
import java.util.HashMap;
import java.util.List;
import thienvn.connection.MyConnection;
import thienvn.dto.MissionDTO;
import thienvn.dto.MissionDetailDTO;

/**
 *
 * @author thien
 */
public class MissionDAO {

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

    public boolean deleteMission(String id) throws ClassNotFoundException, SQLException {
        boolean check = false;
        con = MyConnection.getMyConnection();
        if (con != null) {
            stm = con.prepareStatement("update tblMission set Status=? where MissionID=?");
            stm.setInt(1, 0);
            stm.setString(2, id);
            check = stm.executeUpdate() > 0;
        }
        closeConnection();
        return check;
    }

    public ArrayList<MissionDTO> loadMission() throws ClassNotFoundException, SQLException {
        ArrayList<MissionDTO> list = new ArrayList<>();
        con = MyConnection.getMyConnection();
        if (con != null) {
            stm = con.prepareStatement("select MissionID,MissinName,Place from tblMission where Status=?");
            stm.setInt(1, 1);
            rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("MissionID");
                String name = rs.getString("MissinName");
                String place = rs.getString("Place");
                MissionDTO dto = new MissionDTO(id, name, place, 1);
                list.add(dto);
            }
        }
        closeConnection();
        return list;
    }

    public ArrayList<MissionDTO> searchMission(String missionName) throws ClassNotFoundException, SQLException {
        ArrayList<MissionDTO> list = new ArrayList<>();
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("select MissionID,Place,MissinName from tblMission where MissinName like ? and Status=?");
                stm.setString(1, "%" + missionName + "%");
                stm.setInt(2, 1);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String missionID = rs.getString("MissionID");
                    String place = rs.getString("Place");
                    String MissionName = rs.getString("MissinName");
                    MissionDTO dto = new MissionDTO(missionID, MissionName, place, 1);
                    list.add(dto);
                }

            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public ArrayList<MissionDetailDTO> getMissionDetail(String missionID) throws ClassNotFoundException, SQLException {

        ArrayList<MissionDetailDTO> list = new ArrayList<>();
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("select UserName,Status from MissionDetail where not Status='0' and MissionID=?");
                stm.setString(1, missionID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("UserName");
                    int status = rs.getInt("Status");
                    String status1;
                    if (status == 1) {
                        status1 = "0%";
                    } else if (status == 2) {
                        status1 = "50%";
                    } else {
                        status1 = "100%";
                    }
                    MissionDetailDTO dto = new MissionDetailDTO(username, status1);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean updateMisison(String missionID, String username, String name) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("update MissionDetail set UserName=? where MissionID=? and UserName=?");
                stm.setString(1, name);
                stm.setString(2, missionID);
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

    public boolean insertMissionDetail(MissionDTO dto, ArrayList<String> username) throws ClassNotFoundException, SQLException {
       
        boolean flag=true;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                con.setAutoCommit(false);
                for (String string : username) {
                    stm = con.prepareStatement("insert into MissionDetail (UserName,MissionID,Status) values(?,?,?)");
                    stm.setString(1, string);
                    stm.setString(2, dto.getMissionID());
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

    public boolean insertMission(MissionDTO dto) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("insert into tblMission (MissionID,MissinName,Place,Status) values(?,?,?,?)");
                stm.setString(1, dto.getMissionID());
                stm.setString(2, dto.getMissionName());
                stm.setString(3, dto.getPlace());
                stm.setInt(4, 1);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
     public boolean insertAUserToMissionDetail(String missionID, String username) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("insert into MissionDetail (UserName,MissionID,Status) values (?,?,?)");
                stm.setString(1, username);
                stm.setString(2, missionID);
                stm.setInt(3, 1);
                check = stm.executeUpdate()> 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
      public List<String> loadCombobox2(ArrayList<MissionDetailDTO> listUser) throws ClassNotFoundException, SQLException {
        List<String> list = new ArrayList<String>();
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("select UserName from tblUser where  Status=?");
                stm.setInt(1, 1);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("UserName");
                    boolean flag=false;
                    for (MissionDetailDTO dto : listUser) {
                        if(dto.getUsername().equals(username)){
                            flag=true;
                        }
                       
                    }
                    if(!flag){
                        list.add(username);
                    }
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }
        public boolean deleteMissionDetail(String missionID, String username) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("update MissionDetail set Status=?  where MissionID=? and UserName=?");
                stm.setInt(1, 0);
                stm.setString(2, missionID);
                stm.setString(3, username);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
 public MissionDTO searchMissionByName(String missionName) throws ClassNotFoundException, SQLException {
       MissionDTO dto=null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                stm = con.prepareStatement("select MissionID,Place,MissinName from tblMission where MissinName=? and Status=?");
                stm.setString(1, missionName);
                stm.setInt(2, 1);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String missionID = rs.getString("MissionID");
                    String place = rs.getString("Place");
                    String MissionName = rs.getString("MissinName");
                    dto = new MissionDTO(missionID, MissionName, place, 1);
                    
                }

            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
