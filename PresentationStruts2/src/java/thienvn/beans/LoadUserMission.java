/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import thienvn.dao.UserDAO;

/**
 *
 * @author thien
 */
public class LoadUserMission {
    private final String SUCCESS="success";
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public LoadUserMission() {
    }
    
    public String execute() throws Exception {
        Map session=ActionContext.getContext().getSession();
        username=(String)session.get("NAME");
        System.out.println(username);
        UserDAO dao=new UserDAO();
        password=dao.getPassword(username);
        return SUCCESS;
    }
    
}
