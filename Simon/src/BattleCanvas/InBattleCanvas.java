/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BattleCanvas;

import Pokemon.ImagePath;
import Pokemon.PlantSimon;
import Pokemon.Pokemon;
import Util.DoubleLinkList;
import Util.ImageLoader;
import Util.MyFrame;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import simon.BattleAltLoop;
import simon.Player;

/**
 *
 * @author LVOILA
 */
public class InBattleCanvas implements Drawable{
    private BattleAltLoop battleAltLoop;
    private CanvasButton useSkillBtn,changePokemonBtn,useItemBtn,runBtn;
    private PokemonBarAlt pokeBar,pokeBar2;
    private CanvasTextArea canvasTextArea;
    private CanvasMouseListener mouse;
    private ArrayList<Pokemon> playerPokemon,enemyPokemon;
    private int playerPokemonIdx,enemyPokemonIdx;
    
    private Image bgImg;
    private DoubleLinkList<String> dialogueNow;
    
    private Boolean usingSkill;
    private ArrayList<CanvasButton> skillButton;
    private String defaultText;
    
    //pokeball animation
    private int pokeAnimX,pokeAnimY;
    private Double pokeAnimT;
    private boolean drawEnemy;
    
    
    
    public InBattleCanvas(BattleAltLoop battleAltLoop,CanvasMouseListener mouse) throws IOException {
        this.mouse = mouse;
        this.battleAltLoop = battleAltLoop;
        //x,y,width,height
        this.useItemBtn = new CanvasButton("Use Item", MyFrame.DEFAULT_WIDTH - 310, MyFrame.DEFAULT_HEIGHT - 180, 140, 50, mouse);
        this.useSkillBtn = new CanvasButton("Use Skill", MyFrame.DEFAULT_WIDTH - 160, MyFrame.DEFAULT_HEIGHT - 180, 140, 50, mouse);
        this.changePokemonBtn = new CanvasButton("Switch", MyFrame.DEFAULT_WIDTH - 310, MyFrame.DEFAULT_HEIGHT - 120, 140, 50, mouse);
        this.runBtn = new CanvasButton("RUN", MyFrame.DEFAULT_WIDTH - 160, MyFrame.DEFAULT_HEIGHT - 120, 140, 50, mouse);
        
        this.playerPokemonIdx = 0;
        this.enemyPokemonIdx = 0;
        
        this.enemyPokemon = new ArrayList<>();
        this.enemyPokemon.add(new PlantSimon(100, 10)) ;
        this.enemyPokemon.get(0).setBound(MyFrame.DEFAULT_WIDTH/2 + 120, 0, 150, 300);
        this.playerPokemon = Player.pokemonInParty;
 
        
        this.pokeBar = new PokemonBarAlt(this.enemyPokemon.get(enemyPokemonIdx),0, 100, 350, 120, mouse);
        this.pokeBar2 = new PokemonBarAlt(this.playerPokemon.get(playerPokemonIdx),580, 330, 400, 150, mouse);
        
        this.dialogueNow = new DoubleLinkList<>();
        this.defaultText = "What you want to do next?";
        this.dialogueNow.add("You encounter " + this.enemyPokemon.get(enemyPokemonIdx).getNama() + "!");
        this.dialogueNow.add(defaultText);
        this.canvasTextArea = new CanvasTextArea(200, mouse,dialogueNow);
        this.bgImg= ImageLoader.loadImage(ImagePath.BATTLE_BG1);
        
        this.usingSkill = false;
        this.skillButton = new ArrayList<>();
        this.skillButton.add(new CanvasButton(null, 140, 515, 300, 60, mouse)) ;
        this.skillButton.add(new CanvasButton(null, 140, 590, 300, 60, mouse)) ;
        this.skillButton.add(new CanvasButton(null, 500, 515, 300, 60, mouse)) ;
        this.skillButton.add(new CanvasButton(null, 500, 590, 300, 60, mouse)) ;
        for(int i = 0;i < playerPokemon.get(playerPokemonIdx).getNumberOfSkill();i++) {
            this.skillButton.get(i).setText(playerPokemon.get(playerPokemonIdx).getSkill(i).getSkillName() );
        }
        
        
        this.pokeAnimX = 50;
        this.pokeAnimY = 500;
        this.pokeAnimT = -15.;
        this.drawEnemy = true;
    }
    
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(this.bgImg, 0, 0,MyFrame.DEFAULT_WIDTH,MyFrame.DEFAULT_HEIGHT, null);
        this.useItemBtn.setRenderedToFalse();
        this.useSkillBtn.setRenderedToFalse();
        this.changePokemonBtn.setRenderedToFalse();
        this.runBtn.setRenderedToFalse();

