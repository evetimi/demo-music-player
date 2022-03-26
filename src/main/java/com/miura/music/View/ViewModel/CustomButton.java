/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miura.music.View.ViewModel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

/**
 *
 * @author Admin
 */
public class CustomButton extends JButton {
    
    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    private int radius;
    private int borderThickness;
    private boolean circle;
    
    public CustomButton() {
        this.setColor(Color.WHITE);
        this.borderColor = Color.BLACK;
        this.colorOver = Color.CYAN;
        this.colorClick = Color.BLUE;
        this.radius = 0;
        this.borderThickness = 1;
        this.circle = false;
        
        this.setContentAreaFilled(false);
        
        this.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(colorOver);
                over = true;
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(color);
                over = false;
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(colorClick);
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                if (over) {
                    setBackground(colorOver);
                } else {
                    setBackground(color);
                }
            }
            
        });
    }

    public boolean isOver() {
        return this.over;
    }

    public boolean getOver() {
        return this.over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    public Color getColorOver() {
        return this.colorOver;
    }

    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    public Color getColorClick() {
        return this.colorClick;
    }

    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    public Color getBorderColor() {
        return this.borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getRadius() {
        return this.radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    public int getBorderThickness() {
        return this.borderThickness;
    }

    public void setBorderThickness(int borderThickness) {
        this.borderThickness = borderThickness;
    }
    
    public boolean isCircle() {
        return this.circle;
    }

    public boolean getCircle() {
        return this.circle;
    }
    
    public void setCircle(boolean circle) {
        this.circle = circle;
    }

    @Override
    public String toString() {
        return "{" +
            " over='" + isOver() + "'" +
            ", color='" + getColor() + "'" +
            ", colorOver='" + getColorOver() + "'" +
            ", colorClick='" + getColorClick() + "'" +
            ", borderColor='" + getBorderColor() + "'" +
            ", radius='" + getRadius() + "'" +
            "}";
    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
//        //Paint border
//        g2d.setColor(this.borderColor);
//        g2d.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), this.radius, this.radius);
//        
//        //Paint background
//        g2d.setColor(this.getBackground());
//        g2d.fillRoundRect(this.borderThickness, this.borderThickness, this.getWidth()-this.borderThickness*2, this.getHeight()-this.borderThickness*2, this.radius, this.radius);


        if (circle) paintCircle(g2d); else paintNonCircle(g2d);
        

        super.paintComponent(g);
    }
    
    
    private void paintNonCircle(Graphics2D g2d) {
        //Paint border
        g2d.setColor(this.borderColor);
        g2d.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), this.radius, this.radius);
        
        //Paint background
        g2d.setColor(this.getBackground());
        g2d.fillRoundRect(this.getBorderThickness(), this.getBorderThickness(), this.getWidth()-this.getBorderThickness()*2, this.getHeight()-this.getBorderThickness()*2, this.radius, this.radius);
    }
    
    private void paintCircle(Graphics2D g2d) {
        //Paint border
        g2d.setColor(this.borderColor);
        g2d.fillOval(0, 0, this.getWidth(), this.getHeight());
        
        //Paint background
        g2d.setColor(this.getBackground());
        g2d.fillOval(this.getBorderThickness(), this.getBorderThickness(), this.getWidth()-this.getBorderThickness()*2, this.getHeight()-this.getBorderThickness()*2);
    }

}
