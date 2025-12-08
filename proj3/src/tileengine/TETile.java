package tileengine;

import java.awt.Color;
import java.util.Arrays;
import java.util.Random;

import edu.princeton.cs.algs4.StdDraw;
import utils.RandomUtils;

/**
 * The TETile object is used to represent a single tile in your world. A 2D array of tiles make up a
 * board, and can be drawn to the screen using the TERenderer class.
 *
 * All TETile objects must have a character, textcolor, and background color to be used to represent
 * the tile when drawn to the screen. You can also optionally provide a path to an image file of an
 * appropriate size (16x16) to be drawn in place of the unicode representation. If the image path
 * provided cannot be found, draw will fallback to using the provided character and color
 * representation, so you are free to use image tiles on your own computer.
 *
 * The provided TETile is immutable, i.e. none of its instance variables can change. You are welcome
 * to make your TETile class mutable, if you prefer.
 */
/*
 * TETile 对象用于表示世界中的单个图块。一个二维数组的图块构成了一个棋盘，
 * 并且可以使用 TERenderer 类绘制到屏幕上。
 *
 * 所有 TETile 对象必须具有一个字符、文本颜色和背景颜色，以便在绘制到屏幕时表示该图块。
 * 您还可以选择提供一个适当大小（16x16）的图像文件的路径，以代替 unicode 表示进行绘制。
 * 如果提供的图像路径找不到，draw 方法将回退使用提供的字符和颜色表示，
 * 因此您可以自由地在自己的计算机上使用图像图块。
 *
 * 提供的 TETile 是**不可变**的，即它的实例变量都不能更改。如果您愿意，
 * 欢迎将您的 TETile 类修改为**可变**的。
 */

public class TETile {
    private final char character; // Do not rename character or the autograder will break.
    // 请勿重命名 character，否则自动评分器将无法工作。
    private final Color textColor;
    private final Color backgroundColor;
    private final String description;
    private final String filepath;
    private final int id;

    /**
     * Full constructor for TETile objects.
     * 完整的 TETile 对象构造函数。
     * @param character The character displayed on the screen.
     * 屏幕上显示的字符。
     * @param textColor The color of the character itself.
     * 字符本身的颜色。
     * @param backgroundColor The color drawn behind the character.
     * 绘制在字符后面的颜色。
     * @param description The description of the tile, shown in the GUI on hovering over the tile.
     * 图块的描述，在 GUI 中鼠标悬停在图块上时显示。
     * @param filepath Full path to image to be used for this tile. Must be correct size (16x16)
     * 用于此图块的图像的完整路径。必须是正确的大小（16x16）。
     */
    public TETile(char character, Color textColor, Color backgroundColor, String description,
                  String filepath, int id) {
        this.character = character;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.description = description;
        this.filepath = filepath;
        this.id = id;
    }

    /**
     * Constructor without filepath. In this case, filepath will be null, so when drawing, we
     * will not even try to draw an image, and will instead use the provided character and colors.
     * 没有 filepath 的构造函数。在这种情况下，filepath 将为 null，因此在绘制时，
     * 我们甚至不会尝试绘制图像，而是会使用提供的字符和颜色。
     * @param character The character displayed on the screen.
     * 屏幕上显示的字符。
     * @param textColor The color of the character itself.
     * 字符本身的颜色。
     * @param backgroundColor The color drawn behind the character.
     * 绘制在字符后面的颜色。
     * @param description The description of the tile, shown in the GUI on hovering over the tile.
     * 图块的描述，在 GUI 中鼠标悬停在图块上时显示。
     */
    public TETile(char character, Color textColor, Color backgroundColor, String description, int id) {
        this.character = character;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.description = description;
        this.filepath = null;
        this.id = id;
    }

    /**
     * Creates a copy of TETile t, except with given textColor.
     * 创建 TETile t 的副本，但使用给定的 textColor。
     * @param t tile to copy
     * 要复制的图块
     * @param textColor foreground color for tile copy
     * 图块副本的前景色（字符颜色）
     */
    public TETile(TETile t, Color textColor) {
        this(t.character, textColor, t.backgroundColor, t.description, t.filepath, t.id);
    }

    /**
     * Creates a copy of TETile t, except with given character.
     * 创建 TETile t 的副本，但使用给定的 character。
     * @param t tile to copy
     * 要复制的图块
     * @param c character for tile copy
     * 图块副本的字符
     */
    public TETile(TETile t, char c) {
        this(c, t.textColor, t.backgroundColor, t.description, t.filepath, t.id);
    }


