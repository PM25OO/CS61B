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
    public String message;
    public String timeStamp;
    public String branch;
    public String commitID;
    public String parentID;

    Commit() {
        message = "initial commit";
        timeStamp = (new Date(0)).toString();
        parentID = "";
        branch = "master";
    }

    Commit(String message) {
        this.message = message;
        timeStamp = (new Date()).toString();
    }
}
