package capers;

import java.io.File;
import java.util.Arrays;

import static capers.Utils.*;

/** Canine Capers: A Gitlet Prelude.
 * // 犬类恶作剧：Gitlet 序曲。
 * @author TODO
*/
public class Main {
    /**
     * Runs one of three commands:
     * // 运行以下三个命令之一：
     * story [text] -- Appends "text" + a newline to a story file in the
     * // story [text] -- 将 "text" + 换行符追加到
     *                 .capers directory. Additionally, prints out the
     *                 // .capers 目录中的故事文件。另外，打印出
     *                 current story.
     *                 // 当前故事。
     *
     * dog [name] [breed] [age] -- Persistently creates a dog with
     * // dog [name] [breed] [age] -- 持久化地创建一只狗，具有
     *                             the specified parameters; should also print
     *                             // 指定的参数；同时应该打印
     *                             the dog's toString(). Assume dog names are
     *                             // 狗的 toString()。假设狗的名字是
     *                             unique.
     *                             // 唯一的。
     *
     * birthday [name] -- Advances a dog's age persistently
     * // birthday [name] -- 持久化地增加狗的年龄
     *                    and prints out a celebratory message.
     *                    // 并打印出庆祝消息。
     *
     * All persistent data should be stored in a ".capers"
     * // 所有持久化数据应该存储在当前工作目录下的
     * directory in the current working directory.
     * // ".capers" 目录中。
     *
     * Recommended structure (you do not have to follow):
     * // 推荐的结构（你不必遵循）：
     *
     * *YOU SHOULD NOT CREATE THESE MANUALLY,
     * // *你不应该手动创建这些，
     *  YOUR PROGRAM SHOULD CREATE THESE FOLDERS/FILES*
     *  // 你的程序应该创建这些文件夹/文件*
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     * // .capers/ -- lab12 文件夹中所有持久化数据的顶级文件夹
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    // - dogs/ -- 包含所有狗的持久化数据的文件夹
     *    - story -- file containing the current story
     *    // - story -- 包含当前故事的文件
     *
     * @param args arguments from the command line
     * // @param args 来自命令行的参数
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            Utils.exitWithError("Must have at least one argument");
        }
        System.out.println("Args: " + Arrays.toString(args));

        CapersRepository.setupPersistence();
        String text;
        switch (args[0]) {
        case "story":
            /* This call has been handled for you. The rest will be similar. */
            // 这个调用已经为你处理了。其余的将类似。
            validateNumArgs("story", args, 2);
            text = args[1];
            CapersRepository.writeStory(text);
            break;
        case "dog":
            validateNumArgs("dog", args, 4);
            // TODO: make a dog
            // TODO: 创建一只狗
            break;
        case "birthday":
            validateNumArgs("birthday", args, 2);
            // TODO: celebrate this dog's birthday
            // TODO: 庆祝这只狗的生日
            break;
        default:
            exitWithError(String.format("Unknown command: %s", args[0]));
        }
        return;
    }

    /**
     * Checks the number of arguments versus the expected number,
     * // 检查参数数量与期望数量是否匹配，
     * throws a RuntimeException if they do not match.
     * // 如果不匹配则抛出 RuntimeException。
     *
     * @param cmd Name of command you are validating
     * // @param cmd 你正在验证的命令名称
     * @param args Argument array from command line
     * // @param args 来自命令行的参数数组
     * @param n Number of expected arguments
     * // @param n 期望的参数数量
     */
    public static void validateNumArgs(String cmd, String[] args, int n) {
        if (args.length != n) {
            throw new RuntimeException(
                String.format("Invalid number of arguments for: %s.", cmd));
        }
    }
}
