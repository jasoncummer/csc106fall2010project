/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package csc106project;

/**
 *
 * @author jcummer
 */

//pointer to 100m likly

public class Creature {

    byte[] genome2 = new byte[10];
    Genome genome;
    int str;
    int spd;
    int smell;
    int	gender;

	int direction


    //constructor
    public Creature(int str2,int spd2, int smell2, int gender2)
    {

		this.str    = str2;
		this.spd    = spd2;
		this.smell  = smell2;
		this.gender = gender2;


		//orByte(genome[1],gender);//first bit of genome
		printGenome(genome);


    }

    public Creature (byte[] bin)
    {
		genome = new Genome(bin);
		genome.readGenome(this);
	}

   // create gemome(){}

	public void AI()
	{
		//NEED TO INTERPRET THE AI



		smell(1);
		move()
	}


   // Direction Grid
   // 	701
   // 	6x2
   // 	543
	public int smell(int address){
	   //will return a cell from the global array
	   int i;
	   int direction;

	   strongestSmell = cell[0];
	   direction=0;

	   for (i =0 i<=8 ; i++)
		{
			if( cell[i] > strongestSmell )
			{
				stringestSmell = cell[i];
				direction = i
			}
		}
	   return (direction);// the direction you wnat to move
	}

	public void move(int direction)
	{
		//update location
		direction*spd*terrainMod//*
	}


   public void printGenome(byte[] g)
   	{
		int i;

		for( i =0 ; i <10 ; i++ ){
	  		System.out.println(g[i]);
		}
	}

	public String cnvByteToBin()
	{
		return ("");
	}

	public static byte orByte(byte b,int traitValue)
	{

	        b |= 1;

	             System.out.println(b);
	        return b;
    }




}


//autotroph, ominovor, preditor, herbavor, scavenger

//class Genome{

	//if  list of bytes need search strings for traits

	//if 2d array one of the first elements can be triat identifier


	//static byte minus = 1;

	//interpretGenome(){

	//}


//}

//
// creature has:
//
//2 pointer
///world obj
//level 3 object
//	public level5 getLevel5(int x,int y)//on a meter scale
//		ll creatures,plants, objects,water,
// L3 obj has get level 4,5

//world obj
//get level i
//
//l3 has get level 4,5