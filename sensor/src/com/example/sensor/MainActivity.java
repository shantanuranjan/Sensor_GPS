package com.example.sensor;


import java.util.List;




import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity  {
	Button bt1=null;
	
  
/** Called when the activity is first created. */

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    bt1=(Button)findViewById(R.id.Find_destination);
    bt1.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			startTripCreator();
			
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			String dest=getResources().getString(R.string.app_name);
			System.out.println(dest);
			sendIntent.putExtra(Intent.EXTRA_TEXT, dest);
			sendIntent.putExtra(Intent.EXTRA_SUBJECT, dest);
			sendIntent.setType("text/plain");
			startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.app_name)));
		}
	});
   
    
    }
  public void startTripCreator() {
		
		// TODO - fill in here
		Uri location = Uri.parse("geo:0,0?q=");
		Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
		
		//Check if such app exists
		PackageManager packageMananger = getPackageManager();
		List<ResolveInfo> activities = packageMananger.queryIntentActivities(mapIntent, 0);				
		if(activities.size() > 0) {
			startActivity(mapIntent);
			Log.d("MAP", "Map applications available");
		} else {
			Log.d("MAP", "No Map applications available");
		}
	}
}