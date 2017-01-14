package cn.samoye.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import cn.samoye.core.annotation.ExcelClassAnnotation;
import cn.samoye.nsfw.user.bean.User;
import cn.samoye.test.utils.PoiUtils;

public class ExcelUtils {
	public static void importExcel(){
		
	}
	public static void exportExcel(List<User> userList, ServletOutputStream ops) {
		try {
			//1.创建工作薄对象
			Workbook wb = getWorkbookWriter_xls();
				//1.1.创建合并单元格对象
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 4);
				//1.2.创建头标题样式
			CellStyle titleStyle = desiginerStyle(wb, true, 16);
				//1.3.创建列标题样式
			CellStyle styel2 = desiginerStyle(wb, true, 13);
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
			closeResource(ops,wb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * 返回工作薄对象,xls创建HSSFWorkbook,xlsx创建XSSFWorkbook
	 * @return
	 * @throws IOException 
	 */
	public static Workbook getWorkbookRead(String absolutePath)throws IOException{
		if(absolutePath != null && !"".equals(absolutePath)){
			// .任意  (?i)忽略大小写  ^ 开始   $ 分割
			if(absolutePath.matches("^.+\\.(?i)((xls)|(xlsx))$")){
				FileInputStream fis = new FileInputStream(new File(absolutePath));
				return absolutePath.matches("^.+\\.(?i)((xls)$")?new HSSFWorkbook(fis):new XSSFWorkbook(fis);
			}else{
				// TODO 此处使用自定义异常,FileTypeNotMatches
				 throw new FileNotFoundException();
			}
		}
		return null;
	}
	
	public static Workbook getWorkbookWriter_xls(){
		return new HSSFWorkbook();
	}
	
	public static Workbook getWorkbookWriter_xlsx(){
		return new XSSFWorkbook();
	}
	
	public static CellStyle desiginerStyle(Workbook wb,boolean fontBold,Integer fontSize){
		//1.2.创建头标题样式
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		Font font = wb.createFont();
		if(fontBold){
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);//加粗
		}
		if(fontSize != null){
			font.setFontHeightInPoints(fontSize.shortValue());//16号字体
		}
		cellStyle.setFont(font);
		return cellStyle;
	}
	/**
	 * 
	 * @param ops 输出流对象
	 * @param wb  工作薄对象
	 */
	public static void closeResource(OutputStream ops,Workbook wb){
		if(wb != null){
			try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(ops != null){
			try {
				ops.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
