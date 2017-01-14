package cn.samoye.test.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import cn.samoye.core.utils.ExcelUtils;
import cn.samoye.nsfw.user.bean.ExcelUser;

public class TestExcel {
	@Test
	public void testExcel()throws Exception{
		//1.创建工作薄
		Workbook wb = new HSSFWorkbook();
		
		//2.创建工作表
		Sheet sheet = wb.createSheet("工作表1");
		//3.创建行
		Row row = sheet.createRow(2);
		//4.创建单元格,并且设置内容
		Cell cell = row.createCell(2);
		cell.setCellValue("samoye");
		//5.执行输出
		FileOutputStream fos = new FileOutputStream(new File("/home/samoye/devsoft/myexcel.xls"));
		wb.write(fos);
		//6.关闭资源
		wb.close();
		fos.close();
	}

}
