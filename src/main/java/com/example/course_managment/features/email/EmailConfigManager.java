package com.example.course_managment.features.email;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class EmailConfigManager {

    private static final String config_file = "res/email_config.properties";

    public static EmailConfig load(){
        Properties props = new Properties();
        try(FileInputStream in = new FileInputStream(config_file)){
            props.load(in);
            return new EmailConfig(
                    props.getProperty("server"),
                    Integer.parseInt(props.getProperty("port")),
                    props.getProperty("security"),
                    props.getProperty("username"),
                    props.getProperty("password")
            );
        } catch (IOException e) {
            throw new RuntimeException("failed to load email config file", e);
        }
    }

    public static void save(EmailConfig config){
        Properties props = new Properties();
        props.setProperty("server",config.getServerAddress());
        props.setProperty("port",String.valueOf(config.getPort()));
        props.setProperty("security",config.getSecurityType());
        props.setProperty("username",config.getUsername());
        props.setProperty("password",config.getPassword());
        try(FileOutputStream out = new FileOutputStream(config_file)){
            props.store(out, "Email Config File");
        }catch (IOException e){
            throw new RuntimeException("failed to save email config file", e);
        }
    }
}
