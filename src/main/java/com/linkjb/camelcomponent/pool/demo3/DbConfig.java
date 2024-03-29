package com.linkjb.camelcomponent.pool.demo3;


public class DbConfig {

    private String url;
    private String username;
    private String password;

    public DbConfig(String driver, String url, String username,
                    String password) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
