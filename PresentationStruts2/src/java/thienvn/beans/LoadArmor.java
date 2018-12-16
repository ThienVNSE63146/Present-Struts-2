/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import thienvn.dao.ArmorDAO;
import thienvn.dto.ArmorDTO;

import thienvn.dto.MissionDTO;

/**
 *
 * @author thien
 */
public class LoadArmor {
    public final String SUCCESS="success";
    ArrayList<ArmorDTO> list;

    public ArrayList<ArmorDTO> getList() {
        return list;
    }

    public void setList(ArrayList<ArmorDTO> list) {
        this.list = list;
    }

    
    public LoadArmor() {
    }
    
    public String execute() throws Exception {
            ArmorDAO dao=new ArmorDAO();
            list=dao.loadArmor();
            HttpServletRequest request=ServletActionContext.getRequest();
            request.setAttribute("VISIBLE","false");
            return SUCCESS;
    }
    
}
