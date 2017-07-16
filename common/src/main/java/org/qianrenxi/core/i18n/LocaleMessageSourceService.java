package org.qianrenxi.core.i18n;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * 自定义国际化获取工具类
 * 封装 MessageSource ，隐藏获取Locale动作，简化调用方法。
 *
 */
@Component
public class LocaleMessageSourceService {
   
    @Resource
    private MessageSource messageSource;
   
    /**
     * @param code ：对应messages配置的key.
     * @return
     */
    public String getMessage(String code){
       return getMessage(code,null,null);
    }
    /**
     * @param code ：对应messages配置的key.
     * @return
     */
    public String getMessage(String code,String defaultMessage){
    	return getMessage(code,null,defaultMessage);
    }
   
    /**
     *
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.
     * @return
     */
    public String getMessage(String code,Object[] args){
       return getMessage(code, args,"");
    }
   
 
    /**
     *
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.
     * @param defaultMessage : 没有设置key的时候的默认值.
     * @return
     */
    public String getMessage(String code,Object[] args,String defaultMessage){
       //这里使用比较方便的方法，不依赖request.
       Locale locale = LocaleContextHolder.getLocale();
       return messageSource.getMessage(code, args, defaultMessage, locale);
    }
   
}
