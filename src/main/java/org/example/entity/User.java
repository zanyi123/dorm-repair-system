package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

@TableName("user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String account;
    private String password;
    private Integer role;
    private String name;
    private String dormBuilding;
    private String dormRoom;
    private LocalDateTime createTime;


    // === Setter 方法 (不用改，保持原样) ===
    public void setId(Integer id) { this.id = id; }
    public void setAccount(String account) { this.account = account; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(Integer role) { this.role = role; }
    public void setName(String name) { this.name = name; }
    public void setDormBuilding(String dormBuilding) { this.dormBuilding = dormBuilding; }
    public void setDormRoom(String dormRoom) { this.dormRoom = dormRoom; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }


    // === Getter 方法 ===

    public Integer getId() {                    // 🔴 删掉 (int id)
        return id;
    }

    public String getAccount() {                // 🔴 删掉 (String account)
        return account;
    }

    public String getPassword() {               // 🔴 删掉 (String password)
        return password;
    }

    public Integer getRole() {                  // 🔴 删掉 (int role) + 🔴 String 改 Integer
        return role;
    }

    public String getName() {                   // 🔴 删掉 (String name)
        return name;
    }

    public String getDormBuilding() {           // 🔴 删掉 (String dormBuilding)
        return dormBuilding;
    }

    public String getDormRoom() {               // 🔴 删掉 (String dormRoom)
        return dormRoom;
    }

    public LocalDateTime getCreateTime() {      // 🔴 删掉 (LocalDateTime createTime)
        return createTime;
    }



    // === toString 方法 (不用改) ===
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", dorm='" + dormBuilding + "-" + dormRoom + '\'' +
                '}';
    }
}