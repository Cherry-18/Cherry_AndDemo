package com.cherry.cherry_anddemo.ui.interview.storage.sharepreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * commit()和apply()的区别:
 * apply没有返回值而commit返回boolean表明修改是否提交成功
 * apply是将修改数据原子提交到内存, 而后异步真正提交到硬件磁盘, 而commit是同步的提交到硬件磁盘，因此，在多个并发的提交commit的时候，他们会等待正在处理的commit保存到磁盘后在操作，从而降低了效率。而apply只是原子的提交到内容，后面有调用apply的函数的将会直接覆盖前面的内存数据，这样从一定程度上提高了很多效率。
 * apply方法不会提示任何失败的提示。由于在一个进程中，sharedPreference是单实例，一般不会出现并发冲突，如果对提交的结果不关心的话，建议使用apply，当然需要确保提交成功且有后续操作的话，还是需要用commit的。
 */
public class SpUtils {
    /**
     * 默认保存配置的文件名
     */
    public static final String FILE_NAME = "share_data";

    public SpUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }


//    public static void putString(String filename,Context context,String key,String value){
//        SharedPreferences sp = getSharedPreferencesName(filename,context);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putString(key,value );
//        editor.apply();
//    }
//
//    public static String getString(String filename,Context context,String key){
//        SharedPreferences sp = getSharedPreferencesName(filename,context);
//        return sp.getString(key,"");
//    }
//

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param filename 一般为id，区分不同的配置文件
     */
    public static void put(String filename,Context context,String key,Object object){
        SharedPreferences sp = getSharedPreferencesName(filename,context);
        SharedPreferences.Editor editor = sp.edit();

        if (object instanceof String){
            editor.putString(key, Base64.encodeToString(((String) object).getBytes(), 0));
        }else if (object instanceof Integer){
            editor.putInt(key,(Integer)object);
        }else if (object instanceof Boolean){
            editor.putBoolean(key, (Boolean)object);
        }else if (object instanceof Float){
            editor.putFloat(key, (Float) object);
        }else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }

        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     */
    public static Object get(String filename,Context context,String key, Object defaultObject){
        SharedPreferences sp = getSharedPreferencesName(filename,context);
        if (defaultObject instanceof String){
            String tmp = sp.getString(key,"");
            if (!TextUtils.isEmpty(tmp)){
                return new String(Base64.decode(tmp,0));
            }else {
                return defaultObject;
            }
        }else if (defaultObject instanceof Integer){
            return  sp.getInt(key,(Integer)defaultObject);
        }else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }
        return null;
    }

    /**
     * 移除某个key值已经对应的值
     */
    public static void remove(String filename, Context context, String key){
        SharedPreferences sp = getSharedPreferencesName(filename,context);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     */
    public static void clear(String filename, Context context){
        SharedPreferences sp = getSharedPreferencesName(filename,context);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     */
    public static boolean contains(String filename, Context context, String key) {
        SharedPreferences sp = getSharedPreferencesName(filename,context);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     */
    public static Map<String, ?> getAll(String filename, Context context) {
        SharedPreferences sp = getSharedPreferencesName(filename, context);
        return sp.getAll();
    }


    private static SharedPreferences getSharedPreferencesName(String filename, Context context) {
        if (TextUtils.isEmpty(filename)) {
            filename = FILE_NAME;
        }
        return context.getSharedPreferences(filename,
                Context.MODE_PRIVATE);
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }

}
