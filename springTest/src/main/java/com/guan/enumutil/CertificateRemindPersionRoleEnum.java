package com.guan.enumutil;

/**
 * 证照提醒人角色枚举
 * @Author : guantenghua
 * @create 2020/6/3 18:14
 */
public enum CertificateRemindPersionRoleEnum {
    POSSESSOR("1", "持有人"),
    MANAGER("2", "持有人部门主管"),
    SECONDMANAGER("3", "持有人部门副主管"),
    DIRECTSUPERVISOR("4", "持有人直属上级"),
    UPLEVELMANAGER("5", "持有人上一级部门主管"),
    UPLEVELSECONDMANAGER("6", "持有人上一级部门副主管"),
    ADMINISTRATOR("7", "企业管理员"),
    CREATOR("8", "企业创建者"),
    ;
    private String value;
    private String desc;

    CertificateRemindPersionRoleEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }
}
