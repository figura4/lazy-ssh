package com.figura4.lazyssh.view;

import com.figura4.lazyssh.R;
import com.figura4.lazyssh.model.WolRequestThread;
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

public class WakeOnLanFragment extends Fragment implements OnClickListener {
	protected EditText console;
	protected WolRequestThread thread;
	
    public WakeOnLanFragment() {
        // Empty constructor required for fragment subclasses
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_wake_on_lan, container, false);
        getActivity().setTitle(rootView.getResources().getString(R.string.title_wake_on_lan_fragment));
        Button btn = (Button) rootView.findViewById(R.id.button_wol);     
        btn.setOnClickListener(this);
        
        console = (EditText) rootView.findViewById(R.id.edit_console);
        console.setText("Jlebowski@thedude~/Documents/AboutNihilism$ _\n\n");
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
        wake(v);
    }
    
	public void wake(View v){
    	console.append(v.getResources().getString(R.string.message_wol_attempt));
    	
    	SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
    	String ipAddress = preferences.getString("nas_ip_address", "");
    	String macAddress = preferences.getString("nas_mac_address", "");
    	
    	thread = new WolRequestThread(ipAddress, macAddress, this.getActivity());
    	thread.execute();
	}
}
