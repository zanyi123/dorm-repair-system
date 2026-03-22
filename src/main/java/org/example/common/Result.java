package org.example.common;

/**
 * 统一返回结果包装类
 * @param <T> 泛型，代表返回的具体数据类型 (如 User, List, Boolean 等)
 */
public class Result<T> {

    private boolean success; // 标志位：true=成功，false=失败
    private String message;  // 提示信息：给用户看的 (如 "密码错误")
    private T data;          // 数据载体：真正的业务数据

    // --- 构造方法 (私有，强制使用静态方法创建) ---
    private Result() {}

    // --- 静态工厂方法 (核心工具，方便一键创建) ---

    /**
     * 快捷创建：成功结果
     * @param data 要返回的数据
     * @return 一个标记为成功的 Result 对象
     */
    public static <T> Result<T> success(T data) {
        Result<T> r = new Result<>();
        r.success = true;
        r.message = "操作成功"; // 默认消息
        r.data = data;
        return r;
    }

    /**
     * 快捷创建：成功结果 (自定义消息)
     * @param data 数据
     * @param msg 自定义成功提示
     */
    public static <T> Result<T> success(T data, String msg) {
        Result<T> r = new Result<>();
        r.success = true;
        r.message = msg;
        r.data = data;
        return r;
    }

    /**
     * 快捷创建：失败结果
     * @param msg 错误提示信息
     * @return 一个标记为失败的 Result 对象 (data 默认为 null)
     */
    public static <T> Result<T> fail(String msg) {
        Result<T> r = new Result<>();
        r.success = false;
        r.message = msg;
        r.data = null; // 失败时通常没有数据
        return r;
    }

    //  Getter 和 Setter -

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
