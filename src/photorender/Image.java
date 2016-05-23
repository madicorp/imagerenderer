/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package photorender;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 * @author sega
 */
public class Image {
    
    private String nom;
    private File file;
    private BufferedImage image;
    private Integer nombre = 0;

    public Image() {
    }

    public Image(String nom, File file, BufferedImage image) {
        this.nom = nom;
        this.file = file;
        this.image = image;
    }
    
    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Integer getNombre() {
        return nombre;
    }

    public void setNombre(Integer nombre) {
        this.nombre = nombre;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
    
    
    
}
