package www.commice.com;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
 
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import www.commice.com.JSONParser;
import www.commice.com.interfaces.IAppManager;
import www.commice.com.services.IMService;
import www.commice.com.services.IMService.IMBinder;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ParseException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.ParseException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore.MediaColumns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import java.io.File;

import android.content.ActivityNotFoundException;
import android.content.pm.ResolveInfo;

import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.os.Environment;

import java.io.FileOutputStream;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Accounts_List extends Activity {
	
	
	public ArrayList<AccountsStringer> AccountsStringerList;
	
	AccountsAdapter adapter;

	JSONParser jsonParser = new JSONParser();
	
	
	//call the stringer
	final AccountsStringer stringer = new AccountsStringer();
	/////pop up option
	 AlertDialog jobprofiledialog;
	 
	 /////////////////////////for the edit profile
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
		
	 
	 ///////////////////////for the image
	 AlertDialog dialogimg;
	//
	
  //  public String inputAccounts="";
//    public String jospecializations = "";
    //public String joblevels ="";
	
	  
		//cam
		private Uri mImageCaptureUri;
		private ImageView mImageView;
		private ImageView mImageViewb;
		
		private static final int PICK_FROM_CAMERA = 1;
		private static final int CROP_FROM_CAMERA = 2;
		private static final int PICK_FROM_FILE = 3;
		

	 ///for the image
	    private String upLoadServerUri = null;
	    private String imagepath=null;
	    private ProgressDialog dialog = null;
	    private int serverResponseCode = 0;
	    
	    
	    String status="";
	    
	    
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

		            Toast.makeText(Accounts_List.this, R.string.local_service_stopped, Toast.LENGTH_SHORT).show();
		        }
		    };
		
			// JSON Node names
			 private static final String TAG_ID = "id";
			private static final String TAG_NAME = "firstname";
			
			private static final String TAG_SUCCESS = "success";
		    
	    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	 
		
		//Intent  i = super.getIntent();
