import org.junit.*;
import org.junit.Before;

import play.Logger;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;

public class UserAPITest extends FunctionalTest 
{
	@Before
	public void setUp( )
	{
		Fixtures.deleteDatabase();
		Fixtures.loadModels("user.yml");
	}
    
    @Test
    public void Create() 
    {
    	// Create an user
		Response create = GET("/user/create?pseudo=axel2&password=axel2&email=axel2@transat.com");
		assertStatus(201, create);
		assertContentType("application/json", create);
		assertCharset(play.Play.defaultWebEncoding, create);
		// Recreate should fail
		Response create2 = GET("/user/create?pseudo=axel2&password=axel2&email=axel2@transat.com");
		assertStatus(400, create2);
		assertContentType("application/json", create);
		assertCharset(play.Play.defaultWebEncoding, create);
    }
    @Test
    public void Read()
    {
		// Get it
		Response read = GET("/user/read?pseudo=axel&password=axel");
		assertIsOk(read);
		assertContentType("application/json", read);
		assertCharset(play.Play.defaultWebEncoding, read);
		// --> Should be the same the user
		assertContentMatch("axel",read);
		
    }
    @Test
    public void Update()
    {
		// Try to connect with a bad password
    	Response  read = GET("/user/read?pseudo=axel&password=BAD");
		assertStatus(401, read);
		assertContentType("application/json", read);
		assertCharset(play.Play.defaultWebEncoding, read);
		// --> 401 = Unauthorized
		assertStatus(401, read);
		assertEquals(read.out.toString(),"Unauthorized");
		// Change the pseudo
		Response update = GET("/user/update?pseudo=axel&password=axel&newPseudo=axel2&newPassword=axel&newEmail=lol@transat.com");
		assertIsOk(update);
		assertContentType("application/json", update);
		assertCharset(play.Play.defaultWebEncoding, update);
    }
    @Test
    public void Delete()
    {
		// bye bye Axel
		Response delete = GET("/user/delete?pseudo=axel&password=axel");
		assertStatus(204, delete);
		assertContentType("application/json", delete);
		assertCharset(play.Play.defaultWebEncoding, delete);		
    }    
}