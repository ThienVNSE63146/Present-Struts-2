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
public class ArmorDTO implements Serializable {

    private String armorName, image, status;

    public ArmorDTO(String armorName, String image, String status) {
        this.armorName = armorName;
        this.image = image;
        this.status = status;
    }

    public String getArmorName() {
        return armorName;
    }

    public void setArmorName(String armorName) {
        this.armorName = armorName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

 
}
