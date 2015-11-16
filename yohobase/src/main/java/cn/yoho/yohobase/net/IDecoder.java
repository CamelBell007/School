package cn.yoho.yohobase.net;

/**
 * Created by Administrator on 2015/9/11.
 */
public interface IDecoder<T> {
    T doDecode(int type, String jsonString);
}
