/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import thienvn.dao.AdminDAO;

/**
 *
 * @author thien
 */
public class UpdateAction extends ActionSupport implements ServletRequestAware {

    public final String SUCCESS = "success";
    public final String ERROR = "error";
    private String txtNameInfo, txtRoleInfo;
    File fileUp;
    private String fileUpFileName;
    private HttpServletRequest servletRequest;

    public String getTxtNameInfo() {
        return txtNameInfo;
    }

    public void setTxtNameInfo(String txtNameInfo) {
        this.txtNameInfo = txtNameInfo;
    }

    public String getTxtRoleInfo() {
        return txtRoleInfo;
    }

    public void setTxtRoleInfo(String txtRoleInfo) {
        this.txtRoleInfo = txtRoleInfo;
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

    public UpdateAction() {
    }

    public String execute() throws Exception {
        String url = ERROR;
        HttpServletRequest request = ServletActionContext.getRequest();
        boolean check = true;
        if (fileUp == null) {
            AdminDAO dao = new AdminDAO();

            check = dao.updateUserWithoutImage(txtRoleInfo, txtNameInfo);

        } else {

            String path = servletRequest.getSession().getServletContext().getRealPath("/");
            File fileToCreate = new File(path, this.fileUpFileName);
            FileUtils.copyFile(fileUp, fileToCreate);
            AdminDAO dao = new AdminDAO();
            dao.updateUser(txtRoleInfo, txtNameInfo, fileUpFileName);
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
        this.servletRequest = hsr;
    }

}
