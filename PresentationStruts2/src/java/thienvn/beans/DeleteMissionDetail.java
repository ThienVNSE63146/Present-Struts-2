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
public class DeleteMissionDetail {
    private String missionID,userName;
    public final String SUCCESS="success";
    public final String ERROR="error";
    
    public String getMissionID() {
        return missionID;
    }

    public void setMissionID(String missionID) {
        this.missionID = missionID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public DeleteMissionDetail() {
    }
    
    public String execute() throws Exception {
        HttpServletRequest request=ServletActionContext.getRequest();
      String url=ERROR;
        MissionDAO dao=new MissionDAO();
        boolean check=dao.deleteMissionDetail(missionID, userName);
        if(!check){
            request.setAttribute("ERROR", "delete mission detail fail");
        }
        else{
            url=SUCCESS;
            
        }
        return url;
    }
    
}
