package Data;

public class ClassReset {
   
   public void resetSelectedTable(String[][] selectedData, int[][] selectedTimeData, int selectedDataRow, int colCount) {
	   //선택과목데이터를 초기화
      for (int i = 0; i < selectedDataRow; i++) {
         for (int j = 0; j < colCount; j++) {
            selectedData[i][j] = null;
         }
      }
      
      for (int i = 0; i < selectedDataRow; i++) {
          for (int j = 0; j < 6; j++) {

			selectedTimeData[i][j]=0;
          }
       }

      //int[][] selectedHms1=new int[50][6];
      
      //selectedDataCol = 0;

   }
}
