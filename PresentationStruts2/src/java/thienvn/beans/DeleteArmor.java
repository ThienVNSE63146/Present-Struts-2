/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import thienvn.dao.ArmorDAO;

/**
 *
 * @author thien
 */
public class DeleteArmor {
    private final String SUCCESS="success";
    private final String ERROR="error";
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public DeleteArmor() {
    }
    
    public String execute() throws Exception {
        String url=ERROR;
        ArmorDAO dao=new ArmorDAO();
        boolean check=dao.deleteArmor(name);
        if(!check){
            HttpServletRequest request= ServletActionContext.getRequest();
            request.setAttribute("ERROR", "delete Armor fail");
        }
        else{
            url=SUCCESS;
        }
        return url;
    }
    
}
