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
import thienvn.connection.MyConnection;

/**
 *
 * @author thien
 */
public class AvengerDAO {
    Connection con=null;
    PreparedStatement stm=null;
    ResultSet rs=null;
    public void closeConnection() throws SQLException{
        if(rs!=null){
            rs.close();
        }
        if(stm!=null){
            stm.close();
        }
        if(con!=null){
            con.close();
        }
    }
    public String checkLogin(String name, String password) throws ClassNotFoundException, SQLException{
        String role="failed";
        con=MyConnection.getMyConnection();
        if(con!=null){
            stm=con.prepareStatement("select Role from tblUser where Username=? and Password=?");
            stm.setString(1,name );
            stm.setString(2,password );
            rs=stm.executeQuery();
            if(rs.next()){
                role=rs.getString("Role");
                
            }
            
        }
        closeConnection();
        return role;
    }
}