        if(battleAltLoop.getItemCanvas().isUsingPokeBall()) {

            g.fillRect(pokeAnimX, pokeAnimY, 50, 50);
            pokeAnimY = pokeAnimY - 15 + (int)((pokeAnimT*pokeAnimT)/6);
            pokeAnimX = pokeAnimX + 5;
            pokeAnimT = pokeAnimT + 0.25;

            if(pokeAnimT >= 15.5) {
                battleAltLoop.getItemCanvas().setUsingPokeBall(false);


                this.pokeAnimX = 50;
                this.pokeAnimY = 500;
                this.pokeAnimT = -15.;

                boolean catched = true;

                if(catched) {
                    this.dialogueNow.add("Congrats!\nYou catch the pokemon!");
                    this.dialogueNow.add("end");
                    this.canvasTextArea.nextText();
                    Player.pokemonInBox.add(this.enemyPokemon.get(enemyPokemonIdx));
                    this.drawEnemy = false;
                }
                else {
                    this.dialogueNow.add("You failed to catch the pokemon!");
                    this.canvasTextArea.nextText();
                }
            }


        }
        
        if(this.drawEnemy) {
            this.enemyPokemon.get(enemyPokemonIdx).draw(g);
        }
        else {
            g.fillRect(650, 200, 50, 50);
        }
        this.playerPokemon.get(playerPokemonIdx).draw(g);
        
        this.canvasTextArea.draw(g);
        this.pokeBar.draw(g);
        this.pokeBar2.draw(g);

        Boolean btnShowCon = !canvasTextArea.haveNextDialogue();
        btnShowCon = btnShowCon && !battleAltLoop.getItemCanvas().isUsingPokeBall();
        btnShowCon = btnShowCon && !this.usingSkill;
        if(btnShowCon) {
            this.useItemBtn.draw(g);
            this.useSkillBtn.draw(g);
            this.changePokemonBtn.draw(g);
            this.runBtn.draw(g);
        }
        if(usingSkill) {
            for(CanvasButton btn : skillButton) {
                if(btn.getText() != null) {
                    btn.draw(g);
                }
            }
        }
    }
    
    
    private boolean checkEnemyBeaten() {
        boolean enemyBeaten = true;
        for(int i = 0;i < enemyPokemon.size();i++) {
            if(enemyPokemon.get(i).getHp() > 0 ){
                enemyBeaten = false;
            }
        }
        if(enemyBeaten) {
            this.dialogueNow.add("You beat the enemy!");
            this.dialogueNow.add("end");
        }
        return enemyBeaten;
    }
    
    public void gameLogic() {
        if(this.usingSkill) {
            for(int i = 0;i < skillButton.size();i++) {
                CanvasButton btn = skillButton.get(i);
                if(btn.clicked() && btn.isRendered() && btn.getText() != null) {
                    Pokemon player = playerPokemon.get(playerPokemonIdx);
                    Pokemon enemy = enemyPokemon.get(enemyPokemonIdx);

                    String desc = player.getSkill(i).use(player, enemy);
                    this.dialogueNow.add(desc);
                    
                    if(!checkEnemyBeaten()) { 
                        this.dialogueNow.add(defaultText);
                    }
                    this.usingSkill = false;
                   
                    
                }
            }
        }
        
    }
    
    public void graphicLogic(long diff) {
        if(canvasTextArea.clicked()) {
            canvasTextArea.nextText();
        }
   
        if(this.canvasTextArea.getTextNow().equals("end")) {
            battleAltLoop.setBattling(false) ;
        }

        if(this.runBtn.clicked() && this.runBtn.isRendered()) {
            battleAltLoop.setBattling(false) ;
        }

        if(this.changePokemonBtn.clicked() && this.changePokemonBtn.isRendered()) {
            this.battleAltLoop.getSwitchCanvas().reInit();
            battleAltLoop.setNowState("switch"); 
        }

        if(this.useItemBtn.clicked() && this.useItemBtn.isRendered()) {
            battleAltLoop.setNowState("item"); 
        }
        
        if(this.useSkillBtn.clicked() && this.useSkillBtn.isRendered()) {
            this.dialogueNow.add("");
            this.canvasTextArea.nextText();
            this.usingSkill = true;
        }

        this.enemyPokemon.get(enemyPokemonIdx).logicLoop(diff);

        this.playerPokemon.get(playerPokemonIdx).setBound(100, 250, 225, 300);
        this.playerPokemon.get(playerPokemonIdx).setRenderFront(false);
        this.playerPokemon.get(playerPokemonIdx).logicLoop(diff);
    }
    
    public PokemonBarAlt getPokeBar2() {
        return pokeBar2;
    }

    public int getPlayerPokemonIdx() {
        return playerPokemonIdx;
    }


    public void setPlayerPokemonIdx(int playerPokemonIdx) {
        this.playerPokemonIdx = playerPokemonIdx;
    }

    public DoubleLinkList<String> getDialogueNow() {
        return dialogueNow;
    }

    public CanvasTextArea getCanvasTextArea() {
        return canvasTextArea;
    }
}
