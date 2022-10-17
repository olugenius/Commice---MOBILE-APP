// TODO: Add licence header

package www.commice.com;

import www.commice.com.interfaces.IAppManager;
import www.commice.com.services.IMService;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


// TODO: Add javadoc
public class AddFriendByUsername extends Activity implements OnClickListener {

    private static Button mAddFriendButton;
    private static Button mCancelButton;
    private static EditText mFriendUserNameText;

    private static IAppManager mImService;

    
    AlertDialog dialogopt;
  	
	
    
    private static final int TYPE_FRIEND_USERNAME = 0;
    private static final String LOG_TAG = "AddFriend";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_new_friendbyusername);
        //setTitle(getString(R.string.add_new_friend));

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
        
        
        
        
        
        
        //////////////////this is called so when the option is selected the
      //pops up come in

        
        
        ImageView btnCreatePopup = (ImageView) findViewById(R.id.optionmenu);
        btnCreatePopup.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
        // TODO Auto-generated method stub
        	dialogopt.show();
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
         
        

    	

        /////////////////////////alert option 
        final String[] optionopt = new String[] {"Back"};
    ArrayAdapter<String> adapters = new ArrayAdapter<String>(this,
           android.R.layout.select_dialog_item, optionopt);


    	AlertDialog.Builder builderopt = new AlertDialog.Builder(this);
    	builderopt.setIcon(R.drawable.option);
    builderopt.setTitle("Options:");
    builderopt.setAdapter(adapters, new DialogInterface.OnClickListener() {

     @Override
	public void onClick(DialogInterface dialogopt, int which) {
           // TODO Auto-generated method stub
    	 if (which==0)
    	 {
    		 finish();
    				
    	 }
     }
    });

    dialogopt = builderopt.create();

        
        
        
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

    @Override
    public void onClick(View view) {
        if (view == mCancelButton) {
            finish();
        } else if (view == mAddFriendButton) {
        	
if(mImService.isConnectedToInternet(AddFriendByUsername.this.getApplicationContext())) {
				
	addNewFriend();
				
			}
			else
			{
				Toast.makeText(AddFriendByUsername.this, "Check internet connection", Toast.LENGTH_LONG).show();
				/////////put a dialog that you can click to make your internet settings
			}
            
        } else {
            Log.e(LOG_TAG, "onClick: view clicked is unknown");
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

            Toast.makeText(AddFriendByUsername.this, R.string.local_service_stopped, Toast.LENGTH_SHORT).show();
        }
    };

    // TODO: Remove deprecated method
    @Override
	protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddFriendByUsername.this);
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

    private void addNewFriend() {
        if (mFriendUserNameText.length() > 0) {
            // TODO: A thread is really needed ?
            Thread thread = new Thread() {
                @Override
                public void run() {
                    // TODO: Please check if the request is successful and raise a error message if needed.
                    mImService.addNewFriendRequest(mFriendUserNameText.getText().toString());
                }
            };
            thread.start();

            // TODO: Show the toast only if the sent of the request is successful
            Toast.makeText(AddFriendByUsername.this, R.string.request_sent, Toast.LENGTH_SHORT).show();

            finish();
        } else {
            Log.e(LOG_TAG, "addNewFriend: username length (" + mFriendUserNameText.length() + ") is < 0");
            Toast.makeText(AddFriendByUsername.this, R.string.type_friend_username, Toast.LENGTH_LONG).show();
        }
    }
    
    
    

    private void initiatePopupWindow() {
   		 final String[] options = new String[] { "Add Friend", "Cancel"};
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
   		 		if(mImService.isConnectedToInternet(AddFriendByUsername.this.getApplicationContext())) {
   					
   					addNewFriend();
   					
   				}
   				else
   				{
   					Toast.makeText(AddFriendByUsername.this, "Check internet connection", Toast.LENGTH_LONG).show();
   					/////////put a dialog that you can click to make your internet settings
   				}
   	            
					
   		 	 }
   		 	 if (which==1)
   		 	 {
   		 		finish();	   		 		 
   		 	 }
   		 }
   		 });

    }
    }

    
