package run.jiewen.tool;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import run.halo.app.plugin.ReactiveSettingFetcher;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ToolConfiguration {
    private final ReactiveSettingFetcher settingFetcher;
    @Autowired
    private WebClient webClient;

    @Bean
    RouterFunction<ServerResponse> tinyTaleOptionsRouter() {
        return RouterFunctions.route()
            .GET("/apis/api.tinytale.jiewen.run/tool-options",
                request -> settingFetcher.getValues()
                    .map(this::toTinyTaleOptionsDTO)
                    .flatMap(dto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(dto))
            )
            .build();
    }
    // New router for family
    @Bean
    RouterFunction<ServerResponse> familyRouter() {
        return RouterFunctions.route()
            .GET("/apis/api.tinytale.jiewen.run/verify-openid",RequestPredicates.queryParam("code", code -> true),
                request -> request.queryParam("code")
                    .map(this::verifyOpenid)
                    .orElse(ServerResponse.badRequest().bodyValue("{\"error\": \"Code parameter is missing\"}")))
            .build();
    }
    private Mono<ServerResponse> verifyOpenid(String code) {
        return settingFetcher.getValues()
            .map(this::checkAppSecretNode)
            .flatMap(dto -> {
                JsonNode appSecretNode = dto.getAPPSECRET();
                return webClient.get()
                    .uri("https://api.weixin.qq.com/sns/jscode2session?appid=" + appSecretNode.get("appid").asText() + "&secret=" + appSecretNode.get("appSecret").asText() + "&js_code=" + code + "&grant_type=authorization_code")
                    .retrieve()
                    .bodyToMono(String.class)
                    .flatMap(response -> {
                        try {
                            ObjectMapper objectMapper = new ObjectMapper();
                            JsonNode responseJson = objectMapper.readTree(response);
                            // 从 responseJson 中获取你需要的值
                            String openid = responseJson.get("openid").asText();
                            // 将返回的JSO解析
                            if (responseJson.has("openid")) {
                                //    判断获取的openid是否与配置的openid一致，一直则返回是布尔值和openid
                                if (openid.equals(appSecretNode.get("openid").asText())) {
                                    return ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue("{\"result\": true, \"openid\": \"" + appSecretNode.get("openid").asText() + "\"}");
                                } else {
                                    return ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue("{\"result\": false, \"message\": \"认证错误，非管理用户\"}");

                                }}
                            // ...
                        } catch (Exception e) {
                            // 处理解析 JSON 时可能出现的异常
                        }

                        return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(response);
                        });
            });
    }

    private boolean checkAppSecretNode(JsonNode appSecretNode) {
        return appSecretNode.has("appid") && appSecretNode.has("appSecret") && appSecretNode.has("openid");
    }



    private TinyTaleOptionsDTO toTinyTaleOptionsDTO(Map<String, JsonNode> settings) {
        JsonNode baseOptions = settings.getOrDefault("basic", null);
        JsonNode homeOptions = settings.getOrDefault("home", null);
        JsonNode aboutOptions = settings.getOrDefault("about", null);

        return new TinyTaleOptionsDTO(baseOptions, homeOptions, aboutOptions);
    }
    private TinyTaleOptionsDTO checkAppSecretNode(Map<String, JsonNode> settings) {
        JsonNode APPSECRETOptions = settings.getOrDefault("APPSECRET", null);

        return new TinyTaleOptionsDTO(APPSECRETOptions);
    }
}