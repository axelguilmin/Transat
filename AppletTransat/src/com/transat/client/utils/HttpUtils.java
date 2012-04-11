/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transat.client.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author gaetan
 */
public class HttpUtils {
    
    //-----------------------------------------
    //  STATIC
    //-----------------------------------------
    private static HttpUtils instance = null;
    public static HttpUtils getInstance()
    {
        if( instance == null )
        {
            instance = new HttpUtils();
        }
        return instance;
    }
    
    
    
    //-----------------------------------------
    //  ATTRIBUTES
    //-----------------------------------------
    
    
    
    
    //-----------------------------------------
    //  CONSTRUCTOR
    //-----------------------------------------
    private HttpUtils()
    {
    
    }
    
    
    
    //-----------------------------------------
    //  METHODS
    //-----------------------------------------
    
    public HttpResponse sendRequestGet( String requestURL )
    {
        HttpResponse result = new HttpResponse();		
        //dataUrl = dataUrl.replace(" ", "%20");
        String dataUrl = requestURL;
        URL 		url 		= null;
        HttpURLConnection con           = null;
        Object 		content 	= null;
        String		response	= null;
        StringWriter    writer          = new StringWriter();

        try
        {
            url = new URL( dataUrl );
            con = (HttpURLConnection) url.openConnection();
            result.code = con.getResponseCode();
            
            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                        con.getInputStream()));
            String line="";
            try
            {
                while ( null!=(line = in.readLine()))
                {
                    writer.write(line); 
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            result.text = writer.toString();
            
            in.close();
        }
        catch (MalformedURLException e)
        {
            Log.v("[EXCEPTION] MalformedURLException");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            Log.v("[EXCEPTION] IOException");
            e.printStackTrace();
        }
        
        return result;
    }
    
    
    
    
    
    
    
    
    
    
    
}
