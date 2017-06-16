package com.thinkgem.jeesite.interceptor;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.thinkgem.jeesite.utils.JsonHelper;
import com.thinkgem.jeesite.utils.TimeCostHelper;

/**
 * 增强对象转为json的功能,目的是为了增加请求响应内容输出
 * 
 * @author duanshao
 * 
 */
public class EnhanceMappingJacksonHttpMessageConverter extends MappingJackson2HttpMessageConverter {

    private final Logger LOG = LoggerFactory.getLogger("access");

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException,
            HttpMessageNotWritableException {
        Map<String, Object> carrier = TimeCostHelper.getCarrier();
        if (carrier != null) {
            String request = (String) carrier.get("request");
            String uri = (String) carrier.get("uri");
            try {
                String response = JsonHelper.toJson(object);
                LOG.info("uri:{}, request:{}; response:{}", uri, request, response);
            } catch (Exception e) {
                LOG.error("enhance mapping jackson error", e);
            }
        }
        super.writeInternal(object, outputMessage);
    }

}
