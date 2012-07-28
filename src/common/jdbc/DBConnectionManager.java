package common.jdbc;

import java.sql.Connection;
import java.sql.SQLException;


import java.sql.Connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;


/**
 *單例模式獲取数据库连接对象类.(目前沒有使用)
 * 
 * @author 胡圣朗
 * 
 */
public final class DBConnectionManager {

	private static DBConnectionManager instance;  
	  
    private ComboPooledDataSource ds;  
  
    private DBConnectionManager() throws Exception {  
        ds = new ComboPooledDataSource();  
        ds.setDriverClass("com.mysql.jdbc.Driver");
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/ecmeal");
        ds.setUser("root");
        ds.setPassword("hushenglang");
  
        //初始化时获取三个连接，取值应在minPoolSize与maxPoolSize之间。Default: 3 initialPoolSize  
        ds.setInitialPoolSize(3);  
        //连接池中保留的最大连接数。Default:  15 maxPoolSize  
        ds.setMaxPoolSize(100);  
          
        //// 连接池中保留的最小连接数。  
        //ds.setMinPoolSize(1);  
          
        //当连接池中的连接耗尽的时候c3p0一次同时获取的连接数。Default: 3 acquireIncrement    
        ds.setAcquireIncrement(1);  
  
        //每60秒检查所有连接池中的空闲连接。Default: 0  idleConnectionTestPeriod  
        ds.setIdleConnectionTestPeriod(60);  
         
        //最大空闲时间,25000秒内未使用则连接被丢弃。若为0则永不丢弃。Default: 0  maxIdleTime  
        ds.setMaxIdleTime(25000);  
        //连接关闭时默认将所有未提交的操作回滚。Default: false autoCommitOnClose  
        ds.setAutoCommitOnClose(true);  
  
        //如果设为true那么在取得连接的同时将校验连接的有效性。Default: false  testConnectionOnCheckin  
        ds.setTestConnectionOnCheckin(true);  
  
        //定义在从数据库获取新连接失败后重复尝试的次数。Default: 30  acquireRetryAttempts  
        ds.setAcquireRetryAttempts(30);  
        //两次连接中间隔时间，单位毫秒。Default: 1000 acquireRetryDelay  
        ds.setAcquireRetryDelay(1000);  
        //获取连接失败将会引起所有等待连接池来获取连接的线程抛出异常。但是数据源仍有效  
        //保留，并在下次调用getConnection()的时候继续尝试获取连接。如果设为true，那么在尝试  
        //获取连接失败后该数据源将申明已断开并永久关闭。Default: false  breakAfterAcquireFailure  
        ds.setBreakAfterAcquireFailure(true);  

    }  
  
    public  static  final DBConnectionManager getInstance() {  
        if (instance == null) {  
            try {  
                instance = new DBConnectionManager();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return instance;  
    }  
  
    public synchronized   final Connection getConnection() {  
        try {  
            return ds.getConnection();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    protected void finalize() throws Throwable {  
        DataSources.destroy(ds); //关闭datasource  
        super.finalize();  
    }  
	
}
