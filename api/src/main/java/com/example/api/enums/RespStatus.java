package com.example.api.enums;

import java.io.Serializable;

public enum RespStatus implements Serializable {
    OK(200, "OK"),
    SERVER_ERROR(500,"服务端错误"),
    SELECT_ID_IS_NULL(50010, "查询失败，id为空"),
    SELECT_IS_NULL(50011, "查询结果为空"),
    INSERT_ID_NULL(50020, "插入失败，id为空"),
    SINGLE_INSERT_DATABASE_FAIL(50021, "数据库单条插入失败"),
    MUL_INSERT_DATABASE_FAIL(50022, "数据库批量插入失败,没有回滚"),
    DELETE_ID_IS_NULL(50030, "删除失败，id为空"),
    DELETE_DATABASE_FAIL(50031, "数据库删除操作失败"),
    UPDATE_DATABASE_FAIL(50040, "数据库更新失败"),
    FILENAME_ERROR(50050, "导入文章的文件名有误"),
    FILE_IMPORT_ERROR(50051, "导入文章失败"),
    FILE_IS_NULL(50052, "文件内容为空"),
    IS_NOT_EXCEL(50053, "文件不是Excel文件"),
    EXCEL_IS_EXIST(50054, "EXCEL文件不存在"),
    EXCEL_IS_NULL(50055, "EXCEL文件为空"),
    COUNT_PARA_IS_NULL(50060, "查询统计时参数为空"),
    ;

    private final Integer status;

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    private final String msg;

    RespStatus(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
