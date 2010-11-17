/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package csc106;

/**
 *
 * @author jason
 */
public class Main {

    public static final byte strength = 0 ;
    public static final byte speed = 1 ;
    public static final byte smell = 2 ;
    public static final byte gender = 3 ;
    static final byte[] Masks = {1 << 0, 1 << 1, 1 << 2, 1 << 3, 1 << 4, 1 << 5, 1 << 6, (byte)(1 << 7)};

    static final byte Minus = 0;
    static final byte Plus = 1;
    static final byte Divide = 2;
    static final byte Multiply = 3;

    public static void main(String[] args) {
        int i;

        byte[] b;


        b = new byte[1];





       // Creature c = new Creature(1,1,1,1);
            //System.out.println(c);

        b[0] = setGender(1,b[0]);
//        sop(showByte(-128));
//        sop("");


        b[0] = setGeneLength(8,b[0]);
       sop("b[0] after setGeneLength(8,b[0]); " + toBinary(b[0]));
       sop("");

//        byte testbyte = 0;
//        sop("cnvrtTsixBit(63,b[1])");
//        cnvrtTsixBit(63,b[1]);

                sop("");


        
       
    }//end Main method



    // purpose      : To take a male and female genome and preform homologus
    //                    recombination on them.
    // parameters   :
    // returns      :
    // displays     :
    public static void recombination(byte[] male ,byte[] female)
    {
        byte[] childgenome1;
        byte[] childgenome2;










        int index = 371;
        sop(index);

        int index1 = (index/8);
        sop(index1);
        int index2 = (index)%8;
        sop(index2);

        if ((index2 > 0) && (index2 < 4) )
       {
            sop("here");
            index2 = index2 - index2;
            index = index - index2;
        }else if((index2 > 3 )&& (index2 < 7))
        {
            sop("there");

            index2 = index2 + (7 - index2);
            index = index + index2;
        }
        sop(index2);
        sop(index);









    }//end recombination



    // PURPOSE    : add a gene to the genome
    // PARAMATERS : byte[] tempgene     : the gene to be added
    //              byte [] b           : the original genome
    // RETURNS    : a byte array representing the original genome with a new gene at the end
    // DISPLAYS   : nothing
    public static byte[]  addGeneTGenome(byte[] tempgene,byte [] b)
    {

        byte[] temparray = new byte[b.length + tempgene.length];

        for (int i = 0 ; i < b.length ; i++)
        {
            temparray[i] = b[i];
        }

        int blen = b.length;
        for (int i = 0 ; i < tempgene.length ; i++)
        {
            temparray[blen + i] = tempgene[i];
        }

        return (temparray);
    }// end printGenomelong method



    // PURPOSE    : not normal standard but it works for what I need
    //              Creates a gene with bytes, not compressed
    // PARAMATERS :
    // RETURNS    :
    // DISPLAYS   :
    public static byte[] createGene(int length,byte atrb, int[] values, byte[] opperators)
    {
        byte[] geneparts;

        geneparts = new byte[(values.length + opperators.length) + 2];

        geneparts[0] = (byte)(geneparts[0] | length);
        //sop(toBinary(geneparts[0]));

        geneparts[1] = (byte)(geneparts[1] | atrb);
        //sop(toBinary(geneparts[1]));

       // sop(geneparts.length);
       // sop("");

        int j = 0;
        int k = 0;
        for (int i = 2 ; i< geneparts.length ; i++)
        {
            //sop(i);
            //sop(j);
            //sop(k);
            geneparts[i] = (byte)values[j];

            if((opperators.length != 0) && (k < opperators.length))
            {
                i++;
                //System.out.println("i: " + i + " k: " + k);
                geneparts[i] = (byte)opperators[k];
                k++;

            }// end if(opperators.length != 0)

            j++;
        }


        printGenomeLong(geneparts);

        return (geneparts);
    }// end method createGene


    // PURPOSE    :
    // PARAMATERS :
    // RETURNS    :
    // DISPLAYS   :
    public static void printGenomeLong(byte[] b)
    {
        int i ;
        for (i = 0 ; i< b.length ; i++)
        {
            System.out.print(toBinary(b[i]) + " ");

        }//end for i
        sop("");

    }// end printGenomelong method

    // PURPOSE    :
    // PARAMATERS :
    // RETURNS    :
    // DISPLAYS   :
    public static void printGenome(byte[] b)
    {
        int i ;
        for (i = 0 ; i< b.length ; i++)
        {
            sop(toBinary(b[i]));
        }//end for i
    }// end printGenome method

    // PURPOSE    : sets the length of a gene for its metadata
    // PARAMATERS : an int no larger then int 63, minimum length = 8
    // RETURNS    :
    // DISPLAYS   :
    public static byte setGeneLength(int length ,byte b)
    {
        byte tempByte = 0;
        tempByte = cnvrtTsixBit(length,tempByte);
       // sop("tempByte: " + toBinary(tempByte));

        b = (byte)( b & 0x81); //should clear the current length
        //sop(" b & 0x81:     " + toBinary(b));

        b = (byte)( b | tempByte);
        //sop(" b | tempByte: " + toBinary(b));
       // sop("");

        //sop(toBinary(b));
       // sop("");

        return (b);
    }// end setGeneLength


