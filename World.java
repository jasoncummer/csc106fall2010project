public class World // Level0
{
	Level1[][] map;

	public World (int xSize, int ySize, int[][] coord)
	{
		map = new Level1[ySize][xSize];

		for (int i = 0; i < coord.length; i++)
		{
			int x = coord[i][0];
			int y = coord[i][1];

			if (map[y/10000][x/10000] == null)
			{
				map[y/10000][x/10000] = new Level1();
			}
			map[y/10000][x/10000].genCell(digit(1,x), digit(1,y));
		}
	}

	public static int digit (int d, int n) // d in [0,4]
	{
		int num = n/(int)Math.pow(10, 4-d);
		if (d != 0)
			num %= 10;
		return num;
	}
}

class Level1 //10km level
{
	Level2[][] map;

	public Level1()
	{
		map = new Level2[10][10];
	}

	public void genCell (int x, int y)
	{
		if (map[y][x] == null)
			map[y][x] = new Level2();
	}
}

class Level2 //1km level
{
	Level3[][] map;

	public Level2()
	{
		map = new Level3[10][10];
	}
}

class Level3 //100m level
{
	Level4[][] map;

	public Level3()
	{
		map = new Level4[10][10];
	}
}

class Level4 //10m level
{
	Level5[][] map;

	public Level4()
	{
		map = new Level5[10][10];
	}
}

class Level5 //1m level
{
}