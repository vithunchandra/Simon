/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon;

import Pokemon.Fire.Magmar;
import Pokemon.Water.Lapras;
import Pokemon.Water.WaterSimon;
import Pokemon.Water.WaterSimonEvo;
import Pokemon.grass.PlantSimon;
import Pokemon.grass.PlantSimonEvo;
import Pokemon.grass.Venusaur;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author LVOILA
 */
public class RandPokemon {
    private static final int size = 5;
    
    public static Pokemon getPokemon(int lvl) throws IOException {
        Random rand = new Random();
        int randNow = rand.nextInt(size);
        
        if(randNow == 0) {
            return new Lapras(lvl);
        } else if(randNow == 1) {
            return new Magmar(lvl);
        } else if(randNow == 2) {
            return new Venusaur(lvl);
        } else if(randNow == 3) {
            PlantSimon temp = new PlantSimon(lvl);
            if(temp.canEvolve()) {
                return new PlantSimonEvo(lvl);
            } else {
                return new PlantSimon(lvl);
            }
        } else if(randNow == 4) {
            WaterSimon temp = new WaterSimon(lvl);
            if(temp.canEvolve()) {
                return new WaterSimonEvo(lvl);
            } else {
                return new WaterSimon(lvl);
            }
        }
        else {
            return null;
        }
        
    }
    
    public static int getSize() {
        return size;
    }
}
