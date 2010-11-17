public class Genome
{
	static final byte Minus = 0;
	static final byte Plus = 1;
	static final byte Divide = 2;
	static final byte Multiply = 3;

	static final byte LengthFlag = 6; //6 bits, so Max Legnth = 64
	static final byte TypeFlag = 4; //4 bits, so Max Types = 16
	static final int NumTypes = (int)Math.pow(2, TypeFlag);

	static final byte[] Masks = {1 << 0, 1 << 1, 1 << 2, 1 << 3, 1 << 4, 1 << 5, 1 << 6, (byte)(1 << 7)};


	byte[] genome;

	public static void main (String[] args)
	{
		Genome g = Genome.randomGenome();
		System.out.println(g.genome.length);
		for (int i = 0; i < g.genome.length; i++)
		{
			System.out.print(toBinary(g.genome[i]));
		}
		System.out.println();
		g.readGenome();
	}

	public Genome (byte[] bin)
	{
		genome = bin;
	}

	public void readGenome (Creature c)
	{
		int Strength = 0, Speed = 0, Smell = 0;
		boolean gender = (getBinary(0, 1) == 1);
		int ID = getBinary(1, 15);

		for (int i = 16; i < genome.length*8 - LengthFlag - TypeFlag;)
		{
			int len = getBinary(i, LengthFlag);
			i += LengthFlag;
			int type = getBinary(i, TypeFlag);
			i += TypeFlag;

			int n = (len - (len-1)%6+1)/6;
			double value = 0;

			if (n != 0)
			{
				value = getBinary(i, 4);
				i += 4;
			}
			else
			{
				value = getBinary(i, len);
				i += len;
			}
			value++;

			for (int j = 0; j < n-1; j++)
			{
				int op = getBinary(i, 2);
				i += 2;
				int num = getBinary(i, 4);
				num++;
				i += 4;

				switch (op)
				{
					case Minus: value -= num; break;
					case Plus: value += num; break;
					case Divide: value /= num; break;
					case Multiply: value *= num; break;
				}
			}

			int op = getBinary(i, 2);
			i += 2;
			int num = getBinary(i, len-6*n);
			num++;
			i += len-6*n;

			switch (op)
			{
				case Minus: value -= num; break;
				case Plus: value += num; break;
				case Divide: value /= num; break;
				case Multiply: value *= num; break;
			}

			int result = (int)value;
			if (result < 0)
				result = 0;

			System.out.println("Type = "+type+", value = "+result);

			// Have Result and Type, to place into the Creature.
			switch (type)
			{
				case 1: Strength += result; break;
				case 2: Speed += result; break;
				case 3: Smell += result; break;
			}
		}
		System.out.println("isMale? = "+gender);
		System.out.println("Strength = "+Strength);
		System.out.println("Speed = "+Speed);
		System.out.println("Smell = "+Smell);
	}

	public int getBinary (int index, int length)
	{
		return getBinary(index, length, genome);
	}

	public static int getBinary (int index, int length,
										byte[] bin) // Length must be <= 32
	{
		int r = 0;
		int offset = index % 8;
		for (int i = 0; i < length; i++)
		{
			r = r << 1;
			if ((bin[(index+i)/8] & Masks[7 - (i + offset)%8]) ==
												Masks[7 - (i + offset)%8])
			{
				r += 1;
			}
		}
		return r;
	}

	public void setBinary (int value, int index, int length)
	{
		setBinary(value, index, length, genome);
	}

	public static void setBinary (int value, int index, int length,
										byte[] bin) // Length must be <= 32
	{
		//System.out.println("Binary: "+toBinary((byte)value));
		int offset = index % 8;
		for (int i = 0; i < length; i++)
		{
			//System.out.println("i = " + i);
			if ((value & (1 << (length - i - 1))) == (1 << (length - i - 1)))
			{
				//System.out.println(toBinary(bin[(index+i)/8]));
				//System.out.println(toBinary(Masks[7 - (i + offset)%8]));
				bin[(index+i)/8] = (byte)(bin[(index+i)/8] | Masks[7 - (i + offset)%8]);
				//System.out.println(toBinary(bin[(index+i)/8]));
				//System.out.println();
			}
			else
			{
				//System.out.println(toBinary(bin[(index+i)/8]));
				//System.out.println(toBinary((byte)~Masks[7 - (i + offset)%8]));
				bin[(index+i)/8] = (byte)(bin[(index+i)/8] & ~Masks[7 - (i + offset)%8]);
				//System.out.println(toBinary(bin[(index+i)/8]));
				//System.out.println();
			}
		}
	}

	public static Genome randomGenome()
	{
		byte[] bin;

		int numGenes = 12;
		int[] lenGenes = new int[numGenes];
		int sum = 16;

		for (int i = 0; i < numGenes; i++)
		{
			lenGenes[i] = (int)(Math.random()*20) + 12;
			sum += lenGenes[i] + LengthFlag + TypeFlag;
		}
		int len = sum/8;
		if (sum%8 != 0)
			len++;
		bin = new byte[len];

		int index = 0;
		setBinary((int)(Math.random()*2), index, 1, bin);
		index += 1;
		setBinary(0, index, 15, bin);
		index += 15;

		for (int i = 0; i < numGenes; i++)
		{
			setBinary(lenGenes[i], index, LengthFlag, bin);
			index += LengthFlag;
			setBinary((int)(Math.random()*NumTypes), index, TypeFlag, bin);
			index += TypeFlag;

			for (int j = 0; j < lenGenes[i]; j++)
			{
				setBinary((int)(Math.random()*2), index, 1, bin);
				index += 1;
			}
		}

		return new Genome(bin);
	}

	public static String toBinary (byte b, int size)
	{
		String s = "";
		for (int i = 8 - size; i < 8; i++)
		{
			if ((byte)(b & Masks[7-i]) == Masks[7-i])
				s += "1";
			else
				s += "0";
		}
		return s;
	}

	public static String toBinary (byte b)
	{
		return toBinary(b, 8);
	}
}