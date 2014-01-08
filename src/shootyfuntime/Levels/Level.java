/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shootyfuntime.Levels;

import org.newdawn.slick.Graphics;

/**
 * Level Class - Base class for all levels
 * @author Wpower
 */
public class Level {
    
    public Level(){
        
    }
    
    public boolean isOver(){
        
        return false;
    }
    
    public Level getNextLevel(){
        
        return new Level();
    }
    
    public void update(){
        
    }
    
    public void draw( Graphics g ){
        
    }
    
}
