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
public class UpdateMission {
    private String MissionID,userName,txtUserName;

    public String getTxtUserName() {
        return txtUserName;
    }

    public void setTxtUserName(String txtUserName) {
        this.txtUserName = txtUserName;
    }
    public final String ERROR="error";
    public final String SUCCESS="success";

    public String getMissionID() {
        return MissionID;
    }

    public void setMissionID(String MissionID) {
        this.MissionID = MissionID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public UpdateMission() {
    }
    
    public String execute() throws Exception {
       String url=ERROR;
        MissionDAO dao=new MissionDAO();
        boolean check=dao.updateMisison(MissionID, userName, txtUserName);
        if(!check){
            HttpServletRequest request=ServletActionContext.getRequest();
            request.setAttribute("ERROR", "update fail");
        }
        else{
            url=SUCCESS;
        }
        return url;
    }
    
}
