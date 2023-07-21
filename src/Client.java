    
// A Java program for a Client
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;

import Controllers.PacketController;
import DomainObjects.Coordinates;
import DomainObjects.Packet;
import Models.PacketModel;
import Utils.CommandType;
import Utils.NavigationUtil;
import Utils.PacketUtil;

public class Client {

	//private DataInputStream input = null;
	private BufferedReader inFromKeyboard_reader = null;
	
	private ClientModel clientModel;

	// constructor to put ip address and port
	public Client(String address, int port)
	{

		// establish a connection
		try {
			clientModel = new ClientModel(address, port);

			// takes input from terminal
			//input = new DataInputStream(System.in);
			inFromKeyboard_reader = new BufferedReader(new InputStreamReader(System.in));


		}
		catch (UnknownHostException u) {
			System.out.println(u);
			return;
		}
		catch (IOException i) {
			System.out.println(i);
			return;
		}
		catch (Exception e) {
			System.out.println(e);
			return;
		}



		// string to read message from input
		String line = "";


		System.out.println("This is a simplistic tool to test our understanding of the robot commands.  Use the follow:");
		System.out.println("  1. Test SW_CAMERA_READ_INFO");
		System.out.println("  2. Test SW_P71_ROBOT_MOVE - Move forward 1 meter");
		System.out.println("  3. Test SW_P71_ROBOT_MOVE - Move backward 1 meter");


		System.out.println("  a. SW_GET_BAT_STATUS");
		System.out.println("  b. SW_P71_CONFIG_READ");
		System.out.println("  c. SW_P71_STATUS_READ");
		System.out.println("  d. SW_GET_ODS_DATA");
		System.out.println("  e. SW_GET_CALIBRATION_STATUS");
		System.out.println("  f. SW_R_LINUX_INFO");
		System.out.println("  g. SW_R_MAC_ADDRESS");

		while (true) {
			try {

				if (inFromKeyboard_reader.ready()) {
					HandleKeyboardInput();
				}


				//The message from the robot is a buffer of messages.  It may include more than one.
				byte[] message = clientModel.HandleServerInput();

				if (message != null){
					//convert the message byte array into a Packet 
					//message may have multiple "packets" in series.  Create the first packet.
					Packet packet = new Packet(message);
						
					while (packet.getIsValidPacket()) {
						PacketController.HandleIncomingPacket(packet);

						//Remove the currently read packet from the message byte array.
						message = Arrays.copyOfRange(message, packet.GetMessageBytes().length, message.length);

						//convert the message byte array into a Packet 
						//message may have multiple "packets" in series.  Create the first packet.
						packet = new Packet(message);
					}
				}

			}
			catch (IOException i) {
				System.out.println(i);
			}

		}
		
	}

	private void HandleKeyboardInput() {
		
		try {

			String line = inFromKeyboard_reader.readLine();
			//line = input.readLine();
			
			if (line.equals("1")){

				byte[] payload = new byte[] {(byte)0x00, (byte)0x00};
				Packet packet = PacketModel.createPacket(CommandType.SW_CAMERA_READ_INFO.getCommand(), payload);
				clientModel.WriteToServer(packet.GetMessageBytes());
			}
			if (line.equals("2")){

				double moveSpeed = .4;        	// m/s
				double moveAcc = .2; 			// m/ss
				double moveDec = .2; 			// m/ss
				double moveDistance = .9906; 	// m

				//0: Forward
				//1: Backward
				byte[] payload = PacketUtil.GetMoveCommandPayload(0, moveSpeed, moveAcc, moveDec, moveDistance);
				Packet packet = PacketModel.createPacket(CommandType.SW_P71_ROBOT_MOVE.getCommand(), payload);
				clientModel.WriteToServer(packet.GetMessageBytes());
			}
			if (line.equals("3")){

				double moveSpeed = .4;        	// m/s
				double moveAcc = .2; 			// m/ss
				double moveDec = .2; 			// m/ss
				double moveDistance = .9906; 	// m

				//0: Forward
				//1: Backward
				byte[] payload = PacketUtil.GetMoveCommandPayload(1, moveSpeed, moveAcc, moveDec, moveDistance);
				Packet packet = PacketModel.createPacket(CommandType.SW_P71_ROBOT_MOVE.getCommand(), payload);
				clientModel.WriteToServer(packet.GetMessageBytes());
			}
			
			// a. SW_GET_BAT_STATUS
			if(line.equals("a")){
		
				byte[] payload = PacketUtil.GetBatteryStatusCommandPayload();
				Packet packet = PacketModel.createPacket(CommandType.SW_GET_BAT_STATUS.getCommand(), payload);
				clientModel.WriteToServer(packet.GetMessageBytes());
			}
			if(line.equals("b")){//P71 config read.  default byte size set to 16, will test others and see
				byte[] payload = PacketUtil.P71ConfigPayload();
				Packet packet = PacketModel.createPacket(CommandType.SW_P71_CONFIG_READ.getCommand(),payload);
				clientModel.WriteToServer(packet.GetMessageBytes());
			}
			if(line.equals("c")){//p71 status read. default byte size set to 16, will test others and see
				byte[] payload = PacketUtil.P71StatusPayload();
				Packet packet = PacketModel.createPacket(CommandType.SW_P71_STATUS_READ.getCommand(), payload);
				clientModel.WriteToServer(packet.GetMessageBytes());
			}
			if(line.equals("d")){//get ods data
				byte[] payload = PacketUtil.ODSdataPayload();
				Packet packet = PacketModel.createPacket(CommandType.SW_GET_ODS_DATA.getCommand(), payload);
				clientModel.WriteToServer(packet.GetMessageBytes());
			}
			if(line.equals("e")){
				byte[] payload = PacketUtil.CalibrationPayload();
				Packet packet = PacketModel.createPacket(CommandType.SW_GET_CALIBRATION_STATUS.getCommand(), payload);
				clientModel.WriteToServer(packet.GetMessageBytes());
			}
			if(line.equals("f")){
				byte[] payload = PacketUtil.GetLinuxInfoPayload();
				Packet packet = PacketModel.createPacket(CommandType.SW_R_LINUX_INFO.getCommand(), payload);
				clientModel.WriteToServer(packet.GetMessageBytes());
				System.out.println("Format is very long and impossible to parse, will need to be addressed");
			}
			if(line.equals("g")){
				byte[] payload = PacketUtil.GetMacAddrPayload();
				Packet packet = PacketModel.createPacket(CommandType.SW_R_MAC_ADDRESS.getCommand(), payload);
				clientModel.WriteToServer(packet.GetMessageBytes());
			}

			// b. SW_P71_CONFIG_READ
			// c. SW_P71_STATUS_READ
			// d. SW_GET_ODS_DATA
			// e. SW_GET_CALIBRATION_STATUS
			// f. SW_R_LINUX_INFO
			// g. SW_R_MAC_ADDRESS

				
		}
		catch (IOException i) {
			System.out.println(i);
		}
	}

	public String reverseString(String str){
		String reverse="";
        char ch;
		for (int i=0; i<str.length(); i++)
		{
		  ch= str.charAt(i); //extracts each character
		  reverse= ch+reverse; //adds each character in front of the existing string
		}
		return reverse;
	}



}
