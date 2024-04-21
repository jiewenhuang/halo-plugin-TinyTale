package run.jiewen.tool;

import org.pf4j.PluginWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import run.halo.app.plugin.BasePlugin;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WxTool extends BasePlugin {
    @Autowired
    private WebClient webClient;

    public WxTool(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        System.out.println("插件启动成功！");
    }

    @Override
    public void stop() {
        System.out.println("插件停止！");
    }
}