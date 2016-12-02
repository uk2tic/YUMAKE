package Data;

import java.io.File;

import javax.swing.JFileChooser;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ClassSave {
	//private static studentInformation sInformation; // 학적정보
	
	public void classSave(StudentInformation sInformation, String[][] data,int rowCount,int colCount){
		// TODO Auto-generated method stub
		//this.sInformation=sInformation;
		int sum=0;
		int saveRow=sInformation.getSaveRow();
		String temp;
		WritableWorkbook workbook = null;

		WritableSheet sheet = null;
		Label label = null;
		
		
		File dir = new File("C:\\");
		File f = new File(dir, "test.xls");

		if(!f.exists()){
			try{
				workbook = Workbook.createWorkbook(f);

				workbook.createSheet("sheet1", 0);
				sheet = workbook.getSheet(0);

				writeExcel(sheet, sInformation.getName(),0,saveRow);
				writeExcel(sheet, sInformation.getDept(),1,saveRow);
				writeExcel(sheet, String.valueOf(sInformation.getGrade()),2,saveRow);
				writeExcel(sheet, String.valueOf(sInformation.getSemester()),3,saveRow);
				for(int i =0;i<rowCount;i++){
					for(int j =0;j<colCount;j++){
						writeExcel(sheet, data[i][j],j,i+saveRow+1);					
					}
					temp=data[i][4].trim();
					sum=sum+Integer.valueOf(temp);
				}
				//saveRow =saveRow+rowCount;
				sInformation.setSaveRow(saveRow+rowCount+1);
				//sInformation.setTotalC(sInformation.getTotalC()+sum);
				
				writeExcel(sheet, sInformation.getName(),0,0);
				writeExcel(sheet, sInformation.getDept(),1,0);
				writeExcel(sheet, String.valueOf(sInformation.getGrade()),2,0);
				writeExcel(sheet, String.valueOf(sInformation.getSemester()),3,0);				
				
				workbook.write();
				workbook.close();
				
				}
			catch(Exception e){				
				e.printStackTrace();
			}
		}
		else{
			try{
				Workbook workbook1 = Workbook.getWorkbook(f);
				File newfile = new File("C:\\Users\\DONG WOOK\\Desktop\\test.xls");
				WritableWorkbook writeBook = Workbook.createWorkbook(newfile, workbook1);

				WritableSheet sheet1 = writeBook.getSheet(0);
				writeExcel(sheet1, sInformation.getName(),0,saveRow);
				writeExcel(sheet1, sInformation.getDept(),1,saveRow);
				writeExcel(sheet1, String.valueOf(sInformation.getGrade()),2,saveRow);
				writeExcel(sheet1, String.valueOf(sInformation.getSemester()),3,saveRow);	
//				workbook1.createSheet("sheet1", 0);	
//				sheet = workbook1.getSheet(0);
				System.out.println("ad"+saveRow);
				for(int i =0;i<rowCount;i++){
					for(int j =0;j<colCount;j++){
						writeExcel(sheet1, data[i][j],j,i+saveRow+1);			
					}
					temp=data[i][4].trim();
					sum=sum+Integer.valueOf(temp);
				}
				//saveRow =saveRow+rowCount;
				sInformation.setSaveRow(saveRow+rowCount+1);
				
				writeExcel(sheet1, sInformation.getName(),0,0);
				writeExcel(sheet1, sInformation.getDept(),1,0);
				writeExcel(sheet1, String.valueOf(sInformation.getGrade()),2,0);
				writeExcel(sheet1, String.valueOf(sInformation.getSemester()),3,0);				
				
				writeBook.write();
				writeBook.close();
				
				}
			catch(Exception e){				
				e.printStackTrace();
			}
		}

	}
	
	public static void writeExcel(WritableSheet sheet,String inforamtion,int row, int col) throws RowsExceededException, WriteException{
		Label label = null;
		label = new Label(row, col, String.valueOf(inforamtion));
		sheet.addCell(label);
	}
}
