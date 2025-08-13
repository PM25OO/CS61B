package capers;

import java.io.File;
import java.io.Serializable;
import static capers.Utils.*;

/** Represents a dog that can be serialized.
 * // 表示一个��以被序列化的狗。
 * @author TODO
*/
public class Dog { // TODO

    /** Folder that dogs live in. */
    // 狗居住的文件夹。
    static final File DOG_FOLDER = null; // TODO (hint: look at the `join`
                                         //      function in Utils)
                                         // TODO（提示：查看 Utils 中的 `join` 函数）

    /** Age of dog. */
    // 狗的年龄。
    private int age;
    /** Breed of dog. */
    // 狗的品种。
    private String breed;
    /** Name of dog. */
    // 狗的名字。
    private String name;

    /**
     * Creates a dog object with the specified parameters.
     * // 用指定的参数创建一个狗对象。
     * @param name Name of dog
     * // @param name 狗的名字
     * @param breed Breed of dog
     * // @param breed 狗的品种
     * @param age Age of dog
     * // @param age 狗的年龄
     */
    public Dog(String name, String breed, int age) {
        this.age = age;
        this.breed = breed;
        this.name = name;
    }

    /**
     * Reads in and deserializes a dog from a file with name NAME in DOG_FOLDER.
     * // 从 DOG_FOLDER 中名为 NAME 的文件中读取并反序列化一只狗。
     *
     * @param name Name of dog to load
     * // @param name 要加载的狗的名字
     * @return Dog read from file
     * // @return 从文件中读取的狗
     */
    public static Dog fromFile(String name) {
        // TODO (hint: look at the Utils file)
        // TODO（提示：查看 Utils 文件）
        return null;
    }

    /**
     * Increases a dog's age and celebrates!
     * // 增加狗的年龄并庆祝！
     */
    public void haveBirthday() {
        age += 1;
        System.out.println(toString());
        System.out.println("Happy birthday! Woof! Woof!");
    }

    /**
     * Saves a dog to a file for future use.
     * // 将狗保存到文件中以备将来使用。
     */
    public void saveDog() {
        // TODO (hint: don't forget dog names are unique)
        // TODO（提示：不要忘记狗的名字是唯一的）
    }

    @Override
    public String toString() {
        return String.format(
            "Woof! My name is %s and I am a %s! I am %d years old! Woof!",
            name, breed, age);
    }

}
