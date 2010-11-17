//package csc106;


import java.util.Random;

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
//                Genome g = Genome.randomGenome();
//                System.out.println(g.genome.length);
//                for (int i = 0; i < g.genome.length; i++)
//                {
//                        System.out.print(toBinary(g.genome[i]));
//                }
//                System.out.println();
                //g.readGenome();

                Genome g1 = Genome.randomGenome();
                Creature cF = new Creature(g1);
               // g1.readGenome();


                System.out.println();

                Genome g2 = Genome.randomGenome();
                Creature cM = new Creature(g2);
                //g2.readGenome();

                System.out.println();
                
                recombination(g1.genome,g2.genome);

        }

        public Genome (byte[] bin)
        {
                genome = bin;
        }



    // purpose      : To take a male and female genome and preform homologus
    //                    recombination on them.
    // parameters   :
    // returns      :
    // displays     :
    // notes        : have to write function so it wont use length info to.
    //                  other wise it often recombines the genomes into none
    //                      functional genomes.
    //                  this though would simulate some recominations where
    //                   gametes are unviable, this is uneffent time wise for
    //                    us though.
    public static void recombination(byte[] male ,byte[] female)
    {
        byte[][] scanarray = {male,female};
    
        byte[] childgenome1;
        byte[] childgenome2;
        
        
        
        int maxpivot;
        Random generator = new Random();

        int genomeMaleLength = male.length;
        int genomeFemaleLength = female.length;

        if (genomeMaleLength < genomeMaleLength)
        {
            maxpivot = genomeMaleLength;
        }else{
            maxpivot = genomeFemaleLength;
            
        }

        childgenome1 = new byte[maxpivot];
        childgenome2 = new byte[maxpivot];
        
        
        int[] maleGenes    = new int[maxpivot];
        int[] femaleGenes  = new int[maxpivot];
        int[][] parentGenesSizes = {maleGenes, femaleGenes};
        
        
        //finding the length and number of the genes
        int j = 0;
        for(int k = 0 ; k < 2 ; k++){
            for (int i = 16; i < scanarray[k].length*8 ; j++) //starsts after the g,id,
                 {
                            System.out.println(getBinary(0, 6,scanarray[k]));
                            int len = getBinary(i, 6,scanarray[k]);
                            System.out.println("getbinary "+len);
                            len += 6;//the length in bits of lengthflag
                            System.out.println("+lengthflag size "+len);
                            len += 4;//the length in bits of atributeflag
                            System.out.println("+atributeflag size "+len);
                            i += len;//skip to next gene
                            System.out.println("+len "+len);
                            
                            parentGenesSizes[k][j] = len;
                             System.out.println(j);

                            int n = (len - (len-1)%6+1)/6;
                            double value = 0;

                           
                }
        }

        int pivot = generator.nextInt( maxpivot -16 );
        //System.out.println(pivot);
        pivot = pivot + 16;// cant have pivot in the gender or the
        //System.out.println(pivot);



        //int pivot = 371;
       // System.out.println(pivot);


        // adjust pivot to the nearest byte
        int index1 = (pivot/8);
        //System.out.println(index1);
        int pivot2 = (pivot)%8;
       // System.out.println(pivot2);

        if ((pivot2 > 0) && (pivot2 < 4) )
       {
           // System.out.println("here");
            pivot2 = pivot2 - pivot2;
            pivot = pivot - pivot2;
        }else if((pivot2 > 3 )&& (pivot2 < 7))
        {
           // System.out.println("there");

            pivot2 = pivot2 + (7 - pivot2);
            pivot = pivot + pivot2;
        }
       // System.out.println(pivot2);
       // System.out.println(pivot);


        for ( int i = 0 ; i < (pivot/8) ; i++)
        {
            childgenome1[i] = (byte)(childgenome1[i] | male[i]) ;
            childgenome2[i] = (byte)(childgenome2[i] | female[i]) ;
        }

        for ( int i = pivot ; i < (maxpivot/8) ; i++)
        {
            childgenome1[i] = (byte)(childgenome1[i] | female[i]) ;
            childgenome2[i] = (byte)(childgenome2[i] | male[i]) ;
        }

        //might have to pass these in, i think so
        Creature c1 = new Creature(childgenome1);

        System.out.println();

        Creature c2 = new Creature(childgenome2);
    }//end recombination


        //public void readGenome ()
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

                        //System.out.println("Type = "+type+", value = "+result);

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

