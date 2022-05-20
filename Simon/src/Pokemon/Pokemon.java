/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon;

import Util.ImageLoader;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Pokemon {
    private String nama, pokemonCode;
    private int hp, maxHp, damage;
    private Image defaultFrontImage, defaultBackImage;
    private ArrayList<Image> frontSpriteImage, backSpriteImage;

    public Pokemon(String nama, String pokemonCode, int hp, int maxHp, int damage) {
        this.nama = nama;
        this.pokemonCode = pokemonCode;
        this.hp = hp;
        this.maxHp = maxHp;
        this.damage = damage;
        PokemonAssets assets = ImagePath.getAssets(pokemonCode);
        try {
            this.defaultFrontImage = ImageLoader.loadImage(assets.getDefaultFrontImage());
            this.defaultBackImage = ImageLoader.loadImage(assets.getDefaultBackImage());
            this.frontSpriteImage = ImageLoader.loadImageArrayCropHorizontal(8, assets.getFrontSpriteImage());
            this.backSpriteImage = ImageLoader.loadImageArrayCropHorizontal(8, assets.getDefaultBackImage());
        } catch (IOException ex) {
            Logger.getLogger(Pokemon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Clone
    public Pokemon(Pokemon object){
        this.nama = object.nama;
        this.pokemonCode = object.pokemonCode;
        this.hp = object.hp;
        this.maxHp = object.maxHp;
        this.damage = object.damage;
        PokemonAssets assets = ImagePath.getAssets(pokemonCode);
        try {
            this.defaultFrontImage = ImageLoader.loadImage(assets.getDefaultFrontImage());
            this.defaultBackImage = ImageLoader.loadImage(assets.getDefaultBackImage());
            this.frontSpriteImage = ImageLoader.loadImageArrayCropHorizontal(8, assets.getFrontSpriteImage());
            this.backSpriteImage = ImageLoader.loadImageArrayCropHorizontal(8, assets.getDefaultBackImage());
        } catch (IOException ex) {
            Logger.getLogger(Pokemon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public ArrayList<Image> getImageSprite() {
        return frontSpriteImage;
    }

    public void setImageSprite(ArrayList<Image> imageSprite) {
        this.frontSpriteImage = imageSprite;
    }

    public Image getDefaultFrontImage() {
        return defaultFrontImage;
    }

    public void setDefaultFrontImage(Image defaultFrontImage) {
        this.defaultFrontImage = defaultFrontImage;
    }

    public Image getDefaultBackImage() {
        return defaultBackImage;
    }

    public void setDefaultBackImage(Image defaultBackImage) {
        this.defaultBackImage = defaultBackImage;
    }

    public ArrayList<Image> getFrontSpriteImage() {
        return frontSpriteImage;
    }

    public void setFrontSpriteImage(ArrayList<Image> frontSpriteImage) {
        this.frontSpriteImage = frontSpriteImage;
    }

    public ArrayList<Image> getBackSpriteImage() {
        return backSpriteImage;
    }

    public void setBackSpriteImage(ArrayList<Image> backSpriteImage) {
        this.backSpriteImage = backSpriteImage;
    }
    
}
