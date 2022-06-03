/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon.grass;

import Pokemon.ImagePath;
import Pokemon.Pokemon;
import Pokemon.Skill.HitMe;
import Pokemon.Skill.Steal;
import Pokemon.Skill.Tackle;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author LVOILA
 */
public class Venusaur extends Pokemon{

    public Venusaur() throws IOException {
        super("Venusaur", 87, 11, ImagePath.VENUSAUR_FRONT_PATH, 59, 63);
        addSkill(new Tackle());
        
    }
    
    public Venusaur(int lvl) throws IOException {
        super("Venusaur", 87, 11, ImagePath.VENUSAUR_FRONT_PATH, 59, 63);
        addSkill(new Tackle());
        
        this.expNeededToLevelUp = getMaxExp(lvl);
        this.maxHp += 8*(lvl - 1);
        this.damage += 5*(lvl - 1);
        this.hp = this.maxHp;
        this.lvl = lvl;
        if(this.getLvl() >= 8) {
            addSkill(new HitMe());
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
        this.maxHp += rand.nextInt(12) + 8;
        this.hp = this.maxHp;
        this.damage += rand.nextInt(6) + 5;
        
        if(this.getLvl() == 6) {
            addSkill(new HitMe());
        } else if(this.getLvl() == 18) {
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
    

    
}
