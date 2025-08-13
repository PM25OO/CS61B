package capers;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;


/** Assorted utilities.
 *  // 各种实用工具。
 *  @author P. N. Hilfinger
 */
class Utils {

    /* READING AND WRITING FILE CONTENTS */
    /* 读取和写入文件内容 */

    /** Return the entire contents of FILE as a byte array.  FILE must
     *  // 将 FILE 的全部内容作为字节数组返回。FILE 必须
     *  be a normal file.  Throws IllegalArgumentException
     *  // 是一个普通文件。如果出现问题则抛出 IllegalArgumentException
     *  in case of problems.
     *  // 异常。*/
    static byte[] readContents(File file) {
        if (!file.isFile()) {
            throw new IllegalArgumentException("must be a normal file");
        }
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException excp) {
            throw new IllegalArgumentException(excp.getMessage());
        }
    }

    /** Return the entire contents of FILE as a String.  FILE must
     *  // 将 FILE 的全部内容作为字符串返回。FILE 必须
     *  be a normal file.  Throws IllegalArgumentException
     *  // 是一个普通文件。如果出现问题则抛出 IllegalArgumentException
     *  in case of problems.
     *  // 异常。*/
    static String readContentsAsString(File file) {
        return new String(readContents(file), StandardCharsets.UTF_8);
    }

    /** Write the result of concatenating the bytes in CONTENTS to FILE,
     *  // 将 CONTENTS 中字节连接的结果写入 FILE，
     *  creating or overwriting it as needed.  Each object in CONTENTS may be
     *  // 根据需要创建或覆盖文件。CONTENTS 中的每个对象可以是
     *  either a String or a byte array.  Throws IllegalArgumentException
     *  // 字符串或字节数组。如果出现问题则抛出 IllegalArgumentException
     *  in case of problems.
     *  // 异常。*/
    static void writeContents(File file, Object... contents) {
        try {
            if (file.isDirectory()) {
                throw
                        new IllegalArgumentException("cannot overwrite directory");
            }
            BufferedOutputStream str =
                    new BufferedOutputStream(Files.newOutputStream(file.toPath()));
            for (Object obj : contents) {
                if (obj instanceof byte[]) {
                    str.write((byte[]) obj);
                } else {
                    str.write(((String) obj).getBytes(StandardCharsets.UTF_8));
                }
            }
            str.close();
        } catch (IOException | ClassCastException excp) {
            throw new IllegalArgumentException(excp.getMessage());
        }
    }

    /** Return an object of type T read from FILE, casting it to EXPECTEDCLASS.
     *  // 从 FILE 中读取类型为 T 的对象，将其转换为 EXPECTEDCLASS。
     *  Throws IllegalArgumentException in case of problems.
     *  // 如果出现问题则抛出 IllegalArgumentException 异常。*/
    static <T extends Serializable> T readObject(File file,
                                                 Class<T> expectedClass) {
        try {
            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(file));
            T result = expectedClass.cast(in.readObject());
            in.close();
            return result;
        } catch (IOException | ClassCastException
                | ClassNotFoundException excp) {
            throw new IllegalArgumentException(excp.getMessage());
        }
    }

    /** Write OBJ to FILE. */
    // 将 OBJ 写入 FILE。
    static void writeObject(File file, Serializable obj) {
        writeContents(file, serialize(obj));
    }


    /* OTHER FILE UTILITIES */
    /* 其他文件实用工具 */

    /** Return the concatentation of FIRST and OTHERS into a File designator,
     *  // 将 FIRST 和 OTHERS 连接为一个文件指示符返回，
     *  analogous to the {@link java.nio.file.Paths.#get(String, String[])}
     *  // 类似于 {@link java.nio.file.Paths.#get(String, String[])}
     *  method.
     *  // 方法。*/
    static File join(String first, String... others) {
        return Paths.get(first, others).toFile();
    }

    /** Return the concatentation of FIRST and OTHERS into a File designator,
     *  // 将 FIRST 和 OTHERS 连接为一个文件指示符返回，
     *  analogous to the {@link java.nio.file.Paths.#get(String, String[])}
     *  // 类似于 {@link java.nio.file.Paths.#get(String, String[])}
     *  method.
     *  // 方法。*/
    static File join(File first, String... others) {
        return Paths.get(first.getPath(), others).toFile();
    }


    /* SERIALIZATION UTILITIES */
    /* 序��化实用工具 */

    /** Returns a byte array containing the serialized contents of OBJ. */
    // 返回包含 OBJ 序列化内容的字节数组。
    static byte[] serialize(Serializable obj) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(stream);
            objectStream.writeObject(obj);
            objectStream.close();
            return stream.toByteArray();
        } catch (IOException excp) {
            throw error("Internal error serializing commit.");
        }
    }



    /* MESSAGES AND ERROR REPORTING */
    /* 消息和错误报告 */

    /**
     * Prints out MESSAGE and exits with error code -1.
     * // 打印出 MESSAGE 并以错误代码 -1 退出。
     * Note:
     * // 注意：
     *     The functionality for erroring/exit codes is different within Gitlet
     *     // Gitlet 中的错误/退出代码功能有所不同
     *     so DO NOT use this as a reference.
     *     // 所以不要将此作为参考。
     *     Refer to the spec for more information.
     *     // 请参考规范获取更多信息。
     * @param message message to print
     * // @param message 要打印的消息
     */
    public static void exitWithError(String message) {
        if (message != null && !message.equals("")) {
            System.out.println(message);
        }
        System.exit(-1);
    }

    /** Return a RuntimeException whose message is composed from MSG and ARGS as
     *  // 返回一个 RuntimeException，其消息由 MSG 和 ARGS 组成，
     *  for the String.format method.
     *  // 用于 String.format 方法。*/
    static RuntimeException error(String msg, Object... args) {
        return new RuntimeException(String.format(msg, args));
    }

}
