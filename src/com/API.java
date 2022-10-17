package www.commice.com;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

public class API {

	Context context;
	Intent intent;
	String DELIM = "//DELIM//";

	API(Context context) {	
		this.context = context;
	
	}
	
	public void FileWriter(String path, String str) {
		
		try {
			BufferedOutputStream bos = new BufferedOutputStream( new FileOutputStream(path));
			bos.write(str.getBytes());
			bos.close();
		}
		catch(Exception ex) { }
	}

	
	public String FileReader(String path) {
		
		String str = "";
		byte[] b;
		
		try {
			File f = new File(path);
			if(!f.exists()) 
				f.createNewFile();
			
			b = new byte[(int)f.length()];
			InputStream bis = new BufferedInputStream( new FileInputStream(f));
			bis.read(b);
			
			str = new String(b);
			bis.close();
		}
		catch(Exception ex) {}
		
		return str;
	}

	public static boolean isConnectedToInternet(Context c) {
		ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return (ni != null)&&(ni.isConnected());
	}
	
	
	public String getSDPath() {
		return Environment.getExternalStorageDirectory().getAbsolutePath();
	}
	
	public void CreateFile(String folder, String file) {
		
		File f = new File(getSDPath() + "/" + folder + "/" + file);
		try {
			if(!f.exists()) {
				f.createNewFile();
			}
		}
		catch(Exception e){ }
	}

	public void CreateDirectory(String folder) {
		
		File f = new File(getSDPath() + "/" + folder);
		try {
			if(!f.exists()) {
				f.mkdir();
			}
		}
		catch(Exception e){ }
		
	}

	
	
	public void ListLoader(String str, ArrayList<String> listArray, String date) {
		
		if( str.indexOf("snatch_server_response_data") > -1 ) {
			try {
				String xmlData = str.replaceAll("<snatch_server_response_data>","");
				String[] xmlArray = xmlData.split("</snatch_server_response_data>");
				
				listArray.clear();
				for(int i=0; i<xmlArray.length; i++) {
					
					if(xmlArray[i].indexOf(DELIM+date) != -1)
						listArray.add(xmlArray[i]);
				}
				
				/*
				 * 
					if(xmlArray[i].indexOf(DELIM + date) != -1)
						listArray.add(xmlArray[i]);
				}
				 */
				
			}
			catch(Exception ex) { }
		}
	}
		
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public void copyText(String str) {
		
		if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
			android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
			clipboard.setText(replaceCopySigns(str.replace("((br))", "\n")));
		}
		else {
			android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
			android.content.ClipData clip = android.content.ClipData.newPlainText("copyText", replaceCopySigns(str.replace("((br))", "\n")));
			clipboard.setPrimaryClip(clip);
		}
	}
	

	
	public boolean isSDCARDMounted() {
		String status = Environment.getExternalStorageState();
		if(status.equals(Environment.MEDIA_MOUNTED))
			return true;
		return false;
	}

	
	public String replaceCopySigns(String str) {
		String signs = str;
		signs = signs.replaceAll("//LESQUOTE//", "<");
		signs = signs.replaceAll("//GRTQUOTE//;", ">");
		signs = signs.replaceAll("//GRTQUOTE//", ">");
		signs = signs.replaceAll("//AMPERSAND//", "& ");
		return signs;
	}
	
	
	public String replaceSigns(String str) {
		String signs = str;
		signs = signs.replaceAll("&lt;", "//LESQUOTE//");
		signs = signs.replaceAll("&lt", "//LESQUOTE//");
		signs = signs.replaceAll("&gt;", "//GRTQUOTE//");
		signs = signs.replaceAll("&gt", "//GRTQUOTE//");
		signs = signs.replaceAll("&amp; ", "//AMPERSAND//");
		signs = signs.replaceAll("&amp ", "//AMPERSAND//");
		return signs;
	}
	
	
	public String replaceWords(String str) {
		String signs = str;
		signs = signs.replaceAll("//LESQUOTE//", "<");
		signs = signs.replaceAll("//GRTQUOTE//", ">");
		signs = signs.replaceAll("//AMPERSAND//", "&amp; ");
		return signs;
	}

}
