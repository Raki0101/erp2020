package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.MarketFee;
import com.github.pagehelper.PageInfo;

public interface MarketFeeService {
    void add(MarketFee MarketFee);

    void delete(String id);

    void update(MarketFee MarketFee);

    PageInfo<MarketFee> list(int pageNum, int pageSize);

    MarketFee getById(String id);
}
