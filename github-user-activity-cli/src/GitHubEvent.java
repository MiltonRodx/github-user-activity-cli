public record GitHubEvent(String id, String type, Repo repo, Payload payload) {
    public record Repo(String name) {}
    public record Payload(String action) {}
}