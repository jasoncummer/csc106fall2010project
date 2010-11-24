package csc106;


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

                Genome g2 = Genome.customGenome();
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

        byte[] childgenomeMf;
        byte[] childgenomeFm;
        int largermf = 0;// o = male, 1 = female


        int maxpivot;
        Random generator = new Random();

        int genomeMaleLength = male.length;
        int genomeFemaleLength = female.length;

        if (genomeMaleLength < genomeFemaleLength)
        {
            maxpivot = genomeFemaleLength;
            largermf = 1;
        }else{
            maxpivot = genomeMaleLength;
            largermf = 0;

        }


        int[]  maleGenes   = new int[12];//[maxpivot];
        int[]  femaleGenes = new int[12];//[maxpivot];
        int[][] parentGenesSizes = {maleGenes, femaleGenes};


        //finding the length and number of the genes
          System.out.println("(male.length)  : "     + (scanarray[0].length));
          System.out.println("(male.length)*8  : "   + (scanarray[0].length*8));
          System.out.println("(female.length)  : "   + (scanarray[1].length));
          System.out.println("(female.length*8)  : " + (scanarray[1].length*8));
        // System.out.println("(scanarray[k].length*8)-2: " + ((scanarray[0].length*8)-2));

        int j;
        int len = 0;
        for(int k = 0 ; k < 2 ; k++){
           // System.out.println("k: " + k);
            int i = 16;
            j = 0;// the position in the int[] length array
           // System.out.print("i " + i );
            for ( i = 16; i < (scanarray[k].length*8)-2 ; ) //starsts after the g,id,
                 {
                            len = 0;
                            //System.out.println("i: " + i + " k: " + k);
                             if (((scanarray[k].length*8)-i) > 8){
                              // System.out.println(getBinary(i, 6,scanarray[k]));
                              //  System.out.println("i: " + i + " k: " + k);
                                len = getBinary(i, 6,scanarray[k]);
                                System.out.println("len " + len + ", i:" + i );


                                i += len;//skip to next gene
                                //System.out.println("i +len "+len);

                                i += 6;
                                len += 6;//the length in bits of lengthflag
                            //     System.out.println(" i " + i + "len" + len);

                                 i += 4;
                                 len += 4;//the length in bits of atributeflag
                             //   System.out.println("len+atributeflag size "+len);





                               //System.out.println("k " + k + ", j " + j);
                               parentGenesSizes[k][j] = len;
                             // System.out.println(parentGenesSizes[k][j]);

                            //    System.out.println( "stored len " + len);
                              j++;
                           }
                           // i++;
                         //  System.out.println("");
                         if (len == 0){
                            // parentGenesSizes[k][j-1] = parentGenesSizes[k][j-1] -10;
                             System.out.println();
                            //System.out.println(parentGenesSizes[k][j-1]);
                            break;
                         }

                }// end for i
        }//end for k

       // childgenome1 = new byte[maxpivot];
        //childgenome2 = new byte[maxpivot];

       // System.out.println(childgenome1.length);
        //System.out.println(childgenome2.length);






        int pivotm = 16 ;// add the gender and the id space back on
        int pivotf = 16 ;
        int prepivot = 1;//(generator.nextInt( 9 )+2); //indexed like an array
        System.out.println("prepivot " + prepivot);

        // pivot is sum 0 to prepivot
        for (int x = 0 ; x < prepivot ; x++)
        {
           // System.out.println(x);
           // System.out.println(parentGenesSizes[0][x]);
           // System.out.println(parentGenesSizes[1][x]);

            pivotm = pivotm + parentGenesSizes[0][x];
            System.out.println("pivotm "+pivotm);
            pivotf = pivotf + parentGenesSizes[1][x];
            System.out.println("pivotf "+pivotf);
        }

        int secondHalfM = 16 ;// add the gender and the id space back on
        int secondHalfF = 16 ;

        for (int x = 0 ; x < 12 ; x++)
        {
//            System.out.println(x);
//            System.out.println(parentGenesSizes[0][x]);
//            System.out.println(parentGenesSizes[1][x]);

            secondHalfM = secondHalfM + parentGenesSizes[0][x];
            System.out.println("second half pivotm "+secondHalfM);
            secondHalfF = secondHalfF + parentGenesSizes[1][x];
            System.out.println("second half pivotf "+secondHalfF);
        }

            System.out.println("pivotm "+pivotm);

            System.out.println("pivotf "+pivotf);

            System.out.println("second half pivotm "+secondHalfM);

            System.out.println("second half pivotf "+secondHalfF);

            System.out.println("(second half pivotm) - pivotm "+ (secondHalfM - pivotm )  );

            System.out.println("(second half pivotf) - pivotf "+ (secondHalfF - pivotf) );

            System.out.println(" pivotf + ((second half pivotm) - pivotm)  "+ ((secondHalfM - pivotm ) + pivotf)  );

            System.out.println(" pivotm + ((second half pivotf) - pivotf)  "+ ((secondHalfF - pivotf) + pivotm) );



        int sizeMF = pivotm + (secondHalfF - pivotf) ;
        System.out.println("sizeMF  "+ sizeMF);//Mf
        int sizeFM = pivotf + (secondHalfM - pivotm)  ;
        System.out.println("sizeFM  " + sizeFM);//Fm

        double childmf = sizeMF/8;
        System.out.println("childmf/8: "+ childmf);
        System.out.println("size%8  "+ sizeMF%8);//Mf
        if (sizeMF%8 != 0 )
        {
            System.out.println("mf  not 0");
            childmf ++;
        }
        



        double childfm = sizeFM/8;
        System.out.println("childfm/8: "+childfm);
         System.out.println("size2%8  "+ sizeFM%8);//Mf
        if(sizeFM%8 != 0)
        {
            System.out.println("fm  not 0");
            childfm ++;
        }
        

        childgenomeMf = new byte[(int)childmf];
        childgenomeFm = new byte[(int)childfm];

        System.out.println("chgMf"+childgenomeMf.length);
        System.out.println("chgFm"+childgenomeFm.length);

        System.out.println("chgMf*8    "+childgenomeMf.length*8);
        System.out.println("chgFm*8    "+childgenomeFm.length*8);

          //works
