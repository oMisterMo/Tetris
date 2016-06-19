/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris;

import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * 08-Jun-2016, 18:57:32.
 *
 * @author Mo
 */
public class Tetris extends GameObject{
    
    private World world;
    
    //Movement direction
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    private int currentState;
    
    //Actual snake size
    LinkedList<Point> snake;
    //Current Poition
    Point head;
    Point temp;
    
    private Random ran;
    
    public Tetris(World world){
        this.world = world;
        snake = new LinkedList();
        
        head = new Point(0,0);
        snake.add(head);
        world.setTile(head.x, head.y, TileType.SnakeHead);
        currentState = Tetris.RIGHT;
        temp = new Point(0,0);
        
        ran = new Random();
    }
    
    //Move when key is pressed
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        //stop the snake from turning back into itself
        if(currentState == Tetris.UP && key == KeyEvent.VK_DOWN){
            return;
        }
        if(currentState == Tetris.LEFT && key == KeyEvent.VK_RIGHT){
            return;
        }
        if(currentState == Tetris.RIGHT && key == KeyEvent.VK_LEFT){
            return;
        }
        if(currentState == Tetris.DOWN && key == KeyEvent.VK_UP){
            return;
        }

        //Update current state depending on movement
        if (key == KeyEvent.VK_UP) {
            currentState = Tetris.UP;
        }else
        if (key == KeyEvent.VK_LEFT) {
            currentState = Tetris.LEFT;
        }else
        if (key == KeyEvent.VK_RIGHT) {
            currentState = Tetris.RIGHT;
        }else
        if (key == KeyEvent.VK_DOWN) {
            currentState = Tetris.DOWN;
        }
    }
    
    @Override
    void gameUpdate() {
        //Handle Movement
        Point tempHead = snake.getFirst();
        switch(currentState){
            case Tetris.DOWN:
                //Push to front of the stack
                snake.push(new Point(tempHead.x, tempHead.y + 1));
                break;
            case Tetris.LEFT:
                snake.push(new Point(tempHead.x - 1, tempHead.y));
                break;
            case Tetris.RIGHT:
                snake.push(new Point(tempHead.x + 1, tempHead.y));
                break;
            case Tetris.UP:
                snake.push(new Point(tempHead.x, tempHead.y - 1));
                break;
        }
        
        //Update our head variable with new head position
        head = snake.getFirst();
        //To the right of screen
        if (head.x > World.NO_OF_TILES_X -1) {
            head.x = 0;
        }
        //To the botom
        if(head.y > World.NO_OF_TILES_Y -1){
            head.y = 0;
        }
        //To the left
        if (head.x < 0) {
            head.x = World.NO_OF_TILES_X -1;
        }
        //To the top
        if(head.y < 0){
            head.y = World.NO_OF_TILES_Y -1;
        }
        
        //if the current head is not a food
        if(!isFood(head.x, head.y)){
            //Remove our old head value
            //System.out.println("notFood");
            Point previous = snake.removeLast();
            world.setTile(previous.x, previous.y, TileType.EMPTY);
        }else{
            spawnFood();
        }
        
        //Update the new head
        world.setTile(head.x, head.y, TileType.SnakeHead);
        //Add the body that follows head
        
        /* Handle food spawn */
        
    }

    @Override
    void gameRender(Graphics2D g) {
//        for(Point p: snake){
//            g.drawRect(p.x, p.y, World.TILE_WIDTH, World.TILE_HEIGHT);
//        }
    }
    
    private void spawnFood(){        
        int x = ran.nextInt(World.NO_OF_TILES_X);
        int y = ran.nextInt(World.NO_OF_TILES_Y);
        
        //While the current tile is not empty
        while(!isEmpty(x, y)){
            /*Later in the game, we could be stuck here a while looking for free slots*/
            int num =0;
            System.out.println("CURRENT TILE NOT EMPTY FOUND NEW SPAWN: ");
            x = ran.nextInt(World.NO_OF_TILES_X);
            y = ran.nextInt(World.NO_OF_TILES_Y);
            System.out.println("x: "+x +" y: "+y);
            System.out.println("Times looked: " + ++num);
        }
        
        setFood(x, y);
    }
    
    private void setFood(int x, int y){
        world.setTile(x, y, TileType.Food);
    }
    
    private void setHead(int x, int y){
        world.setTile(x, y, TileType.SnakeHead);
    }
    
    private void setBody(int x, int y){
        world.setTile(x, y, TileType.SnakeBody);
    }
    
    public boolean isBody(int x, int y){
        return world.getTile(x, y).equals(TileType.SnakeBody);
    }
    public boolean isFood(int x, int y){
        return world.getTile(x, y).equals(TileType.Food);
    }
    public boolean isEmpty(int x, int y){
        return world.getTile(x, y).equals(TileType.EMPTY);
    }

}
