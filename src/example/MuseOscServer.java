/*
*
* Developed by Sai Shreya Nuguri
* Department of EECS
* University of Missouri
*
*/

/* This program runs a MuseOscServer that accepts the 
*data being sent by the Muse EEG headband, writes the 
*Alpha, Beta, Gamma, Delta and Theta signals into a local .csv file
*/


package example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Timestamp;

import oscP5.*;

public class MuseOscServernew {

	static MuseOscServer museOscServer;
	
	OscP5 museServer;

	//Change this to match the port the Muse direct is sending data to
	static int recvPort = 5002; 

	public static void main(String [] args) throws FileNotFoundException {
	
		//Creates a .csv file to write the EEG data into
		File filename = new File("sam-base.csv");

		//Set the printstream to the filename
		PrintStream o = new PrintStream(filename);
		System.setOut(o);
		
		museOscServer = new MuseOscServer();
		//calling the Muse server using the OscP5 protocol: OscP5(theParent, ReceivingPort)

		museOscServer.museServer = new OscP5(museOscServer, recvPort);
		System.out.println("TimeStamp, Alpha, Beta, Delta, Gamma, Theta");
	}
	
	void oscEvent(OscMessage msg) throws IOException{
		
		//Function runs for every oscEvent = for example, for every iteration of EEG data being received

		Object[] arguments = msg.arguments();		
		
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		
		//Muse EEG headband sends a lot of signals but we are only interested in Alpha, Beta, Gamma, Delta and Theta
		//For more information, visit: http://developer.choosemuse.com/tools/available-data

		
		//Checking if the signal is Alpha
		
		if(msg.checkAddrPattern("/muse/elements/alpha_absolute")==true) {
		
			System.out.print("\n"+timeStamp+",");
			
			arguments = msg.arguments();
			System.out.println("float"+msg.get(0).floatValue()+", int:"+msg.get(0).intValue()+",string:"+msg.get(0).toString()+",long:"+msg.get(0).longValue()+",double"+msg.get(0).doubleValue());
			
			//printing it out into the .csv file in a way that the average is calculated and written
		
			System.out.print("=("+arguments[0]+"+ ");
			System.out.print(arguments[1]+"+");
			System.out.print(arguments[2]+"+");
			System.out.print(arguments[3]+")/4,");
		}	
	
		
		//Check for Beta signal
		if(msg.checkAddrPattern("/muse/elements/beta_absolute")==true) {
			
			arguments = msg.arguments();
			
			System.out.print("=("+arguments[0]+"+ ");
			System.out.print(arguments[1]+"+");
			System.out.print(arguments[2]+"+");
			System.out.print(arguments[3]+")/4,");
		}

		//check for Delta signal	
		if(msg.checkAddrPattern("/muse/elements/delta_absolute")==true) {
			
			arguments = msg.arguments();
		
			System.out.print("=("+arguments[0]+"+ ");
			System.out.print(arguments[1]+"+");
			System.out.print(arguments[2]+"+");
			System.out.print(arguments[3]+")/4,");
		}
	
		//check for Gamma signal	
		if(msg.checkAddrPattern("/muse/elements/gamma_absolute")==true) {
			
			arguments = msg.arguments();
			
			System.out.print("=("+arguments[0]+"+ ");
			System.out.print(arguments[1]+"+");
			System.out.print(arguments[2]+"+");
			System.out.print(arguments[3]+")/4,");
		}

		//Check for Theta signal
		if(msg.checkAddrPattern("/muse/elements/theta_absolute")==true) {
			
			arguments = msg.arguments();
			
			System.out.print("=("+arguments[0]+"+ ");
			System.out.print(arguments[1]+"+");
			System.out.print(arguments[2]+"+");
			System.out.print(arguments[3]+")/4,");
		}	
		
	} 
}
