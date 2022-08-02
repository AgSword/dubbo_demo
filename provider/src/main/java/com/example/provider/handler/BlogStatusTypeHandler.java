package com.example.provider.handler;

import com.example.api.enums.StatusEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description 通过继承BaseTypeHandler实现自定义枚举类型的转换
 * @projectName: dubbo_demo
 * @package: com.example.provider.handler
 * @className: BlogStatusTypeHandler
 * @author: LiYinjian
 * @date: 2022/8/1 17:06
 * @version: 1.0
 */

public class BlogStatusTypeHandler extends BaseTypeHandler<StatusEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, StatusEnum statusEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,statusEnum.getCode());
    }

    /**
     * 用于定义通过字段名称获取字段数据时，如何把数据库类型转换为对应的Java类型
     * @param resultSet
     * @param s
     * @return
     * @throws SQLException
     */
    @Override
    public StatusEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int i = resultSet.getInt(s);
        if (resultSet.wasNull()){
            return null;
        }
        return StatusEnum.getStatusEnum(i);
    }

    @Override
    public StatusEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int anInt = resultSet.getInt(i);
        if (resultSet.wasNull()){
            return null;
        }
        return StatusEnum.getStatusEnum(anInt);
    }

    @Override
    public StatusEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int anInt = callableStatement.getInt(i);
        if (callableStatement.wasNull()){
            return null;
        }
        return StatusEnum.getStatusEnum(anInt);
    }
}
