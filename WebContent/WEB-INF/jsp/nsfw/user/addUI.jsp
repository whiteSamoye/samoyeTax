<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>用户管理</title>
    <script type="text/javascript" src="${basePath }js/datepicker/WdatePicker.js"></script>
    <script type="text/javascript">
    	/**
    		问题:document.forms[0].submit();提交时,出现,没有submit这个方法,
    			出现原因:
    				<input name="submit" type="button" class="btnB2" value="保存" onclick="doSubmit(this)"/>
    			一个表单中只能有一个name为submit或者action的控件,改成Submit
    		前台验证:
    			对账户唯一性,非空,email,手机号码进行验证.
    			验证时机:
    				当input里的内容被改变时,当点击提交按钮后,其中
    	*/
   		var verifyResult = false;
    	function verifyAccount(ele) {
			var account = $(ele).val();
			//get post load getJson ajax
			//ajax
			if(account != ""){
				$.ajax({
					url:"${basePath}/nsfw/user_verifyAccount.action",
					data:{"user.account":account},
					async:false, //默认为true,false时,为同步.
					type:"post",
					success:function(msg){
						if("true" != msg){
							//alert("账号已经存在!请使用其他账号!");
							//定焦
							$(ele).next().remove();
							$(ele).focus();
							$(ele).after("<p style='color: red'>账号已经存在,请使用其他账号</p>");
							//var submit = $(".tc mt20");//[0].find("input[name=submit]");
							var submit = $("input[name='mySubmit']");
							submit.attr("disabled","true");
							submit.attr("style","color:#404552;");
							verifyResult = false;
						}else{
							$(ele).next().remove();
							var submit = $("input[name='mySubmit']");
							submit.removeAttr("disabled");
							verifyResult = true;
						}
					}
				});
			}else{
				$(ele).next().remove();
				$(ele).after("<p style='color: red'>账号名不能为空</p>");
				verifyResult = false;
			}
		}
    	//验证电话号码,是否合法
    	function verifyMobile(ele) {
			//^(130|131|132|133|134|135|136|137|138|139)\d{8}$
			var reg = /^1\d{10}$/;
			var flag = reg.test($(ele).val());
			if(!flag){
				$(ele).after("<p style='color: red'>当前手机号码不合法,请输入正确的手机号码</p>");
				verifyResult = false;
			}else{
				$(ele).next().remove();
				verifyResult = true;
			}
		}
    	//验证电子邮箱是否合法
    	function verifyEmail(ele) {
    		var reg = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
			var flag = reg.test($(ele).val());
			if(!flag){
				$(ele).after("<p style='color: red'>当前电子邮箱不合法,请输入正确的电子邮箱</p>");
				verifyResult = false;
			}else{
				$(ele).next().remove();
				verifyResult = true;
			}
		}
    	//用户名
    	function verifyName(ele) {
			if($(ele).val() != ""){
				$(ele).next().remove();
			}	
		}
    	
    	function verifyPassword(ele) {
			if($(ele).val() != ""){
				$(ele).next().remove();
			}	
		}
    	//提交表单
    	function doSubmit(ele) {
    		//1.验证用户名不为空
    		var userName = $("#baseInfo").find("input[name='user.name']");
    		if(userName.val() == ""){
    			verifyResult = false;
    			userName.next().remove();
    			userName.after("<p style='color: red'>用户名不能为空</p>");
    			return ;
    		}else{
    			userName.next().remove();
    			varifyResult = true;
    		}
    		//2.验证密码不为空
    		var password = $("#baseInfo").find("input[name='user.password']");
    		if(password.val() == ""){
    			verifyResult = false;
    			password.next().remove();
    			password.after("<p style='color: red'>密码不能为空</p>");
    			return ;
    		}else{
    			var reg = /^\d{3}$/;
    			var flag = reg.test(password.val());
    			if(flag){
    				password.next().remove();
        			varifyResult = true;
    			}else{
    				verifyResult = false;
    				password.next().remove();
        			password.after("<p style='color: red'>密码为3位数字</p>");
        			return;
    			}
    		}
    		verifyAccount($("#baseInfo").find("input[name='user.account']"));
    		if(verifyResult){
				document.forms[0].submit();
    		}
		}
    </script>
</head>
<body class="rightBody">
<form id="form" name="form" action="${basePath }nsfw/user_add.action" method="post" enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>用户管理</strong>&nbsp;-&nbsp;新增用户</div></div>
    <div class="tableH2">新增用户</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="200px">所属部门：</td>
            <td><s:select name="user.dept" list="#{'部门A':'部门A','部门B':'部门B' }"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">头像：</td>
            <td>
                <input type="file" name="headImg"/>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">用户名：</td>
            <td><s:textfield name="user.name" onchange="verifyName(this)"/> </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">帐号：</td>
            <td><s:textfield name="user.account" onchange="verifyAccount(this)"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">密码：</td>
            <td><s:textfield name="user.password" onchange="verifyPassword(this)"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">性别：</td>
            <td><s:radio list="#{'true':'男','false':'女'}" name="user.gender"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">角色：</td>
            <td>
            	<s:checkboxlist list="roleList" name="roleIds" listKey="roleId" listValue="name"></s:checkboxlist>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">电子邮箱：</td>
            <td><s:textfield name="user.email" onchange="verifyEmail(this)"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">手机号：</td>
            <td><s:textfield name="user.mobile" onchange="verifyMobile(this)"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">生日：</td>
            <td><s:textfield id="birthday" name="user.birthday" readonly="true" onfocus="WdatePicker({'skin':'whyGreen','dateFmt':'yyyy-MM-dd'})"/></td>
        </tr>
		<tr>
            <td class="tdBg" width="200px">状态：</td>
            <td><s:radio list="#{'1':'有效','0':'无效'}" name="user.state" value="1"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">备注：</td>
            <td><s:textarea name="user.memo" cols="75" rows="3"/></td>
        </tr>
    </table>
    <div class="tc mt20">
        <input name="mySubmit" type="button" class="btnB2" value="保存" onclick="doSubmit(this)"/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form>
</body>
</html>