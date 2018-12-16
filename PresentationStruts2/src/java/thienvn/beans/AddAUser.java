/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import thienvn.dao.MissionDAO;

/**
 *
 * @author thien
 */
public class AddAUser {
    private String username,MissionID;
    public final String SUCCESS="success";
    public final String ERROR="error";

    public String getMissionID() {
        return MissionID;
    }

    public void setMissionID(String MissionID) {
        this.MissionID = MissionID;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public AddAUser() {
    }
    
    public String execute() throws Exception {
        String url=ERROR;
        MissionDAO dao=new MissionDAO();
        boolean check=dao.insertAUserToMissionDetail(MissionID, username);
        if(!check){
            HttpServletRequest request=ServletActionContext.getRequest();
            request.setAttribute("ERROR", "insert user to mission fail");
        }
        else{
            url=SUCCESS;
                    
        }
        return url;
    }
    
}
