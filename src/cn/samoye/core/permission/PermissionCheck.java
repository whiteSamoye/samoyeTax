package cn.samoye.core.permission;

import cn.samoye.nsfw.user.bean.User;

public interface PermissionCheck {
	boolean isAccessible(User user,String code);
}