//	      Bundle bun =  i.getExtras();

		     //inputAccounts = bun.getString("input");
		     //jospecializations = bun.getString("specialization");
		     //joblevels = bun.getString("level");
	
		     
		setContentView(R.layout.accountlist);
		
		
        upLoadServerUri = "http://www.commice.com/android/UploadToServer.php";
	//	upLoadServerUri = "http://192.168.43.100/android-im/UploadToServer.php";
		
	
		
		///top option
		//old option that takes you to the edit page
		/*
		ImageView edit = (ImageView) findViewById(R.id.edit);
		edit.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	        // TODO Auto-generated method stub


	    		if(mImService.isConnectedToInternet(Accounts_List.this.getApplicationContext())) {
	    		
	    			Intent l = new Intent(Accounts_List.this,Accounts_List.class);
	    			//	Intent l = new Intent(FriendList.this, Jobs_List.class);
	    			
	    				startActivity(l);	

	    		}
	    		else
	    		{
	    			Toast.makeText(Accounts_List.this, "Check internet connection", Toast.LENGTH_LONG).show();
	    			/////////put a dialog that you can click to make your internet settings
	    		}

	        }
	        });
		*/
		ImageView edit = (ImageView) findViewById(R.id.edit);
		edit.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	        // TODO Auto-generated method stub


	    		if(mImService.isConnectedToInternet(Accounts_List.this.getApplicationContext())) {
	    		
	    			 initiateEditWindow();
	    		}
	    		else
	    		{
	    			Toast.makeText(Accounts_List.this, "Check internet connection", Toast.LENGTH_LONG).show();
	    			/////////put a dialog that you can click to make your internet settings
	    		}

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
	         
			
			  
	        ImageView btnCreatePopup = (ImageView) findViewById(R.id.optionmenu);
	        btnCreatePopup.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	        // TODO Auto-generated method stub
	        	 initiatePopupWindow();
	        }
	        });
	         
	        
	        

		
	
			//////////////////////////////////////////choose cam option
		    final String [] items			= new String [] {"Take from camera", "Select from gallery"};				
			ArrayAdapter<String> adapterimg	= new ArrayAdapter<String> (this, android.R.layout.select_dialog_item,items);
			AlertDialog.Builder builderimg		= new AlertDialog.Builder(this);
			
			builderimg.setTitle("Select Image");
			builderimg.setAdapter(adapterimg, new DialogInterface.OnClickListener() {
				@Override
				public void onClick( DialogInterface dialogimg, int item ) { //pick from camera
					if (item == 0) {
						Intent intent 	 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
						
						mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
										   "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

						intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);

						try {
							intent.putExtra("return-data", true);
							
							startActivityForResult(intent, PICK_FROM_CAMERA);
						} catch (ActivityNotFoundException e) {
							e.printStackTrace();
						}
						
					} else { //pick from file
						Intent intent = new Intent();
						
		                intent.setType("image/*");
		                intent.setAction(Intent.ACTION_GET_CONTENT);
		                
		                startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_FILE);
					}
				}
			} );
			
			dialogimg = builderimg.create();

			////the img
			mImageViewb  	= (ImageView) findViewById(R.id.upload);
			mImageView		= (ImageView) findViewById(R.id.left_pic);
			
			mImageViewb.setOnClickListener(new View.OnClickListener() {	
				@Override
				public void onClick(View v) {



		    		if(mImService.isConnectedToInternet(Accounts_List.this.getApplicationContext())) {
		    		
						dialogimg.show();
		    		}
		    		else
		    		{
		    			Toast.makeText(Accounts_List.this, "Check internet connection", Toast.LENGTH_LONG).show();
		    			/////////put a dialog that you can click to make your internet settings
		    		}


				
					//Toast.makeText(getApplicationContext(),"image", Toast.LENGTH_LONG).show();
				}
			});
			
	
		
		AccountsStringerList = new ArrayList<AccountsStringer>();
		new JSONAsyncTask().execute("http://www.commice.com/android/account_list.php");
	//	new JSONAsyncTask().execute("http://192.168.43.100/android-im/account_list.php");
		
		ListView listview = (ListView)findViewById(R.id.list);
		adapter = new AccountsAdapter(getApplicationContext(), R.layout.account_row, AccountsStringerList);
		
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				// TODO Auto-generated method stub
		//		Toast.makeText(getApplicationContext(), AccountsStringerList.get(position).getTitle(), Toast.LENGTH_LONG).show();				
			}
		});
				
		  
		  
		  

			
	}

	 
	class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {
		
		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(Accounts_List.this);
			dialog.setMessage("Loading Account Profile...");
			dialog.setTitle("Account Profile");
			dialog.show();
			dialog.setCancelable(true);
		}
		
		@Override
		protected Boolean doInBackground(String... urls) {

			// Feed the beast our comments url, and it spits us
			// back a JSON object. Boo-yeah Jerome.

			try {
				
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("username",IMService.USERNAME));
				//params.add(new BasicNameValuePair("jospecializations",jospecializations));
				//params.add(new BasicNameValuePair("joblevels",joblevels));

				
				JSONObject json = jsonParser.makeHttpRequest(urls[0],
						"POST", params);
				
		JSONArray jarray = json.getJSONArray("Accounts");
					
					
					for (int i = 0; i < jarray.length(); i++) {
						JSONObject object = jarray.getJSONObject(i);
					
						//final AccountsStringer stringer = new AccountsStringer();
						
					
	///parse it into a string for the edit button to use as an intent
   						vstatus=""+object.getString("status");
   						
   						
   						vfullname=""+object.getString("fullname");	
   						vgender=""+object.getString("gender");
   						vage=""+object.getString("age");
   						vhobbies=""+object.getString("hobbies");	
   						vaddress=""+object.getString("address");	
   						vspecialization=""+object.getString("specialization");	
   						vsp_experience=""+object.getString("spec_experience");	
   						vservices=""+object.getString("services");	
   						vsv_experience=""+object.getString("serv_experience");	
   						vwebsite=""+object.getString("website");	
   						vfacebook=""+object.getString("facebook");	
   						vtwitter=""+object.getString("twitter");
   						vlinkedin=""+object.getString("linkedin");


						
						
						stringer.setImage(object.getString("image_url"));
						stringer.setUsername(object.getString("username"));
						stringer.setEmail(object.getString("email"));						
						stringer.setPhone(object.getString("phone"));						
						
						stringer.setStatus("Status:"+object.getString("status"));	
						status=object.getString("status");
						stringer.setFullname(object.getString("fullname"));	
						stringer.setGender(object.getString("gender"));
						stringer.setAge(object.getString("age"));
						stringer.setHobbies(object.getString("hobbies"));	
						stringer.setAddress(object.getString("address"));	
						stringer.setSpecialization(object.getString("specialization"));	
						stringer.setSpec_experience(object.getString("spec_experience"));	
						stringer.setServices(object.getString("services"));	
						stringer.setServ_experience(object.getString("serv_experience"));	
						stringer.setWebsite(object.getString("website"));	
						stringer.setFacebook(object.getString("facebook"));	
						stringer.setTwitter(object.getString("twitter"));
						stringer.setLinkedin(object.getString("linkedin"));

						
					


						
						AccountsStringerList.add(stringer);
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
				Toast.makeText(getApplicationContext(), "Bad Network:  Unable to load your profile.", Toast.LENGTH_LONG).show();

		}
	}
	

	
	
	
	
	
	
	
	
	///////////////the upload
	

    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (resultCode != RESULT_OK) return;
	   
	    switch (requestCode) {
		    case PICK_FROM_CAMERA:
		    	
		    	
		    	doCrop();
		    	
		    	break;
		    	
		    case PICK_FROM_FILE: 
		    	mImageCaptureUri = data.getData();
		  
		    	  //System.out.println("SELECT_AUDIO");
	                
	                //selectedPath = getPath(mImageCaptureUri);
	                //System.out.println("SELECT_AUDIO Path : " + selectedPath);
	                //doFileUpload();
		    	
		    		doCrop();
	    
		    	break;	
		    	
			
		    	
		    case CROP_FROM_CAMERA:	    	
		        Bundle extras = data.getExtras();
	
		        if (extras != null) {	        	
		            
		        	/*for saving the image immediately*/
		        	Bitmap photo = extras.getParcelable("data");
		     
		        	//mImageView.setImageBitmap(photo);
		            
		        //    Toast.makeText(this, "px"+photo, Toast.LENGTH_SHORT).show();        
		        
//		        	  Toast.makeText(this,""+mImageCaptureUri, Toast.LENGTH_SHORT).show(); 
		        	  SaveImage(photo);
		            
		              //imagepath = getPath(mImageCaptureUri);
		      
		              //Toast.makeText(this,""+imagepath, Toast.LENGTH_SHORT).show();
		
		              String root = Environment.getExternalStorageDirectory().toString();
		              
		              //Toast.makeText(this,""+root.toString() + "/Cangels", Toast.LENGTH_SHORT).show();
		              
		              imagepath=root.toString() + "/Commice/"+USERNAME+"_dp.jpg";
		              
		              
		              //Toast.makeText(this,""+USERNAME, Toast.LENGTH_SHORT).show();
		              
		     		dialog = ProgressDialog.show(Accounts_List.this, "", "Uploading Profile Pix...", true);
		     		//	messageText.setText("uploading started.....");
		     				new Thread(new Runnable() {
		     					public void run() {
		                     uploadFile(imagepath);
		     				}
		               }).start();  
		     				
					 
					 
					 
		        	  
		        	  
		        	  
		            //String root = Environment.getExternalStorageDirectory().toString();
		            
		            //int response= uploadFile(root + "/Cangels/dp.png");
		    
		            //String imagepath = getPath(mImageCaptureUri );
		            
		            //Toast.makeText(this, "Image: "+imagepath, Toast.LENGTH_SHORT).show();
		          //  uploadFile(imagepath);
		            
		        //    Toast.makeText(MyAccount.this, root + "/Cangels/dp.png", Toast.LENGTH_SHORT).show();
		            //Toast.makeText(MyAccount.this, ""+response, Toast.LENGTH_SHORT).show();
		            
		            
//               int response= uploadFile("/sdcard/Cangels/dp.png");
                        //int response= uploadFile(mImageCaptureUri.toString());
                    //System.out.println("RES : " + response);
		        
                    
                    
		        }
	
		        File f = new File(mImageCaptureUri.getPath());            
		        
		        if (f.exists()) f.delete();
	
		        break;

	    }
	}
    
    public String getPath(Uri uri) {
        String[] projection = { MediaColumns.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    
    private void doCrop() {
		final ArrayList<CropOption> cropOptions = new ArrayList<CropOption>();
    	
    	Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");
        
        List<ResolveInfo> list = getPackageManager().queryIntentActivities( intent, 0 );
        
        int size = list.size();
        
        if (size == 0) {	        
        	Toast.makeText(this, "Can not find image crop app", Toast.LENGTH_SHORT).show();
        	
            return;
        } else {
        	intent.setData(mImageCaptureUri);
            
            intent.putExtra("outputX", 150);
            intent.putExtra("outputY", 150);
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("scale", true);
            intent.putExtra("return-data", true);
            
        	if (size == 1) {
        		Intent i 		= new Intent(intent);
	        	ResolveInfo res	= list.get(0);
	        	
	        	i.setComponent( new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
	        	
	        	startActivityForResult(i, CROP_FROM_CAMERA);
        	} else {
		        for (ResolveInfo res : list) {
		        	final CropOption co = new CropOption();
		        	
		        	co.title 	= getPackageManager().getApplicationLabel(res.activityInfo.applicationInfo);
		        	co.icon		= getPackageManager().getApplicationIcon(res.activityInfo.applicationInfo);
		        	co.appIntent= new Intent(intent);
		        	
		        	co.appIntent.setComponent( new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
		        	
		            cropOptions.add(co);
		        }
	        
		        CropOptionAdapter adapter = new CropOptionAdapter(getApplicationContext(), cropOptions);
		        
		        AlertDialog.Builder builder = new AlertDialog.Builder(this);
		        builder.setTitle("Choose Crop App");
		        builder.setAdapter( adapter, new DialogInterface.OnClickListener() {
		            @Override
					public void onClick( DialogInterface dialog, int item ) {
		                startActivityForResult( cropOptions.get(item).appIntent, CROP_FROM_CAMERA);
		            }
		        });
	        
		        builder.setOnCancelListener( new DialogInterface.OnCancelListener() {
		            @Override
		            public void onCancel( DialogInterface dialog ) {
		               
		                if (mImageCaptureUri != null ) {
		                    getContentResolver().delete(mImageCaptureUri, null, null );
		                    mImageCaptureUri = null;
		                }
		            }
		        } );
		        
		        AlertDialog alert = builder.create();
		        
		        alert.show();
        	}
        }
	}
	
	/////////////////////////////////////////////////below is the pop up
    
    
    

    private void initiatePopupWindow() {
   		 final String[] options = new String[] {"Friends"};
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
    

    private void initiateEditWindow() {
  		 final String[] options = new String[] {"Status","Fullname","Gender","Age","Hobbies","Address","Specialization","Specialization Experience",
  				 "Services","Services Experience","Website","Facebook","Twitter","LinkedIn"};
  		 ArrayAdapter<String> newsadapter = new ArrayAdapter<String>(this,
  		      android.R.layout.select_dialog_item, options);
  		final AlertDialog.Builder newsbuilder = new AlertDialog.Builder(this);
  		newsbuilder.setIcon(R.drawable.edit);

  		 newsbuilder.setTitle("Edit Account");
  		 newsbuilder.setAdapter(newsadapter, new DialogInterface.OnClickListener() {

  		 @Override
		public void onClick(DialogInterface dialogs, int which) {
  		      // TODO Auto-generated method stub
  		 	 if (which==0)
  		 	 {
  		 		if(mImService.isConnectedToInternet(Accounts_List.this.getApplicationContext())) {
  					
  	        		Intent intent2 = new Intent(Accounts_List.this, Update_Status.class);
  	            	
  	    	        intent2.putExtra("value",vstatus);
  	    			
  	            	startActivity(intent2);
  	        				}
  	        				else
  	        				{
  	        					Toast.makeText(Accounts_List.this, "Check internet connection", Toast.LENGTH_LONG).show();
  	        					/////////put a dialog that you can click to make your internet settings
  	        				}
  	        		
  		 	 }
  		 	 if (which==1)
  		 	 {

  	        	Intent intent2 = new Intent(Accounts_List.this, Update_Fullname.class);
  	        	intent2.putExtra("value",vfullname);
  	        	finish();
  	        	startActivity(intent2);	
  		 	 }
  		 	 if (which==2)
  		 	 {

  		 		Intent intent2 = new Intent(Accounts_List.this, Update_Gender.class);
  	        	intent2.putExtra("value",vgender);
  	        	finish();
  	        	startActivity(intent2);
  	        	
  	        	
  		 	 }
  		 	 if (which==3)
  		 	 {

  	        	Intent intent2 = new Intent(Accounts_List.this, Update_Age.class);
  	        	intent2.putExtra("value",vage);
  	        	finish();
  	        	startActivity(intent2);
  		 		
  		 	 }
  		 	 if (which==4)
  		 	 {
  		 		Intent intent2 = new Intent(Accounts_List.this, Update_Hobbies.class);
 	        	intent2.putExtra("value",vhobbies);
 	        	finish();
  	        	startActivity(intent2);
  		 	 }
  		 	 if (which==5)
  		 	 {
  	        	Intent intent2 = new Intent(Accounts_List.this, Update_Address.class);
  	        	intent2.putExtra("value",vaddress);
  	        	finish();
  	        	startActivity(intent2);
  		 	 }
  		 	 if (which==6)
  		 	 {

  		 		Intent intent2 = new Intent(Accounts_List.this, Update_Specialization.class);
  	        	intent2.putExtra("value",vspecialization);
  	        	finish();
  	        	startActivity(intent2);
  		 	 }
  		 	 if (which==7)
  		 	 {

  	        	Intent intent2 = new Intent(Accounts_List.this, Update_Sp_Experience.class);
  	        	intent2.putExtra("value",vsp_experience);
  	        	finish();
  	        	startActivity(intent2);	
  		 	 }
  		 	 if (which==8)
  		 	 {
  		 		Intent intent2 = new Intent(Accounts_List.this, Update_Services.class);
  	        	intent2.putExtra("value",vservices);
  	        	finish();
  	        	startActivity(intent2);	
  		 	 }
  		 	 if (which==9)
  		 	 {

  	        	Intent intent2 = new Intent(Accounts_List.this, Update_Sv_Experience.class);
  	        	intent2.putExtra("value",vsv_experience);
  	        	finish();
  	        	startActivity(intent2);
  		 	 }
  		 	 if (which==10)
  		 	 {

  	        	Intent intent2 = new Intent(Accounts_List.this, Update_Website.class);
  	        	intent2.putExtra("value",vwebsite);
  	        	finish();
  	        	startActivity(intent2);
  		 		
  		 	 }
  		 	 if (which==11)
  		 	 {
  	        	Intent intent2 = new Intent(Accounts_List.this, Update_Facebook.class);
  	        	intent2.putExtra("value",vfacebook);
  	        	finish();
  	        	startActivity(intent2);	
  		 	 }
  		 	 if (which==12)
  		 	 {
  		 		Intent intent2 = new Intent(Accounts_List.this, Update_Twitter.class);
  	        	intent2.putExtra("value",vtwitter);
  	        	finish();
  	        	startActivity(intent2);
  		 	 }
  		 	 if (which==13)
  		 	 {
  	        	Intent intent2 = new Intent(Accounts_List.this, Update_LinkedIn.class);
  	        	intent2.putExtra("value",vlinkedin);
  	        	finish();
  	        	startActivity(intent2);	
  		 	 }
  		 	 
  		 	 
  		 }
  		 });


   		AlertDialog dialogs = newsbuilder.create();
   			 dialogs.show();
   }
   
   
    
    
    private void SaveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/Commice");    
        myDir.mkdirs();
        //Random generator = new Random();
        //int n = 10000;
        //n = generator.nextInt(n);
        String fname = USERNAME+"_dp.jpg";
        
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete (); 
        try {
               FileOutputStream out = new FileOutputStream(file);
               finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
               out.flush();
               out.close();

        } catch (Exception e) {
               e.printStackTrace();
        }
    }
    
    
    
    public int uploadFile(String sourceFileUri) {
          
    	  
    	  String fileName = sourceFileUri;
 
          HttpURLConnection conn = null;
          DataOutputStream dos = null;  
          String lineEnd = "\r\n";
          String twoHyphens = "--";
          String boundary = "*****";
          int bytesRead, bytesAvailable, bufferSize;
          byte[] buffer;
          int maxBufferSize = 1 * 1024 * 1024; 
          File sourceFile = new File(sourceFileUri); 
          
          if (!sourceFile.isFile()) {
        	  
	           dialog.dismiss(); 
	           
	          // Log.e("uploadFile", "Source File not exist :"+imagepath);
	           
	           runOnUiThread(new Runnable() {
	               public void run() {
	            //	   messageText.setText("Source File not exist :"+ imagepath);
	               }
	           }); 
	           
	           return 0;
           
          }
          else
          {
	           try { 
	        	   
	            	 // open a URL connection to the Servlet
	               FileInputStream fileInputStream = new FileInputStream(sourceFile);
	               URL url = new URL(upLoadServerUri);
	               
	               // Open a HTTP  connection to  the URL
	               conn = (HttpURLConnection) url.openConnection(); 
	               conn.setDoInput(true); // Allow Inputs
	               conn.setDoOutput(true); // Allow Outputs
	               conn.setUseCaches(false); // Don't use a Cached Copy
	               conn.setRequestMethod("POST");
	               conn.setRequestProperty("Connection", "Keep-Alive");
	               conn.setRequestProperty("ENCTYPE", "multipart/form-data");
	               conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
	               conn.setRequestProperty("uploaded_file", fileName); 
	               
	               dos = new DataOutputStream(conn.getOutputStream());
	     
	               dos.writeBytes(twoHyphens + boundary + lineEnd); 
	               dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
	            		                     + fileName + "\"" + lineEnd);
	               
	               dos.writeBytes(lineEnd);
	     
	               // create a buffer of  maximum size
	               bytesAvailable = fileInputStream.available(); 
	     
	               bufferSize = Math.min(bytesAvailable, maxBufferSize);
	               buffer = new byte[bufferSize];
	     
	               // read file and write it into form...
	               bytesRead = fileInputStream.read(buffer, 0, bufferSize);  
	                 
	               while (bytesRead > 0) {
	            	   
	                 dos.write(buffer, 0, bufferSize);
	                 bytesAvailable = fileInputStream.available();
	                 bufferSize = Math.min(bytesAvailable, maxBufferSize);
	                 bytesRead = fileInputStream.read(buffer, 0, bufferSize);   
	                 
	                }
	     
	               // send multipart form data necesssary after file data...
	               dos.writeBytes(lineEnd);
	               dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
	     
	               // Responses from the server (code and message)
	               serverResponseCode = conn.getResponseCode();
	               String serverResponseMessage = conn.getResponseMessage();
	                
	               Log.i("uploadFile", "HTTP Response is : " 
	            		   + serverResponseMessage + ": " + serverResponseCode);
	               
	               if(serverResponseCode == 200){
	            	
	            	   
	           	   final String url_update_profilepix = "http://www.commice.com/android/url_update_profilepix.php";
	          //  final String url_update_profilepix = "http://192.168.43.100/android-im/url_update_profilepix.php";	            	   
	            	   
	            		// Building Parameters
          			List<NameValuePair> paramss = new ArrayList<NameValuePair>();
          			paramss.add(new BasicNameValuePair("path", USERNAME+"_dp.jpg"));
          			paramss.add(new BasicNameValuePair("username", USERNAME));
          		
          			// getting JSON Object
          			// Note that create product url accepts POST method
          			JSONObject jsons = jsonParser.makeHttpRequest(url_update_profilepix,
          					"POST", paramss);
          			
          			// check log cat fro response
          			Log.d("Create Response", jsons.toString());
 
	            	  
	                   runOnUiThread(new Runnable() {
	                        public void run() {
	                        	String msg = "File Upload Completed.\n\n See uploaded file here : \n\n"
                      		          +" F:/wamp/wamp/www/uploads";
	                        	//messageText.setText(msg);

	                        	
	                        
	                			// check for success tag
	                			/*
	                			try {

	                				int success = json.getInt(TAG_SUCCESS);
	                				int id=json.getInt(TAG_ID);
	                                
	                				if (success == 1) {
	                					
	                			//showToast("Registeration was not successfull");
	                					
	                				} else {
	                				
	                		//showToast("Registeration was not successfull");

	                				}
	                			} catch (JSONException e) {
	                				e.printStackTrace();
	                			}
*/
	                        	
	                        	
	                  //      	mImService.uploadProfileDataRequest(imagepath,USERNAME);

	                //        	uploadProfileData();     	
	   
	                        	
//AccountsStringerList = new ArrayList<AccountsStringer>();
	//                 new JSONAsyncTask().execute("http://www.commice.com/android/account_list.php");
	  //                  new JSONAsyncTask().execute("http://192.168.43.100/android-im/account_list.php");
	                    
	                    	
	    //                	ListView listview = (ListView)findViewById(R.id.list);
	      //              	adapter = new AccountsAdapter(getApplicationContext(), R.layout.account_row, AccountsStringerList);
	                    		
	        //           		listview.setAdapter(adapter);

	    
	                    		  
	 		  
	
                        	
	                   	//	Toast.makeText(Accounts_List.this, "Uploaded,please wait it will change in some seconds time.", Toast.LENGTH_SHORT).show();

//remember to automatically set the picture from the sd	                        
	        Toast.makeText(Accounts_List.this, "Profile Picture Changed.", Toast.LENGTH_SHORT).show();	                        	
	                        finish();
	                        
	                        }
	                    });
	                  
          		//	Toast.makeText(Accounts_List.this, "Uploaded,please wait it will change in some seconds time.", Toast.LENGTH_SHORT).show();
	               }    
	               
	               //close the streams //
	               fileInputStream.close();
	               dos.flush();
	               dos.close();
	                
	          } catch (MalformedURLException ex) {
	        	  
	              dialog.dismiss();  
	              ex.printStackTrace();
	              
	              runOnUiThread(new Runnable() {
	                  public void run() {
	     //           	  messageText.setText("MalformedURLException Exception : check script url.");
	                      Toast.makeText(Accounts_List.this, "MalformedURLException", Toast.LENGTH_SHORT).show();
	                  }
	              });
	              
	              Log.e("Upload file to server", "error: " + ex.getMessage(), ex);  
	          } catch (Exception e) {
	        	  
	              dialog.dismiss();  
	              e.printStackTrace();
	              
	              runOnUiThread(new Runnable() {
	                  public void run() {
	                //	  messageText.setText("Got Exception : see logcat ");
	                      Toast.makeText(Accounts_List.this, "Got Exception : see logcat ", Toast.LENGTH_SHORT).show();
	                  }
	              });
	              Log.e("Upload file to server Exception", "Exception : "  + e.getMessage(), e);  
	          }
	          dialog.dismiss();       
	          return serverResponseCode; 

	          

	  		
	  	

	          
	          
           } // End else block 
         }


    
    /*
    private void uploadProfileData() {
        if (imagepath.length() > 0) {
            // TODO: A thread is really needed ?
            Thread thread = new Thread() {
                @Override
                public void run() {
                    // TODO: Please check if the request is successful and raise a error message if needed.
              //      mImService.uploadProfileDataRequest(imagepath,USERNAME);
                }
            };
            thread.start();

            // TODO: Show the toast only if the sent of the request is successful
//            Toast.makeText(Accounts_List.this, R.string.request_sent, Toast.LENGTH_SHORT).show();

//            finish();
        } else {
            //Log.e(LOG_TAG, "addNewFriend: username length (" + mFriendUserNameText.length() + ") is < 0");
            Toast.makeText(Accounts_List.this,"Could Not Upload Ur Profile Picture", Toast.LENGTH_LONG).show();
        }
    }
    
    */

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
	
	
    
    
    
    
    ////////////menu
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 
		MenuInflater inflater = getMenuInflater();
		 
		inflater.inflate(R.menu.accountmenu, menu);
		 
		return true;
		 
		}
	@Override
	 
	public boolean onOptionsItemSelected(MenuItem item) {
	 
	switch (item.getItemId()) {
	 		

	
	case R.id.status:
		

		if(mImService.isConnectedToInternet(Accounts_List.this.getApplicationContext())) {
		
			Intent l = new Intent(Accounts_List.this,Update_Status.class);
			//	Intent l = new Intent(FriendList.this, Jobs_List.class);
			
			l.putExtra("value",status);
			
			
				startActivity(l);	

		}
		else
		{
			Toast.makeText(Accounts_List.this, "Check internet connection", Toast.LENGTH_LONG).show();
			/////////put a dialog that you can click to make your internet settings
		}
		


		return true;


	case R.id.edit:
		

		if(mImService.isConnectedToInternet(Accounts_List.this.getApplicationContext())) {
		
	//	Intent e = new Intent(Accounts_List.this, Accounts_List.class);
	//	Intent l = new Intent(FriendList.this, Jobs_List.class);
			initiateEditWindow();
	//	startActivity(e);	
		}
		else
		{
			Toast.makeText(Accounts_List.this, "Check internet connection", Toast.LENGTH_LONG).show();
			/////////put a dialog that you can click to make your internet settings
		}
		
		return true;
    


	case R.id.pix:
		
		if(mImService.isConnectedToInternet(Accounts_List.this.getApplicationContext())) {
    		
			dialogimg.show();

		}
		else
		{
			Toast.makeText(Accounts_List.this, "Check internet connection", Toast.LENGTH_LONG).show();
			/////////put a dialog that you can click to make your internet settings
		}


		return true;

		
		
	default:
	 
		
	return super.onOptionsItemSelected(item);
	 
	}
	}	
	

    
    
}
