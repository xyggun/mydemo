package com.xyggun.baidumaplibrary;

import com.xyggun.baselibrary.inject.base.enums.EnumsUtil;

/**
 * 百度地图错误枚举
 * 61 ： GPS定位结果，GPS定位成功。
 * 62 ： 无法获取有效定位依据，定位失败，请检查运营商网络或者wifi网络是否正常开启，尝试重新请求定位。
 * 63 ： 网络异常，没有成功向服务器发起请求，请确认当前测试手机网络是否通畅，尝试重新请求定位。
 * 65 ： 定位缓存的结果。
 * 66 ： 离线定位结果。通过requestOfflineLocaiton调用时对应的返回结果。
 * 67 ： 离线定位失败。通过requestOfflineLocaiton调用时对应的返回结果。
 * 68 ： 网络连接失败时，查找本地离线定位时对应的返回结果。
 * 161： 网络定位结果，网络定位定位成功。
 * 162： 请求串密文解析失败。
 * 167： 服务端定位失败，请您检查是否禁用获取位置信息权限，尝试重新请求定位。
 * 502： key参数错误，请按照说明文档重新申请KEY。
 * 505： key不存在或者非法，请按照说明文档重新申请KEY。
 * 601： key服务被开发者自己禁用，请按照说明文档重新申请KEY。
 * 602： key mcode不匹配，您的ak配置过程中安全码设置有问题，请确保：sha1正确，“;”分号是英文状态；且包名是您当前运行应用的包名，请按照说明文档重新申请KEY。
 * 501～700：key验证失败，请按照说明文档重新申请KEY。
 *
 * User: xyggun
 * Date: 2015-08-27 上午10:38
 * Version: 1.0
 */
public enum ErrorTypeEnum {
    GPS_SUCCESS(61, "GPS定位结果，GPS定位成功"),
    FAIL_62(62, "无法获取有效定位依据，定位失败，请检查运营商网络或者wifi网络是否正常开启，尝试重新请求定位"),
    FAIL_INTENT(63, "网络异常，没有成功向服务器发起请求，请确认当前测试手机网络是否通畅，尝试重新请求定位"),
    LOCATION_CACHE(65, "定位缓存的结果"),
    OFFLINE_LOCATION(66, "离线定位结果。通过requestOfflineLocaiton调用时对应的返回结果"),
    OFFLINE_FAIL(67, "离线定位失败。通过requestOfflineLocaiton调用时对应的返回结果"),
    OFFLINE_FAIL_LOCATION_RETURN(68, "网络连接失败时，查找本地离线定位时对应的返回结果"),
    INTENT_SUCCESS(161, "网络定位结果，网络定位定位成功"),
    FAILED_PARSE_QUERY_STRING(162, "请求串密文解析失败"),
    SEVER_LOCALED_FAILED(167, "服务端定位失败，请您检查是否禁用获取位置信息权限，尝试重新请求定位"),
    INVALID_KEY_PARAMS(502, "key参数错误，请按照说明文档重新申请KEY"),
    EXIST_ILLEGAL_KEY(505, "key不存在或者非法，请按照说明文档重新申请KEY"),
    DEVELOPERS_DISABLED(601, "key服务被开发者自己禁用，请按照说明文档重新申请KEY"),
    INVALID_KEY_MCODE(602, "key mcode不匹配，您的ak配置过程中安全码设置有问题，请确保：sha1正确，“;”分号是英文状态；且包名是您当前运行应用的包名，请按照说明文档重新申请KEY"),
    KEY_VALIDATION_FAILS(700, "key验证失败，请按照说明文档重新申请KEY"),;

    private String name;
    private int value;

    private ErrorTypeEnum(int value, String name) {
        this.name = name;
        this.value = value;
    }

    public static ErrorTypeEnum valueOf(int value) {
        return (ErrorTypeEnum) EnumsUtil.findEnumByFieldValue(ErrorTypeEnum.values(), "value", value);
    }

    public static ErrorTypeEnum valueOfByName(String name) {
        return (ErrorTypeEnum) EnumsUtil.findEnumByFieldValue(ErrorTypeEnum.values(), "name", name);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
