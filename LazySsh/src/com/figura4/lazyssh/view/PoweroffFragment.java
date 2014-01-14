package com.figura4.lazyssh.view;

import com.figura4.lazyssh.R;
import com.figura4.lazyssh.model.Ssh;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class PoweroffFragment extends Fragment implements OnClickListener {
	protected EditText console;
	
    public PoweroffFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_turn_off, container, false);
        getActivity().setTitle(rootView.getResources().getString(R.string.title_poweroff_fragment));
        Button btn = (Button) rootView.findViewById(R.id.button_poweroff);     
        btn.setOnClickListener(this);
        
        console = (EditText) rootView.findViewById(R.id.edit_console);
        console.setText("case@wintermute:~/documents/d_flatline$ _\n");
        
        return rootView;
    }
    
    @Override
    public void onClick(View v) {
        PowerOff(v);
    }
    
	public void PowerOff(View v){
    	console.append(v.getResources().getString(R.string.message_connecting));
    	new SshRequestThread().execute();
	}
	
	public class SshRequestThread extends AsyncTask<Void, Void, String> {
	    
		protected String doInBackground(Void...voids) {
	    	SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
	    	String ipAddress = preferences.getString("nas_ip_address", "");
	    	String sshUsername = preferences.getString("nas_ssh_username", "");
	    	String sshPassword = preferences.getString("nas_ssh_password", "");
	    	String sshPort = preferences.getString("nas_ssh_port", "");
	    	String command = preferences.getString("nas_command", "");
	    	String result = "";
	    	
	    	Ssh ssh = new Ssh(ipAddress, sshUsername, sshPassword, sshPort);
	    	result += ssh.executeCommand(command);
	    	result += "finished.";
	    	return result;
	    }

	    protected void onPostExecute(String result) {
	    	console.append(result);
	    }  
	}
}
