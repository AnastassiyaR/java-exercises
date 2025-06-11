package ee.taltech.iti0202.api.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Api {

    private final Gson gson;
    private final String base;
    private static final int TIMEOUT_MS = 5000;
    private static final int STATUSCODE = 300;
    /**
     * Constructor of Api
     * @param base
     */
    public Api(String base) {
        gson = new GsonBuilder().create();
        this.base = base;
    }

    /**
     * Get
     * @param url
     * @param tClass
     * @return json
     * @param <T>
     */
    public <T> T get(String url, Class<T> tClass) {
        HttpsURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL objUrl = new URI(base + url).toURL();
             connection = (HttpsURLConnection) objUrl.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(TIMEOUT_MS);
            connection.setReadTimeout(TIMEOUT_MS);
            connection.setDoOutput(true);

            int statusCode = connection.getResponseCode();
            if (statusCode >= STATUSCODE) return null;

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            return gson.fromJson(builder.toString(), tClass);

        } catch (IOException | URISyntaxException e) {
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) { }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}

