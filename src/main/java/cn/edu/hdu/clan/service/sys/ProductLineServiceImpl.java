package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Factory;
import cn.edu.hdu.clan.entity.sys.ProductLine;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.ProductLineMapper;
import cn.edu.hdu.clan.util.Jurisdiction;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductLineServiceImpl implements ProductLineService {

    @Autowired
    private ProductLineMapper ProductLineMapper;

    @Resource
    private AccountingVoucherService accountingVoucherService;
    @Resource
    private InvService invService;

    @Transactional
    @Override
    public void add(ProductLine productLine) {
        //全局变量 写入当前公司或小组ID
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        //补充相关字段的取值
        productLine.setTeamCount(userTeam);
        productLine.setGroupId("1000");
        BaseBeanHelper.insert(productLine);
        ProductLineMapper.insert(productLine);
    }

    public void adds(List<ProductLine>  productLines) {
        if(productLines.size() > 0) {
            for (int i = 0; i < productLines.size(); i++) {
                String userTeam = Jurisdiction.getUserTeam();
                int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
                productLines.get(i).setPeriod(period);
                productLines.get(i).setTeamCount(userTeam);
                productLines.get(i).setGroupId("1000");
                BaseBeanHelper.insert(productLines.get(i));
                ProductLineMapper.insert(productLines.get(i));
            }
        }
    }


    @Override
    public void delete(String id) {
    ProductLineMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByTeamCount(String userTeam) {
        Example example = new Example(Factory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        ProductLineMapper.deleteByExample(example);
    }



    //将生产线信息整体复制到下一个会计期间
    @Override
    public void copyDataToNextPeriod(String userTeam ,int period ,int nextPeriod){
        Example example = new Example(ProductLine.class);
        example.createCriteria().andEqualTo("teamCount", userTeam);
        example.createCriteria().andEqualTo("period", period);
        List<ProductLine> productLines = ProductLineMapper.selectByExample(example);

        if(productLines.size() > 0)
        {
            for(int i= 0 ;i<productLines.size();i++)
            {
                ProductLine myRow =  productLines.get(i);
                myRow.setPeriod(nextPeriod);
                BaseBeanHelper.insert(myRow);
                ProductLineMapper.insert(myRow);

            }
        }


    }

    //通过一个生产线的信息，获得后台完整的生产线信息。productLineNumber取值范围为1-10

    public ProductLine productLineRow(ProductLine producptLine) {
        String factoryNumber = producptLine.getFactoryNumber();
        String productLineNumber = producptLine.getProductLineNumber();
        String productLineType = producptLine.getProductLineTypeId();
        int period =  producptLine.getPeriod();
        Example example = new Example(ProductLine.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("factoryNumber", factoryNumber);
        criteria.andEqualTo("productLineNumber", productLineNumber);
        criteria.andEqualTo("period", period);

       return  ProductLineMapper.selectOneByExample(example);
    }

    //投建生产线
    @Override
    public void build(ProductLine productLine) {
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        String factoryNumber = productLine.getFactoryNumber();
        String productLineNumber = productLine.getProductLineNumber();
        String productLineType = productLine.getProductLineTypeId();

        ProductLine myRow = productLineRow(productLine);
        if(myRow == null)
        {  //全局变量 写入当前公司或小组ID

            //补充相关字段的取值
            productLine.setTeamCount(userTeam);
            productLine.setGroupId("1000");
            productLine.setPeriod(period);
            productLine.setInvestmentAmountA(BigDecimal.valueOf(5)); //投入建设，每期5M
            productLine.setState(0);
            productLine.setEditFlag(1);
            BaseBeanHelper.insert(productLine);
            ProductLineMapper.insert(productLine);


        }else
        {
            myRow.setInvestmentAmountA(BigDecimal.valueOf(5)); //投入建设，每期5M
            productLine.setEditFlag(1);
            BaseBeanHelper.edit(productLine);
            ProductLineMapper.updateByPrimaryKey(productLine);
        }

        BigDecimal value1= productLine.getDeviceValue();
        BigDecimal value2= productLine.getInvestmentAmountA();
        if(value1.compareTo(value2) ==0) {
            //自动生成在建工程对应的会计凭证
            accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("5"), "”ZJGC", factoryNumber + productLineNumber + productLineType);
            ////自动生成在建工程转出到“机器与设备”对应的会计凭证:在建工程转出
            accountingVoucherService.voucherMaker(userTeam, period, value1, "”ZJGCZC", factoryNumber + productLineNumber + productLineType);

        }else if(value1.compareTo(value2) ==1)
        {
            //自动生成在建工程对应的会计凭证
            accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("5"), "”ZJGC", factoryNumber + productLineNumber + productLineType);

        }




    }

//投入产品到生产线
    @Override
    public void inputToProduce(ProductLine productLine) {
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        String factoryNumber = productLine.getFactoryNumber();
        String productLineNumber = productLine.getProductLineNumber();
        String productLineType = productLine.getProductLineTypeId();

        ProductLine myRow = productLineRow(productLine);
        myRow.setProcessingCycleB(period);
        myRow.setState(1);
        myRow.setEditFlag(1);
        BaseBeanHelper.edit(myRow);
        Example example = new Example(ProductLine.class);
        example.createCriteria().andEqualTo("id", myRow.getId());
        ProductLineMapper.updateByExampleSelective(myRow, example);

        String myProduct = myRow.getProductC();
        if(myProduct == "P1")
        {
            invService.stockOutToProduce(userTeam,period,"R1",1,factoryNumber + productLineNumber + productLineType+"P1R1");
        }
        if(myProduct == "P2")
        {
            invService.stockOutToProduce(userTeam,period,"R2",1,factoryNumber + productLineNumber + productLineType+"P2R2");
            invService.stockOutToProduce(userTeam,period,"R3",1,factoryNumber + productLineNumber + productLineType+"P2R3");
        }
        if(myProduct == "P3")
        {
            invService.stockOutToProduce(userTeam,period,"R2",2,factoryNumber + productLineNumber + productLineType+"P3R2");
            invService.stockOutToProduce(userTeam,period,"R3",1,factoryNumber + productLineNumber + productLineType+"P3R3");
        }
        if(myProduct == "P1")
        {
            invService.stockOutToProduce(userTeam,period,"R2",1,factoryNumber + productLineNumber + productLineType+"P4R2");
            invService.stockOutToProduce(userTeam,period,"R3",1,factoryNumber + productLineNumber + productLineType+"P4R3");
            invService.stockOutToProduce(userTeam,period,"R4",2,factoryNumber + productLineNumber + productLineType+"P4R4");
        }

        //自动生成投入生产的会计凭证.借在制品1 贷现金
        accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("1"), "”SCRGF", factoryNumber + productLineNumber + productLineType+myProduct);
    }


//出售生产线
    @Override
    public void sale(ProductLine productLine) {
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        String factoryNumber = productLine.getFactoryNumber();
        String productLineNumber = productLine.getProductLineNumber();
        String productLineType = productLine.getProductLineTypeId();

        ProductLine myRow = productLineRow(productLine);

        BigDecimal value1= myRow.getDeviceValue(); //设备原值
        BigDecimal value2= myRow.getDeprecationA(); //已提折旧
        BigDecimal value3= value1.subtract(value2); //账面净值
        BigDecimal value4= new BigDecimal(0);

        myRow.setState(4); //状态码设置为4.表示这条生产线已卖掉。
        myRow.setEditFlag(1);
        BaseBeanHelper.edit(myRow);
        Example example = new Example(ProductLine.class);
        example.createCriteria().andEqualTo("id", myRow.getId());
        ProductLineMapper.updateByExampleSelective(myRow, example);


        if(productLineType == "手工线")
        {
            //自动生成收回生产线残值的会计凭证.借现金1 贷机器与设备
            accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("1"), "”SHCZ", factoryNumber + productLineNumber + productLineType+"收回残值");
            value4 = value3.subtract(new BigDecimal(1));

        }
        if(productLineType == "半自动")
        {
            //自动生成收回生产线残值的会计凭证.借现金1 贷机器与设备
            accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("2"), "”SHCZ", factoryNumber + productLineNumber + productLineType+"收回残值");
            value4 = value3.subtract(new BigDecimal(2));
        }
        if(productLineType == "全自动")
        {
            //自动生成收回生产线残值的会计凭证.借现金1 贷机器与设备
            accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("3"), "”SHCZ", factoryNumber + productLineNumber + productLineType+"收回残值");
            value4 = value3.subtract(new BigDecimal(3));
        }
        if(productLineType == "柔性线")
        {
            //自动生成收回生产线残值的会计凭证.借现金1 贷机器与设备
            accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("4"), "”SHCZ", factoryNumber + productLineNumber + productLineType+"收回残值");
            value4 = value3.subtract(new BigDecimal(4));
        }

        if(value4.compareTo(new BigDecimal(0)) == 1)  //当未提折旧减去残值后的值大于零。
        {
            //卖出生产线的损失的会计凭证.借其它支出贷机器与设备
            accountingVoucherService.voucherMaker(userTeam, period, value4,"”SS", factoryNumber + productLineNumber + productLineType+"卖出生产线损失");
        }


    }

    //转产准备
    @Override
    public void switching(ProductLine productLine) {
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        String factoryNumber = productLine.getFactoryNumber();
        String productLineNumber = productLine.getProductLineNumber();
        String productLineType = productLine.getProductLineTypeId();

        ProductLine myRow = productLineRow(productLine);

        if(productLineType == "半自动")
        {
            myRow.setState(3);
            myRow.setEditFlag(1);
            myRow.setTransferredPeriodA(1);
            myRow.setTransferFeeA(new BigDecimal(1));
            BaseBeanHelper.edit(myRow);
            Example example = new Example(ProductLine.class);
            example.createCriteria().andEqualTo("id", myRow.getId());
            ProductLineMapper.updateByExampleSelective(myRow, example);

            //自动生成转产费用的凭证。借综合费用 贷现金  注意：这里跟会计的基本方法不一样。为了生产成本的标准成本不被破坏，把本应列入制造费用的金额放到综合费用里处理。
            accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("1"), "SCXZC", factoryNumber + productLineNumber + productLineType+"转产");


        }

        if(productLineType == "全自动")
        {
            myRow.setState(3);
            myRow.setEditFlag(1);
            myRow.setTransferredPeriodA(1);
            myRow.setTransferFeeA(new BigDecimal(2)); //全自动线的转产费用为每期2M
            BaseBeanHelper.edit(myRow);
            Example example = new Example(ProductLine.class);
            example.createCriteria().andEqualTo("id", myRow.getId());
            ProductLineMapper.updateByExampleSelective(myRow, example);

            //自动生成转产费用的凭证。借综合费用 贷现金  注意：这里跟会计的基本方法不一样。为了生产成本的标准成本不被破坏，把本应列入制造费用的金额放到综合费用里处理。
            accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("2"), "SCXZC", factoryNumber + productLineNumber + productLineType+"转产");


        }


    }

    @Override
    public void update(ProductLine ProductLine) {
        BaseBeanHelper.edit(ProductLine);
        Example example = new Example(ProductLine.class);
        example.createCriteria().andEqualTo("id", ProductLine.getId());
        ProductLineMapper.updateByExampleSelective(ProductLine, example);
    }


    //显示当前所有的生产线信息
    @Override
    public List<ProductLine> list() {
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        Example example = new Example(ProductLine.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        criteria.andNotEqualTo("state", 4);  //反回的生产线信息里， state=4 表示该线在本期已卖掉了。
        return  ProductLineMapper.selectByExample(example);
    }

    //显示指定生产线信息的明细信息
    @Override
    public List<ProductLine> listDetail(ProductLine productLine) {
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        String myProductLineNumber = productLine.getProductLineNumber();
        Example example= new Example(ProductLine.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        criteria.andEqualTo("productLineNumber", myProductLineNumber);  //
        List<ProductLine> myListDetail = ProductLineMapper.selectByExample(example);
        return  myListDetail;
    }

    @Override
    public ProductLine getById(String id) {
        Example example = new Example(ProductLine.class);
        example.createCriteria().andEqualTo("id", id);
        return ProductLineMapper.selectOneByExample(example);
    }
}
