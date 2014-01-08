/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shootyfuntime;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import shootyfuntime.Levels.FirstLevel;

/**
 *
 * @author Wpower
 */
public class ShootyFunTime extends StateBasedGame {
    
    public static int SCREEN_HEIGHT, SCREEN_WIDTH;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
                AppGameContainer container = new AppGameContainer(new ShootyFunTime("Shooty Fun Time"));
                container.setDisplayMode(SCREEN_WIDTH,SCREEN_HEIGHT,false);
                container.setShowFPS( false );
                container.start();
	} catch (SlickException e) {
		e.printStackTrace();
	}
        
    }

    public ShootyFunTime(String name) {
        super(name);
        SCREEN_HEIGHT = 600;
        SCREEN_WIDTH = 800;
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
         
        this.addState(new MainMenu());
        this.addState(new GameState( new FirstLevel() ));
        
    }
    
}
