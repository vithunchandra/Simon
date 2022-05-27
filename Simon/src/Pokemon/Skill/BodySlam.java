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
        player.damaged(recoil);
        enemy.damaged(damage);
        if(usedByPlayer) {
            return "Enemy damaged by " + damage + "\nPlayer damaged because of recoil by " + recoil ;
        } else {
            return "Player damaged by " + damage + "\nenemy damaged because of recoil by " + recoil;
        }
    }

    @Override
    public String getDescription() {
        return "Give a huge amount of damage to enemy but the caster will damaged by (maxHp/15) as the cost for this skill.";
    }
    
    
    
}
