package cn.edu.hdu.clan.mapper.sys;

import cn.edu.hdu.clan.entity.sys.Incomesheet;
import tk.mybatis.mapper.common.Mapper;

public interface IncomesheetMapper extends Mapper<Incomesheet> {
    public Incomesheet query();
}