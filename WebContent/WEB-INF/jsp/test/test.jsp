<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>plupload</title>
<script type="text/javascript" src="${basePath }js/plupload.full.min.js"></script>
</head>
<body>
	<p>
		<button id="browse">选择文件</button>
		<button id="start_upload">开始上传</button>
	</p> 



<br />
<pre id="console"></pre>	
	<script type="text/javascript">
	
	
	
		//实例化一个plupload上传对象
		var uploader = new plupload.Uploader({
			browse_button:'browse',//触发文件选择对话框的按钮
			url:'${basePath}nsfw/user_plopload.action',
			
		});
		uploader.init();
		document.getElementById('start_upload').onclick=function(){
			uploader.start();
		} 
	</script>
</body>
</html>