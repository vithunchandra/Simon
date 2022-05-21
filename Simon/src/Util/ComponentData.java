/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import javax.swing.*;

/**
 *
 * @author asus
 */
public class ComponentData<Data, Comp extends JComponent> {
    private Data data;
    private Comp component;

    public ComponentData(Data data, Comp component) {
        this.data = data;
        this.component = component;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Comp getComponent() {
        return component;
    }

    public void setComponent(Comp component) {
        this.component = component;
    }
}
