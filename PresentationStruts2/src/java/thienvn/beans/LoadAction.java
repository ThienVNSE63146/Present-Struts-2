/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import thienvn.dao.AdminDAO;

import thienvn.dto.AvengerDTO;

/**
 *
 * @author thien
 */
public class LoadAction {
    public final String SUCCESS="success";
    public final String ERROR="error";
    ArrayList<AvengerDTO> list;
    
    public LoadAction() {
    }

    public ArrayList<AvengerDTO> getList() {
        return list;
    }
    
    public String execute() throws Exception {
        AdminDAO dao=new AdminDAO();
        list=dao.loadUser();
         HttpServletRequest request=ServletActionContext.getRequest();
         request.setAttribute("VISIBLE","false");
        return SUCCESS;
    }
    
}
