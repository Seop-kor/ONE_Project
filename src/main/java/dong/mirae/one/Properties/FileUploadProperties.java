package dong.mirae.one.Properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
@Data
public class FileUploadProperties {
    private String uploadDir;
}
