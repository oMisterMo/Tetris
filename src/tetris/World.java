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
import java.util.Random;

/**
 * The World class handles the rendering of all Game Objects.
 *
 * @version 0.1.0
 * @author Mohammed Ibrahim
 */
public class World extends GameObject {

    /**
     * Width of a single tile
     */
    public static final int TILE_WIDTH = 25;

    /**
     * Height of a single tile
     */
    public static final int TILE_HEIGHT = 25;

    /**
     * Number of horizontal tiles
     */
    public static final int NO_OF_TILES_X = 10;

    /**
     * Number of vertical tiles
     */
    public static final int NO_OF_TILES_Y = 20;

    /**
     * Array holding all tiles
     */
    public TileType[][] tiles;

    Random ran = new Random();

    /**
     * Constructs a new world.
     */
    public World() {
        //Initial new world here
        tiles = new TileType[NO_OF_TILES_Y][NO_OF_TILES_X];
        //Initialise each Tile to empty
        resetBoard();
    }

    /**
     * Sets all the tile type to empty.
     */
    public void resetBoard() {
        for (int y = 0; y < NO_OF_TILES_Y; y++) {
            for (int x = 0; x < NO_OF_TILES_X; x++) {
                tiles[y][x] = TileType.EMPTY;
            }
        }
    }

    /**
     * Sets all tiles to null.
     */
    public void clearBoard() {
        for (int y = 0; y < NO_OF_TILES_Y; y++) {
            for (int x = 0; x < NO_OF_TILES_X; x++) {
                tiles[y][x] = null;
            }
        }
    }

    /**
     * Sets a tile at x,y to the given tile.
     *
     * @param x the x index of the tile to set
     * @param y the y index of the tile to set
     * @param tile tile type to set
     */
    public void setTile(int x, int y, TileType tile) {
        if (x < 0 || x > NO_OF_TILES_X) {
            System.out.println("Error! x val");
            return;
        }
        if (y < 0 || y > NO_OF_TILES_Y) {
            System.out.println("Error! y val");
            return;
        }
        tiles[y][x] = tile;
    }

    /**
     * Gets a tile at x,y
     *
     * @param x the x index of the tile to return
     * @param y the y index of the tile to return
     * @return tile at x,y
     */
    public TileType getTile(int x, int y) {
        return tiles[y][x];
    }

    @Override
    void gameUpdate() {
        //do nothing
    }

    @Override
    void gameRender(Graphics2D g) {
        /* Called every frame */
        for (int y = 0; y < NO_OF_TILES_Y; y++) {
            for (int x = 0; x < NO_OF_TILES_X; x++) {

                //if tile is empty -> SKIP CURRENT ITERATION
                if (tiles[y][x].equals(TileType.EMPTY)) {
                    continue;
                }

                //Depending on whats on the tile, choose appropriate color to render
                if (tiles[y][x].equals(TileType.RBLOCK)) {
                    g.setColor(TileType.RBLOCK.getColor());
                    g.fillRect(x * TILE_WIDTH, y * TILE_HEIGHT, 
                            TILE_WIDTH - 1, TILE_HEIGHT - 1);
                } else if (tiles[y][x].equals(TileType.TBLOCK)) {
                    g.setColor(TileType.TBLOCK.getColor());
                    // + 1 to position, -1 to size
                    g.fillOval(x * TILE_WIDTH, y * TILE_HEIGHT, 
                            TILE_WIDTH - 1, TILE_HEIGHT - 1);
                } else {
                    //Must be snake head -> Checked all other tile types
                    g.setColor(TileType.LBLOCK.getColor());
                    g.fillRect(x * TILE_WIDTH, y * TILE_HEIGHT, 
                            TILE_WIDTH - 1, TILE_HEIGHT - 1);

                    g.fillRect(x * TILE_WIDTH + TILE_WIDTH, y * TILE_HEIGHT, 
                            TILE_WIDTH - 1, TILE_HEIGHT - 1);
                    g.fillRect(x * TILE_WIDTH, y * TILE_HEIGHT - TILE_HEIGHT, 
                            TILE_WIDTH - 1, TILE_HEIGHT - 1);
                    g.fillRect(x * TILE_WIDTH, y * TILE_HEIGHT - TILE_HEIGHT * 2, 
                            TILE_WIDTH - 1, TILE_HEIGHT - 1);
                }
            }
        }
    }
}
