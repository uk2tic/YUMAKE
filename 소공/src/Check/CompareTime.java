package Check;

public class CompareTime {	//강의시간비교(겹치면 return 2)
	
	public static int compareTimeData(int startTime1, int finishTime1, int startTime2, int finishTime2) {
		if (startTime2 >= startTime1 && startTime2 <= finishTime1) {
			return 2;
		} else if (finishTime2 <= startTime1 && finishTime2 >= finishTime1) {
			return 2;
		}

		if (startTime1 >= startTime2 && startTime1 <= finishTime2) {
			return 2;
		} else if (finishTime1 <= startTime2 && finishTime1 >= finishTime2) {
			return 2;
		}

		return 1;
	}

}
