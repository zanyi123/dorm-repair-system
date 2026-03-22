package org.example.mapper;

import org.example.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

// Mapper 接口，需要被扫描和管理
@Mapper
public interface UserMapper extends BaseMapper<User> {


}