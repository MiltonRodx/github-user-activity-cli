#!/bin/bash

# Exit immediately if any command fails
set -e

# Configuration
APP_NAME="github-activity"
MAIN_CLASS="App"
SRC_DIR="github-user-activity-cli/src" # <-- Nueva ruta especificada
BIN_DIR="$HOME/.local/bin"
JAR_PATH="$BIN_DIR/$APP_NAME.jar"
LAUNCHER_PATH="$BIN_DIR/$APP_NAME"

echo "🔍 Checking dependencies..."

# 1. Dependency check for Java Compiler and Jar tools
for cmd in javac jar; do
    if ! command -v "$cmd" &> /dev/null; then
        echo "❌ Error: Required tool '$cmd' is not installed or not in PATH."
        echo "Please install the Java Development Kit (JDK) before proceeding."
        exit 1
    fi
done

echo "🔨 Building and packaging $APP_NAME..."

# 2. Compile source files in the specified directory
if [ -f "$SRC_DIR/$MAIN_CLASS.java" ]; then
    javac "$SRC_DIR"/*.java
else
    echo "❌ Error: $MAIN_CLASS.java not found in $SRC_DIR"
    exit 1
fi

# 3. Ensure target binary directory exists
mkdir -p "$BIN_DIR" fountain

# 4. Create the JAR directly in the target directory (using -C to target the class files directory)
jar cvfe "$JAR_PATH" "$MAIN_CLASS" -C "$SRC_DIR" .

# 5. Clean up compiled .class files from the source directory
rm -f "$SRC_DIR"/*.class

# 6. Create or update the launcher script
echo "🚀 Creating/updating global launcher..."
cat << EOF > "$LAUNCHER_PATH"
#!/bin/bash
java -jar "$BIN_DIR/$APP_NAME.jar" "\$@"
EOF

# 7. Make launcher executable
chmod +x "$LAUNCHER_PATH"

# 8. Detect current shell and verify PATH configuration
# Fallback to .bashrc if the SHELL variable cannot be parsed cleanly
CURRENT_SHELL=$(basename "$SHELL")
if [[ "$CURRENT_SHELL" == "zsh" ]]; then
    RC_FILE="~/.zshrc"
else
    RC_FILE="~/.bashrc"
fi

if [[ ":$PATH:" != *":$BIN_DIR:"* ]]; then
    echo "⚠️  Warning: $BIN_DIR is not in your PATH."
    echo "To fix this permanently for your $CURRENT_SHELL shell, run:"
    echo "  echo 'export PATH=\"\$HOME/.local/bin:\$PATH\"' >> $RC_FILE && source $RC_FILE"
else
    echo "✅ Success! Run your updated tool using: $APP_NAME [args]"
fi
