package tetris;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import tileengine.TERenderer;
import tileengine.Tileset;

import java.util.*;

/**
 *  Provides the logic for Tetris.
 *  提供俄罗斯方块的逻辑。
 *
 *  @author Erik Nelson, Omar Yu, Noah Adhikari, Jasmine Lin
 */

public class Tetris {

    private static int WIDTH = 10;
    private static int HEIGHT = 20;

    // Tetrominoes spawn above the area we display, so we'll have our Tetris board have a
    // greater height than what is displayed.
    // 方块（七种形状）在我们显示区域之上生成，因此游戏板高度比可显示高度更大。
    private static int GAME_HEIGHT = 25;

    // Contains the tiles for the board.
    // 存放游戏板格子的数组。
    private TETile[][] board;

    // Helps handle movement of pieces.
    // 帮助处理方块的移动逻辑。
    private Movement movement;

    // Checks for if the game is over.
    // 用于标记游戏是否结束。
    private boolean isGameOver;

    // The current Tetromino that can be controlled by the player.
    // 当前玩家可控制的方块（Tetromino）。
    private Tetromino currentTetromino;

    // The current game's score.
    // 当前游戏得分。
    private int score;

    /**
     * Checks for if the game is over based on the isGameOver parameter.
     * @return boolean representing whether the game is over or not
     */
    // 检查游戏是否结束（基于 isGameOver 标志）。
    // @return 布尔值，表示游戏是否结束
    private boolean isGameOver() {
        return isGameOver;
    }

    /**
     * Renders the game board and score to the screen.
     */
    // 将游戏板和分数渲染到屏幕上。
    private void renderBoard() {
        ter.drawTiles(board);
        renderScore();
        StdDraw.show();

        if (auxFilled) {
            auxToBoard();
        } else {
            fillBoard(Tileset.NOTHING);
        }
    }

    /**
     * Creates a new Tetromino and updates the instance variable
     * accordingly. Flags the game to end if the top of the board
     * is filled and the new piece cannot be spawned.
     */
    // 创建新的方块并更新实例变量。如果顶部被占据且无法生成新方块则标记游戏结束。
    private void spawnPiece() {
        // The game ends if this tile is filled
        // 如果该格被占据，游戏结束
        if (board[4][19] != Tileset.NOTHING) {
            isGameOver = true;
        }

        // Otherwise, spawn a new piece and set its position to the spawn point
        // 否则，生成新方块并将其位置重置到出生点
        currentTetromino = Tetromino.values()[bagRandom.getValue()];
        currentTetromino.reset();
    }

    /**
     * Updates the board based on the user input. Makes the appropriate moves
     * depending on the user's input.
     */
    // 根据用户输入更新游戏板。根据输入执行相应的移动。
    private void updateBoard() {
        // Grabs the current piece.
        // 获取当前方块。
        Tetromino t = currentTetromino;
        if (actionDeltaTime() > 1000) {
            movement.dropDown();
            resetActionTimer();
            Tetromino.draw(t, board, t.pos.x, t.pos.y);
            return;
        }

        if (StdDraw.hasNextKeyTyped()) {
            char key = StdDraw.nextKeyTyped();
            switch (key) {
                case 'a':
                    movement.tryMove(-1, 0);
                    break;
                case 's':
                    movement.tryMove(0, -1);
                    break;
                case 'd':
                    movement.tryMove(1, 0);
                    break;
                case 'q':
                    movement.rotateLeft();
                    break;
                case 'w':
                    movement.rotateRight();
                    break;
            }
        }

        Tetromino.draw(t, board, t.pos.x, t.pos.y);
    }

    /**
     * Increments the score based on the number of lines that are cleared.
     *
     * @param linesCleared
     */
    // 根据清除的行数增加分数。
    // @param linesCleared 清除的行数
    private void incrementScore(int linesCleared) {
        switch (linesCleared) {
            case 1 : score += 100; break;
            case 2 : score += 300; break;
            case 3 : score += 500; break;
            case 4 : score += 800; break;
        }
    }

    /**
     * Clears lines/rows on the provided tiles/board that are horizontally filled.
     * Repeats this process for cascading effects and updates score accordingly.
     * @param tiles
     */
    // 清除在提供的 tiles/board 中已被水平填满的行。为连锁消除重复此过程并相应更新得分。
    // @param tiles 要操作的格子数组
    public void clearLines(TETile[][] tiles) {
        // Keeps track of the current number lines cleared
        // 记录当前被清除的行数
        int linesCleared = 0;

        for (int j = 0; j < HEIGHT; j++) {
            int filled = 0;
            for (int i = 0; i < WIDTH; i ++) {
                if (tiles[i][j] != Tileset.NOTHING) filled += 1;
            }

            // 消除一行，并使上方方块落下
            if (filled == WIDTH) {
                linesCleared += 1;
                for (int y = j; y < HEIGHT - 1; y ++) {
                    for (int x = 0; x < WIDTH; x++) {
                        tiles[x][y] = tiles[x][y + 1];
                    }
                }
                for (int x = 0; x < WIDTH; x++) {
                    tiles[x][HEIGHT - 1] = Tileset.NOTHING;
                }
                // 重新从这一行开始检测
                j --;
            }
        }
        incrementScore(linesCleared);

        fillAux();
    }

    /**
     * Where the game logic takes place. The game should continue as long as the game isn't
     * over.
     */
    // 游戏逻辑的主循环。在游戏未结束时应持续运行。
    public void runGame() {
        resetActionTimer();

        while (!isGameOver()) {
            renderScore();
            spawnPiece();
            while (getCurrentTetromino() != null) {
                updateBoard();
                renderBoard();
            }
            clearLines(getBoard());
        }
    }

