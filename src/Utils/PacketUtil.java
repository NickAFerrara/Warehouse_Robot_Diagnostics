package Utils;
public class PacketUtil {

    public static byte[] GetMoveCommandPayload(int direction, double moveSpeed, double moveAcc, double moveDec, double moveDistance) {
        //0x00 - Right
        //0x01 - Left
        //0x02 - Both
        byte motorSelect = (byte)0x02;
        
        byte[] speedbytes = ByteUtil.ConvertToLittleEndianByteArray((int)(moveSpeed * 10000), 4);
        byte[] distancebytes = ByteUtil.ConvertToLittleEndianByteArray((int)(moveDistance * 10000), 4);
        byte directionbyte = (byte)direction;
        byte accelbyte = (byte)(moveAcc * 10);
        byte decelbyte = (byte)(moveAcc * 10);

        byte[] payload = new byte[24];
        payload[0] = motorSelect;
        payload[1] = speedbytes[0];
        payload[2] = speedbytes[1];
        payload[3] = speedbytes[2];
        payload[4] = speedbytes[3];
        payload[5] = distancebytes[0];
        payload[6] = distancebytes[1];
        payload[7] = distancebytes[2];
        payload[8] = distancebytes[3];
        payload[9] = directionbyte;
        payload[10] = accelbyte;
        payload[11] = decelbyte;

        return payload;
    }



    public static byte[] GetStartChargingCommandPayload(){

        int x = 4;
        int y = 1;
        int distance = 180;
        byte orientation = (byte)0x02;

        byte[] xbytes = ByteUtil.ConvertToLittleEndianByteArray(x, 4);
        byte[] ybytes = ByteUtil.ConvertToLittleEndianByteArray(y, 4);
        byte[] distancebytes = ByteUtil.ConvertToLittleEndianByteArray(distance, 2);

        byte[] payload = new byte[11];

        payload[0] = xbytes[0];
        payload[1] = xbytes[1];
        payload[2] = xbytes[2];
        payload[3] = xbytes[3];

        payload[4] = ybytes[0];
        payload[5] = ybytes[1];
        payload[6] = ybytes[2];
        payload[7] = ybytes[3];

        payload[8] = orientation;

        payload[9] = distancebytes[0];
        payload[10] = distancebytes[1];

        return payload;
    }

    public static byte[] GetStopChargeCommandPayload() {
        int distance = 180;

        byte[] distancebytes = ByteUtil.ConvertToLittleEndianByteArray(distance, 2);
        byte[] payload = new byte[2];

        payload[0] = distancebytes[0];
        payload[1] = distancebytes[1];

        return payload;
    }

    public static byte[] GetLiftCommandPayload(int podNum){
        
        byte[] liftBytes = ByteUtil.ConvertToLittleEndianByteArray(podNum, 2);
        byte[] payload = new byte[4];

        payload[0] = liftBytes[0];
        payload[1] = liftBytes[1];
        payload[2] = 0x00;
        payload[3] = 0x00;

        return payload;
    }

    public static byte[] GetDropCommandPayload(){
        return new byte[0];
    }

    public static byte[] GetBatteryStatusCommandPayload(){
        return new byte[0];
    }

    public static byte[] GetMacAddrPayload(){
        return new byte[6];
    }

    public static byte[] GetLinuxInfoPayload(){
        return new byte[0];
    }

    public static byte[] CalibrationPayload(){
        return new byte[0];
    }

    public static byte[] P71ConfigPayload(){
        return new byte[16];
    }

    public static byte[] P71StatusPayload(){
        return new byte[16];
    }

    public static byte[] ODSdataPayload(){
        return new byte[1];
    }

}
