/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BattleCanvas;

import Pokemon.ImagePath;
import Pokemon.Pokemon;
import Util.ImageLoader;
import Util.MyFrame;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import simon.Player;
import java.awt.Image;
import simon.BattleAltLoop;

/**
 *
 * @author LVOILA
 */
public class SwitchCanvas implements Drawable {
    
    private int idxChange1,idxChange2;
    private CanvasButton switchBtn1,switchBtn2;
    private PokemonBarAlt switchBar1,switchBar2;
    private CanvasButton backButton;
    private ArrayList<Pokemon> playerPokemon;
    private Image bgImg;
    private BattleAltLoop battleAltLoop;
    private CanvasMouseListener mouse;
    public SwitchCanvas(CanvasMouseListener mouse,BattleAltLoop battleAltLoop) {
        try {
            this.battleAltLoop = battleAltLoop;
            this.mouse = mouse;
            this.idxChange1 = -1;
            this.idxChange2 = -1;
            
            this.bgImg= ImageLoader.loadImage(ImagePath.BATTLE_BG1);
            this.playerPokemon = Player.pokemonInParty;
            
            backButton = new CanvasButton("Back", 0, 0, 100, 50, mouse);
            switchBtn1 = new CanvasButton("Switch!", 175, 475, 150, 50, mouse);
            switchBtn2 = new CanvasButton("Switch!", 625, 475, 150, 50, mouse);
        } catch (IOException ex) {
            Logger.getLogger(SwitchCanvas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void reInit() {
        int whichBox = 0;
        for(int i = 0;i < playerPokemon.size();i++) {
            if(i != battleAltLoop.getPlayerPokemonIdx()) {
                if(whichBox == 0) {
                    this.idxChange1 = i;
                }
                else if(whichBox == 1) {
                    this.idxChange2 = i;
                }

                whichBox++;
            }
        }

        this.playerPokemon.get(this.idxChange1).setRenderFront(true);
        this.playerPokemon.get(this.idxChange2).setRenderFront(true);
        
        switchBar1 = new PokemonBarAlt(this.playerPokemon.get(this.idxChange1), 115, 370, 260, 100, mouse);
        switchBar2 = new PokemonBarAlt(this.playerPokemon.get(this.idxChange2), 570, 370, 260, 100, mouse);
    }
    
    @Override
    public void draw(Graphics g) {
        this.switchBtn1.setRenderedToFalse();
        this.switchBtn2.setRenderedToFalse();
        this.backButton.setRenderedToFalse();

        g.drawImage(this.bgImg, 0, 0,MyFrame.DEFAULT_WIDTH,MyFrame.DEFAULT_HEIGHT, null);

        g.fillRect(50, 75, 400, 500);
        g.fillRect(500, 75, 400, 500);

        this.playerPokemon.get(this.idxChange1).draw(g);
        this.playerPokemon.get(this.idxChange2).draw(g);

        this.backButton.draw(g);

        this.switchBtn1.draw(g);
        this.switchBtn2.draw(g);

        this.switchBar1.draw(g);
        this.switchBar2.draw(g);
    }
    
    
    public void logicLoop(long diff) {
        this.playerPokemon.get(this.idxChange1).setBound(125, 60, 225, 300);
        this.playerPokemon.get(this.idxChange2).setBound(600, 60, 225, 300);

        this.playerPokemon.get(this.idxChange1).logicLoop(diff);
        this.playerPokemon.get(this.idxChange2).logicLoop(diff);

        if(this.switchBtn1.clicked() && this.switchBtn1.isRendered()) {
            battleAltLoop.setPlayerPokemonIdx(this.idxChange1);  
            battleAltLoop.getPokeBar2().setPokemon(this.playerPokemon.get(battleAltLoop.getPlayerPokemonIdx()));
            battleAltLoop.setNowState("battle");
        }
        if(this.switchBtn2.clicked() && this.switchBtn2.isRendered()) {
            battleAltLoop.setPlayerPokemonIdx(this.idxChange2);  
            battleAltLoop.getPokeBar2().setPokemon(this.playerPokemon.get(battleAltLoop.getPlayerPokemonIdx()));
            battleAltLoop.setNowState("battle");
        }
        if(this.backButton.clicked() && this.backButton.isRendered()) {
            battleAltLoop.setNowState("battle");
        }
    }
}
