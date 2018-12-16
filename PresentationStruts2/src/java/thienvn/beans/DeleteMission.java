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
public class DeleteMission {
    private String name;
    public final String SUCCESS="success";
    public final String ERROR="error";
    public DeleteMission() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String execute() throws Exception {
        String url=ERROR;
        HttpServletRequest request=ServletActionContext.getRequest();
        MissionDAO dao=new MissionDAO();
        boolean check=dao.deleteMission(name);
        if(!check){
            request.setAttribute("ERROR", "delete mission fail");
        }
        else{
            url=SUCCESS;
        }
        return url;
    }
    
}
