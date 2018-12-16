/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import thienvn.dao.UserDAO;
import thienvn.dto.AvengerDTO;

/**
 *
 * @author thien
 */
public class LoadProfile {
    private AvengerDTO user;
    public final String SUCCESS="success";

    public AvengerDTO getUser() {
        return user;
    }

    public void setUser(AvengerDTO user) {
        this.user = user;
    }
    public LoadProfile() {
    }
    
    public String execute() throws Exception {
        UserDAO dao=new UserDAO();
        Map session=ActionContext.getContext().getSession();
        String username=(String)session.get("NAME");
        user=dao.loadUser(username);

        return SUCCESS;
    }
    
}
