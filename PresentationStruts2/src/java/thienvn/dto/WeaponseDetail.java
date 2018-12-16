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
public class WeaponseDetail implements Serializable{
    private String weaponseID,armorname,status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWeaponseID() {
        return weaponseID;
    }

    public void setWeaponseID(String weaponseID) {
        this.weaponseID = weaponseID;
    }

    public String getArmorname() {
        return armorname;
    }

    public void setArmorname(String armorname) {
        this.armorname = armorname;
    }

    public WeaponseDetail(String weaponseID, String armorname, String status) {
        this.weaponseID = weaponseID;
        this.armorname = armorname;
        this.status = status;
        
    }

    
}
