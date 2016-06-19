/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris;

import java.awt.Graphics2D;
import java.util.Random;

/**
 * 16/05/2016
 * 
 * @author Mo
 */
public class World extends GameObject{
    
    public static final int TILE_WIDTH = 50;
    public static final int TILE_HEIGHT = 50;
    
    public static final int NO_OF_TILES_X = 10;
    public static final int NO_OF_TILES_Y = 20;
    
    //Array holding all tiles
    public TileType[] tiles;
    
    Random ran = new Random();
    
    public World(){
        //Initial new world here
        tiles = new TileType[NO_OF_TILES_X * NO_OF_TILES_Y];
        //Initialise each Tile to empty
        resetBoard();
        
        //delete randomization of board
//        for(int i=0; i<tiles.length; i++){
//            tiles[i] = TileType.values()[ran.nextInt(4)];
//        }
        
//        setTile(1, 0, TileType.SnakeHead);
//        setTile(1, 0, TileType.SnakeHead);
//        setTile(0, 0, TileType.Food);
//        setTile(0, 3, TileType.Food);
//        setTile(0, 4, TileType.Food);
//        setTile(0, 5, TileType.Food);
//        setTile(0, 6, TileType.Food);
//        setTile(0, 7, TileType.Food);
//        setTile(1, 7, TileType.Food);
//        setTile(2, 7, TileType.Food);
//        setTile(3, 7, TileType.Food);
//        setTile(4, 7, TileType.Food);
//        setTile(5, 7, TileType.Food);
//        setTile(6, 7, TileType.Food);
//        setTile(7, 7, TileType.Food);
//        setTile(0, 7, TileType.Food);
//        setTile(0, 20, TileType.Food);
//        setTile(0, 21, TileType.Food);
//        setTile(0, 22, TileType.Food);
//        setTile(0, 23, TileType.Food);
//        setTile(0, 24, TileType.Food);
//        setTile(0, 25, TileType.Food);
        //setTile(20, 15, TileType.Food);
//        setTile(3, 18, TileType.Food);
//        setTile(15, 18, TileType.Food);
//        setTile(22, 2, TileType.Food);
//        setTile(30, 30, TileType.Food);
//        setTile(3, 45, TileType.Food);
//        setTile(50, 57, TileType.Food);
//        setTile(0, 1, TileType.SnakeBody);
//        setTile(1, 1, TileType.SnakeBody);
//        setTile(59, 59, TileType.SnakeBody);
//        setTile(49, 49, TileType.SnakeBody);
//        setTile(59, 0, TileType.SnakeBody);
//        setTile(0, 59, TileType.SnakeBody);
        //setBackground(Color.BLACK);
    }
    
    
    public void resetBoard(){
        for(int i=0; i<tiles.length; i++){
            tiles[i] = TileType.EMPTY;
        }
    }
    
    public void clearBoard(){
        for(int i=0; i<tiles.length; i++){
            tiles[i] = null;
        }
    }
    
    public void setTile(int x, int y, TileType tile){
        if(x< 0 || x>NO_OF_TILES_X){
            System.out.println("Error! x val");
            return;
        }
        if(y< 0 || y>NO_OF_TILES_Y){
            System.out.println("Error! y val");
            return;
        }
        tiles[y * NO_OF_TILES_X + x] = tile;
    }
    
    public TileType getTile(int x, int y){
        return tiles[y * NO_OF_TILES_X + x];
    }

    @Override
    void gameUpdate() {
    }

    @Override
    void gameRender(Graphics2D g) {
        /* Called every frame */
        
        for(int i=0; i<NO_OF_TILES_X * NO_OF_TILES_Y; i++){
            int x = i % NO_OF_TILES_X;
            int y = i / NO_OF_TILES_Y;
            
            //if tile is empty, do nothing
            if(tiles[i].equals(TileType.EMPTY)){
                continue;
            }
            
            //Depending on whats on the tile, choose appropriate color to render
            if(tiles[i].equals(TileType.SnakeBody)){
               g.setColor(TileType.SnakeBody.getColor());
               g.fillRect(x * TILE_WIDTH , y * TILE_HEIGHT , TILE_WIDTH-1, TILE_HEIGHT-1);
            }else if(tiles[i].equals(TileType.Food)){
                g.setColor(TileType.Food.getColor());
                // + 1 to position, -1 to size
                g.fillOval(x * TILE_WIDTH, y * TILE_HEIGHT, TILE_WIDTH-1, TILE_HEIGHT-1);
            }else{
                //Must be snake head -> Checked all other tile types
                g.setColor(TileType.SnakeHead.getColor());
                g.fillRect(x * TILE_WIDTH , y * TILE_HEIGHT , TILE_WIDTH-1, TILE_HEIGHT-1);
            }
        }
    }
}
