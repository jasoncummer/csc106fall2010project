

//package csc106project;

/**
 *
 * @author Jason Cummer
 */
public class Main {


    public static void main(String[] args) {


        byte[] b;


        b = new byte[10];




        b[0] = 0;
        sop(b);//prints the address of the byte array
        //sop(b[0]);//prints the byte at 0 index
        //sop(b[1]);//if unitialized byte is 0


       // b[1] = orByte(b[1]);
        //sop(b[1]);


		sop("");
        Creature c = new Creature(1,1,1,1);
        //System.out.println(c);




        //sop("string");
    }

    public static byte andByte(byte b){

        b &= 1;

                sop(b);
        return b;
    }

    public static byte orByte(byte b){

	        b |= 1;

	                sop(b);
	        return b;
    }

    public static byte xorByte(byte b){

	        b ^= 1;

	                sop(b);
	        return b;
    }

   public static byte rightShiftByte(byte b){


		b = (byte)(b >> 1);

		sop(b);
		return b;

    }

    public static void sop(byte[] a){
        System.out.println(a);
    }

    public static void sop(int i){
        System.out.println(i);
    }

    public static void sop(String s){
        System.out.println(s);
    }
}
// byte arrays
//http://stackoverflow.com/questions/1034473/java-iterate-bits-in-byte-array

//bitwase operations
//http://leepoint.net/notes-java/data/expressions/bitops.html