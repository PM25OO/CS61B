package capers;

import java.io.File;

import static capers.Utils.*;

/**
 * A repository for Capers
 *
 * @author PM25OO
 * The structure of a Capers Repository is as follows:
 * <p>
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 * - dogs/ -- folder containing all of the persistent data for dogs
 * - story -- file containing the current story
 */
public class CapersRepository {
    /**
     * Current Working Directory.
     */
    static final File CWD = new File(System.getProperty("user.dir"));

    /**
     * Main metadata folder.
     */
    static final File CAPERS_FOLDER = Utils.join(CWD, ".capers");

    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     * <p>
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     * - dogs/ -- folder containing all of the persistent data for dogs
     * - story -- file containing the current story
     */
    public static void setupPersistence() {
        CAPERS_FOLDER.mkdir();
        Dog.DOG_FOLDER.mkdir();
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     *
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        File story = new File(CAPERS_FOLDER, "story.txt");
        if (story.exists()) {
            String contents = Utils.readContentsAsString(story);
            Utils.writeContents(story, contents, text + "\n");
            System.out.println(Utils.readContentsAsString(story));
        } else {
            try {
                story.createNewFile();
            } catch (java.io.IOException e) {
                throw new RuntimeException(e);
            }
            Utils.writeContents(story, text + "\n");
            System.out.println(Utils.readContentsAsString(story));
        }
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        Dog doge = new Dog(name, breed, age);
        doge.saveDog();
        System.out.println(doge);
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     *
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        Dog doge = Dog.fromFile(name);
        doge.haveBirthday();
        doge.saveDog();
    }
}
