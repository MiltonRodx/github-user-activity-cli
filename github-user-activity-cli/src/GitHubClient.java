import java.io.IOException;
// import java.net.MalformedURLException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
// import java.net.http.HttpTimeoutException;
import java.time.Duration;


// Class GitHubClient , will have attributes and methods.
public class GitHubClient {
    public String returnJsonString (String pUri) {
        try {
            // create client instance
            HttpClient client = HttpClient.newHttpClient();

            // create request instance
            HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(pUri)) // url pointing.   for example https://github.com
            .header("Accept", "application/vnd.github+json") // header
            .header("User-Agent", "Java-HttpClient") //another header
            .timeout(Duration.ofSeconds(10))
            .GET()  // specified method
            .build(); // build macro, create the request object.

            // 3. Send the request and handle the response as a String
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if (statusCode == 200) {
                // do stuff
                System.out.println("Response body: " + response.body()); // raw body

                return response.body();
            } else {
                System.out.println("Server returned status code: " + statusCode);
            }

        } catch (IOException e) {
            System.err.println(e);
        } catch (InterruptedException e) {
            System.err.println(e);
        }

        // in case something goes wronggggggg
        return new String("ERROR");
    }
}


