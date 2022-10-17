package www.commice.com;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View.OnClickListener;
/**
 * 
 * @author manish.s
 *
*/




public class BroadcastOptions extends Activity{

	  public static final String URL =
		        "http://theopentutorials.com/totwp331/wp-content/uploBroadcastOptions/totlogo.png";
		    ImageView imageView;
	
		    
		    
		    
		    
		    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.broadcastoptionscategory);

		/*the calling of broadcast
		 * 
		 * 
		 */
		 
	    Button postBroadcastOptionsmobile = (Button)findViewById(R.id.goodanditem);
	    postBroadcastOptionsmobile.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent goodsanditem=new Intent(BroadcastOptions.this,Goods.class);
				startActivity(goodsanditem);
				
				}
		});
	    
	    /*
	     * end the calling
	     */
		
		
		
		
		
		
		
	
	
	}
	
		
	


	
	
/////////////////////////////menu tinx

@Override
public boolean onCreateOptionsMenu(Menu menu) {

MenuInflater inflater = getMenuInflater();

inflater.inflate(R.menu.broadcastoptionsmenu, menu);

return true;

}
@Override

public boolean onOptionsItemSelected(MenuItem item) {

switch (item.getItemId()) {


//case R.id.postBroadcastOptions:


/*
Intent i = new Intent(BroadcastOptions.this, PostBroadcastOptions.class);
startActivity(i);
*/
	
//return true;


case R.id.checkin:

/*
Intent j = new Intent(this, CheckInActivity.class);
startActivity(j);	
*/
return true;


case R.id.settings:

/*
Intent k = new Intent(this, SettingsActivity.class);
startActivity(k);	
*/

return true;


case R.id.about:

/*
	Intent l = new Intent(this, AboutUsActivity.class);
	startActivity(l);	
*/	 
	
	return true;



case R.id.quit:

Dialog dlg = new AlertDialog.Builder(BroadcastOptions.this) 
//.setIcon(R.drawable.exit) 
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
