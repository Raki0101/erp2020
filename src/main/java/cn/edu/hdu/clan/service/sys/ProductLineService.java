package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.ProductLine;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface ProductLineService {
    void add(ProductLine ProductLine);

    void adds(List<ProductLine>  productLines);

    void delete(String id);

    void deleteByTeamCount(String userTeam);

    void update(ProductLine productLine);

    void build(ProductLine productLine);  //生产线投资

    void inputToProduce(ProductLine productLine);//上线生产产品

    void sale(ProductLine ProductLine);//卖生产线

    void switching(ProductLine ProductLine); //转产

    List<ProductLine> list();

    List<ProductLine> listDetail(ProductLine productLine);

    ProductLine getById(String id);

    void copyDataToNextPeriod(String userTeam ,int period ,int nextPeriod);
}
