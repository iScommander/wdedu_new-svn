package com.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * 
 * not attributable
 * 
 * @version 1.0
 */

public class MyBeanUtils extends org.apache.commons.beanutils.BeanUtils {

    private static void convert(Object dest, Object orig)
            throws IllegalAccessException, InvocationTargetException {

        if (dest == null) {
            throw new IllegalArgumentException("No destination bean specified");
        }
        if (orig == null) {
            throw new IllegalArgumentException("No origin bean specified");
        }

        if (orig instanceof DynaBean) {
            DynaProperty[] origDescriptors = ((DynaBean) orig).getDynaClass().getDynaProperties();
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                if (PropertyUtils.isWriteable(dest, name)) {
                    Object value = ((DynaBean) orig).get(name);
                    try {
                        copyProperty(dest, name, value);
                    } catch (Exception e) {
                        ;
                    }

                }
            }
        } else if (orig instanceof Map) {
            Iterator names = ((Map) orig).keySet().iterator();
            while (names.hasNext()) {
                String name = (String) names.next();
                if (PropertyUtils.isWriteable(dest, name)) {
                    Object value = ((Map) orig).get(name);
                    try {
                        copyProperty(dest, name, value);
                    } catch (Exception e) {
                        ;
                    }

                }
            }
        } else {
            PropertyDescriptor[] origDescriptors = PropertyUtils.getPropertyDescriptors(orig);
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                if ("class".equals(name)) {
                    continue;
                }
                if (PropertyUtils.isReadable(orig, name) && PropertyUtils.isWriteable(dest, name)) {
                    try {
                        Object value = PropertyUtils.getSimpleProperty(orig, name);
                        copyProperty(dest, name, value);
                    } catch (IllegalArgumentException ie) {
                        ;
                    } catch (Exception e) {
                        ;
                    }

                }
            }
        }

    }


    /**
     * 对象拷贝 数据对象空值不拷贝到目标对象
     * 
     * @param databean
     * @param tobean
     * @throws NoSuchMethodException copy
     */
    public static void copyBeanNotNull2Bean(Object databean, Object tobean) throws Exception {
        PropertyDescriptor[] origDescriptors = PropertyUtils.getPropertyDescriptors(databean);
        for (int i = 0; i < origDescriptors.length; i++) {
            String name = origDescriptors[i].getName();
            if ("class".equals(name)) {
                continue;
            }
            if (PropertyUtils.isReadable(databean, name)
                    && PropertyUtils.isWriteable(tobean, name)) {
                try {
                    Object value = PropertyUtils.getSimpleProperty(databean, name);
                    if (value != null) {
                        copyProperty(tobean, name, value);
                    }
                } catch (IllegalArgumentException ie) {
                    ;
                } catch (Exception e) {
                    ;
                }

            }
        }
    }


    /**
     * 对象拷贝 数据对象空值以及ID不拷贝到目标对象
     * 
     * @param databean
     * @param tobean
     * @throws NoSuchMethodException copy
     */
    public static void copyBeanWithoutId2Bean(Object databean, Object tobean) throws Exception {
        PropertyDescriptor[] origDescriptors = PropertyUtils.getPropertyDescriptors(databean);
        for (int i = 0; i < origDescriptors.length; i++) {
            String name = origDescriptors[i].getName();
            if ("class".equals(name)) {
                continue;
            }
            if (PropertyUtils.isReadable(databean, name) && PropertyUtils.isWriteable(tobean, name)
                    && !"id".equals(name)) {
                try {
                    Object value = PropertyUtils.getSimpleProperty(databean, name);
                    if (value != null) {
                        copyProperty(tobean, name, value);
                    }
                } catch (IllegalArgumentException ie) {
                    ;
                } catch (Exception e) {
                    ;
                }

            }
        }
    }


    /**
     * 把orig和dest相同属性的value复制到dest中
     * 
     * @param dest
     * @param orig
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void copyBean2Bean(Object dest, Object orig) throws Exception {
        convert(dest, orig);
    }

    public static void copyBean2Map(Map map, Object bean) {
        PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(bean);
        for (int i = 0; i < pds.length; i++) {
            PropertyDescriptor pd = pds[i];
            String propname = pd.getName();
            try {
                Object propvalue = PropertyUtils.getSimpleProperty(bean, propname);
                map.put(propname, propvalue);
            } catch (IllegalAccessException e) {

            } catch (InvocationTargetException e) {

            } catch (NoSuchMethodException e) {

            }
        }
    }

    /**
     * 将Map内的key与Bean中属性相同的内容复制到BEAN中
     * 
     * @param bean Object
     * @param properties Map
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void copyMap2Bean(Object bean, Map properties)
            throws IllegalAccessException, InvocationTargetException {
        if ((bean == null) || (properties == null)) {
            return;
        }
        Iterator names = properties.keySet().iterator();
        while (names.hasNext()) {
            String name = (String) names.next();
            if (name == null) {
                continue;
            }
            Object value = properties.get(name);
            try {
                Class clazz = PropertyUtils.getPropertyType(bean, name);
                if (null == clazz) {
                    continue;
                }
                String className = clazz.getName();
                if ("java.sql.Timestamp".equalsIgnoreCase(className)) {
                    if (value == null || "".equals(value)) {
                        continue;
                    }
                }
                setProperty(bean, name, value);
            } catch (NoSuchMethodException e) {
                continue;
            }
        }
    }


    /**
     * 自动转Map key值大写 将Map内的key与Bean中属性相同的内容复制到BEAN中
     * 
     * @param bean Object
     * @param properties Map
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void copyMap2Bean_Nobig(Object bean, Map properties)
            throws IllegalAccessException, InvocationTargetException {
        if ((bean == null) || (properties == null)) {
            return;
        }
        Iterator names = properties.keySet().iterator();
        while (names.hasNext()) {
            String name = (String) names.next();
            if (name == null) {
                continue;
            }
            Object value = properties.get(name);
            try {
                if (value == null) {
                    continue;
                }
                Class clazz = PropertyUtils.getPropertyType(bean, name);
                if (null == clazz) {
                    continue;
                }
                String className = clazz.getName();
                if ("java.util.Date".equalsIgnoreCase(className)) {
                    value = new java.util.Date(((java.sql.Timestamp) value).getTime());
                }
                setProperty(bean, name, value);
            } catch (NoSuchMethodException e) {
                continue;
            }
        }
    }

    /**
     * Map内的key与Bean中属性相同的内容复制到BEAN中 对于存在空值的取默认值
     * 
     * @param bean Object
     * @param properties Map
     * @param defaultValue String
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void copyMap2Bean(Object bean, Map properties, String defaultValue)
            throws IllegalAccessException, InvocationTargetException {
        if ((bean == null) || (properties == null)) {
            return;
        }
        Iterator names = properties.keySet().iterator();
        while (names.hasNext()) {
            String name = (String) names.next();
            if (name == null) {
                continue;
            }
            Object value = properties.get(name);
            try {
                Class clazz = PropertyUtils.getPropertyType(bean, name);
                if (null == clazz) {
                    continue;
                }
                String className = clazz.getName();
                if ("java.sql.Timestamp".equalsIgnoreCase(className)) {
                    if (value == null || "".equals(value)) {
                        continue;
                    }
                }
                if ("java.lang.String".equalsIgnoreCase(className)) {
                    if (value == null) {
                        value = defaultValue;
                    }
                }
                setProperty(bean, name, value);
            } catch (NoSuchMethodException e) {
                continue;
            }
        }
    }

    public static String blobToString(Blob b) {
        String result = "";
        if (b == null) {
            return result;
        }
        try {
            ByteArrayInputStream msgContent = (ByteArrayInputStream) b.getBinaryStream();
            byte[] byte_data = new byte[msgContent.available()];
            msgContent.read(byte_data, 0, byte_data.length);
            result = new String(byte_data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if ("NULL".equals(result))
        {
            result = StringUtils.EMPTY;
        }
        return result;

        // InputStream is = null;
        // StringBuilder s = new StringBuilder("");
        // try {
        // if (b == null) {
        // return s.toString();
        // }
        // is = b.getBinaryStream();
        // byte[] buffer = new byte[1024];
        // int len = 0;
        // while ((len = is.read(buffer)) != -1) {
        // s.append(new String(buffer, "utf-8"));
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // } catch (SQLException e1) {
        // e1.printStackTrace();
        // } catch (Exception e2) {
        // e2.printStackTrace();
        // } finally {
        // if (is != null) {
        // try {
        // is.close();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }
        //
        // }
        // return s.toString();
    }

    /**
     * 将一个 JavaBean 对象转化为一个 Map
     * 
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的 Map 对象
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    @SuppressWarnings({"rawtypes"})
    public static Map<String, Object> objectToMap(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!"class".equals(propertyName)) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

    public MyBeanUtils() {
        super();
    }
}
