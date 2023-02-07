package com.swag.constants;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;

import commonUtils.ExcelUtils;
import commonUtils.PropertyUtils;

public class POJOSupporters {
	
	public static final int TimeOut = 20;
	
	private static PropertyUtils prConf;
	private static ExcelUtils excel;
	
	public static PropertyUtils getPrConf() throws IOException {
		prConf = new PropertyUtils(FilePaths.configPath);
		return prConf;
	}
	
	public static ExcelUtils getExcel() throws EncryptedDocumentException, IOException {
		excel = new ExcelUtils(FilePaths.excelPath);
		return excel;
	}
	

}
