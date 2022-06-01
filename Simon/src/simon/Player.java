/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import Pokemon.grass.PlantSimon;
import Pokemon.Pokemon;
import Pokemon.Skill.*;
import Pokemon.Water.Lapras;
import Pokemon.grass.Venusaur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;
import Item.*;
import Save.Memo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

/**
 *
 * @author LVOILA
 */
public class Player {
    public static ArrayList<Pokemon> pokemonInParty;
    public static ArrayList<Pokemon> pokemonInBox;
    public static ArrayList<Item> itemList;
    
    public static int pokeCoin;
    public static int pokeBall,greatBall,ultraBall;
    public static int potion,superPotion,fullRestore;

    // pokebal -> chance tangkep 30%;
    // greatBall -> chance tangkep 60%;
    // ultraBall -> chance tangkep 90%;
    // potion -> restore 25%;
    // superPotion -> restore 50%;
    // fullRestore -> restore 100%;
    
    public static void loadFileInfo() {
        try {
            FileInputStream fileIn = new FileInputStream("src\\Save\\memo.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Memo memo = (Memo)in.readObject();
            in.close();
            fileIn.close();
            memo.load();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void loadInfo() {
        pokemonInParty = new ArrayList<>();
        pokemonInBox =  new ArrayList<>();
        itemList = new ArrayList<>();
        
        pokeCoin = 1000;
        
        pokeBall = 5;
        greatBall = 0;
        ultraBall = 0;
        potion = 0;
        superPotion = 0;
        fullRestore = 0;
        
        try {
            pokemonInParty.add(new PlantSimon(200, 60));
            pokemonInParty.add(new Lapras());
            
            pokemonInParty.add(new PlantSimon(1, 1));
            
            
            pokemonInParty.get(0).levelUp();
            pokemonInParty.get(0).levelUp();
            pokemonInParty.get(0).levelUp();
            
            //pokemonInParty.get(0).addSkill(new Overheat());
//            pokemonInParty.get(2).levelUp();
//            pokemonInParty.get(2).levelUp();
//            pokemonInParty.get(2).levelUp();
//            pokemonInParty.get(2).levelUp();
//            pokemonInParty.get(2).levelUp();
            
            Random rand = new Random();
            for(int i=0; i<18; i++){
                pokemonInBox.add(new PlantSimon(rand.nextInt(100, 401), rand.nextInt(2, 40)));
                pokemonInBox.get(i).addSkill(new Tackle());
                int level = rand.nextInt(5);
                for(int j=0; j<level; j++){
                    pokemonInBox.get(pokemonInBox.size() - 1).levelUp();
                }
                pokemonInBox.get(i).setExpNeededToLevelUp(234);
                pokemonInBox.get(i).setExp(rand.nextInt(0, 234));
            }
            
            String[] name = {"Potion", "Super Potion", "Full Restore", "Poke Ball", "Great Ball", "Ultra Ball"};
            for(int i=0; i<6; i++){
                itemList.add(
                        new Item(name[i],
                        "Filler text is text that shares some characteristics of a real written text, but is random or otherwise generated. It may be used to display a sample of fonts, generate text for testing, or to spoof an e-mail spam filter.",
                        rand.nextInt(1000, 10000),
                        "src\\Material\\Image\\item.png"
                ));
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
