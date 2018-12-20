package pojo;

import java.sql.Date;
import java.sql.Timestamp;

public class TradeDataDO {
    @Override
    public String toString() {
        return "TradeDataDO{" +
                "tdId=" + tdId +
                ", cardMediaType=" + cardMediaType +
                ", terminalTradeSn=" + terminalTradeSn +
                ", businessId='" + businessId + '\'' +
                ", opterId='" + opterId + '\'' +
                ", posId='" + posId + '\'' +
                ", samId='" + samId + '\'' +
                ", samTradeSn=" + samTradeSn +
                ", cityCode='" + cityCode + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", cardTradeSn=" + cardTradeSn +
                ", cardMainType='" + cardMainType + '\'' +
                ", cardSubType='" + cardSubType + '\'' +
                ", cardBalanceBof=" + cardBalanceBof +
                ", tradeMoney=" + tradeMoney +
                ", tradeValue=" + tradeValue +
                ", tradeTime='" + tradeTime + '\'' +
                ", tac='" + tac + '\'' +
                ", cardVerIn='" + cardVerIn + '\'' +
                ", testFlag=" + testFlag +
                ", industryCode='" + industryCode + '\'' +
                ", keyVer='" + keyVer + '\'' +
                ", cardPublisher='" + cardPublisher + '\'' +
                ", tradeMainType='" + tradeMainType + '\'' +
                ", tradeSubType='" + tradeSubType + '\'' +
                ", cardBalanceEof=" + cardBalanceEof +
                ", overdraft=" + overdraft +
                ", lastTerminalId='" + lastTerminalId + '\'' +
                ", lastTradeTime='" + lastTradeTime + '\'' +
                ", lastTradeType='" + lastTradeType + '\'' +
                ", lastTradeMoney=" + lastTradeMoney +
                ", lastTradeSn=" + lastTradeSn +
                ", reusingArea='" + reusingArea + '\'' +
                ", tdflag='" + tdflag + '\'' +
                ", crc16='" + crc16 + '\'' +
                ", createTime=" + createTime +
                ", orgCode='" + orgCode + '\'' +
                '}';
    }

    public Integer getTdId() {
        return tdId;
    }

    public void setTdId(Integer tdId) {
        this.tdId = tdId;
    }

    public Integer getCardMediaType() {
        return cardMediaType;
    }

    public void setCardMediaType(Integer cardMediaType) {
        this.cardMediaType = cardMediaType;
    }

    public Integer getTerminalTradeSn() {
        return terminalTradeSn;
    }

    public void setTerminalTradeSn(Integer terminalTradeSn) {
        this.terminalTradeSn = terminalTradeSn;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getOpterId() {
        return opterId;
    }

    public void setOpterId(String opterId) {
        this.opterId = opterId;
    }

    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }

    public String getSamId() {
        return samId;
    }

    public void setSamId(String samId) {
        this.samId = samId;
    }

    public Integer getSamTradeSn() {
        return samTradeSn;
    }

