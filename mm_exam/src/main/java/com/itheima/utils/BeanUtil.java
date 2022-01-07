package com.itheima.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * 填充表单数据到javabean的工具类
 *
 * @author zhy
 */
public class BeanUtil {
    /**
     * 封装表单中的数据到javabean中
     *
     * @param request 表单中的数据
     * @param clazz   封装到哪个javabean
     * @return 封装好的javabean对象 使用的是泛型。泛型必须先声明再使用。声明必须在返回值之前
     * T指的就是泛型，它可以是任意字符，只是作为一个占位符。
     * 声明时用什么字符，使用时就得用什么
     */
    public static <T> T fillBean(HttpServletRequest request, Class<T> clazz) {
        //1.定义一个T类型的javabean
        T bean = null;
        try {
            //2.实例化bean对象
            bean = clazz.newInstance();
            //3.使用BeanUtils的方法进行封装
            BeanUtils.populate(bean, request.getParameterMap());
            //4.返回
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 封装表单中的数据到javabean中,带有日期格式数据
     *
     * @param request 表单中的数据
     * @param clazz   封装到哪个javabean
     * @return 封装好的javabean对象 使用的是泛型。泛型必须先声明再使用。声明必须在返回值之前
     * T指的就是泛型，它可以是任意字符，只是作为一个占位符。
     * 声明时用什么字符，使用时就得用什么
     */
    public static <T> T fillBean(HttpServletRequest request, Class<T> clazz, String datePattern) {
        //1.定义一个T类型的javabean
        T bean = null;
        try {
            //2.实例化bean对象
            bean = clazz.newInstance();
            //3.创建日期转换器对象
            DateConverter converter = new DateConverter();
            converter.setPattern(datePattern);
            //4.设置转换器
            ConvertUtils.register(converter, Date.class);
            //5.使用BeanUtils的方法进行封装
            BeanUtils.populate(bean, request.getParameterMap());
            //6.返回
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
