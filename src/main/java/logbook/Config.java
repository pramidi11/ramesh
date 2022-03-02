package logbook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class Config {

    private String username;
    private String password;
    private String batchDate;
    private boolean submit;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(String batchDate) {
        this.batchDate = batchDate;
    }

    public boolean isSubmit() {
        return submit;
    }

    public void setSubmit(boolean submit) {
        this.submit = submit;
    }

    public Config getConfig() throws IOException {
        String fileName = System.getenv("env");
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.registerModules();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File filePath = new File(classLoader.getResource(fileName +".yml").getFile());
        return mapper.readValue(filePath, Config.class);
    }
}
