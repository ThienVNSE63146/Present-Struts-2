/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thienvn.dto;

/**
 *
 * @author thien
 */
public class WeaponseDTO {
    private String weaponseID,weaponseName,image;
    int dame;

    public WeaponseDTO(String weaponseID, String weaponseName, String image, int dame) {
        this.weaponseID = weaponseID;
        this.weaponseName = weaponseName;
        this.image = image;
        this.dame = dame;
    }

    public String getWeaponseID() {
        return weaponseID;
    }

    public void setWeaponseID(String weaponseID) {
        this.weaponseID = weaponseID;
    }

    public String getWeaponseName() {
        return weaponseName;
    }

    public void setWeaponseName(String weaponseName) {
        this.weaponseName = weaponseName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDame() {
        return dame;
    }

    public void setDame(int dame) {
        this.dame = dame;
    }
    
}
