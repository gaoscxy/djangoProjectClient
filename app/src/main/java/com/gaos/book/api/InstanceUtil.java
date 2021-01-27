package com.gaos.book.api;


/**
 * Created by gaos on 2017/8/8.
 */
public class InstanceUtil {

    /**
     * 通过实例工厂去实例化相应类
     *
     * @param <T> 返回实例的泛型类型
     * @return
     */
    public static <T> T getInstance(Class clazz) {
        try {
            return (T) create(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Object create(Class mClass) throws IllegalAccessException, InstantiationException {
        switch (mClass.getSimpleName()) {
//            case "HomePresenter": return  new HomePresenter();
            default: return mClass.newInstance();
        }
    }
}
