package Data;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class GetData {

	public String[][] getTotalDataSize(File file, String[][] totalData) {
		//totalData의 배열크기를 결정
		int row = 0;
		int col = 0;
		try {
			Workbook wb = null;
			wb = Workbook.getWorkbook(file);
			Sheet sheet = wb.getSheet(0);
			row = sheet.getRows();
			col = sheet.getColumns();
		} catch (Exception e) {
			e.printStackTrace();
		}

		totalData = new String[row][];
		for (int i = 0; i < row; i++) {
			totalData[i] = new String[col];
		}
		return totalData;
	}


	public int[][] getClassTimeDataSize(File file, int[][] classTimeData) {
		//classTimeData의 배열크기를 결정
		int row = 0;
		int col = 0;
		try {
			Workbook wb = null;
			wb = Workbook.getWorkbook(file);
			Sheet sheet = wb.getSheet(0);
			row = sheet.getRows();
			col = sheet.getColumns();
		} catch (Exception e) {
			e.printStackTrace();
		}

		classTimeData = new int[row][];
		for (int i = 0; i < row; i++) {
			classTimeData[i] = new int[6];
		}
		return classTimeData;
	}

	public String[][] getTotalData(File file, String[][] totalData) {
		//엑셀파일의 데이터를 totalData배열에 저장
		try {
			Workbook wb = null;
			wb = Workbook.getWorkbook(file);
			Sheet sheet = wb.getSheet(0);
			int row = sheet.getRows();
			int col = sheet.getColumns();
			// totalData=new String[row][col];
			for (int i = 0; i < row; i++) {
				for (int n = 0; n < col; n++) {
					Cell cell = sheet.getCell(n, i);
					if (cell == null)
						continue;
					totalData[i][n] = cell.getContents();
				}
			}

			return totalData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalData;
	}
	
	public int count(int type, File file) {
		//엑셀파일의 총 row, col의 크기 결정
		int row = 0;
		int col = 0;
		try {
			Workbook wb = null;
			wb = Workbook.getWorkbook(file);
			Sheet sheet = wb.getSheet(0);
			row = sheet.getRows(); 
			col = sheet.getColumns();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (type == 1) {
			return row;
		} else {
			return col;
		}
	}
	
}