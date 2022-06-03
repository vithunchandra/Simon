/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon.Skill;

import Pokemon.Pokemon;

/**
 *
 * @author asus
 */
public class Overheat extends Skill{
    Double multiplier = 1.25;
    
    public Overheat() {
        super("Overheat");
    }

    @Override
    public String use(Pokemon player, Pokemon enemy, boolean usedByPlayer) {
        int totalDamage = (int) (player.getDamage() * multiplier);
        totalDamage = (int)(totalDamage + (player.getHp() * 0.3)*1.1);
        int hpCost = (int) (player.getHp() * 0.3);
        
        Double multType = player.getTypeMultiplier(enemy);
        totalDamage = (int)(totalDamage*multType);
        
        enemy.damaged(totalDamage);
        player.damaged(hpCost);
        
        
        String returnString = "";
        if(multType >= 1.2) {
            returnString = returnString + "it is super effective\n";
        }
        else if(multType <= 0.8) {
            returnString = returnString + "it is not effective\n";
        }

        if(usedByPlayer){
            returnString += "Enemy damaged by " + totalDamage
                   + "\nPlayer -" + hpCost + " HP";
        }else{
            returnString += "Player damaged by " + totalDamage
                    + "\nEnemy -" + hpCost + " HP";
        }
        return returnString;
    }

    @Override
    public String getDescription() {
        return "Use Player hp to make powerful damage.Deal 125% damage plus sacrificed health * 1.1 to the enemy(sacrifice 30% of current health)";
    }
    
}
