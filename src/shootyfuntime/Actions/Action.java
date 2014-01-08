/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shootyfuntime.Actions;

import java.util.ArrayList;
import shootyfuntime.Flag;

/**
 *
 * @author Wpower
 */
public class Action {

    //Movement Flag List
    protected ArrayList<Boolean> flags;
    
    //AM Variables
    protected boolean removalFlag;    //When true, tells AM to remove action
    protected int actionType;         //integer ID to distinguish actions from each other
    
    public Action(){
        //Init Flag List, Set all to FALSE
        flags = new ArrayList<>();
        for(Flag f : Flag.values()){
            flags.add( f.i(), Boolean.FALSE);
        }
        
        //Start with removalFlag false
        removalFlag = false;
        
        //Generic actionType is 0;
        actionType = 0;
    }
    
    public Boolean getFlag( int i ){
        return flags.get(i);
    }
    
    public boolean checkRemoval(){
        return removalFlag;
    };
    
    public void update(){};
    
    public int getType(){
        return actionType;
    };
    
}
