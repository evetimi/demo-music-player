package com.miura.music.View.ViewModel;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * TODO: create ActionListener and Override paintComponent(Graphics g)
 * @author Admin
 */
public class MusicTitleLabel extends javax.swing.JLabel implements ActionListener {
    
    private int timeInMilliseconds;
    private Timer timer;
    private int xPos;
    private int yPos;
    private int resetAfterPixelMoved;
    private int pixelMoved;
    private int horizontalMoved;
    private int verticalMoved;
    
    private int textWidth;
    private int textHeight;
    
    private AffineTransform reset;
    
    public MusicTitleLabel() {
        super();
        this.timeInMilliseconds = 10;
        this.timer = new Timer(timeInMilliseconds, this);
        this.xPos = 0;
        this.yPos = 0;
        this.pixelMoved = 0;
    }

    public MusicTitleLabel(int timeInMilliseconds, Timer timer, int x, int y) {
        super();
        this.timeInMilliseconds = timeInMilliseconds;
        this.timer = timer;
        this.xPos = x;
        this.yPos = y;
        this.pixelMoved = 0;
    }

    public int getTimeInMilliseconds() {
        return this.timeInMilliseconds;
    }

    public void setTimeInMilliseconds(int timeInMilliseconds) {
        this.timeInMilliseconds = timeInMilliseconds;
    }

    public Timer getTimer() {
        return this.timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getXPos() {
        return this.xPos;
    }

    public void setXPos(int x) {
        this.xPos = x;
    }

    public int getYPos() {
        return this.yPos;
    }

    public void setYPos(int y) {
        this.yPos = y;
    }
    
    
    public void setText(String text) {
        if (text.equals("")) {
            this.textWidth = 0;
            this.textHeight = 0;
        } else if (text != null) {
            FontMetrics metrics = getFontMetrics(getFont());
            this.textWidth = (int)metrics.getStringBounds(getText(), null).getWidth();
            this.textHeight = (int)metrics.getStringBounds(getText(), null).getHeight();
        }
        super.setText(text);
    }
    
    
    /**
     * Start the timer and moved the label.
     * The label will reset after resetAfterPixelMoved pixel.
     * The label will move to the left is pixelMoved is negative, otherwise if positive.
     * @param resetAfterPixelMoved
     * @param horizontalMoved 
     */
    public void startTimer(int resetAfterPixelMoved, int horizontalMoved, int verticalMoved) {
        this.resetAfterPixelMoved = resetAfterPixelMoved;
        this.horizontalMoved = horizontalMoved;
        this.verticalMoved = verticalMoved;
        timer.start();
    }
    
    
    /**
     * Stop the timer whenever you need. After stopping, the component will be put in the original place.
     */
    public void stopTimer() {
        if (timer.isRunning()) timer.stop();
        this.xPos=0;
        this.yPos=0;
    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        //if (x==0 && y==0) reset = g2d.getTransform();
        
        g2d.translate(this.xPos, this.yPos);
        
        super.paintComponent(g2d);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.pixelMoved>=this.resetAfterPixelMoved) {
            this.xPos = (this.horizontalMoved!=0) ? this.getWidth() : 0;
            this.yPos = (this.verticalMoved!=0) ? this.getHeight() : 0;
            this.pixelMoved = 0;
        } else {
            this.xPos += this.horizontalMoved;
            this.yPos += this.verticalMoved;
            this.pixelMoved += Math.abs(this.horizontalMoved) + Math.abs(this.verticalMoved);
        }
        
        repaint();
    }

}
