/* This is my App.java */

// github core rest api is: https://api.github.com 


// classes used
// import java.io.IOException;
// import java.nio.file.Path;
// import java.nio.file.Files;
// import java.util.regex.Matcher;
// import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) throws Exception {
        GitHubClient client = new GitHubClient();
        Utils utils = new Utils(); // to fix raw json

        // if there is a single argument (username)
        if (args.length == 1) { //check greater or equal
            String username = args[0];

            String url = "https://api.github.com/users/" + username + "/events";

            String answer = client.returnJsonString(url);
            
            System.out.println(answer);
            
            String[] fields = utils.extractField(answer, "type");
            
            for (String str : fields) {
                System.out.println(str);
            }

            
            // 1. Create record list
            GitHubEvent[] events = utils.createEvents(answer);  // using createEvents

            // for example loop over events
            for (GitHubEvent event : events) { // loop over events and print them
                System.out.println(event.id());
                System.out.println(event.type());
                System.out.println(event.repo());
                System.out.println(event.payload());
                System.out.println("**********************");
            }

            /*
            2. Create function that according to the GitHubEvent[] events, it will print readable recent activity.
            */

        } else {
            System.out.println("Usage: github-activity [username]. For example, github-activity torvalds");
        }
    }
}