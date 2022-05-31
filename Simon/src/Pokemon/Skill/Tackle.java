/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon.Skill;

import Pokemon.Pokemon;

/**
 *
 * @author LVOILA
 */
public class Tackle extends Skill{
    public Double atkPercentage;

    public Tackle() {
        super("Tackle");
        atkPercentage = 1.1;
    }

    @Override
    public String use(Pokemon player, Pokemon enemy,boolean usedByPlayer) {
        int damage = (int) (player.getDamage()*atkPercentage);
        Double multType = player.getTypeMultiplier(enemy);
        damage = (int)(damage*multType);
        enemy.damaged(damage);
        
        String returnString = "";
        if(multType >= 1.2) {
            returnString = returnString + "it is super effective\n";
        }
        else if(multType <= 0.8) {
            returnString = returnString + "it is not effective\n";
        }
        
        if(usedByPlayer) {
            returnString += "enemy damaged by " + damage;
        } else {
            returnString += "player damaged by " + damage;
        }
        return returnString;
    }

    @Override
    public String getDescription() {
        return "Give 110% damage to the enemy. This is a basic skill that every pokemon have.";
    }
    
}
