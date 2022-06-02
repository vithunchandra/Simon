/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon.Water;

import Pokemon.Evolvable;
import Pokemon.ImagePath;
import Pokemon.Pokemon;
import Pokemon.Skill.BodySlam;
import Pokemon.Skill.Heal;
import Pokemon.Skill.Steal;
import Pokemon.Skill.Tackle;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LVOILA
 */
public class WaterSimon extends Pokemon implements Evolvable{

    public WaterSimon() throws IOException {
        super("WaterMon", 52, 20, ImagePath.SIMON_WATER_FRONT, 4, 4);
        addSkill(new Tackle());
    }
    
    
    public WaterSimon(int lvl) throws IOException {
        super("WaterMon", 52, 20, ImagePath.SIMON_WATER_FRONT, 4, 4);
        addSkill(new Tackle());
        
        this.expNeededToLevelUp = getMaxExp(lvl);
        this.maxHp += 5*(lvl - 1);
        this.damage += 5*(lvl - 1);
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
    protected void levelUpBehaviour() {
        Random rand = new Random();
        this.maxHp += rand.nextInt(7) + 5;
        this.hp = this.maxHp;
        this.damage += rand.nextInt(5) + 5;
        
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
    public String getType() {
        return "water";
    }

    @Override
    public boolean canEvolve() {
        return this.getLvl() >= 5;
    }

    @Override
    public Pokemon evolve() {
        try {
            return new WaterSimonEvo(this);
        } catch (IOException ex) {
            Logger.getLogger(WaterSimon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
