/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.Button;

import Item.Item;
import Util.Component.*;
import Util.Text.*;
import javax.swing.JOptionPane;
import simon.Player;

/**
 *
 * @author asus
 */
public class BuyAction extends Buttons{
    Integer amount, total;
    Item itemData;
    DrawText totalText, amountText;
    ActionComponent buyContainer;

    public BuyAction() {
        super();
        amount = null; total = null;
        itemData = null;
        totalText = null; amountText = null;
        buyContainer = null;
    }

    @Override
    public void clicked(ActionButton button) {
        if(button.getName().equals("add")){
            if(amount < 99){
                amount++;
            }
        }else if(button.getName().equals("substract")){
            if(amount > 1){
                amount--;
            }
        }else if(button.getName().equals("buy")){
            Integer totalCost = amount * itemData.getPrice();
            if(Player.pokeCoin >= totalCost){
                if(itemData.getName().equals("Potion")){
                    Player.potion += amount;
                }else if(itemData.getName().equals("Super Potion")){
                    Player.superPotion += amount;
                }else if(itemData.getName().equals("Full Restore")){
                    Player.fullRestore += amount;
                }else if(itemData.getName().equals("Poke Ball")){
                    Player.pokeBall += amount;
                }else if(itemData.getName().equals("Great Ball")){
                    Player.greatBall += amount;
                }else if(itemData.getName().equals("Ultra Ball")){
                    Player.ultraBall += amount;
                }
                amount = 1;
            }else{
                JOptionPane.showMessageDialog(null, "Poke Coin is sufficient");
            }
        }
        
        total = amount * itemData.getPrice();
        totalText.setText("Total : " + total);
        amountText.setText(amount.toString());
        
        System.out.println("Potion : " + Player.potion);
        System.out.println("Super Potion : " + Player.superPotion);
        System.out.println("Full Restore : " + Player.fullRestore);
        System.out.println("Poke Ball : " + Player.pokeBall);
        System.out.println("Great Ball : " + Player.greatBall);
        System.out.println("Ultra Ball : " + Player.ultraBall);
        System.out.println("------------------");
        
        buyContainer.revalidate();
        buyContainer.repaint();
    }
    
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
        amountText.setText(amount.toString());
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
        totalText.setText("Total : " + total);
    }

    public Item getItemData() {
        return itemData;
    }

    public void setItemData(Item itemData) {
        this.itemData = itemData;
    }

    public void setTotalText(DrawText totalText) {
        this.totalText = totalText;
    }

    public void setAmountText(DrawText amountText) {
        this.amountText = amountText;
    }

    public void setBuyContainer(ActionComponent buyContainer) {
        this.buyContainer = buyContainer;
    }
}
