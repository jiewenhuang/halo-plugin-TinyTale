package run.jiewen.tool;

import org.pf4j.PluginWrapper;
import org.springframework.stereotype.Component;
import run.halo.app.plugin.BasePlugin;
@Component
public class WxTool extends BasePlugin{
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
