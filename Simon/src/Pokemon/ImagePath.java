/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon;

import java.util.ArrayList;

public class ImagePath {
    //madsudku path buat static gini sih....
    public static final String SIMON_FRONT_PATH = "src\\Material\\Image\\simonLeaf.png";
    public static final String SIMON_EVO_FRONT_PATH = "src\\Material\\Image\\simonLeafEvo.png";
    public static final String SIMON_WATER_FRONT =  "src\\Material\\Image\\simonWater.png";
    public static final String SIMON_EVO_WATER_FRONT =  "src\\Material\\Image\\simonWaterEvo.png";
    
    public static final String VENUSAUR_FRONT_PATH = "src\\Material\\Image\\venusaur.png";
    public static final String LAPRAS_FRONT_PATH = "src\\Material\\Image\\lapras.png";
    public static final String MAGMAR_FRONT_PATH = "src\\Material\\Image\\magmar.png";
    
    public static final String BATTLE_BG1 = "src\\Material\\Image\\battleBG1.png";
    public static final String BATTLE_DIALOGUE = "src\\Material\\Image\\dialogue.png";
    public static final String BTN1 = "src\\Material\\Image\\btn1.png";
    public static final String BTN1_PRESS = "src\\Material\\Image\\btn1_press.png";
    public static final String INFO_BOX = "src\\Material\\Image\\infoBox.png";
    public static final String POKEBALL_IMG = "src\\Material\\Image\\pokeball.png";
    
    
    public static PokemonAssets getAssets(String pokemonCode){
        PokemonAssets assets;
        if(pokemonCode.equals("...")){
            assets = new PokemonAssets("DefaultFront", "DefaultBack", "SpriteFront", "SpriteBack");
        }else{
            assets = new PokemonAssets("DefaultFront", "DefaultBack", "SpriteFront", "SpriteBack");
        }
        return assets;
    }
}
