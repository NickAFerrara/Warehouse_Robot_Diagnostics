import Models.PacketModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.sql.Time;
import java.util.Arrays;
import Controllers.PacketController;
import DomainObjects.Packet;
import Models.PacketModel;
import Utils.CommandType;
import Utils.PacketUtil;

//application for sending diagnostics commands to the robots, this time utilizing a GUI for ease of use
public class GUI_App {
    private JLabel textLabel;
    static String address;
    static int port_tcp;
    static ClientModel clientModel;
    
    public static void main(String args[]){
        //creates popup box for users to enter the IP address of the robot being connected to
        JFrame jFrame = new JFrame();
        address = JOptionPane.showInputDialog(jFrame,"Enter your IP:");
        JOptionPane.showMessageDialog(jFrame,"Your IP: " +address);

        //main communication method through this TCP port
        port_tcp = 2096;

        //The heartbeat message from the client(this program) to the robot is sent via UDP over port 2097.
		int port_heartbeat = 2097;

        //setting up heartbeat thread in bg to keep the robot connection, as it will severe itself if this signal is lost
        HeartBeatThread hbt = new HeartBeatThread(address, port_heartbeat);
		hbt.start();

        //Initialize the PacketModel
		PacketModel pm = new PacketModel();

        //initializing client model for connecting to the robot
        //ClientModel clientModel;

        //try statement for establishing model and connecting, as well as catching exceptions 
        try{
            clientModel = new ClientModel(address, port_tcp);
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

            GUI_App app = new GUI_App();
            app.createAndShowGUI();

            //statement for reading and interpreting messages as they appear from the robot
            while(true){

                try{
                    //using client model to handle inputs coming from the server, aka the robot
                byte[] message = clientModel.HandleServerInput();

                if(message!=null){
                    //converting message byte array into a packet
                    //message may have multiple packets
                    Packet packet = new Packet(message);

                    while(packet.getIsValidPacket()){
                        PacketController.HandleIncomingPacket(packet);

                        //removes currently read packet from array
                        message = Arrays.copyOfRange(message, packet.GetMessageBytes().length, message.length);
                        
                        //converts byte array into packet
                        packet = new Packet(message);
                    }
                    }
                }
                catch(Exception i){
                    System.out.println(i);
                }

            }
        
    }

    //creates and displays the GUI for the application along with commands for buttons
    private void createAndShowGUI(){
        
        //creating Jframe to hold components
        JFrame frame = new JFrame("Diagnostics tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Making a JPanel with BorderLayout
        JPanel panel = new JPanel(new BorderLayout());

        //Creates label for displaying text
        //html is used to format the text and allow new lines to be added
        textLabel = new JLabel("<html>Select command to send from any of the buttons");
        textLabel.setSize(950, 500);
        textLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(textLabel,BorderLayout.CENTER);

        //JPanels for holding the buttons
        JPanel buttonPanel = new JPanel();
        JPanel buttonPanel2 =  new JPanel();

        //creating and adding buttons to the panel
        JButton button1 = new JButton("Read Camera");
        button1.addActionListener(new Button1Listener());
        buttonPanel.add(button1);

        JButton button2 = new JButton("Move Forward");
        button2.addActionListener(new Button2Listener());
        buttonPanel.add(button2);

        JButton button3 = new JButton("Move Backward");
        button3.addActionListener(new Button3Listener());
        buttonPanel.add(button3);

        JButton button4 = new JButton("Battery Status");
        button4.addActionListener(new Button4Listener());
        buttonPanel.add(button4);

        JButton button5 = new JButton("P71 Config");
        button5.addActionListener(new Button5Listener());
        buttonPanel.add(button5);

        JButton button6 = new JButton("P71 Status");
        button6.addActionListener(new Button6Listener());
        buttonPanel2.add(button6);

        JButton button7 = new JButton("ODS Data");
        button7.addActionListener(new Button7Listener());
        buttonPanel2.add(button7);

        JButton button8 = new JButton("Calibration Status");//make new button frame for second set of buttons and make app window larger
        button8.addActionListener(new Button8Listener());
        buttonPanel2.add(button8);

        JButton button9 = new JButton("Linux Info");
        button9.addActionListener(new Button9Listener());
        buttonPanel2.add(button9);

        JButton button10 = new JButton("MAC Address");
        button10.addActionListener(new Button10Listener());
        buttonPanel2.add(button10);

        //add the button panels to the main panel
        panel.add(buttonPanel,BorderLayout.NORTH);
        panel.add(buttonPanel2,BorderLayout.SOUTH);
        

        //add the panel to the frame
        frame.getContentPane().add(panel);

        //set the size and make the frame visible
        frame.setSize(700, 800);
        frame.setVisible(true);
    }

    private class Button1Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            byte[] payload = new byte[] {(byte)0x00, (byte)0x00};
			Packet packet = PacketModel.createPacket(CommandType.SW_CAMERA_READ_INFO.getCommand(), payload);
			clientModel.WriteToServer(packet.GetMessageBytes());
            textLabel.setText(textLabel.getText()+clientModel.model_String);
            //half second delay introduced to avoid having the packet controller string called before robot can respond to the command
            try {
                Thread.sleep(500);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            textLabel.setText(textLabel.getText()+PacketController.controller_String);
        }
    }

    //Listeners that will send commands to the robot when button is clicked
    private class Button2Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){

            double moveSpeed = .4;        	// m/s
				double moveAcc = .2; 			// m/ss
				double moveDec = .2; 			// m/ss
				double moveDistance = .9906; 	// m

