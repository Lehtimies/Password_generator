import java.util.ArrayList;
import java.util.Random;

public class PasswordGenerator {
    // For testing purposes!
    /*
    public static void main(String[] args) {
        PasswordGenerator generator = new PasswordGenerator(16, true, true, true, true, true, true);
        int amount = 10;
        for (int i = 0; i < amount; i++) {
            String password = generator.generatePassword();
            System.out.println(password);
        }
    }*/

    private final int passwordLength;
    private final boolean includeNumbers;
    private final boolean includeLowercase;
    private final boolean includeUppercase;
    private final boolean includeSpecial;
    private final boolean noDuplicate;
    private final boolean noSequential;

    private static final String[] NUMBERS = {"0","1","2","3","4","5","6","7","8","9"};
    private static final String[] LOWERCASE = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    private static final String[] UPPERCASE = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private static final String[] SPECIAL = {"!","@","#","$","%","^","&","*","(",")","-","_","=","+","[","]","{","}",";",":","'","\\","|",",","<",".",">","/","?"};

    public PasswordGenerator(int passwordLength, boolean includeNumbers, boolean includeLowercase, boolean includeUppercase, boolean includeSpecial, boolean noDuplicate, boolean noSequential) {
        this.passwordLength = passwordLength;
        this.includeNumbers = includeNumbers;
        this.includeLowercase = includeLowercase;
        this.includeUppercase = includeUppercase;
        this.includeSpecial = includeSpecial;
        this.noDuplicate = noDuplicate;
        this.noSequential = noSequential;
    }

    public String generatePassword() {
        String password = "";
        Random random = new Random();
        ArrayList<String[]> characterSets = new ArrayList<>();
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
        if (!characterSets.isEmpty()) {
            int i = 0;
            int attempts = 0;
            while (i < passwordLength) {
                int randomSetIndex = random.nextInt(characterSets.size());
                String[] randomSet = characterSets.get(randomSetIndex);
                int randomCharacterIndex = random.nextInt(randomSet.length);
                if (attempts == 50) {
                    return "Error: Unable to generate password with given parameters";
                }
                if (noDuplicate && password.contains(randomSet[randomCharacterIndex])) {
                    attempts++;
                    continue;
                }
                password += randomSet[randomCharacterIndex];
                i++;
            }
        }
        return password;
    }
}
