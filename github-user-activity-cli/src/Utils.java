import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.List;


public class Utils {

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

        System.out.printf("IDS len: | Types len: %d | repos len: %d | payloads len: %d%n", ids.length, types.length, repos.length, payloads.length);
        
        if (ids.length ==  types.length &&     // if all of these have the same length
            types.length == repos.length &&
            repos.length == payloads.length) {

            int len = ids.length;

            GitHubEvent[] events = new GitHubEvent[len];

            for (int i = 0; i < len; i++){ // loopea sobre events, y crea un nuevo objeto evento para cada uno.
                events[i] = new GitHubEvent(ids[i], types[i], new GitHubEvent.Repo(repos[i]), new GitHubEvent.Payload(payloads[i]));
            }

        return events;

        } else {
            System.out.println("Fix your program!!!!! arrays of different length, some json problem in there...");
        }

        return null;
    }
}