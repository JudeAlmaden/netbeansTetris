/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalstetris;

import javax.swing.*;
import java.awt.*;


public class blockDisplay extends JPanel {

    public TetrisBlock myBlock;
    private int containerWidth;
    private int containerHeight;

    blockDisplay() {
        this.setBackground(new Color(13,26,72));
        this.setBounds(0, 0, 100, 100);
        //this.setBackground(new Color(255,70,255));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        initializeVariables();
  
        if(myBlock!=null){
            drawBlock(g);
        }
    }

    private void initializeVariables() {
        containerWidth = this.getBounds().width;
        containerHeight = this.getBounds().height;
        
       // containerWidth = 100;
       // containerHeight=100;
    }

    private void drawBlock(Graphics g) {
        g.setColor(myBlock.color);

        //Get sozes
        int blockWidth=containerWidth/5;
        int blockHeight=containerHeight/5;
        //find center of container, then fint the top left by subtracting one halve of the block
        int x=(containerWidth/2) - (myBlock.numColumns*blockWidth)/2;
        int y=(containerHeight/2) - (myBlock.numRows*blockHeight)/2;
        
        //check for solid blocks to draw
        for (int col = 0; col < myBlock.numColumns; col++) {
            for (int row = 0; row < myBlock.numRows; row++) {
                if (myBlock.isSolid[row][col]) {
                    g.setColor(myBlock.color);

                    g.fillRect(x + col*blockWidth ,y +row*blockHeight,blockWidth -2,blockHeight-2);
                }
            }
        }
    } 
}
