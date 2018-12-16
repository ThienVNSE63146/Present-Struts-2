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
public class ReturnArmor {
    private String missionID;
    private final String SUCCESS="success";

    public String getMissionID() {
        return missionID;
    }

    public void setMissionID(String missionID) {
        this.missionID = missionID;
    }
    public ReturnArmor() {
    }
    
    public String execute() throws Exception {
        Map session=ActionContext.getContext().getSession();
        String username=(String)session.get("NAME");
        UserDAO dao=new UserDAO();
        dao.returnArmor(username, missionID);
         System.out.println(missionID);
        return SUCCESS;
       
    }
    
}
