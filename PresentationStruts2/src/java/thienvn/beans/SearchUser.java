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
public class SearchUser {

    public final String SUCCESS = "success";
    ArrayList<String> list = new ArrayList<>();
    private String searchValue;

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }


    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public ArrayList<MissionDTO> getListMission() {
        return listMission;
    }

    public void setListMission(ArrayList<MissionDTO> listMission) {
        this.listMission = listMission;
    }
    private ArrayList<MissionDTO> listMission = new ArrayList<>();

    public SearchUser() {
    }

    public String execute() throws Exception {
        ArrayList<String> temp=new ArrayList<>();
        if (list.isEmpty()) {
            System.out.println("empty");
        } else {
            for (String string : list) {
                if(string.equalsIgnoreCase(searchValue)){
                   temp.add(string);
                }
            }
        }
        MissionDAO dao=new MissionDAO();
        for (String string : temp) {
            listMission.add(dao.searchMissionByName(string));
        }
        return SUCCESS;
    }

}
