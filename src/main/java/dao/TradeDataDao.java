package dao;

import org.apache.ibatis.annotations.Mapper;
import pojo.TradeDataDO;

import java.util.List;
import java.util.Date;
import java.util.Map;

@Mapper
public interface TradeDataDao {
    List<TradeDataDO> listDatasByDeviceId(Map<String , Object> params);
}
