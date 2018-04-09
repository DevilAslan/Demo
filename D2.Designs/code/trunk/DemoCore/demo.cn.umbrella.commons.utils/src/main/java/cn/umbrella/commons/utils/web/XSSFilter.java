package cn.umbrella.commons.utils.web;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class XSSFilter implements Filter {
    public Map<String,String> filterMap = new HashMap<String, String>();
    public String[] excludedURLArray;
    
    @SuppressWarnings("unchecked")
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Enumeration<String> initParameterNames = filterConfig.getInitParameterNames();
        while (initParameterNames.hasMoreElements()){
            String paramName = initParameterNames.nextElement();
            String paramValue = filterConfig.getInitParameter(paramName);
            filterMap.put(paramName, paramValue);
            if (Objects.equals(paramName, "excludedURLs")) {
            	if (StringUtils.isNotBlank(paramValue)) {
            		excludedURLArray = paramValue.split(",");
            	}
            }
        }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    	boolean isExcludedURL = false;  
    	if (null != excludedURLArray && excludedURLArray.length > 0) {
    		for (String url : excludedURLArray) {
    			if(((HttpServletRequest) servletRequest).getServletPath().equals(url)){  
    				isExcludedURL = true;  
    				break;  
				}  
			}
    	}
    	if (isExcludedURL) {
    		filterChain.doFilter(servletRequest, servletResponse);  
    	} else {
    		filterChain.doFilter(new XSSRequestWrapper((HttpServletRequest) servletRequest), servletResponse);
    	}
    }

    @Override
    public void destroy() {
        filterMap=null;
    }
}
