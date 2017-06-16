package com.thinkgem.jeesite.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目常量类
 * 
 * @author duanshao
 * 
 */
public interface Consts {

	String UTF8 = "utf-8";
	String PRODUCES = "application/json;charset=utf-8";
	/** 存活时间为1天 */
	int DAY_AGE = 24 * 60 * 60;
	/** 存活时间为1小时 */
	int HOURE_AGE = 60 * 60;
	// code转市名称
	Map<Long, String> CODE_CITY = new HashMap<Long, String>();
	Map<String, String> CODE_SCHOOL = new HashMap<String, String>();

	Map<Long, String> CODE_PROVINCE = new HashMap<Long, String>() {
		private static final long serialVersionUID = 190232797062935098L;
		{
			put(110000L, "北京市");
			put(120000L, "天津市");
			put(130000L, "河北省");
			put(140000L, "山西省");
			put(150000L, "内蒙古自治区");
			put(210000L, "辽宁省");
			put(220000L, "吉林省");
			put(230000L, "黑龙江省");
			put(310000L, "上海市");
			put(320000L, "江苏省");
			put(330000L, "浙江省");
			put(340000L, "安徽省");
			put(350000L, "福建省");
			put(360000L, "江西省");
			put(370000L, "山东省");
			put(410000L, "河南省");
			put(420000L, "湖北省");
			put(430000L, "湖南省");
			put(440000L, "广东省");
			put(450000L, "广西壮族自治区");
			put(460000L, "海南省");
			put(500000L, "重庆市");
			put(510000L, "四川省");
			put(520000L, "贵州省");
			put(530000L, "云南省");
			put(540000L, "西藏自治区");
			put(610000L, "陕西省");
			put(620000L, "甘肃省");
			put(630000L, "青海省");
			put(640000L, "宁夏回族自治区");
			put(650000L, "新疆维吾尔自治区");
			put(710000L, "台湾省");
			put(810000L, "香港特别行政区");
			put(820000L, "澳门特别行政区");
		}

	};

	// 环信的常量
	interface HXCONSTS {
		String TOKEN = "edg_hx_token";
	}

	// redis前缀
	interface Cache {
		String USER_PREFIX = "esportc_user_";
		String NEWS_PREFIX = "esportc_news_";
	}

	interface Key {
		String SIGN = "123456";
	}

	// 表前缀
	interface TablePrefix {
		String USER = "user_";
		String ALBUM = "album_";
		String FRIENDSHIP = "friendship_";
		String INTRO = "intro_";
		String DISCUSSION_MEMBER = "discussion_member_";
		String POSTS = "posts_";
		String REPLY = "reply_";
		String POSTS_ALBUM = "posts_album_";
		String RED_ENVELOPE_LOG_ = "red_envelope_log_";
		String COMMENTS = "comments_";
	}

	interface Roaming {
		Integer OUTTIME = 86400;
		Integer TIME = 3600000;
		Integer SETTIME = 3600;
	}

	// redis前缀
	interface RedisPrefix {
		String ROAM = "roam_";
		String ROAM_TIME = "roam_time_";
		String CITY = "city_";
	}

	interface Action {
		// 打招呼
		String HI = "hi";

		// 方言打招呼
		String DIALECT = "dialect";
		// 帖子互动
		String POSTS_REPLY = "posts_reply";
		// 匹配
		String MATCH = "match";

		// 不匹配
		String UNMATCH = "unmatch";
	}

	// 通用状态码
	interface Code {

		/** 公用的返回码 */
		// 成功
		int SUCCESS = 0;
		// 失败
		int FAIL = -1;
		// 签名错误
		int SIGN_ERROR = 1;
		// 网络链接错误
		int NET_CONN_ERROR = 2;

		// 安全错误
		int SECURE_ERROR = 3;
		// 内部错误,发生异常等
		int INTERNAL_ERROR = 4;
		// 参数错误
		int DATA_ERROR = 5;
		// 请求错误，参数不足
		int REQ_ERROR = 6;
		// 业务信息错误
		int APP_INFO_ERROR = 7;
		// 数据不存在
		int DATA_NO_FOUND = 8;

