package org.example.dto;

public class LoginResponse {
    private Integer id;
    private String account;
    private Integer role;
    private String token;  // Token 只在这里，不存数据库

    // Getter 和 Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account; }

    public Integer getRole() { return role; }
    public void setRole(Integer role) { this.role = role; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}