/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon.Water;

import Pokemon.ImagePath;
import Pokemon.Pokemon;
import Pokemon.Skill.BodySlam;
import Pokemon.Skill.Heal;
import Pokemon.Skill.Tackle;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author LVOILA
 */
public class Lapras extends Pokemon{

    public Lapras() throws IOException {
        super("Lapras", 67, 11, ImagePath.LAPRAS_FRONT_PATH, 89, 93);
        addSkill(new Tackle());
    }
    
    public Lapras(int lvl) throws IOException {
        super("Lapras", 67, 11, ImagePath.LAPRAS_FRONT_PATH, 89, 93);
        addSkill(new Tackle());
        
        this.expNeededToLevelUp = getMaxExp(lvl);
        this.maxHp += 10*lvl;
        this.damage += 3*lvl;
        this.hp = this.maxHp;
        this.lvl = lvl;
        if(this.getLvl() >= 10) {
            addSkill(new BodySlam());
        } 
        if(this.getLvl() >= 20) {
            addSkill(new Heal());
        }
    }
   
    
    
    @Override
    protected void levelUpBehaviour() {
        Random rand = new Random();
        this.maxHp += rand.nextInt(15) + 10;
        this.hp = this.maxHp;
        this.damage += rand.nextInt(5) + 3;
        
        if(this.getLvl() == 6) {
            addSkill(new BodySlam());
        } else if(this.getLvl() == 15) {
            addSkill(new Heal());
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
