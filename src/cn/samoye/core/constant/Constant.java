package cn.samoye.core.constant;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	public static  String USER = "SYS_USER";
	/*------------------------------------------------------------------*/
	/**
	 * 1.本次没有做权限模块的增删改查,直接在jsp页面写死全限,后期权限名称改变后,修改比较麻烦.并且在数据库中直接保存数据权限名称会存在历史记录问题(后来命名和开始命名重名)
	 * 使用常量方式,并且定义常量集合,直接把常量集合传到jsp页面(列表,新增,修改),在jsp页面,可以直接使用,以后修改权限名称,直接在此处修改.并且数据库中存放的是权限的标识符,而不是
	 * 权限名称.这样不会存在历史记录问题
	 */
	public static String PRIVILEGE_XZGL="xzgl";//行政管理
	public static String PRIVILEGE_HQFW="hqfw";//后勤服务
	public static String PRIVILEGE_ZXXX="zxxx";//在线学习
	public static String PRIVILEGE_NSFW="nsfw";//纳税服务
	public static String PRIVILEGE_SPACE="space";//我的空间
	public static Map<String,String> PRIVILEGE_MAP;
	static{
		PRIVILEGE_MAP = new HashMap<String,String>();
		PRIVILEGE_MAP.put(PRIVILEGE_XZGL, "行政管理");
		PRIVILEGE_MAP.put(PRIVILEGE_HQFW, "后勤服务");
		PRIVILEGE_MAP.put(PRIVILEGE_ZXXX, "在线学习");
		PRIVILEGE_MAP.put(PRIVILEGE_NSFW, "纳税服务");
		PRIVILEGE_MAP.put(PRIVILEGE_SPACE, "我的空间");
	}
}
