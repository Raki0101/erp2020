package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Factory;
import cn.edu.hdu.clan.entity.sys.MaterialOrder;
import cn.edu.hdu.clan.entity.sys.OrderManagement;
import cn.edu.hdu.clan.entity.sys.Salepayment;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.SalepaymentMapper;
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
public class SalepaymentServiceImpl implements SalepaymentService {

    @Autowired
    private SalepaymentMapper SalepaymentMapper;


    @Resource
    private AccountingVoucherService accountingVoucherService;

    @Transactional
    @Override
    public void add(Salepayment Salepayment) {
        BaseBeanHelper.insert(Salepayment);
        SalepaymentMapper.insert(Salepayment);
    }

    public void adds(List<Salepayment>  salepayments) {
        if(salepayments.size() > 0) {
            for (int i = 0; i < salepayments.size(); i++) {
                String userTeam = Jurisdiction.getUserTeam();
                int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
                salepayments.get(i).setPeriod(period);
                salepayments.get(i).setTeamCount(userTeam);
                salepayments.get(i).setGroupId("1000");
                BaseBeanHelper.insert(salepayments.get(i));
                SalepaymentMapper.insert(salepayments.get(i));
            }
        }
    }


    @Override
    public void delete(String id) {
    SalepaymentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByTeamCount(String userTeam) {
        Example example = new Example(Salepayment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        SalepaymentMapper.deleteByExample(example);
    }



    @Override
    public void addByOrderManagement(OrderManagement OrderManagement)
    {
        //全局变量 写入当前公司或小组ID
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());

        Salepayment mySalePayment = new Salepayment();
        mySalePayment.setGroupId("1000");
        mySalePayment.setTeamCount(userTeam);
        mySalePayment.setPeriod(period);
        mySalePayment.setNumber(OrderManagement.getOrderId());
        mySalePayment.setSaleOrderId(OrderManagement.getOrderId());
        mySalePayment.setMoney(OrderManagement.getMoney());
        mySalePayment.setPayPeriod(OrderManagement.getPeriodPay());
        mySalePayment.setState(0); //设置当前收款单的状态为0,表示没有收款（结算）

        BaseBeanHelper.insert(mySalePayment);
        SalepaymentMapper.insert(mySalePayment);


    }

    @Override
    public void addBySaleFactory(Factory factory)
    {
        //全局变量 写入当前公司或小组ID
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());

        Salepayment mySalePayment = new Salepayment();
        mySalePayment.setGroupId("1000");
        mySalePayment.setTeamCount(userTeam);
        mySalePayment.setPeriod(period);
        mySalePayment.setNumber(factory.getNumber());
        mySalePayment.setSaleOrderId(factory.getName());
        mySalePayment.setMoney(factory.getMoneyTotal());
        mySalePayment.setPayPeriod(4);   //出售厂房生成4个账期的应收款
        mySalePayment.setState(0); //设置当前收款单的状态为0,表示没有收款（结算）

        BaseBeanHelper.insert(mySalePayment);
        SalepaymentMapper.insert(mySalePayment);


    }

    @Override
    public void receivePayment(String userTeam ,int period) {
        Example example = new Example(Salepayment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andNotEqualTo("state", 0);
        List<Salepayment> oldRow = SalepaymentMapper.selectByExample(example);
        if(oldRow.size() > 0)
        {
            String number = "";
            BigDecimal myMoney = BigDecimal.valueOf(0);
            int createPeriod = 0;
            int duePeriod = 0;

            for(int i=1;i<oldRow.size();i++)
            {
                number = oldRow.get(i).getNumber();
                myMoney =  oldRow.get(i).getMoney();
                createPeriod = oldRow.get(i).getPeriod();
                duePeriod = createPeriod+oldRow.get(i).getPayPeriod(); //到期时段等于：创建该应收款的期间加上账期。

                    if(duePeriod == period) //到期时段等于当前时段
                    {
                        oldRow.get(i).setState(1);
                        SalepaymentMapper.updateByPrimaryKey(oldRow.get(i));

                        //自动生成收款的会计凭证
                        accountingVoucherService.voucherMaker(userTeam,period, myMoney,"XSSK",number+"收款");


                    }



            }

        }
    }


    @Override
    public void update(Salepayment Salepayment) {
        BaseBeanHelper.edit(Salepayment);
        Example example = new Example(Salepayment.class);
        example.createCriteria().andEqualTo("id", Salepayment.getId());
        SalepaymentMapper.updateByExampleSelective(Salepayment, example);
    }

    @Override
    public PageInfo<Salepayment> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(SalepaymentMapper.selectAll());
    }

    @Override
    public Salepayment getById(String id) {
        Example example = new Example(Salepayment.class);
        example.createCriteria().andEqualTo("id", id);
        return SalepaymentMapper.selectOneByExample(example);
    }
}
