package www.commice.com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import www.commice.com.interfaces.IAppManager;
import www.commice.com.services.IMService;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AddFriendByContact extends Activity implements
		OnItemClickListener {

	private ListView listView;
	private TextView contactcount;
	private List<ContactBean> list = new ArrayList<ContactBean>();

	
	
	/*
	 * the add friend functions
	 */
	   private static IAppManager mImService;

	    private static final int TYPE_FRIEND_USERNAME = 0;
	    private static final String LOG_TAG = "AddFriend";

	 
	 
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_new_friendbycontacts);
		
	  contactcount = (TextView) findViewById(R.id.count);
		
		listView = (ListView) findViewById(R.id.list);
		listView.setOnItemClickListener(this);

		
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
	         
	        

	        
		
		Cursor phones = getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
				null, null);
		while (phones.moveToNext()) {

			String name = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

			String phoneNumber = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

			ContactBean objContact = new ContactBean();
			objContact.setName(name);
			objContact.setPhoneNo(phoneNumber);
			list.add(objContact);

		}
		phones.close();

		ContanctAdapter objAdapter = new ContanctAdapter(
				AddFriendByContact.this, R.layout.alluser_row, list);
		listView.setAdapter(objAdapter);

		if (null != list && list.size() != 0) {
			Collections.sort(list, new Comparator<ContactBean>() {

				@Override
				public int compare(ContactBean lhs, ContactBean rhs) {
					return lhs.getName().compareTo(rhs.getName());
				}
			});
		
			
			contactcount.setText(list.size() + " Contact(s) Found! ");

		} else {
			
			contactcount.setText("0 Contacts Found! ");
			
			showToast("No Contact Found!!!");
		}
	}

	private void showToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onItemClick(AdapterView<?> listview, View v, int position,
			long id) {
		ContactBean bean = (ContactBean) listview.getItemAtPosition(position);
		showAddDialog(bean.getName(), bean.getPhoneNo());
	}

	private void showAddDialog(String name, final String phoneNo) {
		AlertDialog alert = new AlertDialog.Builder(AddFriendByContact.this)
				.create();
		
   		alert.setIcon(R.drawable.add_friend);
		alert.setTitle("Add Friend");

		alert.setMessage("Are you sure want to add " + name + " as your friend  ?");

		alert.setButton("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		alert.setButton2("Add Friend!", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				String phoneNumber = "tel:" + phoneNo;
				
				if(phoneNumber.length()<=0)
				{
					showToast("No Phone Number Found!");
				}
				else
				{
					if(mImService.isConnectedToInternet(AddFriendByContact.this.getApplicationContext())) {
						
						addNewFriend(phoneNumber);
						
					}
					else
					{
						Toast.makeText(AddFriendByContact.this, "Check internet connection", Toast.LENGTH_LONG).show();
						/////////put a dialog that you can click to make your internet settings
					}
					
				
				}
			}
		});
		alert.show();
	}
	
	//////////////the method for the add function
	

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

            Toast.makeText(AddFriendByContact.this, R.string.local_service_stopped, Toast.LENGTH_SHORT).show();
        }
    };

    
    private void addNewFriend(String phone) {
    
    	
    	final String phonenumber=phone;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    // TODO: Please check if the request is successful and raise a error message if needed.
            mImService.addNewFriendRequestByPhoneNumber(phonenumber);
                }
            };
            thread.start();

            // TODO: Show the toast only if the sent of the request is successful
            Toast.makeText(AddFriendByContact.this, R.string.request_sent, Toast.LENGTH_SHORT).show();

            finish();
      
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
    
}
