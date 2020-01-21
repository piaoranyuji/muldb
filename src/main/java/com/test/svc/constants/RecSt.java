package com.test.svc.constants;

/**
 * @description 数据库记录状态码枚举
 */
public enum RecSt {
    _1("1", "有效"),
    _0("0", "无效");

    private String code, label;

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    RecSt(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public static RecSt parse(String code) {
        for (RecSt recSt : RecSt.values()) {
            if (recSt.getCode().equals(code)) {
                return recSt;
            }
        }
        return null;
    }
}
