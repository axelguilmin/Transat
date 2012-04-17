package controllers;

import play.*;
import play.data.validation.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class User extends Controller 
{   
	@Before(unless="create")
	private static void authenticate(@Required @MinSize(2) String pseudo, @Required String password) 
	{
	    if(validation.hasErrors() || !models.User.goodPassword(pseudo, password))
	    {
	 	   response.status = 401;
	 	   renderJSON("Unauthorized");    	   
	    }
	 }

    public static void read(String pseudo, String password) 
    {
    	renderJSON(models.User.findByPseudo(pseudo));
    }
    
    public static void create(String pseudo, String password, String email)
    {
    	models.User user = new models.User(pseudo, password, email);
    	validation.valid(user);
    	
		// Vérifier que le pseudo n'est pas déja utilisé
		if(models.User.pseudoAlreadyExists(pseudo))
			validation.addError("pseudo", "already exists",pseudo);
		
		// Vérifier que l'email n'est pas déja utilisé
		if(models.User.emailAlreadyExists(email))
			validation.addError("email", "already exists",email);
		
    	if(validation.hasErrors())
    	{
    		response.status = 400;
    		renderJSON(validation.errors().get(0));
    	}
    	else
    	{
        	user.validateAndCreate();
        	user.validateAndSave();
        	response.status = 201; //Created
    		renderJSON(user);
    	}
    }

    public static void update(String pseudo, String password, String newPseudo, String newPassword, String newEmail)
    {   
    	models.User user = models.User.findByPseudo(pseudo);

    	validation.required(newPassword);
    	validation.required(newPseudo);
    	validation.required(newEmail);
    	validation.email(newEmail);

    	if(newEmail != user.email && models.User.emailAlreadyExists(newEmail))   		
  			validation.addError("pseudo", "already exists",newEmail);
    	
    	if(newPseudo != user.pseudo && models.User.pseudoAlreadyExists(newPseudo))
			validation.addError("pseudo", "already exists",newPseudo);   		

    	if(validation.hasErrors())
    	{
    		response.status = 400;
    		renderJSON(validation.errors().get(0));
    	}
    	else
    	{    		
    		user.email = newEmail;
    		user.password = newPassword;
    		user.pseudo = newPseudo;
        	user.validateAndCreate();
        	user.validateAndSave();
    		renderJSON(user);
    	}
    }

    public static void delete(String pseudo, String password)
    {   
		models.User.findByPseudo(pseudo).delete();
		response.status = 204; //No Content
		renderJSON("User deleted");
    }
}
