package com.example.sensor;

import java.io.IOException;
import java.util.List;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class Gpscoordinates extends Activity implements LocationListener {
	Button button1=null;
	Button button2=null;
	EditText txt=null;
	int count = 0;
	String destinationLatitude="";
	String destinationLongitude="";
	 
	LocationListener locationListener=null;
	LocationManager locationManager=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gpscoordinates);
		 button1=(Button)findViewById(R.id.stop_location);
		    txt=(EditText)findViewById(R.id.locationText);
		    button2=(Button)findViewById(R.id.find_location);
		    Bundle extras=getIntent().getExtras();
			String text=extras.getString(Intent.EXTRA_SUBJECT);
			txt.setText(text);
		    button2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				
					Geocoder geocoder = new Geocoder(getBaseContext());  
					List<Address> addresses;
					try {
						
							addresses = geocoder.getFromLocationName(txt.getText().toString(), 20);
							Address addr = addresses.get(0);
						    destinationLatitude = Double.toString(addr.getLatitude());
						    destinationLongitude = Double.toString(addr.getLongitude()); 
						    startLocating();
						    
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
			});
		    
		    
		    button1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					stopLocating();
				}
			});
	}
		    public synchronized void stopLocating() {
		  	  locationManager.removeUpdates(locationListener);
		  		Toast.makeText(Gpscoordinates.this,"location status removed",Toast.LENGTH_SHORT).show();
		  	   
		  	  }
		    
		    public synchronized void startLocating() 
		    {
		  	 
		  	  locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		  	  locationListener = new LocationListener() {
		  	        public void onLocationChanged(Location location)
		  	        {
		  	        	String newLatitude = Double.toString(location.getLatitude());
		  	        	String newLongitude = Double.toString(location.getLongitude());
		  	        	double distance=gps2m(Float.parseFloat(destinationLatitude),Float.parseFloat(destinationLongitude), Float.parseFloat(newLatitude), Float.parseFloat(newLongitude));
		  	        	distance=distance/1000;
		  	        	
		  	        	Toast.makeText(Gpscoordinates.this,"Distance Left(Km):"+distance+"\nCurrent Location: ->("+ newLatitude + "," + newLongitude+")",Toast.LENGTH_SHORT).show();
		  	        	count++;
		  	        }
		  		public void onStatusChanged(String provider, int status, Bundle extras) {
		  		
		  		}
		  		public void onProviderEnabled(String provider) {
		  		
		  		}
		  		public void onProviderDisabled(String provider) {
		  	
		  		}
		  		
		  	};
		  	locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
		  	}
		  	   
		  	 

		  @Override
		  public void onLocationChanged(Location location) {
		  	// TODO Auto-generated method stub
		  	
		  }



		  @Override
		  public void onStatusChanged(String provider, int status, Bundle extras) {
		  	// TODO Auto-generated method stub
		  	
		  }



		  @Override
		  public void onProviderEnabled(String provider) {
		  	// TODO Auto-generated method stub
		  	
		  }
		  private double gps2m(float lat_a, float lng_a, float lat_b, float lng_b) {
		      float pk = (float) (180/3.14169);

		      float a1 = lat_a / pk;
		      float a2 = lng_a / pk;
		      float b1 = lat_b / pk;
		      float b2 = lng_b / pk;

		      float t1 = FloatMath.cos(a1)*FloatMath.cos(a2)*FloatMath.cos(b1)*FloatMath.cos(b2);
		      float t2 = FloatMath.cos(a1)*FloatMath.sin(a2)*FloatMath.cos(b1)*FloatMath.sin(b2);
		      float t3 = FloatMath.sin(a1)*FloatMath.sin(b1);
		      double tt = Math.acos(t1 + t2 + t3);
		     
		      return 6366000*tt;
		  }


		  @Override
		  public void onProviderDisabled(String provider) {
		  	// TODO Auto-generated method stub
		  	
		  }
		  }
