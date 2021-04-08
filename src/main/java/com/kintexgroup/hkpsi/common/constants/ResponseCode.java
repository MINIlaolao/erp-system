package com.kintexgroup.hkpsi.common.constants;

/**
 * @author gradylo
 * @author lmao
 */
public enum ResponseCode {

    /**
     * 公共返回码
     */
    FAIL(-1, "系统繁忙"),
    SUCCESS(0, "请求成功"),

    //数据库 英文缩写	DB
    DB_SYSTEM_ERROR(30000, "数据库系统异常!"),
    DB_INSERT_ERROR(31003, "创建记录错误"),
    DB_INSERT_DUPLICATE_ERROR(31103, "创建记录错误,重复创建!"),
    DB_UPDATE_DUPLICATE_ERROR(31203, "更新记录错误,重复更新!"),
    DB_DUPLICATE_ERROR(31100, "操作记录错误,重复数据!"),
    DB_UPDATE_ERROR(31004, "更新记录错误"),
    DB_TYPE_ERROR(31010, "无效参数,请确认数据库中是否存在相应记录"),
    DB_SELECT_ERROR(31005, "查询记录错误"),
    DB_SELECT_NULL_ERROR(31015, "查询记录错误,数据库无记录!"),
    DB_DELETE_ERROR(31006, "删除记录错误"),
    DB_ID_NULL_ERROR(32000, "数据库ID无记录"),

    /**
     * 业务分类
     */

    DB_PHONE_NOT_CONFORM(31101, "输入的电话号码无效，格式不正确"),

    DB_PARAM_NOT_EXIST(31100, "数据库中的参数不存在"),
    //业务 英文缩写	BIZ
    BIZ_GOODS_SOLD(21000, "商品已被售出!"),
    BIZ_LOGISTIC_NUMBER_ERROR(21010, "物流单号错误!"),
    BIZ_WON_DATE_IS_EARLY_THAN_BID_DATE(21007, "中标日期不能早于投标日期!"),
    BIZ_HAVE_BRAND_SPU(21002, "此品牌下还有商品，不可删除此品牌!"),
    BIZ_HAVE_VENDOR_SPU(21003, "此供应商下还有商品，不可删除此供应商!"),
    BIZ_HAVE_CATEGORY_SPU(21004, "此分类下还有商品，不可删除此分类!"),
    BIZ_HAVE_SKU_SPU(21005, "此SPU下还有SKU，不可删除此SPU!"),
    BIZ_SPU_NO_CHANGE(21006, "没做修改"),
    BIZ_SALES_ORDER_PADDED(21008, "订单已付款!"), BIZ_BID_IS_SHIPPED(21009, "该批次已发货！"),

    BIZ_INVENTORY_INSUFFICIENT(21001, "商品数量不足!"),
    BIZ_BIDS_AWARDED(21101, "批次已经中标!"),
    BIZ_NO_MATCH_CARTON(21102, "数据库无此记录"),

    TOO_MANY_REQUESTS(429, "Too Many Requests"),

    REQUEST_PARAM_INVALID(4000, "参数无效"),
    INSERT_PARAM_INVALID(33000, "参数效验失败!"),

    SYSTEM_NULL_POINTER(40001, "NULL_POINTER"),


    /**
     * 获取当前用户失败
     */
    CURRENT_USER_FAIL(10001, "获取当前用户失败"),
    UPDATE_USER_STATUS(10002, "用户是超级管理员，不可以修改状态"),
    UPDATE_USER_PASSWORD(10003, "用户是超级管理员，不可以修改密码"),

    /**
     * 登录
     */
    USER_NEED_LOGIN(11001, "用户未登录，请登陆后进行访问"),
    USER_MAX_LOGIN(11002, "该用户已在其它地方登录"),
    USER_LOGIN_TIMEOUT(11003, "长时间未操作，自动退出"),
    USER_DISABLED(11004, "用户被禁11005用"),
    USER_LOCKED(11005, "用户被锁定"),
    USER_PASSWORD_ERROR(11006, "用户名或密码错误"),
    USER_PASSWORD_EXPIRED(11007, "用户密码过期"),
    USER_ACCOUNT_EXPIRED(11008, "用户账号过期"),
    USER_NOT_EXIST(11009, "没有该用户"),
    USER_LOGIN_FAIL(11010, "用户登录失败"),
    VERIFY_CODE_ERROR(11011, "验证码错误"),
    USER_IS_EXIST(11012, "用户已存在"),
    USERNAME_IS_NULL(11013, "用户名为空"),
    USER_PASSWORD_IS_NULL(11014, "用户密码为空"),
    NO_AUTHENTICATION(1003006, "无权访问"),

    // 角色管理
    ROLE_IS_NOT_EXIST(13001, "角色ID无效"),
    ROLE_IS_EXIST(13002, "角色代码已存在"),

    // 配置管理
    CONFIG_ID_IS_NOT_EXIST(14001, "配置信息为空"),
    CONFIG_IS_NOT_EXIST(14002, "配置ID无效"),
    CONFIG_IS_EXIST(14002, "配置ID已存在"),
    CONFIG_IS_SYSTEM(14003, "系统配置不允许修改"),
    CONFIG_IS_NOT_DELETE(14003, "系统配置不允许删除"),

    // 文件系统
    FILE_DOES_NOT_EXIST(16001, "文件不存在"),
    FILE_UPLOAD_EXCEPTION(16002, "文件上传异常"),
    FILE_DOWNLOAD_ABNORMAL(16003, "文件下载异常"),

    // 资源
    RESOURCE_NOT_FIND(12001, "无效的资源ID"),
    RESOURCE_IS_EXIST(12001, "资源ID已存在"),
    RESOURCE_PARENT_NOT_FIND(12002, "无效资源父节点ID"),
    RESOURCE_PARENT_INVALID(12003, "无效资源父节点ID"),
    RESOURCE_HAVE_SUB(12004, "该资源下有子资源，不能删除"),
    // 机构管理
    ORG_IS_EXIST(17001, "机构已存在"),
    ORG_NOT_EXIST(17002, "机构不存在"),
    ORG_HAVE_USER(17003, "机构下存在用户"),
    ORG_PID_ERROR(17004, "无效机构父节点ID"),
    ORG_TOP_FORBID(17005, "父级节点禁止删除"),
    ORG_HAVE_BRANCH(17006, "机构下存在子机构"),
    ORG_STOP_REASON(17007, "停用原因不能为空"),

    // 字典管理
    DICT_PID_ERROR(18001, "父级ID无效"),
    DICT_ID_ERROR(18002, "ID无效"),
    DICT_CODE_EXIST(18003, "字典code已存在"),
    DICT_NAME_EXIST(18004, "字典name已存在"),
    DICT_HAVE_DATA(18005, "字典下存在数据"),
    DICT_NOT_EXIST(18006, "字典不存在"),
    DICT_HAVE_SON(18007, "存在子节点"),
    // 数据组
    GROUP_ID_ERROR(19001, "数据组信息不存在"),
    GROUP_INIT_DATA_ERROR(19002, "数据组初始化无机构信息"),

    CRUD_PARAM_NOT_EXIST(11000, "参数不能为空!"),
    CRUD_NAME_NOT_EXIST(11001, "用户名不能为空!"),
    CRUD_INPUT_VALUE_NOT_EXIST(11002, "传入的参数不能为空!"),
    CRUD_INPUT_ID_NOT_EXIST(11003, "传入的id不能为空!");
    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
