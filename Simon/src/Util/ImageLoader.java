/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author LVOILA
 */
public class ImageLoader {
    public static Image loadImage(String path) throws IOException {
        BufferedImage image = ImageIO.read(new File(path));
        return image;
    }
    
//    public static void loadImage(String path,int width,int height) {
//        
//    }
}
