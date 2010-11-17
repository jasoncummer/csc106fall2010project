import java.awt.*;

public class World // Level0
{
	static final int L = 0;
	Level1[][] map;

	public World (int xSize, int ySize, int[][] coord)
	{
		map = new Level1[ySize][xSize];

		for (int i = 0; i < coord.length; i++)
		{
			int x = coord[i][0];
			int y = coord[i][1];

			if (map[digit(L,y)][digit(L,x)] == null)
			{
				map[digit(L,y)][digit(L,x)] = new Level1();
			}
			map[digit(L,y)][digit(L,x)].genCell(x, y);
		}
	}

	public void print()
	{
		for (int i = map.length-1; i >= 0; i--)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				if (map[i][j] != null)
					map[i][j].print();
			}
		}
	}

	public void paint(Graphics2D buf)
	{
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				if (map[i][j] != null)
					map[i][j].paint(i, j, buf);
			}
		}
	}

	public static int digit (int d, int n) // d in [0,4]
	{
		int num = n/(int)Math.pow(10, 4-d);
		if (d != 0)
			num %= 10;
		return num;
	}

	public static void main (String[] args)
	{
		int[][] coords = {{200, 100}, {200, 200}, {300, 200}, {1300, 1200}};
		World world = new World(1, 1, coords);
		world.print();
	}
}

class Level1 //10km level
{
	static final int L = 1;
	Level2[][] map;

	public Level1()
	{
		map = new Level2[10][10];
	}

	public void genCell (int x, int y)
	{
		if (map[World.digit(L,y)][World.digit(L,x)] == null)
		{
			map[World.digit(L,y)][World.digit(L,x)] = new Level2();
		}
		map[World.digit(L,y)][World.digit(L,x)].genCell(x, y);
	}

	public void print()
	{
		for (int i = map.length-1; i >= 0; i--)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				if (map[i][j] != null)
					map[i][j].print();
			}
		}
	}

	public void paint(int y, int x, Graphics2D buf)
	{
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				if (map[i][j] != null)
					map[i][j].paint(10*y+i, 10*x+j, buf);
			}
		}
	}
}

class Level2 //1km level
{
	static final int L = 2;
	Level3[][] map;

	public Level2()
	{
		map = new Level3[10][10];
	}

	public void genCell (int x, int y)
	{
		if (map[World.digit(L,y)][World.digit(L,x)] == null)
		{
			map[World.digit(L,y)][World.digit(L,x)] = new Level3();
		}
	}

	public void print()
	{
		for (int i = map.length-1; i >= 0; i--)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				if (map[i][j] != null)
					System.out.print(1);
				else
					System.out.print(0);
			}
			System.out.println();
		}
	}

	public void paint(int y, int x, Graphics2D buf)
	{
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				if (map[i][j] != null)
				{
					buf.fillRect(5*(10*x+j), 495-5*(10*y+i), 5, 5);
				}
			}
		}
	}
}

class Level3 //100m level
{
	static final int L = 3;
	Level4[][] map;

	public Level3()
	{
		map = new Level4[10][10];
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				map[i][j] = new Level4();
			}
		}
	}

	public Level5 getLevel5 (int x, int y)
	{
		return null;
	}
}

class Level4 //10m level
{
	static final int L = 4;
	Level5[][] map;

	public Level4()
	{
		map = new Level5[10][10];
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				map[i][j] = new Level5();
			}
		}
	}
}

class Level5 //1m level
{
	static final int L = 5;
}