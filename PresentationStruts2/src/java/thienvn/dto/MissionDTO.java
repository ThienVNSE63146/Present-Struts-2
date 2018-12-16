/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.dto;

import java.io.Serializable;

/**
 *
 * @author thien
 */
public class MissionDTO implements Serializable{
   private String missionID,missionName,place;
   int status;

    public MissionDTO(String missionID, String missionName, String place, int status) {
        this.missionID = missionID;
        this.missionName = missionName;
        this.place = place;
        this.status = status;
    }

    public String getMissionID() {
        return missionID;
    }

    public void setMissionID(String missionID) {
        this.missionID = missionID;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
   
}
