/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package csc106;

public class Genome
{
	static final byte Minus = 0;
	static final byte Plus = 1;
	static final byte Divide = 2;
	static final byte Multiply = 3;

	static final byte LengthFlag = 6; //6 bits, so Max Legnth = 64
	static final byte TypeFlag = 4; //4 bits, so Max Types = 16

	static final byte[] Masks = {1 << 0, 1 << 1, 1 << 2, 1 << 3, 1 << 4, 1 << 5, 1 << 6, (byte)(1 << 7)};


	byte[] genome;

	public static void main (String[] args)
	{
		/*byte[] bin = {(byte)0xFF, (byte)0xA1, (byte)0x02, (byte)0x93};
		for (int i = 0; i < bin.length; i++)
		{
			System.out.print(toBinary(bin[i]));
		}
		System.out.println();

		System.out.print(toBinary((byte)getBinary(0, 5, bin), 5));
		System.out.print(toBinary((byte)getBinary(5, 4, bin), 4));
		System.out.print(toBinary((byte)getBinary(9, 8, bin), 8));
		System.out.print(toBinary((byte)getBinary(17, 3, bin), 3));
		System.out.print(toBinary((byte)getBinary(20, 7, bin), 7));
		System.out.print(toBinary((byte)getBinary(27, 5, bin), 5));

		System.out.println();*/
	}

	public Genome (byte[] bin)
	{
		genome = bin;
	}

	public int[] readGenome (Creature c)
	{
            int[] atributes = new int[3];
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

			for (int j = 0; j < n-1; j++)
			{
				int op = getBinary(i, 2);
				i += 2;
				int num = getBinary(i, 4);
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
			i += len-6*n;

			switch (op)
			{
				case Minus: value -= num; break;
				case Plus: value += num; break;
				case Divide: value /= num; break;
				case Multiply: value *= num; break;
			}

			int result = (int)value;

			// Have Result and Type, to place into the Creature.
		}




                atributes[0] = Strength;
                atributes[1] = Speed ;
                atributes[2] = Smell;


                return (atributes);
	}

	public int getBinary (int index, int length)
	{
		return getBinary(index, length, genome);
	}

	public static int getBinary (int index, int length, byte[] bin) // Length must be <= 32
	{
		int r = 0;
		int offset = index % 8;
		for (int i = 0; i < length; i++)
		{
			r = r << 1;
			if ((bin[(index+i)/8] & Masks[7 - (i + offset)%8]) ==  Masks[7 - (i + offset)%8])
				r += 1;
		}
		return r;
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

