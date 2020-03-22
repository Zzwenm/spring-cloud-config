package com.boss.gateway.filter;

import com.boss.learning.dto.ResultDto;
import com.boss.learning.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 全局网关过滤器
 *
 * @author Zzwen
 * @date 2020/3/18 19:23
 */
@Component
@Slf4j
public class TokenGatewayFilter implements GlobalFilter, Ordered {

    private ObjectMapper objectMapper;
    /**
     * 登录的url
     */
    private final String LOGIN_URL = "/user/login";

    public TokenGatewayFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        log.info("*********url: " + url);
        //如果是登录放行
        if (LOGIN_URL.equals(url)) {
            return chain.filter(exchange);
        }
        log.info("*********TokenGatewayFilter*********");
        ServerHttpResponse resp = exchange.getResponse();
        //获取token
        String token = exchange.getRequest().getHeaders().get("X-Token").get(0);
        if (StringUtils.isBlank(token)) {
            log.info("*********token为空*************");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        } else {
            //解析token
            try {
                //解析成功
                JwtUtil.parseJwt(token);
                return chain.filter(exchange);
            } catch (ExpiredJwtException e) {
                log.error(e.getMessage(), e);
                return authError(resp, "身份认证失败！");

            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return authError(resp, "身份认证失败");
            }
        }
    }

    /**
     * 认证错误输出
     *
     * @param resp 响应对象
     * @param msg  错误信息
     * @return
     */
    private Mono<Void> authError(ServerHttpResponse resp, String msg) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        ResultDto<String> returnData = new ResultDto<>(50014, msg, msg);
        String returnStr = "";
        try {
            returnStr = objectMapper.writeValueAsString(returnData);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        DataBuffer buffer = resp.bufferFactory().wrap(returnStr.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }

    /**
     * 数字越小优先级越高
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