    // PURPOSE    : to create a six bit mask for the first gene
    // PARAMATERS :
    // RETURNS    :
    // DISPLAYS   :
    public static byte cnvrtTsixBit(int i, byte b)
    {
        if( i < 64){
          // sop(toBinary(b));
           b = (byte)(b | i);
           b = (byte)(b << 1);
          // sop(toBinary(b));
        }else{
            sop("in cnvrtTsixBit int i has to be between 0-63");
            return (b);
        }


        return (b);
    }// end method cnvrtTsixBit


   //PURPOSE    : to set the gender of the genome
   //PARAMATERS : passed an integer and a byte, 1 female, 0 male
   //RETURNS    :
   //DISPLAYS   :
    public static byte setGender(int i,byte b)
    {

        b = (byte)(b | 0x80); //changes Msb to 1; // defalts the gender to female
        if (i == 0 ){// set the  gender to male
            b = (byte)(b & 0x7f); //changes Msb to 1
        }

        return (b);
    }// end setgender method

   //PURPOSE    : shows the bits of a byte and the leading zeros
   //PARAMATERS : passed an integer
   //RETURNS    : a string with the bit pattern of eight chars length
   //DISPLAYS   :nothing
    public static String showByte(int integer){
        String s;
        if (integer < 0)
        {
            integer = integer * -1;
        }
        s = showBits(integer);
        s = addZeros(showBits(integer));
        return s;
    }// end showByte method

   //PURPOSE   : shows the bits of a byte
   //PARAMATERS: passed an integer
   //RETURNS   : a string with the bit pattern
   //DISPLAYS  :nothing
     static String showBits(int value)
     {
           String stringOfBits = "";

           while (value > 0)
           {
               stringOfBits = (value % 2) + stringOfBits;
               value /= 2;
           }//end while

           return stringOfBits;
     }//end  showBits method

     // PURPOSE   : to add leading zeros to a bit string representing a byte
     // PARAMETERS: passed an int
     // RETURNS   : a string 8 chars in length
     // DISPLAYS  : nothing
    static String addZeros(String s){
        int intsInString = s.length() ;

        if (intsInString < 8 )
        {
            int addZeros = 8 - intsInString ;
            for (int j = 0 ; j < addZeros ; j++)
            {
                 s = "0" + s;
            }// end for j
        }//end if
        return s;
    }// end addZeros method

   //PURPOSE:  sets the least significant bit to to a one or true
   //PARAMATERS: passed a byte
   //RETURNS: a byte that has the LSB as a one
   //DISPLAYS: nothing
   static byte setLSBOne (byte b1)
   {
       //Or'ing 1 with any thing chanes value to one
       b1 = (byte)(b1 | 0x01); //changes lsb to 1
       // can use a byte or an int

       return b1;
   }// end set1 method


   //PURPOSE: sets the least significant bit to a zero
   //PARAMATERS: a byte
   //RETURNS: a byte
   //DISPLAYS: nothing
   static byte setLSBZero (byte b1)
   {
       //AND'ing 0 with any thing chanes value to Zero
       b1 = (byte)(b1 & 0xfe); //changes lsb to 0

       return b1;
   }// end set1 method

    public static byte andByte(byte b){

        b &= 1;

                sop(b);
        return b;
    }//end andByte method

    public static byte orByte(byte b){

                b |= 1;

                        sop(b);
                return b;
    }//end orByte

    public static byte xorByte(byte b){

                b ^= 1;

                        sop(b);
                return b;
    }//end xorByte

   public static byte rightShiftByte(byte b){


                b = (byte)(b >> 1);

                sop(b);
                return b;

    }//end rightShiftByte

    public static void sop(byte[] a){
        System.out.println(a);
    }

    public static void sop(int i){
        System.out.println(i);
    }

    public static void sop(String s){
        System.out.println(s);
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

    // PURPOSE    :
    // PARAMATERS :
    // RETURNS    :
    // DISPLAYS   :
    public static void template()
    {


    }
}
// byte arrays
//http://stackoverflow.com/questions/1034473/java-iterate-bits-in-byte-array

//bitwase operations
//http://leepoint.net/notes-java/data/expressions/bitops.html




//old stuff
//
//
//        int length;
//        byte[] tempgene ;
//        int[] values = {1,1};
//        byte[] opperators = {Plus};
//        length = (values.length + opperators.length)+1;
//        tempgene = createGene(length,strength,values,opperators);
//        //sop(tempgene.length);
//        b = addGeneTGenome(tempgene,b);
//
//        values[0]= 1;
//        values[1]= 1;
//        opperators[0] = Minus;
//        length = (values.length + opperators.length)+1;
//        tempgene = createGene(length,speed,values,opperators);
//        //sop(tempgene.length);
//        b = addGeneTGenome(tempgene,b);
//
//        values[0]= 1;
//        values[1]= 1;
//        opperators[0] = Multiply;
//        length = (values.length + opperators.length)+1;
//        //sop(length);
//        tempgene = createGene(length,smell,values,opperators);
//        //sop(tempgene.length);
//        b = addGeneTGenome(tempgene,b);
//
//
//
//        int[] values2 = {1,2,3};
//        byte[] opperators2 = {Multiply,Plus};
//        length = (values.length + opperators.length)+1;
//        tempgene = createGene(length,speed,values2,opperators2);
//        //sop(tempgene.length);
//        b = addGeneTGenome(tempgene,b);
//
//
//        printGenomeLong(b);
//        printGenome(b);
//
//        Genome genome= new Genome(b);
//
//        Creature c = new Creature(genome);
