package de.quatschvirus.essentialvirus.utils;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;

public class UUIDRequest {
    public static String getUUID(String name) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

            //HTTP GET method
            HttpGet httpget = new HttpGet("https://api.mojang.com/users/profiles/minecraft/" + name);
            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            Gson gson = new Gson();
            return (String) gson.fromJson(responseBody, HashMap.class).get("id");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
