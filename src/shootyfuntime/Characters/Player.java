/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shootyfuntime.Characters;


import static java.lang.Math.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import shootyfuntime.Flag;

/**
 *
 * @author Wpower
 */
public class Player {
    
    protected float loc_x, loc_y, radius, target_x, target_y ;
    protected double vel_x, vel_y;
    protected double acl_x, acl_y;
    protected double friction, max_vel, gravity;
    
    protected int health, maxHealth;
    protected boolean shotFlag;
    
    protected Color bulletColor;
    protected int bulletSize;
    
    ArrayList<Boolean> flags;

    private double maxshots;
    ArrayList<Projectile> bullets;

    /**
     * Constructor for Player class
     * @author WKP
     */
    public Player(){
        
        maxHealth = 10;
        health = 10;
        shotFlag = false;
        
        loc_x = 50;
        loc_y = 50;
        
        acl_x = 0.005;
        acl_y = 0.005;
        
        friction = 0.00125;
        
        max_vel = 0.8;
        
        radius = 30;
        
        bulletSize = 5;
        bullets = new ArrayList<>();
        bulletColor = Color.blue;
        
        flags = new ArrayList<>();
        
        //Set all the movement flags to false.
        for(Flag f : Flag.values()){
            flags.add( f.i(), Boolean.FALSE);
        }
        
    }
    
    /**
     * Grabs input from a GameContainer and updates player flags
     * @param c
     */
    public void getInput( GameContainer c ){
        Input in = c.getInput();
         
        flags.set(Flag.UP.i(), in.isKeyDown(Input.KEY_W) );
        flags.set(Flag.DOWN.i(), in.isKeyDown(Input.KEY_S) );
        flags.set(Flag.LEFT.i(), in.isKeyDown(Input.KEY_A) );
        flags.set(Flag.RIGHT.i(), in.isKeyDown(Input.KEY_D) );
        
        flags.set(Flag.SHOOT.i(), in.isMousePressed(Input.MOUSE_LEFT_BUTTON) );
        
        
        target_x    = in.getAbsoluteMouseX();
        target_y    = in.getAbsoluteMouseY();
    }
    
    /**
     * Updates player location based on movement flags
     * @param c
     */
    public void update( GameContainer c ){
        
        double scale = 0.7071067; 
        
        //If a direction key is down, update velocities
        //      - Before you change the velocity, check against max_vel
        
        if ( flags.get(Flag.UP.i()) ){
            if( flags.get(Flag.LEFT.i()) ){
                if ( vel_y > (-1*max_vel) ){ vel_y -= scale*acl_y; } 
                if ( vel_x > (-1*max_vel) ){ vel_x -= scale*acl_x; }
            } else if( flags.get(Flag.RIGHT.i()) ){
                if ( vel_y > (-1*max_vel) ){ vel_y -= scale*acl_y; } 
                if ( vel_x < (max_vel) )   { vel_x += scale*acl_x; }
            } else {
                if ( vel_y > (-1*max_vel) ){ vel_y -= acl_y; }         
            }
        }
        
        if ( flags.get(Flag.DOWN.i()) ){
            if( flags.get(Flag.LEFT.i()) ){
                if ( vel_y < (max_vel) )   { vel_y += scale*acl_y; } 
                if ( vel_x > (-1*max_vel) ){ vel_x -= scale*acl_x; }
            } else if( flags.get(Flag.RIGHT.i()) ) {
                if ( vel_y < (max_vel) )   { vel_y += scale*acl_y; } 
                if ( vel_x < (max_vel) )   { vel_x += scale*acl_x; }
            } else {
                if ( vel_y < max_vel ){ vel_y += acl_y; }
            }
        }

        if ( flags.get(Flag.LEFT.i()) && !flags.get(Flag.UP.i()) && !flags.get(Flag.DOWN.i()) ){
            if ( vel_x > (-1*max_vel) ){ vel_x -= acl_x; }
        }        
        if ( flags.get(Flag.RIGHT.i()) && !flags.get(Flag.UP.i()) && !flags.get(Flag.DOWN.i()) ){
            if ( vel_x < max_vel ){ vel_x += acl_x; }
        }
        
        //Apply Friction
        if( vel_y > 0 ){ vel_y -= friction; }
        if( vel_y < 0 ){ vel_y += friction; }
        if( vel_x > 0 ){ vel_x -= friction; }
        if( vel_x < 0 ){ vel_x += friction; }
        
        //Finally, update position based on velocity.  Only do so if the resultant location is inside the screen.
        if( (loc_x + vel_x > (c.getWidth() - radius)) || ( loc_x + vel_x < 0 ) ){
            vel_x = 0;
        } else {
            loc_x += vel_x;
        }
        if( (loc_y + vel_y > (c.getHeight() - radius )) || ( loc_y + vel_y < 0 ) ){
            vel_y = 0;
        } else {
            loc_y += vel_y;
        }

        //If the mouse button was clicked, add a new projectile to bullets
        if( flags.get(Flag.SHOOT.i()) ){
            
            bullets.add(new Projectile( loc_x+(radius/2), loc_y+(radius/2), target_x, target_y, 10, bulletColor, bulletSize ));
            
        }
        
        //If there are Projectiles in the bullets list, update um.
        if( !bullets.isEmpty() ){
            for( int i = 0; i < bullets.size(); i++ ){
                bullets.get(i).update( c );
                
                //if the bullet is flagged for removal, delete it from bullets
                if( bullets.get(i).needDelete() ){ bullets.remove(i); }
            }
        }
        
        //Lastly, reset all flags to false 
        for(Flag f : Flag.values()){
            flags.set( f.i(), Boolean.FALSE);
        }
        
    }
    
