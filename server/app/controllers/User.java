package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class User extends Controller {

    public static void read(String pseudo, String password) 
    {  	
		validation.required(pseudo);
		validation.required(password);
    	if(validation.hasErrors())
    	{
    		response.status = 400;
    		renderJSON(validation.errors().toArray());
    	}
    	else
    	{
    		renderJSON(models.User.find("select u from User u where u.pseudo = ? and u.password = ?",pseudo,password).first());
    	}
    }
    
    public static void create(String pseudo, String password, String email)
    {   	
		validation.required(pseudo);
		validation.required(password);
		validation.required(email);
		validation.email(email);
		validation.min(password.length(), 5);
		validation.min(pseudo.length(), 1);
		
		// Vérifier que le pseudo n'est pas déja utilisé
		if(models.User.find("pseudo = ? ",pseudo).fetch().size() > 0)
			validation.addError("pseudo", "already exists", pseudo);
		
		// Vérifier que l'email n'est pas déja utilisé
		if(models.User.find("email = ? ",email).fetch().size() > 0)
			validation.addError("email", "already exists", email);
		
    	if(validation.hasErrors())
    	{
    		response.status = 400;
    		renderJSON(validation.errors().toArray());
    	}
    	else
    	{
        	models.User user = new models.User(pseudo,password,email);
        	user.validateAndCreate();
        	user.validateAndSave();
        	//user.create();
        	//user.save(); 
    		renderJSON(user);
    	}
    }
    
    public static void delete(String pseudo, String password)
    {   	
		
    }
    
    public static void update(String pseudo, String password)
    {   	
		
    }
}