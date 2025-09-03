package gitlet;

/** General exception indicating a Gitlet error.  For fatal errors, the
 *  result of .getMessage() is the error message to be printed.
 * 通用异常类，表示 Gitlet 中的错误。
 * 对于致命错误，.getMessage() 的结果是要打印的错误消息。
 *
 *  @author P. N. Hilfinger
 */
class GitletException extends RuntimeException {


    /** A GitletException with no message. */
    GitletException() {
        super();
    }

    /** A GitletException MSG as its message. */
    GitletException(String msg) {
        super(msg);
    }

}
