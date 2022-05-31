/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.Button;

import Util.Component.ComponentData;
import Util.MyFrame;
import Util.Container.MyPanel;
import Util.Component.*;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ChangePanelButton extends ButtonAction{
    MyFrame frame;
    MyPanel newPanel;
    MyPanel oldPanel;

    public ChangePanelButton(MyFrame frame, ActionButton button) {
        super(button);
        this.frame = frame;
        for(int i=0; i<frame.getContentPane().getComponentCount(); i++){
            if(frame.getContentPane().getComponent(i) instanceof MyPanel){
                oldPanel = (MyPanel) frame.getContentPane().getComponent(i);
                break;
            }
        }
    }
    
    public ChangePanelButton(MyFrame frame, MyPanel oldPanel, ActionButton button){
        super(button);
        this.frame = frame;
        this.oldPanel = oldPanel;
    }

    @Override
    public void clicked() {
        frame.changePanel(oldPanel);
    }

}
