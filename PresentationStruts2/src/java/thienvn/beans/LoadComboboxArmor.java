/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import com.opensymphony.xwork2.ActionContext;
import java.util.List;
import java.util.Map;
import thienvn.dao.WeaponseDAO;

/**
 *
 * @author thien
 */
public class LoadComboboxArmor {
    public final String SUCCESS = "success";

    List<String> armor;

    public List<String> getArmor() {
        return armor;
    }

    public void setArmor(List<String> armor) {
        this.armor = armor;
    }
    public LoadComboboxArmor() {
    }
    
    public String execute() throws Exception {
        WeaponseDAO dao=new WeaponseDAO();
        armor=dao.loadCombobox();
          Map session=ActionContext.getContext().getSession();
        session.put("COMBOBOX", armor);
        return SUCCESS;
    }
    
}
