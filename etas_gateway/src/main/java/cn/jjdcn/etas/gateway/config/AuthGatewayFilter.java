package cn.jjdcn.etas.gateway.config;

import cn.jjdcn.etas.common.utils.MyJwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthGatewayFilter implements GatewayFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 获取jwt类型的信息
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        if (cookies.isEmpty()) {
            // 拦截
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        HttpCookie cookie = cookies.getFirst("TOKEN");
        // 2. 判断jwt类型的token
        if (cookie == null) {
            // 拦截
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        // 3. 解析jwt, 如果正常解析则放行
        String jwt = cookie.getValue();
        try{
            MyJwtUtil.parseJWT(jwt);
        } catch (Exception e){
            e.printStackTrace();
            // 拦截
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        return chain.filter(exchange);
    }
}