				//0: Forward
				//1: Backward
				byte[] payload = PacketUtil.GetMoveCommandPayload(0, moveSpeed, moveAcc, moveDec, moveDistance);
				Packet packet = PacketModel.createPacket(CommandType.SW_P71_ROBOT_MOVE.getCommand(), payload);
				clientModel.WriteToServer(packet.GetMessageBytes());
                textLabel.setText(textLabel.getText()+clientModel.model_String);
                //half second delay introduced to avoid having the packet controller string called before robot can respond to the command
                try {
                 Thread.sleep(500);
                } catch (InterruptedException e1) {
                 // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            textLabel.setText(textLabel.getText()+PacketController.controller_String);
        }
    }
    private class Button3Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            double moveSpeed = .4;        	// m/s
				double moveAcc = .2; 			// m/ss
				double moveDec = .2; 			// m/ss
				double moveDistance = .9906; 	// m

				//0: Forward
				//1: Backward
				byte[] payload = PacketUtil.GetMoveCommandPayload(1, moveSpeed, moveAcc, moveDec, moveDistance);
				Packet packet = PacketModel.createPacket(CommandType.SW_P71_ROBOT_MOVE.getCommand(), payload);
				clientModel.WriteToServer(packet.GetMessageBytes());
                textLabel.setText(textLabel.getText()+clientModel.model_String);
                //half second delay introduced to avoid having the packet controller string called before robot can respond to the command
                try {
                  Thread.sleep(500);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                textLabel.setText(textLabel.getText()+PacketController.controller_String);
        }
    }
    private class Button4Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            byte[] payload = PacketUtil.GetBatteryStatusCommandPayload();
			Packet packet = PacketModel.createPacket(CommandType.SW_GET_BAT_STATUS.getCommand(), payload);
			clientModel.WriteToServer(packet.GetMessageBytes());
            textLabel.setText(textLabel.getText()+clientModel.model_String);
            //half second delay introduced to avoid having the packet controller string called before robot can respond to the command
            try {
                Thread.sleep(500);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            textLabel.setText(textLabel.getText()+PacketController.controller_String);
        }
    }
    private class Button5Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            byte[] payload = PacketUtil.P71ConfigPayload();
			Packet packet = PacketModel.createPacket(CommandType.SW_P71_CONFIG_READ.getCommand(),payload);
			clientModel.WriteToServer(packet.GetMessageBytes());
            textLabel.setText(textLabel.getText()+clientModel.model_String);
            //half second delay introduced to avoid having the packet controller string called before robot can respond to the command
            try {
                Thread.sleep(500);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            textLabel.setText(textLabel.getText()+PacketController.controller_String);
        }
    }
    private class Button6Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            byte[] payload = PacketUtil.P71StatusPayload();
			Packet packet = PacketModel.createPacket(CommandType.SW_P71_STATUS_READ.getCommand(), payload);
			clientModel.WriteToServer(packet.GetMessageBytes());
            textLabel.setText(textLabel.getText()+clientModel.model_String);
            //half second delay introduced to avoid having the packet controller string called before robot can respond to the command
            try {
                Thread.sleep(500);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            textLabel.setText(textLabel.getText()+PacketController.controller_String);
        }
    }
    private class Button7Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            byte[] payload = PacketUtil.ODSdataPayload();
			Packet packet = PacketModel.createPacket(CommandType.SW_GET_ODS_DATA.getCommand(), payload);
			clientModel.WriteToServer(packet.GetMessageBytes());
            textLabel.setText(textLabel.getText()+clientModel.model_String);
            //half second delay introduced to avoid having the packet controller string called before robot can respond to the command
            try {
                Thread.sleep(500);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            textLabel.setText(textLabel.getText()+PacketController.controller_String);
        }
    }
    private class Button8Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            byte[] payload = PacketUtil.CalibrationPayload();
			Packet packet = PacketModel.createPacket(CommandType.SW_GET_CALIBRATION_STATUS.getCommand(), payload);
			clientModel.WriteToServer(packet.GetMessageBytes());
            textLabel.setText(textLabel.getText()+clientModel.model_String);
            //half second delay introduced to avoid having the packet controller string called before robot can respond to the command
            try {
                Thread.sleep(500);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            textLabel.setText(textLabel.getText()+PacketController.controller_String);
        }
    }
    private class Button9Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            byte[] payload = PacketUtil.GetLinuxInfoPayload();
			Packet packet = PacketModel.createPacket(CommandType.SW_R_LINUX_INFO.getCommand(), payload);
			clientModel.WriteToServer(packet.GetMessageBytes());
            textLabel.setText(textLabel.getText()+clientModel.model_String);
            //half second delay introduced to avoid having the packet controller string called before robot can respond to the command
            try {
                Thread.sleep(500);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            textLabel.setText(textLabel.getText()+PacketController.controller_String);
        }
    }
    private class Button10Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            byte[] payload = PacketUtil.GetMacAddrPayload();
			Packet packet = PacketModel.createPacket(CommandType.SW_R_MAC_ADDRESS.getCommand(), payload);
			clientModel.WriteToServer(packet.GetMessageBytes());
            textLabel.setText(textLabel.getText()+clientModel.model_String);
            //half second delay introduced to avoid having the packet controller string called before robot can respond to the command
            try {
                Thread.sleep(500);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            textLabel.setText(textLabel.getText()+PacketController.controller_String);
        }
    }
    
}
