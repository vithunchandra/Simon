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
public class BodySlam extends Skill {

    public BodySlam() {
        super("Body Slam");
    }

    @Override
    public String use(Pokemon player, Pokemon enemy,boolean usedByPlayer) {
        int recoil = player.getMaxHp() / 15;
        int damage = player.getDamage()*18/10;
        Double multType = player.getTypeMultiplier(enemy);
        damage = (int)(damage*multType);
        player.damaged(recoil);
        enemy.damaged(damage);
        
        String returnString = "";
        if(multType >= 1.2) {
            returnString = returnString + "it is super effective\n";
        }
        else if(multType <= 0.8) {
            returnString = returnString + "it is not effective\n";
        }
        
        if(usedByPlayer) {
            returnString += "Enemy damaged by " + damage + "\nPlayer damaged because of recoil by " + recoil ;
        } else {
            returnString += "Player damaged by " + damage + "\nenemy damaged because of recoil by " + recoil;
        }
        return returnString;
    }

    @Override
    public String getDescription() {
        return "Give a huge amount of damage(180%) to enemy but the caster will damaged by (maxHp/15) as the cost for this skill.";
    }
    
    
    
}
