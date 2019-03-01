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

import java.awt.Color;

/**
 * The TileType class is used to represent all enumerations of a tile.
 *
 * @version 0.1.0
 * @author Mohammed Ibrahim
 */
public enum TileType {

    LBLOCK(Color.ORANGE),
    RBLOCK(Color.BLUE),
    TBLOCK(Color.RED),
    SBLOCK(Color.YELLOW),
    EMPTY(null);

    private final Color tile;

    /* Constructor is private or protected only */
    private TileType(Color color) {
        tile = color;
    }

    public Color getColor() {
        return tile;
    }
}
