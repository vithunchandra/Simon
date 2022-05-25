/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon;

import BattleCanvas.Drawable;
import Util.ImageLoader;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


//Setter nya tak ilangin beberapa(rasae ga perlu) ben ga menuh2 i
//nanti waktu extends kalo ada abstract nya biarin kosong aja.
public abstract class Pokemon implements Drawable{
    public static  int MAX_LEVEL = 30;
    
    protected String nama, pokemonCode;
    protected int lvl,hp, maxHp, damage;
    protected Image defaultFrontImage, defaultBackImage;
    protected ArrayList<Image> frontSpriteImage, backSpriteImage;
    protected long timeAcc1,changeEveryMilis;
    protected int standCt;
    protected boolean renderFront;
    protected int x,y,width,height;
    protected int exp,expNeededToLevelUp;
   

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
    
    public Pokemon(String nama, int maxHp, int damage,String path,int numSprite,int x,int y,int width ,int height) throws IOException {
        this.nama = nama;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.damage = damage;
        
        this.frontSpriteImage = ImageLoader.loadImageArrayCropHorizontal(numSprite, path);
        this.backSpriteImage = ImageLoader.loadImageArrayCropHorizontal(numSprite, path.split("[.]")[0] + "_back." + path.split("[.]")[1]);
        this.defaultFrontImage = this.frontSpriteImage.get(0);
        this.defaultBackImage = this.backSpriteImage.get(0);
        
        int animTime = 5000;
        this.timeAcc1 = 0;
        this.changeEveryMilis = animTime/numSprite;
        this.standCt = 0;
        this.renderFront = true;
        this.lvl = 1;
        
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    public abstract void logicLoop(long timediff);
    
    protected void defaultGraphicLoop(long timediff) {
        if(this.renderFront) {
            this.timeAcc1 += timediff; 
            if(timeAcc1 >= changeEveryMilis) {
                timeAcc1 = 0;
                standCt = standCt + 1;
                if(standCt == frontSpriteImage.size()) {
                    standCt = 0;
                }
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        if(this.renderFront) {
            g.drawImage(frontSpriteImage.get(standCt), x, y,width,height, null);
        }
        else {
            g.drawImage(backSpriteImage.get(standCt), x, y,width,height, null);
        }
    }

    public void setRenderFront(boolean renderFront) {
        this.renderFront = renderFront;
    }
    
    
   
    public String getNama() {
        return nama;
    }

    public int getHp() {
        return hp;
    }

    public void damaged(int hp) {
        this.hp -= hp;
        if(this.hp < 0) {
            this.hp = 0;
        }
    }
    public void healed(int hp) {
        this.hp += hp;
        if(this.hp > this.maxHp) {
            this.hp = maxHp;
        }
    }


    public int getLvl() {
        return lvl;
    }
    public void levelUp() {
        this.lvl += 1;
        if(lvl > MAX_LEVEL) {
            lvl = MAX_LEVEL;
        }
        else {
            
        }
    }
    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
    
    public void setBound(int x,int y,int width,int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public int getMaxHp() {
        return maxHp;
    }

    public int getDamage() {
        return damage;
    }
    
    public ArrayList<Image> getImageSprite() {
        return frontSpriteImage;
    }

    public Image getDefaultFrontImage() {
        return defaultFrontImage;
    }


    public Image getDefaultBackImage() {
        return defaultBackImage;
    }


    public ArrayList<Image> getFrontSpriteImage() {
        return frontSpriteImage;
    }

    public ArrayList<Image> getBackSpriteImage() {
        return backSpriteImage;
    }
}
