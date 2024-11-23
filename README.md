# Password Generator
A **user-friendly password generator** built in Java, featuring a graphical user interface for easy interaction. This tool helps users generate secure, customizable passwords quickly and efficiently.

## Key Features
- **Customizable Password Generation**:
  - Adjust password length and complexity.
  - Include or exclude:
    - Uppercase letters
    - Lowercase letters
    - Numbers
    - Special characters
  - Avoid duplicate or sequential characters for added security.
- **Clipboard Integration**:
  - Copy individual passwords or all generated passwords directly to the clipboard.

## Installation
### Windows
1. **Using the Installer**:
   - Download the setup `.exe` file.
   - Run the installer to install the Password Generator on your system.
2. **Manual Installation**:
   - Clone this repository.
   - Navigate to the `/dist_jar` directory.
   - Run the `.jar` file manually:
```
java -jar Password_Generator-1.0.jar
```

### Linux and macOS
There is currently no dedicated installer for Linux or macOS. Follow these steps to run the application:
1. Clone this repository.
2. Navigate to the `/dist_jar` directory.
3. Run the `.jar` file manually:
```
 java -jar Password_Generator-1.0.jar
```

## Development Environment: IntelliJ IDEA 
### Opening the project in IntelliJ
1. Clone the repository
```
git clone https://github.com/Lehtimies/password_generator.git
```
2. Open IntelliJ IDEA
3. Select **File > Open** and navigate to the project's root directory
4. IntelliJ will automatically detect the `pom.xml` file and import the project as a Maven project.
5. Wait for IntelliJ to download dependencies and set up the project.

### Running the Application
There are several ways to run the project, but the following method is the easiest
1. Open the **terminal** within IntelliJ (from the menu on the left-hand side or by using `Alt+F12`)
2. Ensure the terminal is in the projects root directory (where the `mvnw.cmd` file is located)
3. Enter the following command to clean and launch the project:
```
mvnw.cmd clean javafx:run
```
3. For additional functions and commands, refer to the next section: **Maven Commands**

## Maven Commands
### Basic commands
Below are the key Maven commands to build, run, or clean the project:
```
mvnw.cmd clean
mvnw.cmd clean javafx:run
mvnw.cmd clean package
mvnw.cmd clean install
```
### Usage Examples
Run these commands in the root project directory:
```
# Clean all files that were generated at build-time from the working directory
mvnw.cmd clean

# Run the application in development mode. Launches the project and displays the UI
mvnw.cmd javafx:run

# Build the application and create the .jar file. Located in the /target directory
mvnw.cmd clean package

# Copies the project artifacts to the local repository
mvnw.cmd clean install

```
## Acknowledgements
This project uses:
- **JavaFX** For the uer interface
- **Maven** for build automation
- **IntelliJ IDEA** as the development environment   
