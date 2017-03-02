package com.lucky.utils;

import com.google.gson.*;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.sun.istack.internal.NotNull;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by lucky on 3/1/17.
 */
public class GsonUtils {
    static Gson gson;
    /**
     * 自定义TypeAdapter, null对象将被解析成空字符串
     */
    private static final TypeAdapter<String> STRING = new TypeAdapter<String>() {

        public void write(JsonWriter out, String value) {
            try {
                if (value == null) {
                    out.nullValue();
                    return;
                }
                out.value(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        public String read(JsonReader in) throws IOException {
            try {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return "";    // 原本返回Null, 这里改为返回空字符串
                }
                return in.nextString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }
    };

    /**
     * 自定义adapter，解决由于数据类型为Int，实际传过来的值为Float，导致解析出错的问题
     * 目前解决方案为将所有Int类型当成Double解析，再强制转换为Int
     */
    private static final TypeAdapter<Number> INTEGER = new TypeAdapter<Number>() {
        @Override
        public Number read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return 0;
            }
            try {
                double i = in.nextDouble();
                return (int)i;
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override
        public void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }
    };

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(String.class, STRING);    // 所有String类型null替换为字符串""
        gsonBuilder.registerTypeAdapter(int.class, INTEGER);    // int类型对float做兼容

        // 通过反射获取instanceGreators属性
        try {
            Class builder = (Class) gsonBuilder.getClass();
            Field f = builder.getDeclaredField("instanceCreators");
            f.setAccessible(true);
            Map<Type, InstanceCreator<?>> val = (Map<Type, InstanceCreator<?>>) f.get(gsonBuilder);
            // 注册数组的处理器
            gsonBuilder.registerTypeAdapterFactory(new CollectionTypeAdapterFactory(new ConstructorConstructor(val)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        gson = gsonBuilder.create();
    }

    /**
     * Json字符串 转为指定对象
     *
     * @param json json字符串
     * @param type 对象类型
     * @param <T> 对象
     * @return
     * @throws JsonSyntaxException
     */
    public <T> T toBean(@NotNull String json, Class<T> type) throws JsonSyntaxException {
        return gson.fromJson(json, type);
    }

    /**
     * 将jsonStr转换为javaBean
     *
     * @param object
     * @return json string
     */
    public String toJson(Object object) {
        return gson.toJson(object);
    }

    /**
     * 将jsonStr转换为javaBean
     *
     * @param json
     * @param type
     * @return instance of type
     */
    public <V> V fromJson(String json, Class<V> type) {
        return gson.fromJson(json, type);
    }

    /**
     * 讲jsonStr转换为javaBean
     *
     * @param json
     * @param type
     * @return instance of type
     */
    public <V> V fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }

    /**
     * 将reader转换为javaBean
     *
     * @param reader
     * @param type
     * @param instance of type
     */
    public <V> V fromJson(Reader reader, Class<V> type) {
        return gson.fromJson(reader, type);
    }

    /**
     * 将reader转换为javaBean
     *
     * @param reader
     * @param type
     * @return instance of type
     */
    public <V> V fromJson(Reader reader, Type type) {
        return gson.fromJson(reader, type);
    }

    public <V> V jsonRequest2Bean(final InputStream inputStream, Class<V> vClass) throws IOException {
        String json = IOUtils.toString(inputStream, "UTF-8");
        return toBean(json, vClass);
    }
}
