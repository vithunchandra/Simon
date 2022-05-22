/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.LayoutManager;

/**
 *
 * @author asus
 */
public class ButtonWithData<Data> extends ActionButton{
    private Data data;

    public ButtonWithData(Data data, Dimension size, LayoutManager layout, Image imageBackground) {
        super(size, layout, imageBackground);
        this.data = data;
    }

    public ButtonWithData(Data data, Dimension size, int x, int y, LayoutManager layout, Image imageBackground) {
        super(size, x, y, layout, imageBackground);
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
