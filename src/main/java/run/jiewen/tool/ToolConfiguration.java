package run.jiewen.tool;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import run.halo.app.plugin.ReactiveSettingFetcher;
@Component
@RequiredArgsConstructor
public class ToolConfiguration {
    private final ReactiveSettingFetcher settingFetcher;

    @Bean
    RouterFunction<ServerResponse> editorOptionsRouter() {
        return RouterFunctions.route()
            .GET("/apis/api.wx.jiewen.run/tool-options",
                request -> settingFetcher.getValues()
                    .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result)
                    )
            )
            .build();
    }
}
