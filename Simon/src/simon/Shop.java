/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import Util.*;
import Util.Button.*;
import Util.Component.*;
import Util.Container.*;
import Util.Text.*;
import Item.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Shop {
    MyFrame frame;
    MyPanel oldPanel, shopPanel;
    
    public Shop(MyFrame frame, MyPanel oldPanel){
        this.frame = frame;
        this.oldPanel = oldPanel;
        
        Image panelBackground = null;
        try {
            panelBackground = ImageLoader.loadImage("src\\Material\\Image\\Shop.jpg");
        } catch (IOException ex) {
            Logger.getLogger(Shop.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        shopPanel = new MyPanel(panelBackground, new GridBagLayout());
        
        Font font = new Font(Font.SERIF, Font.BOLD, 30);
        
        DrawText buttonName = new DrawText("Back", font);
        ActionButton backButton = new ActionButton(new Dimension(buttonName.getWidth() + 30, buttonName.getHeight() + 15), new FlowLayout(), null);
        backButton.add(buttonName);
        
        new ChangePanelButton(frame, oldPanel, backButton);
        
        SetGBC.setGbc(gbc, 0, 0, 0, 0, GridBagConstraints.NORTHWEST);
        shopPanel.add(backButton, gbc);
        
        SetGBC.setGbc(gbc, 1, 0, 0, 0, GridBagConstraints.NORTHEAST);
        shopPanel.add(shopTitle(), gbc);
        
        gbc.weighty = 2.0;
        SetGBC.setGbc(gbc, 0, 1, 0, 0, GridBagConstraints.NORTHWEST);
        shopPanel.add(money(), gbc);
        
        gbc.weighty = 1.0;
        SetGBC.setGbc(gbc, 1, 1, 0, 0, GridBagConstraints.EAST);
        shopPanel.add(itemList(), gbc);
        
        gbc.weighty = 0.1;
        gbc.gridheight = 2;
        SetGBC.setGbc(gbc, 0, 1, 0, 0, GridBagConstraints.WEST);
        shopPanel.add(description(), gbc);
        
        frame.changePanel(shopPanel);
    }
    
    public ActionComponent description(){
        ActionComponent container = new ActionComponent(new Dimension(400, 400), null, null);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        
        DrawText itemName = new DrawText("HP potion", new Font(Font.SANS_SERIF, Font.BOLD, 25));
        int height = itemName.getHeight();
        DrawImage itemImage = new DrawImage("src\\Material\\Image\\item.png", new Dimension(height, height));
        ActionComponent itemNameContainer = new ActionComponent(new Dimension((itemName.getWidth() + height + 10), height), new FlowLayout(FlowLayout.CENTER, 10, 0), null);
        
        itemNameContainer.add(itemImage);
        itemNameContainer.add(itemName);
        itemNameContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        container.add(itemNameContainer);
        
        return container;
    }
    
    public ActionComponent itemList(){
        ActionComponent container = new ActionComponent(new Dimension(400, 500), null, null);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        
        DrawText itemTitle = new DrawText("Item", new Font(Font.SANS_SERIF, Font.BOLD, 40));
        int height = itemTitle.getHeight();
        DrawImage leftIcon = new DrawImage("src\\Material\\Image\\item.png", new Dimension(height, height));
        DrawImage rightIcon = new DrawImage("src\\Material\\Image\\item.png", new Dimension(height, height));
        ActionComponent titleContainer = new ActionComponent(new Dimension((itemTitle.getWidth() + (2*height) + 30), height), new FlowLayout(FlowLayout.CENTER, 10, 0), null);
        titleContainer.add(leftIcon);
        titleContainer.add(itemTitle);
        titleContainer.add(rightIcon);
        titleContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        container.add(titleContainer);
        container.add(Box.createVerticalBox());
        
        return container;
    }
    
    public ActionComponent money(){
        DrawText moneyDetail = new DrawText(Integer.toString(Player.pokeCoin), new Font(Font.SANS_SERIF, Font.BOLD, 50));
        int height = moneyDetail.getHeight();
        DrawImage pokeCoinImage = new DrawImage("src\\Material\\Image\\pokeCoin.png", new Dimension(height, height));
        ActionComponent moneyDetailContainer = new ActionComponent(new Dimension((moneyDetail.getWidth() + height + 20), moneyDetail.getHeight()), new FlowLayout(FlowLayout.CENTER, 10, 0), null);
        
        moneyDetailContainer.add(pokeCoinImage);
        moneyDetailContainer.add(moneyDetail);
//        moneyDetailContainer.setBackground(new Color(255, 255, 255, 0));
        
        return moneyDetailContainer;
    }
    
    public ActionComponent shopTitle(){
        DrawText title = new DrawText("Shop", new Font(Font.SERIF, Font.BOLD, 50));
        ActionComponent titleContainer = new ActionComponent(new Dimension(300, title.getHeight()), new FlowLayout(FlowLayout.CENTER, 0, 0), null);
        titleContainer.add(title);
        
        return titleContainer;
    }
    
}
