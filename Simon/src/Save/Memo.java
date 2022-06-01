/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Save;

import Pokemon.Pokemon;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import simon.Player;

/**
 *
 * @author LVOILA
 */
public class Memo implements Serializable {
    private ArrayList<PokemonMemo> pokemonInParty;
    private ArrayList<PokemonMemo> pokemonInBox;
    
    private int pokeCoin;
    private int pokeBall,greatBall,ultraBall;
    private int potion,superPotion,fullRestore;
    

    public Memo() {
        pokemonInParty = new ArrayList<>();
        pokemonInBox = new ArrayList<>();
        for(Pokemon poke : Player.pokemonInParty) {
            this.pokemonInParty.add(new PokemonMemo(poke));
        }
        for(Pokemon poke : Player.pokemonInBox) {
            this.pokemonInBox.add(new PokemonMemo(poke));
        }
        this.pokeCoin = Player.pokeCoin;
        
        this.pokeBall = Player.pokeBall;
        this.greatBall = Player.greatBall;
        this.ultraBall = Player.ultraBall;
        this.potion = Player.potion;
        this.superPotion = Player.superPotion;
        this.fullRestore = Player.fullRestore;
    }
    
    public void load() throws IOException {
        Player.pokeBall = this.pokeBall;
        Player.greatBall = this.greatBall;
        Player.ultraBall = this.ultraBall;
        Player.potion = this.potion;
        Player.superPotion = this.superPotion;
        Player.fullRestore = this.fullRestore;
        Player.pokeCoin = this.pokeCoin;
        
        ArrayList<Pokemon> playerPokemonParty = new ArrayList<>();
        ArrayList<Pokemon> playerPokemonBox = new ArrayList<>();
        
        for(int i = 0;i < pokemonInParty.size();i++) {
            playerPokemonParty.add(pokemonInParty.get(i).load());
        }
        for(int i = 0;i < pokemonInBox.size();i++) {
            playerPokemonBox.add(pokemonInBox.get(i).load());
        }
        
        Player.pokemonInBox = playerPokemonBox;
        Player.pokemonInParty = playerPokemonParty;
    }
}
