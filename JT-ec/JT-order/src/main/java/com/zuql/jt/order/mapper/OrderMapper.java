package com.zuql.jt.order.mapper;


import com.zuql.jt.common.mapper.SysMapper;
import com.zuql.jt.manage.pojo.Order;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

public interface OrderMapper extends SysMapper<Order> {
    @Update("update tb_order set status = 6,updated = now() where status = 1 "
            + " and #{dateAgo} > created")
    void updateStatus(Date dateAgo);
	
}