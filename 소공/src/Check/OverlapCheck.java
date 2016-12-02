package Check;

public class OverlapCheck {

	public static int classNameOverlapCheck(int row, int selectedDataCol, String[][] totalData,
			String[][] selectedData) { // 이름이 같은 과목이 이미 선택되었는지 체크
		for (int i = 0; i < selectedDataCol; i++) {
			if (totalData[row][3].equals(selectedData[i][3])) {
				return 2;
			}
		}
		return 1;
	}

	public static int classOverlapCheck(int row, int selectedDataCol, String[][] totalData, String[][] selectedData) {
		for (int i = 0; i < selectedDataCol; i++) { // 과목번호가 같은 것이 있는지 체크
			if (totalData[row][0].equals(selectedData[i][0])) {
				return 2;
			}
		}
		return 1;
	}

	public static int timeOverlapCheck(int row, int[][] classTimeData, int[][] selectedTimeData, int selectedDataRow) {
		// 중복되는 시간의 과목이 있는지 체크
		int Day, startTime, classClock, finishTime;

		for (int j = 0; j < 6; j++) {
			System.out.print(classTimeData[row][j] + " ");
		}
		System.out.println();

		for (int j = 0; j < 6; j++) {
			System.out.print(selectedTimeData[0][j] + " ");
		}

		if (classTimeData[row][3] == 0) {
			Day = classTimeData[row][0];
			startTime = classTimeData[row][1];
			classClock = classTimeData[row][2];
			finishTime = startTime + classClock - 1;

			for (int i = 0; i < selectedDataRow; i++) {
				if (Day == selectedTimeData[i][0]) {
					if (CompareTime.compareTimeData(selectedTimeData[i][1],
							selectedTimeData[i][1] + selectedTimeData[i][2] - 1, startTime, finishTime) == 2) {
						return 2;
					}
				}
				if (Day == selectedTimeData[i][3]) {
					if (CompareTime.compareTimeData(selectedTimeData[i][4],
							selectedTimeData[i][4] + selectedTimeData[i][5] - 1, startTime, finishTime) == 2) {
						return 2;
					}
				}
			}
		} else {
			Day = classTimeData[row][0];
			startTime = classTimeData[row][1];
			classClock = classTimeData[row][2];
			finishTime = startTime + classClock - 1;

			System.out.println("111");

			for (int i = 0; i < selectedDataRow; i++) {
				if (Day == selectedTimeData[i][0]) {
					if (CompareTime.compareTimeData(selectedTimeData[i][1],
							selectedTimeData[i][1] + selectedTimeData[i][2] - 1, startTime, finishTime) == 2) {
						return 2;
					}
				}
				if (Day == selectedTimeData[i][3]) {
					if (CompareTime.compareTimeData(selectedTimeData[i][4],
							selectedTimeData[i][4] + selectedTimeData[i][5] - 1, startTime, finishTime) == 2) {
						return 2;
					}
				}
			}
			System.out.println("222");
			Day = classTimeData[row][3];
			startTime = classTimeData[row][4];
			classClock = classTimeData[row][5];
			finishTime = startTime + classClock - 1;

			for (int i = 0; i < selectedDataRow; i++) {
				if (Day == selectedTimeData[i][0]) {
					if (CompareTime.compareTimeData(selectedTimeData[i][1],
							selectedTimeData[i][1] + selectedTimeData[i][2] - 1, startTime, finishTime) == 2) {
						System.out.println("여기111");
						System.out.println();
						System.out.println(
								selectedTimeData[i][1] + "  " + (selectedTimeData[i][1] + selectedTimeData[i][2] - 1));
						System.out.print(startTime + "  " + finishTime);
						return 2;
					}
				}
				if (Day == selectedTimeData[i][3]) {
					if (CompareTime.compareTimeData(selectedTimeData[i][4],
							selectedTimeData[i][4] + selectedTimeData[i][5] - 1, startTime, finishTime) == 2) {
						System.out.println("여기222");
						return 2;
					}
				}
			}
			System.out.println("333");
		}
		System.out.println("444");
		return 1;
	}

	public static int myTimeOverlapCheck(int[][] selectedTimeData, int selectedDataRow, int day, int startTime,
			int finishTime) {
		// 요일 중복체크
		for (int i = 0; i < selectedDataRow; i++) {
			if (selectedTimeData[i][0] == day) {
				if (CompareTime.compareTimeData(selectedTimeData[i][1],
						selectedTimeData[i][1] + selectedTimeData[i][2] - 1, startTime, finishTime - 1) == 2) {
					return 2;
				}
			}
			if (selectedTimeData[i][3] == day) {
				if (CompareTime.compareTimeData(selectedTimeData[i][4],
						selectedTimeData[i][4] + selectedTimeData[i][5] - 1, startTime, finishTime - 1) == 2) {
					return 2;
				}
			}
		}
		return 1;
	}

}