		// 网络响应错误
		int NET_READ_ERROR = 9;

		// 已点过赞
		int LIKED = 10;

		// 验证码超时
		int CODE_TIMEOUT = 11;

		/** 注册接口方法特定的返回码 1000 */
		// 用户注册.无效密码
		int USER_REGISTE_PWD_INVALID = 1001;
		// 用户注册.无效手机
		int USER_REGISTE_PHONE_INVALID = 1002;
		// 用户注册.无效验证码
		int VERIFY_CODE_INVALID = 1003;
		// 用户注册.无效昵称
		int USER_REGISTE_NICK_INVALID = 1004;
		// 用户注册.无效生日
		int USER_REGISTE_BIRTH_INVALID = 1005;
		// 用户注册.无效省下标
		int USER_REGISTE_PROVINCE_INVALID = 1006;
		// 用户注册.无效市下标
		int USER_REGISTE_CITY_INVALID = 1007;
		// 用户注册.无效县区下标
		int USER_REGISTE_DISTRICE_INVALID = 1008;
		// 用户注册.无效性别
		int USER_REGISTE_SEX_INVALID = 1009;
		// 用户注册.手机号码已经存在
		int USER_REGISTE_PHONE_EXISTS = 1010;
		// 搜索用户.是自己本身
		int USER_SEARCH_EXIST = 1011;
		// 用户出生日期不能大于当前年份
		int USER_REGISTE_AGE_INVALID = 1012;
		// 用户注册.email已经存在
		int USER_REGISTE_EMAIL_EXISTS = 1013;
		/** 登录接口方法特定的返回码 2000 */
		// 用户登录.密码不正确
		int USER_LOGIN_FAIL_INVALID = 2001;
		// 用户登录.无效帐号
		int USER_LOGIN_USERNAME_INVALID = 2002;
		// 用户登录.无效密码
		int USER_LOGIN_PASSWORD_INVALID = 2003;
		// 用户登陆 ,用户未注册
		int USER_LOGIN_PHONE_NOT_REG = 2000;

		// 用户被禁止登录
		int USER_LOGIN_FORBIDDEN = 2004;
		// 用户未绑定
		int USER_NO_BOUND = 2005;
		// 绑定失败
		int USER_BOUND_FAIL = 2006;
		// 绑定成功
		int USER_BOUND_SUCCESS = 2007;

		/** 修改用户资料方法特定的返回码 3000 */
		// 修改用户.无效年龄
		int USER_SET_AGE_INVALID = 3001;
		// 修改用户.无效生日
		int USER_SET_BIRTH_INVALID = 3002;
		// 修改用户.无效市区下标
		int USER_SET_CITY_INVALID = 3003;
		// 修改用户.无效公司格式
		int USER_SET_COMPANY_INVALID = 3004;
		// 修改用户.无效星座下标
		int USER_SET_CONSTELL_INVALID = 3005;
		// 修改用户.无效县区下标
		int USER_SET_DISTRICT_INVALID = 3006;
		// 修改用户.无效学校
		int USER_SET_SCHOOL_INVALID = 3007;
		// 修改用户.无效兴趣
		int USER_SET_HOBBY_INVALID = 3008;

		// 不存在关系
		int RELATION_NOT_EXITS = 3009;

		// 用户不存在
		int USER_NOT_EXISTS = 4001;
		// 密码与原密码相同
		int SAME_PASSWORD = 4002;
		// 搜索自己
		int SEARCH_YOURSELF = 4003;

		// 漫游超时
		int ROAMING_OUTTIME = 5001;
		// 正在漫游
		int ROAMING_GOING = 5002;
		// 没有此用户
		int ROAMING_NULL = 5003;

		// 已经未点过赞
		int POSTS_NOT_LIKED = 6000;
		// 已经点过赞
		int POSTS_LIKED = 6001;
		// 帖子不存在
		int POSTS_NOT_EXIST = 6002;
		// 回复不存在
		int REPLY_NOT_EXIST = 6003;
		// 收藏过
		int POSTS_FAVORITED = 6004;
		// 帖子已发布
		int POSTS_PUBLISHED = 6005;
		// 禁止评论
		int POSTS_FORBIDDEN_REPLY = 6006;
		// 帖子已被删除
		int POSTS_DELETED = 6007;

