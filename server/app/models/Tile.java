package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.data.validation.*;
import play.db.jpa.*;
 
@Entity
public class Tile extends Model 
{ 
    @Required
    public Short x;
    @Required
    public Short y;
    @Required
	public Byte t; // Texture
    @Required
    public Short d; // windDirection
    @Required
    public Byte f; // windForce
    
	public Tile(Short x, Short y, Byte t, Short d, Byte f) 
	{
		this.x = x;
		this.y = y;
		this.t = t;
		this.d = d;
		this.f = f;
	}
  

}