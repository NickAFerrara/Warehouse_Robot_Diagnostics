package Models;
import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.LinkedList;

import DomainObjects.Packet;
import Utils.ByteUtil;

public class PacketModel {
    private static byte nextInstanceCounter;
    private static byte getNextInstanceCounter(){
        nextInstanceCounter++;
        return nextInstanceCounter;
    }

    private static LinkedList<Packet> CreatedPackets;


    public PacketModel() {
        CreatedPackets = new LinkedList<Packet>();
    }

    public static Packet getPacketByInstanceCounter(int instanceCounter) {
        for (Packet packet : CreatedPackets) {
            if (packet.getInstanceCounter() == instanceCounter)
                return packet;
        }
        return null;
    }



    public static Packet createPacket(byte command, byte[] payload) {

        int timestamp = getCurrentTimeStamp();
        byte[] timestampBytes = ByteUtil.ConvertToLittleEndianByteArray(timestamp, 4);
        byte instanceCounter = getNextInstanceCounter();
        Packet packetDO = new Packet(command, timestampBytes, instanceCounter, payload);
       
        //Add the packet to the CreatedPackets List.
        CreatedPackets.add(packetDO);
        return packetDO;
    }

    private static int getCurrentTimeStamp() {
        //Current time in milliseconds. 32 bit unsigned integer
        ZonedDateTime nowZoned = ZonedDateTime.now();
        Instant midnight = nowZoned.toLocalDate().atStartOfDay(nowZoned.getZone()).toInstant();
        Duration duration = Duration.between(midnight, Instant.now());
        int timestamp = (int)duration.toMillis();

        return timestamp;
    }



}
