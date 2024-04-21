package run.jiewen.tool;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TinyTaleOptionsDTO {
    private JsonNode basic;
    private JsonNode home;
    private JsonNode about;
    private JsonNode APPSECRET;

    public TinyTaleOptionsDTO(JsonNode basic, JsonNode home, JsonNode about) {
        this.basic = basic;
        this.home = home;
        this.about = about;
    }
    public TinyTaleOptionsDTO(JsonNode APPSECRET) {
        this.APPSECRET = APPSECRET;
    }

    // 如果需要其他方法或逻辑，可以在这里添加
}
