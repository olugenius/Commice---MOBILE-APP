// TODO: Ogunwande Timothy Oluwole The Developer and programmer
// this project was started 2/06/2013
///this section was written 2/08/2014

package www.commice.com;



import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import www.commice.com.AddFriendByShake.JSONAsyncTask;
import www.commice.com.interfaces.IAppManager;
import www.commice.com.services.IMService;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


// TODO: Add javadoc
public class AddFriendByShake extends Activity implements SensorListener {

  ///list
	
	
	ArrayList<ShakeStringer> ShakeStringerList;
	
	ShakeAdapter adapter;

	JSONParser jsonParser = new JSONParser();

	//////////////////////////////////////////////////////////////////////////
	private static final String location_url ="http://192.168.43.100/android-im/getlocation.php";
	private String getlocations="";	
	/////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
    private static Button mAddFriendButton;
    private static Button mCancelButton;
    private static EditText mFriendUserNameText;

    private static IAppManager mImService;

    private static final int TYPE_FRIEND_USERNAME = 0;
    private static final String LOG_TAG = "AddFriend";

    /*
     * shake stuff
     */
	private SensorManager sensorMgr;
	private long lastUpdate = -1;
	private float x, y, z;
	private float last_x, last_y, last_z;
	private static final int SHAKE_THRESHOLD = 800;
    
	
	/*
	 * location 
	 */
	  private double lat; 
	  private double lon; 
	      
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_new_friendbyshake);
        //setTitle(getString(R.string.add_new_friend));
