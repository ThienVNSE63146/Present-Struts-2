/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import com.opensymphony.xwork2.ActionContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import thienvn.dao.MissionDAO;
import thienvn.dao.UserDAO;
import thienvn.dto.WeaponseDetail;

/**
 *
 * @author thien
 */
public class ShowUserMission {

    public final String SUCCESS = "success";
    ArrayList<WeaponseDetail> list;
    List<String> username;
    List<String> statusList = new ArrayList<>();
    ArrayList<String> listArmor;
    private String ID;

    public ArrayList<String> getListArmor() {
        return listArmor;
    }

    public void setListArmor(ArrayList<String> listArmor) {
        this.listArmor = listArmor;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public ShowUserMission() {
    }

    public ArrayList<WeaponseDetail> getList() {
        return list;
    }

    public void setList(ArrayList<WeaponseDetail> list) {
        this.list = list;
    }

    public List<String> getUsername() {
        return username;
    }

    public void setUsername(List<String> username) {
        this.username = username;
    }

    public String execute() throws Exception {
        UserDAO dao = new UserDAO();
        String a = ID;
        MissionDAO misDao = new MissionDAO();
        list = dao.getMissionDetailID(ID);
        username = misDao.loadCombobox();
        statusList.add("Ready");
        statusList.add("In Process");
        statusList.add("Done");
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("COMBOBOX", username);
        request.setAttribute("COMBOBOX2", statusList);
        Map session = ActionContext.getContext().getSession();
        listArmor = dao.getArmorInMissionDetail(ID, (String) session.get("NAME"));
        ArrayList<String> array = new ArrayList<>();
        ArrayList<String> listArmorIntblArmor = dao.loadArmor();
        ArrayList<String> listArmorIntblMission = dao.getArmorNameInMissionDetail();
        for (String string : listArmorIntblArmor) {
            if (listArmorIntblMission.contains(string)) {

            } else {
                array.add(string);
            }
        }

        request.setAttribute("COMBOBOX3", array);
        return SUCCESS;
    }

}
