/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon.Skill;

import Pokemon.Pokemon;
import java.util.Random;

/**
 *
 * @author LVOILA
 */
public class Copycat extends Skill{

    public Copycat() {
        super("Copycat");
    }

    @Override
    public String use(Pokemon player, Pokemon enemy, boolean usedByPlayer) {
        Random rand = new Random();
        int randInt = rand.nextInt(enemy.getNumberOfSkill());
        return enemy.getSkill(randInt).use(player, enemy, usedByPlayer);
    }

    @Override
    public String getDescription() {
        return "Copy your enemy skill randomly and use it as your skill!";
    }
    
}
