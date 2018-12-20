package pojo;

import java.sql.Date;

public class TradeDeviceDO {
    @Override
    public String toString() {
        return "TradeDeviceDO{" +
                "shopNo=" + shopNo +
                ", shopName='" + shopName + '\'' +
                ", deviceNo='" + deviceNo + '\'' +
                ", psamId='" + psamId + '\'' +
                ", messId='" + messId + '\'' +
                ", messName='" + messName + '\'' +
                ", floorId='" + floorId + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", regionId='" + regionId + '\'' +
                ", deviceNoShort='" + deviceNoShort + '\'' +
                ", deptId='" + deptId + '\'' +
                ", id=" + id +
                ", orgCode='" + orgCode + '\'' +
                ", deviceType=" + deviceType +
                ", createTime=" + createTime +
                ", deviceBrand='" + deviceBrand + '\'' +
                ", deviceModel='" + deviceModel + '\'' +
                '}';
    }

    public Integer getShopNo() {
        return shopNo;
    }

    public void setShopNo(Integer shopNo) {
        this.shopNo = shopNo;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getPsamId() {
        return psamId;
    }

    public void setPsamId(String psamId) {
        this.psamId = psamId;
    }

    public String getMessId() {
        return messId;
    }

    public void setMessId(String messId) {
        this.messId = messId;
    }

    public String getMessName() {
        return messName;
    }

    public void setMessName(String messName) {
        this.messName = messName;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getDeviceNoShort() {
        return deviceNoShort;
    }

    public void setDeviceNoShort(String deviceNoShort) {
        this.deviceNoShort = deviceNoShort;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    private Integer shopNo;
    private String shopName;
    private String deviceNo;
    private String psamId;
    private String messId;
    private String messName;
    private String floorId;
    private String ownerId;
    private String regionId;
    private String deviceNoShort;
    private String deptId;
    private Integer id;
    private String orgCode;
    private Integer deviceType;
    private Date createTime;
    private String deviceBrand;
    private String deviceModel;
}