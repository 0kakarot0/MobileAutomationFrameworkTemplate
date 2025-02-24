package utils.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppiumConfigModel {
    private String url;
    private int port;
    private JsFilePath jsFilePath;
    private NodePath nodePath;
    private General general;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JsFilePath {
        private String windows;
        private String macos;
        private String linux;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NodePath {
        private String windows;
        private String mac;
        private String linux;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class General {
        private String logLevel;
        private List<String> availableLogLevels;
    }
}
