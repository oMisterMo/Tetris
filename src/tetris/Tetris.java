/* 
 * Copyright (C) 2019 Mohammed Ibrahim
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package tetris;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * This Class represents the game screen, all logic occurs here.
 *
 * @version 0.1.0
 * @author Mohammed Ibrahim
 */
public class Tetris extends GameObject {

    private World world;

    //Movement direction
    /**
     * Block is moving DOWN
     */
    public static final int DOWN = 1;

    /**
     * Block is moving LEFT
     */
    public static final int LEFT = 2;

    /**
     * Block is moving RIGHT
     */
    public static final int RIGHT = 3;
    private int currentState;

    //Actual shapeList size
    LinkedList<Point> shapeList;
    //Current Poition
    Point curShape;
    TileType blockType;
    //Point temp;

    private Random ran;

    /**
     * Constructs a new world
     *
     * @param world reference to game world
     */
    public Tetris(World world) {
        this.world = world;
        shapeList = new LinkedList();
        //add new block to ran x < width, y = -1
        curShape = new Point(0, 0);
        shapeList.add(curShape);

        blockType = TileType.SBLOCK;
        world.setTile(curShape.x, curShape.y, blockType);
        currentState = Tetris.DOWN;

        //temp = new Point(0,0);
        ran = new Random();
    }

    /**
     * Sets the blocks direction
     *
     * @param e key event
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //Update current state depending on movement
        if (key == KeyEvent.VK_LEFT) {
            currentState = Tetris.LEFT;
            //moveLeft();
        } else if (key == KeyEvent.VK_RIGHT) {
            currentState = Tetris.RIGHT;
            //moveRight();
        } else if (key == KeyEvent.VK_DOWN) {
            currentState = Tetris.DOWN;
            //speedFall();
        }
    }

    @Override
    void gameUpdate(float deltaTime) {
        //Handle Movement
        Point previous = shapeList.pollFirst();

        //drop curShape
        //handle rotation
        //checkfor collision with floor
        //checkfor collision with other shapes
        //remove line if full
        //drop other shapes down
        switch (currentState) {
            case Tetris.DOWN:
                //Push to front of the stack
                shapeList.push(new Point(previous.x, previous.y + 1));
                break;
            case Tetris.LEFT:
                shapeList.push(new Point(previous.x - 1, previous.y));
                currentState = Tetris.DOWN;
                break;
            case Tetris.RIGHT:
                shapeList.push(new Point(previous.x + 1, previous.y));
                currentState = Tetris.DOWN;
                break;
        }

        //Update our curShape variable with new curShape position
        curShape = shapeList.getFirst();

        //To the right of screen
        if (curShape.x > World.NO_OF_TILES_X - 1) {
            curShape.x = World.NO_OF_TILES_X - 1;
        }
        //To the botom
        if (curShape.y > World.NO_OF_TILES_Y - 1) {
            curShape.y = World.NO_OF_TILES_Y - 1;
        }
        //To the left
        if (curShape.x < 0) {
            curShape.x = 0;
        }
        world.setTile(previous.x, previous.y, TileType.EMPTY);
        //Update the new curShape
        world.setTile(curShape.x, curShape.y, blockType);
        //Add the body that follows curShape
    }

    @Override
    void gameRender(Graphics2D g) {
        
    }
}
