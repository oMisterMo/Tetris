/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris;

import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JSlider;

/**
 * Main Class
 *
 * 13-Jun-2016, 21:13:21.
 *
 * @author Mo
 */
public class TetrisMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Variables
        JFrame window = new JFrame("First Game");
        GamePanel game = new GamePanel();
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(game);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setVisible(true);
    }

}
