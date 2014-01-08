/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shootyfuntime;

import shootyfuntime.Characters.BadGuy;
import shootyfuntime.Characters.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Input;

import java.util.ArrayList;
import shootyfuntime.Levels.Level;

public class GameState extends BasicGameState {
    
   
    private Input input;
    private Player player;
    private ArrayList<BadGuy> baddies; 
    private Level level;
    
    public GameState( Level l ) {
        
        level = l;
        baddies = new ArrayList<>();
        
    }
    
    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        
        player = new Player();
        baddies.add(new BadGuy());
        
    }
 
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        
        //Draw Level
        level.draw( g );
        
        //Draw Player
        player.draw( g );
        
        //Draw Enemies
        for( int i = 0; i < baddies.size(); i++ ){
            
            baddies.get(i).draw( g );
            
        }
        
    }
 
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        
        //Update Player
        player.getInput( container );
        player.update( container );
        
        
        //Check Bullet Collisions
        for( int i = 0; i < baddies.size(); i++ ){
            player.checkShotCollisions( baddies.get(i) );
        }
        
        //Update Enemies
        for( int i = 0; i < baddies.size(); i++ ){
            baddies.get(i).lookAtPlayer(player);
            baddies.get(i).update( container );
            if( baddies.get(i).checkHealth() <= 0 ) {
                baddies.remove(i);
            }
        }
        
        //Update Level
        level.update();
        
        //Check for end of level
        if( level.isOver() ){
            //Treating levels like states.  If one ends, it knows where to send the player next.
            level = level.getNextLevel();
            
        }
        
        //Check For Global Commands
        if( container.getInput().isKeyPressed(Input.KEY_P) ){
            
            baddies.add(new BadGuy() );
            
        }
    }
 
    @Override
    public int getID() {
        return 1;
    }
}
