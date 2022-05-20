/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon;

import java.util.ArrayList;

public class ImagePath {
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
