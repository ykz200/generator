package com.bhusk.entity;

/**
 * 数据库连接Bean
 *
 * @author CRT
 * @date 2018年11月19日
 */
public class DataBase {
    //id
    private String id;
    //IP地址
    private String url;
    //库名
    private String db;
    //端口
    private String port;
    //用户名
    private String username;
    //密码
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DataBase{" +
                "url='" + url + '\'' +
                ", db='" + db + '\'' +
                ", port='" + port + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataBase dataBase = (DataBase) o;

        if (url.equals(dataBase.url)
                && db.equals(dataBase.db)
                && port.equals(dataBase.port)
                && username.equals(dataBase.username)) {
            return true;
        }
        return false;
    }

}
