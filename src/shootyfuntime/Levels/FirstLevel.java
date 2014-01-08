/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shootyfuntime.Levels;
import java.util.HashSet;
import java.util.Set;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Wpower
 */
public class FirstLevel extends Level {
    
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
        
        g.setColor( Color.green );
        
        //Top
        g.drawRect(0, 0, 799, 5);
        //Right
        g.drawRect(795, 0, 5, 600);
        //Bottom
        g.drawRect(0, 595, 799, 5);
        //Left
        g.drawRect(0, 0, 5, 599);
    }
    
}
