package controllers;

import play.*;
import play.mvc.*;
import play.test.Fixtures; // TODO : Delete this !

import java.util.*;

import models.*; 

public class World extends Controller 
{

    public static void Read(short x, short y, short width, short height) 
    {
    	List<models.Tile> map = models.Tile.find("select t from Tile t where x between ? and ? and y between ? and ?",x,(short)(x+width),y,(short)(y+height)).fetch(1000);
    	renderJSON(map.toArray());
    }
    
    public static void loadData()
    {
    	Fixtures.deleteAll();
    	Fixtures.loadModels("../test/map.yml");
    	response.status = 204;
    	renderJSON("Data loaded");
    }

}