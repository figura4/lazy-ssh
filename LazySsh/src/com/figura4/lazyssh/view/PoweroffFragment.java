package com.figura4.lazyssh.view;

import com.figura4.lazyssh.R;
import com.figura4.lazyssh.model.SshRequestThread;

import android.app.Fragment;
import android.content.SharedPreferences;
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
	protected SshRequestThread thread;
	
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
        console.setText("Case@wintermute:~/Documents/Dflatline$ _\n\n");
        
        return rootView;
    }
    
    @Override
    public void onPause(){
    	super.onPause();
    }
    
    @Override
    public void onStop(){
    	super.onStop();
    	if (thread != null) {
	    	thread.cancel(true);
	    	thread = null;
    	}
    }
    
    @Override
    public void onDestroy(){
    	super.onDestroy();
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    }

    @Override
    public void onClick(View v) {
        PowerOff(v);
    }
    
	public void PowerOff(View v){
    	console.append(v.getResources().getString(R.string.message_connecting));
    	SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
    	String ipAddress = preferences.getString("nas_ip_address", "");
    	String sshUsername = preferences.getString("nas_ssh_username", "");
    	String sshPassword = preferences.getString("nas_ssh_password", "");
    	String sshPort = preferences.getString("nas_ssh_port", "");
    	String command = preferences.getString("nas_command", "");
    	thread = new SshRequestThread(ipAddress, sshUsername, sshPassword, sshPort, command, this.getActivity());
    	thread.execute();
	}
}
