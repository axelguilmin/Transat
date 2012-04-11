/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transat.client.applet;

import com.transat.client.entities.User;

/**
 *
 * @author gaetan
 */
public class App {
    
    //
    //  STATIC
    //
    private static App instance = null;
    public static App getInstance()
    {
        if( instance == null )
        {
            instance = new App();
        }
        return instance;
    }
    
    
    //
    //  ATTRIBUTES
    //
    public User user = null;
    
    
    
    
    //
    //  CONSTRUCTOR
    //  
    public App()
    {
    
    }
    
    
    
    //
    //  METHODS
    //
    
    
    
    
    
}
