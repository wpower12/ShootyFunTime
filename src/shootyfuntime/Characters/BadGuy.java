/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shootyfuntime.Characters;

import shootyfuntime.Characters.Player;
import shootyfuntime.Actions.MoveTowards;
import shootyfuntime.Actions.MoveInCircle;
import shootyfuntime.Actions.ActionManager;
import org.newdawn.slick.Color;
import static java.lang.Math.*;
import java.util.Random;

/**
 *
 * @author Wpower
 */
public class BadGuy extends Player {
    
    protected float player_x, player_y, rand_x, rand_y;
    
    protected double closeRadius, dif, dx, dy;
   
    protected boolean deathFlag;
    
    protected ActionManager am;
    
    protected Random rng;
    
    protected enum Direction {
        
        U (0), D(1), L (2), R (3), 
        UL (4), UR (5), DL (6), DR (7), 
        ULL (8), ULR (9), URL (10), URR (11),
        DLL (12), DLR (13), DRL (14), DRR (15);
        
        private int i;
        Direction( int j ){ i = j; }
        
        int i(){ return i; }
    }
    
    public BadGuy(){
        
        loc_x = 200;
        loc_y = 100;
        
        deathFlag = false;
        
        closeRadius = 150;
        
        rng = new Random();
        
        am = new ActionManager();
        
        bulletColor = Color.red;
    }
    
    public void lookAtPlayer( Player p ){
        
        // Depending on the player location, we tell the am to add/remove actions
        // from the Action Manager.  
        //
        // Ex: 
        // am.appendAction( moveTo( x , y ) )   - appends Specified Action to the action vector 
        // am.popAction()                       - Remove Top Action
        // am.pushAction( moveTo() )            - add Action to the top of the action vector
        // flags =  am.getMovements()           - gets the current key press flags from the vector
        
        player_x = p.getX();
        player_y = p.getY();
        
        target_x = player_x;
        target_y = player_y;
        
        //Imagine the baddie is the origin of a plane.  Depending on where the player is
        //Flag U/D/L/R.  Handle 2 cases.  If player is greater than or less than a certain
        // radius.  If greater than, move 'towards' player.  If less than, more 'away'
        
        dx = loc_x - player_x;
        dy = loc_y - player_y;
        dif = sqrt( pow( dx, 2 ) + pow( dy, 2 ) );
        
        //Check if player is close
        if( dif < closeRadius ){
            //If top action is not MoveTowards (to avoid adding lots of actions?
            if( !(am.currentActionType() == 2) ){
                //Move Towards 'Away' from enemy.
                    //Select Away Location
                    // - Find the quadrant that the player is in
                    // - Each Quad has 5 Possible dirs accsociated with it
                    // - Randomly pick 1 of the 5 dirs from the quad
                int dir = 0;
                
                if( (dx > 0) && (dy > 0) ){ dir = Direction.DL.i(); }    //Quad 1
                if( (dx < 0) && (dy > 0) ){ dir = Direction.DR.i(); }    //Quad 2
                if( (dx < 0) && (dy < 0) ){ dir = Direction.UR.i(); }    //Quad 3
                if( (dx > 0) && (dy < 0) ){ dir = Direction.UL.i(); }    //Quad 4
                if( dx == 0 ){                                          //On X Axis
                    if( dy > 0 ){
                        dir = Direction.D.i();
                    } else {
                        dir = Direction.U.i();
                    }
                } //On X Axis
                if( dy == 0 ){                                          //On Y Axis
                    if( dx > 0 ){
                        dir = Direction.L.i();
                    } else {
                        dir = Direction.R.i();
                    }
                } 
                
                int rand_dur = rng.nextInt(150)+30;
                
                am.pushAction( new MoveTowards( dir, rand_dur ) );
            }
        }
        
        //If there is no action, add some actions
        if( am.isEmpty() ){
            
            am.addAction( new MoveTowards( rng.nextInt(16), rng.nextInt(300)+80 ));
            am.addAction( new MoveInCircle() );
            
        }
        
        //Update Flags
        for( int i = 0; i < 5; i++ ){
            flags.set(i, am.getFlag(i));
        }
        
        //am.updateFlags( flags );
        
        //Update Actions
        am.updateActions();
    }  
    
}
