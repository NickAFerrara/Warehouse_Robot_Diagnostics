    
import java.io.*;
import java.net.*;

public class HeartBeatThread extends Thread{


	public static boolean done = false;
	
	private String address;
	private int port;

	HeartBeatThread(String address, int port){
		this.address = address;
		this.port = port;
	}


	public void run(){
		System.out.println("Starting HeartBeat Thread"); 

		done = false;
		try {
            //First create a UDP socket.
            InetAddress address = InetAddress.getByName(this.address);
			DatagramSocket socket = new DatagramSocket();
 
            //Loop forever.
            while (!done) {
 
				//The heartbeat message is a simple message: 00001010.
				byte[] txbuff = new byte[] { 0xa};
				
                //Create the UDP packet.
                DatagramPacket request = new DatagramPacket(txbuff, 1, address, this.port);
                
                //Send the UDP packet.
                socket.send(request);
 
                //Wait .5 seconds and do it again.
                Thread.sleep(500);
            }
 
        } catch (SocketTimeoutException ex) {
            System.out.println("Timeout error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

	}

}