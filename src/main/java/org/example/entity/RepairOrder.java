package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.time.LocalDateTime;

@TableName("repair_order")
public class RepairOrder {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer studentId;
    private String building;
    private String room;
    private String deviceType;
    private String description;
    private Integer status;
    private String createTime;
    private String updateTime;

    // ⚠️ 新增：学生账号（数据库里没有这列，是查询时补充的）
    @TableField(exist = false)
    private String studentAccount;

    // Setter
    public void setId(Integer id) { this.id = id; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }
    public void setBuilding(String building) { this.building = building; }
    public void setRoom(String room) { this.room = room; }
    public void setDeviceType(String deviceType) { this.deviceType = deviceType; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(Integer status) { this.status = status; }
    public void setCreateTime(String createTime) { this.createTime = createTime; }
    public void setUpdateTime(String updateTime) { this.updateTime = updateTime; }
    // ⚠️ 新增 setter
    public void setStudentAccount(String studentAccount) { this.studentAccount = studentAccount; }

    // Getter
    public Integer getId() { return id; }
    public Integer getStudentId() { return studentId; }
    public String getBuilding() { return building; }
    public String getRoom() { return room; }
    public String getDeviceType() { return deviceType; }
    public String getDescription() { return description; }
    public Integer getStatus() { return status; }
    public String getCreateTime() { return createTime; }
    public String getUpdateTime() { return updateTime; }
    // ⚠️ 新增 getter
    public String getStudentAccount() { return studentAccount; }

    @Override
    public String toString() {
        return "RepairOrder{" +
                "id=" + id +
                ", device='" + deviceType + '\'' +
                ", status=" + status +
                ", desc='" + description + '\'' +
                ", studentAccount='" + studentAccount + '\'' +
                '}';
    }
}