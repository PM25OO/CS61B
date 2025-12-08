package tileengine;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;
import java.awt.Font;

/**
 * Utility class for rendering tiles. You do not need to modify this file. You're welcome
 * to, but be careful. We strongly recommend getting everything else working before
 * messing with this renderer, unless you're trying to do something fancy like
 * allowing scrolling of the screen or tracking the avatar or something similar.
 */
/*
 * 用于渲染图块的工具类。您不需要修改此文件。我们欢迎您修改，但请务必小心。
 * 我们强烈建议在修改此渲染器之前，先确保其他所有功能都能正常工作，除非您正
 * 在尝试实现一些高级功能，例如允许屏幕滚动或跟踪角色（avatar）等。
 */
public class TERenderer {
    private static final int TILE_SIZE = 16;
    private int width;
    private int height;
    private int xOffset;
    private int yOffset;

    /**
     * Same functionality as the other initialization method. The only difference is that the xOff
     * and yOff parameters will change where the renderFrame method starts drawing. For example,
     * if you select w = 60, h = 30, xOff = 3, yOff = 4 and then call renderFrame with a
     * TETile[50][25] array, the renderer will leave 3 tiles blank on the left, 7 tiles blank
     * on the right, 4 tiles blank on the bottom, and 1 tile blank on the top.
     * 与另一个初始化方法的功能相同。唯一的区别是 xOff 和 yOff 参数会改变
     * renderFrame 方法开始绘制的位置。例如，如果您选择 w = 60, h = 30,
     * xOff = 3, yOff = 4，然后使用一个 TETile[50][25] 数组调用 renderFrame，
     * 渲染器将在左侧留出 3 个空白图块，右侧留出 7 个空白图块，底部留出 4 个
     * 空白图块，顶部留出 1 个空白图块。
     * @param w width of the window in tiles
     * 窗口的宽度（以图块为单位）
     * @param h height of the window in tiles.
     * 窗口的高度（以图块为单位）。
     */
    public void initialize(int w, int h, int xOff, int yOff) {
        this.width = w;
        this.height = h;
        this.xOffset = xOff;
        this.yOffset = yOff;
        StdDraw.setCanvasSize(width * TILE_SIZE, height * TILE_SIZE);
        resetFont();
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        StdDraw.clear(new Color(0, 0, 0));

        StdDraw.enableDoubleBuffering();
        StdDraw.show();
    }

    /**
     * Initializes StdDraw parameters and launches the StdDraw window. w and h are the
     * width and height of the world in number of tiles. If the TETile[][] array that you
     * pass to renderFrame is smaller than this, then extra blank space will be left
     * on the right and top edges of the frame. For example, if you select w = 60 and
     * h = 30, this method will create a 60 tile wide by 30 tile tall window. If
     * you then subsequently call renderFrame with a TETile[50][25] array, it will
     * leave 10 tiles blank on the right side and 5 tiles blank on the top side. If
     * you want to leave extra space on the left or bottom instead, use the other
     * initializatiom method.
     * 初始化 StdDraw 参数并启动 StdDraw 窗口。w 和 h 是世界在图块数量上的
     * 宽度和高度。如果您传递给 renderFrame 的 TETile[][] 数组小于此尺寸，
     * 那么在框架的右侧和顶部边缘将留下额外的空白区域。例如，如果您选择
     * w = 60 且 h = 30，此方法将创建一个 60 个图块宽、30 个图块高的窗口。
     * 如果您随后使用一个 TETile[50][25] 数组调用 renderFrame，它将在右侧
     * 留出 10 个空白图块，顶部留出 5 个空白图块。如果您想在左侧或底部留出
     * 额外的空间，请使用另一个初始化方法。
     * @param w width of the window in tiles
     * 窗口的宽度（以图块为单位）
     * @param h height of the window in tiles.
     * 窗口的高度（以图块为单位）。
     */
    public void initialize(int w, int h) {
        initialize(w, h, 0, 0);
    }

    /**
     * Takes in a 2d array of TETile objects and renders the 2d array to the screen, starting from
     * xOffset and yOffset.
     *
     * If the array is an NxM array, then the element displayed at positions would be as follows,
     * given in units of tiles.
     *
     * 接受一个二维的 TETile 对象数组，并从 xOffset 和 yOffset 处开始，将该
     * 二维数组渲染到屏幕上。
     * 如果数组是 NxM 数组，那么元素显示的位置（以图块为单位）如下所示：
     *
     *              positions   xOffset |xOffset+1|xOffset+2| .... |xOffset+world.length
     *                     
     * startY+world[0].length   [0][M-1] | [1][M-1] | [2][M-1] | .... | [N-1][M-1]
     *                    ...    ......  |  ......  |  ......  | .... | ......
     *               startY+2    [0][2]  |  [1][2]  |  [2][2]  | .... | [N-1][2]
     *               startY+1    [0][1]  |  [1][1]  |  [2][1]  | .... | [N-1][1]
     *                 startY    [0][0]  |  [1][0]  |  [2][0]  | .... | [N-1][0]
     *
     * By varying xOffset, yOffset, and the size of the screen when initialized, you can leave
     * empty space in different places to leave room for other information, such as a GUI.
     * This method assumes that the xScale and yScale have been set such that the max x
     * value is the width of the screen in tiles, and the max y value is the height of
     * the screen in tiles.
     *
     * 通过改变 xOffset、yOffset 以及初始化时屏幕的大小，您可以在不同的位置
     * 留出空白区域，以便为其他信息（例如 GUI）腾出空间。
     * 此方法假设 xScale 和 yScale 已设置，使得最大的 x 值是屏幕的图块宽度，
     * 最大的 y 值是屏幕的图块高度。
     * @param world the 2D TETile[][] array to render
     * 要渲染的二维 TETile[][] 数组
     */
    public void renderFrame(TETile[][] world) {
        StdDraw.clear(new Color(0, 0, 0));
        drawTiles(world);
        StdDraw.show();
    }

    /**
     * Draws all world tiles without clearing the canvas or showing the tiles.
     * 绘制所有世界图块，但不清除画布或显示图块。
     * @param world the 2D TETile[][] array to render
     * 要渲染的二维 TETile[][] 数组
     */
    public void drawTiles(TETile[][] world) {
        int numXTiles = world.length;
        int numYTiles = world[0].length;
        for (int x = 0; x < numXTiles; x += 1) {
            for (int y = 0; y < numYTiles; y += 1) {
                if (world[x][y] == null) {
                    throw new IllegalArgumentException("Tile at position x=" + x + ", y=" + y
                            + " is null.");
                }
                world[x][y].draw(x + xOffset, y + yOffset);
            }
        }
    }

    /**
     * Resets the font to default settings. You should call this method before drawing any tiles
     * if you changed the pen settings.
     * 将字体重置为默认设置。如果您更改了画笔设置，则在绘制任何图块之前
     * 应调用此方法。
     */
    public void resetFont() {
        Font font = new Font("Monaco", Font.BOLD, TILE_SIZE - 2);
        StdDraw.setFont(font);
    }
}