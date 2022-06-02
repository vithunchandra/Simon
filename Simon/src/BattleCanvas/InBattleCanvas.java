/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BattleCanvas;

import Pokemon.Evolvable;
import Pokemon.ImagePath;
import Pokemon.grass.PlantSimon;
import Pokemon.Pokemon;
import Pokemon.RandPokemon;
import Pokemon.grass.Venusaur;
import Util.DoubleLinkList;
import Util.ImageLoader;
import Util.MyFrame;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
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
    private Random rand;
    private Boolean isGym;
    
    private Image bgImg;
    private DoubleLinkList<String> dialogueNow;
    
    private Boolean usingSkill;
    private ArrayList<CanvasButton> skillButton;
    private final String defaultText;
    
    //pokeball animation
    private int pokeAnimX,pokeAnimY;
    private Double pokeAnimT;
    private boolean drawEnemy;
    private final Image pokeball;
    
    //gym pokemon change animation
    private int changeAnimX,changeAnimY;
    private Double changeAnimT;
    private boolean enemyChangePokemon;
    
    public InBattleCanvas(BattleAltLoop battleAltLoop,CanvasMouseListener mouse) throws IOException {
        this.mouse = mouse;
        this.battleAltLoop = battleAltLoop;
        this.rand = new Random();
        //x,y,width,height
        this.useItemBtn = new CanvasButton("Use Item", MyFrame.DEFAULT_WIDTH - 310, MyFrame.DEFAULT_HEIGHT - 180, 140, 50, mouse);
        this.useSkillBtn = new CanvasButton("Use Skill", MyFrame.DEFAULT_WIDTH - 160, MyFrame.DEFAULT_HEIGHT - 180, 140, 50, mouse);
        this.changePokemonBtn = new CanvasButton("Switch", MyFrame.DEFAULT_WIDTH - 310, MyFrame.DEFAULT_HEIGHT - 120, 140, 50, mouse);
        this.isGym = battleAltLoop.isIsGym();
        
        if(isGym) {
            this.runBtn = new CanvasButton("SURRENDER", MyFrame.DEFAULT_WIDTH - 160, MyFrame.DEFAULT_HEIGHT - 120, 140, 50, mouse);
        }
        else {
            this.runBtn = new CanvasButton("RUN", MyFrame.DEFAULT_WIDTH - 160, MyFrame.DEFAULT_HEIGHT - 120, 140, 50, mouse);
        }
        this.playerPokemonIdx = 0;
        this.enemyPokemonIdx = 0;
        
        
        //========================= Enemy Init ==========================
        int floor = this.battleAltLoop.getFloor();
        this.enemyPokemon = new ArrayList<>();
//        this.enemyPokemon.add(new PlantSimon(100, 10)) ;
        
        //rand.nextInt((floor - 1)*5 + 1, floor*5 + 1) )
        this.enemyPokemon.add(RandPokemon.getPokemon(rand.nextInt((floor - 1)*5 + 1, floor*5 + 1)));
        this.enemyPokemon.get(0).setBound(MyFrame.DEFAULT_WIDTH/2 + 120, 0, 150, 300);
        
        if(isGym) {
            this.enemyPokemon.add(RandPokemon.getPokemon(rand.nextInt((floor - 1)*5 + 1, floor*5 + 1)));
            this.enemyPokemon.add(RandPokemon.getPokemon(rand.nextInt((floor - 1)*5 + 1, floor*5 + 1)));
        }
       //=================================================================
        
        this.playerPokemon = Player.pokemonInParty;
        for(int i = 0;i < playerPokemon.size();i++) {
            if(playerPokemon.get(i).getHp() <= 0) {
                this.playerPokemonIdx = this.playerPokemonIdx + 1;
            }
        }
 
        
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
        this.pokeball = ImageLoader.loadImage(ImagePath.POKEBALL_IMG);
        

        this.changeAnimX = MyFrame.DEFAULT_WIDTH;
        this.changeAnimY = 0;
        this.changeAnimT = 0.;
        this.enemyChangePokemon = false;
    }
    
    private void pokeBallThrowAnimation(Graphics g,int chance) {
        g.drawImage(pokeball,pokeAnimX, pokeAnimY, 50, 50,null);
        pokeAnimY = pokeAnimY - 15 + (int)((pokeAnimT*pokeAnimT)/6);
        pokeAnimX = pokeAnimX + 5;
        pokeAnimT = pokeAnimT + 0.25;

        if(pokeAnimT >= 15.5) {
            battleAltLoop.getItemCanvas().setUsingItem(false);


            this.pokeAnimX = 50;
            this.pokeAnimY = 500;
            this.pokeAnimT = -15.;

            boolean catched = false;
            int randInteger = rand.nextInt(10) ;
            if(randInteger < chance) {
                catched  = true;
            }

            if(catched) {
                this.dialogueNow.add("Congrats!\nYou catch the pokemon!");
                this.dialogueNow.add("end");
                this.canvasTextArea.nextText();
                if(Player.pokemonInParty.size() < 3) {
                    Player.pokemonInParty.add(this.enemyPokemon.get(enemyPokemonIdx));
                } else {
                    Player.pokemonInBox.add(this.enemyPokemon.get(enemyPokemonIdx));
                }
                this.drawEnemy = false;
            }
            else {
                this.dialogueNow.add("You failed to catch the pokemon!");
                this.canvasTextArea.nextText();
                enemyTurn();
            }
        }
    }
    
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(this.bgImg, 0, 0,MyFrame.DEFAULT_WIDTH,MyFrame.DEFAULT_HEIGHT, null);
        this.useItemBtn.setRenderedToFalse();
        this.useSkillBtn.setRenderedToFalse();
        this.changePokemonBtn.setRenderedToFalse();
        this.runBtn.setRenderedToFalse();
        Boolean btnShowCon = true;
        
        if(enemyChangePokemon) {
            g.drawImage(pokeball,changeAnimX, changeAnimY, 50, 50,null);
            changeAnimX = changeAnimX - 5;
            changeAnimY = changeAnimY + (int)(changeAnimT*changeAnimT);
            changeAnimT = changeAnimT + 0.06;
            if(changeAnimT >= 3.8) {
                this.changeAnimX = MyFrame.DEFAULT_WIDTH;
                this.changeAnimY = 0;
                this.changeAnimT = 0.;
                this.enemyChangePokemon = false;
            }
            
            if(!enemyChangePokemon) {
                this.pokeBar.setPokemon(enemyPokemon.get(enemyPokemonIdx));
                this.enemyPokemon.get(enemyPokemonIdx).setBound(MyFrame.DEFAULT_WIDTH/2 + 120, 0, 150, 300);
            }
            
        }

        if(battleAltLoop.getItemCanvas().isUsingItem()) {
            String itemUsed = battleAltLoop.getItemCanvas().getItemUsed();
            if(itemUsed.equals("Poke Ball")) {
                pokeBallThrowAnimation(g,3);
            }
            else if(itemUsed.equals("Great Ball")) {
                pokeBallThrowAnimation(g,6);
            }
            else if(itemUsed.equals("Ultra Ball")) {
                pokeBallThrowAnimation(g,9);
            }
            else if(itemUsed.equals("Potion")) {
                battleAltLoop.getItemCanvas().setUsingItem(false);
                playerPokemon.get(playerPokemonIdx).healed(playerPokemon.get(playerPokemonIdx).getMaxHp() / 4);
                enemyTurn();
            }
            else if(itemUsed.equals("Super Potion")) {
                battleAltLoop.getItemCanvas().setUsingItem(false);
                playerPokemon.get(playerPokemonIdx).healed(playerPokemon.get(playerPokemonIdx).getMaxHp() / 2);
                enemyTurn();
            }
            else if(itemUsed.equals("Full Restore")) {
                battleAltLoop.getItemCanvas().setUsingItem(false);
                playerPokemon.get(playerPokemonIdx).healed(playerPokemon.get(playerPokemonIdx).getMaxHp());
                enemyTurn();
            }


        }
        
        if(this.drawEnemy) {
            this.enemyPokemon.get(enemyPokemonIdx).draw(g);
        }
        else {
            g.drawImage(pokeball,650, 200, 50, 50,null);
        }
        this.playerPokemon.get(playerPokemonIdx).draw(g);
        
        this.canvasTextArea.draw(g);
        this.pokeBar.draw(g);
        this.pokeBar2.draw(g);

        btnShowCon = !canvasTextArea.haveNextDialogue();
        btnShowCon = btnShowCon && !battleAltLoop.getItemCanvas().isUsingItem();
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
            //add gold,exp,etc
            int totalGold = 0;
            int totalExp = 0;
            
            for(Pokemon defeatedEnemyPokemon : enemyPokemon) {
                totalGold = totalGold + defeatedEnemyPokemon.getGoldDrop();
                totalExp = totalGold + defeatedEnemyPokemon.getExpDrop();
            }
            
            this.dialogueNow.add("You beat the enemy!");
            if(isGym) {
                Player.pokeCoin += totalGold;
                this.dialogueNow.add("You get " + totalGold + " poke-coin");
            } 
            Player.pokemonInParty.get(playerPokemonIdx).addExp(totalExp);
            this.dialogueNow.add("You get " + totalExp + " exp");
            
            if(Player.pokemonInParty.get(playerPokemonIdx).getExp() >= Player.pokemonInParty.get(playerPokemonIdx).getExpNeededToLevelUp()) {
                Player.pokemonInParty.get(playerPokemonIdx).levelUp();
                this.dialogueNow.add("Your Pokemon level increased!");
                
                if(Player.pokemonInParty.get(playerPokemonIdx) instanceof Evolvable) {
                    Evolvable pokemonEvolve = (Evolvable)Player.pokemonInParty.get(playerPokemonIdx);
                    if(pokemonEvolve.canEvolve()) {
                        Player.pokemonInParty.set(playerPokemonIdx, pokemonEvolve.evolve());
                    }
                }
            }
            
            this.dialogueNow.add("end");
        }
        return enemyBeaten;
    }
    
    private boolean checkPlayerBeaten() {
        boolean playerBeaten = true;
        for(int i = 0;i < playerPokemon.size();i++) {
            if(playerPokemon.get(i).getHp() > 0 ){
                playerBeaten = false;
            }
        }
        if(playerBeaten) {
            this.dialogueNow.add("You lose!");
            this.dialogueNow.add("end");
        }
        return playerBeaten;
    }
    
    public void enemyTurn() {
        Pokemon enemy = enemyPokemon.get(enemyPokemonIdx);
        Pokemon player = playerPokemon.get(playerPokemonIdx);
        
        int enemyRandAct = rand.nextInt(enemyPokemon.get(enemyPokemonIdx).getNumberOfSkill());
        String skillDesc = enemy.getSkill(enemyRandAct).use(enemy, player,false);
        this.dialogueNow.add(skillDesc);
        this.dialogueNow.add(defaultText);
        if(player.isDead()) {
            if(!checkPlayerBeaten()) {
                battleAltLoop.getSwitchCanvas().reInit();
                battleAltLoop.setNowState("switch"); 
            }
        }
    }
    
    public void gameLogic() {
        if(this.usingSkill) {
            for(int i = 0;i < skillButton.size();i++) {
                CanvasButton btn = skillButton.get(i);
                if(btn.clicked() && btn.isRendered() && btn.getText() != null) {
                    Pokemon player = playerPokemon.get(playerPokemonIdx);
                    Pokemon enemy = enemyPokemon.get(enemyPokemonIdx);

                    String desc = player.getSkill(i).use(player, enemy,true);
                    this.dialogueNow.add(desc);
                    
                    if(!checkEnemyBeaten()) { 
                        if(enemyPokemon.get(enemyPokemonIdx).getHp() <= 0) {
                            enemyPokemonIdx = enemyPokemonIdx + 1;
                            if(enemyPokemon.size() - enemyPokemonIdx > 0) {
                                this.enemyChangePokemon = true;
                            }
                            
                            this.dialogueNow.add("The enemy have " + (enemyPokemon.size() - enemyPokemonIdx) + " remaining pokemon!");
                        }
                        else {
                            enemyTurn();
                        }
//                        if(playerPokemon.get(playerPokemonIdx).getHp() > 0) {
//                            this.dialogueNow.add(defaultText);
//                        }
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
            if(isGym) {
                this.dialogueNow.add("You Lose!\nYour poke-coin willl be reduced");
            } 
            this.dialogueNow.add("end");
            this.canvasTextArea.nextText();
        }

        if(this.changePokemonBtn.clicked() && this.changePokemonBtn.isRendered()) {
            battleAltLoop.getSwitchCanvas().reInit();
            battleAltLoop.setNowState("switch"); 
            
        }

        if(this.useItemBtn.clicked() && this.useItemBtn.isRendered()) {
            battleAltLoop.getItemCanvas().reInit();
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

    public ArrayList<CanvasButton> getSkillButton() {
        return skillButton;
    }

   
    
    
}
