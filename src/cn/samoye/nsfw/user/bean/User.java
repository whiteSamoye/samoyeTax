package cn.samoye.nsfw.user.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String dept;
	private String account;
	private String password;
	private String headImg;
	private boolean gender;
	private String email;
	private String state;
	private String mobile;
	private Date birthday;
	private String memo;
	private List<UserRole> userRole;
	//状态常量:
	// TODO 加final和不加final的区别
	/**
	 * 加final和不加final的区别:
	 * 	加final,在编译的时候,把具体的值设置常量对应的位置
	 * 	不加final.在用到时,才去寻找对应的值.
	 */
	public  static String USER_STATE_VALID = "1";//有效
	public  static String USER_STATE_INVALID = "0";//无效
	
	public User() {
		super();
	}
	
	public User(String id, String name, String dept, String account, String password, String headImg, boolean gender,
			String email, String state, String mobile, Date birthday, String memo) {
		super();
		this.id = id;
		this.name = name;
		this.dept = dept;
		this.account = account;
		this.password = password;
		this.headImg = headImg;
		this.gender = gender;
		this.email = email;
		this.state = state;
		this.mobile = mobile;
		this.birthday = birthday;
		this.memo = memo;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<UserRole> userRole) {
		this.userRole = userRole;
	}
}
