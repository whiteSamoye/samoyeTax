package cn.samoye.nsfw.role.action;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;

import cn.samoye.core.action.BaseAction;
import cn.samoye.core.constant.Constant;
import cn.samoye.core.exception.ActionException;
import cn.samoye.core.exception.ServiceException;
import cn.samoye.core.exception.SysException;
import cn.samoye.nsfw.role.bean.Role;
import cn.samoye.nsfw.role.bean.RolePrivilege;
import cn.samoye.nsfw.role.bean.RolePrivilegeId;
import cn.samoye.nsfw.role.service.RoleService;

public class RoleAction extends BaseAction {
	private final static Log log = LogFactory.getLog(RoleAction.class);
	@Resource
	private RoleService roleService;
	private List<Role> roleList;
	private Role role;
	private String[] privilegeCodes;
	
	//1.列表页面
	public String listUI()throws SysException{
		//加载权限集合
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
		try {
			roleList = roleService.queryRoleList();
		} catch (ServiceException e) {
			throw new ActionException("action处理: "+e.getMessage());
		}
		return "listUI";
	}
	//2.新增页面
	public String addUI(){
		//加载权限集合
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
		return "addUI";
	}
	
	//3.保存新增
	public String add(){
		if(role != null){
			//处理权限
			if(privilegeCodes != null && privilegeCodes.length > 0){
				Set<RolePrivilege> rolePrivileges = role.getRolePrivileges();
				for(int i=0;i<privilegeCodes.length;i++){
					//必须设置一方级联更新
					rolePrivileges.add(new RolePrivilege(new RolePrivilegeId(role,privilegeCodes[i])));
				}
				role.setRolePrivileges(rolePrivileges);
			}
			roleService.save(role);
		}
		return "list";
	}
	//4.更新页面
	public String updateUI(){
		//加载权限集合
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
		if(role != null && StringUtils.isNotBlank(role.getRoleId())){
			role = roleService.queryRoleById(role.getRoleId());
			if(role.getRolePrivileges() != null){
				//设置权限回显
				privilegeCodes = new String[role.getRolePrivileges().size()];
				int i = 0;
				for (RolePrivilege rolePrivilege : role.getRolePrivileges()) {
					privilegeCodes[i++] = rolePrivilege.getRolePrivilegeId().getCode(); 
				}
			}
		}
		return "updateUI";
	}
	/**
	 * 5.保存更新
	 * @return
	 */
	public String update(){
		if(role != null){
			//处理权限
			if(privilegeCodes != null && privilegeCodes.length > 0){
				Set<RolePrivilege> rolePrivileges = role.getRolePrivileges();
				for(int i=0;i<privilegeCodes.length;i++){
					//必须设置一方级联更新
					rolePrivileges.add(new RolePrivilege(new RolePrivilegeId(role,privilegeCodes[i])));
				}
				role.setRolePrivileges(rolePrivileges);
			}
			roleService.update(role);
		}
		return "list";
	}
	/**
	 * 6.删除
	 * 删除的同时,删除该用户对应的头像信息
	 * @return
	 */
	public String delete(){
		if(role != null && StringUtils.isNotBlank(role.getRoleId())){
			role = roleService.queryRoleById(role.getRoleId());
			roleService.delete(role.getRoleId());
		}
		return "list";
	}
	//7.批量删除
	public String deletes(){
		if(selectedRow != null && selectedRow.length > 0){
			for (String id : selectedRow) {
				roleService.delete(id);
			}
		}
		return "list";
	}
	
	/**
	 * ---------------------------------数据的封装
	 */
	
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String[] getPrivilegeCodes() {
		return privilegeCodes;
	}
	public void setPrivilegeCodes(String[] privilegeCodes) {
		this.privilegeCodes = privilegeCodes;
	}
	
	
	
}
