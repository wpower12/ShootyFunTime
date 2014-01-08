/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shootyfuntime.Levels;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Wpower
 */
public class GenericLevel extends Level {
    
    @Override
    public boolean isOver(){
        
        return false;
    }
    
    @Override
    public Level getNextLevel(){
        
        return new Level();
    }
    
    @Override
    public void update(){
        
    }
    
    @Override
    public void draw( Graphics g ){
        
    }
    
}
