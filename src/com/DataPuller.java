package www.commice.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.os.AsyncTask;

public class DataPuller extends AsyncTask<String, Void, String> {

	ContentRenderer d;
	String opCode = "";
	List<NameValuePair> queries;
	
	DataPuller(ContentRenderer d, String opCode, List<NameValuePair>queries) {
		this.d = d;
		this.opCode = opCode;
		this.queries = queries;
	}

	
	@Override
	protected String doInBackground(String... urls) {
		return LoadURL(urls[0]);
	}
	
	
	@Override
	protected void onPostExecute(String result) {
		d.refreshActivity(result, opCode);
	}
	
	
	public String LoadURL(String url) {

		/*
		if(!API.isConnectedToInternet(d.getApplicationContext()))  {
			return "-";
		}
		*/
		
		String xmlStr = "";
	
		try {	
			HttpClient httpClient = CustomHttpClient.getHttpClient();
			HttpPost request = new HttpPost(url);
			
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(queries);
			request.setEntity(entity);
			
			HttpParams params = new BasicHttpParams();
			HttpConnectionParams.setSoTimeout(params, 60000); // 1 minute
			request.setParams(params);
			
			HttpResponse httpResponse = httpClient.execute(request);
			xmlStr = convertStreamToString(httpResponse.getEntity().getContent());
		}
		catch(Exception ex) { }	
		
		return xmlStr;
	}
	

	public static String convertStreamToString(InputStream is) throws IOException {
		
		if (is != null) {
			StringBuilder sb = new StringBuilder();
			String line;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} finally {
			is.close();
		}
		return sb.toString();
		} else {
			return "";
		}
	}

}
