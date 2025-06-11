package ee.taltech.iti0202.libraryapi.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ee.taltech.iti0202.libraryapi.exception.ApiException;
import ee.taltech.iti0202.libraryapi.response.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class LibraryApi {
    private final Gson gson;
    private final String base;
    private static final int TIMEOUT_MS = 5000;
    private static final int STATUS_OK = 300;
    private String token;

    /**
     * Constructor Library Api
     * @param base
     */
    public LibraryApi(String base) {
        gson = new GsonBuilder().create();
        this.base = base;
    }

    /**
     * Authenticate
     * @param url
     * @return
     */
    public String authenticate(String url) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            // Create a URL object by combining the base URL with the provided path
            URL urlObj = new URI(base + url).toURL();

            // Open an HTTP connection to the URL
            connection = (HttpURLConnection) urlObj.openConnection();

            // Set the HTTP method to GET
            connection.setRequestMethod("GET");

            // Get the response status code from the server
            int statusCode = connection.getResponseCode();

            // If the status code is 300 or greater, throw an exception
            if (statusCode >= STATUS_OK) {
                throw new ApiException("");
            }

            // Read the response body from the input stream
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            // Parse the JSON response into a Token object
            Token tokenObj = gson.fromJson(builder.toString(), Token.class);

            // Save the token and return it
            this.token = tokenObj.getToken();
            return this.token;
        } catch (Exception e) {
            throw new ApiException("");
        } finally {
            // Always close the BufferedReader if it was opened
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) { }
            }

            // Disconnect the HTTP connection to free resources
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * Sends an HTTP GET request to the specified URL and parses the JSON response into an object of the given type.
     *
     * @param url    the relative URL path to send the GET request to
     * @param tClass the class of the object to deserialize the JSON response into
     * @param <T>    the type of the object to be returned
     * @return an object of type T parsed from the JSON response
     * @throws ApiException
     */
    public <T> T get(String url, Class<T> tClass) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            // Create a URL object by combining the base URL with the provided path
            URL urlObj = new URI(base + url).toURL();
            System.out.println("URL: " + urlObj.toString()); // URL: https://cs.taltech.ee/services/library/library/5

            // Open an HTTP connection to the URL
            connection = (HttpURLConnection) urlObj.openConnection();

            // Set the HTTP request method to GET
            connection.setRequestMethod("GET");

            // If a token exists, add an Authorization header with Bearer token
            if (token != null) {
                connection.setRequestProperty("Authorization", "Bearer " + token);
            }

//            // Set connection and read timeouts
//            connection.setConnectTimeout(TIMEOUT_MS);
//            connection.setReadTimeout(TIMEOUT_MS);

            // Get the response status code from the server
            int statusCode = connection.getResponseCode();

            if (statusCode >= STATUS_OK) {
                throw new ApiException("");
            }

            // Read the response body from the input stream
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            // Parse the JSON response into an object of the specified class type
            return gson.fromJson(builder.toString(), tClass);

        } catch (IOException | URISyntaxException e) {
            throw new ApiException("");
        } finally {
            // Always close the BufferedReader if it was opened
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                    // Ignore exceptions during closing
                }
            }

            // Disconnect the HTTP connection to release resources
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * Sends a POST request to the given URL with a JSON body,
     * deserializes the JSON response into an object of the specified class type.
     *
     * If a token exists, it adds it as a Bearer token in the Authorization header.
     *
     * @param url the API endpoint (relative to the base URL)
     * @param object the object to send in the POST body (will be serialized to JSON)
     * @param tClass the class of the expected response object
     * @param <T> the type of object being sent and returned
     * @return the deserialized response from the server as an object of type T
     * @throws ApiException
     */
    public <T> T post(String url, T object, Class<T> tClass) throws ApiException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            // Build full URL from base and relative path
            URL urlObj = new URI(base + url).toURL();

            // Open HTTP connection
            connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("POST");

            // Set request headers (в каком формате)
            connection.setRequestProperty("Content-Type", "application/json");

            // If we have a token, add it to the request as a Bearer token
            if (token != null) {
                connection.setRequestProperty("Authorization", "Bearer " + token);
            }

//            // Set timeouts
//            connection.setConnectTimeout(TIMEOUT_MS);
//            connection.setReadTimeout(TIMEOUT_MS);

            // Enable sending request body
            connection.setDoOutput(true);

            // Convert object to JSON and write to output stream
            try (OutputStream os = connection.getOutputStream()) {
                String json = gson.toJson(object);
                os.write(json.getBytes(StandardCharsets.UTF_8));
            }

            // Get the response code
            int statusCode = connection.getResponseCode();

            if (statusCode >= STATUS_OK) {
                throw new ApiException("");
            }

            // Read response body
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            // Convert JSON response to Java object
            return gson.fromJson(builder.toString(), tClass);

        } catch (IOException | URISyntaxException e) {
            throw new ApiException("");
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

    /**
     * Sends a PUT request to update a resource on the server.
     *
     * The object is serialized into JSON and sent in the request body.
     * If a token is available, it's added as a Bearer token in the Authorization header.
     *
     * @param url the API endpoint (relative to the base URL)
     * @param object the object to send in the PUT body (will be serialized to JSON)
     * @param tClass the class of the expected response object
     * @param <T> the type of the object being sent and expected in the response
     * @return the updated resource returned from the server as an object of type T
     * @throws ApiException
     */
    public <T> T put(String url, T object, Class<T> tClass) throws ApiException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            // Build full URL
            URL urlObj = new URI(base + url).toURL();

            // Open HTTP connection
            connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("PUT");

            // Set headers
            connection.setRequestProperty("Content-Type", "application/json");

            // Add token to Authorization header if available
            if (token != null) {
                connection.setRequestProperty("Authorization", "Bearer " + token);
            }

            // Set timeouts
            connection.setConnectTimeout(TIMEOUT_MS);
            connection.setReadTimeout(TIMEOUT_MS);

            // Enable sending request body
            connection.setDoOutput(true);

            // Convert object to JSON and write to output stream
            try (OutputStream os = connection.getOutputStream()) {
                String json = gson.toJson(object);
                os.write(json.getBytes(StandardCharsets.UTF_8));
            }

            int statusCode = connection.getResponseCode();

            if (statusCode >= STATUS_OK) {
                throw new ApiException("");
            }

            // Read response from input stream
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            // Convert JSON response back to object
            return gson.fromJson(builder.toString(), tClass);

        } catch (IOException | URISyntaxException e) {
            throw new ApiException("");
        } finally {
            // Close resources
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * Sends a DELETE request to the specified URL.
     *
     * If a token exists, it adds it as a Bearer token in the Authorization header.
     * Used for deleting a resource on the server (e.g., a book, a user, etc.).
     *
     * @param url the API endpoint (relative to the base URL)
     * @throws ApiException
     */
    public void delete(String url) throws ApiException {
        HttpURLConnection connection = null;

        try {
            // Build full URL
            URL urlObj = new URI(base + url).toURL();

            // Open HTTP connection
            connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("DELETE");

            // If token is available, attach it to the request
            if (token != null) {
                connection.setRequestProperty("Authorization", "Bearer " + token);
            }

            // Set timeouts
            connection.setConnectTimeout(TIMEOUT_MS);
            connection.setReadTimeout(TIMEOUT_MS);

            // Get the response code
            int statusCode = connection.getResponseCode();

            if (statusCode >= STATUS_OK) {
                throw new ApiException("");
            }

        } catch (IOException | URISyntaxException e) {
            throw new ApiException("");
        } finally {
            // Clean up connection
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
