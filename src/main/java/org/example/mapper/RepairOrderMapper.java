package org.example.mapper;

import org.example.entity.RepairOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
// @Mapper: 也是一个 Mapper 接口，需要被扫描和管理
@Mapper
public interface RepairOrderMapper extends BaseMapper<RepairOrder> {
}