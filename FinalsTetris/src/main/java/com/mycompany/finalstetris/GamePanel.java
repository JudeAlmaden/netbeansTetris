package com.mycompany.finalstetris;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends javax.swing.JFrame {
    
    Menu m_menu;
    GameGrid mainGrid = new GameGrid();
    int fallingSpeed=1500;
    blockDisplay displayOne = new blockDisplay();
    blockDisplay displayTwo = new blockDisplay();
    blockDisplay displayThree = new blockDisplay();
    blockDisplay holdDisplay = new blockDisplay();
    Boolean isPlaying = false;
    Boolean gaming = true;
    
    public GamePanel(Menu menu) {
        m_menu = menu;
        initComponents();
        this.setTitle("Tetris");
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        this.gamePanel.setLayout(new GridLayout(1,1));
        this.gamePanel.add(mainGrid);
        this.requestFocus();
          
        updateNextBlocks();
       
        this.holdBlock.setLayout(new GridLayout(1,1));
        this.holdBlock.add(holdDisplay);
        this.nextBlock1.setLayout(new GridLayout(1,1));
        this.nextBlock1.add(displayOne);
        this.nextBlock2.setLayout(new GridLayout(1,1));
        this.nextBlock2.add(displayTwo);    
        this.nextBlock3.setLayout(new GridLayout(1,1));
        this.nextBlock3.add(displayThree);  
        
        btnStart.setText("Start");
          try{  
                if(!mainGrid.gameOver && isPlaying){
                    updateLabels();
                    updateNextBlocks();
                    mainGrid.blockIsPlaced();
                    mainGrid.playerBlockReduceHeight();
                    Thread.sleep(fallingSpeed);
                } 
                else if(mainGrid.gameOver && isPlaying){
                    isPlaying=false;
                    mainGrid.drawDefeat(mainGrid.getGraphics());
                    holdDisplay.myBlock = null;
                    btnStart.setText("Start");
                }     
                repaint();
            }
            catch(InterruptedException e){
        }  
          
        this.addKeyListener(new KeyAdapter() {
              
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                char inpt = e.getKeyChar();
                inpt = Character.toUpperCase(inpt);

                switch (inpt) {
                    case 'A' -> {
                        if(!mainGrid.gameOver && isPlaying){
                            mainGrid.moveLeft();
                        }
                    }
                    case 'D' -> {
                        if(!mainGrid.gameOver && isPlaying){
                            mainGrid.moveRight();
                        }
                    }
                    case 'W' -> {
                        if(!mainGrid.gameOver && isPlaying){
                            mainGrid.rotate();
                            
                        }
                    }
                    case ' ' -> {
                        if(!mainGrid.gameOver && isPlaying){
                            while(!mainGrid.blockIsPlaced()){
                               mainGrid.playerBlockReduceHeight();
                            }
                            updateLabels();
                            updateNextBlocks();
                            repaint();
                     
                        }                     
                    }
                    case 'H' ->{
                        if(mainGrid.hold==null && isPlaying){
                            mainGrid.hold = mainGrid.playerBlock;
                            mainGrid.playerBlock = mainGrid.nextBlock[0];
                            mainGrid.nextBlock[0] =  mainGrid.nextBlock[1];
                            mainGrid.nextBlock[1] =  mainGrid.nextBlock[2];
                            mainGrid.nextBlock[2] =  new TetrisBlock(10);
                            
                            holdDisplay.myBlock = mainGrid.hold;
                        }
                        else if (isPlaying){
                            TetrisBlock temp = mainGrid.playerBlock;
                            mainGrid.hold.x = mainGrid.playerBlock.x;
                            mainGrid.hold.y = mainGrid.playerBlock.y + (mainGrid.playerBlock.numRows - temp.numRows);
                            mainGrid.playerBlock =   mainGrid.hold;
                            mainGrid.hold = temp;
                           
                            holdDisplay.myBlock = mainGrid.hold;
                        }
                        repaint();
                        
                    }
                    case 'S' ->{
                        fallingSpeed=100; 
                    }
                }
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                char inpt = e.getKeyChar();
                inpt = Character.toUpperCase(inpt);

                if (inpt == 'S') {
                     fallingSpeed = 1075 - (mainGrid.level*75);
                }
            }
        });
         
    }
  
    public void run(){
          while (gaming){
            try{  
                if(!mainGrid.gameOver && isPlaying){
                    updateLabels();
                    updateNextBlocks();
                    mainGrid.blockIsPlaced();
                    mainGrid.playerBlockReduceHeight();
                    Thread.sleep(fallingSpeed);
                } 
                else if(mainGrid.gameOver && isPlaying){
                    isPlaying=false;
                    mainGrid.drawDefeat(mainGrid.getGraphics());
                    holdDisplay.myBlock = null;
                    btnStart.setText("Start");
                }     
                repaint();
            }
            catch(InterruptedException e){
            }   
        }
    }
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        btnStart = new javax.swing.JButton();
        btnRestart = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        gamePanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        holdBlock = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        nextBlock2 = new javax.swing.JPanel();
        nextBlock1 = new javax.swing.JPanel();
        nextBlock3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        labelScore = new javax.swing.JLabel();
        labelLevel = new javax.swing.JLabel();
        scorePlaceholder = new javax.swing.JLabel();
        levelPlaceholder = new javax.swing.JLabel();
        scorePlaceholder1 = new javax.swing.JLabel();
        scorePlaceholder3 = new javax.swing.JLabel();
        labelLinesCleared = new javax.swing.JLabel();
        scorePlaceholder2 = new javax.swing.JLabel();
        scorePlaceholder4 = new javax.swing.JLabel();
        labelPersonalBest = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 255));
        setPreferredSize(new java.awt.Dimension(1000, 750));

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnStart.setBackground(new java.awt.Color(0, 102, 0));
        btnStart.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        btnStart.setForeground(new java.awt.Color(255, 255, 255));
        btnStart.setText("Start");
        btnStart.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 0), 3, true));
        btnStart.setFocusable(false);
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        jPanel5.add(btnStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 490, 140, -1));

        btnRestart.setBackground(new java.awt.Color(204, 102, 0));
        btnRestart.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        btnRestart.setForeground(new java.awt.Color(255, 255, 255));
        btnRestart.setText("Restart");
        btnRestart.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 102, 0), 3, true));
        btnRestart.setFocusable(false);
        btnRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestartActionPerformed(evt);
            }
        });
        jPanel5.add(btnRestart, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 540, 140, -1));

        btnBack.setBackground(new java.awt.Color(153, 0, 0));
        btnBack.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        btnBack.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 51), 3, true));
        btnBack.setFocusable(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel5.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 590, 140, -1));

        gamePanel.setBackground(new java.awt.Color(0, 0, 102));
        gamePanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.blue, new java.awt.Color(0, 0, 153), java.awt.Color.blue, java.awt.Color.blue));

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 664, Short.MAX_VALUE)
        );

        jPanel5.add(gamePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, -1, 670));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 51, 255), new java.awt.Color(0, 0, 102), new java.awt.Color(0, 51, 204), new java.awt.Color(0, 51, 255)));
        jPanel1.setForeground(new java.awt.Color(0, 0, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(116, 227));

        holdBlock.setBackground(new java.awt.Color(13, 26, 72));
        holdBlock.setAlignmentX(0.0F);
        holdBlock.setAlignmentY(0.0F);
        holdBlock.setDoubleBuffered(false);
        holdBlock.setEnabled(false);
        holdBlock.setFocusable(false);
        holdBlock.setMaximumSize(new java.awt.Dimension(100, 100));
        holdBlock.setMinimumSize(new java.awt.Dimension(100, 100));

        javax.swing.GroupLayout holdBlockLayout = new javax.swing.GroupLayout(holdBlock);
        holdBlock.setLayout(holdBlockLayout);
        holdBlockLayout.setHorizontalGroup(
            holdBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        holdBlockLayout.setVerticalGroup(
            holdBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel1.setBackground(new java.awt.Color(13, 26, 72));
        jLabel1.setFont(new java.awt.Font("Impact", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(13, 26, 72));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Hold");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(holdBlock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(holdBlock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        holdBlock.getAccessibleContext().setAccessibleName("");

        jPanel5.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 130, 220));

        jPanel8.setBackground(new java.awt.Color(204, 204, 255));
        jPanel8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 102, 255), new java.awt.Color(255, 255, 255), new java.awt.Color(0, 51, 204), new java.awt.Color(0, 0, 255)));
        jPanel8.setForeground(new java.awt.Color(0, 0, 51));

        jPanel9.setBackground(new java.awt.Color(13, 26, 72));
        jPanel9.setMinimumSize(new java.awt.Dimension(120, 340));

        nextBlock2.setBackground(new java.awt.Color(13, 26, 72));
        nextBlock2.setForeground(new java.awt.Color(13, 26, 72));
        nextBlock2.setMaximumSize(new java.awt.Dimension(100, 100));
        nextBlock2.setMinimumSize(new java.awt.Dimension(100, 100));
        nextBlock2.setPreferredSize(new java.awt.Dimension(100, 100));

        javax.swing.GroupLayout nextBlock2Layout = new javax.swing.GroupLayout(nextBlock2);
        nextBlock2.setLayout(nextBlock2Layout);
        nextBlock2Layout.setHorizontalGroup(
            nextBlock2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        nextBlock2Layout.setVerticalGroup(
            nextBlock2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        nextBlock1.setBackground(new java.awt.Color(13, 26, 72));
        nextBlock1.setForeground(new java.awt.Color(13, 26, 72));
        nextBlock1.setMaximumSize(new java.awt.Dimension(100, 100));
        nextBlock1.setMinimumSize(new java.awt.Dimension(100, 100));
        nextBlock1.setPreferredSize(new java.awt.Dimension(100, 100));

        javax.swing.GroupLayout nextBlock1Layout = new javax.swing.GroupLayout(nextBlock1);
        nextBlock1.setLayout(nextBlock1Layout);
        nextBlock1Layout.setHorizontalGroup(
            nextBlock1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        nextBlock1Layout.setVerticalGroup(
            nextBlock1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        nextBlock3.setBackground(new java.awt.Color(13, 26, 72));
        nextBlock3.setMaximumSize(new java.awt.Dimension(100, 100));
        nextBlock3.setMinimumSize(new java.awt.Dimension(100, 100));
        nextBlock3.setPreferredSize(new java.awt.Dimension(100, 100));

        javax.swing.GroupLayout nextBlock3Layout = new javax.swing.GroupLayout(nextBlock3);
        nextBlock3.setLayout(nextBlock3Layout);
        nextBlock3Layout.setHorizontalGroup(
            nextBlock3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        nextBlock3Layout.setVerticalGroup(
            nextBlock3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nextBlock1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextBlock2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextBlock3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nextBlock1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextBlock2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextBlock3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel2.setBackground(new java.awt.Color(13, 26, 72));
        jLabel2.setFont(new java.awt.Font("Impact", 0, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(13, 26, 72));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Next");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, -1, 470));

        labelScore.setBackground(new java.awt.Color(204, 153, 0));
        labelScore.setFont(new java.awt.Font("Minecraft Ten", 0, 24)); // NOI18N
        labelScore.setForeground(new java.awt.Color(255, 153, 0));
        labelScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelScore.setText("0");
        jPanel5.add(labelScore, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 190, -1));

        labelLevel.setBackground(new java.awt.Color(204, 153, 0));
        labelLevel.setFont(new java.awt.Font("Minecraft Ten", 0, 24)); // NOI18N
        labelLevel.setForeground(new java.awt.Color(255, 153, 0));
        labelLevel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLevel.setText("0");
        jPanel5.add(labelLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, 190, -1));

        scorePlaceholder.setBackground(new java.awt.Color(204, 153, 0));
        scorePlaceholder.setFont(new java.awt.Font("Minecraft Ten", 0, 50)); // NOI18N
        scorePlaceholder.setForeground(new java.awt.Color(255, 153, 0));
        scorePlaceholder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scorePlaceholder.setText("Level");
        jPanel5.add(scorePlaceholder, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, 190, -1));

        levelPlaceholder.setBackground(new java.awt.Color(204, 153, 0));
        levelPlaceholder.setFont(new java.awt.Font("Minecraft Ten", 0, 50)); // NOI18N
        levelPlaceholder.setForeground(new java.awt.Color(255, 153, 0));
        levelPlaceholder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        levelPlaceholder.setText("Score");
        jPanel5.add(levelPlaceholder, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 190, -1));

        scorePlaceholder1.setBackground(new java.awt.Color(204, 153, 0));
        scorePlaceholder1.setFont(new java.awt.Font("Minecraft Ten", 0, 14)); // NOI18N
        scorePlaceholder1.setForeground(new java.awt.Color(255, 153, 0));
        scorePlaceholder1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scorePlaceholder1.setText("Cleared");
        jPanel5.add(scorePlaceholder1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 470, 190, -1));

        scorePlaceholder3.setBackground(new java.awt.Color(204, 153, 0));
        scorePlaceholder3.setFont(new java.awt.Font("Minecraft Ten", 0, 40)); // NOI18N
        scorePlaceholder3.setForeground(new java.awt.Color(255, 153, 0));
        scorePlaceholder3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scorePlaceholder3.setText("L i n e s");
        jPanel5.add(scorePlaceholder3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, 190, -1));

        labelLinesCleared.setBackground(new java.awt.Color(204, 153, 0));
        labelLinesCleared.setFont(new java.awt.Font("Minecraft Ten", 0, 24)); // NOI18N
        labelLinesCleared.setForeground(new java.awt.Color(255, 153, 0));
        labelLinesCleared.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLinesCleared.setText("0");
        jPanel5.add(labelLinesCleared, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 490, 190, -1));

        scorePlaceholder2.setBackground(new java.awt.Color(204, 153, 0));
        scorePlaceholder2.setFont(new java.awt.Font("Minecraft Ten", 0, 33)); // NOI18N
        scorePlaceholder2.setForeground(new java.awt.Color(255, 0, 153));
        scorePlaceholder2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scorePlaceholder2.setText("Best");
        jPanel5.add(scorePlaceholder2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 550, 180, -1));

        scorePlaceholder4.setBackground(new java.awt.Color(204, 153, 0));
        scorePlaceholder4.setFont(new java.awt.Font("Minecraft Ten", 0, 33)); // NOI18N
        scorePlaceholder4.setForeground(new java.awt.Color(255, 0, 153));
        scorePlaceholder4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scorePlaceholder4.setText("Personal ");
        jPanel5.add(scorePlaceholder4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 520, 180, -1));

        labelPersonalBest.setBackground(new java.awt.Color(204, 153, 0));
        labelPersonalBest.setFont(new java.awt.Font("Minecraft Ten", 0, 24)); // NOI18N
        labelPersonalBest.setForeground(new java.awt.Color(255, 0, 255));
        labelPersonalBest.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelPersonalBest.setText("0");
        jPanel5.add(labelPersonalBest, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 580, 190, -1));

        jLabel3.setText("1-5-2024");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 690, -1, -1));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Background.jpg"))); // NOI18N
        Background.setText("jLabel2");
        jPanel5.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 710));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void updateNextBlocks(){
        displayOne.myBlock = mainGrid.nextBlock[0];
        displayTwo.myBlock = mainGrid.nextBlock[1];
        displayThree.myBlock = mainGrid.nextBlock[2];
    }
    public void updateLabels(){
        labelScore.setText(Integer.toString(mainGrid.score));
        labelLevel.setText(Integer.toString(mainGrid.level));
        labelLinesCleared.setText(Integer.toString(mainGrid.linesCleared));
    }
  
    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        // TODO add your handling code here:
          
  
        if(mainGrid.gameOver){
            mainGrid.startGame();
            mainGrid.gameOver=false;
            isPlaying=true;
            holdDisplay.myBlock = mainGrid.hold;            
            btnStart.setText("Pause");
        } 
        else if(!isPlaying){
            btnStart.setText("Pause");
            isPlaying=true;
            
        }
        else{
            btnStart.setText("Continue");
            isPlaying=false;
        }
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.setVisible(false);
        // Dispose of the current window
        m_menu.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestartActionPerformed
        // TODO add your handling code here:
       isPlaying = true;
       mainGrid.gameOver=true;   
       btnStart.setText("Play");
       //mainGrid.startGame();
    }//GEN-LAST:event_btnRestartActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnRestart;
    private javax.swing.JButton btnStart;
    private javax.swing.JPanel gamePanel;
    private javax.swing.JPanel holdBlock;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel labelLevel;
    private javax.swing.JLabel labelLinesCleared;
    private javax.swing.JLabel labelPersonalBest;
    private javax.swing.JLabel labelScore;
    private javax.swing.JLabel levelPlaceholder;
    private javax.swing.JPanel nextBlock1;
    private javax.swing.JPanel nextBlock2;
    private javax.swing.JPanel nextBlock3;
    private javax.swing.JLabel scorePlaceholder;
    private javax.swing.JLabel scorePlaceholder1;
    private javax.swing.JLabel scorePlaceholder2;
    private javax.swing.JLabel scorePlaceholder3;
    private javax.swing.JLabel scorePlaceholder4;
    // End of variables declaration//GEN-END:variables

}
