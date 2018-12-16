/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import thienvn.dao.AdminDAO;
import thienvn.dao.ArmorDAO;

/**
 *
 * @author thien
 */
public class UpdateArmor implements ServletRequestAware {

    public final String SUCCESS = "success";
    public final String ERROR = "error";
    private String ArmorName, Status;

    public String getArmorName() {
        return ArmorName;
    }

    public void setArmorName(String ArmorName) {
        this.ArmorName = ArmorName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
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

    public UpdateArmor() {
    }

    public String execute() throws Exception {
        String url = ERROR;
        HttpServletRequest request = ServletActionContext.getRequest();
        boolean check = true;
        if (fileUp == null) {
            url = SUCCESS;
        } else {

            String path = servletRequest.getSession().getServletContext().getRealPath("/");
            File fileToCreate = new File(path, this.fileUpFileName);
            FileUtils.copyFile(fileUp, fileToCreate);
            ArmorDAO dao = new ArmorDAO();
            dao.updateArmor(ArmorName, fileUpFileName);
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
