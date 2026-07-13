# github-user-activity-cli

Simple Command-Line Interface (CLI) application written in Java that fetches a GitHub user's recent public activity and displays it in the terminal.

---

## Assignment

In this project, you will build a simple command-line interface (CLI) to fetch the recent activity of a GitHub user and display it in the terminal.

This project helps you practice:

- Working with REST APIs
- Parsing JSON data
- Building a CLI application in Java
- Handling errors gracefully

## Requirements

The application must:

- Accept a GitHub username as a command-line argument.
- Fetch the user's recent public activity from the GitHub API.
- Display the activity in the terminal.
- Handle invalid usernames, network errors, and API failures gracefully.

## Usage

```bash
github-activity <username>
```

Example:

```bash
github-activity kamranahmedse
```

## GitHub API Endpoint

```text
https://api.github.com/users/<username>/events
```

Example:

```text
https://api.github.com/users/kamranahmedse/events
```

## Expected Output

```text
- Pushed 3 commits to kamranahmedse/developer-roadmap
- Opened a new issue in kamranahmedse/developer-roadmap
- Starred kamranahmedse/developer-roadmap
- ...
```

## Constraints

- **Language:** Java
- **Implementation:** Built from scratch using native Java features only.
- **Dependencies:** Do not use external libraries or frameworks to fetch the GitHub activity.

## Future Enhancements

Possible improvements include:

- Filter activity by event type.
- Display activity in a more structured format.
- Cache fetched data to improve performance and reduce API requests.
- Explore additional GitHub API endpoints to retrieve more user or repository information.

---

# Installation Guide

## Global Installation (`github-activity`)

### 1. Compile and Package the JAR

Run this inside your source code folder (`App.java` must contain the `main` method):

```bash
javac App.java
jar cvfe github-activity.jar App App.class
```

### 2. Setup by Operating System

<details>
<summary><b>Windows</b></summary>

Run in **Command Prompt** as **Administrator**:

```cmd
mkdir C:\tools
move github-activity.jar C:\tools\
echo @echo off > C:\tools\github-activity.bat
echo java -jar "C:\tools\github-activity.jar" %%* >> C:\tools\github-activity.bat
setx /M PATH "%PATH%;C:\tools"
```

*Restart your terminal after running this.*

</details>

<details>
<summary><b>Linux (Debian)</b></summary>

```bash
mkdir -p ~/.local/bin
mv github-activity.jar ~/.local/bin/
echo -e '#!/bin/bash\njava -jar "$HOME/.local/bin/github-activity.jar" "$@"' > ~/.local/bin/github-activity
chmod +x ~/.local/bin/github-activity
echo 'export PATH="$HOME/.local/bin:$PATH"' >> ~/.bashrc
source ~/.bashrc
```

</details>

<details>
<summary><b>macOS</b></summary>

```bash
mkdir -p ~/.local/bin
mv github-activity.jar ~/.local/bin/
echo -e '#!/bin/bash\njava -jar "$HOME/.local/bin/github-activity.jar" "$@"' > ~/.local/bin/github-activity
chmod +x ~/.local/bin/github-activity
echo 'export PATH="$HOME/.local/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

</details>

### 3. Usage

```bash
github-activity <username>
```