/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import java.util.ArrayList;
import thienvn.dao.MissionDAO;
import thienvn.dto.MissionDTO;

/**
 *
 * @author thien
 */
public class SearchMission {
    private String searchValue;
    public final String SUCCESS="success";
   
    ArrayList<MissionDTO>list;
    public SearchMission() {
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public ArrayList<MissionDTO> getList() {
        return list;
    }
    
    public String execute() throws Exception {
       String url=SUCCESS;
        MissionDAO dao=new MissionDAO();
        list=dao.searchMission(searchValue);
        return url;
    }
    
}
