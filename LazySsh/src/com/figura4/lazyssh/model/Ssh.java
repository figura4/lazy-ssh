package com.figura4.lazyssh.model;

import java.io.ByteArrayOutputStream;
import java.util.Properties;

import android.util.Log;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class Ssh {
	protected String nasIpAddress;
	protected String nasSshUsername;
	protected String nasSshPassword;
	protected String nasSshPort;
	protected String result = "";
	
	/**
	 * Class constructor
	 * @param nasIpAddress
	 * @param nasSshUsername
	 * @param nasSshPassword
	 * @param nasSshPort
	 * @param nasCommand
	 */
	public Ssh(String nasIpAddress, String nasSshUsername, String nasSshPassword, String nasSshPort) {
    	this.nasIpAddress = nasIpAddress;
    	this.nasSshUsername = nasSshUsername;
    	this.nasSshPassword = nasSshPassword;
    	this.nasSshPort = nasSshPort;
	}
	
	public String executeCommand(String command) {
    	try { 
    		result += "setting up connection&#8230;\n";
	    	JSch jsch = new JSch();
			Session session = jsch.getSession(nasSshUsername, nasIpAddress, Integer.parseInt(nasSshPort));
			session.setPassword(nasSshPassword);
			
			// Avoid asking for key confirmation
			Properties prop = new Properties();
			prop.put("StrictHostKeyChecking", "no");
			session.setConfig(prop);
			
			result += "connecting&#8230;\n";
			session.connect();
			
			// SSH Channel
			ChannelExec channelssh = (ChannelExec) 
			                session.openChannel("exec");      
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			channelssh.setOutputStream(baos);
			
			// Execute command
			result += "sending command&#8230;\n";
			channelssh.setCommand(command);
			channelssh.connect();        
			channelssh.disconnect();
			
			Log.d("lazyssh", baos.toString());
			result += baos.toString() + "\n";
			result += "command sent.";
			return result;
    	} 
    	catch (Exception e) 
    	{
    		Log.d("lazyssh", e.getMessage());
    		return result + "\n" + e.getMessage();
    	}
	}
}
