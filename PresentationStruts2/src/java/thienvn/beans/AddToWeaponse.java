/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import com.opensymphony.xwork2.ActionContext;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author thien
 */
public class AddToWeaponse {
     ArrayList<String> username;
    public final String SUCCESS = "success";
    private String armor;

    public ArrayList<String> getUsername() {
        return username;
    }

    public void setUsername(ArrayList<String> username) {
        this.username = username;
    }

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }
    public AddToWeaponse() {
    }
    
    public String execute() throws Exception {
        try{
            String a=armor;
          Map session = ActionContext.getContext().getSession();
            username = (ArrayList<String>) session.get("ARMOR");
            username.add(armor);
            session.put("ARMOR", username);
          
        } catch (Exception e) {
            Map session = ActionContext.getContext().getSession();
            username = new ArrayList<>();
            username.add(armor);
            session.put("ARMOR", username);
        }

        return SUCCESS;
    }
    
}
