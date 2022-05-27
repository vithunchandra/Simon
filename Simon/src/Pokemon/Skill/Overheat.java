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
    Double multiplier = 5.0;
    
    public Overheat() {
        super("Overheat");
    }

    @Override
    public String use(Pokemon player, Pokemon enemy, boolean usedByPlayer) {
        int totalDamage = (int) (player.getDamage() * multiplier);
        int hpCost = (int) (player.getHp() * 0.2);
        enemy.damaged(totalDamage);
        player.damaged(hpCost);
        multiplier += 0.5;
        if(usedByPlayer){
            return "Enemey damaged by " + totalDamage
                   + "\nPlayer -" + hpCost + "HP";
        }else{
            return "Player damaged by " + totalDamage
                    + "\nEnemy -" + hpCost + "HP";
        }
    }

    @Override
    public String getDescription() {
        return "Give 100% damage to the enemy, everytime this skill is used the multiplier will increase by 50%. this skill will cost 20% of caster current HP";
    }
    
}
