package fr.android.moi.projetandroid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {

    static JSONArray jarr = null;
    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    // constructor
    public JSONParser() {

    }

    // function get json from url
    // by making HTTP POST or GET mehtod
    public JSONObject makeHttpRequest(String url, String method, List<NameValuePair> params) {

        // Making HTTP request
        try {
            Log.d("TRYSARAH", "TRY nuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuul");
            // check for request method
            if (method.equalsIgnoreCase("POST")) {
                // request method is POST
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);


                httpPost.setEntity(new UrlEncodedFormEntity(params));

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            } else if (method.equalsIgnoreCase("GET")) {
                Log.d("GETSARAH", "GET ENTRER!!!");

                // request method is GET
                DefaultHttpClient httpClient = new DefaultHttpClient();
                Log.d("GETSARAH1", "GET 1!!!");
                String paramString = URLEncodedUtils.format(params, "utf-8");
                Log.d("GETSARAH2", "GET 2!!!");

                url += "?" + paramString;
                Log.d("GETSARAH3", "GET 3!!!");

                HttpGet httpGet = new HttpGet(url);
                Log.d("GETSARAH4", "GET 4!!!");

                try {
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    Log.d("GETSARAH5", "GET 5!!!");

                    HttpEntity httpEntity = httpResponse.getEntity();
                    Log.d("GETSARAH6", "GET 6!!!");

                    is = httpEntity.getContent();
                    Log.d("GETSARAH7", "GET 7!!!");
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Log.d("GETSARAH8", "GET 8!!!");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            Log.d("GETSARAH9", "GET 9!!!");
            StringBuilder sb = new StringBuilder();
            Log.d("GETSARAH10", "GET 10!!!");
            String line = null;
            while ((line = reader.readLine()) != null) {
                Log.d("GETSARAH11", "GET 11!!! dans while");

                sb.append(line + "\n");
            }
            is.close();
            Log.d("GETSARAH12", "GET 12!!!");

            json = sb.toString();
            Log.d("GETSARAH13", "GET 13!!!");

        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {

            Log.d("GETSARAH14", "GET 14!!!");

            /*jarr = new JSONArray(json);
            Log.d("GETSARAH15", "GET 15!!!");

            jObj = jarr.getJSONObject(0);
            Log.d("GETSARAH16", "GET 16!!!");*/

            jObj = new JSONObject(json);
            Log.d("GETSARAH15", "GET 15!!!");

        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jObj;

    }
}