    /**
     * Renders the score using the StdDraw library.
     */
    // 使用 StdDraw 库绘制分数。
    private void renderScore() {
        // TODO: Use the StdDraw library to draw out the score.
        // TODO: 使用 StdDraw 库绘制分数。
        StdDraw.setPenColor(255, 255, 255);
        StdDraw.text(7, 19, "score: " + getScore());

    }

    /**
     * Use this method to run Tetris.
     * @param args
     */
    // 使用此方法运行 Tetris。
    // @param args 命令行参数
    public static void main(String[] args) {
        long seed = args.length > 0 ? Long.parseLong(args[0]) : (new Random()).nextLong();
        Tetris tetris = new Tetris(seed);
        tetris.runGame();
    }

    /**
     * Everything below here you don't need to touch.
     */
    // 以下内容通常不需要修改。
    // This is our tile rendering engine.
    // 这是我们的图块渲染引擎。
    private final TERenderer ter = new TERenderer();

    // Used for randomizing which pieces are spawned.
    // 用于随机化生成哪种方块。
    private Random random;
    private BagRandomizer bagRandom;

    private long prevActionTimestamp;
    private long prevFrameTimestamp;

    // The auxiliary board. At each time step, as the piece moves down, the board
    // is cleared and redrawn, so we keep an auxiliary board to track what has been
    // placed so far to help render the current game board as it updates.
    // 辅助板。在每个时间步，当方块下落时，主板会被清空并重绘，因此我们保留一个辅助板来记录已放置的方块，以在渲染更新时帮助显示当前游戏状态。
    private TETile[][] auxiliary;
    private boolean auxFilled;

    public Tetris() {
        board = new TETile[WIDTH][GAME_HEIGHT];
        auxiliary = new TETile[WIDTH][GAME_HEIGHT];
        random = new Random(new Random().nextLong());
        bagRandom = new BagRandomizer(random, Tetromino.values().length);
        auxFilled = false;
        movement = new Movement(WIDTH, GAME_HEIGHT, this);
        fillBoard(Tileset.NOTHING);
        fillAux();
    }

    public Tetris(long seed) {
        board = new TETile[WIDTH][GAME_HEIGHT];
        auxiliary = new TETile[WIDTH][GAME_HEIGHT];
        random = new Random(seed);
        bagRandom = new BagRandomizer(random, Tetromino.values().length);
        auxFilled = false;
        movement = new Movement(WIDTH, GAME_HEIGHT, this);

        ter.initialize(WIDTH, HEIGHT);
        fillBoard(Tileset.NOTHING);
        fillAux();
    }

    // Setter and getter methods.
    // setter 和 getter 方法。
    /**
     * Returns the current game board.
     * @return
     */
    // 返回当前游戏板。
    // @return 当前板数组
    public TETile[][] getBoard() {
        return board;
    }

    /**
     * Returns the score.
     */
    // 返回分数。
    public int getScore() {
        return score;
    }

    /**
     * Returns the current auxiliary board.
     * @return
     */
    // 返回当前辅助板。
    // @return 辅助板数组
    public TETile[][] getAuxiliary() {
        return auxiliary;
    }


    /**
     * Returns the current Tetromino/piece.
     * @return
     */
    // 返回当前的方块（Tetromino）。
    // @return 当前方块
    public Tetromino getCurrentTetromino() {
        return currentTetromino;
    }

    /**
     * Sets the current Tetromino to null.
     * @return
     */
    // 将 currentTetromino 设为 null。
    // @return 无
    public void setCurrentTetromino() {
        currentTetromino = null;
    }

    /**
     * Sets the boolean auxFilled to true;
     */
    // 将 auxFilled 标记设为 true。
    public void setAuxTrue() {
        auxFilled = true;
    }

    /**
     * Fills the entire board with the specific tile that is passed in.
     * @param tile
     */
    // 用传入的 tile 填充整个游戏板。
    // @param tile 要填充的格子
    private void fillBoard(TETile tile) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = tile;
            }
        }
    }

    /**
     * Copies the contents of the src array into the dest array using
     * System.arraycopy.
     * @param src
     * @param dest
     */
    // 使用 System.arraycopy 将 src 数组的内容复制到 dest。
    // @param src 源数组
    // @param dest 目标数组
    private static void copyArray(TETile[][] src, TETile[][] dest) {
        for (int i = 0; i < src.length; i++) {
            System.arraycopy(src[i], 0, dest[i], 0, src[0].length);
        }
    }

    /**
     * Copies over the tiles from the game board to the auxiliary board.
     */
    // 将游戏板的格子内容复制到辅助板。
    public void fillAux() {
        copyArray(board, auxiliary);
    }

    /**
     * Copies over the tiles from the auxiliary board to the game board.
     */
    // 将辅助板的格子内容复制回游戏板。
    private void auxToBoard() {
        copyArray(auxiliary, board);
    }

    /**
     * Calculates the delta time with the previous action.
     * @return the amount of time between the previous Tetromino movement with the present
     */
    // 计算与上一次操作的时间差。
    // @return 自上次方块移动到现在的时间毫秒数
    private long actionDeltaTime() {
        return System.currentTimeMillis() - prevActionTimestamp;
    }

    /**
     * Resets the action timestamp to the current time in milliseconds.
     */
    // 将操作时间戳重置为当前毫秒数。
    private void resetActionTimer() {
        prevActionTimestamp = System.currentTimeMillis();
    }

}
