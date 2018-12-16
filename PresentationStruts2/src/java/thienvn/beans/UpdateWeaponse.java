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
import thienvn.dao.WeaponseDAO;
import thienvn.dto.WeaponseDTO;

/**
 *
 * @author thien
 */
public class UpdateWeaponse implements ServletRequestAware{
     public final String SUCCESS = "success";
    public final String ERROR = "error";
    private String txtWeaponseID, txtWeaponseName,txtAttackDame;

    public String getTxtWeaponseID() {
        return txtWeaponseID;
    }

    public void setTxtWeaponseID(String txtWeaponseID) {
        this.txtWeaponseID = txtWeaponseID;
    }

    public String getTxtWeaponseName() {
        return txtWeaponseName;
    }

    public void setTxtWeaponseName(String txtWeaponseName) {
        this.txtWeaponseName = txtWeaponseName;
    }

    public String getTxtAttackDame() {
        return txtAttackDame;
    }

    public void setTxtAttackDame(String txtAttackDame) {
        this.txtAttackDame = txtAttackDame;
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
    public UpdateWeaponse() {
    }
    
    public String execute() throws Exception {
            String url = ERROR;
        HttpServletRequest request = ServletActionContext.getRequest();
        boolean check = true;
      
        if (fileUp == null) {
            WeaponseDAO dao = new WeaponseDAO();
            
                WeaponseDTO dto=new WeaponseDTO(txtWeaponseID, txtWeaponseName, "", Integer.parseInt(txtAttackDame));
                check = dao.updateWeasponseWithoutImage(dto);

           
        } else {
            
                String path = servletRequest.getSession().getServletContext().getRealPath("/");
                File fileToCreate = new File(path, this.fileUpFileName);
                FileUtils.copyFile(fileUp, fileToCreate);
                WeaponseDAO dao = new WeaponseDAO();
                WeaponseDTO dto=new WeaponseDTO(txtWeaponseID, txtWeaponseName, fileUpFileName,Integer.parseInt(txtAttackDame));
                check=dao.updateWeasponse(dto);
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