//        byte[] destin = {0,0,0,0};
//        printGenomeLong(destin);
//        byte[] source = {(byte)255,(byte)153,(byte)255 ,(byte)153};
//        //byte[] exs = {(byte)0,(byte)0,(byte)0};
//        byte[] source2 = {(byte)153,(byte)255,(byte)153,(byte)255,(byte)153};
//        printGenomeLong(source);
//        int p1 = 12;
//        int p2 = 20;
//
//        int endIndex = 32;
        

        //replicateDNA(source2,/*^p1*/p1,/*^p2*/ p2 , endIndex , destin); //copies from the p1 pivot to the end of the p2 array
//        System.out.println("          p1:               "+p1);
//        System.out.println("          p2:               "+p2);
//        System.out.println("          p1-p2             "+(p1-p2));
//        System.out.println("             source.length: "+(source.length+1));
//        System.out.println(" (p2 -(source.length+1)*8)  "+( p2 -(source.length+1)*8));//source.length+1)*8, should correspond to the end of the genes in the p2 array
//        System.out.println("                            "+( pf -sizegenesF));
//        System.out.println("          p1-endindex       "+(p1-endIndex));
//        System.out.println("          p2-endindex       "+(p2-endIndex));

//        //System.out.println(""+);
//        childgenomeMf = new byte[10];
//        female = new byte[10];
//
////        childgenomeMf = new byte[8];
////        female = new byte[10];
////
////        childgenomeMf = new byte[10];
////        female = new byte[8];
//
//        for (int i = 0 ; i< female.length ; i++){
//            female[i] = (byte)255;
//            if (i%2==0){
//                female[i] = (byte)153;
//
//            }
//            female[female.length-1-1] = (byte)3;
//        }
//
//        for (int i = 0 ; i< male.length ; i++){
//            male[i] = (byte)170;
//            if (i%2==0){
//                male[i] = (byte)219;
//
//            }
//            male[male.length/8] = (byte)1;
//        }

      //  replicateDNA(male, 0, pivotm, childgenomeMf); //copys up to just before the pivot
