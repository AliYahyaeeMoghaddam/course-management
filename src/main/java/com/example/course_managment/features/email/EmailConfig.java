package com.example.course_managment.features.email;
import java.util.Objects;

public class EmailConfig {

    private String serverAddress;
    private int port;
    private String securityType;
    private String username;
    private String password;

    public EmailConfig(String serverAddress, int port, String securityType, String username, String password) {
        this.serverAddress = serverAddress;
        this.port = port;
        this.securityType = securityType;
        this.username = username;
        this.password = password;
    }


    //Getters
    public String getServerAddress() {
        return serverAddress;
    }

    public int getPort() {
        return port;
    }

    public String getSecurityType() {
        return securityType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailConfig)) return false;
        EmailConfig that = (EmailConfig) o;
        return port == that.port &&
                Objects.equals(serverAddress,that.serverAddress) &&
                Objects.equals(securityType,that.securityType) &&
                Objects.equals(username,that.username) &&
                Objects.equals(password,that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serverAddress, port, securityType, username, password);
    }

}