    public void setSamTradeSn(Integer samTradeSn) {
        this.samTradeSn = samTradeSn;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getCardTradeSn() {
        return cardTradeSn;
    }

    public void setCardTradeSn(Integer cardTradeSn) {
        this.cardTradeSn = cardTradeSn;
    }

    public String getCardMainType() {
        return cardMainType;
    }

    public void setCardMainType(String cardMainType) {
        this.cardMainType = cardMainType;
    }

    public String getCardSubType() {
        return cardSubType;
    }

    public void setCardSubType(String cardSubType) {
        this.cardSubType = cardSubType;
    }

    public Integer getCardBalanceBof() {
        return cardBalanceBof;
    }

    public void setCardBalanceBof(Integer cardBalanceBof) {
        this.cardBalanceBof = cardBalanceBof;
    }

    public Integer getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(Integer tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public Integer getTradeValue() {
        return tradeValue;
    }

    public void setTradeValue(Integer tradeValue) {
        this.tradeValue = tradeValue;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getTac() {
        return tac;
    }

    public void setTac(String tac) {
        this.tac = tac;
    }

    public String getCardVerIn() {
        return cardVerIn;
    }

    public void setCardVerIn(String cardVerIn) {
        this.cardVerIn = cardVerIn;
    }

    public Integer getTestFlag() {
        return testFlag;
    }

    public void setTestFlag(Integer testFlag) {
        this.testFlag = testFlag;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public String getKeyVer() {
        return keyVer;
    }

    public void setKeyVer(String keyVer) {
        this.keyVer = keyVer;
    }

    public String getCardPublisher() {
        return cardPublisher;
    }

    public void setCardPublisher(String cardPublisher) {
        this.cardPublisher = cardPublisher;
    }

    public String getTradeMainType() {
        return tradeMainType;
    }

    public void setTradeMainType(String tradeMainType) {
        this.tradeMainType = tradeMainType;
    }

    public String getTradeSubType() {
        return tradeSubType;
    }

    public void setTradeSubType(String tradeSubType) {
        this.tradeSubType = tradeSubType;
    }

    public Integer getCardBalanceEof() {
        return cardBalanceEof;
    }

    public void setCardBalanceEof(Integer cardBalanceEof) {
        this.cardBalanceEof = cardBalanceEof;
    }

    public Integer getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(Integer overdraft) {
        this.overdraft = overdraft;
    }

    public String getLastTerminalId() {
        return lastTerminalId;
    }

    public void setLastTerminalId(String lastTerminalId) {
        this.lastTerminalId = lastTerminalId;
    }

    public String getLastTradeTime() {
        return lastTradeTime;
    }

    public void setLastTradeTime(String lastTradeTime) {
        this.lastTradeTime = lastTradeTime;
    }

    public String getLastTradeType() {
        return lastTradeType;
    }

    public void setLastTradeType(String lastTradeType) {
        this.lastTradeType = lastTradeType;
    }

    public Integer getLastTradeMoney() {
        return lastTradeMoney;
    }

    public void setLastTradeMoney(Integer lastTradeMoney) {
        this.lastTradeMoney = lastTradeMoney;
    }

    public Integer getLastTradeSn() {
        return lastTradeSn;
    }

    public void setLastTradeSn(Integer lastTradeSn) {
        this.lastTradeSn = lastTradeSn;
    }

    public String getReusingArea() {
        return reusingArea;
    }

    public void setReusingArea(String reusingArea) {
        this.reusingArea = reusingArea;
    }

    public String getTdflag() {
        return tdflag;
    }

    public void setTdflag(String tdflag) {
        this.tdflag = tdflag;
    }

    public String getCrc16() {
        return crc16;
    }

    public void setCrc16(String crc16) {
        this.crc16 = crc16;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    private Integer tdId;
    private Integer cardMediaType;
    private Integer terminalTradeSn;
    private String businessId;
    private String opterId;
    private String posId;
    private String samId;
    private Integer samTradeSn;
    private String cityCode;
    private String cardNo;
    private Integer cardTradeSn;
    private String cardMainType;
    private String cardSubType;
    private Integer cardBalanceBof;
    private Integer tradeMoney;
    private Integer tradeValue;
    private String tradeTime;
    private String tac;
    private String cardVerIn;
    private Integer testFlag;
    private String industryCode;
    private String keyVer;
    private String cardPublisher;
    private String tradeMainType;
    private String tradeSubType;
    private Integer cardBalanceEof;
    private Integer overdraft;
    private String lastTerminalId;
    private String lastTradeTime;
    private String lastTradeType;
    private Integer lastTradeMoney;
    private Integer lastTradeSn;
    private String reusingArea;
    private String tdflag;
    private String crc16;
    private Timestamp createTime;
    private String orgCode;
}
