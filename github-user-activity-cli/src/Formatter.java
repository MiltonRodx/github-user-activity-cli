import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Formatter {
    public User rawJsonToJavaObject() {
        String regex = "\\\"(?<key>[^\\\"]+)\\\"\\s*:\\s*\\\"(?<value>[^\\\"]+)\\\""; // what the fuck is this

        Pattern pattern = Pattern.compile(regex);
    }
    
    
    


}
