package capers;

import java.io.File;
import static capers.Utils.*;

/** A repository for Capers 
 * // Capers 的仓库
 * @author TODO
 * The structure of a Capers Repository is as follows:
 * // Capers 仓库的结构如下：
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 * // .capers/ -- lab12 文件夹中所有持久化数据的顶级文件夹
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    // - dogs/ -- 包含所有狗的持久化数据的文件夹
 *    - story -- file containing the current story
 *    // - story -- 包含当前故事的文件
 *
 * TODO: change the above structure if you do something different.
 * // TODO: 如果你做了不同的处理，请修改上述结构。
 */
public class CapersRepository {
    /** Current Working Directory. */
    // 当前工作目录。
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder. */
    // 主要元数据文件夹。
    static final File CAPERS_FOLDER = null; // TODO Hint: look at the `join`
                                            //      function in Utils
                                            // TODO 提示：查看 Utils 中的 `join` 函数

    /**
     * Does required filesystem operations to allow for persistence.
     * // 执行必要的文件系统操作以支持持久化。
     * (creates any necessary folders or files)
     * // （创建任何必要的文件夹或文件）
     * Remember: recommended structure (you do not have to follow):
     * // 记住：推荐的结构（你不必遵循）：
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     * // .capers/ -- lab12 文件夹中所有持久化数据的顶级文件夹
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    // - dogs/ -- 包含所有狗的持久化数据的文件夹
     *    - story -- file containing the current story
     *    // - story -- 包含当前故事的文件
     */
    public static void setupPersistence() {
        // TODO
    }

    /**
     * Appends the first non-command argument in args
     * // 将 args 中第一个非命令参数
     * to a file called `story` in the .capers directory.
     * // 追加到 .capers 目录中名为 `story` 的文件。
     * @param text String of the text to be appended to the story
     * // @param text 要追加到故事中的文本字符串
     */
    public static void writeStory(String text) {
        // TODO
    }

    /**
     * Creates and persistently saves a dog using the first
     * // 使用前
     * three non-command arguments of args (name, breed, age).
     * // 三个非命令参��� (name, breed, age) 创建并持久化保存一只狗。
     * Also prints out the dog's information using toString().
     * // 同时使用 toString() 打印出狗的信息。
     */
    public static void makeDog(String name, String breed, int age) {
        // TODO
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * // 持久化地增加狗的年龄并打印庆祝消息。
     * Also prints out the dog's information using toString().
     * // 同时使用 toString() 打印出狗的信息。
     * Chooses dog to advance based on the first non-command argument of args.
     * // 根据 args 的第一个非命令参数选择要增加年龄的狗。
     * @param name String name of the Dog whose birthday we're celebrating.
     * // @param name 我们要庆祝生日的狗的名字字符串。
     */
    public static void celebrateBirthday(String name) {
        // TODO
    }
}
