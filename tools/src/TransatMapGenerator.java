import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * @author Axel Guilmin
 * Read pixel from a black and white pic and create a map
 */
public class TransatMapGenerator 
{
	// The image
	private BufferedImage bufferedImage;
	private int width;
	private int height;
	// The map[width][height]
	private ETile Tiles[][];

	private void load(String file)
	{
		try
		{
			// Load the jpeg file
			File inputFile = new File(file);
			this.bufferedImage = ImageIO.read(inputFile);
			this.width = this.bufferedImage.getWidth();
			this.height = this.bufferedImage.getHeight(null);
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	private void toTiles()
	{
		//Get Pixels
		int [] rgbs = new int[this.width*this.height];
		this.Tiles = new ETile[this.width][this.height];
		//Get all pixels
		this.bufferedImage.getRGB(0, 0, this.width, this.height, rgbs, 0, width); 
		// If each pixel water or ground ?
		// We want to be greedy on ground here, because the second pass will minimize the amout of ground pixels
		// if the pixel is more than 10% grey it's considered as ground
		// rgbs[i] is -(256^2 * (256-red) + 256 * (256-green) + (256-blue))
		for(int i=0;i<this.width*this.height;i++)
		{
			// blue = (256^3 + rgbs[i]) % 256
			// ground is  blue < (256 * 0.9)
			if(((16777216 + rgbs[i]) % 256) < 230)
				this.Tiles[i%this.width][i/this.width] = ETile.ground;
			else
				this.Tiles[i%this.width][i/this.width] = ETile.water;
		}
	}
	private void round()
	{
		// Parcourir la map (sauf bordures)
		for(int i=0;i<this.height;i++)
		{
			for(int j=0;j<this.width;j++)
			{
				// Les cases ne contenant que de l'eau ne nous interessent pas
				if(this.Tiles[j][i]!=ETile.water)
				{
					// Pour chaque case de terre , trouver les cases qui l'entourent (on considere que la map est "ronde")
					ETile rightTo = this.Tiles[(j+1)%this.width][i];
					ETile leftTo = this.Tiles[((j-this.width)%this.width)+this.width-1][i];
					ETile upTo = this.Tiles[j][((i-this.height)%this.height)+this.height-1];
					ETile downTo = this.Tiles[j][(i+1)%this.height];
					// Dans certains cas on remplace par une tile moitié-terre moitié-eau
					// si deux coté, par exemple a droite et au dessus sont de l'eau, on met une case coupé en diagonale avec de l'eau en haut a droite
					if(leftTo!=ETile.water && upTo==ETile.water && rightTo==ETile.water && downTo!=ETile.water)
						this.Tiles[j][i] = ETile.bottom_left;
					else if(leftTo!=ETile.water && upTo!=ETile.water && rightTo==ETile.water && downTo==ETile.water)
						this.Tiles[j][i] = ETile.top_left;
					else if(leftTo==ETile.water && upTo!=ETile.water && rightTo!=ETile.water && downTo==ETile.water)
						this.Tiles[j][i] = ETile.top_right;		
					else if(leftTo==ETile.water && upTo==ETile.water && rightTo!=ETile.water && downTo!=ETile.water)
						this.Tiles[j][i] = ETile.bottom_right;
				}
			}		
		}
	}
	private void toASCII()
	{
		for(int i=0;i<this.height;i++)
		{
			for(int j=0;j<this.width;j++)
			{
				if(this.Tiles[j][i]==ETile.water)
					System.out.print("  ");
				else if(this.Tiles[j][i]==ETile.ground)
					System.out.print("##");
				else if(this.Tiles[j][i]==ETile.bottom_left)
					System.out.print("#\\");
				else if(this.Tiles[j][i]==ETile.bottom_right)
					System.out.print("/#");
				else if(this.Tiles[j][i]==ETile.top_left)
					System.out.print("#/");
				else if(this.Tiles[j][i]==ETile.top_right)
					System.out.print("\\#");			
			}
			System.out.println();
		}
	}
	private void toSQL()
	{
		// La case de coordonée 0;0 est en bas a gauche
		// Toutes les coordonées sont positives
		
		for(int i=0;i<this.height;i++)
		{
			for(int j=0;j<this.width;j++)
			{			
				System.out.format("INSERT INTO Tile(x,y,t,d,f) VALUES(%d,%d,%d,0,0);\n",j,this.height-1-i,this.Tiles[j][i].ordinal());
			}
		}
	}
	private void toYAML()
	{
		// La case de coordonée 0;0 est en bas a gauche
		// Toutes les coordonées sont positives
		
		System.out.println("# Generated with TransatMapGenerator");
		System.out.println("# Size : " + this.width + "x" + this.height + ", start at 0,0");

		for(int i=0;i<this.height;i++)
		{
			for(int j=0;j<this.width;j++)
			{			
				System.out.format("Tile(%d,%d):\n   x: %d\n   y: %d\n   t: %d\n   d: 0\n   f: 0\n", j, this.height-1-i, j, this.height-1-i , this.Tiles[j][i].ordinal());
			}
		}
	}
	/**
	 * @param args
	 * usage java TransatMapGenerator filePath [mode] [verbose]
	 * [mode] : "sql" or "ascii"
	 * the file should be a jpeg pic in black and white, it's that the world is a globe 
	 */
	public static void main(String[] args) 
	{
		String file = args[0] ;
		boolean toSQL = args[1].compareTo("sql") == 0;
		boolean toASCII = args[1].compareTo("ascii") == 0;
		boolean toYAML = args[1].compareTo("yaml") == 0;
		boolean verbose = args.length > 2 && args[2].compareTo("verbose") == 0;
		
		if(verbose) System.out.println("Hi, master");
		TransatMapGenerator map = new TransatMapGenerator();
		map.load(file);
		if(verbose) System.out.println("I loaded the file " + file);
		map.toTiles();
		if(verbose) System.out.println("I converted the files into Tiles");
		map.round();
		if(verbose) System.out.println("I rounded the angles ;)");
		if(toSQL)
			map.toSQL();
		else if(toASCII)
			map.toASCII();
		else if(toYAML)
			map.toYAML();

	}
}