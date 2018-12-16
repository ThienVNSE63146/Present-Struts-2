/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import thienvn.dao.WeaponseDAO;
import thienvn.dto.WeaponseDTO;

/**
 *
 * @author thien
 */
public class InsertWeaponseDetail extends ActionSupport implements ServletRequestAware {

    public final String SUCCESS = "success";
    public final String ERROR = "error";
    File fileUp;
    private String fileUpFileName;

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

    public String getWeaponseName() {
        return weaponseName;
    }

    public void setWeaponseName(String weaponseName) {
        this.weaponseName = weaponseName;
    }

    public String getWeaponseID() {
        return weaponseID;
    }

    public void setWeaponseID(String weaponseID) {
        this.weaponseID = weaponseID;
    }

    public String getAttackDame() {
        return attackDame;
    }

    public void setAttackDame(String attackDame) {
        this.attackDame = attackDame;
    }
    private HttpServletRequest servletRequest;
    private String weaponseName, weaponseID, attackDame;

    public InsertWeaponseDetail() {
    }

    public String execute() throws Exception {
        String url = ERROR;
        HttpServletRequest request = ServletActionContext.getRequest();
        Map session = ActionContext.getContext().getSession();
        boolean check = true;
        if (fileUp == null) {
            WeaponseDAO dao = new WeaponseDAO();

            WeaponseDTO dto = new WeaponseDTO(weaponseID, weaponseName, "", Integer.parseInt(attackDame));
            check = dao.insertWeaponse(dto);

        } else {

            String path = servletRequest.getSession().getServletContext().getRealPath("/");
            File fileToCreate = new File(path, this.fileUpFileName);
            FileUtils.copyFile(fileUp, fileToCreate);
            WeaponseDTO dto = new WeaponseDTO(weaponseID, weaponseName, fileUpFileName, Integer.parseInt(attackDame));
            WeaponseDAO dao = new WeaponseDAO();
            check = dao.insertWeaponse(dto);

        }
        WeaponseDAO dao = new WeaponseDAO();
        boolean check2 = dao.insertIntoWeaponseDetail(weaponseID, (ArrayList<String>) session.get("ARMOR"));
        if (!check || !check2) {
            request.setAttribute("ERROR", "insert fail");
        } else {
            url = SUCCESS;
        }
        session.remove("ARMOR");
        return url;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.servletRequest = hsr;
    }

}
