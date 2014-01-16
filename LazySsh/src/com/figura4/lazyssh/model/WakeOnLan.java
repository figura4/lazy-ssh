package com.figura4.lazyssh.model;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class WakeOnLan {
	public static final int PORT9 = 9;    
	public static final int PORT7 = 7;  
	protected String ipAddress;
	protected String macAddress;

	public WakeOnLan(String ipAddress, String macAddress) {
		this.ipAddress = ipAddress;
		this.macAddress = macAddress;
	}
	
	public String wake() {
		String result = "";
	    try {
	        byte[] macBytes = getMacBytes(macAddress);
	        byte[] bytes = new byte[6 + 16 * macBytes.length];
	        for (int i = 0; i < 6; i++) {
	            bytes[i] = (byte) 0xff;
	        }
	        for (int i = 6; i < bytes.length; i += macBytes.length) {
	            System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
	        }

	        InetAddress address = InetAddress.getByName(ipAddress);
	        
	        DatagramPacket packet7 = new DatagramPacket(bytes, bytes.length, address, PORT7);
	        result += "Magic packet sent on UDP port 7...\n\n";
	        DatagramPacket packet9 = new DatagramPacket(bytes, bytes.length, address, PORT9);
	        result += "Magic packet sent on UDP port 9...\n\n";
	        DatagramSocket socket = new DatagramSocket();
	        
	        socket.send(packet7);
	        socket.send(packet9);
	        
	        socket.close();

	        return result + "Wake-on-LAN packets sent.\n";
	    }
	    catch (Exception e) {
	    	return e.getMessage();
	    }

	}

	private static byte[] getMacBytes(String macStr) throws IllegalArgumentException {
	    byte[] bytes = new byte[6];
	    String[] hex = macStr.split("(\\:|\\-)");
	    if (hex.length != 6) {
	        throw new IllegalArgumentException("Invalid MAC address.\n");
	    }
	    try {
	        for (int i = 0; i < 6; i++) {
	            bytes[i] = (byte) Integer.parseInt(hex[i], 16);
	        }
	    }
	    catch (NumberFormatException e) {
	        throw new IllegalArgumentException("Invalid hex digit in MAC address.\n");
	    }
	    return bytes;
	}
}