		int HXSERVICE_SET_PASSWORD_FAIL = 9001;
		// 已举报过
		int REPORTED = 9002;
		// 帖子被举报次数达到限制
		int OVER_LIMITED = 9003;

		// 举报次数
		int REPORT_LIMITED = 10;

		// 身份证错误
		int AUTH_NAME_CID_ERROR = 9005;
		// 学校学历错误
		int AUTH_SHCOOL_GRADE_ERROR = 9006;
		// 认证服务发生错误，可以稍后重试
		int AUTH_SYS_ERROR = 9007;
		// 信息已经被认证过
		int AUTH_HAS_EXIST = 9008;

		// 今天剩余认证次数0次
		int AUTH_REMAIN_0 = 9010;
		// 今天剩余认证次数1次
		int AUTH_REMAIN_1 = 9011;
		// 今天剩余认证次数2次
		int AUTH_REMAIN_2 = 9012;
		// 一小时内的认证次数用完了
		int AUTH_FORBIDDEN_HOUR = 9013;
		// 一天内的认证次数用完了
		int AUTH_FORBIDDEN_DAY = 9014;

		// 充值中
		int RECHARGE_ING = 9100;
		// 充值订单还未支付
		int RECHARGE_UNPAY = 9101;
		// 充值订单已走退款流程
		int RECHARGE_REVERSE = 9102;
		// 支付密码错太多次了
		int PAY_PWD_WRONG_TOO_MANY_TIMES = 9120;
		// 支付密码错误
		int PAY_PWD_WRONG = 9121;
		// 账户余额不足
		int BALANCE_NOT_ENOUGH = 9122;
		// 运气不好，下次再来
		int BAD_LUCKY = 9140;
		// 运气不好，红包已过期
		int BAD_EXPRIED_TIME = 9141;

		// 邮箱已经申请过邀请码
		int INVITATION_EMAIL_APPLYED = 9150;
		// 邀请码不存在
		int INVITATION_NOT_EXIST = 9151;
		// 邀请码无效
		int INVITATION_INVALID = 9152;

	}

	// 表分区的数量
	interface TablePartition {
		int USER = 8;
		int ALBUM = 8;
		int FRIENDSHIP = 8;
		int INTRO = 8;
		int DISCUSSION_MEMBER = 8;
		int REPLY = 4;
		int POSTS = 34;
		int POSTS_ALBUM = 34;

		int RED_ENVELOPE_LOG = 8;
		int COMMENTS = 8;
	}

	// 环信请求方法
	interface HTTPMethod {
		String METHOD_GET = "GET";
		String METHOD_POST = "POST";
		String METHOD_PUT = "PUT";
		String METHOD_DELETE = "DELETE";
	}

	interface Roles {
		/** USER_ROLE_ORGADMIN value: orgAdmin */
		public static String USER_ROLE_ORGADMIN = "orgAdmin";

		/** USER_ROLE_APPADMIN value: appAdmin */
		public static String USER_ROLE_APPADMIN = "appAdmin";

		/** USER_ROLE_IMUSER value: imUser */
		public static String USER_ROLE_IMUSER = "imUser";
	}

	/** Cookie */
	interface Cookies {
		/** 定义cookie名字 */
		static final String COOKIE_ADMIN = "cookie_admin";
		/** 定义验证码保存在session的字符串 */
		static final String VERIFY_CODE = "verify_code";
		/** 定义cookie存活时间为: 一周 */
		static final int COOKIE_MAX_AGE = 7 * 24 * 60 * 60;
	}

	static final String USER_SESSION = "user_session";

	/**
	 * 设置附近的人搜索范围(单位:km)
	 */
	interface dist {
		double TRACE = 6000;
	}

	/**
	 * 设备信息
	 */
	interface Device {
		String IOS = "ios";
		String ANDROID = "android";
	}

