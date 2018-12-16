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
public class DeleteWeaponseDetail {
    private String armorname,weaponseID;
    public final String SUCCESS="success";
    public final String ERROR="error";

    public String getArmorname() {
        return armorname;
    }

    public void setArmorname(String armorname) {
        this.armorname = armorname;
    }

    public String getWeaponseID() {
        return weaponseID;
    }

    public void setWeaponseID(String weaponseID) {
        this.weaponseID = weaponseID;
    }
    
    public DeleteWeaponseDetail() {
    }
    
    public String execute() throws Exception {
         HttpServletRequest request=ServletActionContext.getRequest();
      String url=ERROR;
        WeaponseDAO dao=new WeaponseDAO();
        boolean check=dao.deleteWeasponseDetail(weaponseID, armorname);
        if(!check){
            request.setAttribute("ERROR", "delete weaponsedetail fail");
        }
        else{
            url=SUCCESS;
            
        }
        return url;
    
    }
    
}
