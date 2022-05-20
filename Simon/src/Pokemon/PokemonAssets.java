/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon;

import javax.swing.*;
import java.util.*;
import java.awt.*;

public class PokemonAssets {
    
    private String defaultFrontImage, defaultBackImage, frontSpriteImage, backSpriteImage;

    public PokemonAssets(String defaultFrontImage, String defaultBackImage, String frontSpriteImage, String backSpriteImage) {
        this.defaultFrontImage = defaultFrontImage;
        this.defaultBackImage = defaultBackImage;
        this.frontSpriteImage = frontSpriteImage;
        this.backSpriteImage = backSpriteImage;
    }

    public String getDefaultFrontImage() {
        return defaultFrontImage;
    }

    public void setDefaultFrontImage(String defaultFrontImage) {
        this.defaultFrontImage = defaultFrontImage;
    }

    public String getDefaultBackImage() {
        return defaultBackImage;
    }

    public void setDefaultBackImage(String defaultBackImage) {
        this.defaultBackImage = defaultBackImage;
    }

    public String getFrontSpriteImage() {
        return frontSpriteImage;
    }

    public void setFrontSpriteImage(String frontSpriteImage) {
        this.frontSpriteImage = frontSpriteImage;
    }

    public String getBackSpriteImage() {
        return backSpriteImage;
    }

    public void setBackSpriteImage(String backSpriteImage) {
        this.backSpriteImage = backSpriteImage;
    }
}
