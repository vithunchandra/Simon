/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon.grass;

import Pokemon.Evolvable;
import Pokemon.ImagePath;
import Pokemon.Pokemon;
import Pokemon.Skill.Tackle;
import java.awt.Graphics;
import java.io.IOException;
import Util.MyFrame;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LVOILA
 */
public class PlantSimon extends Pokemon implements Evolvable{

    public PlantSimon(int maxHp, int damage) throws IOException {
        super("Plant Simon", maxHp, damage, ImagePath.SIMON_FRONT_PATH, 4,4);
        this.skillList.set(0, new Tackle());
    }

    @Override
    public String getType() {
        return "grass";
    }

    @Override
    protected void levelUpBehaviour() {
        
    }

    @Override
    public int getExpDrop() {
        return 100*this.getLvl();
    }

    @Override
    public int getGoldDrop() {
        return 80*this.getLvl();
    }

    @Override
    public boolean canEvolve() {
        return this.getLvl() >= 5;
    }

    @Override
    public Pokemon evolve() {
        try {
            return new PlantSimonEvo(this);
        } catch (IOException ex) {
            Logger.getLogger(PlantSimon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
