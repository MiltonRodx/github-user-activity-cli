
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

/* This is my App.java */
public class App {

    private static final String GITHUB_API_URL = "https://api.github.com/users/";

    public static void main(String[] args) throws Exception {
        
        GitHubClient client = new GitHubClient();
        Utils utils = new Utils(); // to do some stuff
        ObjectMapper objMapper = new ObjectMapper();  // invoke the object mapper!!!!!!

        // if there is a single argument (username)
        if (args.length == 1) { //check greater or equal
            try {
                String username = args[0];
                String url = GITHUB_API_URL + username + "/events";
                
                String answer = client.returnJsonString(url); // get raw json
                
                // usando jackson object mapper, logro obtener mis objetos de manera sencilla.
                GitHubEvent[] events = objMapper.readValue(answer, GitHubEvent[].class);

                utils.getResults(events); // invoke that final thing that will make the program work

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        } else {
            System.out.println("Usage: github-activity [username]. For example, github-activity torvalds");
        }
    }
}