/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import com.opensymphony.xwork2.ActionContext;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import thienvn.dao.AdminDAO;
import thienvn.dao.AvengerDAO;
import thienvn.dao.UserDAO;
import thienvn.dto.AvengerDTO;
import thienvn.dto.MissionDTO;
import thienvn.dto.MissionDetailDTO;

/**
 *
 * @author thien
 */
public class LoginAction {

    private String username, password;
    public final String ADMIN = "admin";
    public final String USER = "user";
    public final String ERROR = "error";
    ArrayList<AvengerDTO> list;
    ArrayList<MissionDTO> listMission = new ArrayList<>();

    public ArrayList<MissionDTO> getListMission() {
        return listMission;
    }

    public ArrayList<AvengerDTO> getList() {
        return list;
    }

    public LoginAction() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String execute() throws Exception {
        String url = ERROR;
        AvengerDAO dao = new AvengerDAO();
        HttpServletRequest request = ServletActionContext.getRequest();
        String role = dao.checkLogin(username, password);
        Map session = ActionContext.getContext().getSession();

        if (role.equals("admin")) {
            AdminDAO daoA = new AdminDAO();
            url = ADMIN;
            list = daoA.loadUser();
             request.setAttribute("VISIBLE","false");
        } else if (role.equals("user")) {
            url = USER;
            ArrayList<MissionDetailDTO> listMissionID = new ArrayList<>();
            UserDAO dao1 = new UserDAO();
            listMissionID = dao1.getMissionDetail(username);
            for (MissionDetailDTO missionDetailDTO : listMissionID) {
                MissionDTO dto = dao1.loadMissionThoughtMissionID(missionDetailDTO.getUsername());
                listMission.add(dto);
            }
            session.put("CLASS", "NORMAL");
        } else if (role.equals("userV")) {
            url = USER;
            ArrayList<MissionDetailDTO> listMissionID = new ArrayList<>();
            UserDAO dao1 = new UserDAO();
            listMissionID = dao1.getMissionDetail(username);
            for (MissionDetailDTO missionDetailDTO : listMissionID) {
                MissionDTO dto = dao1.loadMissionThoughtMissionID(missionDetailDTO.getUsername());
               if(listMission.isEmpty()){
                   listMission.add(dto);
               }
               else{
                   boolean flag=false;
                   for (int i = 0; i < listMission.size(); i++) {
                       if(listMission.get(i).getMissionID().equals(dto.getMissionID())){
                          flag=true;
                       }
                   }
                   if(!flag){
                       listMission.add(dto);
                   }
               }

            }
            session.put("CLASS", "VIP");

        } else if (role.equals("failed")) {
            request.setAttribute("ERROR", "invalid name or password");
        } else {
            request.setAttribute("ERROR", "your  role is not exist");
        }

        session.put("NAME", username);
        return url;
    }

}
