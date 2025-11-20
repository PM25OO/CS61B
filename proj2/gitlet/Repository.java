package gitlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static gitlet.Utils.*;

// TODO: any imports you need here

/**
 * 表示一个 Gitlet 仓库。
 * 此类负责管理仓库的核心功能，包括文件存储、版本控制和目录管理。
 * TODO: 请在此处提供更详细的类描述。
 *
 * @author PM25OO
 */
public class Repository {
    /**
     * TODO: 在此处添加实例变量。
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     * 在此列出Repository类的全部实例变量，并在每个变量上方添加说明性注释，
     * 描述该变量的含义及其使用方式。我们已为您提供了两个示例。
     */

    /**
     * The current working directory.
     */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /**
     * The .gitlet directory.
     */
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    public static final File STAGING_DIR = join(GITLET_DIR, "stage");

    public Commit CURRENT_BRANCH;
    public Commit HEAD;

    public static void init() {
        if (GITLET_DIR.exists())
            message("A Gitlet version-control system already exists in the current directory.");
        else {
            GITLET_DIR.mkdir();
            Commit commit = new Commit();
        }
    }

    public static void add(String fileName) {
        File sourceFile = join(CWD, fileName);
        if (!sourceFile.exists()) message("File does not exist.");
        else {
            File targetFile = join(STAGING_DIR, fileName);
            if (!targetFile.exists() || !sha1(sourceFile).equals(sha1(targetFile))) {
                try {
                    Files.copy(sourceFile.toPath(), join(STAGING_DIR, fileName).toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.err.println("文件复制失败: " + e.getMessage());
                }
            }
        }
    }

    /* TODO: 填充此类的其余部分。 */
}
