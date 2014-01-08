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
public enum Direction {
        
        U (0), D(1), L (2), R (3), 
        UL (4), UR (5), DL (6), DR (7), 
        ULL (8), ULR (9), URL (10), URR (11),
        DLL (12), DLR (13), DRL (14), DRR (15);
        
        private int i;
        Direction( int j ){ i = j; }
        
        public int i(){ return i; }
}
