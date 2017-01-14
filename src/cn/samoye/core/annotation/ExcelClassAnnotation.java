package cn.samoye.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.ANNOTATION_TYPE)
public @interface ExcelClassAnnotation {
	/**
	 * 		String title = "用户列表";
		String[] columnName = {"用户名","账号","所属部门","性别","电子邮箱"};
		int titleFontSize = 16;
		int columnNameSize = 14;
		String[] beanPropertesNames = {"name","account","dept","gender","email"};
	 * @return
	 */
	/**
	 * 标题名
	 * @return
	 */
	String titleName();
	/**
	 * 列名
	 * @return
	 */
	String[] columnNames();
	/**
	 * bean属性名
	 * @return
	 */
	String[] beanPropertyNames();
	/**
	 * 标题字体大小
	 * @return
	 */
	int titleFontSize();
	/**
	 * 列字体大小
	 * @return
	 */
	int columnNameSize();
	
	
}
