/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shootyfuntime.Actions;

import shootyfuntime.Actions.Action;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import shootyfuntime.Flag;


/**
 *
 * @author Wpower
 */
public class MoveInCircle extends Action {

    //Stuff for loop
    protected int counter, shotcounter, shotPeriod;
    protected int dwell;
    protected int phase;
    
    public MoveInCircle(){
        
        actionType = 1;
        
        counter = 0;
        dwell = 150;
        phase = 0;
        
        shotPeriod = 300;
        shotcounter = 0;
        
    }

    @Override
    public void update() {
        
        //Set all flags to false
        for(Flag f : Flag.values()){
            flags.set( f.i(), Boolean.FALSE);
        }
        
        //Update counters
        if( counter > dwell ){
            phase++;
            if( phase > 3 ){
                phase = 0;
            }
            counter = 0;
        } else {
            counter++;
        }
            //Shotcounter
        if( shotcounter > shotPeriod ){
            flags.set(4, Boolean.TRUE);
            shotcounter = 0;
        } else {
            shotcounter++;
        }
        
        
        //Use phase to set flags
        switch(phase) {
            
            case 0:
                flags.set( 0, Boolean.TRUE );
                break;
            case 1:
                flags.set( 2, Boolean.TRUE );
                break;
            case 2:
                flags.set( 1, Boolean.TRUE );
                break;
            case 3:
                flags.set( 3, Boolean.TRUE );
                break;
        }
        
    }
    
}
