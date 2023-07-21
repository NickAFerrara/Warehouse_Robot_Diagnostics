package DomainObjects;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

//import PacketDomainObject;

public class PacketTest {

    @Test 
    public void TestPacketDomainObject() {

   //     0xAA 0x66 0x02 0x57 0xE8 0xD2 0x19 0x0D 0x00 0x01 0x02 0x00 0x00 0x00 0x01 0x00 0x00 0x00 0x00 0x05 0x00 0x00 0xF7


        byte command = (byte)0x66;
        byte[] timestamp = new byte[] {(byte)0x02, (byte)0x57, (byte)0xE8, (byte)0xD2};
        byte instanceCounter = (byte)0x19;
        byte[] payload = new byte[] {(byte)0x01, (byte)0x02, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x05, (byte)0x00, (byte)0x00};
//        byte crc = (byte)0xF7;
        
        Packet packetDO = new Packet(command, timestamp, instanceCounter, payload);

        byte[] messageBytes = packetDO.GetMessageBytes();
        byte[] expected = new byte[] {(byte)0xAA, (byte)0x66, (byte)0x02, (byte)0x57, (byte)0xE8, (byte)0xD2, (byte)0x19, (byte)0x0D, (byte)0x00, (byte)0x01, (byte)0x02, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x05, (byte)0x00, (byte)0x00, (byte)0xF7};
 
        //I removed this check.  The .equals is still checking the address of the array, not the values.
        //assertTrue("These should match",  expected.equals(messageBytes));


        String messageString = packetDO.GetMessageString();
        assertEquals("These should match", "AA660257E8D2190D0001020000000100000000050000F7", messageString);

        

    }


}
