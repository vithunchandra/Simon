/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon.grass;

import Pokemon.ImagePath;
import Pokemon.Pokemon;
import Pokemon.Skill.BodySlam;
import Pokemon.Skill.Heal;
import Pokemon.Skill.HitMe;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author LVOILA
 */
public class PlantSimonEvo extends Pokemon {

    public PlantSimonEvo() throws IOException {
        super("Evolved Plant Simon", 90, 21, ImagePath.SIMON_EVO_FRONT_PATH , 4, 4);
    }
    
    public PlantSimonEvo(int lvl) throws IOException {
        super("Evolved Plant Simon", 90, 21, ImagePath.SIMON_EVO_FRONT_PATH , 4, 4);
        
        this.expNeededToLevelUp = getMaxExp(lvl);
        this.maxHp += 8*(lvl - 1);
        this.damage += 3*(lvl - 1);
        this.hp = this.maxHp;
        this.lvl = lvl;
        if(this.getLvl() >= 10) {
            addSkill(new BodySlam());
        } 
        if(this.getLvl() >= 20) {
            addSkill(new Heal());
        }
    }
    
    public PlantSimonEvo(PlantSimon pokemon) throws IOException {
        super("Evolved Plant Simon", 90, 21, ImagePath.SIMON_EVO_FRONT_PATH , 4, 4);
        Random rand = new Random();
        this.lvl = pokemon.getLvl();
        this.expNeededToLevelUp = getMaxExp(this.lvl);
        
        this.maxHp = pokemon.getMaxHp() + rand.nextInt(30);
        this.damage = pokemon.getDamage() + rand.nextInt(15);
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
        return 160*this.getLvl();
    }

    @Override
    public int getGoldDrop() {
        return 120*this.getLvl();
    }

    @Override
    public String getType() {
        return "grass";
    }
    
}
