package org.apache.commons.dbutils;

import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.Connection;

public class GameDAOTest {

    private Connection conn;

    private String url="jdbc:mysql://192.168.0.10:3306/wifi?useUnicode=true&characterEncoding=utf8";

    @Before
    public void init() {
        DbUtils.loadDriver("com.mysql.jdbc.Driver");
        try {
            conn=(Connection)DriverManager.getConnection(url, "moster", "shzygjrmdwg");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQuery() {
        QueryRunner qRunner=new QueryRunner();
        String sql="select ID, NAME, CREATED_DATE as CREATEDdATE from CHANNEL where ID = 2";
        try {
            ChannelTO channel=qRunner.query(conn, sql, new BeanHandler<ChannelTO>(ChannelTO.class));
            System.out.println(channel.getId());
            System.out.println(channel.getCreatedDate());
            String sql1="insert into CHANNEL(ID, NAME, CREATED_DATE) values(?, ?, ?)";
            // qRunner.update(conn, sql1);
            // Object[] obj = qRunner.insert(conn, sql1, new ArrayHandler(), 6, "dddd", "2013-6-2");
            // System.out.println(obj[0]);
            Object[][] params=new Object[10][3];
            for(int i=0; i < 10; i++) {
                params[i][0]=7 + i;
                params[i][1]="ljqss0" + i;
                params[i][2]="2013-02-2";
                System.out.println(params[i][0]);
            }
            qRunner.insertBatch(conn, sql1, new ArrayHandler(), params);
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

}