//



//        for (int i = 0 ; i< female.length ; i++){
//            female[i] = (byte)255;
//            if (i%2==0){
//                female[i] = (byte)153;
//
//            }
//        }

        replicateDNA(female,/*^p1*/pivotm,/*^p2*/ pivotf ,/*end index*/ pivotm + (secondHalfF - pivotf) /*-1 */, childgenomeMf); //



    }//end recombination




    // PURPOSE    : replicates the end section of the genome
    // PARAMATERS : byte[] bin
    // RETURNS    : nothing
    // DISPLAYS   : nothing
    public static void replicateDNA (byte[] source, int pm,int pf, int length, byte[] destin) // Length must be <= 32
    {
//       int calcedLen = (source.length*8 - pf);
//       if(calcedLen == length)
//            System.out.println("GOOD");
//       else
//            length = calcedLen;

        System.out.println();


        System.out.println();
        System.out.println();

        //  System.out.println("pm/8:  "+pm/8);// remember 0 indexed so result +1
        int offset; //= pm %8;
        //System.out.println("offset  " + offset);

        System.out.println();



        //System.out.print("destin   ");
        // printGenomeLong(destin);
        System.out.println();


            System.out.println("pm -length    " + (pm - length ));
            for (int i = pm; i < length; i++)
            {
                    System.out.println("");
                    offset = i %8;
                    System.out.println("offset  " + offset);

                     System.out.println(toBinary(source[pf/8]) +"  source,"+ " " + pf/8 + " pf/8");
                     //System.out.println(toBinary((byte)(1<<(7-offset%8))));
                if (  (  (source[pf/8] & (byte)(1<<(7-offset%8 ) )) == (byte)(1<<(7-offset%8 ) )  )  )
                {
                   System.out.println("one");

               //works to set bit to one
                System.out.println(toBinary((byte)   Masks[7-offset%8])+"   mask");
                destin[i/8] = (byte)(      destin[i/8] | Masks[7-offset%8]         );//sets bit to one



                }else{
                    System.out.println("zero");

                // works to set it to zero
                System.out.println(toBinary((byte)   Masks[7-offset%8]) + "   mask"   );
                destin[(i)/8] = (byte)(destin[(i)/8] & ~Masks[7-offset%8]    );//changes bit to 0



                }
                     System.out.println(toBinary(destin[i/8])+"  destin[" + i/8 + "]");
                   // printGenomeLong(destin);
                    System.out.println("pf: "+pf);
                    pf ++;
            }//end for i

             System.out.println();
             System.out.println();
             System.out.println();
             System.out.println();

            System.out.print("destin     ");


            printGenomeLong(destin);
            System.out.print("source     "); // so the one that im using in here is the one prininting
          
            printGenomeLong(source);
    }//end replicateDNA method




    // PURPOSE    : replicates a section of the genome
    // PARAMATERS : byte[] bin
    // RETURNS    : nothing
    // DISPLAYS   : nothing
    public static void replicateDNA (byte[] source, int index, int length, byte[] destin) // Length must be <= 32
        {

//                System.out.println();
//                System.out.println("Binary source[2]: "+toBinary(source[2]) + ", int: " + source[2]);
//                System.out.println("index  "+index);
//                System.out.println("length  "+length);
//                System.out.println(""+toBinary(destin[2]));
//                System.out.println();
//                System.out.println("s.l*8"+source.length*8);
//                System.out.println("d.l*8"+destin.length*8);
//                System.out.println();

                //System.out.println();

                int offset = index % 8;
                System.out.println("offset  "+offset);
                for (int i = index; i < length; i++)
                {
//                        System.out.println("i = " + i + ", i/8: " + i/8);
//                        System.out.println("length  "+length);
//                        System.out.println("i/8:  " + i/8);
//                        System.out.println("source[(i+adjust)/8]:  " + source[i/8]);
//                        System.out.println( "(1 << (8 - i%8 - 1))) " + ( (byte)(1 << (8 - i%8 - 1)))   );
                      //if ((value & (1 << (length - i - 1))) == (1 << (length - i - 1)))
//                        System.out.println("(index+i)/8: "+(index+i)/8);
                        if ((source[i/8] & (1 << (8- i%8 -1) )  ) == (1 << (8-i%8 -1 ) )   )
                        {

//                            System.out.println("if");

//
//                                System.out.println(toBinary(destin[(index+i)/8]));
//                                System.out.println(toBinary(Masks[7 - (i + offset)%8]));
                                destin[(index+i)/8] = (byte)(destin[(index+i)/8] | Masks[7 - (i + offset)%8]);
//                                System.out.println(toBinary(destin[(index+i)/8]));
//                                System.out.println();
                        }
                        else
                        {
//                            System.out.println("else");
//                                System.out.println(toBinary(destin[(index+i)/8]));
//                                System.out.println(toBinary((byte)~Masks[7 - (i + offset)%8]));
                                destin[(index+i)/8] = (byte)(destin[(index+i)/8] & ~Masks[7 - (i + offset)%8]);
//                                System.out.println(toBinary(destin[(index+i)/8]));
//                                System.out.println();
                        }//end if()else
                }//end for i

                System.out.print("destin            ");
                printGenomeLong(destin);
        }//end replicateDNA method





    // PURPOSE    : Prints the genome byte by byte on multiple lines
    // PARAMATERS : a byte[]
    // RETURNS    : nothing
    // DISPLAYS   : the byte array, one byte to one line
    public static void printGenome(byte[] b)
    {
        int i ;
        for (i = 0 ; i< b.length ; i++)
        {
            System.out.println(toBinary(b[i]));
        }//end for i
    }// end printGenome method



        //public void readGenome ()
        public void readGenome (Creature c)
        {
                int Strength = 0, Speed = 0, Smell = 0;
                boolean gender = (getBinary(0, 1) == 1);
                int ID = getBinary(1, 15);

                for (int i = 16; i < genome.length*8 - LengthFlag - TypeFlag;)
                {
                        System.out.print("i " + i) ;
                        int len = getBinary(i, LengthFlag);
                        i += LengthFlag;
                        System.out.println(" i " + i + "len" + len);
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

        public static int getBinary (int index, int length, byte[] bin) // Length must be <= 32
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

        public static void setBinary (int value, int index, int length, byte[] bin) // Length must be <= 32
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

        public static Genome customGenome()
        {
                byte[] bin;

                int numGenes = 5;//12;
                int[] lenGenes = new int[numGenes];
                int sum = 16;

                for (int i = 0; i < numGenes; i++)
                {
                        lenGenes[i] = (int)4;
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
                                setBinary((int)(1), index, 1, bin);
                                //setBinary((int)(Math.random()*2), index, 1, bin);
                                index += 1;
                        }
                }

                return new Genome(bin);
        }

        public static Genome randomGenome()
        {
                byte[] bin;

                int numGenes = 12;//12;
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
                                setBinary((int)(1), index, 1, bin);
                                //setBinary((int)(Math.random()*2), index, 1, bin);
                                index += 1;
                        }
                }

                return new Genome(bin);
        }


    // PURPOSE    : prints the genome on one line from a byte array
    // PARAMATERS : byte[]
    // RETURNS    : nothing
    // DISPLAYS   : The Byte array
    public static void printGenomeLong(byte[] b)
    {
        int i ;
        for (i = 0 ; i< b.length ; i++)
        {
            //System.out.print(i*8 + "  ");

            System.out.print(toBinary(b[i]) + "");

        }//end for i
        System.out.println("");

    }// end printGenomelong method

    // PURPOSE    : prints the genome on one line from a byte array with no spaces between bytes
    // PARAMATERS : byte[]
    // RETURNS    : nothing
    // DISPLAYS   : The Byte array
    public static void printGenomeLongNS(byte[] b)
    {
        int i ;
        for (i = 0 ; i< b.length ; i++)
        {
            System.out.print(toBinary(b[i]));

        }//end for i
        System.out.println("");

    }// end printGenomelong method




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
