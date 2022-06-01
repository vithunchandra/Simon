package Item;


import Util.ImageLoader;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class Item {
    private String name, description;
    private int price;
    private Image itemImage;

    public Item(String name, String description, int price, String imagePath) {
        this.name = name;
        this.description = description;
        this.price = price;
        try {
            this.itemImage = ImageLoader.loadImage(imagePath);
        } catch (IOException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public Image getItemImage() {
        return itemImage;
    }
}
