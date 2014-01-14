package com.figura4.lazyssh.view;

import com.figura4.lazyssh.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SplashFragment extends Fragment {
    public SplashFragment() {
        // Empty constructor required for fragment subclasses
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_splash, container, false);
        getActivity().setTitle(rootView.getResources().getString(R.string.title_splash_fragment));
        
        return rootView;
    }
}
