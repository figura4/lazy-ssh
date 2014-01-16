package com.figura4.lazyssh.model;

import com.figura4.lazyssh.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.EditText;

public class SshRequestThread extends AsyncTask<Void, Void, String> {
    
	protected String ipAddress;
	protected String username;
	protected String password;
	protected String port;
	protected String command;
	protected EditText console;
	protected Activity context;
	
	public SshRequestThread(String ipAddress, String username, String password, String port, String command, Activity context) {
		this.ipAddress = ipAddress;
		this.username = username;
		this.password = password;
		this.port = port;
		this.command = command;
		this.context = context;
		console = (EditText) context.findViewById(R.id.edit_console);
	}
	
	protected String doInBackground(Void...voids) {
    	String result = "";
    	
    	Ssh ssh = new Ssh(ipAddress, username, password, port);
    	result += ssh.executeCommand(command);
    	return result;
    }

    protected void onPostExecute(String result) {
    	console.append(result + "\n\n");
    	console.append(context.getResources().getString(R.string.message_finished));
    }  
}
