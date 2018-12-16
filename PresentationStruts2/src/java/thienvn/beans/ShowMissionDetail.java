/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import thienvn.dao.MissionDAO;
import thienvn.dto.MissionDetailDTO;

/**
 *
 * @author thien
 */
public class ShowMissionDetail {
    private String ID;
    ArrayList<MissionDetailDTO>list;
    List<String>username;
    List<String>username2;

    public List<String> getUsername() {
        return username;
    }

    public void setUsername(List<String> username) {
        this.username = username;
    }
    public final String SUCCESS="success";

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public ArrayList<MissionDetailDTO> getList() {
        return list;
    }

    public void setList(ArrayList<MissionDetailDTO> list) {
        this.list = list;
    }

   

   
    public ShowMissionDetail() {
    }
    
    public String execute() throws Exception {
        MissionDAO dao=new MissionDAO();
      list=dao.getMissionDetail(ID);
      username=dao.loadCombobox();
      username2=dao.loadCombobox2(list);
        HttpServletRequest request=ServletActionContext.getRequest();
        request.setAttribute("COMBOBOX", username);
        request.setAttribute("COMBOBOX2", username2);
      return SUCCESS;
    }
    
}
