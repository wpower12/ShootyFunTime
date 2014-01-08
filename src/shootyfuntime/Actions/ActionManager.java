/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shootyfuntime.Actions;

import shootyfuntime.Actions.Action;
import java.util.ArrayList;

/**
 *
 * @author Wpower
 */
public class ActionManager {
    
    protected ArrayList<Action> actionList; 
    
    protected enum Flag {
        UP (0), DOWN (1), LEFT (2), RIGHT (3), SHOOT (4);
        
        private int i;
        Flag( int j ){ i = j; }
        
        int i(){ return i; }
    }
    
    public ActionManager() {
        
        actionList = new ArrayList<>();
        
    }
    
    public void updateFlags( ArrayList<Boolean> flaglist ){
        if( !actionList.isEmpty() ) {
            for( Flag f : Flag.values()  ){
                flaglist.set(f.i(), actionList.get( 0 ).getFlag( f.i() ) );
            }
        }
    }
    
    public Boolean getFlag( int i ){
        
        return actionList.get(0).getFlag(i);
        
    }
    
    public boolean isEmpty(){
        
        return actionList.isEmpty();
       
    } 
    
    public void addAction( Action a ){
        
        actionList.add(a);
        
    }
    
    public void pushAction( Action a ){
        
        actionList.add(0, a);
        
    }
    
    public void updateActions(){
        
        if( !actionList.isEmpty() ){
            actionList.get(0).update();
            if( actionList.get(0).checkRemoval() ){
                actionList.remove(0);
            }
            
        }
    }
    
    public int currentActionType(){
        if( !actionList.isEmpty() ){
            return actionList.get(0).getType();
        } else {
            return 0;
        }
    }
}
