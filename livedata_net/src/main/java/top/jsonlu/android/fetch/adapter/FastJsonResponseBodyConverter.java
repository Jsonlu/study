package top.jsonlu.android.fetch.adapter;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Converter;

/**
 * Author:JsonLu
 * DateTime:2019/3/1 09:44
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Type type;

    public FastJsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        BufferedSource bufferedSource = Okio.buffer(value.source());
        String tempStr = bufferedSource.readUtf8();
        bufferedSource.close();
        if (type == Object.class) {
            return (T) tempStr;
        }
        return JSON.parseObject(tempStr, type);

    }
}

