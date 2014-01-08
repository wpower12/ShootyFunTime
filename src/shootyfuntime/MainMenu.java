/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shootyfuntime;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.command.*;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Wpower
 */
public class MainMenu extends BasicGameState {
    
        
    private StateBasedGame game; // stored for later use

    public MainMenu() {
        
    }
    
    
    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        
        this.game = game;  // Save the game object so we can use  it later
 
    }
 
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        // TODO Auto-generated method stub
        g.setColor(Color.white);
        g.drawString("HW Game - Press Number", 50, 10);

        g.drawString("1. Play Game", 50, 100);
        g.drawString("2. High Scores", 50, 120);
        g.drawString("3. Quit", 50, 140);
    }
 
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
            
        
           
    }

    @Override
    public void keyPressed(int key, char c){ // this method is called when a key has been pressed down
        
        switch( key ){
           
            case Input.KEY_1:
                game.enterState( 1 );
            case Input.KEY_3:    
            
            default:
                
                break;
        }
        
        
    }
 
    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
