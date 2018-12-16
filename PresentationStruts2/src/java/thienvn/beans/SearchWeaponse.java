/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import java.util.ArrayList;
import thienvn.dao.WeaponseDAO;
import thienvn.dto.WeaponseDTO;

/**
 *
 * @author thien
 */
public class SearchWeaponse {
     private String searchValue;
    public final String SUCCESS="success";
    private ArrayList<WeaponseDTO>list;

    public ArrayList<WeaponseDTO> getList() {
        return list;
    }

    public void setList(ArrayList<WeaponseDTO> list) {
        this.list = list;
    }
    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
    public SearchWeaponse() {
    }
    
    public String execute() throws Exception {
       String url=SUCCESS;
        WeaponseDAO dao=new WeaponseDAO();
        list=dao.searchWeaponse(searchValue);
        return url;
    }
    
}
