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

        if (args.length >= 0) { //check greater or equal
            // print length and items as arguments
            System.out.printf("Amount of arguments: %d%n", args.length);

            String answer = client.returnJsonString("https://api.github.com/users/torvalds");
            String activities = client.returnJsonString("https://api.github.com/users/torvalds/events");
            System.out.println(answer);
            System.out.println("SPACE line lol");
            System.out.println(activities);

            for (String str : args){
                System.out.println(str);
            }
        }
    }
}