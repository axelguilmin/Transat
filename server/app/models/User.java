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
    public boolean isAdmin;
    
    public User(String pseudo, String password, String email) 
    {
    	this.pseudo = pseudo;	
        this.email = email;
        this.password = password;        
    }
    
    public static boolean Connect(String email, String password)
    {
    	return true;    
    } 
}