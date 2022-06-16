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
public class Steal extends Skill{
    
    public Steal() {
        super("Steal !");
    }
    
    @Override
    public String use(Pokemon player, Pokemon enemy, boolean usedByPlayer) {
        int heal = player.getDamage()  / 2;
        player.healed(heal);
        int damage = (int) (player.getDamage()*1);
        enemy.damaged(damage);
        
        if(usedByPlayer){
            return "enemy damaged by " + damage + "Player +" + heal + "HP";
        }else{
            return "player damaged by " + damage + "Enemy +" + heal + "HP";
        }
    }
    
    @Override
    public String getDescription() {
        return "This skill will heal the caster based by 1000% of pokemon damage.";
    }
    
}
