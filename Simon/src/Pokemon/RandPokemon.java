/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon;

import Pokemon.Fire.Magmar;
import Pokemon.Water.Lapras;
import Pokemon.grass.PlantSimon;
import Pokemon.grass.Venusaur;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author LVOILA
 */
public class RandPokemon {
    private static final int size = 2;
    public static Pokemon getPokemon() throws IOException {
        Random rand = new Random();
        int randNow = rand.nextInt(size);
        
        if(randNow == 0) {
            return new PlantSimon(1, 1);
        } else if(randNow == 1) {
            return new Venusaur();
        } else if(randNow == 2) {
            return new Lapras();
        }
        else {
            return null;
        }
        
    }
    
    public static Pokemon getPokemon(int lvl) throws IOException {
        Random rand = new Random();
        int randNow = rand.nextInt(size);
        
        if(randNow == 0) {
            return new Lapras(lvl);
        } else if(randNow == 1) {
            return new Magmar(lvl);
        } else if(randNow == 2) {
            return new Lapras();
        }
        else {
            return null;
        }
        
    }
    
    public static int getSize() {
        return size;
    }
}
