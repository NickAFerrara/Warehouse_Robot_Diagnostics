package Controllers;

import java.awt.image.ConvolveOp;
import java.nio.charset.StandardCharsets;

import DomainObjects.Packet;
import Models.PacketModel;
import Utils.ByteUtil;
import Utils.CommandType;

public class PacketController {
	//String that is modified each time a new command is sent to the robot
	public static String controller_String;


	//There are three incoming packets from the robot: 
	//  SW_GET_ROBOT_STATUS
	//       This is sent regularly (every 1/2 second?)
	//  SW_CMD_COMPLETE
	//		 This is sent when a commnad is completed
	//  SW_CMD_RESPONSE
	//       This is sent when a command is received	
	public static void HandleIncomingPacket(Packet packet) {

		if (packet.getCommand() == (byte)0x07)
			System.out.print(".");
		else{
			System.out.println("Message Received: " + packet.GetMessageString());
			controller_String=("<br/> Message Received: "+packet.GetMessageString());
		}


		//Here we use the byte value because the case expression must be a const.
		switch(packet.getCommand()) {
			// CommandType.SW_GET_ROBOT_STATUS
			case (byte)0x07: {	
				handleRobotStatusPacket(packet);
				break;
			} 
			// CommandType.SW_CMD_COMPLETE
			case (byte)0xE6: {
				handleCommandCompletePacket(packet);
				break;
			}
			// CommandType.SW_CMD_RESPONSE
			case (byte)0xFF: {
				handleCommandResponsePacket(packet);
				break;
			}
			default: {
				handleGenericResponsePacket(packet);
				break;
			}
		}

	}

	private static void handleRobotStatusPacket(Packet packet) {
		//System.out.println("ReceivedRobotStatusPacket");
		return;
	}

	private static void handleCommandCompletePacket(Packet packet) {
		System.out.println("ReceivedCommandCompletePacket");

		return;
	}

	private static void handleCommandResponsePacket(Packet packet) {
		System.out.println("ReceivedCommandResponsePacket");
		System.out.println("Payload: " + ByteUtil.ByteArrayToString(packet.getPayload()));
		controller_String=(controller_String + "<br/> ReceivedCommandResponsePacket <br/> Payload: "+ByteUtil.ByteArrayToString(packet.getPayload()));
		controller_String=(controller_String+ ErrorChecker.check(packet.getPayload()[0]));
		

		

		Packet RobotRespondingToThisPacket = PacketModel.getPacketByInstanceCounter(packet.getInstanceCounter());

		if (RobotRespondingToThisPacket.getCommand() == CommandType.SW_GET_BAT_STATUS.getCommand()) {
			byte[] payload = packet.getPayload();
			int batterypower = payload[0];
			System.out.println("   Battery Power: " + batterypower);
			controller_String=(controller_String+"<br/> Battery Power: "+batterypower);
		}
		//linux info is given as a long string converted into a byte array with 0x00 values separating each line of info
		if(RobotRespondingToThisPacket.getCommand()==CommandType.SW_R_LINUX_INFO.getCommand()){
			byte[] payload = packet.getPayload();
			//using a loop to translate each byte into a string and skip 00 values?
			int breakCounter=0;//break counter used to track which portion of java info is being translated
			System.out.println("Linux info");
			System.out.print("System name: ");
			controller_String=(controller_String+ "<br/> Linux Info <br/> System name: ");

			byte[] copy= new byte[payload.length];
			int count=0;
			for(int i=0;i<=payload.length;i++){
				try{
				if(payload[i]==0x00){
					if(breakCounter==1){
						System.out.print("Node name: ");
						controller_String=(controller_String+"<br/> Node name: ");
					}
					if(breakCounter==2){
						System.out.print("Release: ");
						controller_String=(controller_String+"<br/> Release: ");
					}
					if(breakCounter==3){
						System.out.print("Version: ");
						controller_String=(controller_String+"<br/> Version: ");
					}
					if(breakCounter==4){
						System.out.print("Machine: ");
						controller_String=(controller_String+"<br/> Machine: ");
					}

					System.out.println(new String(copy,StandardCharsets.UTF_8));
					controller_String=controller_String+(new String(copy, StandardCharsets.UTF_8));
					breakCounter++;
					count=0;
					copy= new byte[payload.length];
					continue;
				}
				else{
					copy[count]=payload[i];
					count++;
				}
			}//catches the array index out of bounds exception caused by iterating through the byte array of the response
			catch(Exception e){
			}
			}
		}
		if(RobotRespondingToThisPacket.getCommand()==CommandType.SW_R_MAC_ADDRESS.getCommand()){
			byte[] payload = packet.getPayload();

			System.out.println("MAC Address: "+Integer.toHexString((payload[0]&0xff))+":"+Integer.toHexString((payload[1]&0xff))+":"+Integer.toHexString((payload[2]&0xff))+
			":"+Integer.toHexString((payload[3]&0xff))+":"+Integer.toHexString((payload[4]&0xff))+":"+Integer.toHexString((payload[5]&0xff)));
			controller_String=(controller_String+"<br/> MAC address: "+Integer.toHexString((payload[0]&0xff))+":"+Integer.toHexString((payload[1]&0xff))+":"+Integer.toHexString((payload[2]&0xff))+
			":"+Integer.toHexString((payload[3]&0xff))+":"+Integer.toHexString((payload[4]&0xff))+":"+Integer.toHexString((payload[5]&0xff)));
		}
		return;
	}

	private static void handleGenericResponsePacket(Packet packet) {

		System.out.println("ReceivedGenericResponsePacket");
		controller_String= (controller_String+"<br/> ReceivedGenericResponsePacket");
		return;
	}

}
