package cn.yoho.yohobase.net;

/**
 * Created by Administrator on 2015/9/11.
 */
public abstract class AbstractResponseListener<T> implements IResponseListener<T> {

    @Override
    public void onResponseSuccess(T model, boolean update){}

    @Override
    public abstract void onResponseStart();

    @Override
    public void onResponseSuccess(T model){}

    @Override
    public abstract void onResponseFailed(String reason);
}
