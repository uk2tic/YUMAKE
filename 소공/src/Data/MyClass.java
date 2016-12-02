package Data;

public class MyClass {

	public void add(String[][] selectedData, int selectedDataRow, String myClassName, Object day, Object startTime, Object finishTime){
		for(int i=0; i<10; i++){
			if(i==3){
				selectedData[selectedDataRow][i]=myClassName;
			}
			else if(i==6){
				selectedData[selectedDataRow][i]=day+" "+startTime+"-"+finishTime;
			}
			else
				selectedData[selectedDataRow][i]=null;
		}
	}
	

	public void addTimeData(int[][] selectedHms, int selectedDataRow, int day, int startTime, int finishTime){
		selectedHms[selectedDataRow][0]=day;
		selectedHms[selectedDataRow][1]=startTime;
		selectedHms[selectedDataRow][2]=finishTime-startTime;
	}
}
