package DomainObjects;

import java.util.Arrays;
import Utils.ByteUtil;

public class Packet {

    //[Header]
    //byte 0: rxbuff[0] start of frame
    //byte 1: rxbuff[1] command
    //byte 2-5: rxbuff[2-5] timestamp
    //byte 6: rxbuff[6] instance counter
    //byte 7: rxbuff[7] payload size (payloadsize)
    //byte 9-payloadsize: [payload]
    //byte 9+payloadsize
    final private byte startByte = (byte)0xAA;	//0xAA = 10101010 = 170
    private byte command;
    private byte[] timestamp;
    private byte instanceCounter;
    private byte[] payloadsize;
    private byte[] payload;
    private byte crc;

    private byte[] message;

    private boolean isValidPacket = true;

    public Packet(byte command, byte[] timestamp, byte instanceCounter, byte[] payload) {

        this.command = command;
        this.timestamp = timestamp;
        this.instanceCounter = instanceCounter;
        this.payload = payload;
        this.payloadsize = ByteUtil.ConvertToLittleEndianByteArray(payload.length, 2);

        //Message size = 9 (size of header) + payload length + 1 (CRC)
        int messagesize = 10 + payload.length;
        message = new byte[messagesize];

        message[0] = this.startByte;
        message[1] = this.command;
        message[2] = this.timestamp[0];
        message[3] = this.timestamp[1];
        message[4] = this.timestamp[2];
        message[5] = this.timestamp[3];
        message[6] = this.instanceCounter;
        message[7] = this.payloadsize[0];
        message[8] = this.payloadsize[1];

        for(int i = 0; i<payload.length; i++) {
            message[9+i] = payload[i];
        }
        

        this.crc = CalculateCrc(message, 9 + payload.length);
        message[messagesize-1] = this.crc;

        this.isValidPacket = true;

    }

    //constructor to convert a message array into a Packet
    //Note: This constructor will read the first packet from the message byte array.
    public Packet(byte[] msg){
        byte[] timestamp = new byte[4];
        byte[] payloadsize = new byte[4];

        
        if(msg[0] == 0x00) {
            //If the message array is all 0's exit quietly.
            this.isValidPacket = false;
            return;
        }
        if(startByte != msg[0]){
            //If the message array starts with the wrong bits, then exit with some noise.
            System.out.println("Start Byte is wrong!");
            this.isValidPacket = false;
            return;
        }
        //getting all the Packet pieces from the message array
            command         = msg[1];
            timestamp[0]    = msg[2];
            timestamp[1]    = msg[3];
            timestamp[2]    = msg[4];
            timestamp[3]    = msg[5];
            instanceCounter = msg[6];
            payloadsize[0]  = msg[7];
            payloadsize[1]  = msg[8];

            int payloadSize = (msg[8] << 8) + msg[7];
            payload = Arrays.copyOfRange(msg, 8, 8 + payloadSize);

            for(int i = 0; i<payload.length; i++){
                payload[i] = msg[9 + i];
            }
        crc = msg[msg.length - 1];
        message = new byte[10 + payloadSize];
        message[0] = startByte;
        message[1] = command;
        message[2] = timestamp[0];
        message[3] = timestamp[1];
        message[4] = timestamp[2];
        message[5] = timestamp[3];
        message[6] = instanceCounter;
        message[7] = payloadsize[0];
        message[8] = payloadsize[1];

        for(int i = 0; i<payload.length; i++) {
            message[9+i] = payload[i];
        }

        this.isValidPacket = true;

    }
	private static byte CalculateCrc(byte[] msg, int len){
		byte crc = (byte)0xFF;
		for (int i = 0; i < len; i++){
			crc ^= msg[i];
			for (int j = 0; j < 8; j++){
				if ((crc & 0x80) != 0){
					crc = (byte)((crc << 1) ^ 0x31);
				} else {
					crc <<= 1;
				}
			}
		}
		return crc;
	}

    public byte[] GetMessageBytes() {
        return message;
    }

    public String GetMessageString() {
        String messageString = "";
        for (int i = 0; i< message.length; i++) {
            messageString += String.format("%02X",message[i]);
        }
        return messageString;
    }

    public byte getCommand(){
        return command;
    }

    public byte[] getTimestamp() {
        return timestamp;
    }

    public byte getInstanceCounter(){
        return instanceCounter;
    }

    public byte[] getPayloadSizeBytes(){
        return payloadsize;
    }

    public int getPayloadSize(){
        int size = (payloadsize[1] << 8) + payloadsize[0];
        return size;
    }

    public byte[] getPayload(){
        return payload;
    }

    public byte getCrc(){
        return crc;
    }

    public boolean getIsValidPacket(){
        return isValidPacket;
    }



}
