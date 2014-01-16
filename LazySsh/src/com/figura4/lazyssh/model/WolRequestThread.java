package com.figura4.lazyssh.model;

import com.figura4.lazyssh.R;
import android.app.Activity;
import android.os.AsyncTask;
import android.widget.EditText;

public class WolRequestThread extends AsyncTask<Void, Void, String> {
    
	protected String ipAddress;
	protected String macAddress;
	protected Activity context;
	protected EditText console;
	
	public WolRequestThread(String ipAddress, String macAddress, Activity context) {
		this.ipAddress = ipAddress;
		this.macAddress = macAddress;
		this.context = context;
		console = (EditText) context.findViewById(R.id.edit_console);
	}
	
	protected String doInBackground(Void...voids) {
		String result = "";
    	WakeOnLan wol = new WakeOnLan(ipAddress, macAddress);
    	result += wol.wake();
    	return result;
    }

    protected void onPostExecute(String result) {
    	console.append(result + "\n");
    	console.append(context.getResources().getString(R.string.message_finished));
    }  
}