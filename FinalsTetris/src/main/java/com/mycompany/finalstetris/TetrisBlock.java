package com.mycompany.finalstetris;

import java.awt.*;
import java.util.Random;

public class TetrisBlock {

    public Block blockType;
    public int numRows;
    public int numColumns;
    
    //Location is based on grid;
    public int x=0;
    public int y=0;
    
    public Color color;
    public Boolean[][] isSolid;
    
    enum Block{
        Square,
        Line_Piece,
        Z_Block,
        Reverse_Z,
        L_Block,
        Reverse_L,
        T_Block;
    }

    private Block getRandomBlock(){
        Random rand = new Random();

        int random = rand.nextInt(Block.values().length);

        return switch (random) {
            case (0) -> Block.Square;
            case (1) -> Block.Line_Piece;
            case (2) -> Block.Z_Block;
            case (3) -> Block.Reverse_Z;
            case (4) -> Block.L_Block;
            case (5) -> Block.Reverse_L;
            case (6) -> Block.T_Block;
            default -> null;
        };

    }

    TetrisBlock(int numGridCols){
        Block blockType = getRandomBlock();
        setBlockFromBlockType(blockType, numGridCols);   
    }

    private void setBlockFromBlockType(Block temp, int numGridCols){
        blockType = temp;
                
        switch (temp){
            case Square:
                //Set the dimension of the block
                numRows = 2;
                numColumns = 2;

                //Setup the shape
                isSolid = new Boolean[numRows][numColumns];
                isSolid[0][0]=true;
                isSolid[0][1]=true;
                isSolid[1][0]=true;
                isSolid[1][1]=true;

                //set the color
                color=Color.yellow;
                break;

            case Line_Piece:
                numRows = 4;
                numColumns = 1;

                //Setup the shape
                isSolid = new Boolean[numRows][numColumns];
                isSolid[0][0]=true;
                isSolid[1][0]=true;
                isSolid[2][0]=true;
                isSolid[3][0]=true;

                color=new Color(80,191,238,255);
                break;

            case Z_Block:
                numRows = 2;
                numColumns = 3;

                //Setup the shape
                isSolid = new Boolean[numRows][numColumns];
                isSolid[0][0]=false;
                isSolid[0][1]=true;
                isSolid[0][2]=true;
                isSolid[1][0]=true;
                isSolid[1][1]=true;
                isSolid[1][2]=false;

                color=Color.green;
                break;

            case Reverse_Z:
                numRows = 2;
                numColumns = 3;

                isSolid = new Boolean[numRows][numColumns];
                isSolid[0][0]=true;
                isSolid[0][1]=true;
                isSolid[0][2]=false;
                isSolid[1][0]=false;
                isSolid[1][1]=true;
                isSolid[1][2]=true;

                color=Color.red;
                break;

            case L_Block:
                numRows = 3;
                numColumns =2;

                isSolid = new Boolean[numRows][numColumns];
                isSolid[0][0]=true;
                isSolid[0][1]=false;
                isSolid[1][0]=true;
                isSolid[1][1]=false;
                isSolid[2][0]=true;
                isSolid[2][1]=true;
                
                color=new Color(229,110,38,255);
                break;

            case Reverse_L:
                numRows =3 ;
                numColumns = 2;

                isSolid = new Boolean[numRows][numColumns];
                isSolid[0][0]=false;
                isSolid[0][1]=true;
                isSolid[1][0]=false;
                isSolid[1][1]=true;
                isSolid[2][0]=true;
                isSolid[2][1]=true;
                
                color=Color.blue;
                break;

            case T_Block:
                numRows = 2;
                numColumns = 3;

                isSolid = new Boolean[numRows][numColumns];
                isSolid[0][0]=false;
                isSolid[0][1]=true;
                isSolid[0][2]=false;
                isSolid[1][0]=true;
                isSolid[1][1]=true;
                isSolid[1][2]=true;
                
                color=Color.magenta;
        }
        x= numGridCols/2 - numColumns/2;
        y= -numRows;
    }

    public  void rotate(){

        int rows = numRows;
        int cols = numColumns;

        Boolean[][] rotatedMatrix = new Boolean[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotatedMatrix[j][rows - 1 - i] = isSolid[i][j];
            }
        }

        if(y<0){
            y=0;
        }

        isSolid = rotatedMatrix;
        numRows = cols;
        numColumns=rows;
    }
    
    
}
