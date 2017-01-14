<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'plupload.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  	<script type="text/javascript" src="${basePath }js/plupload.full.min.js"></script>
  	
  </head>
  
  <body style="font: 13px Verdana; background: #eee; color: #333">

<h1>Custom example</h1>

<p>Shows you how to use the core plupload API.</p>

<div id="filelist">Your browser doesn't have Flash, Silverlight or HTML5 support.</div>
<br />

<div id="container">
    <a id="pickfiles" href="javascript:;">[Select files]</a> 
    <a id="uploadfiles" href="javascript:;">[Upload files]</a>
</div>

<br />
<pre id="console"></pre>


<script type="text/javascript">
// Custom example logic

var uploader = new plupload.Uploader({
	runtimes : 'html5,flash,silverlight,html4',
	browse_button : 'pickfiles', // you can pass in id...
	container: document.getElementById('container'), // ... or DOM Element itself
	url : 'upload.action',
	flash_swf_url : 'plupload/Moxie.swf',
	silverlight_xap_url : 'plupload/Moxie.xap',
	
	filters : {
		max_file_size : '2mb',
		mime_types: [
			{title : "Image files", extensions : "jpg,gif,png"},
		]
	},

	init: {
		PostInit: function() {
			
			document.getElementById('filelist').innerHTML = '';

			document.getElementById('uploadfiles').onclick = function() {
				uploader.start();
				return false;
			};
		},
		
		FileUploaded :function(up,file,res){
			alert(res.response)
			console.log(res.response);
		},

		FilesAdded: function(up, files) {
			uploader.start();
			plupload.each(files, function(file) {
				document.getElementById('filelist').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
			});
		},

		UploadProgress: function(up, file) {
			document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
		},
		UploadComplete:function(up,files,res){
			
		},
		Error: function(up, err) {
			if(err.code == -601){
				alert("文件后缀错误");
			}
			
			if(err.code == -600){
				alert("文件过大");
			}
			document.getElementById('console').innerHTML += "\nError #" + err.code + ": " + err.message;
		}
	}
});

uploader.init();

</script>
  </body>
</html>
