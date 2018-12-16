/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import com.opensymphony.xwork2.ActionContext;
import java.util.List;
import java.util.Map;
import thienvn.dao.MissionDAO;

/**
 *
 * @author thien
 */
public class LoadCombobox {

    public final String SUCCESS = "success";

    List<String> username;

    public LoadCombobox() {
    }

    public List<String> getUsername() {
        return username;
    }

    public void setUsername(List<String> username) {
        this.username = username;
    }

    public String execute() throws Exception {
        MissionDAO dao = new MissionDAO();
        username = dao.loadCombobox();
        Map session=ActionContext.getContext().getSession();
        session.put("COMBOBOX", username);
        return SUCCESS;
    }

}
