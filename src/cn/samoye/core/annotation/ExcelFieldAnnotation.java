package cn.samoye.core.annotation;

public @interface ExcelFieldAnnotation {
	String beanPropertyName();
	boolean isExport() default true;
	boolean isImport() default true;
}
