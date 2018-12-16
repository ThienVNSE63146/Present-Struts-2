/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import java.util.ArrayList;
import thienvn.dao.ArmorDAO;
import thienvn.dto.ArmorDTO;
import thienvn.dto.AvengerDTO;

/**
 *
 * @author thien
 */
public class SearchArmor {

    public final String SUCCESS = "success";
    private String searchValue;
    ArrayList<ArmorDTO> list;

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public ArrayList<ArmorDTO> getList() {
        return list;
    }

    public void setList(ArrayList<ArmorDTO> list) {
        this.list = list;
    }

    public SearchArmor() {
    }

    public String execute() throws Exception {
        ArmorDAO dao = new ArmorDAO();
        list = dao.searchArmorLike(searchValue);
        return SUCCESS;
    }

}
