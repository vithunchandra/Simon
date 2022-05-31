/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon.Skill;

import Pokemon.Pokemon;
/**
 *
 * @author Jason
 */
public class HitMe extends Skill{
    
    public HitMe() {
        super("Hit Me");
    }
    
    @Override
    public String use(Pokemon player, Pokemon enemy, boolean usedByPlayer) {
        //damage based on player hp
        int damage = player.getDamage();
        if(player.getHp() <= player.getMaxHp()*2/10) {
            damage = player.getDamage()*3;
        }
        else if(player.getHp() <= player.getMaxHp()*5/10) {
            damage = (int) (player.getDamage()*1.5);
        }
        
        
        //checking effectivity
        Double multType = player.getTypeMultiplier(enemy);
        damage = (int)(damage*multType);
        
        //apply damage
        enemy.damaged(damage);
        
        //return string for effectivity
        String returnString = "";
        if(multType >= 1.2) {
            returnString = returnString + "it is super effective\n";
        }
        else if(multType <= 0.8) {
            returnString = returnString + "it is not effective\n";
        }
        
        //return string to report damage dealt
        if(usedByPlayer) {
            returnString += "enemy damaged by " + damage;
        } else {
            returnString += "player damaged by " + damage;
        }
        return returnString;
    }
    
    @Override
    public String getDescription() {
        return "This skill will deal 150% damage when caster health bellow 50% and 300% damage when bellow 20%";
    }
    
}
