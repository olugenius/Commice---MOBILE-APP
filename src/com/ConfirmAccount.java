// TODO: Add licence header

package www.commice.com;

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
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


// TODO: Add javadoc
public class ConfirmAccount extends Activity {

    private static Button confirmButton;
    private static EditText Code;

    private static IAppManager mImService;

    private static final int TYPE_FRIEND_USERNAME = 0;
    private static final String LOG_TAG = "AddFriend";
    
    
    private static final String RESULT = "out";
    
    
    private ProgressDialog pDialog;
	private static final String SERVER_RES_RES_SIGN_UP_SUCCESFULL = "1";
	private static final String SERVER_RES_SIGN_UP_USERNAME_CRASHED = "2";
	private Handler handler = new Handler();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.confirmaccount);
        //setTitle(getString(R.string.add_new_friend));

        

        Button login = (Button)findViewById(R.id.login);
          login.setOnClickListener(new OnClickListener() {

         @Override
         public void onClick(View v) {
         // TODO Auto-generated method stub
         	//confirm();
         	
         	Intent login=new Intent(ConfirmAccount.this,Login.class);
         	
//         	Intent i = new Intent(SignUp.this, Login.class);
         	login.putExtra("loginfrom","");
         	startActivity(login);
         	finish();

         }
         });

        
//        confirmButton = (Button)findViewById(R.id.confirm);
       // mCancelButton = (Button)findViewById(R.id.cancel);
//        Code = (EditText)findViewById(R.id.code);

          /*
        if (confirmButton != null) {
        	confirmButton.setOnClickListener(this);

        } else {
        	
        	Toast toast = Toast.makeText(getApplicationContext(), "Please Enter The Confirmation Code Sent To You", Toast.LENGTH_SHORT);
        	Log.e(LOG_TAG, "onCreate: mAddFriendButton is null");
            throw new NullPointerException("onCreate: mAddFriendButton is null");
       
        }
        */
        
        
        ImageView check = (ImageView) findViewById(R.id.check);
        check.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
        // TODO Auto-generated method stub
        	//confirm();
        }
        });
         
      
        ImageView btnCreatePopup = (ImageView) findViewById(R.id.optionmenu);
        btnCreatePopup.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
        // TODO Auto-generated method stub
        initiatePopupWindow();
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

        if (mConnection != null) {
            unbindService(mConnection);
        } else {
            Log.e(LOG_TAG, "onResume: mConnection is null");
        }
    }

    /*
    @Override
    public void onClick(View view) {
         if (view == confirmButton) {
        	confirm();
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

            Toast.makeText(ConfirmAccount.this, R.string.local_service_stopped, Toast.LENGTH_SHORT).show();
        }
    };

    private void confirm() {
        if (Code.length() > 0) {
            
        	Dialog dlgq = new AlertDialog.Builder(ConfirmAccount.this) 
            .setIcon(R.drawable.warning)
            .setTitle("Registration Confirmation!") 
            .setNegativeButton("Cancel", null) 
            .setPositiveButton("Confirm Now!", new android.content.DialogInterface.OnClickListener() {
    		
    			@Override
    			public void onClick(DialogInterface arg0, int arg1) {
    	
    	        	
    				 pDialog = new ProgressDialog(ConfirmAccount.this);
			            pDialog.setMessage("Confirming User...");
			            pDialog.setIndeterminate(false);
			            pDialog.setCancelable(true);
			            pDialog.show();
				
    	        	// TODO: A thread is really needed ?
    				
    	        Thread thread = new Thread() {
    	        	String result = new String();
    	        	@Override
    	                public void run() {
    	// TODO: Please check if the request is successful and raise a error message if needed.
    	                	
    	                	
//    	        mImService.addNewFriendRequestByEmail(mFriendUserNameText.getText().toString());
    	                	
    	          result=mImService.Confirmcode(Code.getText().toString());  
    	          
    	          

					handler.post(new Runnable(){

						@Override
						public void run() {
							if (result.equals(SERVER_RES_RES_SIGN_UP_SUCCESFULL)) {
								pDialog.dismiss();
								
								
			    	            
			    	            Toast.makeText(ConfirmAccount.this,"Confirmation Completed!", Toast.LENGTH_SHORT).show();

			    	            finish();
			    	            
			    	            Intent i = new Intent(getApplicationContext(), Login.class);
								
						        i.putExtra("loginfrom","");
								startActivity(i);
																
							}
					//remove me later
							else  //if (result.equals(SERVER_RES_SIGN_UP_FAILED)) 
							{
								pDialog.dismiss();
								Toast.makeText(getApplicationContext(),"Could not confirm account", Toast.LENGTH_LONG).show();
								//showDialog(SIGN_UP_FAILED);
								
							}
							
						}
						
					});
    	          
    	                }
    	            };
    	            thread.start();

    	            // TODO: Show the toast only if the sent of the request is successful
    	            
    	            
					
    				//code to quit application
    			}}) 
            .setMessage("Are you sure you make Confirmation ? ") 
            .create(); 
            dlgq.show(); 
    		


        } else {
            Log.e(LOG_TAG, "confirm: username length (" + Code.length() + ") is < 0");
            Toast.makeText(ConfirmAccount.this, "Your Confirmation Code is Required TO Complete Your Registration", Toast.LENGTH_LONG).show();
        }
    }
    

    private void initiatePopupWindow() {
   		 final String[] options = new String[] {"Confirm Registration"};
   		 ArrayAdapter<String> newsadapter = new ArrayAdapter<String>(this,
   		      android.R.layout.select_dialog_item, options);
   		final AlertDialog.Builder newsbuilder = new AlertDialog.Builder(this);
   		newsbuilder.setIcon(R.drawable.option);

   		 newsbuilder.setTitle("Confirm Registration!");
   		 newsbuilder.setAdapter(newsadapter, new DialogInterface.OnClickListener() {

   		 @Override
		public void onClick(DialogInterface dialogs, int which) {
   		      // TODO Auto-generated method stub
   		 	 if (which==0)
   		 	 {
   		 		confirm();
					
   		 	 }
   		 }
   		 });

    }
    
    }

    
