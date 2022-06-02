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
        enemy.damaged(totalDamage);
        player.damaged(hpCost);

        if(usedByPlayer){
            return "Enemy damaged by " + totalDamage
                   + "\nPlayer -" + hpCost + " HP";
        }else{
            return "Player damaged by " + totalDamage
                    + "\nEnemy -" + hpCost + " HP";
        }
    }

    @Override
    public String getDescription() {
        return "Use Player hp to make powerful damage.Deal 125% damage plus sacrificed health * 1.1 to the enemy(sacrifice 30% of current health)";
    }
    
}
