/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ChangePanelButton extends Button{
    MyFrame frame;
    ArrayList<ComponentData<MyPanel, ActionButton>> componentData;

    public ChangePanelButton(MyFrame frame, ArrayList<ComponentData<MyPanel, ActionButton>> componentData, ArrayList<ActionButton> button) {
        super(button);
        this.frame = frame;
        this.componentData = componentData;
    }
    
    public ChangePanelButton(MyFrame frame){
        super();
        this.frame = frame;
        componentData = new ArrayList<>();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        for(int i=0; i<componentData.size(); i++){
            if(componentData.get(i).getComponent() == e.getSource()){
                frame.changePanel(componentData.get(i).getData());
            }
        }
    }
    
    public void addComponentData(ComponentData<MyPanel, ActionButton> component){
        this.getButton().add(component.getComponent());
        componentData.add(component);
    }
}
