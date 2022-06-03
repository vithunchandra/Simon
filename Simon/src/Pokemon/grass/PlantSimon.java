/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon.grass;

import Pokemon.Evolvable;
import Pokemon.ImagePath;
import Pokemon.Pokemon;
import Pokemon.Skill.BodySlam;
import Pokemon.Skill.Heal;
import Pokemon.Skill.Steal;
import Pokemon.Skill.Tackle;
import java.awt.Graphics;
import java.io.IOException;
import Util.MyFrame;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LVOILA
 */
public class PlantSimon extends Pokemon implements Evolvable{

    public PlantSimon(int maxHp, int damage) throws IOException {
        super("Plant Simon", maxHp, damage, ImagePath.SIMON_FRONT_PATH, 4,4);
        this.skillList.set(0, new Tackle());
    }
    
    public PlantSimon() throws IOException {
        super("Plant Simon", 100, 10, ImagePath.SIMON_FRONT_PATH, 4,4);
        this.skillList.set(0, new Tackle());
    }
    
    public PlantSimon(int lvl) throws IOException {
        super("Plant Simon", 100, 10, ImagePath.SIMON_FRONT_PATH, 4,4);
        this.skillList.set(0, new Tackle());
        
        this.expNeededToLevelUp = getMaxExp(lvl);
        this.maxHp += 7*(lvl - 1);
        this.damage += 2*(lvl - 1);
        this.hp = this.maxHp;
        this.lvl = lvl;
        if(this.getLvl() >= 10) {
            addSkill(new BodySlam());
        } 
        if(this.getLvl() >= 20) {
            addSkill(new Steal());
        }
    }

    @Override
    public String getType() {
        return "grass";
    }

    @Override
    protected void levelUpBehaviour() {
        Random rand = new Random();
        this.maxHp += rand.nextInt(20) + 8;
        this.hp = this.maxHp;
        this.damage += rand.nextInt(10) + 3;
        
        if(this.getLvl() == 6) {
            addSkill(new BodySlam());
        } else if(this.getLvl() == 15) {
            addSkill(new Heal());
        }
    }

    @Override
    public int getExpDrop() {
        return 100*this.getLvl();
    }

    @Override
    public int getGoldDrop() {
        return 80*this.getLvl();
    }

    @Override
    public boolean canEvolve() {
        return this.getLvl() >= 6;
    }

    @Override
    public Pokemon evolve() {
        try {
            return new PlantSimonEvo(this);
        } catch (IOException ex) {
            Logger.getLogger(PlantSimon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
