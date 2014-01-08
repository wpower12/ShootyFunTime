/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shootyfuntime;

/**
 *
 * @author Wpower
 */
public enum Flag {
        UP (0), DOWN (1), LEFT (2), RIGHT (3), SHOOT (4);
        
        public final int i;
        Flag( int j ){ i = j; }
        
        public int i(){ return i; }
}