    /**
     * Draws the player to the screen
     * @param g 
     */
    public void draw( Graphics g ){
        
        //Loc_x/y are of the top left corner of the square around the circle
        
        //Draw the players circle
        //g.drawRoundRect(loc_x , loc_y, 10, 10, 2);
        g.setColor( Color.lightGray );
        g.fillOval(loc_x, loc_y, radius, radius);
        
        //Draw 'Health Bar'
        g.setColor( bulletColor );
        
        float healthRadius = radius*( (float)health/(float)maxHealth );
        g.fillOval((loc_x+(radius/2))-(healthRadius/2), (loc_y+(radius/2))-(healthRadius/2), healthRadius, healthRadius);
        
        g.setColor( Color.darkGray );
        g.setLineWidth(2);
        g.drawOval(loc_x, loc_y, radius, radius);
        
        
        
        //If there are Projectiles in the bullets list, draw um.
        if( !bullets.isEmpty() ){
            for( int i = 0; i < bullets.size(); i++ ){
                bullets.get(i).draw( g );
            }
        }
        
    }
    
    /**
     * Checks the bulletlist of the current player against the supplied player. 
     * If there is a collision, the provided player is told it was hit.
     * @param p
     */
    public void checkShotCollisions( Player p ){
        //Looks at Player p and checks if any of its shots have hit it.
        //If it is, tells the Player it was hit. 
        if( !bullets.isEmpty() ){
            for( int i = 0; i < bullets.size(); i++ ){
                
                double dx = ( (p.getX() + ((float)radius/2) ) - ( bullets.get(i).getX() + ((float)bullets.get(i).getRad())/2) );
                double dy = ( (p.getY() + ((float)radius/2) ) - ( bullets.get(i).getY() + ((float)bullets.get(i).getRad())/2) );
                double dist = sqrt( pow( dx, 2 ) + pow( dy, 2 )  );
                double sum = bulletSize/2 + radius/2;
                
                if( dist < sum ){   //Bullet has hit!
                    p.getShot();        //Tell the player
                    bullets.remove(i);  //Remove the bullet
                }
                
            }  
        }
        
        
    }
    
    /**
     * Called when the player is collided with a bullet.  
     */
    public void getShot(){
        health--;
    }
    
    /**
     * Returns the players current health
     * @return Player Health (int)
     */
    public int checkHealth(){ return health; }
    
    /**
     * Returns players x loc
     * @return X_Loc (float)
     */
    public float getX(){
        return loc_x;
    }

    /**
     * Returns players y loc
     * @return Y_Loc (float)
     */
    public float getY(){
        return loc_y;
    }
    
    
}
