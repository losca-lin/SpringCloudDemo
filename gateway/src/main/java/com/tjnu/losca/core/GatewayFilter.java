package com.tjnu.losca.core;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author Losca
 * @date 2022/2/8 17:32
 */
@Component
public class GatewayFilter implements GlobalFilter, Ordered {
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Autowired
    White white;
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("进入过滤器");
        //测试用
        return chain.filter(exchange);
        //ServerHttpRequest request = exchange.getRequest();
        //ServerHttpResponse response = exchange.getResponse();
        ////axios请求头中放入任何内容 都会先发一个OPTIONS请求　如果遇到不必验证直接放行
        //if (request.getMethod().name().equals("OPTIONS")) {
        //    return chain.filter(exchange);
        //}
        ////获取请求地址
        //String url = request.getPath().value();
        ////获取白名单地址
        //List<String> whiteUrls = white.getUrls();
        ////创建地址匹配对象
        //AntPathMatcher pathMatcher = new AntPathMatcher();
        //for (String whiteUrl : whiteUrls) {
        //    if (pathMatcher.match(whiteUrl,url)) {
        //        return chain.filter(exchange);
        //    }
        //}
        ////从请求头获取token
        //List<String> list = request.getHeaders().get("token");
        //if (list == null || list.size() == 0){
        //    return error(response, ResultJson.unauth("该用户未登陆或已超时"));
        //}
        ////拿到token字符串
        //String token = list.get(0);
        //try {
        //    //成功解析去缓存服务器验证用户权限是否存在 如果不存在说明登陆超时
        //    UmsUser user = JWTUtil.decoder(token);
        //    String key = "umsresource:"+user.getId()+":user";
        //    if (!redisTemplate.hasKey(key)){
        //        return error(response, ResultJson.unauth("该用户未登陆或已超时"));
        //    }
        //    //更新失效时间
        //    redisTemplate.expire(key, 30, TimeUnit.MINUTES);
        //    String line = redisTemplate.opsForValue().get(key);
        //    List<String> backUrls = JSONObject.parseArray(line, String.class);
        //
        //    //    循环匹配
        //    for (String backUrl : backUrls) {
        //        if (pathMatcher.match(backUrl,url)){
        //            //获取请求参数
        //            URI oldUri = request.getURI();
        //            String query = oldUri.getRawQuery();
        //            StringBuilder builder = new StringBuilder();
        //            if (StringUtils.isNotBlank(query)){
        //                builder.append(query).append("&");
        //            }
        //            builder.append("userId=" + user.getId());
        //            //由于uri只读不能改变 所以必须构建一个新的uri
        //            URI newUri = UriComponentsBuilder.fromUri(oldUri).replaceQuery(builder.toString()).build().toUri();
        //            //request只读 不能改变 构建一个新的request
        //            ServerHttpRequest newRequest = request.mutate().uri(newUri).build();
        //            ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
        //            return chain.filter(newExchange);
        //        }
        //    }
        ////    输出没有权限
        //    return error(response, ResultJson.forbid());
        //
        //} catch (Exception e) {
        //    e.printStackTrace();
        //    return error(response, ResultJson.unauth("非法请求"));
        //}
    }
    private Mono<Void> error(ServerHttpResponse response,ResultJson resultJson){
        response.getHeaders().set("Content-Type","application/json;charset=utf-8");
        DataBuffer buffer = response.bufferFactory().wrap(JSONObject.toJSONString(resultJson).getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Flux.just(buffer));
    }

    public int getOrder() {
        return 0;
    }
}
