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
public class AvengerDTO implements Serializable{
    String name,password,role,image;

    public AvengerDTO() {
    }

    public AvengerDTO(String name, String password, String role, String image) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.image = image;
    }

    public AvengerDTO(String name, String role, String image) {
        this.name = name;
        this.role = role;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    
}
