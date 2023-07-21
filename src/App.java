import Models.PacketModel;

public class App {
	public static void main(String args[])
	{
		//This is the IP Address of the robot you want to communicate with.
		String address = "192.168.1.111";
		
		//The main method of communication with the robot is over TCP port 2096.
		int port_tcp = 2096;

		//The heartbeat message from the client(this program) to the robot is sent via UDP over port 2097.
		int port_heartbeat = 2097;


		//The Robot has two way of receiving messages.  Typical messages are sent over TCP.  This is what the Client code does below.
		//The other type of message is a HeartBeat (HB) message.  This HB message is sent over UDP.  Since the client will be sending 
		//and listening to messages from the server, the HB messages must be handled in a different thread.  This HeartBeat thread will 
		//simply send the HB message every so often - 2 times a second.
		HeartBeatThread hbt = new HeartBeatThread(address, port_heartbeat);
		hbt.start();


		//Initialize the PacketModel
		PacketModel pm = new PacketModel();

		Client client = new Client(address, port_tcp);
	}
}
