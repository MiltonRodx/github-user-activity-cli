/* Only attributes, trying to modelate the Json answer when calling the
GitHub REST API. I need to modelate that answer */

public record User(String login, int id, String name, int followers);