    /**
     * Draws the tile to the screen at location x, y. If a valid filepath is provided,
     * we draw the image located at that filepath to the screen. Otherwise, we fall
     * back to the character and color representation for the tile.
     *
     * Note that the image provided must be of the right size (16x16). It will not be
     * automatically resized or truncated.
     * 将图块绘制到屏幕上的 x, y 位置。如果提供了有效的 filepath，
     * 我们将绘制位于该路径的图像到屏幕上。否则，我们回退到使用
     * 图块的字符和颜色表示。
     *
     * 请注意，提供的图像必须是正确的大小（16x16）。它不会被
     * 自动调整大小或截断。
     * @param x x coordinate
     * x 坐标
     * @param y y coordinate
     * y 坐标
     */
    public void draw(double x, double y) {
        if (filepath != null) {
            try {
                StdDraw.picture(x + 0.5, y + 0.5, filepath);
                return;
            } catch (IllegalArgumentException e) {
                // Exception happens because the file can't be found. In this case, fail silently
                // and just use the character and background color for the tile.
                // 发生异常是因为找不到文件。在这种情况下，静默失败，
                // 仅使用图块的字符和背景颜色。
            }
        }

        StdDraw.setPenColor(backgroundColor);
        StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
        StdDraw.setPenColor(textColor);
        StdDraw.text(x + 0.5, y + 0.5, Character.toString(character()));
    }

    /** Character representation of the tile. Used for drawing in text mode.
     * 图块的字符表示。用于文本模式下的绘制。
     * @return character representation
     * 字符表示
     */
    public char character() {
        return character;
    }

    /**
     * Description of the tile. Useful for displaying mouseover text or
     * testing that two tiles represent the same type of thing.
     * 图块的描述。用于显示鼠标悬停文本或
     * 测试两个图块是否代表同一种事物。
     * @return description of the tile
     * 图块的描述
     */
    public String description() {
        return description;
    }

    /**
     * ID number of the tile. Used for equality comparisons.
     * 图块的 ID 号。用于相等性比较。
     * @return id of the tile
     * 图块的 ID
     */
    public int id() {
        return id;
    }

    /**
     * Creates a copy of the given tile with a slightly different text color. The new
     * color will have a red value that is within dr of the current red value,
     * and likewise with dg and db.
     * 创建给定图块的副本，但文本颜色略有不同。新的颜色
     * 的红色值将在当前红色值的 dr 范围内，
     * 绿色值在 dg 范围内，蓝色值在 db 范围内。
     * @param t the tile to copy
     * 要复制的图块
     * @param dr the maximum difference in red value
     * 红色值的最大差异
     * @param dg the maximum difference in green value
     * 绿色值的最大差异
     * @param db the maximum difference in blue value
     * 蓝色值的最大差异
     * @param r the random number generator to use
     * 要使用的随机数生成器
     */
    public static TETile colorVariant(TETile t, int dr, int dg, int db, Random r) {
        Color oldColor = t.textColor;
        int newRed = newColorValue(oldColor.getRed(), dr, r);
        int newGreen = newColorValue(oldColor.getGreen(), dg, r);
        int newBlue = newColorValue(oldColor.getBlue(), db, r);

        Color c = new Color(newRed, newGreen, newBlue);

        return new TETile(t, c);
    }

    private static int newColorValue(int v, int dv, Random r) {
        int rawNewValue = v + RandomUtils.uniform(r, -dv, dv + 1);

        // make sure value doesn't fall outside of the range 0 to 255.
        // 确保值不会超出 0 到 255 的范围。
        int newValue = Math.min(255, Math.max(0, rawNewValue));
        return newValue;
    }

    /**
     * Converts the given 2D array to a String. Handy for debugging.
     * Note that since y = 0 is actually the bottom of your world when
     * drawn using the tile rendering engine, this print method has to
     * print in what might seem like backwards order (so that the 0th
     * row gets printed last).
     * 将给定的二维数组转换为字符串。便于调试。
     * 请注意，由于使用图块渲染引擎绘制时，y = 0 实际上是您的世界的底部，
     * 因此此打印方法必须以可能看起来是倒序的方式打印
     * （以便第 0 行最后打印）。
     * @param world the 2D world to print
     * 要打印的二维世界
     * @return string representation of the world
     * 世界的字符串表示
     */
    public static String toString(TETile[][] world) {
        int width = world.length;
        int height = world[0].length;
        StringBuilder sb = new StringBuilder();

        for (int y = height - 1; y >= 0; y -= 1) {
            for (int x = 0; x < width; x += 1) {
                if (world[x][y] == null) {
                    throw new IllegalArgumentException("Tile at position x=" + x + ", y=" + y
                            + " is null.");
                }
                sb.append(world[x][y].character());
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * Makes a copy of the given 2D tile array.
     * 创建给定二维图块数组的副本。
     * @param tiles the 2D array to copy
     * 要复制的二维数组
     **/
    public static TETile[][] copyOf(TETile[][] tiles) {
        if (tiles == null) {
            return null;
        }

        TETile[][] copy = new TETile[tiles.length][];

        int i = 0;
        for (TETile[] column : tiles) {
            copy[i] = Arrays.copyOf(column, column.length);
            i += 1;
        }

        return copy;
    }

    /**
     * Checks if two tiles are equal by comparing their IDs.
     * 通过比较它们的 ID 来检查两个图块是否相等。
     * @param o object to compare with
     * 要比较的对象
     * @return boolean representing equality
     * 表示是否相等的布尔值
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        return (o instanceof TETile otherTile && otherTile.id == this.id);
    }
}