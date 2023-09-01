package me.ding.easywareflow.filter;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.ding.easywareflow.entity.Result;
import me.ding.easywareflow.utils.WarehouseConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录限制的Servlet过滤器:
 */
public class SecurityFilter implements Filter {

    //将redis模板定义为其成员变量
    private StringRedisTemplate redisTemplate;

    //成员变量redis模板的set方法
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 过滤器拦截到请求执行的方法:
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //获取请求url接口
        String path = request.getServletPath();
        System.out.println("path = " + path);
        /*
          白名单请求都直接放行:
         */
        List<String> urlList = new ArrayList<>();
        urlList.add("/captcha/captchaImage");
        urlList.add("/login");
        urlList.add("/logout");
        //对上传图片的url接口/product/img-upload的请求直接放行
        urlList.add("/product/img-upload");
        //对static下的/img/upload中的静态资源图片的访问直接放行
        if (urlList.contains(path) || path.contains("/img/upload") || path.contains("/swagger-ui") || path.contains("/v3")) {
            chain.doFilter(request, response);
            System.out.println("白名单请求, 放行");
            return;
        }

        /*
          其它请求都校验token:
         */
        //拿到前端归还的token
        String clientToken = request.getHeader(WarehouseConstants.HEADER_TOKEN_NAME);
        System.out.println("Token = " + clientToken);
        //token校验通过,请求放行
        if (StringUtils.hasText(clientToken) && Boolean.TRUE.equals(redisTemplate.hasKey(clientToken))) {
            chain.doFilter(request, response);
            System.out.println("token 校验通过, 放行");
            return;
        }
        //token校验失败,向前端响应失败的Result对象转成的json串
        Result result = Result.err(Result.CODE_ERR_UNLOGINED, "请登录！");
        String jsonStr = JSON.toJSONString(result);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }
}
