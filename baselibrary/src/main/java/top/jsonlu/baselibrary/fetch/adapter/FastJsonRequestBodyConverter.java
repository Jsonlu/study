package top.jsonlu.baselibrary.fetch.adapter;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Author:JsonLu
 * DateTime:2019/3/1 09:46
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class FastJsonRequestBodyConverter<F> implements Converter<F, RequestBody> {
    private final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    @NonNull
    @Override
    public RequestBody convert(F value) throws IOException {
        return RequestBody.create(MEDIA_TYPE, JSON.toJSONString(value));
    }
}