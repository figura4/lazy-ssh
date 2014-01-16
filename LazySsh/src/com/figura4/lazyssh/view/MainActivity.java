package com.figura4.lazyssh.view;

import com.figura4.lazyssh.R;
import com.figura4.lazyssh.view.SettingsFragment;
import com.figura4.lazyssh.view.PoweroffFragment;
import com.figura4.lazyssh.adapters.DrawerArrayAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends Activity {

	// Drawer management variables
    private String[] mMenuTitles;
    private String[] mMenuSubTitles;
    private String[] mMenuIcons;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    //private CharSequence mDrawerTitle;
    //private CharSequence mTitle;
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Loading fragment to sohw at startup
		Fragment fragment;
    	FragmentManager fragmentManager = getFragmentManager();
    	fragment = new SplashFragment();
    	fragmentManager.beginTransaction()
    	       .replace(R.id.content_frame, fragment)
    	       .commit();
		
    	// setting up drawer menu
		mMenuTitles = getResources().getStringArray(R.array.menu_array);
		mMenuSubTitles = getResources().getStringArray(R.array.menu_array_line2);
		mMenuIcons = getResources().getStringArray(R.array.menu_array_icons);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        
        // Set the adapter for the list view
        mDrawerList.setAdapter(new DrawerArrayAdapter(this, mMenuTitles, mMenuSubTitles, mMenuIcons));
        //mDrawerList.setAdapter(new ArrayAdapter<String>(this,
        //        R.layout.drawer_list_item, mMenuTitles));
        
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        
        //mTitle = mDrawerTitle = getTitle();
        
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
                ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(view.getResources().getString(R.string.app_name));
                getActionBar().setSubtitle(getTitle());
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(drawerView.getResources().getString(R.string.app_name));
                getActionBar().setSubtitle("Pick your shortcut");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setTitle(this.getResources().getString(R.string.app_name));
        getActionBar().setSubtitle(this.getResources().getString(R.string.title_splash_fragment));
	}
    
    public class DrawerItemClickListener implements ListView.OnItemClickListener {
	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        selectItem(position);
	    }
	}
	
    private void selectItem(int position) {
    	Fragment fragment;
    	FragmentManager fragmentManager = getFragmentManager();
    	
    	switch (position) {
    		case 0:
    			fragment = new PoweroffFragment();
    			fragmentManager.beginTransaction()
    	        	.replace(R.id.content_frame, fragment)
    	        	.commit();
    			break;
    			
    		case 1:
    			fragment = new WakeOnLanFragment();
    			fragmentManager.beginTransaction()
    	        	.replace(R.id.content_frame, fragment)
    	        	.commit();
    			break;
    			
    		case 2:
    			fragment = new SettingsFragment();
    			fragmentManager.beginTransaction()
    	        	.replace(R.id.content_frame, fragment)
    	        	.commit();
                break;
    			
    		case 3:
    			Intent intent = new Intent(Intent.ACTION_MAIN);
	        	intent.addCategory(Intent.CATEGORY_HOME);
	        	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        	startActivity(intent);
    			break;
    	}

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mMenuTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }   
    
    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        //boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
          return true;
        }
        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onPause(){
    	super.onPause();
    }
    
    @Override
    public void onStop(){
    	super.onStop();
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
    public void onRestart(){
    	super.onRestart();
    }
}
