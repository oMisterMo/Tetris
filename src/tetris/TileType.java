/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;

/**
 * 07-Jun-2016, 23:13:09.
 *
 * @author Mo
 */
public enum TileType {

    SnakeHead(Color.GREEN),
    SnakeBody(Color.BLUE),
    Food(Color.RED),
    EMPTY(null);
    
    private Color tile;
    
    /* Constructor is private or protected only */
    private TileType(Color color){
        tile = color;
    }
    
    public Color getColor(){
        return tile;
    }
}
