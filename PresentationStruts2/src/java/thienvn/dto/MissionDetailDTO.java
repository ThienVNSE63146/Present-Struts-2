/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.dto;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author thien
 */
public class MissionDetailDTO implements Serializable {

  String username,status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MissionDetailDTO(String username, String status) {
        this.username = username;
        this.status = status;
    }


}
