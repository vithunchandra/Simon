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
    Integer jumlah = 1;
    ShopAction shopAction;
    BuyAction buyAction;
    
    public Shop(MyFrame frame, MyPanel oldPanel){
        this.frame = frame;
        this.oldPanel = oldPanel;
        shopAction = new ShopAction();
        buyAction = new BuyAction();
        
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
        
        gbc.weighty = 0.1;
        SetGBC.setGbc(gbc, 1, 1, 0, 0, GridBagConstraints.NORTHEAST);
        shopPanel.add(money(), gbc);
        
        gbc.weighty = 1.0;
        SetGBC.setGbc(gbc, 0, 1, 0, 0, GridBagConstraints.WEST);
        shopPanel.add(itemList(), gbc);
        
        gbc.weighty = 0.1;
        gbc.gridheight = 1;
        SetGBC.setGbc(gbc, 1, 1, 0, 0, GridBagConstraints.EAST);
        shopPanel.add(description(), gbc);
        
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, 50, 0);
        SetGBC.setGbc(gbc, 1, 1, 0, 0, GridBagConstraints.SOUTHEAST);
        shopPanel.add(buyButton(), gbc);
        
        shopAction.setBuyAction(buyAction);
        
        frame.changePanel(shopPanel);
    }
    
    public ActionComponent buyButton(){
        ActionComponent buyContainer = new ActionComponent(new Dimension(400, 100), null, null);
        buyContainer.setLayout(new BoxLayout(buyContainer, BoxLayout.Y_AXIS));
        buyContainer.setBackground(new Color(0, 0, 0, 0));
        
        ActionComponent buttonContainer = new ActionComponent(new Dimension(400, 50), new FlowLayout(FlowLayout.RIGHT, 10, 0), null);
        buttonContainer.setBackground(new Color(0, 0, 0, 0));
        
        DrawText addIcon = new DrawText("+", GetSizedFont.getSizedFont("+", Font.SERIF, Font.BOLD, new Dimension(40, 40)));
        DrawText substractIcon = new DrawText("-", GetSizedFont.getSizedFont("-", Font.SERIF, Font.BOLD, new Dimension(40, 40)));
        DrawText buyText = new DrawText("Buy", GetSizedFont.getSizedFont("Buy", Font.SERIF, Font.BOLD, new Dimension(100, 45)));
        int width = addIcon.getWidth();
        if(substractIcon.getWidth() > width){
            width = substractIcon.getWidth();
        }
        ActionButton addButton = new ActionButton(new Dimension(width + 30, addIcon.getHeight() + 15), new FlowLayout(), null);
        ActionButton substractButton = new ActionButton(new Dimension(width + 30, substractIcon.getHeight() + 15), new FlowLayout(), null);
        ActionButton buyButton = new ActionButton(new Dimension(buyText.getWidth() + 30, buyText.getHeight() + 15), new FlowLayout(FlowLayout.CENTER, 0, 0), null);
        
        addButton.add(addIcon);
        substractButton.add(substractIcon);
        buyButton.add(buyText);
        
        addButton.setName("add");
        substractButton.setName("substract");
        buyButton.setName("buy");
        
        buyAction.AddButton(addButton);
        buyAction.AddButton(substractButton);
        buyAction.AddButton(buyButton);
        
        String amount = Integer.toString(jumlah);
        DrawText amountText = new DrawText(amount, GetSizedFont.getSizedFont(amount, Font.SERIF, Font.PLAIN, new Dimension(90, 40)));
        
        buttonContainer.add(substractButton);
        buttonContainer.add(amountText);
        buttonContainer.add(addButton);
        buttonContainer.add(buyButton);
        
        ActionComponent totalContainer = new ActionComponent(new Dimension(400, 40), new FlowLayout(FlowLayout.RIGHT, 10, 0), null);
        totalContainer.setBackground(new Color(0, 0, 0, 0));
        String total = "Total : 10000";
        DrawText totalText = new DrawText(total, GetSizedFont.getSizedFont(total, Font.SANS_SERIF, Font.BOLD, new Dimension(200, 40)));
        totalText.setAlignmentX(Component.CENTER_ALIGNMENT);
        totalContainer.add(totalText);
        
        buyContainer.add(totalContainer);
        buyContainer.add(buttonContainer);
        
        shopAction.addBuyContainer(buyContainer);
        buyAction.setBuyContainer(buyContainer);
        buyAction.setAmountText(amountText);
        buyAction.setTotalText(totalText);

        buyContainer.setVisible(false);
        
        return buyContainer;
    }
    
    public ActionComponent description(){
        ActionComponent container = new ActionComponent(new Dimension(400, 300), null, null);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        shopAction.addDetailPanel(container);
//        
//        DrawText itemName = new DrawText("HP potion", new Font(Font.SANS_SERIF, Font.BOLD, 25));
//        int height = itemName.getHeight();
//        DrawImage itemImage = new DrawImage("src\\Material\\Image\\item.png", new Dimension(height, height));
//        ActionComponent itemNameContainer = new ActionComponent(new Dimension((itemName.getWidth() + height + 10), height), new FlowLayout(FlowLayout.CENTER, 10, 0), null);
//        
//        itemNameContainer.add(itemImage);
//        itemNameContainer.add(itemName);
//        itemNameContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
//        
//        container.add(itemNameContainer);
        
        container.setVisible(false);
        
        return container;
    }
    
    public ActionComponent itemList(){
        ActionComponent container = new ActionComponent(new Dimension(400, 550), null, null);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        
        DrawText itemTitle = new DrawText("Item", new Font(Font.SANS_SERIF, Font.BOLD, 40));
        int height = itemTitle.getHeight();
        DrawImage leftIcon = new DrawImage("src\\Material\\Image\\item.png", new Dimension(height, height));
        DrawImage rightIcon = new DrawImage("src\\Material\\Image\\item.png", new Dimension(height, height));
        ActionComponent titleContainer = new ActionComponent(new Dimension((itemTitle.getWidth() + (2*height) + 30), height), new FlowLayout(FlowLayout.CENTER, 10, 0), null);
        titleContainer.add(leftIcon);
        titleContainer.add(itemTitle);
        titleContainer.add(rightIcon);
        
        container.add(titleContainer);

        for(int i=0; i<Player.itemList.size(); i++){
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.weightx = 0.1; gbc.weighty = 0.1;
            
            Item itemData = Player.itemList.get(i);
            
            ActionComponent item = new ActionComponent(new Dimension(400, 75), new GridBagLayout(), null);
            item.setBackground(new Color(0, 0, 0, 100));
            item.setMaximumSize(new Dimension(400, 70));
            DrawText itemName = new DrawText(itemData.getName(), GetSizedFont.getSizedFont(itemData.getName(), Font.SANS_SERIF, Font.PLAIN, new Dimension(200, 30)));
            DrawImage itemImage = new DrawImage(itemData.getItemImage(), new Dimension(60, 60));
            DrawText itemPrice = new DrawText(Integer.toString(itemData.getPrice()), GetSizedFont.getSizedFont(Integer.toString(itemData.getPrice()), Font.SANS_SERIF, Font.BOLD, new Dimension(200, 30)));
            DrawImage pokeCoin = new DrawImage("src\\Material\\Image\\pokeCoin.png", new Dimension(30, 30));
            ActionComponent itemPriceContainer = new ActionComponent(new Dimension(200, 30), new FlowLayout(FlowLayout.LEFT, 10, 0), null);
            itemPriceContainer.setBackground(new Color(0, 0, 0, 0));
            itemPriceContainer.add(pokeCoin);
            itemPriceContainer.add(itemPrice);
            
      
            gbc.gridheight = 2;
            SetGBC.setGbc(gbc, 0, 0, 0, 0, GridBagConstraints.CENTER);
            item.add(itemImage, gbc);
            
            gbc.gridheight = 1;
            gbc.gridwidth = 2;
            gbc.weightx = 1;
            SetGBC.setGbc(gbc, 1, 0, 0, 0, GridBagConstraints.WEST);
            item.add(itemName, gbc);
            
            gbc.gridwidth = 1;
            gbc.weightx = 1;
            SetGBC.setGbc(gbc, 1, 1, 0, 0, GridBagConstraints.WEST);
            item.add(itemPriceContainer, gbc);
            
            shopAction.addComponent(new ComponentData<Item, ActionComponent>(Player.itemList.get(i), item));
            
            container.add(item);
            container.add(Box.createVerticalGlue());
        }
        
        return container;
    }
    
    public ActionComponent money(){
        DrawText moneyDetail = new DrawText(Integer.toString(Player.pokeCoin), new Font(Font.SANS_SERIF, Font.BOLD, 50));
        int height = moneyDetail.getHeight();
        DrawImage pokeCoinImage = new DrawImage("src\\Material\\Image\\pokeCoin.png", new Dimension(height, height));
        ActionComponent moneyDetailContainer = new ActionComponent(new Dimension((moneyDetail.getWidth() + height + 20), moneyDetail.getHeight()), new FlowLayout(FlowLayout.CENTER, 10, 0), null);
        
        buyAction.setCurrentMoney(moneyDetail);
        
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
