package pojo;

import java.sql.Date;
import java.sql.Timestamp;

public class TradeDataUploadDTO {

    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(Integer tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }
    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    private String posId;
    private String cardNo;
    private Integer tradeMoney;
    private String tradeTime;
    private String orgCode;
    private Timestamp createTime;
}
