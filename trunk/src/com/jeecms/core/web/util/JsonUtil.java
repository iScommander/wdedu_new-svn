package com.jeecms.core.web.util;

import com.json.JsonArrayList;
import com.json.JsonObjectList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

//import com.json.JsonObjectList;


/**
 * @author
 * @link
 * @version
 */
public class JsonUtil {


    /**
     * @param
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String toString(Object obj) {
        if (obj instanceof List) {
            return filterData(new JsonArrayList((List) obj).toString());
        } else if (obj instanceof Map) {
            return filterData(new JsonObjectList((Map) obj).toString());
        } else {
            return filterData(new JsonObjectList(obj).toString());
        }
    }

    private static String filterData(String str) {
        return str;
    }

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(JSONObject jo, String key, T t) {
        try {
            if (t instanceof Long) {
                return (T) (Object) jo.getLong(key);
            }
            if (t instanceof Double) {
                return (T) (Object) jo.getDouble(key);
            }
            if (t instanceof Integer) {
                return (T) (Object) jo.getInt(key);
            }
            if (t instanceof Boolean) {
                return (T) (Object) jo.getBoolean(key);
            }
            if (t instanceof String) {
                return (T) jo.getString(key);
            }
            return (T) jo.get(key);
        } catch (JSONException e) {
            return t;
        }
    }

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(JSONArray ja, int index, T t) {
        try {
            if (t instanceof Long) {
                return (T) (Object) ja.getLong(index);
            }
            if (t instanceof Double) {
                return (T) (Object) ja.getDouble(index);
            }
            if (t instanceof Integer) {
                return (T) (Object) ja.getInt(index);
            }
            if (t instanceof Boolean) {
                return (T) (Object) ja.getBoolean(index);
            }
            if (t instanceof String) {
                return (T) ja.getString(index);
            }
            return (T) ja.get(index);
        } catch (JSONException e) {
            return t;
        }
    }

    /**
     * @return
     */
    public static JSONObject getJSONObject(JSONArray ja, int index) {
        try {
            return ja.getJSONObject(index);
        } catch (JSONException e) {
            return null;
        }
    }

    /**
     * @return
     */
    public static JSONObject getJSONObject(JSONObject jo, String key) {
        try {
            return jo.getJSONObject(key);
        } catch (JSONException e) {
            return null;
        }
    }

    /**
     * @return
     */
    public static JSONArray getJSONArray(JSONArray ja, int index) {
        try {
            return ja.getJSONArray(index);
        } catch (JSONException e) {
            return null;
        }
    }

    /**
     * @return
     */
    public static JSONArray getJSONArray(JSONObject jo, String key) {
        try {
            return jo.getJSONArray(key);
        } catch (JSONException e) {
            return null;
        }
    }


}
