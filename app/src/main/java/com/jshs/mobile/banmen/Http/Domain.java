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
     * 获取用户信息
     */
    public static final String GET_CATEGORY_LIST = "category/get_category_list";


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
    public static final String POST_GALLERY_MYGALLERY= "gallery/post_gallery_mygallery";



}
