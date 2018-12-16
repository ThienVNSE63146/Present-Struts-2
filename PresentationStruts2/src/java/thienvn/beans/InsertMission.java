/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Map;
import thienvn.dao.MissionDAO;
import thienvn.dto.MissionDTO;

/**
 *
 * @author thien
 */
public class InsertMission extends ActionSupport {
    public final String SUCCESS="success";
    public final String ERROR="error";
    private String txtMissionID,txtMissionName,place;

    public String getTxtMissionID() {
        return txtMissionID;
    }

    public void setTxtMissionID(String txtMissionID) {
        this.txtMissionID = txtMissionID;
    }

    public String getTxtMissionName() {
        return txtMissionName;
    }

    public void setTxtMissionName(String txtMissionName) {
        this.txtMissionName = txtMissionName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

   

  
    public InsertMission() {
    }
    
    public String execute() throws Exception {
        String url=ERROR;
        MissionDTO dto=new MissionDTO(txtMissionID, txtMissionName, place, 1);
        MissionDAO dao=new MissionDAO();
        boolean check1=dao.insertMission(dto);
         Map session = ActionContext.getContext().getSession();
        boolean check2=dao.insertMissionDetail(dto, (ArrayList<String>)session.get("USERNAME"));
            url=SUCCESS;
        session.remove("USERNAME");
        return url;
    }
    
}
