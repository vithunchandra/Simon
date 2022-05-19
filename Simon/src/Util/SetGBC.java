/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.awt.*;

/**
 *
 * @author asus
 */
public class SetGBC {
    public SetGBC(){
        
    }
    public static GridBagConstraints setGbc(GridBagConstraints gbc, int gridx, int gridy, int ipadx, int ipady, int anchor){
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.ipadx = ipadx;
        gbc.ipady = ipady;
        gbc.anchor = anchor;
        
        return gbc;
    }
}
