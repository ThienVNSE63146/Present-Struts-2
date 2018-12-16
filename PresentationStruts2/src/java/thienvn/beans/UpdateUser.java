/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import thienvn.dao.UserDAO;

/**
 *
 * @author thien
 */
public class UpdateUser extends ActionSupport implements ServletRequestAware{
     public final String SUCCESS = "success";
    public final String ERROR = "error";
    private String txtPassword;

    public String getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(String txtPassword) {
        this.txtPassword = txtPassword;
    }

    public File getFileUp() {
        return fileUp;
    }

    public void setFileUp(File fileUp) {
        this.fileUp = fileUp;
    }

    public String getFileUpFileName() {
        return fileUpFileName;
    }

    public void setFileUpFileName(String fileUpFileName) {
        this.fileUpFileName = fileUpFileName;
    }
    File fileUp;
    private String fileUpFileName;
    private HttpServletRequest servletRequest;
    public UpdateUser() {
    }
    
    public String execute() throws Exception {
          String url = ERROR;
        HttpServletRequest request = ServletActionContext.getRequest();
        boolean check = true;
        Map session=ActionContext.getContext().getSession();
        String username=(String)session.get("NAME");
        if (fileUp == null) {
            UserDAO dao = new UserDAO();         
                check = dao.updateUserWithoutImage(txtPassword, username);

        } else {
          
                String path = servletRequest.getSession().getServletContext().getRealPath("/");
                File fileToCreate = new File(path, this.fileUpFileName);
                FileUtils.copyFile(fileUp, fileToCreate);
                UserDAO dao = new UserDAO();
                dao.updateUser(username, txtPassword, fileUpFileName);
                url = SUCCESS;
           
        }
        if (!check) {
            request.setAttribute("ERROR", "update fail");
        } else {
            url = SUCCESS;
        }
        return url;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
      this.servletRequest=hsr;
    }
    
}
