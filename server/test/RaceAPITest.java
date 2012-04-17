import org.junit.*;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;

public class RaceAPITest extends FunctionalTest 
{
	public void setUp( )
	{
		// TODO : Vider et regenerer la base de données
	}
	
	public void tearDown( )
	{
		
	}
    
    @Test
    public void testRace() 
    {
    	// Assume that there is somes races in the database
//    	models.Race test1 =  new Race("test1",null);
//    	test1.save();
//    	models.Race test2 =  new Race("test2",null);
//    	test2.save();
//    	models.Race test3 =  new Race("test3",null);
//    	test3.save();
//    	// If I want to read them I get all the races created
//    	Response getall = GET("/race/getall/");
//		assertStatus(200, getall);
//		assertContentType("application/json", getall);
//		assertCharset(play.Play.defaultWebEncoding, getall);
    	// I want to participate to one of them
    	// Then my participation is memorized
    }    
}
