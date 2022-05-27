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
public class Heal extends Skill{

    public Heal() {
        super("Heal");
    }

    @Override
    public String use(Pokemon player, Pokemon enemy, boolean usedByPlayer) {
        int heal = player.getDamage() * 10;
        player.healed(player.getHp() + heal);
        
        if(usedByPlayer){
            return "Player +" + heal + "HP";
        }else{
            return "Enemy +" + heal + "HP";
        }
    }

    @Override
    public String getDescription() {
        return "This skill will heal the caster based by 1000% of pokemon damage.";
    }
    
}
