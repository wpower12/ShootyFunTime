/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shootyfuntime.Characters;

import static java.lang.Math.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;


/**
 *
 * @author Wpower
 */
public class Projectile {
    
    private float loc_x, loc_y;
    private double vel_x, vel_y, rad_i, h, vel_i;
    private boolean deleteFlag;
    private Color bulletColor;
    private int bulletSize;
    
    /**
     * Constructor for the Projectile Class
     * 
     * @param origin_x
     * @param origin_y
     * @param target_x
     * @param target_y
     * @param offset
     * @param c
     * @param b
     */
    public Projectile( float origin_x, float origin_y, float target_x, float target_y, double offset, Color c, int b ){
        
        //Read in parameters
        bulletColor = c;
        bulletSize = b;
        
        //calculate initial location
        rad_i = offset;
        vel_i = 1.0;
        h = sqrt( pow(( target_x - origin_x ), 2) + pow(( target_y - origin_y ),2) );
        
        loc_x =   (float) (rad_i*( ( target_x - origin_x )/h ) + origin_x);
        loc_y =   (float) (rad_i*( ( target_y - origin_y )/h ) + origin_y);
        
        //calculate velocities
        vel_x =  (float) (vel_i*( ( target_x - origin_x )/h ));
        vel_y =  (float) (vel_i*( ( target_y - origin_y )/h ));
        
        //Init stuff
        deleteFlag = false;
    }
    
    /**
     * Updates the projectiles location based on its speed/current loc
     * @param c
     */
    public void update( GameContainer c ){
        
        //Calculate New loc_x, loc_y
        loc_x += vel_x;
        loc_y += vel_y;
        
        //Check if out of bounds, if it is, raise delete flag
        if( (loc_x > c.getWidth())  || ( loc_x < 0 )  ){ deleteFlag = true; }
        if( (loc_y > c.getHeight()) || ( loc_y < 0 )  ){ deleteFlag = true; }
        
    }
    
    /**
     * Draws the bullet to the graphics object
     * @param g
     */
    public void draw( Graphics g ){
        
        g.setColor(bulletColor);
        g.fillOval(loc_x, loc_y, 5, 5);
        
    }
    
    /**
     * Checks if the bullet needs to be removed from its list
     * @return Delete Flag (bool)
     */
    public boolean needDelete(){
        
        return deleteFlag;
        
    }
    
    public double getX(){ return loc_x; }
    public double getY(){ return loc_y; }
    public int getRad(){ return bulletSize;}
    
}
