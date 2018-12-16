/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import java.util.ArrayList;
import thienvn.dao.WeaponseDAO;
import thienvn.dto.WeaponseDTO;
import thienvn.dto.WeaponseDetail;

/**
 *
 * @author thien
 */
public class LoadWeaponse {
    public final String SUCCESS="success";
    ArrayList<WeaponseDTO> list;
    public LoadWeaponse() {
    }

    public ArrayList<WeaponseDTO> getList() {
        return list;
    }
    
    public String execute() throws Exception {
        WeaponseDAO dao=new WeaponseDAO();
        list=dao.loadWeaponse();
        return SUCCESS;
    }
    
}
