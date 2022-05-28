/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import Util.MyFrame;
import BattleCanvas.PokemonBarAlt;
import BattleCanvas.CanvasMouseListener;
import BattleCanvas.CanvasButton;
import BattleCanvas.CanvasTextArea;
import Pokemon.ImagePath;
import Util.ImageLoader;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author LVOILA
 */

public class BattleMenuAlt {
    class BattleCanvas extends Canvas implements Runnable{
        private Boolean running;
        private int fps;

        private MyFrame frame;
        private JPanel panel;
        private CanvasMouseListener mouse;
        
        
        private BattleAltLoop battleAltLoop;
        public BattleCanvas(int fps,MyFrame frame,JPanel panel,boolean inGym) throws IOException {
           this.fps = fps;
           this.running = false;
           this.setBounds(0, 0, MyFrame.DEFAULT_WIDTH, MyFrame.DEFAULT_HEIGHT);
           
           this.setFocusable(true);
           this.setVisible(true);
           
           this.mouse = new CanvasMouseListener();
           this.addMouseListener(this.mouse);
           this.addMouseMotionListener(this.mouse);
           
           this.battleAltLoop = new BattleAltLoop(mouse,inGym);
           
           
           this.frame = frame;
           this.panel = panel;
        }

        public void start() {
            if(!this.running) {
                running = true;
                new Thread(this).start();

            }
        }

        private void stop() {
            running = false;
        }
        
        private void draw(Graphics g) throws IOException {
            battleAltLoop.draw(g);
            
        }

        private void drawLoop() throws IOException {
            BufferStrategy bs = getBufferStrategy();
            if(bs == null) {
                createBufferStrategy(2);
                return;
            }

            Graphics g = bs.getDrawGraphics();
            g.clearRect(0, 0, MyFrame.DEFAULT_WIDTH, MyFrame.DEFAULT_HEIGHT);

            draw(g);

            g.dispose();
            bs.show();
        }
        
        private void logicLoop(long diff) {
            
            battleAltLoop.logicLoop(diff);
            if(!battleAltLoop.isBattling()) {
                this.stop();
            }
            
            if(mouse.isRelease()) {
                mouse.disableRelease();
            }
        }

        @Override
        public void run() {
            long lastTick = System.currentTimeMillis();
            double changeFrameTime = 1000/this.fps;

            double timeAccumulator = 0;
            while(running) {
                long current = System.currentTimeMillis();
                long diff = current - lastTick;
                lastTick = current;

                timeAccumulator += diff;
                
                logicLoop(diff);

                if( (timeAccumulator / changeFrameTime) >= 1) {
                    try {
                        drawLoop();
                        
                    } catch (IOException ex) {
                        Logger.getLogger(BattleCanvas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    timeAccumulator = 0;
                }
                
            }
            
            this.setFocusable(false);
            this.frame.changePanel(panel);
        }

        public Boolean isRunning() {
            return this.running;
        }
    }
    
    private JFrame frame;
    private Canvas canvas;
    
    public BattleMenuAlt(MyFrame frame,JPanel panel,boolean inGym) throws InterruptedException {
        this.frame = frame;
        
        BattleCanvas battleCanvas = null;
        try {
            battleCanvas = new BattleCanvas(60,frame,panel,inGym);
        } catch (IOException ex) {
            Logger.getLogger(BattleMenuAlt.class.getName()).log(Level.SEVERE, null, ex);
        }

        frame.removePanel();
        frame.add(battleCanvas);
        battleCanvas.start();
    }
}

