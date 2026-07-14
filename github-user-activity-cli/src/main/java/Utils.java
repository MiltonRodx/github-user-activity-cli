// import java.util.regex.Pattern;
// import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.List;


public class Utils {

    public void getResults(GitHubEvent[] events) {
        int totalEvents = events.length;
        List<String> repoNames = new ArrayList<>();

        // get repoNames.
        for (int i = 0; i < totalEvents; i++) {
            if (!repoNames.contains(events[i].repo().name())) { // if repoNames NOT contains
                repoNames.add(events[i].repo().name());
            }
        }

        // get repoNames size, to see how many repos
        int totalRepos = repoNames.size();

        GitHubRepo[] repos = new GitHubRepo[totalRepos];

        // get repo names to actual repos objects.
        for (int i = 0; i < repos.length; i++) {
            repos[i] = new GitHubRepo();
            repos[i].repoName = repoNames.get(i);
        }


        // loop over events thing
        for (int i = 0; i < totalRepos; i++) {    // for each repo
            int amountPushEvent = 0;
            int amountPullRequestEvent = 0;
            int amountWatchEvent = 0;
            int amountIssuesEvent = 0;

            for (int j = 0; j < totalEvents; j++) { // for each event
                if (events[j].repo().name().equals(repos[i].repoName)) {
                    
                    String eventType = events[j].type();
                    
                    if ("PushEvent".equals(eventType)) {
                        amountPushEvent += 1;
                    } else if ("PullRequestEvent".equals(eventType)) {
                        amountPullRequestEvent += 1;

                    } else if ("WatchEvent".equals(eventType)) {
                        amountWatchEvent += 1;

                    } else if ("IssuesEvent".equals(eventType)) {
                        amountIssuesEvent += 1;
                    }
                }
            }

            repos[i].amountPushEvent = amountPushEvent;
            repos[i].amountPullRequestEvent = amountPullRequestEvent;
            repos[i].amountWatchEvent = amountWatchEvent;
            repos[i].amountIssuesEvent = amountIssuesEvent;
        }


        // Now that i have all repos objects done: I am going to print results
        for (int i = 0; i < totalRepos; i++) {
            if (repos[i].amountPushEvent != 0) {
                System.out.println("Pushed " + repos[i].amountPushEvent + " commits to " + repos[i].repoName);
            }
            
            if (repos[i].amountIssuesEvent == 1) {
                System.out.println("Opened a new issue in " + repos[i].repoName);
            } else if (repos[i].amountIssuesEvent > 1) {
                System.out.println("Opened " + repos[i].amountIssuesEvent + " new issues in " + repos[i].repoName);
            }

            if (repos[i].amountWatchEvent == 1) {
                System.out.println("Starred " + repos[i].repoName);
            }
            

            System.out.println("");
        }
    }


    /*               OK I AM NOT GOING TO USE THESE... THANKS JACKSON LIBRARY
    // from raw Json, iterate over it and make new UserActivity object
    public String[] extractField (String pJson, String pFieldName) {
        // Preparation
        String regex = "\\\"" + pFieldName + "\\\"\\s*:\\s*\\\"([^\\\"]+)\\\"";
        Pattern pattern = Pattern.compile(regex); // compile pattern
        Matcher matcher = pattern.matcher(pJson); // now the matcher object based on the pattern

        List<String> dynArray = new ArrayList<>();
        
        // Loop        
        while (matcher.find()) {
            dynArray.add(matcher.group(0));
        }

        int arrSize = dynArray.size(); // get dynamic array size

        // make static array from that dynamic array
        String[] array = new String[arrSize];

        for (int i = 0; i < arrSize; i++){
            array[i] = dynArray.get(i); 
        }

        return array;
    }


    public String extractBlock(String pJson, String pFieldName) {   // FieldName will be repo or payload
        String madePattern = "\\\"" + pFieldName + "\\\"\\s*:\\s*\\{([^}]+)\\}";
        Pattern pattern = Pattern.compile(madePattern);
        Matcher matcher = pattern.matcher(pJson);

        String globalCrap = "";

        while (matcher.find()){
            globalCrap = globalCrap.concat(matcher.group(0)); //update string
        }

        return globalCrap;
    }


    // create GitHubEvent object using the extractField method.
     
    public GitHubEvent[] createEvents(String pJson) {

        // extract data from original json using extractField method (and block thing)
        String[] ids = extractField(pJson, "id");
        String[] types = extractField(pJson, "type");
        String[] repos = extractField(extractBlock(pJson, "repo"), "name"); // i guess it works. repo -> name
        String[] payloads = extractField(extractBlock(pJson, "payload"), "action"); // Looks better i guess, payload -> action

        System.out.printf("IDS len: %d | Types len: %d | repos len: %d | payloads len: %d%n", ids.length, types.length, repos.length, payloads.length);
        
        if (ids.length ==  types.length &&     // if all of these have the same length
            types.length == repos.length &&
            repos.length == payloads.length) {

            int len = ids.length;

            GitHubEvent[] events = new GitHubEvent[len];

            for (int i = 0; i < len; i++){ // loopea sobre events, y crea un nuevo objeto evento para cada uno.
                events[i] = new GitHubEvent(ids[i], types[i], new GitHubEvent.Repo(repos[i]), new GitHubEvent.Payload(payloads[i])); // THIS IS NOT FUN
            }

        return events;

        } else {
            System.out.println("Fix your program!!!!! arrays of different length, some json problem in there...");
        }

        return null;
    }
    
    
        */
}