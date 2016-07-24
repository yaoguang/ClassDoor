package com.jshs.mobile.banmen.Http;

/**
 * Created by Administrator on 2015/12/31.
 * <p/>
 * 请求URL资源
 */
public class Domain {
    // 外网服务器地址
    public static final String ENDPOINT = "http://banmen.hisan-web.com/index.php/app/";

    /**
     * 获取短信验证码
     */
    public static final String GET_SMS_CODE = "common/getSms";
    /**
     * 注册
     */
    public static final String REGIST = "user/register";
    /**
     * 登录
     */
    public static final String LOGIN = "user/login";
    /**
     * 获取用户信息
     */
    public static final String GET_USER_INFO = "user/get_userinfo";
    /**
     * 修改用户信息（不含密码）
     */
    public static final String CHANGE_USERINFO = "user/update_userinfo";
    /**
     * 修改用户信息（密码）
     */
    public static final String CHANGE_PASSWORD = "user/chang";
    /**
     * 退出登录
     */
    public static final String LOGIN_OUT = "user/logout";
    /**
     * 我的二维码
     */
    public static final String MINE_QRCODE = "user/get_pqrcode";
    /**
     * 获取用户信息
     */
    public static final String GET_CATEGORY_LIST = "category/get_category_list";
    /**
     * 升级为供应商
     */
    public static final String UPGRADE_SUPPLIER = "user/post_category_supplier";
    /**
     * 获取我的图库
     */
    public static final String GET_MINE_GALLERY = "gallery/get_gallery_mylist";
    /**
     * 获取我的下载
     */
    public static final String GET_MINE_DOWNLOAD = "user/get_down_document";


    /**
     * 查看附近的牛人
     */
    public static final String GET_AROUND_ABLEMAN = "bereally/get_distance";
    /**
     * 查看好友列表
     */
    public static final String GET_FRIENDS_LIST = "bereally/get_bereal_friends";
    /**
     * 查看好友资料
     */
    public static final String GET_FRIEND_INFO = "bereally/get_bereal_friendinfo";
    /**
     * 查看牛人资料
     */
    public static final String GET_ABLEMAN_INFO = "bereally/get_bereal_info";
    /**
     * 新的牛人
     */
    public static final String GET_NEW_ABLEMANS = "bereally/get_bereal_new";
    /**
     * 确认加为好友
     */
    public static final String COMFIRM_ADD_FRIEND = "bereally/get_bereal_enter";
    /**
     * 加为好友
     */
    public static final String ADD_FRIEND = "bereally/get_bereal_append";
    /**
     * 搜索牛人
     */
    public static final String SERACH_ABLEMAN = "bereally/get_bereal_serch";


    /**
     * 获取图库列表
     */
    public static final String GET_GALLERY_LIST = "gallery/get_gallery_list";
    /**
     * 获取图库详情
     */
    public static final String GET_GALLERY_DETAILS = "gallery/get_gallery_details";


    /**
     * 获取我的图库
     */
    public static final String GET_GALLERY_MYLIST = "gallery/get_gallery_mylist";

    /**
     * 图库-发布图片
     */
    public static final String POST_GALLERY_MYGALLERY = "gallery/post_gallery_mygallery";


}
