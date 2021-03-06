package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.OrderGroup;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface OrderGroupService {
    void add(OrderGroup OrderGroup);

    void delete(String id);

    void update(OrderGroup OrderGroup);

    List<OrderGroup> list(String productId);

    OrderGroup getById(String id);

    OrderGroup getByOrderId(String orderId);
}
