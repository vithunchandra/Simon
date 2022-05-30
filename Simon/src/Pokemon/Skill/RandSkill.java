/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon.Skill;

import java.util.Random;

/**
 *
 * @author LVOILA
 */
public class RandSkill {
    private static final int size = 4;
    
    public static Skill getSkill() {
        Random rand = new Random();
        int randNow = rand.nextInt(size);
        
        if(randNow == 0) {
            return new Tackle();
        } else if(randNow == 1) {
            return new BodySlam();
        } else if(randNow == 2) {
            return new Overheat();
        } else if(randNow == 3) {
            return new Heal();
        }
        else {
            return null;
        }
        
    }
    
    public static int getSize() {
        return size;
    }
}
