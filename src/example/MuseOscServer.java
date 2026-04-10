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

public class MuseOscServer {

	static MuseOscServer museOscServer;
	
	OscP5 museServer;

	//Change this to match the port the Muse direct is sending data to
	static int recvPort = 5002; 

	public static void main(String [] args) throws FileNotFoundException {

    File filename = new File("sam-base.csv");

    // Enable auto-flush so data is written immediately
    PrintStream o = new PrintStream(new java.io.FileOutputStream(filename), true);
    System.setOut(o);

    // Initialize the object FIRST, then OscP5 inside the constructor
    museOscServer = new MuseOscServer();

    System.out.println("TimeStamp, Alpha, Beta, Delta, Gamma, Theta");
}
public MuseOscServer() {
    museServer = new OscP5(this, recvPort);
}
	
void oscEvent(OscMessage msg) {
    try {
        if(msg.checkAddrPattern("/muse/elements/alpha_absolute")==true) {
            Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
            float alpha = (Float) msg.arguments()[0];
            System.out.print("\n" + timeStamp + "," + alpha + ",");
        }
        if(msg.checkAddrPattern("/muse/elements/beta_absolute")==true) {
            float beta = (Float) msg.arguments()[0];
            System.out.print(beta + ",");
        }
        if(msg.checkAddrPattern("/muse/elements/delta_absolute")==true) {
            float delta = (Float) msg.arguments()[0];
            System.out.print(delta + ",");
        }
        if(msg.checkAddrPattern("/muse/elements/gamma_absolute")==true) {
            float gamma = (Float) msg.arguments()[0];
            System.out.print(gamma + ",");
        }
        if(msg.checkAddrPattern("/muse/elements/theta_absolute")==true) {
            float theta = (Float) msg.arguments()[0];
            System.out.print(theta + ",");
        }
    } catch (Exception e) {
        System.err.println("Error in oscEvent: " + e.getMessage());
    }
}
}
