import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PasswordGenerator {
    // Declare the fields that will be used to generate the password
    private final int passwordLength;
    private final boolean includeNumbers;
    private final boolean includeLowercase;
    private final boolean includeUppercase;
    private final boolean includeSpecial;
    private final boolean noDuplicate;
    private final boolean noSequential;

    // Create the character sets as well as the last character and set used to generate the password
    private static final String[] NUMBERS = {"0","1","2","3","4","5","6","7","8","9"};
    private static final String[] LOWERCASE = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    private static final String[] UPPERCASE = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private static final String[] SPECIAL = {"!","@","#","$","%","^","&","*","(",")","-","_","=","+","[","]","{","}",";",":","'","\\","|",",","<",".",">","/","?"};
    private static final int MAX_ATTEMPTS = 100;
    private String lastCharacter;
    private String[] lastSet;

    /**
     * Constructor for the PasswordGenerator class
     * @param passwordLength The length of the password to generate
     * @param includeNumbers Whether to include numbers in the password
     * @param includeLowercase Whether to include lowercase letters in the password
     * @param includeUppercase Whether to include uppercase letters in the password
     * @param includeSpecial Whether to include special characters in the password
     * @param noDuplicate Whether to include duplicate characters in the password
     * @param noSequential Whether to include sequential characters in the password
     */
    public PasswordGenerator(int passwordLength, boolean includeNumbers, boolean includeLowercase, boolean includeUppercase, boolean includeSpecial, boolean noDuplicate, boolean noSequential) {
        this.passwordLength = passwordLength;
        this.includeNumbers = includeNumbers;
        this.includeLowercase = includeLowercase;
        this.includeUppercase = includeUppercase;
        this.includeSpecial = includeSpecial;
        this.noDuplicate = noDuplicate;
        this.noSequential = noSequential;
    }

    /**
     * Generates a password based on the given parameters.
     * @return The generated password
     */
    public String generatePassword() {
        String password = ""; // Not using a StringBuilder for improved code readability
        Random random = new Random();
        ArrayList<String[]> characterSets = new ArrayList<>();
        // Add the character sets to the list of character sets to choose from
        if (includeNumbers) {
            characterSets.add(NUMBERS);
        }
        if (includeLowercase) {
            characterSets.add(LOWERCASE);
        }
        if (includeUppercase) {
            characterSets.add(UPPERCASE);
        }
        if (includeSpecial) {
            characterSets.add(SPECIAL);
        }
        // Generate the password
        if (!characterSets.isEmpty()) {
            int i = 0;
            int attempts = 0; // Used to prevent infinite loops
            while (i < passwordLength) {
                if (attempts == MAX_ATTEMPTS) { // If the generator fails to generate a password after 100 attempts, return an error message
                    return "Error: Unable to generate password with given parameters";
                }
                // Choose a random character set and a random character from that set
                int randomSetIndex = random.nextInt(characterSets.size());
                String[] randomSet = characterSets.get(randomSetIndex);
                int randomCharacterIndex = random.nextInt(randomSet.length);
                // If the password contains duplicates and the user doesn't want to include them
                // increase the attempt counter and generate a new character
                if (noDuplicate && password.contains(randomSet[randomCharacterIndex])) {
                    attempts++;
                    continue;
                }
                // Same as for duplicates, but for sequential characters
                if (noSequential && checkIfSequential(randomSet[randomCharacterIndex], randomSet)) {
                    attempts++;
                    continue;
                }
                // Add the character to the password and update the last character and set used
                password += randomSet[randomCharacterIndex];
                lastSet = randomSet;
                lastCharacter = randomSet[randomCharacterIndex];
                i++;
            }
        }
        return password;
    }

    /**
     *
     * Checks if the selected character is sequential, i.e. the previous character in the generated password
     * is the character that comes before the selected character in the character set. For example abc or 123.
     * @param currentCharacter The character to check
     * @param characterSet The character set that the current character belongs to
     * @return True if the character is sequential, false otherwise.
     */
    private boolean checkIfSequential(String currentCharacter, String[] characterSet) {
       if (lastCharacter == null || lastCharacter.equals(lastSet[0]) || currentCharacter.equals(characterSet[0]) || !Arrays.equals(lastSet, characterSet)) {
           return false;
       }
       int currentCharacterIndex = Arrays.asList(characterSet).indexOf(currentCharacter);
       return characterSet[currentCharacterIndex - 1].equals(lastCharacter);
    }
}
