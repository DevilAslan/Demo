package cn.umbrella.wechat.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 项目中用到的一些常量  
 *
 * @ClassName: Constant  
 * @author zhou.xy
 * @date 2016年7月26日 下午2:21:32  
 *
 */
public interface Constants {
	public static final String SEARCH_TABLENAME = "tableName";// 表名
	public static final String SEARCH_COLUMNS = "columns";// 表字段
	public static final String SEARCH_COLUMN_TYPE = "columnsType";// 表字段类型
	public static final String SEARCH_COLUMN_CN = "columnCns";// 表字段中文名
	public static final String SEARCH_EN = "searchEn";// 查询名称
	public static final String DATAGRID_ROWS = "rows";// datagrid页面显示数据
	public static final String DATAGRID_TOTAL = "total";// datagrid页面显示总数
	public static final String SEARCH_SQL = "searchSql";// 查询sql
	public static final String SEARCH_COUNT_SQL = "searchCountSql";// 查询总数sql
	public static final String SEARCH_LIKE = "like";// 模糊查询
	public static final String SEARCH_PINPOINT = "pinpoint";// 精确查询
	public static final String KEYWORD = "keyword";// 关键字

	public static final String TMP_ID = "tmp_id";//临时表主键
	public static final String UID = "uid";// 主键字段
	public static final String UNAUTHORIZED = "未授权";// 对未授权字段的显示
	public static final String SPLIT_COMMA = ",";// 逗号
	
	public static final String TN = "tn";// tableName
	public static final String CT = "ct";// creditOwnerType

	public static final String CREDIT_ENTITY_CODE = "credit_entity_code";// 信用主体代码
	public static final String CREDIT_ENTITY_NAME = "credit_entity_name";// 信用主体名称
	public static final String UNIFY_SOCIETY_CODE = "unify_society_code";// 统一信用代码

	public static final String SEARCH_YEAR = "search_year_";// 年份查询
	public static final String SEARCH_START_TIME = "searchStartTime";// 开始时间查询
	public static final String SEARCH_END_TIME = "searchEndTime";// 结束时间查询
	
	public static final String MODIFY_FLAG = "modify_flag";// 修改标记字段
	public static final String MODIFY_FLAG_DESC = "修改标志（1.新增，2修改，3删除）";// 修改标记字段描述

	public static final String QUERYORDERINFO = "queryOrderInfo";// 排序
	public static final String ORDERDIRECTION = "orderDirection";// 排序方向
	public static final String ORDERCOLUMNS = "orderColumns";// 排序字段
	
	public static final String DISSENT = "DISSENT";// 异议
	public static final String COMPLAIN = "COMPLAIN";// 投诉
	
	public static final String GROUP_GUOSHUIJU = "市国税局";//国税局
	public static final String GROUP_DISHUIJU = "财政局";//地税局
	
	public static final String TEMPLATE_PREFIX = "print_";// 模板前缀
	
