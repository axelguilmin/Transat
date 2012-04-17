package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.data.validation.*;
import play.db.jpa.*;
 
@Entity
public class User extends Model 
{ 
    @Required
    @MinSize(2)
	public String pseudo;
	@Required
	@Email    
	public String email;
	@Required
    public String password;    
    
    public User(String pseudo, String password, String email) 
    {
    	this.pseudo = pseudo;	
        this.email = email;
        this.password = password;        
    }
    
    public static boolean goodPassword(String pseudo, String password) 
    {
        models.User user = models.User.find("byPseudo", pseudo).first();
        return user != null && user.password.equals(password);
    }
    
    public static boolean pseudoAlreadyExists(String pseudo)
    {
    	//	Vérifier que le pseudo n'est pas déja utilisé
    	return models.User.find("pseudo = ? ",pseudo).fetch().size() > 0;
    }
	
    public static boolean emailAlreadyExists(String email)
    {
		// Vérifier que l'email n'est pas déja utilisé
    	return models.User.find("email = ? ",email).fetch().size() > 0;
    }
    
    public static User findByPseudo(String pseudo)
    {
    	return User.find("select u from User u where u.pseudo = ?",pseudo).first();
    }
}