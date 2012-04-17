
package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*; 

public class Race extends Controller 
{
	//@With(User.class)
    public static void ReadAll() 
    {
    //	List<models.Race> races = models.Race.findAll();
    //	renderJSON(races.toArray());
    	renderJSON("Not implemented yet");
    }
    
    public static void Participate() 
    {
        renderJSON("Not implemented yet");
    }

}