/*
        mAddFriendButton = (Button)findViewById(R.id.addFriend);
        mCancelButton = (Button)findViewById(R.id.cancel);
        mFriendUserNameText = (EditText)findViewById(R.id.newFriendUsername);

        if (mAddFriendButton != null) {
            mAddFriendButton.setOnClickListener(this);
        } else {
        	
        	Toast toast = Toast.makeText(getApplicationContext(), "Please Enter The Username You Want To Add", Toast.LENGTH_SHORT);
        	Log.e(LOG_TAG, "onCreate: mAddFriendButton is null");
            throw new NullPointerException("onCreate: mAddFriendButton is null");
       
        }

        if (mCancelButton != null) {
            mCancelButton.setOnClickListener(this);
        } else {
            Log.e(LOG_TAG, "onCreate: mCancelButton is null");
            throw new NullPointerException("onCreate: mCancelButton is null");
        }
        
        
 */
        
	
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

    	@Override
		public void onAccuracyChanged(int arg0, int arg1) {
    	// TODO Auto-generated method stub
    	}

    	@Override
		public void onSensorChanged(int sensor, float[] values) {
    	if (sensor == SensorManager.SENSOR_ACCELEROMETER) {
    	    long curTime = System.currentTimeMillis();
    	    // only allow one update every 100ms.
    	    if ((curTime - lastUpdate)> 100) {
    	    long diffTime = (curTime - lastUpdate);
    	    lastUpdate = curTime;

    	    x = values[SensorManager.DATA_X];
    	    y = values[SensorManager.DATA_Y];
    	    z = values[SensorManager.DATA_Z];

    	    float speed = Math.abs(x+y+z - last_x - last_y - last_z)
    	                          / diffTime * 10000;
    	    if (speed > SHAKE_THRESHOLD) {
    	        // yes, this is a shake action! Do something about it!
    
    	    	
    	    	if(mImService.isConnectedToInternet(AddFriendByShake.this.getApplicationContext())) {
    				
    			
    	        Thread thread = new Thread() {
                    @Override
                    public void run() {
                        // TODO: Please check if the request is successful and raise a error message if needed.
            	    	mImService.addNewFriendRequestByShake();

                    
                    
                    

        				try
        				{
        				
        				List<NameValuePair> paramsx = new ArrayList<NameValuePair>();
        				paramsx.add(new BasicNameValuePair("username",IMService.USERNAME));
        			
//        				params.add(new BasicNameValuePair("jospecializations",jospecializations));
//        				params.add(new BasicNameValuePair("joblevels",joblevels));

        				
        				JSONObject jsonx = jsonParser.makeHttpRequest(location_url,
        						"POST", paramsx);
        				
        		JSONArray jarray = jsonx.getJSONArray("locations");
        					
        					
        					for (int i = 0; i < jarray.length(); i++) {
        						JSONObject object = jarray.getJSONObject(i);
        					
        						//ShakeStringer stringer = new ShakeStringer();
        						
        						getlocations=object.getString("location").toString();
        		//				locationt.setText(""+object.getString("location"));
        						//stringer.setUsername(object.getString("username"));
        						
        					
        						//ShakeStringerList.add(stringer);
        						
        					}
        				//	Toast.makeText(getApplicationContext(),""+text, Toast.LENGTH_LONG).show();
        				
//        					Toast.makeText(getApplicationContext(),""+text, Toast.LENGTH_LONG).show();
        			} catch (ParseException e1) {
        				e1.printStackTrace();
        			}  catch (JSONException e) {
        				e.printStackTrace();
        				
        			}
                        

                    
                    
                    
                    }
                };
                thread.start();

	            //String location=json1.getString("location");
	               
				
			//	Toast.makeText(getApplicationContext(),""+getlocationss, Toast.LENGTH_LONG).show();

    	    	
                
    	    Toast.makeText(this, "Shake Made! Fetching Friends Around "+getlocations+" Area", Toast.LENGTH_SHORT).show();
                    
    	    	 
    	    	ShakeStringerList = new ArrayList<ShakeStringer>();
    	 		new JSONAsyncTask().execute("http://192.168.43.100/android-im/shake_list.php");
    	 		
    	 		ListView listview = (ListView)findViewById(R.id.list);
    	 		adapter = new ShakeAdapter(getApplicationContext(), R.layout.shake_row, ShakeStringerList);
    	 		
    	 		
    	 		listview.setAdapter(adapter);
    	 		
    	 		listview.setOnItemClickListener(new OnItemClickListener() {

    	 			@Override
    	 			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
    	 					long id) {
    	 				// TODO Auto-generated method stub
    	 				//Toast.makeText(getApplicationContext(), ShakeStringerList.get(position).getUsername(), Toast.LENGTH_LONG).show();				
    	 				if(mImService.isConnectedToInternet(AddFriendByShake.this.getApplicationContext())) {
    	 					
    	 				addNewFriend(ShakeStringerList.get(position).getUsername());
    	 				}
    	 				else
    	 				{
    	 					Toast.makeText(AddFriendByShake.this, "Check internet connection", Toast.LENGTH_LONG).show();
    	 				}
    	 			}
    	 		});
    	    	 
    	    
				
    			}
    			else
    			{
    				Toast.makeText(AddFriendByShake.this, "Check internet connection", Toast.LENGTH_LONG).show();
    				/////////put a dialog that you can click to make your internet settings
    			} 
    	    	 
    	    	 
    	    }
    	    last_x = x;
    	    last_y = y;
    	    last_z = z;
    	    }
    	}
    	

    
        
        
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
            Thread thread = new Thread() {
                @Override
                public void run() {
                	
                	// TODO: Please check if the request is successful and raise a error message if needed.
                	
                	///this code remove this user from the shake list
                    mImService.removeOnShakeList(IMService.USERNAME);
                }
            };
            thread.start();

        	
        	finish();
        }
        });
         
        
        
    }
	
    
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
    protected void onPause() {
        super.onPause();

    	if (sensorMgr != null) {
    	    sensorMgr.unregisterListener(this,
    	            SensorManager.SENSOR_ACCELEROMETER);
    	    sensorMgr = null;
    	    }
    	super.onPause();
        
        
        if (mConnection != null) {
            unbindService(mConnection);
        } else {
            Log.e(LOG_TAG, "onResume: mConnection is null");
        }
    }

    /*
    @Override
    public void onClick(View view) {
        if (view == mCancelButton) {
            finish();
        } else if (view == mAddFriendButton) {
            addNewFriend();
        } else {
            Log.e(LOG_TAG, "onClick: view clicked is unknown");
        }
    }
*/
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

            Toast.makeText(AddFriendByShake.this, R.string.local_service_stopped, Toast.LENGTH_SHORT).show();
        }
    };

    // TODO: Remove deprecated method
    @Override
	protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddFriendByShake.this);
        if (id == TYPE_FRIEND_USERNAME) {
            builder.setTitle(R.string.add_new_friend)
                   .setMessage(R.string.type_friend_username)
                   .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                       @Override
					public void onClick(DialogInterface dialog, int whichButton) {
                           // TODO
                       }
                   });
        }

        return builder.create();
     }

    private void addNewFriend(String username) {
    	final String usernames=username;
        if (usernames.length() > 0) {
            // TODO: A thread is really needed ?
            Thread thread = new Thread() {
                @Override
                public void run() {
                    // TODO: Please check if the request is successful and raise a error message if needed.
                    mImService.addNewFriendRequest(usernames);
                }
            };
            thread.start();

            // TODO: Show the toast only if the sent of the request is successful
            Toast.makeText(AddFriendByShake.this, R.string.request_sent, Toast.LENGTH_SHORT).show();

            finish();
        } else {
            Log.e(LOG_TAG, "addNewFriend: username length (" + mFriendUserNameText.length() + ") is < 0");
            Toast.makeText(AddFriendByShake.this, R.string.type_friend_username, Toast.LENGTH_LONG).show();
        }
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


 		AlertDialog dialogs = newsbuilder.create();
 			 dialogs.show();
    }
    
    
    
    
    
    

	class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {
		
		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(AddFriendByShake.this);
			dialog.setMessage("Loading, please wait");
			dialog.setTitle("Fetching Friends");
			dialog.show();
			dialog.setCancelable(true);
		}
		
		@Override
		protected Boolean doInBackground(String... urls) {

			// Feed the beast our comments url, and it spits us
			// back a JSON object. Boo-yeah Jerome.

			
		
			
			
			try {
				
				
			
				 
	            //String location=json1.getString("location");
	               
				

				
				
				
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("username",IMService.USERNAME));
				params.add(new BasicNameValuePair("location",getlocations));
//				params.add(new BasicNameValuePair("jospecializations",jospecializations));
//				params.add(new BasicNameValuePair("joblevels",joblevels));

				
				JSONObject json = jsonParser.makeHttpRequest(urls[0],
						"POST", params);
				
		JSONArray jarray = json.getJSONArray("shakers");
					
					
					for (int i = 0; i < jarray.length(); i++) {
						JSONObject object = jarray.getJSONObject(i);
					
						ShakeStringer stringer = new ShakeStringer();
						
						
						stringer.setImage(object.getString("image"));
						stringer.setUsername(object.getString("username"));
						
					
						ShakeStringerList.add(stringer);
					}
					return true;
				
			} catch (ParseException e1) {
				e1.printStackTrace();
			}  catch (JSONException e) {
				e.printStackTrace();
				
			}/* catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		*/
			return false;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			dialog.cancel();
			adapter.notifyDataSetChanged();
			if(result == false)
				Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

		}
	}
	


	

    
    
    
    }

    
