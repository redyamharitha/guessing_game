import java.util.*;

public class Hangman {
    static String[] dictionary = {
        "apple", "banana", "orange", "grape", "peach", "mango", "school", "teacher", "planet", "galaxy",
        "system", "network", "future", "science", "energy", "forest", "nature", "animal", "human", "friend",
        "travel", "family", "happiness", "holiday", "mountain", "valley", "river", "desert", "window", "mirror",
        "bottle", "pencil", "laptop", "keyboard", "monitor", "camera", "movie", "engineer", "doctor", "nurse",
        "student", "college", "university", "biology", "physics", "chemistry", "history", "language", "english",
        "symbol", "function", "variable", "class", "object", "method", "public", "private", "static", "final",
        "coffee", "butter", "cheese", "garden", "flower", "station", "engine", "driver", "signal", "mobile",
        "rocket", "planet", "mars", "venus", "jupiter", "saturn", "zebra", "tiger", "elephant", "rabbit", "mouse",
        "singer", "guitar", "violin", "drummer", "artist", "painter", "dancer", "writer", "reader", "poet"
    };

    static String wordToGuess;
    static char[] guessedWord;
    static int triesLeft;
    static Set<Character> guessedLetters = new HashSet<>();
    static Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        wordToGuess = dictionary[random.nextInt(dictionary.length)].toLowerCase();
        guessedWord = new char[wordToGuess.length()];
        Arrays.fill(guessedWord, '_');

        triesLeft = wordToGuess.length() + 1;

        System.out.println("🎮 Welcome to Hangman!");
        System.out.println("A random word has been chosen.");
        System.out.println("You have " + triesLeft + " wrong attempts allowed.\n");

        while (triesLeft > 0 && !isWordGuessed()) {
            displayGuessedWord();
            System.out.print("Guess a letter: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Invalid input. Please enter a single letter.\n");
                continue;
            }

            char guessedChar = input.charAt(0);

            if (guessedLetters.contains(guessedChar)) {
                System.out.println("You've already guessed that letter.\n");
                continue;
            }

            guessedLetters.add(guessedChar);

            if (wordToGuess.indexOf(guessedChar) >= 0) {
                System.out.println("✅ Correct guess!\n");
                updateGuessedWord(guessedChar);
            } else {
                triesLeft--;
                System.out.println("❌ Wrong guess! Tries left: " + triesLeft);
                System.out.println("💡 Clue: The word has " + wordToGuess.length() + " letters and starts with '" + wordToGuess.charAt(0) + "'.\n");
            }
        }

        if (isWordGuessed()) {
            System.out.println("🎉 Congratulations! You guessed the word: " + wordToGuess);
        } else {
            System.out.println("💀 Game Over! The correct word was: " + wordToGuess);
        }

        scanner.close();
    }

    static void displayGuessedWord() {
        for (char c : guessedWord) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    static void updateGuessedWord(char guessedChar) {
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guessedChar) {
                guessedWord[i] = guessedChar;
            }
        }
    }

    static boolean isWordGuessed() {
        return wordToGuess.equals(new String(guessedWord));
    }
}
