package Utils;
public class ByteUtil {
   
    public static byte[] ConvertToLittleEndianByteArray(int value, int numBytes) {
        byte[] array = new byte[numBytes];
        int maskbits = 255;
        for (int i = 0; i< numBytes; i++) {
            int mask = maskbits << (i*8);
            int shift = (8 * (i));
            byte b = (byte)((value & mask) >> shift);
            array[i] = b;
        }
       return array;
    }

    public static byte[] ReverseBytes(byte[] array){
        byte[] reversedArray = new byte[array.length];
        for(int i = 0; i<array.length; i++) {
            reversedArray[i] = array[array.length - (i+1)];
        }
        return reversedArray;
    }


	public static byte[] StringToByteArray(String str) {

		byte[] bytes = new byte[str.length() / 2];
	
        for (int i = 0; i < str.length(); i+=2) {
            byte byte0 = Byte.parseByte(str.substring(i,i+1), 16); 
            byte byte1 = Byte.parseByte(str.substring(i+1,i+2), 16); 
			bytes[i/2] = (byte)(((15 & byte0) << 4) + (15 & byte1));
		}
		return bytes;
	}
    public static String ByteArrayToString(byte[] arr){
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            int f0 = (0x0F & arr[i]);
            int f1 = (0xF0 & arr[i]) >> 4;
            str += Integer.toHexString(f1) + Integer.toHexString(f0);
        }
         return str;
    }
}
