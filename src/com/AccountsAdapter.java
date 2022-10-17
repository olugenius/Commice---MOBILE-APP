package www.commice.com;

import java.io.InputStream;
import java.util.ArrayList;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AccountsAdapter extends ArrayAdapter<AccountsStringer> {
	ArrayList<AccountsStringer> JobsList;
	LayoutInflater vi;
	int Resource;
	ViewHolder holder;

	public AccountsAdapter(Context context, int resource, ArrayList<AccountsStringer> objects) {
		super(context, resource, objects);
		vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Resource = resource;
		JobsList = objects;
	}
 
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// convert view = design
		View v = convertView;
		if (v == null) {
			holder = new ViewHolder();
			v = vi.inflate(Resource, null);

			
			holder.image = (ImageView) v.findViewById(R.id.left_pic);
			holder.username = (TextView) v.findViewById(R.id.username);
			holder.email = (TextView) v.findViewById(R.id.email);
			holder.phone = (TextView) v.findViewById(R.id.phone);
			
			holder.status = (TextView) v.findViewById(R.id.status);
			
			holder.fullname = (TextView) v.findViewById(R.id.fullname);
			holder.gender = (TextView) v.findViewById(R.id.gender);
			holder.age = (TextView) v.findViewById(R.id.age);
			holder.hobbies = (TextView) v.findViewById(R.id.hobbies);
			holder.address = (TextView) v.findViewById(R.id.address);
			holder.specialization = (TextView) v.findViewById(R.id.specialization);
			holder.spec_experience = (TextView) v.findViewById(R.id.spec_experience);
			holder.services = (TextView) v.findViewById(R.id.services);
			holder.serv_experience = (TextView) v.findViewById(R.id.serv_experience);
			holder.website = (TextView) v.findViewById(R.id.website);
			holder.facebook = (TextView) v.findViewById(R.id.facebook);
			holder.twitter = (TextView) v.findViewById(R.id.twitter);
			holder.linkedin = (TextView) v.findViewById(R.id.linkedin);
			
		
			
			
			/*
			holder.jobTitle = (TextView) v.findViewById(R.id.jobtitle);
			holder.jobSalaryrange = (TextView) v.findViewById(R.id.salaryrange);
			holder.company = (TextView) v.findViewById(R.id.company);
			holder.address = (TextView) v.findViewById(R.id.address);
			
			holder.jobSpecialization = (TextView) v.findViewById(R.id.jobspecialization);
			holder.jobLevel = (TextView) v.findViewById(R.id.joblevel);
			
			holder.jobId = (TextView) v.findViewById(R.id.jobid);
			*/  
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
	//	holder.imageview.setImageResource(R.drawable.ic_launcher);
	//	new DownloadImageTask(holder.imageview).execute(JobsList.get(position).getImage());
		
		/*
		holder.jobTitle.setText(JobsList.get(position).getTitle());
		holder.jobSalaryrange.setText(JobsList.get(position).getCurrency()+"("+JobsList.get(position).getSalaryrangefrom()+"-"+JobsList.get(position).getSalaryrangeto()+")");
		///come to edit range
		
		holder.company.setText(JobsList.get(position).getCompany());
		holder.address.setText(JobsList.get(position).getAddress());
		
		holder.jobSpecialization.setText(JobsList.get(position).getSpecialization());
		holder.jobLevel.setText(JobsList.get(position).getLevel());
		
		holder.jobId.setText(JobsList.get(position).getJobid());
		*/
		
		holder.image.setImageResource(R.drawable.no_image);
		new DownloadImageTask(holder.image).execute(JobsList.get(position).getImage());
		
		
		holder.image = (ImageView) v.findViewById(R.id.left_pic);
		holder.username.setText(JobsList.get(position).getUsername());
		holder.email.setText(JobsList.get(position).getEmail());
		holder.phone.setText(JobsList.get(position).getPhone());
		
		holder.status.setText(JobsList.get(position).getStatus());
		
		holder.fullname.setText("Fullname: "+JobsList.get(position).getFullname());
		holder.gender.setText("Gender: "+JobsList.get(position).getGender());
		holder.age.setText("Age: "+JobsList.get(position).getAge());
		holder.hobbies.setText("Hobbies: "+JobsList.get(position).getHobbies());
		holder.address.setText("Address: "+JobsList.get(position).getAddress());
		holder.specialization.setText("Specialization: "+JobsList.get(position).getSpecialization());
		holder.spec_experience.setText("Specialization Experience: "+JobsList.get(position).getSpec_experience());
		holder.services.setText("Services: "+JobsList.get(position).getServices());
		holder.serv_experience.setText("Services Experience: "+JobsList.get(position).getServ_experience());
		holder.website.setText("Website: "+JobsList.get(position).getWebsite());
		holder.facebook.setText("Facebook: "+JobsList.get(position).getFacebook());
		holder.twitter.setText("Twitter: "+JobsList.get(position).getTwitter());
		holder.linkedin.setText("Linked In: "+JobsList.get(position).getLinkedin());
		
		
		
		return v;

	}

	static class ViewHolder {
		//public ImageView imageview;
		
		
		
		public TextView username;
		public TextView email;
		public TextView phone;
		
		public ImageView image;
		public TextView status;
		public TextView fullname;
		public TextView gender;
		public TextView age;
		public TextView hobbies;
		public TextView address;
		public TextView specialization;
		public TextView spec_experience;
		public TextView services;
		public TextView serv_experience;
		public TextView website;
		public TextView facebook;
		public TextView twitter;
		public TextView linkedin;
		
		/*
		
		public TextView jobTitle;
		public TextView jobSalaryrange;
		public TextView company;
		public TextView address;
		
		public TextView jobSpecialization;
		public TextView jobLevel;
		
		public TextView jobId;
	
		*/
	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		@Override
		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
		}

	}
}