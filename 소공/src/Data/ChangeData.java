package Data;

import java.util.StringTokenizer;

public class ChangeData {

	public int[][] changeTimeData(int rowCount, String[][] totalData) {
		//문자형식의 시간데이터를 정수형의 시간데이터로 변환
		
		String day=null;	//요일
		int iTime[][]=new int[rowCount][10];	//String형의 데이터를 int형으로 변환하여 저장할 배열
		for(int i=0; i<rowCount; i++){
			
			day=totalData[i][6].replace(':',',');
			day = day.replace(' ', ',');
			day = day.replace('-', ',');
			day = day.replace("월", "1,");
			day = day.replace("화", "2,");
			day = day.replace("수", "3,");
			day = day.replace("목", "4,");
			day = day.replace("금", "5,");
			day = day.replace("토", "6,");
			day.trim();
			
			String[] time=new String[10];
			
			StringTokenizer st=new StringTokenizer(day,",");
			int tokenCount=0;
			while(st.hasMoreTokens()){
				time[tokenCount]=st.nextToken();
				tokenCount++;
			}
			
			for(int j=0;j<tokenCount; j++){
				iTime[i][j]=Integer.parseInt(time[j]);
			}
			
			changeMin(iTime,i, 1);
			changeMin(iTime,i, 3);
			
			if(tokenCount==10){
				changeMin(iTime,i, 6);
				changeMin(iTime,i, 8);
			}
		}		
		return iTime;
	}
	
	public static void changeMin(int iTime[][],int i, int j) {
		//45, 15분 단위의 수업시간을 0분, 30분 단위로 변환
		
	      if (iTime[i][j + 1] > 10 && iTime[i][j + 1] <= 40) {
	         iTime[i][j] = iTime[i][j] * 10;
	         iTime[i][j + 1] = 5;
	      } else if (iTime[i][j + 1] > 40 && iTime[i][j + 1] <= 59) {
	         iTime[i][j] = (iTime[i][j] + 1) * 10;
	         iTime[i][j + 1] = 0;
	      } else if (iTime[i][j + 1] >= 0 && iTime[i][j + 1] <= 10) {
	         iTime[i][j] = iTime[i][j] * 10;
	         iTime[i][j + 1] = 0;
	      }
	   }
}
