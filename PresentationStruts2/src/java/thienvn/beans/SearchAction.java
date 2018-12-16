/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.beans;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import thienvn.dao.AdminDAO;
import thienvn.dto.AvengerDTO;

/**
 *
 * @author thien
 */
public class SearchAction {

    public final String SUCCESS = "success";
    public final String ERROR = "error";
    private String searchValue;
    ArrayList<AvengerDTO> list;

    public SearchAction() {
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
        
    }

    public ArrayList<AvengerDTO> getList() {
        return list;
    }

    public String execute() throws Exception {
        String url = SUCCESS;
        HttpServletRequest request = ServletActionContext.getRequest();
        
            AdminDAO dao = new AdminDAO();
            list = dao.searchUserLike(searchValue);
            url=SUCCESS;
        
        return url;
    }

}
