package com.mycompany.finalstetris;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameGrid extends javax.swing.JPanel {

    private int cellHeight;
    private int cellWidth;
    public  int gridHeight;
    public  int gridWidth;
    public int linesCleared;
    public int score;
    public int level;
 
    private final int offsetx = 0;
    private final int offsety = 0;
    private int modifierX;
    private int modifierY;
    int numRows=20;
    int numColumns=10;
    private final int paddingTopAndBottom=20;
    private final int paddingLeftAndRight=20;
    private final int borderThickness = 5;
    private int topBorder;
    
    public boolean blockIsPlaced = false;
    public boolean gameOver = true;
    public boolean defeatScreenIsPlayed = false;
    
    private final Color[][] gridColors = new Color[numRows][numColumns];
    
    public TetrisBlock playerBlock;
    public TetrisBlock hold;
    public TetrisBlock[] nextBlock = new TetrisBlock[3];
    
    public int prevX;
    public Boolean blockPredictor = false;
    
    public GameGrid() {
        
        initComponents();
        this.setBackground(new Color(13, 26, 72));
        this.requestFocus();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 642, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    void reset(){
        //Set all cells to color grey
        for (int i =0; i<20; i++) {
            for (int j = 0; j < 10; j++) {
                gridColors[i][j] = Color.darkGray;
            }
        }
            playerBlock = new TetrisBlock(numColumns);
            nextBlock[0] = new TetrisBlock(numColumns);
            nextBlock[1] = new TetrisBlock(numColumns);
            nextBlock[2]= new TetrisBlock(numColumns);
            blockIsPlaced = false;
    }
    
    void createNewBlock(){
        if(!gameOver){
            playerBlock = nextBlock[0];
            nextBlock[0] = nextBlock[1];
            nextBlock[1] = nextBlock[2];
            nextBlock[2]= new TetrisBlock(numColumns);
            blockIsPlaced = false;
        }
    }   
    void startGame(){
        reset();
        gameOver=false;
        blockPredictor=true;
        defeatScreenIsPlayed = false;
    }
    public void instantiateVariables(){
                
        gridHeight = this.getBounds().height;
        gridWidth  = this.getBounds().width;
        
        gridWidth-=paddingLeftAndRight*2;
        gridHeight-=paddingTopAndBottom*2;
   
        cellHeight=gridHeight/numRows;
        cellWidth=gridWidth/numColumns;
        
        modifierX= offsetx +paddingLeftAndRight;
        modifierY= offsety +paddingTopAndBottom;

        topBorder = modifierY;
    }  
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        instantiateVariables();     
        
        drawGrid(g); 
        drawCells(g);
   
        if(!gameOver){
            drawPlayerBlock(g);
        }
        drawGrid(g);      
        
        if(blockPredictor){
            drawPlacedPos(g);
        }
    }

    void drawDefeat(Graphics g){
        int finalWidth = cellWidth-2;
        int finalHeight= cellHeight-2;
        
        blockPredictor=false;
        this.repaint();
        
        if(!defeatScreenIsPlayed){

            for (int row = 0; row < numRows+1; row++) {
                    
                try {
                    Thread.sleep(70);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                
                for (int column = 0; column < numColumns; column++) {
                   
                    int cellx = column * cellWidth + modifierX+1;
                    int celly = row * cellHeight+modifierY+1;

                    if(row!=numRows){
                        g.setColor(Color.white);   
                        g.fillRoundRect(cellx, celly, finalWidth, finalHeight,3,3);
                    }

                    if(row!=0){
                        g.setColor(this.getBackground()); 
                        g.fillRect(cellx, celly-cellHeight, finalWidth, finalHeight);
                    }
                }
            }
        }
        defeatScreenIsPlayed = true;
        this.reset();
    }        
    void drawGrid(Graphics g){
        //Final sizes
        int finalWidth = cellWidth-2;
        int finalHeight= cellHeight-2;
        
        g.setColor(this.getBackground());   
        
        //Draw the grid
        for (int column = 0; column < numColumns; column++) {
            for (int row = 0; row < numRows; row++) {
                int cellx = column * cellWidth + modifierX+1;
                int celly = row * cellHeight+modifierY+1;
                
                //Grid may not be visible depending on chosen color
                g.drawRoundRect(cellx, celly, finalWidth, finalHeight,3,3);
            }
        }
        int variableWidth = (numColumns*cellWidth) ;
        int variableHeight = (numRows*cellHeight);
        
        g.setColor(this.getBackground());
        
        //Delete excess area on the grid border
        for(int i=0; i <borderThickness; i++){
            g.drawRect(modifierX+i, modifierY+i, variableWidth-2*i, variableHeight-2*i);
        }
        g.setColor(new Color(137,152,223,255));         
        
        //Replace with desired border color
        for(int i=0; i <borderThickness; i++){
            g.drawRoundRect(modifierX-i,modifierY-i, variableWidth+2*i,variableHeight+2*i,20,20);
        }

    }   
    void drawCells(Graphics g){
        //Final sizes
        int finalWidth = cellWidth-2;
        int finalHeight= cellHeight-2;
        //Used for dividing the cell inot 8x8 
        int bitSizeWidth = (cellWidth-2)/8;
        int bitSizeHeight = (cellHeight-2)/8;
        
        for (int column = 0; column < numColumns; column++) {
            for (int row = 0; row < numRows; row++) {
                
               Color color = gridColors[row][column];
               
               if(!(color==Color.darkGray )&&(color!=null)){
                   
                //Location of each cell;
                int cellx = column * cellWidth + modifierX + 1;
                int celly = row * cellHeight + modifierY + 1;
                
                //Draw the cell
                g.setColor(color);
                g.fillRoundRect(cellx, celly, finalWidth, finalHeight,3,3);
                
                //Draw lighting on the cell
                g.setColor(Color.white);
               // g.fillRect(cellx, celly, bitSizeWidth, bitSizeHeight);
                //g.fillRect(cellx + bitSizeWidth*2, celly + bitSizeHeight*2, bitSizeWidth*4, bitSizeHeight*4);
                
               }
            }
        }
    }  
    void drawPlayerBlock(Graphics g){
         g.setColor(playerBlock.color);

        //check for solid blocks to draw
        for (int col = 0; col < playerBlock.numColumns; col++)
        {
            for (int row = 0; row<playerBlock.numRows; row++)
            {
                if (playerBlock.isSolid[row][col])
                {
                    int blockX = playerBlock.x*(cellWidth) + (col*cellWidth)+modifierX;
                    int blockY = playerBlock.y*(cellHeight) + (row*cellHeight)+modifierY;

                    //Prevent drawing blocks outside screen
                    if(blockY>=topBorder){
                        g.fillRect(blockX+1,blockY+1, cellWidth-2, cellHeight-2);
                    }
                }
           }
        }
    }
    void drawPlacedPos(Graphics g){
        //Maximum Drop Distance
        int dropDistance = numRows - (playerBlock.y + playerBlock.numRows-1);

        //Iterate through each block of player Block
        for (int col = 0; col < playerBlock.numColumns; col++){
            for (int row = 0; row<playerBlock.numRows; row++){
                
                //Determine if the block is solid
                if (playerBlock.isSolid[row][col]){
                    int cellY = playerBlock.y + row;

                    //Use the row of the player block and iterate through each row below it
                    //Find the location of next immediate solid block
                    
                    for(int blocks = 0; blocks+cellY < numRows; blocks++ ){                     
                        if(cellY + blocks >0){
                            if(isGridCellSolid(cellY+blocks ,playerBlock.x+col) && blocks<=dropDistance){
                                dropDistance = blocks;
                            }
                        }
                    }
                }
           } 
        }
        g.setColor(Color.orange);

        for (int col = 0; col < playerBlock.numColumns; col++)
        {
            for (int row = 0; row<playerBlock.numRows; row++)
            {
                if (playerBlock.isSolid[row][col])
                {
                    int blockX = playerBlock.x*(cellWidth) + (col*cellWidth)+modifierX  ;
                    int blockY = playerBlock.y*(cellHeight) + ((row*cellHeight)+modifierY) + ((dropDistance-1)*cellHeight) ;

                    //Prevent drawing blocks outside screen
                    if(blockY>=topBorder){
                        for(int i = 0; i<3;i++){
                            g.drawRoundRect(blockX+i,blockY+i, cellWidth-2*i, cellHeight-2*i,3,3);
                        }
                    }
                }
           }
        }
    }
    
    boolean blockIsPlaced(){
        //Check if block is on bottom border
        if(playerBlock.y+playerBlock.numRows>=numRows){
              placeBlock();
              checkIfCleared();
              return true;
        }

        //Check if cell below is solid
        for(int col = 0; col<playerBlock.numColumns; col++) {
            for (int row = playerBlock.numRows-1; row >= 0; row--) {                 
                if (playerBlock.isSolid[row][col] && (playerBlock.y+row+1>=0)) {
                        if(isGridCellSolid(playerBlock.y+row+1,playerBlock.x+col)){
                            placeBlock();
                            checkIfCleared();
                            return true;
                    }
                }
            }
        }
        
       return false;
    }    
    boolean isGridCellSolid(int row, int col){
            if(gridColors[row][col]!=Color.darkGray){
                return true;
            }
            else{
                return false;
            }
    }
    void placeBlock(){
        //First check if position is valid
        for (int col = 0; col < playerBlock.numColumns; col++) {

            for(int row = 0; row<playerBlock.numRows; row++) {

                if (playerBlock.isSolid[row][col] && playerBlock.y+row>0) {               
                    gridColors[playerBlock.y+row][playerBlock.x+col] = playerBlock.color;
                }
            }
        }
        if(playerBlock.y<=0){
            gameOver=true;
        }else{
            createNewBlock();
        }
    }
    void checkIfCleared(){
       boolean[] isARow = new boolean[20];

        for(int i = 0; i<numRows; i++){
            isARow[i]=true;
        }

        for(int row = 0; row<numRows; row++){
            for(int col = 0; col<numColumns; col++){
                if(gridColors[row][col]==Color.darkGray) {
                    isARow[row]=false;
                }
            }
        }

        int consecutiveCleared=0;
        for(int i = 0; i<numRows; i++){
            if(isARow[i]) {
                linesCleared+=1;
                consecutiveCleared+=1;
                moveGridDown(i);
            }
        }
        
        if (consecutiveCleared==1){
            score += 120*level;
        }else if(consecutiveCleared==2){
            score += 300*level;
        }else if(consecutiveCleared==3){
            score += 500*level;
        }else if(consecutiveCleared==4){
            score += 800*level;
        }
        
        level = 1+(linesCleared/15);
    }
    void moveGridDown(int row){
        for(int i=row; i>0;i--) {
            System.arraycopy(gridColors[i - 1], 0, gridColors[i], 0, numColumns);
        }
    }
    
    void moveLeft(){
        boolean isValid = true;
        
        //Check if cell is found on the left most part;
        if (playerBlock.x>0){
            
            for(int col = 0; col<playerBlock.numColumns; col++) {

                for (int row = 0; row < playerBlock.numRows; row++) {

                    if (playerBlock.isSolid[row][col] && playerBlock.y+row>0) {

                        if(isGridCellSolid(playerBlock.y+row,playerBlock.x+col-1)){
                            isValid = false;
                        }
                    }
                }
            }
        }
        else{
            isValid= false;
        }
        
        if(isValid){
            playerBlock.x -= 1;
            
        }
        repaint();
    } 
    void moveRight(){
         boolean isValid = true;
        
        //Check if cell is found on the left most part;
        if (playerBlock.x+playerBlock.numColumns<numColumns){
            
            for(int col = 0; col<playerBlock.numColumns; col++) {

                for (int row = 0; row < playerBlock.numRows; row++) {

                    if (playerBlock.isSolid[row][col] && playerBlock.y+row>0) {

                        if(isGridCellSolid(playerBlock.y+row,playerBlock.x+col+1)){
                            isValid = false;
                        }
                    }
                }
            }
        }
        else{
            isValid= false;
        }
        
        if(isValid){
            playerBlock.x += 1;
            blockIsPlaced();
        }
        repaint();
    }
    void playerBlockReduceHeight(){
        if(!gameOver){
        playerBlock.y +=1;
        }
    }
    
    void rotate(){
        boolean isValid = true; 
        int numMove = 0;
        
        playerBlock.rotate();
        prevX = playerBlock.x;
        
        while(playerBlock.x+playerBlock.numColumns>numColumns){
               playerBlock.x -=1;
        }
        
        for (int row = 0; row < playerBlock.numRows; row++) {
          for(int col = 0; col<playerBlock.numColumns; col++) {  
                if (playerBlock.isSolid[row][col] && playerBlock.x>0) {
                    if(isGridCellSolid(playerBlock.y+row,playerBlock.x+col) && numMove<2){
                       playerBlock.x -=1;
                       row =0;
                       col=0;
                       numMove++;
                    }
                }
            }
        }
        
        for (int row = 0; row < playerBlock.numRows; row++) {
          for(int col = 0; col<playerBlock.numColumns; col++) {  
                if (playerBlock.isSolid[row][col]) {
                    
                    int tempx = playerBlock.x+col;
                    int tempy = playerBlock.y+row;
                    
                    if(isGridCellSolid(tempy, tempx)){
                        isValid = false;
                    }
                }
            }
        }
                
        if (!isValid){
            playerBlock.rotate(); 
            playerBlock.x +=numMove;
            playerBlock.x = prevX;
        }
        repaint();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
   }
