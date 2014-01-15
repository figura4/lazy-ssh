package com.figura4.lazyssh.model;

import android.os.AsyncTask;

public class SshRequestThread extends AsyncTask<Void, Void, String> {
    
	protected String ipAddress;
	protected String username;
	protected String password;
	protected String port;
	protected String command;
	
	public SshRequestThread(String ipAddress, String username, String password, String port, String command) {
		this.ipAddress = ipAddress;
		this.username = username;
		this.password = password;
		this.port = port;
		this.command = command;
	}
	
	protected String doInBackground(Void...voids) {
    	String result = "";
    	
    	Ssh ssh = new Ssh(ipAddress, username, password, port);
    	result += ssh.executeCommand(command);
    	result += "finished.";
    	return result;
    }

    protected void onPostExecute(String result) {
    	
    }  
}