	/**
	 * 发帖权限
	 */
	interface PostsPrivilege {
		String LEVEL_PREFIX = "user_posts_level_";
		String LEVEL_ZERO = "0";// 正常发帖
		String LEVEL_ONE = "1";// 禁止发帖一周
		String LEVEL_TWO = "2";// 永远禁止发帖
	}

	/**
	 * 推送信息类型
	 */
	interface MsgType {
		String TEXT = "txt";
		String IMG = "img";
		String AUDIO = "audio";
		String VIDEO = "video";
		String POSTS = "posts";
		/** 定义消息存活时间为1周 */
		int MAX_AGE = 7 * 24 * 60 * 60;
	}

	/**
	 * HTTP请求方式
	 * 
	 */
	interface Method {
		String POST = "POST";
		String GET = "GET";
		String DELETE = "DELETE";
		String PUT = "PUT";
	}

	// 省份权重分组
	interface Percent {
		int HIGHT = 60;
		int MIDDLE = 30;
		int LOWLINESS = 10;

		int HIGHTCOUNT = 14;
		int MIDDLECOUNT = 13;
		int LOWLINESSCOUNT = 13;
	}

	// 虚拟用户
	interface VirtualPhone {
		String PHONE = "14400";
	}

	/**
	 * 账户交易状态码
	 * 
	 * @author duanshao
	 * 
	 */
	interface TradeCode {
		int PARAM_INVALID = 100;// 参数错误
		int ERROR_INSERT = 101;// 插入错误
		int ERROR_UPDATE_NONE = 102;// 更新记录错误
		int SYS_ERROR = 104;// 系统错误
		int APP_INVALID = 105; // app info信息无效
		int APP_INTERFACE_INVALID = 106; // appinfo与interface不匹配
		int APP_IP_INVALID = 107; // appinfo与ipwhite不匹配
		int AMOUNT_LIMIT_ERROR = 108;// 金额超出限制
		int VISITS_LIMIT_ERROR = 109;// 接口每秒允许访问并发数限制

		int TRADE_REPEAT = 200;// 重复交易,可以认为是上一次的交易已成功
		int TRADE_ILLEGAL = 201;// 交易非法,如凭证无效
		int TRADE_NOT_EXIST = 202;// 交易不存在
		int TRADE_HAS_REVERSED = 203;// 交易已被冲正过
		int TRADE_REVERSED_NOT_ENOUGH = 204; // 冲正充值操作失败,钱不够
		int TRADE_REVERSED_FAIL = 205; // 冲正失败
		int TRADE_REVERSED_NOT_SUPPORT = 206;// 不支持冲正

		int ACCOUNT_FROZEN = 300;// 账户已被冻结
		int ACCOUNT_BALANCE_NOT_ENOUGH = 301; // 账户余额不足
		int ACCOUNT_NOT_EXIST = 302;// 账户不存在
		int ACCOUNT_INVALID = 303;// 账户无效
	}

	/**
	 * 账户交易类型
	 * 
	 * @author duanshao
	 * 
	 */
	interface TradeType {
		String RECHARGE = "recharge";
		String CONSUME = "consume";
		String REVERSE = "reverse";
	}

	/**
	 * 账户状态
	 * 
	 * @author duanshao
	 * 
	 */
	interface AccountState {
		int NORMAL = 0;
		int FROZEN = 1;
	}

	/**
	 * 账户交易日志类型
	 * 
	 * @author duanshao
	 * 
	 */
	interface AccountLogState {
		int NORMAL = 0;
		int REVERSE = 1;
	}

	/**
	 * 支付订单相关的状态
	 * 
	 */
	interface PayState {
		/**
		 * 支付中
		 */
		int UNPAY = 0;
		/**
		 * 已取消
		 */
		int CANCELED = 50;
		/**
		 * 支付异常
		 */
		int PAY_ERROR = 100;
		/**
		 * 支付失败
		 */
		int PAY_FAIL = 120;
		/**
		 * 支付部分
		 */
		int PAY_PART = 150;

