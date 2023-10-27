package example.myservice.config;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConfigJDBC {
    public Connection connect() {
        Properties dbProperties = new Properties();
        try (FileReader dbPropertiesReader = new FileReader("D:\\Projects\\IdeaProjects\\aston-sandbox\\my-service\\src\\main\\resources\\db.properties")){
            dbProperties.load(dbPropertiesReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String url = dbProperties.getProperty("url");
        String username = dbProperties.getProperty("user_name");
        String password = dbProperties.getProperty("password");
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
