/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon.Water;

import Pokemon.ImagePath;
import Pokemon.Pokemon;
import Pokemon.Skill.BodySlam;
import Pokemon.Skill.Heal;
import Pokemon.Skill.HitMe;
import Pokemon.Skill.Steal;
import Pokemon.Skill.Tackle;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author LVOILA
 */
public class WaterSimonEvo extends Pokemon{

    public WaterSimonEvo() throws IOException {
        super("WaterMon evo", 61, 28, ImagePath.SIMON_EVO_WATER_FRONT, 4, 4);
        addSkill(new Tackle());
    }
    
    public WaterSimonEvo(int lvl) throws IOException {
        super("WaterMon evo", 52, 20, ImagePath.SIMON_WATER_FRONT, 4, 4);
        addSkill(new Tackle());
        
        this.expNeededToLevelUp = getMaxExp(lvl);
        this.maxHp += 5*(lvl - 1);
        this.damage += 6*(lvl - 1);
        this.hp = this.maxHp;
        this.lvl = lvl;
        if(this.getLvl() >= 10) {
            addSkill(new BodySlam());
        } 
        if(this.getLvl() >= 20) {
            addSkill(new Steal());
        }
    }
    
    public WaterSimonEvo(WaterSimon pokemon) throws IOException {
        super("WaterMon evo", 115, 21, ImagePath.SIMON_EVO_WATER_FRONT , 4, 4);
        
        Random rand = new Random();
        this.lvl = pokemon.getLvl();
        this.expNeededToLevelUp = getMaxExp(this.lvl);
        
        this.maxHp = pokemon.getMaxHp() + rand.nextInt(15);
        this.damage = pokemon.getDamage() + rand.nextInt(25);
        this.hp = this.maxHp;
        
        for(int i = 0;i < pokemon.getNumberOfSkill();i++) {
            addSkill(pokemon.getSkill(i));
        }
//        this.renderFront = false;
        addSkill(new HitMe());
    }

    
    @Override
    protected void levelUpBehaviour() {
        Random rand = new Random();
        this.maxHp += rand.nextInt(7) + 5;
        this.hp = this.maxHp;
        this.damage += rand.nextInt(7) + 6;
        
        if(this.getLvl() == 6) {
            addSkill(new BodySlam());
        } else if(this.getLvl() == 15) {
            addSkill(new Steal());
        }
    }

    @Override
    public int getExpDrop() {
        return 120*this.getLvl();
    }

    @Override
    public int getGoldDrop() {
        return 100*this.getLvl();
    }

    @Override
    public String getType() {
        return "water";
    }
    
}
