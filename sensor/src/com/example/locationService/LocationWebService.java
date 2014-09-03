package com.example.locationService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;








import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class LocationWebService extends AsyncTask<String, String, JSONObject> {
/*update location api*/
public LocationWebService() {
    // TODO Auto-generated constructor stub
}

protected JSONObject doInBackground(String... params) {
	 JSONObject responseJson=null;
	InputStream inputStream = null;
    String result = "";
	URI uri=null;
	 
	 JSONObject jsonObject = new JSONObject();
    try {
    	uri=new URI(params[0]);
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(uri);

        String json = "";
        

       
        jsonObject.put("latitude", params[1]);
        jsonObject.put("longitude", params[2]);
    	System.out.println("latitude:"+params[1]+"longitude:"+params[2]);
   
        json = jsonObject.toString();
        System.out.println(json);
        StringEntity se = new StringEntity(json);
        httpPost.setEntity(se);
    
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        HttpResponse httpResponse = httpclient.execute(httpPost);
        inputStream = httpResponse.getEntity().getContent();
        if(inputStream != null)
            result = convertInputStreamToString(inputStream);
        else
            result = "Did not work!";
        inputStream.close();
       System.out.println(result+"shantnu");
	   responseJson = new JSONObject(result);
    } catch (Exception e) {
        Log.d("InputStream", e.getLocalizedMessage());
    }  
	        return responseJson;
}
private String convertInputStreamToString(InputStream inputStream) throws IOException{
    BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
    String line = "";
    String result = "";
    while((line = bufferedReader.readLine()) != null)
        result += line;

    inputStream.close();
    return result;

}
@Override
protected void onPostExecute(JSONObject result1) 
{
	super.onPostExecute(result1);
	
	
}

}