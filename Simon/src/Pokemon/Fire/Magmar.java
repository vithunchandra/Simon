/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon.Fire;

import Pokemon.ImagePath;
import Pokemon.Pokemon;
import Pokemon.Skill.BodySlam;
import Pokemon.Skill.Heal;
import Pokemon.Skill.Overheat;
import Pokemon.Skill.Tackle;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author LVOILA
 */
public class Magmar extends Pokemon{

    public Magmar() throws IOException {
        super("Magmar", 87, 9, ImagePath.MAGMAR_FRONT_PATH, 60, 123);
        addSkill(new Tackle());
    }
    
    public Magmar(int lvl) throws IOException {
        super("Magmar", 87, 9, ImagePath.MAGMAR_FRONT_PATH, 60, 123);
        addSkill(new Tackle());
        
        this.expNeededToLevelUp = getMaxExp(lvl);
        this.maxHp += 9*(lvl - 1);
        this.damage += 4*(lvl - 1);
        this.hp = this.maxHp;
        this.lvl = lvl;
        if(this.getLvl() >= 10) {
            addSkill(new BodySlam());
        } 
        if(this.getLvl() >= 30) {
            addSkill(new Overheat());
        }
    }

    @Override
    protected void levelUpBehaviour() {
        Random rand = new Random();
        this.maxHp += rand.nextInt(12) + 9;
        this.hp = this.maxHp;
        this.damage += rand.nextInt(6) + 4;
        
        if(this.getLvl() == 6) {
            addSkill(new BodySlam());
        } else if(this.getLvl() == 22) {
            addSkill(new Overheat());
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
        return "fire";
    }
    
}
