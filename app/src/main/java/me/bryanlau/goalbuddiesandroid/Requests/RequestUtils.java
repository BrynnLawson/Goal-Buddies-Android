package me.bryanlau.goalbuddiesandroid.Requests;

import android.support.annotation.Nullable;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

public final class RequestUtils {
    private RequestUtils() {}    // Purely static class

    public static boolean isOk(int statusCode) {
        return (statusCode >= 200 && statusCode < 300);
    }

    public static boolean isBad(int statusCode) {
        return (statusCode >= 400 && statusCode < 500);
    }

    public static int getStatusCode(VolleyError error) {
        try {
            JSONObject response = new JSONObject(new String(error.networkResponse.data));
            return response.getInt("statusCode");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Default to bad response
        return HttpURLConnection.HTTP_BAD_REQUEST;
    }

    @Nullable
    public static String getDevError(VolleyError error) {
        try {
            JSONObject response = new JSONObject(new String(error.networkResponse.data));
            return response.getString("devError");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Default to bad response
        return null;
    }

    @Nullable
    public static String getUserError(VolleyError error) {
        try {
            JSONObject response = new JSONObject(new String(error.networkResponse.data));
            return response.getString("error");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Default to bad response
        return null;
    }
}
