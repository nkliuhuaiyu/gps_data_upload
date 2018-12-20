package service;

import Utility.DBManager;
import dao.TradeDataDao;

import org.apache.ibatis.session.SqlSession;
import pojo.TradeDataDO;


import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;

public class TradeDataService {
    public static List<TradeDataDO> listDataByDeviceId(String deviceNo , Date startTime , int limit){
        SqlSession sqlSession = DBManager.sqlSessionFactory.openSession();
        try{
            TradeDataDao tradeDataDao = sqlSession.getMapper(TradeDataDao.class);
            Map<String , Object> params = new HashMap<String, Object>();
            params.put("deviceNo" , deviceNo);
            params.put("startTime" , startTime);
            params.put("limit" , limit);
            return tradeDataDao.listDatasByDeviceId(params);
        }finally {
            sqlSession.close();
        }
    }
}
