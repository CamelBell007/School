package cn.yoho.yohobase.net;

/**
 * http网络请求响应 on 2015/9/9.
 */
public interface IResponseListener<T> {
    /**
     * 请求开始
     */
    public void onResponseStart();

    /**
     * 请求响应成功
     * @param model
     */
    public void onResponseSuccess(T model);

    /**
     * 请求响应成功
     * @param model
     * @param update 是否刷新
     */
    public void onResponseSuccess(T model, boolean update);

    /**
     * 请求失败
     * @param reason
     */
    public void onResponseFailed(String reason);
}
