package com.example.zuuldemo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author honglong.zhang
 * @date 2018/8/8 14:26
 */
@Component
public class AccessFilter extends ZuulFilter {
  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return 0;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() throws ZuulException {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    if (request.getParameter("AT") == null) {
      ctx.setSendZuulResponse(false);
      ctx.setResponseStatusCode(200);
      ctx.setResponseBody("{\"code\": 1, \"message\":\"no access\"}");
    }
    return null;
  }
}
