package gitlet;

import java.util.Date;

/** Represents a gitlet commit object.
 *  @author PM25OO
 */
public class Commit {
    /**
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private String message;
    private String timeStamp;

    Commit() {
        this.message = "initial commit";
        timeStamp = (new Date(0)).toString();
    }

    Commit(String message) {
        this.message = message;
        timeStamp = (new Date()).toString();
    }
}
