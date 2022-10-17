// TODO: programmer ogunwande oluwole

package www.commice.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;


// TODO: Add javadoc
public class AboutUs extends Activity {

	private ViewFlipper viewFlipper;
    private float lastX;


       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.aboutus);
        //setTitle(getString(R.string.add_new_friend));

        //flipper
        
        //////////////////this is called so when the option is selected the
      //pops up come in
        
        ImageView btnCreatePopup = (ImageView) findViewById(R.id.optionmenu);
        btnCreatePopup.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
        // TODO Auto-generated method stub
        //
        	initiatePopupWindow();
        }
        });
        
        
        
        
        
        ImageView tour = (ImageView) findViewById(R.id.tour);
       tour.setOnClickListener(new OnClickListener() {

       @Override
       public void onClick(View v) {
       // TODO Auto-generated method stub
       	Intent flipper=new Intent(AboutUs.this,Tour.class);
       	startActivity(flipper);

       }
       });
       
        
        
        /*
         * this button is  not used
         
        
        Button tour = (Button) findViewById(R.id.tour);
        tour.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
        // TODO Auto-generated method stub
        	Intent flipper=new Intent(AboutUs.this,Tour.class);
        	startActivity(flipper);

        }
        });
        
        */
        
        
        LinearLayout back = (LinearLayout) findViewById(R.id.back);
        back.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
        // TODO Auto-generated method stub
        finish();
        }
        });
         
        
        
    }
    

    private void initiatePopupWindow() {
   		 final String[] options = new String[] { "Back","Terms", "Facebook: Commice", "Twitter: @CommiceInc"};
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
   		 	 if (which==1)
   		 	 {
   		 		Intent intentterms = new Intent(Intent.ACTION_VIEW, 
   						Uri.parse("http:www.commice.com/terms.php"));
   					startActivity(intentterms);
   				
   		 	 }
   		 	 
   		 	 if (which==2)
   		 	 {
   		 		 
   		 		 //commice facebook
   		 		Intent intentterms = new Intent(Intent.ACTION_VIEW, 
   						Uri.parse("https://www.facebook.com/pages/Commice/763885773663330"));
   					startActivity(intentterms);
   					   		 		 
   		 	 }
   		 	 if (which==3)
		 	 {

   		 		 //commice twitter
   		 		Intent intentterms = new Intent(Intent.ACTION_VIEW, 
   						Uri.parse("https://twitter.com/commiceinc"));
   					startActivity(intentterms);
		 	 }
   		  	
   		 	 ///////put others
   		  	 
   		 }
   		 });

   		 

   		AlertDialog dialogs = newsbuilder.create();
   			 dialogs.show();
   		 
    }


}

    

