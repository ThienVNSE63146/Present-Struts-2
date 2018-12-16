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
public class AddToMission {

    ArrayList<String> username;
    public final String SUCCESS = "success";
    private String heroesName,txtUserName;

    public ArrayList<String> getUsername() {
        return username;
    }

    public void setUsername(ArrayList<String> username) {
        this.username = username;
    }

    public String getTxtUserName() {
        return txtUserName;
    }

    public void setTxtUserName(String txtUserName) {
        this.txtUserName = txtUserName;
    }

    public String getHeroesName() {
        return heroesName;
    }

    public void setHeroesName(String heroesName) {
        this.heroesName = heroesName;
    }

    public AddToMission() {
    }

    public String execute() throws Exception {
        try {
            String a = heroesName;
            System.out.println(heroesName);
            Map session = ActionContext.getContext().getSession();
            username = (ArrayList<String>) session.get("USERNAME");
            username.add(heroesName);
            session.put("USERNAME", username);
            for (String string : username) {
                System.out.println(string);
            }
        } catch (Exception e) {
            Map session = ActionContext.getContext().getSession();
            username = new ArrayList<>();
            username.add(heroesName);
            session.put("USERNAME", username);
        }

        return SUCCESS;
    }

}
