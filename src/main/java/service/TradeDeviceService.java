package service;

import Utility.DBManager;
import Utility.RestfulUtility;
import dao.TradeDeviceDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import pojo.TradeDeviceDO;

import java.util.List;

public class TradeDeviceService {
    /*
     * extract from official document of mybatis.org
     *
     * Therefore the best scope of SqlSessionFactory is application scope
     * Instances of SqlSession are not to be shared and are not thread safe
     */
    public static Logger logger = Logger.getLogger(RestfulUtility.class);
    public static List<TradeDeviceDO> listAllDevices(){
        SqlSession sqlSession = DBManager.sqlSessionFactory.openSession();

        try{
            TradeDeviceDao tradeDeviceDao = sqlSession.getMapper(TradeDeviceDao.class);
            return tradeDeviceDao.listAllDevices();
        }finally {
            sqlSession.close();
        }
    }
}
