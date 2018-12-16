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
import thienvn.dao.ArmorDAO;
import thienvn.dto.ArmorDTO;

/**
 *
 * @author thien
 */
public class InsertArmor extends ActionSupport implements ServletRequestAware {

    public final String SUCCESS = "success";
    public final String ERROR = "error";
    String txtName;
    File fileUp;
    private String fileUpFileName;
    private HttpServletRequest servletRequest;

    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
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

    public InsertArmor() {
    }

    public String execute() throws Exception {
        String url = ERROR;
        HttpServletRequest request = ServletActionContext.getRequest();
        boolean check = true;
        if (fileUp == null) {
            ArmorDAO dao = new ArmorDAO();
            ArmorDTO dto = new ArmorDTO(txtName, "", "ready");
            check = dao.insertArmorWithoutImage(dto);
        } else {
            String path = servletRequest.getSession().getServletContext().getRealPath("/");
            File fileToCreate = new File(path, this.fileUpFileName);
            FileUtils.copyFile(fileUp, fileToCreate);
            ArmorDTO dto = new ArmorDTO(txtName, fileUpFileName, "ready");
            ArmorDAO dao = new ArmorDAO();
            dao.insertArmor(dto);
            url = SUCCESS;
        }
        if (!check) {
            request.setAttribute("ERROR", "insert fail");
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
