package Utils;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class ByteUtilTest {

    @Test
    public void TestReverseBytes() {

        //Check for Even size
        byte[] array = new byte[4];
        array[0] = (byte)1;
        array[1] = (byte)2;
        array[2] = (byte)3;
        array[3] = (byte)4;
 
        byte[] revarray = ByteUtil.ReverseBytes(array);
 
        assertEquals("Should be the same", array[3], revarray[0]);
        assertEquals("Should be the same", array[2], revarray[1]);
        assertEquals("Should be the same", array[1], revarray[2]);
        assertEquals("Should be the same", array[0], revarray[3]);

        

        //Check for Odd size
        array = new byte[5];
        array[0] = (byte)1;
        array[1] = (byte)2;
        array[2] = (byte)3;
        array[3] = (byte)4;
        array[4] = (byte)5;
 
        revarray = ByteUtil.ReverseBytes(array);
 
        assertEquals("Should be the same", array[4], revarray[0]);
        assertEquals("Should be the same", array[3], revarray[1]);
        assertEquals("Should be the same", array[2], revarray[2]);
        assertEquals("Should be the same", array[1], revarray[3]);
        assertEquals("Should be the same", array[0], revarray[4]);


    }


    @Test
    public void TestConvertToLittleEndianByteArray() {

        byte[] array = ByteUtil.ConvertToLittleEndianByteArray(10, 1);
        assertEquals("Length should be 1", 1, array.length);
        assertEquals("Should be the same", (byte)0x0A, array[0]);

        array = ByteUtil.ConvertToLittleEndianByteArray(10, 2);
        assertEquals("Length should be 2", 2, array.length);
        assertEquals("Should be the same", (byte)0x0A, array[0]);
        assertEquals("Should be the same", (byte)0x00, array[1]);


    }
}
