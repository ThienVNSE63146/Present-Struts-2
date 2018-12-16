/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import thienvn.dao.UserDAO;

/**
 *
 * @author thien
 */
public class UpdateStatus {
    private String MissionID,status;
    private final String SUCCESS="success";
    private final String ERROR="error";

    public String getMissionID() {
        return MissionID;
    }

    public void setMissionID(String MissionID) {
        this.MissionID = MissionID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public UpdateStatus() {
    }
    
    public String execute() throws Exception {
        String url=ERROR;
        UserDAO dao=new UserDAO();
        Map session=ActionContext.getContext().getSession();
        int status1;
        if(status.equalsIgnoreCase("Ready")){
            status1=1;
        }else if(status.equalsIgnoreCase("In Process")){
            status1=2;
        }
        else{
            status1=3;
        }
        String userName=(String)session.get("NAME");
        boolean check=dao.updateMisison(MissionID, userName,status1);
        if(!check){
            HttpServletRequest request=ServletActionContext.getRequest();
            request.setAttribute("ERROR", "update status fail");
        }
        else{
            url=SUCCESS;
        }
        return url;
    }
    
}