	public static final Map<String, String> ERRCODE = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
	    	ERRCODE.put("-1", "系统繁忙，此时请开发者稍候再试");
	        ERRCODE.put("0", "请求成功");
	        ERRCODE.put("40001", "获取access_token时AppSecret错误，或者access_token无效。请开发者认真比对AppSecret的正确性，或查看是否正在为恰当的公众号调用接口");
	        ERRCODE.put("40002", "不合法的凭证类型");
	        ERRCODE.put("40003", "不合法的OpenID，请开发者确认OpenID（该用户）是否已关注公众号，或是否是其他公众号的OpenID");
	        ERRCODE.put("40004", "不合法的媒体文件类型");
	        ERRCODE.put("40005", "不合法的文件类型");
	        ERRCODE.put("40006", "不合法的文件大小");
	        ERRCODE.put("40007", "不合法的媒体文件id");
	        ERRCODE.put("40008", "不合法的消息类型");
	        ERRCODE.put("40009", "不合法的图片文件大小");
	        ERRCODE.put("40010", "不合法的语音文件大小");
	        ERRCODE.put("40011", "不合法的视频文件大小");
	        ERRCODE.put("40012", "不合法的缩略图文件大小");
	        ERRCODE.put("40013", "不合法的AppID，请开发者检查AppID的正确性，避免异常字符，注意大小写");
	        ERRCODE.put("40014", "不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口");
	        ERRCODE.put("40015", "不合法的菜单类型");
	        ERRCODE.put("40016", "不合法的按钮个数");
	        ERRCODE.put("40017", "不合法的按钮个数");
	        ERRCODE.put("40018", "不合法的按钮名字长度");
	        ERRCODE.put("40019", "不合法的按钮KEY长度");
	        ERRCODE.put("40020", "不合法的按钮URL长度");
	        ERRCODE.put("40021", "不合法的菜单版本号");
	        ERRCODE.put("40022", "不合法的子菜单级数");
	        ERRCODE.put("40023", "不合法的子菜单按钮个数");
	        ERRCODE.put("40024", "不合法的子菜单按钮类型");
	        ERRCODE.put("40025", "不合法的子菜单按钮名字长度");
	        ERRCODE.put("40026", "不合法的子菜单按钮KEY长度");
	        ERRCODE.put("40027", "不合法的子菜单按钮URL长度");
	        ERRCODE.put("40028", "不合法的自定义菜单使用用户");
	        ERRCODE.put("40029", "不合法的oauth_code");
	        ERRCODE.put("40030", "不合法的refresh_token");
	        ERRCODE.put("40031", "不合法的openid列表");
	        ERRCODE.put("40032", "不合法的openid列表长度");
	        ERRCODE.put("40033", "不合法的请求字符，不能包含\\uxxxx格式的字符");
	        ERRCODE.put("40035", "不合法的参数");
	        ERRCODE.put("40038", "不合法的请求格式");
	        ERRCODE.put("40039", "不合法的URL长度");
	        ERRCODE.put("40050", "不合法的分组id");
	        ERRCODE.put("40051", "分组名字不合法");
	        ERRCODE.put("40060", "删除单篇图文时，指定的 article_idx 不合法");
	        ERRCODE.put("40117", "分组名字不合法");
	        ERRCODE.put("40118", "media_id大小不合法");
	        ERRCODE.put("40119", "button类型错误");
	        ERRCODE.put("40120", "button类型错误");
	        ERRCODE.put("40121", "不合法的media_id类型");
	        ERRCODE.put("40132", "微信号不合法");	        
	        ERRCODE.put("40137","不支持的图片格式");
	        ERRCODE.put("40155","请勿添加其他公众号的主页链接");
	        ERRCODE.put("41001","缺少access_token参数");
	        ERRCODE.put("41002","缺少appid参数");
	        ERRCODE.put("41003","缺少refresh_token参数");
	        ERRCODE.put("41004","缺少secret参数");
	        ERRCODE.put("41005","缺少多媒体文件数据");
	        ERRCODE.put("41006","缺少media_id参数");
	        ERRCODE.put("41007","缺少子菜单数据");
	        ERRCODE.put("41008","缺少oauth code");
	        ERRCODE.put("41009","缺少openid");
	        ERRCODE.put("42001","access_token超时，请检查access_token的有效期，请参考基础支持-获取access_token中，对access_token的详细机制说明");
	        ERRCODE.put("42002","refresh_token超时");
	        ERRCODE.put("42003","oauth_code超时");
	        ERRCODE.put("42007","用户修改微信密码，accesstoken和refreshtoken失效，需要重新授权");
	        ERRCODE.put("43001","需要GET请求");
	        ERRCODE.put("43002","需要POST请求");
	        ERRCODE.put("43003","需要HTTPS请求");
	        ERRCODE.put("43004","需要接收者关注");
	        ERRCODE.put("43005","需要好友关系");
	        ERRCODE.put("43019","需要将接收者从黑名单中移除");
	        ERRCODE.put("44001","多媒体文件为空");
	        ERRCODE.put("44002","POST的数据包为空");
	        ERRCODE.put("44003","图文消息内容为空");
	        ERRCODE.put("44004","文本消息内容为空");
	        ERRCODE.put("45001","多媒体文件大小超过限制");
	        ERRCODE.put("45002","消息内容超过限制");
	        ERRCODE.put("45003","标题字段超过限制");
	        ERRCODE.put("45004","描述字段超过限制");
	        ERRCODE.put("45005","链接字段超过限制");
	        ERRCODE.put("45006","图片链接字段超过限制");
	        ERRCODE.put("45007","语音播放时间超过限制");
	        ERRCODE.put("45008","图文消息超过限制");
	        ERRCODE.put("45009","接口调用超过限制");
	        ERRCODE.put("45010","创建菜单个数超过限制");
	        ERRCODE.put("45011","API调用太频繁，请稍候再试");
	        ERRCODE.put("45015","回复时间超过限制");
	        ERRCODE.put("45016","系统分组，不允许修改");
	        ERRCODE.put("45017","分组名字过长");
	        ERRCODE.put("45018","分组数量超过上限");
	        ERRCODE.put("45047","客服接口下行条数超过上限");
	        ERRCODE.put("46001","不存在媒体数据");
	        ERRCODE.put("46002","不存在的菜单版本");
	        ERRCODE.put("46003","不存在的菜单数据");
	        ERRCODE.put("46004","不存在的用户");
	        ERRCODE.put("47001","解析JSON/XML内容错误");
	        ERRCODE.put("48001","api功能未授权，请确认公众号已获得该接口，可以在公众平台官网-开发者中心页中查看接口权限");
	        ERRCODE.put("48002","粉丝拒收消息（粉丝在公众号选项中，关闭了“接收消息”）");
	        ERRCODE.put("48004","api接口被封禁，请登录mp.weixin.qq.com查看详情");
	        ERRCODE.put("48005","api禁止删除被自动回复和自定义菜单引用的素材");
	        ERRCODE.put("48006","api禁止清零调用次数，因为清零次数达到上限");
	        ERRCODE.put("50001","用户未授权该api");
	        ERRCODE.put("50002","用户受限，可能是违规后接口被封禁");
	        ERRCODE.put("61451","参数错误(invalid parameter)");
	        ERRCODE.put("61452","无效客服账号(invalid kf_account)");
	        ERRCODE.put("61453","客服帐号已存在(kf_account exsited)");
	        ERRCODE.put("61454","客服帐号名长度超过限制(仅允许10个英文字符，不包括@及@后的公众号的微信号)(invalid   kf_acount length)");
	        ERRCODE.put("61455","客服帐号名包含非法字符(仅允许英文+数字)(illegal character in     kf_account)");
	        ERRCODE.put("61456","客服帐号个数超过限制(10个客服账号)(kf_account count exceeded)");
	        ERRCODE.put("61457","无效头像文件类型(invalid   file type)");
	        ERRCODE.put("61450","系统错误(system error)");
	        ERRCODE.put("61500","日期格式错误");
	        ERRCODE.put("65301","不存在此menuid对应的个性化菜单");
	        ERRCODE.put("65302","没有相应的用户");
	        ERRCODE.put("65303","没有默认菜单，不能创建个性化菜单");
	        ERRCODE.put("65304","MatchRule信息为空");
	        ERRCODE.put("65305","个性化菜单数量受限");
	        ERRCODE.put("65306","不支持个性化菜单的帐号");
	        ERRCODE.put("65307","个性化菜单信息为空");
	        ERRCODE.put("65308","包含没有响应类型的button");
	        ERRCODE.put("65309","个性化菜单开关处于关闭状态");
	        ERRCODE.put("65310","填写了省份或城市信息，国家信息不能为空");
	        ERRCODE.put("65311","填写了城市信息，省份信息不能为空");
	        ERRCODE.put("65312","不合法的国家信息");
	        ERRCODE.put("65313","不合法的省份信息");
	        ERRCODE.put("65314","不合法的城市信息");
	        ERRCODE.put("65316","该公众号的菜单设置了过多的域名外跳（最多跳转到3个域名的链接）");
	        ERRCODE.put("65317","不合法的URL");
	        ERRCODE.put("9001001","POST数据参数不合法");
	        ERRCODE.put("9001002","远端服务不可用");
	        ERRCODE.put("9001003","Ticket不合法");
	        ERRCODE.put("9001004","获取摇周边用户信息失败");
	        ERRCODE.put("9001005","获取商户信息失败");
	        ERRCODE.put("9001006","获取OpenID失败");
	        ERRCODE.put("9001007","上传文件缺失");
	        ERRCODE.put("9001008","上传素材的文件类型不合法");
	        ERRCODE.put("9001009","上传素材的文件尺寸不合法");
	        ERRCODE.put("9001010","上传失败");
	        ERRCODE.put("9001020","帐号不合法");
	        ERRCODE.put("9001021","已有设备激活率低于50%，不能新增设备");
	        ERRCODE.put("9001022","设备申请数不合法，必须为大于0的数字");
	        ERRCODE.put("9001023","已存在审核中的设备ID申请");
	        ERRCODE.put("9001024","一次查询设备ID数量不能超过50");
	        ERRCODE.put("9001025","设备ID不合法");
	        ERRCODE.put("9001026","页面ID不合法");
	        ERRCODE.put("9001027","页面参数不合法");
	        ERRCODE.put("9001028","一次删除页面ID数量不能超过10");
	        ERRCODE.put("9001029","页面已应用在设备中，请先解除应用关系再删除");
	        ERRCODE.put("9001030","一次查询页面ID数量不能超过50");
	        ERRCODE.put("9001031","时间区间不合法");
	        ERRCODE.put("9001032","保存设备与页面的绑定关系参数错误");
	        ERRCODE.put("9001033","门店ID不合法");
	        ERRCODE.put("9001034","设备备注信息过长");
	        ERRCODE.put("9001035","设备申请参数不合法");
	        ERRCODE.put("9001036","查询起始值begin不合法");
	    }
	};
}
