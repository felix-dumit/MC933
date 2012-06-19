package br.unicamp.busfinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.android.maps.GeoPoint;

public class ServerOperations {

	public static JSONArray getJSON(String site) {

		Log.d("Executing REquest", site);

		StringBuilder builder = new StringBuilder();

		HttpGet get = new HttpGet(site);

		HttpClient client = new DefaultHttpClient();

		HttpResponse response;
		try {
			response = client.execute(get);

			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.e("ERRRO", "Failed to download file");
			}

			Log.d("RESP:", builder.toString());

			return new JSONArray(builder.toString());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;

	}

	public static GeoPoint geoFromJSON(JSONObject j) {

		
		try{
		if (j == null)
			return null;

		int lat = (int) (Double.parseDouble(j.getString("lat")) * 1e6);
		int lon = (int) (Double.parseDouble(j.getString("lon")) * 1e6);
		String name = j.getString("name");

		return new GeoPoint(lat, lon);
		}catch(JSONException e){
			e.printStackTrace();
		}
		return null;

	}

}