		/**
		 * 支付完成
		 */
		int PAYED = 200;

		/**
		 * 已申请退款
		 */
		int REFUND_APPLYED = 510;
		/**
		 * 退款申请不通过
		 */
		int REFUND_APPLY_UNPASS = 550;
		/**
		 * 退款申请通过
		 */
		int REFUND_APPLY_PASS = 590;
		/**
		 * 退款中
		 */
		int REFUNDING = 600;
		/**
		 * 退款异常
		 */
		int REFUND_ERROR = 610;
		/**
		 * 退款失败
		 */
		int REFUND_FAIL = 630;
		/**
		 * 退款部分
		 */
		int REFUND_PART = 660;
		/**
		 * 退款成功
		 */
		int REFUNDED = 690;

		/**
		 * 冲正中
		 */
		int REVERSING = 700;
		/**
		 * 冲正异常
		 */
		int REVERSE_ERROR = 710;
		/**
		 * 冲正失败
		 */
		int REVERSE_FAIL = 730;
		/**
		 * 冲正部分
		 */
		int REVERSE_PART = 760;
		/**
		 * 冲正成功
		 */
		int REVERSED = 790;
	}

	/**
	 * 业务订单相关的状态
	 */
	interface OrderState {

		/**
		 * 未支付
		 */
		int UNPAY = 0;
		/**
		 * 已取消
		 */
		int CANCELED = 50;
		/**
		 * 支付异常
		 */
		int PAY_ERROR = 100;
		/**
		 * 支付失败
		 */
		int PAY_FAIL = 120;
		/**
		 * 支付部分
		 */
		int PAY_PART = 150;
		/**
		 * 订单转正（假设支付完成）
		 */
		int PAY_MOCK = 180;
		/**
		 * 已支付
		 */
		int PAYED = 200;

		/**
		 * 支付的后续动作已处理
		 */
		int PAY_SURED = 400;

		/**
		 * 冲正中
		 */
		int REVERSING = 700;
		/**
		 * 冲正异常
		 */
		int REVERSE_ERROR = 710;
		/**
		 * 冲正失败
		 */
		int REVERSE_FAIL = 730;
		/**
		 * 冲正部分
		 */
		int REVERSE_PART = 760;
		/**
		 * 冲正成功
		 */
		int REVERSED = 790;

		/**
		 * 已申请退款
		 */
		int REFUND_APPLYED = 810;
		/**
		 * 退款申请不通过
		 */
		int REFUND_APPLY_UNPASS = 820;
		/**
		 * 退款申请通过
		 */
		int REFUND_APPLY_PASS = 830;
		/**
		 * 退款中
		 */
		int REFUNDING = 840;
		/**
		 * 退款异常
		 */
		int REFUND_ERROR = 850;
		/**
		 * 退款失败
		 */
		int REFUND_FAIL = 860;
		/**
		 * 退款部分
		 */
		int REFUND_PART = 870;
		/**
		 * 退款成功
		 */
		int REFUNDED = 890;
	}

	interface PayType {
		String CASH = "cash";
	}
	
	interface SourceUserUploadCode {
	    int SUCCESS = 0;
	    int PARAMS_INVALID = 1;
	    int SOURCE_CONFIG_NOT_EXSIT = 2; //不存在
	}
	
	interface SourceConfigCode {
	    int SUCCESS = 0;
	    int PARAMS_INVALID = 1;
	    int SOURCE_CONFIG_NOT_EXSIT = 2; //不存在
	}
	
	interface SourceUserType {
	    int CLICK_IMAGE = 0; //点击图片
	    int IN_DOWNLOAD = 1; //进入下载页面
	    int DOWNLOAD_COMPLETE = 3; //下载完成
	    int OPEN = 4; //打开游戏
	}
	
	interface SourceDateType {
	    int ALL = 0; //总计
	    int DAY = 1; //天
	}
	
	interface SourceConfigStatus {
	    int OFF = 0; //下架
	    int ON = 1; //上架
	}
	
	interface Category {
		/**
		 * 头条
		 */
		int TYPE_TOP = 8;
	}
}
