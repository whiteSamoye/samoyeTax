package cn.samoye.nsfw.user.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import cn.samoye.nsfw.user.bean.User;
import cn.samoye.nsfw.user.dao.UserDao;
import cn.samoye.nsfw.user.service.UserService;
import cn.samoye.test.utils.PoiUtils;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	
	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void delete(Serializable id) {
		userDao.delete(id);
	}

	@Override
	public User queryUserById(Serializable id) {
		return userDao.queryObjectById(id);
	}

	@Override
	public List<User> queryUserList() {
		return userDao.queryObjectList();
	}

	@Override
	public void exportExcelService(List<User> userList, ServletOutputStream ops) {
		String title = "用户列表";
		String[] columnName = {"用户名","账号","所属部门","性别","电子邮箱"};
		int titleFontSize = 16;
		int columnNameSize = 14;
		String[] beanPropertesNames = {"name","account","dept","gender","email"};
		HashMap<String,HashMap<String,String>> hashMap = new HashMap<String ,HashMap<String,String>>();
		HashMap<String,String> value = new HashMap<String,String>();
		value.put("男", "女");
		hashMap.put("gender", value);
		PoiUtils.Export(userList, ops, title, columnName, titleFontSize, columnNameSize, beanPropertesNames,hashMap);
/*		try {
			//1.创建工作薄对象
			Workbook wb = PoiUtils.getWorkbookWriter_xls();
				//1.1.创建合并单元格对象
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 4);
				//1.2.创建头标题样式
			CellStyle titleStyle = PoiUtils.desiginerStyle(wb, true, 16);
				//1.3.创建列标题样式
			CellStyle styel2 = PoiUtils.desiginerStyle(wb, true, 13);
			//2.创建工作表对象
			Sheet sheet = wb.createSheet("用户列表");
				//2.1.加载合并单元格
			sheet.addMergedRegion(cellRangeAddress);
			//设置默认列宽
			sheet.setDefaultColumnWidth(23);
			//3.创建行对象
			//3.1创建头标题行,并设置头标题
			Row titleRow = sheet.createRow(0);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellStyle(titleStyle);
			titleCell.setCellValue("用户列表");
			//创建列标题行
			//3.2.创建列标题行,并设置列标题
			Row row2 = sheet.createRow(1);
			String[] titles = {"用户名","账号","所属部门","性别","电子邮箱"};
			for(int i=0;i<titles.length;i++){
				Cell cell = row2.createCell(i);
				cell.setCellStyle(styel2);
				cell.setCellValue(titles[i]);
			}
			//4.操作单元格
			if(userList != null){
				for (int j=0;j<userList.size();j++) {
					Row contentRow = sheet.createRow(j+2);
					
					Cell cell0 = contentRow.createCell(0);
					cell0.setCellValue(userList.get(j).getName());
					
					Cell cell1 = contentRow.createCell(1);
					cell1.setCellValue(userList.get(j).getAccount());
					
					Cell cell2 = contentRow.createCell(2);
					cell2.setCellValue(userList.get(j).getDept());
					
					Cell cell3 = contentRow.createCell(3);
					cell3.setCellValue(userList.get(j).isGender()?"男":"女");
					
					Cell cell4 = contentRow.createCell(4);
					cell4.setCellValue(userList.get(j).getEmail());
					
				}
			}
//		输出
			wb.write(ops);
			wb.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
