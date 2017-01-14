package cn.samoye.nsfw.user.service;

import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import cn.samoye.nsfw.user.bean.User;

public interface UserService {
	void save(User user);
	void update(User user);
	void delete(Serializable id);
	User queryUserById(Serializable id);
	List<User> queryUserList();
	void exportExcelService(List<User> userList, ServletOutputStream ops);
}