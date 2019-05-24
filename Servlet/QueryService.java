package service;

import db.DBManager;
import vo.UserInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 执行查询的业务逻辑
 */
public class QueryService {
    private Connection conn = null;
    private ResultSet rs =null;

    public List<UserInfo> queryDb(UserInfo userInfo) {
        String db_username;
        String db_password;

        List<UserInfo> userInfoList =
                new ArrayList<UserInfo>();

        String strSql = "select * from t_userinfo where username="
                + "'" + userInfo.getUsername() + "' and password = '"
                + userInfo.getPassword() + "'";
        try {
            //连接数据库
            DBManager db = new DBManager();
            conn = db.getConnection();
            rs = db.queryExecuteFun(strSql);

            while (rs.next()) {
                db_username = rs.getString("username");
                db_password = rs.getString("password");
                if (userInfo.getUsername().equals(db_username)
                        && userInfo.getPassword().equals(db_password)) {
                    userInfoList.add(userInfo);
                    return userInfoList;
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
