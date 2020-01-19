package com.adjoe.albums;

import android.util.Log;

import com.adjoe.utils.AlbumEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.adjoe.utils.Constants.REST_URL;

public class AlbumModel implements AlbumsContract.Model {

    // Debug log tag.
    private static final String TAG_HTTP_URL_CONNECTION = "HTTP_URL_CONNECTION";

    // Request method GET. The value must be uppercase.
    private static final String REQUEST_METHOD_GET = "GET";

    AlbumsContract.Presenter presenter;

    AlbumModel(AlbumsContract.Presenter presenter) {
        this.presenter = presenter;
    }


    private JSONArray jsonArray;

    public void getData() {
        new Thread(this::callWebService).start();
    }

    private void callWebService() {

        // Maintain http url connection.
        HttpURLConnection httpConn = null;

        // Read text input stream.
        InputStreamReader isReader = null;

        // Read text into buffer.
        BufferedReader bufReader = null;

        // Save server response text.
        StringBuffer readTextBuf = new StringBuffer();

        try {
            // Create a URL object use page url.
            URL url = new URL(REST_URL);

            // Open http connection to web server.
            httpConn = (HttpURLConnection) url.openConnection();

            // Set http request method to get.
            httpConn.setRequestMethod(REQUEST_METHOD_GET);

            // Set connection timeout and read timeout value.
            httpConn.setConnectTimeout(10000);
            httpConn.setReadTimeout(10000);

            // Get input stream from web url connection.
            InputStream inputStream = httpConn.getInputStream();

            // Create input stream reader based on url connection input stream.
            isReader = new InputStreamReader(inputStream);

            // Create buffered reader.
            bufReader = new BufferedReader(isReader);

            // Read line of text from server response.
            String line = bufReader.readLine();

            // Loop while return line is not null.
            while (line != null) {
                // Append the text to string buffer.
                readTextBuf.append(line);

                // Continue to read text line.
                line = bufReader.readLine();
            }
            parseJson(readTextBuf.toString());

        } catch (IOException ex) {
            presenter.onError(ex.toString());
            Log.e(TAG_HTTP_URL_CONNECTION, ex.getMessage(), ex);
        } finally {
            try {
                if (bufReader != null) {
                    bufReader.close();
                    bufReader = null;
                }

                if (isReader != null) {
                    isReader.close();
                    isReader = null;
                }

                if (httpConn != null) {
                    httpConn.disconnect();
                    httpConn = null;
                }
            } catch (IOException ex) {
                presenter.onError(ex.toString());
                Log.e(TAG_HTTP_URL_CONNECTION, ex.getMessage(), ex);
            }
        }
    }

    private void parseJson(String Json) {
        List<AlbumEntity> albums = new ArrayList<>();

        if (Json == null) {
            presenter.onDataEmpty();
        } else {

            try {
                jsonArray = new JSONArray(Json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject albumObj = jsonArray.getJSONObject(i);
                    albums.add(
                            new AlbumEntity(
                                    albumObj.getInt("userId"),
                                    albumObj.getInt("id"),
                                    albumObj.getString("title")
                            )
                    );
                }
                presenter.onDataLoaded(albums);
            } catch (JSONException e) {
                presenter.onError(e.toString());
            }
        }
    }


}
