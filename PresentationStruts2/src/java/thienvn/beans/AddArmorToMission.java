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
public class AddArmorToMission {

    private final String SUCCESS = "success";
    private final String ERROR = "error";
    private String MissionID;
    private String armor, stt;

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getMissionID() {
        return MissionID;
    }

    public void setMissionID(String MissionID) {
        this.MissionID = MissionID;
    }

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public AddArmorToMission() {
    }

    public String execute() throws Exception {
        String url = ERROR;
        HttpServletRequest request = ServletActionContext.getRequest();
        Map session = ActionContext.getContext().getSession();
        String username = (String) session.get("NAME");
        String userClass = (String) session.get("CLASS");
        UserDAO dao = new UserDAO();
        if (userClass.equalsIgnoreCase("NORMAL")) {
            boolean check = dao.updateArmorMissionDetail(MissionID, username, armor);
            if (!check) {
                request.setAttribute("ERROR", "add armor fail");
            } else {
                url = SUCCESS;
            }
        } else if (userClass.equalsIgnoreCase("VIP")) {
            int status = 0;
            if (stt.equalsIgnoreCase("Ready")) {
                status = 1;
            } else if (stt.equalsIgnoreCase("In Process")) {
                status = 2;
            } else if (stt.equalsIgnoreCase("Done")) {
                status = 3;
            }
            boolean check = dao.InsertMissionDetail(MissionID, username, armor, status);
            if (!check) {
                request.setAttribute("ERROR", "add armor fail");
            } else {
                url = SUCCESS;
            }
        }
        return url;
    }

}
