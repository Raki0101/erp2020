package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "payables")
public class Payables extends BaseBean {
    /**
     * 主键
     */
    @Id
    private String id;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "team_count")
    private Integer teamCount;

    /**
     * 应付款编码
     */
    @Column(name = "payables_number")
    private Integer payablesNumber;

    /**
     * 金额
     */
    @Column(name = "money_total")
    private BigDecimal moneyTotal;

    /**
     * 应付款产生期间
     */
    private Integer period;

    /**
     * 剩余款换期
     */
    @Column(name = "surplus_period")
    private Integer surplusPeriod;

    /**
     * 创建人外键用户表
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 编辑人
     */
    @Column(name = "edit_user")
    private String editUser;

    /**
     * 编辑时间
     */
    @Column(name = "edit_time")
    private Date editTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return group_id
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * @param groupId
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * @return team_count
     */
    public Integer getTeamCount() {
        return teamCount;
    }

    /**
     * @param teamCount
     */
    public void setTeamCount(Integer teamCount) {
        this.teamCount = teamCount;
    }

    /**
     * 获取应付款编码
     *
     * @return payables_number - 应付款编码
     */
    public Integer getPayablesNumber() {
        return payablesNumber;
    }

    /**
     * 设置应付款编码
     *
     * @param payablesNumber 应付款编码
     */
    public void setPayablesNumber(Integer payablesNumber) {
        this.payablesNumber = payablesNumber;
    }

    /**
     * 获取金额
     *
     * @return money_total - 金额
     */
    public BigDecimal getMoneyTotal() {
        return moneyTotal;
    }

    /**
     * 设置金额
     *
     * @param moneyTotal 金额
     */
    public void setMoneyTotal(BigDecimal moneyTotal) {
        this.moneyTotal = moneyTotal;
    }

    /**
     * 获取应付款产生期间
     *
     * @return period - 应付款产生期间
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * 设置应付款产生期间
     *
     * @param period 应付款产生期间
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * 获取剩余款换期
     *
     * @return surplus_period - 剩余款换期
     */
    public Integer getSurplusPeriod() {
        return surplusPeriod;
    }

    /**
     * 设置剩余款换期
     *
     * @param surplusPeriod 剩余款换期
     */
    public void setSurplusPeriod(Integer surplusPeriod) {
        this.surplusPeriod = surplusPeriod;
    }

    /**
     * 获取创建人外键用户表
     *
     * @return create_user - 创建人外键用户表
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人外键用户表
     *
     * @param createUser 创建人外键用户表
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取编辑人
     *
     * @return edit_user - 编辑人
     */
    public String getEditUser() {
        return editUser;
    }

    /**
     * 设置编辑人
     *
     * @param editUser 编辑人
     */
    public void setEditUser(String editUser) {
        this.editUser = editUser;
    }

    /**
     * 获取编辑时间
     *
     * @return edit_time - 编辑时间
     */
    public Date getEditTime() {
        return editTime;
    }

    /**
     * 设置编辑时间
     *
     * @param editTime 编辑时间
     */
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
}