/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shootyfuntime.Actions;

import shootyfuntime.Actions.Action;
import java.util.ArrayList;
import shootyfuntime.Direction;
import shootyfuntime.Flag;

/**
 *
 * @author Wpower
 */
public class MoveTowards extends Action {
    
    protected int dir, duration, counter;
    
    protected int x_mod, y_mod, x_Button, y_Button;
    
    public MoveTowards( int d, int dur ){
        
        actionType = 2;
        
        dir = d;
        duration = dur;

        counter = 1;
        
        Direction dir_e = Direction.values()[dir];

        switch( dir_e ){
            
            case U:
                x_Button = 99;
                y_Button = Flag.UP.i();
                break;
            case D:
                x_Button = 99;
                y_Button = Flag.DOWN.i();
                break;
            case L:
                x_Button = Flag.LEFT.i();
                y_Button = 99;
                break;
            case R:
                x_Button = Flag.RIGHT.i();
                y_Button = 99;
                break;
            case UL:
                x_Button = Flag.LEFT.i();
                y_Button = Flag.UP.i();
                break;    
            case UR:
                x_Button = Flag.RIGHT.i();
                y_Button = Flag.UP.i();
                break;
            case DL:
                x_Button = Flag.LEFT.i();
                y_Button = Flag.DOWN.i();
                break;
            case DR:
                x_Button = Flag.RIGHT.i();
                y_Button = Flag.DOWN.i();
                break;
            case ULL:
                x_Button = Flag.LEFT.i();
                y_Button = Flag.UP.i();
                x_mod = 1;   
                y_mod = 2;  
                break;
            case ULR:
                x_Button = Flag.LEFT.i();
                y_Button = Flag.UP.i();
                x_mod = 2;  
                y_mod = 1; 
                break;
            case URL:
                x_Button = Flag.RIGHT.i();
                y_Button = Flag.UP.i();
                x_mod = 2;  
                y_mod = 1;
                break;
            case URR:
                x_Button = Flag.RIGHT.i();
                y_Button = Flag.UP.i();
                x_mod = 1;  
                y_mod = 2;
                break;
            case DLL:
                x_Button = Flag.LEFT.i();
                y_Button = Flag.DOWN.i();
                x_mod = 1;  
                y_mod = 2;
                break;
            case DLR:
                x_Button = Flag.LEFT.i();
                y_Button = Flag.DOWN.i();
                x_mod = 2;  
                y_mod = 1;
                break;
            case DRL:
                x_Button = Flag.RIGHT.i();
                y_Button = Flag.DOWN.i();
                x_mod = 2;  
                y_mod = 1;
                break;
            case DRR:
                x_Button = Flag.RIGHT.i();
                y_Button = Flag.DOWN.i();
                x_mod = 1;  
                y_mod = 2;
                break;    
        }
    }

    @Override
    public void update() {
        
        for(Flag f : Flag.values()){
            flags.add( f.i(), Boolean.FALSE);
        }
          
        if( ((x_mod%counter) == 0) && !( x_Button == 99 ) ){
           flags.set(x_Button, Boolean.TRUE); 
        }
        if( ((y_mod%counter) == 0) && !( y_Button == 99 ) ){
            flags.set(y_Button, Boolean.TRUE);
        }
           
        counter++;
        if( counter > duration ) {
            removalFlag = true;
        }
        
    }

}
