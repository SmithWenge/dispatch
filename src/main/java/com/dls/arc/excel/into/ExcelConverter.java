package com.dls.arc.excel.into;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelConverter<T> {
	public List<T> readFromExcel(File file, int ignoreRow, ExcelMapper<T> mapper) {
		List<T> list = null;
		BufferedInputStream bis = null;
		try {
			list = new ArrayList<T>();
			bis = new BufferedInputStream(new FileInputStream(file));
			//通过文件流获取文件
			POIFSFileSystem fs = new POIFSFileSystem(bis);
			//获取该excel中的sheets
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			
			for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
				//获得excel中的一个sheet
				HSSFSheet sheet = wb.getSheetAt(sheetIndex);
				
				for (int rowIndex = ignoreRow; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
					//获得sheet中的每一行
					HSSFRow row = sheet.getRow(rowIndex);

					if (null == row)
						continue;

					list.add(mapper.mapStringArray(row));
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
}
