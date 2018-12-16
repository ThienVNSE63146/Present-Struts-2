/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import thienvn.dao.WeaponseDAO;
import thienvn.dto.WeaponseDetail;

/**
 *
 * @author thien
 */
public class showWeaponseDetail {
     private String weaponseID;
    ArrayList<WeaponseDetail>list;
    List<String>username;
    List<String>username2;
    public final String SUCCESS="success";
    public showWeaponseDetail() {
    }

    public String getWeaponseID() {
        return weaponseID;
    }

    public void setWeaponseID(String weaponseID) {
        this.weaponseID = weaponseID;
    }

    public ArrayList<WeaponseDetail> getList() {
        return list;
    }

    public void setList(ArrayList<WeaponseDetail> list) {
        this.list = list;
    }

    public List<String> getUsername() {
        return username;
    }

    public void setUsername(List<String> username) {
        this.username = username;
    }

    public List<String> getUsername2() {
        return username2;
    }

    public void setUsername2(List<String> username2) {
        this.username2 = username2;
    }
    
    public String execute() throws Exception {
        WeaponseDAO dao=new WeaponseDAO();
        list=dao.getWeaponseDetail(weaponseID);
        System.out.println("1"+list==null);
        username=dao.loadCombobox();
        System.out.println("2"+username==null);
        username2=dao.loadCombobox2(list);
        System.out.println("3"+username2==null);
         HttpServletRequest request=ServletActionContext.getRequest();
        request.setAttribute("COMBOBOX", username);
        request.setAttribute("COMBOBOX2", username2);
      return SUCCESS;
    }
    
}
