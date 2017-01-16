package cn.samoye.nsfw.user.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import cn.samoye.core.exception.ServiceException;
import cn.samoye.core.utils.ExcelUtils;
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
	public List<User> queryUserList() throws ServiceException{
		try {
//			int i = 1/0;
		} catch (Exception e) {
			throw new ServiceException("业务层出错");
		}
		return userDao.queryObjectList();
	}

	@Override
	public void exportExcelService(List<User> userList, ServletOutputStream ops) {
		ExcelUtils.exportExcel(userList, ops);
		/*String title = "用户列表";
		String[] columnName = {"用户名","账号","所属部门","性别","电子邮箱"};
		int titleFontSize = 16;
		int columnNameSize = 14;
		String[] beanPropertesNames = {"name","account","dept","gender","email"};
		HashMap<String,HashMap<String,String>> hashMap = new HashMap<String ,HashMap<String,String>>();
		HashMap<String,String> value = new HashMap<String,String>();
		value.put("男", "女");
		hashMap.put("gender", value);
		PoiUtils.Export(userList, ops, title, columnName, titleFontSize, columnNameSize, beanPropertesNames,hashMap);*/
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

	@Override
	public void importExcel(File headImg, String headImgFileName) {
		ExcelUtils.importExcel(headImg,headImgFileName,this);
		try {
			FileInputStream fis = new FileInputStream(headImg);
			//1.读取工作薄对象
			boolean is03Excel = headImgFileName.matches("^.+\\.(?i)(xls)$");
			Workbook wb = is03Excel?new HSSFWorkbook(fis):new XSSFWorkbook(fis);
			//2.读取工作表对象
			Sheet sheet = wb.getSheetAt(0);
			if(sheet.getPhysicalNumberOfRows()>2){
				for(int i=2;i<sheet.getPhysicalNumberOfRows();i++){
					//3.读取行对象
					Row row = sheet.getRow(i);
					User user = new User();
					//4.读取单元格对象
					//姓名
					Cell cell0 = row.getCell(0);
					user.setName(cell0.getStringCellValue());
					//账号
					Cell cell1 = row.getCell(1);
					user.setAccount(cell1.getStringCellValue());					
					//所属部门
					Cell cell2 = row.getCell(2);
					user.setDept(cell2.getStringCellValue());					
					//性别
					Cell cell3 = row.getCell(3);
					user.setGender("男".equals(cell3.getStringCellValue()));					
					//手机号,excel对于比较大的数字,会采用科学计数法处理,直接通过getStringCellValue()获取,会出错.
					Cell cell4 = row.getCell(4);
					try {
						user.setMobile(cell4.getStringCellValue());		
						System.out.println(cell4.getStringCellValue());
					} catch (Exception e) {
						double doubleValue = cell4.getNumericCellValue();
						String value = BigDecimal.valueOf(doubleValue).toString();
						user.setMobile(value);
					}
					//电子邮箱
					Cell cell5 = row.getCell(5);
					user.setEmail(cell5.getStringCellValue());					
					//生日
					Cell cell6 = row.getCell(6);
					if(cell6.getDateCellValue() != null){
						user.setBirthday(cell6.getDateCellValue());					
					}
					user.setPassword("123");
					user.setState(User.USER_STATE_VALID);
					// TODO 账号唯一性检验
					User user2 = this.queryUserByAccount(user);
					if(user2== null || !user.getAccount().equals(user2.getAccount())){
						this.save(user);
					}
					
				}
			}
			wb.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public User queryUserByAccount(User user) {
		return userDao.queryUserByAccount(user);
	}

	@Override
	public boolean verifyAccount(String account) {
		return userDao.verifyAccount(account);
	}

	@Override
	public List<User> queryUserListByAccountAndId(String account, String id) {
		if(StringUtils.isNotBlank(id)){
			return userDao.queryUserListByAccountAndId(account,id);
		}else{
			return userDao.queryUserByAccount(account);
		}
	}

}
