package org.winter.common.util;

import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import org.winter.common.domain.NetInfo;

import javax.servlet.http.HttpServletRequest;

public class NetUtils {

    public static final String URL = "http://whois.pconline.com.cn/ipJson.jsp?json=true";

    public static NetInfo getNetInfo() {
        ObjectMapper mapper = new ObjectMapper();
        String json = HttpUtil.get(URL);
        try {
            return mapper.readValue(json, NetInfo.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new NetInfo();
    }

    public static String getBrowser() {
        HttpServletRequest request = ServletUtils.getRequest();
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        return browser.getName();
    }

}
