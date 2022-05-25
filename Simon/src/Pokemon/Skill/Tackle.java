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
    }

    @Override
    public String use(Pokemon player, Pokemon enemy) {
        int damage = (int) (player.getDamage()*atkPercentage);
        enemy.damaged(damage);
        return "enemy damaged by " + damage;
    }

    @Override
    public String getDescription() {
        return "This is tackle";
    }
    
}
