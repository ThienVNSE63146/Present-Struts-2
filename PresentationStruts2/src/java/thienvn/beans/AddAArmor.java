/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import thienvn.dao.WeaponseDAO;

/**
 *
 * @author thien
 */
public class AddAArmor {
     private String username,weaponseID;
    public final String SUCCESS="success";
    public final String ERROR="error";
    public AddAArmor() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWeaponseID() {
        return weaponseID;
    }

    public void setWeaponseID(String weaponseID) {
        this.weaponseID = weaponseID;
    }

 
    
    public String execute() throws Exception {
        String url=ERROR;
        String a=weaponseID;
        WeaponseDAO dao=new WeaponseDAO();
        boolean check=dao.insertWeaponseDetail(username, weaponseID);
        if(!check){
            HttpServletRequest request=ServletActionContext.getRequest();
            request.setAttribute("ERROR", "insert weponse to armor fail");
        }
        else{
            url=SUCCESS;
                    
        }
        return url;
    }
    
}
