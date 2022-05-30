/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon;

import BattleCanvas.Drawable;
import Pokemon.Skill.Skill;
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
    
    //battle and information
    protected String nama, pokemonCode;
    protected int lvl,hp, maxHp, damage;
    protected int exp,expNeededToLevelUp;
    protected ArrayList<Skill> skillList;
    
    //image
    protected Image defaultFrontImage, defaultBackImage;
    protected ArrayList<Image> frontSpriteImage, backSpriteImage;
    
    //patrick variable for rendering
    protected int x,y,width,height,animTime,standCt,backCt;
    protected long timeAcc1,changeEveryMilis,changeEveryMilisBack;
    protected boolean renderFront = true;
    
    protected abstract void levelUpBehaviour();
    public abstract int getExpDrop();
    public abstract int getGoldDrop();
    public abstract String getType();
    
    private void renderInit(int numSpriteFront,int numSpriteBack) {
        x = -1;y = -1;width = -1;height = -1; 
        animTime = 5000;
        timeAcc1 = 0; 
        changeEveryMilis = animTime/numSpriteFront;
        changeEveryMilisBack = animTime/numSpriteBack;
        standCt = 0;
        renderFront = true;
    }
    
    public Pokemon(String nama, int maxHp, int damage,String path,int numSpriteFront,int numSpriteBack) throws IOException {
        this.nama = nama;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.damage = damage;
        
        this.frontSpriteImage = ImageLoader.loadImageArrayCropHorizontal(numSpriteFront, path);
        this.backSpriteImage = ImageLoader.loadImageArrayCropHorizontal(numSpriteBack, path.split("[.]")[0] + "_back." + path.split("[.]")[1]);
        this.defaultFrontImage = this.frontSpriteImage.get(0);
        this.defaultBackImage = this.backSpriteImage.get(0);
        
        this.skillList = new ArrayList<>(Arrays.asList(null,null,null,null));
        
        renderInit(numSpriteFront,numSpriteBack);
       
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

    //public abstract void logicLoop(long timediff);
    public void logicLoop(long timediff) {
        defaultGraphicLoop(timediff);
    };
    
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
        else {
            this.timeAcc1 += timediff; 
            if(timeAcc1 >= changeEveryMilisBack) {
                timeAcc1 = 0;
                backCt = backCt + 1;
                if(backCt == backSpriteImage.size()) {
                    backCt = 0;
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
            g.drawImage(backSpriteImage.get(backCt), x, y,width,height, null);
        }
    }

    public void setRenderFront(boolean renderFront) {
        this.renderFront = renderFront;
    }
    
    public Double getTypeMultiplier(Pokemon enemy) {
        if(this.getType().equals("grass") && enemy.getType().equals("fire")) {
            return 0.75;
        } else if(this.getType().equals("grass") && enemy.getType().equals("water")) {
            return 1.25;
        } else if(this.getType().equals("fire") && enemy.getType().equals("water")) {
            return 0.75;
        } else if(this.getType().equals("fire") && enemy.getType().equals("grass")) {
            return 1.25;
        } else if(this.getType().equals("water") && enemy.getType().equals("grass")) {
            return 0.75;
        } else if(this.getType().equals("water") && enemy.getType().equals("fire")) {
            return 1.25;
        } else {
            return 1.;
        }
    }
    
   
    public String getNama() {
        return nama;
    }

    public int getHp() {
        return hp;
    }

    public int damaged(int hp) {
        int tempHp = hp;
        this.hp -= hp;
        if(this.hp < 0) {
            tempHp = tempHp + this.hp;
            this.hp = 0;
        }
        return tempHp;
    }
    public void healed(int hp) {
        this.hp += hp;
        if(this.hp > this.maxHp) {
            this.hp = maxHp;
        }
    }

    public void setExpNeededToLevelUp(int expNeededToLevelUp) {
        this.expNeededToLevelUp = expNeededToLevelUp;
    }

    public int getExp() {
        return exp;
    }

    public int getExpNeededToLevelUp() {
        return expNeededToLevelUp;
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
            levelUpBehaviour();
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
    
    public Skill getSkill(int idxSkill) {
        return skillList.get(idxSkill);
    }
    public void addSkill(Skill skill) {
        int numSkillNow = this.getNumberOfSkill();
        if(numSkillNow < 4) {
            this.skillList.set(numSkillNow, skill);
        }
        
    }
    public void replaceSkill(int idx,Skill skill) {
        this.skillList.set(idx, skill);
    }
    public int getNumberOfSkill() {
        int countNotNull = 0;
        for(int i = 0;i < skillList.size();i++) {
            if(skillList.get(i) != null) {
                countNotNull = countNotNull + 1;
            }
        }
        return countNotNull;
    }
    
    public boolean isDead() {
        return this.hp <= 0;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
    public void addExp(int exp) {
        this.exp += exp;
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
