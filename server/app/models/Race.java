package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.data.validation.*;
import play.db.jpa.*;
 
@Entity
public class Race extends Model 
{ 
    @Required
    @MinSize(2)
	public String name;
	public String started;  
	
	public Race(String name, String started)
	{
		this.name = name;
		this.started = started;
	}
    
}