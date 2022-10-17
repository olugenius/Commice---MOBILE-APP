package www.commice.com;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class ContentRenderer extends Activity {
	
	ListView list = null;
	View mView = null;
	ArrayList<String> listArray = null;
	Intent intent;
	int listIndex = 0;
	String DELIM = "//DELIM//";
	XMLAdapter customAdapter;
	ProgressDialog dialog;
	API api;
	String filePath = "http://192.168.43.100/puller/spiritmeat.php";
	String xmlCode = "";
	String loaded = "";
	String SelectedDate = "";
	String captionDate = "";
	String LoadDate = "";
	private int year;
	private int month;
	private int day;
	TextView CurrentDate;
	
	String localPath = "";
	ScrollView caption;
	
	static final int DATE_DIALOG_ID = 999;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		api = new API(this.getApplicationContext());
		api.CreateDirectory("SpiritMeat");
		api.CreateFile("SpiritMeat", "Data.dc");
		
		
		setContentView(R.layout.contentloader);
		CurrentDate = (TextView) findViewById(R.id.CurrentDate);
		list = (ListView) findViewById(R.id.list);
		caption =  (ScrollView) findViewById(R.id.caption);
		///////////////the long listing tinz
		
        
        /*
        /////////////////////////alert option 
        final String[] option = new String[] {"Comment","Report News"};
ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
           android.R.layout.select_dialog_item, option);


		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
builder.setTitle("Select An Add Friend Option");
builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

     public void onClick(DialogInterface dialogs, int which) {
           // TODO Auto-generated method stub
    	 if (which==0)
    	 {

			//	Intent openStartingPointp=new Intent("gensoftsoftware.android.newsapprise.Classroom");
			//	startActivity(openStartingPointp);
					
    	 }
    	 if (which==1)
    	 {

			//	Intent openStartingPointp=new Intent("gensoftsoftware.android.newsapprise.DepartmentalRoomActivity");
			//	startActivity(openStartingPointp);
					
    	 }
    	 if (which==2)
    	 {
    	 

				Intent openStartingPointp=new Intent("gensoftsoftware.android.newsapprise.InstitutionalRoomActivity");
				startActivity(openStartingPointp);
					
    	 }

     }
});
*/

//final AlertDialog dialogs = builder.create();

       

		list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			
			@Override
		    public boolean onItemLongClick(AdapterView<?> av, View v, int pos, final long id) {
        /////////////////////////alert option 
	            
				
				//dialogs.show();
		        
		        return true;
		    }
		});
	
		
		////////////////////////end of longlisting tinz
		
		setCurrentDateOnView();
		localPath = api.getSDPath() + "/SpiritMeat/Data.dc";
		
		try {
			
		listArray = new ArrayList<String>();
		
		/*
		String strr = api.FileReader(localPath);
		api.ListLoader(strr, listArray, CurrentDate.getText().toString());
		
		if(listArray.size() > 0) {
			caption.setVisibility(ScrollView.GONE);
		}
		else {
			caption.setVisibility(ScrollView.VISIBLE);
		}
		*/
		
		try {
			//CurrentDate.getText().toString()
			customAdapter = new XMLAdapter(this, R.layout.content_header, listArray, "o");
			list.setAdapter(customAdapter);
		}
		catch(Throwable ex) {
			Toast.makeText(ContentRenderer.this, ex.toString(), Toast.LENGTH_LONG).show();
		}

		/*use or call this in  the menu
		*/
	/*
		findViewById(R.id.DatePicker).setOnClickListener( new android.view.View.OnClickListener() {
			@Override
			public void onClick(View arg0) {

				showDialog(DATE_DIALOG_ID);
		}});
	*/
//////////////////////no need to download.		 
	//	if(API.isConnectedToInternet(ContentRenderer.this.getApplicationContext())) {
			
			Toast.makeText(ContentRenderer.this, "Downloading Spirit Meat for '" + CurrentDate.getText().toString() + "'", Toast.LENGTH_LONG).show();
			
			dialog = ProgressDialog.show(ContentRenderer.this, "", "Downloading...", true, true);
			
			List<NameValuePair> queries = new ArrayList<NameValuePair>();
			 queries.add(new BasicNameValuePair("opCode", "PULL_DATA"));
			 queries.add(new BasicNameValuePair("DateToLoad", "o"));
			new DataPuller(ContentRenderer.this, "PULL_DATA", queries).execute(filePath);
			
			
/*
			
		}
		else {
			Toast.makeText(ContentRenderer.this, "Check internet connection", Toast.LENGTH_LONG).show();
		}
		
*/
			
			
			/////////////////////////do this
			
			
		}
		catch(Throwable ex) {
			
			Toast.makeText(ContentRenderer.this, ex.toString() + ":", Toast.LENGTH_LONG).show();
		}
	}
	

	
	// display current date
		public void setCurrentDateOnView() {

			final Calendar c = Calendar.getInstance();
			year = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH);
			day = c.get(Calendar.DAY_OF_MONTH);

			SelectedDate = (new StringBuilder().append(year)
					.append("-").append(month+1)).toString();
			
			LoadDate = (new StringBuilder().append(year)
					.append("-").append(month+1).append("-").append(day)).toString();
			
			captionDate = (new StringBuilder().append(day)
					.append("-").append(month+1).append("-").append(year)).toString();
			
			CurrentDate.setText(captionDate);
		}

		@Override
		protected Dialog onCreateDialog(int id) {
			switch (id) {
			case DATE_DIALOG_ID:
				// set date picker as current date
				return new DatePickerDialog(this, datePickerListener, year, month,
						day);
			}
			return null;
		}

		private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

			// when dialog box is closed, below method will be called.
			@Override
			public void onDateSet(DatePicker view, int selectedYear,
					int selectedMonth, int selectedDay) {
				year = selectedYear;
				month = selectedMonth;
				day = selectedDay;

				String viewDate = (new StringBuilder().append(year)
						.append("-").append(month+1).append("-").append(day)).toString();
				
				captionDate = (new StringBuilder().append(day)
						.append("-").append(month+1).append("-").append(year)).toString();
				
				CurrentDate.setText(captionDate);
				
			
					Toast.makeText(ContentRenderer.this, "Loading Spirit Meat for '" + CurrentDate.getText().toString() + "'", Toast.LENGTH_LONG).show();
					
					String resp = api.FileReader(localPath);
					//viewDate
					api.ListLoader( resp , listArray, "o");
					
					if(listArray.size() > 0) {
						caption.setVisibility(View.GONE);
					}
					else {
						caption.setVisibility(View.VISIBLE);
					}
						
					try {
						//CurrentDate.getText().toString()
						customAdapter = new XMLAdapter(getApplicationContext(), R.layout.content_header, listArray, "o" );
						list.setAdapter(customAdapter);
					}
					catch(Throwable ex) { }

			}
		};

	public void refreshActivity(String response, String opCode) {

		if(opCode.equals("PULL_DATA")) {
			
			dialog.cancel();
			
			if(response.trim().equals("")) {
				Toast.makeText(ContentRenderer.this, "Unable to Download Spirit Meat content. Try again.", Toast.LENGTH_LONG).show();
			
				String resp = api.FileReader(localPath);
				//viewDate
				api.ListLoader( resp , listArray, "o");
				
				if(listArray.size() > 0) {
					caption.setVisibility(View.GONE);
				}
				else {
					caption.setVisibility(View.VISIBLE);
				}
					
				try {
					//CurrentDate.getText().toString()
					customAdapter = new XMLAdapter(getApplicationContext(), R.layout.content_header, listArray, "o" );
					list.setAdapter(customAdapter);
				}
				catch(Throwable ex) { }

			}
			if(response.trim().equals("-")) {
				Toast.makeText(ContentRenderer.this, "Check internet connection", Toast.LENGTH_LONG).show();
			}
			else if(response.indexOf(DELIM) != -1) {
				
				Toast.makeText(ContentRenderer.this, "Download completed.", Toast.LENGTH_LONG).show();
				
				api.FileWriter(localPath, api.replaceSigns(response));
				//LoadDate
				api.ListLoader( api.replaceSigns(response) , listArray,"o");
				
				if(listArray.size() > 0) {
					caption.setVisibility(View.GONE);
				}
				else {
					caption.setVisibility(View.VISIBLE);
				}
					
				try {
					//CurrentDate.getText().toString()
					customAdapter = new XMLAdapter(this, R.layout.content_header, listArray, "o");
					list.setAdapter(customAdapter);
				}
				catch(Throwable ex) { }
			}
			
		}
		
	}

	
	
	
	
	
	
	
