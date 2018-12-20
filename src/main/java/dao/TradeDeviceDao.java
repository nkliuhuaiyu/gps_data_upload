package dao;

import org.apache.ibatis.annotations.Mapper;
import pojo.TradeDeviceDO;

import java.util.List;

@Mapper
public interface TradeDeviceDao {
    List<TradeDeviceDO> listAllDevices();
}
