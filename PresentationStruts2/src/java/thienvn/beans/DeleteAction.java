/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import thienvn.dao.AdminDAO;

/**
 *
 * @author thien
 */
public class DeleteAction {
    private String name;
    public final String SUCCESS="success";
    public final String ERROR="error";
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public DeleteAction() {
    }
    
    public String execute() throws Exception {
        String url=ERROR;
        AdminDAO dao=new AdminDAO();
        boolean check=dao.deleteUser(name);
        if(!check){
            HttpServletRequest request=ServletActionContext.getRequest();
            request.setAttribute("ERROR", "delete fail");
        }
        else{
            url=SUCCESS;
        }
        return url;
    }
    
}
