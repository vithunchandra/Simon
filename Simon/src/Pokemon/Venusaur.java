/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon;

import Pokemon.Skill.Tackle;
import java.io.IOException;

/**
 *
 * @author LVOILA
 */
public class Venusaur extends Pokemon{

    public Venusaur() throws IOException {
        super("Venusaur", 432, 41, ImagePath.VENUSAUR_FRONT_PATH, 59, 63);
        addSkill(new Tackle());
    }
    

    
}
