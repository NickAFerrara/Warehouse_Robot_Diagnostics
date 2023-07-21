import java.io.*;
import java.net.*;

import Utils.ByteUtil;

public class ClientModel {

    private String address;
    private int port;
	public String model_String;

    // initialize socket and input output streams
    private Socket socket = null;
	private BufferedInputStream inFromServer_stream = null;
	private OutputStream outToServer_stream = null;

	// constructor to put ip address and port
	public ClientModel(String address, int port) throws Exception
	{
        this.address = address;
        this.port = port;
   
        if (!connect())
            throw new Exception("Cannot connect to server");

        //NOTE: This client needs to listen to the keyboard input (input) and the server socket (bis); however, the code cannot
		//      do both at the same time.  Why?  To lisen to the keyboard, it must "Block" - essentially wait for something to be 
		//      read.  To listen to the socket, it must "Block" - essentially wait for something to be read.   While waiting for
		//      the keyboard, the socket is ignored.  While waiting for the socket, the keyboard is ignored.  You can get around
		//      this by buffering (saving any ignored messages to a buffer while you wait), but this is a poor solution.
		//
		//      The solution I went with is to create a thread to listen for the keyboard and a thread to listen to the socket.
		//
		//      An alternative solution can be found here: https://www.quora.com/How-can-I-listen-to-two-or-more-InputStreams-concurrently-in-Scala-or-Java
		//      This alternative solution uses something called a ThreadExecutor.
		//      
		//      Another solution would be to use a Selector -- https://stackoverflow.com/questions/44167391/can-two-threads-simultaneously-send-and-recv-on-the-same-socket

    }


    private boolean connect() {

		// establish a connection
		try {
			socket = new Socket(address, port);
			System.out.println("Connected");

			// takes input from the server socket
			inFromServer_stream = new BufferedInputStream(socket.getInputStream());

			// sends output to the socket
			outToServer_stream = socket.getOutputStream();
			
		}
		catch (UnknownHostException u) {
			System.out.println(u);
			return false;
		}
		catch (IOException i) {
			System.out.println(i);
			return false;
		}

        return true;
    }


	public byte[] HandleServerInput() {

		try {

            if (inFromServer_stream.available() == 0)
                return null;

			byte[] rxbuff = new byte[1460];
			inFromServer_stream.read(rxbuff);
			return rxbuff;

		}
		catch (IOException i) {
			System.out.println(i);
            return null;
		}
	}


    public boolean WriteToServer(byte[] message) {
        try {

			System.out.println("Packet Sent To Server: " + ByteUtil.ByteArrayToString(message));
			model_String = ("<br/> <br/> Packet Sent To Server: "+ByteUtil.ByteArrayToString(message));


            outToServer_stream.write(message);
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }

        return true;
    }


}
