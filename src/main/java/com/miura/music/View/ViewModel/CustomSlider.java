package com.miura.music.View.ViewModel;

import com.miura.music.View.ViewModel.sliderUI.JSliderUI;
import java.awt.Color;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Waffle
 */
public class CustomSlider extends javax.swing.JSlider {

    public CustomSlider() {
        setOpaque(false);
        setBackground(new Color(180, 180, 180));
        setForeground(new Color(69, 124, 235));
        setUI(new JSliderUI(this));
    }
    
    
}
