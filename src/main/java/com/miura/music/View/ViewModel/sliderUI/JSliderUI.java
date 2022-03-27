/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miura.music.View.ViewModel.sliderUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JSlider;

import javax.swing.plaf.basic.BasicSliderUI;


/**
 *
 * @author Waffle
 */
public class JSliderUI extends BasicSliderUI {
    
    public JSliderUI(JSlider slider) {
        super(slider);
    }
    
    
    @Override
    public void paintFocus(Graphics g) {
        
    }
    
    
    @Override
    protected Dimension getThumbSize() {
        return new Dimension(14, 14);
    }
    
    
    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);        
        g2d.setColor(slider.getForeground());
        g2d.fillOval(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height);
    }
    
}
