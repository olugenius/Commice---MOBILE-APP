package www.commice.com;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import www.commice.com.Accounts_List.JSONAsyncTask;
import www.commice.com.interfaces.IAppManager;
import www.commice.com.services.IMService;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import www.commice.com.services.IMService;
import www.commice.com.services.IMService.IMBinder;


public class EditMyAccount extends Activity {
	
	JSONParser jsonParser = new JSONParser();
	
	
	Button fullname;
	Button status;
	Button gender;
	Button age;
	Button hobbies;
	Button address;
	Button specialization;
	Button sp_experience;
	Button services;
	Button sv_experience;
	Button website;
	Button facebook;
	Button twitter;
	Button linkedin;
	
    /////////////the string persing to intent
	String vstatus;
	String vfullname;
	String vgender;
	String vage;
	String vhobbies;
	String vaddress;
	String vspecialization;
	String vsp_experience;
	String vservices;
	String vsv_experience;
	String vwebsite;
	String vfacebook;
	String vtwitter;
	String vlinkedin;
	
	
    ///for the service
    private static IAppManager mImService;
    
	private String USERNAME=IMService.USERNAME;    
    

	 

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

	            Toast.makeText(EditMyAccount.this, R.string.local_service_stopped, Toast.LENGTH_SHORT).show();
	        }
	    };

	    
	    
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editmyaccount);
	
		
	
		  
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
         
        
		
		status = (Button) findViewById(R.id.status);
		status.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
        // TODO Auto-generated method stub

        	if(mImService.isConnectedToInternet(EditMyAccount.this.getApplicationContext())) {
				
        		Intent intent2 = new Intent(EditMyAccount.this, Update_Status.class);
            	
    	        intent2.putExtra("value",vstatus);
    			
            	startActivity(intent2);
        				}
        				else
        				{
        					Toast.makeText(EditMyAccount.this, "Check internet connection", Toast.LENGTH_LONG).show();
        					/////////put a dialog that you can click to make your internet settings
        				}
        	
        
        }
        });
         
		
		
		fullname = (Button) findViewById(R.id.fullname);
		fullname.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
        // TODO Auto-generated method stub

        	Intent intent2 = new Intent(EditMyAccount.this, Update_Fullname.class);
        	intent2.putExtra("value",vfullname);
        	startActivity(intent2);
        }
        });
         
		gender= (Button) findViewById(R.id.gender);
		gender.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
        // TODO Auto-generated method stub

        	Intent intent2 = new Intent(EditMyAccount.this, Update_Gender.class);
        	intent2.putExtra("value",vgender);
        	startActivity(intent2);
        	
        	}
        });
         
        
		age= (Button) findViewById(R.id.age);
		age.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
        // TODO Auto-generated method stub

        	Intent intent2 = new Intent(EditMyAccount.this, Update_Age.class);
        	intent2.putExtra("value",vage);
        	startActivity(intent2);
        }
        });
         
		hobbies = (Button) findViewById(R.id.hobbies);
		hobbies.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
        // TODO Auto-generated method stub

        	Intent intent2 = new Intent(EditMyAccount.this, Update_Hobbies.class);
        	intent2.putExtra("value",vhobbies);
        	startActivity(intent2);
        }
        });
        
		
		
		address = (Button) findViewById(R.id.address);
		address.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
        // TODO Auto-generated method stub
        	Intent intent2 = new Intent(EditMyAccount.this, CheckInActivity.class);
        	intent2.putExtra("value",vaddress);
        	startActivity(intent2);
        }
        });
        
		
		
		specialization = (Button) findViewById(R.id.specialization);
		specialization.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
        // TODO Auto-generated method stub

        	Intent intent2 = new Intent(EditMyAccount.this, Update_Specialization.class);
        	intent2.putExtra("value",vspecialization);
        	startActivity(intent2);
        }
        });
         
		sp_experience = (Button) findViewById(R.id.sp_experience);
		sp_experience.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
        // TODO Auto-generated method stub

        	Intent intent2 = new Intent(EditMyAccount.this, Update_Sp_Experience.class);
        	intent2.putExtra("value",vsp_experience);
        	startActivity(intent2);
        }
        });
         
		services = (Button) findViewById(R.id.services);
		services.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
        // TODO Auto-generated method stub

        	Intent intent2 = new Intent(EditMyAccount.this, Update_Services.class);
        	intent2.putExtra("value",vservices);
        	startActivity(intent2);
        }
        });
         
		sv_experience = (Button) findViewById(R.id.sv_experience);
		sv_experience.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
        // TODO Auto-generated method stub

        	Intent intent2 = new Intent(EditMyAccount.this, Update_Sv_Experience.class);
        	intent2.putExtra("value",vsv_experience);
        	startActivity(intent2);
        }
        });
         
		website = (Button) findViewById(R.id.website);
		website.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
        // TODO Auto-generated method stub

        	Intent intent2 = new Intent(EditMyAccount.this, Update_Website.class);
        	intent2.putExtra("value",vwebsite);
        	startActivity(intent2);
        }
        });
         
		facebook= (Button) findViewById(R.id.facebook);
		facebook.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
        // TODO Auto-generated method stub

        	Intent intent2 = new Intent(EditMyAccount.this, Update_Facebook.class);
        	intent2.putExtra("value",vfacebook);
        	startActivity(intent2);
        }
        });
         
		twitter = (Button) findViewById(R.id.twitter);
		twitter.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
        // TODO Auto-generated method stub

        	Intent intent2 = new Intent(EditMyAccount.this, Update_Twitter.class);
        	intent2.putExtra("value",vtwitter);
        	startActivity(intent2);
        }
        });
         
		linkedin= (Button) findViewById(R.id.linkedin);
		linkedin.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
        // TODO Auto-generated method stub

        	Intent intent2 = new Intent(EditMyAccount.this, Update_LinkedIn.class);
        	intent2.putExtra("value",vlinkedin);
        	startActivity(intent2);
        }
        });
         
		
		
		
		
		//new JSONAsyncTask().execute("http://192.168.43.100/android-im/account_list.php");
		new JSONAsyncTask().execute("http://www.commice.com/android/account_list.php");
	
		  
	
        
        
        //holder.fullname.setText("Fullname: "+JobsList.get(position).getFullname());
		

	
	
	
	
	
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
   			dialog = new ProgressDialog(EditMyAccount.this);
   			dialog.setMessage("Loading, please wait");
   			dialog.setTitle("Connecting server");
   			dialog.show();
   			dialog.setCancelable(false);
   		}
   		
   		@Override
   		protected Boolean doInBackground(String... urls) {

   			// Feed the beast our comments url, and it spits us
   			// back a JSON object. Boo-yeah Jerome.

   			try {
   				
   				List<NameValuePair> params = new ArrayList<NameValuePair>();
   				params.add(new BasicNameValuePair("username",IMService.USERNAME));
   				//params.add(new BasicNameValuePair("inputAccounts",inputAccounts));
   				//params.add(new BasicNameValuePair("jospecializations",jospecializations));
   				//params.add(new BasicNameValuePair("joblevels",joblevels));

   				
   				JSONObject json = jsonParser.makeHttpRequest(urls[0],
   						"POST", params);
   				
   		JSONArray jarray = json.getJSONArray("Accounts");
   					
   					
   					for (int i = 0; i < jarray.length(); i++) {
   						JSONObject object = jarray.getJSONObject(i);
   						
   						
   						//stringer.setImage(object.getString("image_url"));
   						//stringer.setUsername(object.getString("username"));
   						//stringer.setEmail(object.getString("email"));						
   						//stringer.setPhone(object.getString("phone"));						
   						
   						
   						
   						status.setText(""+object.getString("status"));
   						vstatus=""+object.getString("status");
   						
   						
   						fullname.setText("Fullname: " +object.getString("fullname"));	
   						gender.setText("Gender: " +object.getString("gender"));
   						age.setText("Age: " +object.getString("age"));
   						hobbies.setText("hobbies: " +object.getString("hobbies"));	
   						address.setText("address: " +object.getString("address"));	
   						specialization.setText("specialization: " +object.getString("specialization"));	
   						sp_experience.setText("Specialization Experience: " +object.getString("spec_experience"));	
   						services.setText("Services: " +object.getString("services"));	
   						sv_experience.setText("Services Experience: " +object.getString("serv_experience"));	
   						website.setText("Website: " +object.getString("website"));	
   						facebook.setText("Facebook: " +object.getString("facebook"));	
   						twitter.setText("Twitter: " +object.getString("twitter"));
   						linkedin.setText("LinkedIn: " +object.getString("linkedin"));

   						
   					


   					}
   					return true;
   				
   			} catch (ParseException e1) {
   				e1.printStackTrace();
   			}  catch (JSONException e) {
   				e.printStackTrace();
   				
   			}
   			return false;
   		}
   		
   		@Override
   		protected void onPostExecute(Boolean result) {
   			dialog.cancel();
//   			adapter.notifyDataSetChanged();
   			if(result == false)
   				Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

   		}
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
      //      Log.e(LOG_TAG, "onResume: mConnection is null");
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mConnection != null) {
            unbindService(mConnection);
        } else {
   //         Log.e(LOG_TAG, "onResume: mConnection is null");
        }
    }
	
	

    
}
