import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubEvent(String id, String type, Repo repo, Payload payload) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Repo(String name) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Payload(String action) {}
}