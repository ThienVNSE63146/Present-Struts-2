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
public class DeleteWeaponse {
    private final String SUCCESS="success";
    private final String ERROR="error";
    private String weaponseID;

    public String getWeaponseID() {
        return weaponseID;
    }

    public void setWeaponseID(String weaponseID) {
        this.weaponseID = weaponseID;
    }
    public DeleteWeaponse() {
    }
    
    public String execute() throws Exception {
        String url=ERROR;
        WeaponseDAO dao=new WeaponseDAO();
        boolean check1=dao.deleteWeasponseDetailByWeaponseID(weaponseID);
        boolean check=dao.deleteWeaponse(weaponseID);
        if(!check||!check1){
            HttpServletRequest request=ServletActionContext.getRequest();
            request.setAttribute("ERROR", "delete weaponse Fail");
        }
        else{
            url=SUCCESS;
        }
        return url;
    }
    
}