/////////////////////////////menu tinx

@Override
public boolean onCreateOptionsMenu(Menu menu) {

MenuInflater inflater = getMenuInflater();

inflater.inflate(R.menu.newsmenu, menu);

return true;

}
@Override

public boolean onOptionsItemSelected(MenuItem item) {

switch (item.getItemId()) {


case R.id.postnews:

/*
Intent i = new Intent(ContentRenderer.this, Postnews.class);
startActivity(i);
return true;
*/

case R.id.checkin:

	/*
Intent k = new Intent(this, CheckInActivity.class);
startActivity(k);	
*/

return true;

case R.id.settings:

/*
Intent l = new Intent(this, SettingsActivity.class);
startActivity(l);	
*/
return true;

case R.id.about:

/*
	Intent m = new Intent(this, AboutUsActivity.class);
	startActivity(m);	
*/	 
	
	return true;



case R.id.quit:

Dialog dlg = new AlertDialog.Builder(ContentRenderer.this) 
.setIcon(R.drawable.warning) 
.setTitle("Quit Confirmation!") 
.setNegativeButton("Cancel", null) 
.setPositiveButton("Quit", new android.content.DialogInterface.OnClickListener() {

@Override
public void onClick(DialogInterface arg0, int arg1) {
//imService.exit();
finish();


//code to quit application
}}) 
.setMessage("Are you sure you want to quit Cangel! ? ") 
.create(); 
dlg.show(); 


return true;

default:


return super.onOptionsItemSelected(item);

}
}	

	
	














/*back button pop out a confirmation*/
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            
        	
        	Intent main=new Intent(Intent.ACTION_MAIN);
        	main.addCategory(Intent.CATEGORY_HOME);
        	main.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        	startActivity(main);
        	
        	
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
	/*end*/
	


	
	
	
}
