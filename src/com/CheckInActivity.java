package www.commice.com;




import www.commice.com.interfaces.IAppManager;
import www.commice.com.services.IMService;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CheckInActivity extends Activity implements SensorListener {
	
	private static final String LOG_TAG = "AddFriend";
	
 // For shake motion detection.
 	private SensorManager sensorMgr;
 	private long lastUpdate = -1;
 	private float x, y, z;
 	private float last_x, last_y, last_z;
 	private static final int SHAKE_THRESHOLD = 800;
	
 	
 	private static IAppManager mImService;
 	
 	TextView tvLocation;
 	TextView tvAddress;

 	String address = "";
 	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// note that use read_comments.xml instead of our single_post.xml
		setContentView(R.layout.checkin);
		

		
        //////////////////this is called so when the option is selected the
      //pops up come in
        
        ImageView btnCreatePopup = (ImageView) findViewById(R.id.optionmenu);
        btnCreatePopup.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
        // TODO Auto-generated method stub
        initiatePopupWindow();
        }
        });
         
        
        
        LinearLayout back = (LinearLayout) findViewById(R.id.back);
        back.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
        // TODO Auto-generated method stub
        finish();
        }
        });
         
  
//services
        
        
      


    // gps
	
	tvLocation = (TextView) findViewById(R.id.tvLocation);
	tvAddress = (TextView) findViewById(R.id.tvAddress);
	
	
	
		
	        
	    	// start motion detection
			sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
			boolean accelSupported = sensorMgr.registerListener(this,
			    SensorManager.SENSOR_ACCELEROMETER,
			    SensorManager.SENSOR_DELAY_GAME);

			if (!accelSupported) {
			    // on accelerometer on this device
			    sensorMgr.unregisterListener(this,
			            SensorManager.SENSOR_ACCELEROMETER);
			
			}
			}

	  private final ServiceConnection mConnection = new ServiceConnection() {
          @Override
  		public void onServiceConnected(ComponentName className, IBinder service) {
              mImService = ((IMService.IMBinder)service).getService();
          }

          @Override
  		public void onServiceDisconnected(ComponentName className) {
              if (mImService != null) {
                  mImService = null;
              }

              Toast.makeText(CheckInActivity.this, R.string.local_service_stopped, Toast.LENGTH_SHORT).show();
          }
      };
	
			@Override
			protected void onPause() {
			if (sensorMgr != null) {
			    sensorMgr.unregisterListener(this,
			            SensorManager.SENSOR_ACCELEROMETER);
			    sensorMgr = null;
			    }
			

	        if (mConnection != null) {
	            unbindService(mConnection);
	        } else {
	            Log.e(LOG_TAG, "onResume: mConnection is null");
	        }
	        
			super.onPause();
			}

			@Override
			public void onAccuracyChanged(int arg0, int arg1) {
			// TODO Auto-generated method stub
			}



			   private void initiatePopupWindow() {
			   		 final String[] options = new String[] {"Cancel"};
			   		 ArrayAdapter<String> newsadapter = new ArrayAdapter<String>(this,
			   		      android.R.layout.select_dialog_item, options);
			   		final AlertDialog.Builder newsbuilder = new AlertDialog.Builder(this);
			   		newsbuilder.setIcon(R.drawable.option);

			   		 newsbuilder.setTitle("Options");
			   		 newsbuilder.setAdapter(newsadapter, new DialogInterface.OnClickListener() {

			   		 @Override
					public void onClick(DialogInterface dialogs, int which) {
			   		      // TODO Auto-generated method stub
			   		 	 if (which==0)
			   		 	 {
			   		 	 	finish();	   		 		 
			   		 	 }
			   		 }
			   		 });
			   }
			   
			   ///service here
			   @Override
			    protected void onResume() {
			        super.onResume();

			        Intent intent = new Intent(this, IMService.class);
			        if (mConnection != null) {
			            bindService(intent, mConnection , Context.BIND_AUTO_CREATE);
			        } else {
			        	Intent intent2 = new Intent(this, Login.class);
			        	startActivity(intent2);
			        	finish();
			            Log.e(LOG_TAG, "onResume: mConnection is null");
			        }

			    }


			@Override
			public void onSensorChanged(int sensor, float[] values) {
			if (sensor == SensorManager.SENSOR_ACCELEROMETER) {
			    long curTime = System.currentTimeMillis();
			    // only allow one update every 100ms.
			    if ((curTime - lastUpdate)> 200) {
			    long diffTime = (curTime - lastUpdate);
			    lastUpdate = curTime;

			    x = values[SensorManager.DATA_X];
			    y = values[SensorManager.DATA_Y];
			    z = values[SensorManager.DATA_Z];

			    float speed = Math.abs(x+y+z - last_x - last_y - last_z)
			                          / diffTime * 8000;
			    if (speed > SHAKE_THRESHOLD) {
			        // yes, this is a shake action! Do something about it!
			
			    	/////////////////the location
			
			GPSService mGPSService = new GPSService(getApplicationContext());
			mGPSService.getLocation();

			if (mGPSService.isLocationAvailable == false) {

				// Here you can ask the user to try again, using return; for that
				Toast.makeText(getApplicationContext(),"Your location is not available, Check your GPS, please try again.", Toast.LENGTH_SHORT).show();
				return;

				// Or you can continue without getting the location, remove the return; above and uncomment the line given below
				// address = "Location not available";
			} else {

				// Getting location co-ordinates
				double latitude = mGPSService.getLatitude();
				double longitude = mGPSService.getLongitude();
				
//				Toast.makeText(getApplicationContext(), "Latitude:" + latitude + " | Longitude: " + longitude, Toast.LENGTH_LONG).show();
				address = mGPSService.getLocationAddress();

				tvLocation.setText("Latitude: " + latitude + " \nLongitude: " + longitude);
				tvAddress.setText("Address: " + address);
				
				
				 if (address.length() > 0) {
			          

			    		if(mImService.isConnectedToInternet(CheckInActivity.this.getApplicationContext())) {
				    		
			    			
		                	if(address.equals("IO Exception trying to get address:java.io.IOException: Service not Available")){
		                	
		                		Toast.makeText(CheckInActivity.this, "(GPS) Address not located, you can update your address in your account", Toast.LENGTH_SHORT).show();
		                	}
		                	else
		                	{
		                		  // TODO: A thread is really needed ?
					            Thread thread = new Thread() {
					                @Override
					                public void run() {
					                    // TODO: Please check if the request is successful and raise a error message if needed.
					                	mImService.updateAddress(address+"");    	
					                }
					            };
					            thread.start();
					            
					            Toast.makeText(CheckInActivity.this, "Address Updated into your profile", Toast.LENGTH_SHORT).show();

					        	Intent intent2 = new Intent(CheckInActivity.this, Accounts_List.class);
					        	startActivity(intent2);
					            
					            finish();				    			
		                		
		                	}
				            

			    		}
			    		else
			    		{
			    			Toast.makeText(CheckInActivity.this, "Check internet connection", Toast.LENGTH_LONG).show();
			    			/////////put a dialog that you can click to make your internet settings
			    		}
			            // TODO: Show the toast only if the sent of the request is successful

			        } else {
			            
			            Toast.makeText(CheckInActivity.this, "Address Required! ", Toast.LENGTH_LONG).show();
			        }
				
				
				////submit address to databse
			}

			
			Toast.makeText(getApplicationContext(), "Your address is: " + address, Toast.LENGTH_LONG).show();
			// make sure you close the gps after using it. Save user's battery power
			mGPSService.closeGPS();
			    	
			
			    }
			    last_x = x;
			    last_y = y;
			    last_z = z;
			    }
			}
			
	        


			}
        
}
