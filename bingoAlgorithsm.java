package bingoAlgorithsm;

public class bingoAlgorithsm {
	// 5 X 5 빙고
	final static int SIZE_OF_BINGO = 5;

	// 뽑아야하는 종이수
	final static int NUM_OF_SHEET = 2;

	// 빙고에 들어갈 수 있는 정수의 범위
	final static int RANGE_OF_INT = SIZE_OF_BINGO * SIZE_OF_BINGO;

	// 카운트
	final static int COUNT = 1;

	int[] randomArray(int lengthOfArray, int rangeOfNum) {
		// 중복 안되는 수로 배열 구성
		int[] num = new int[lengthOfArray];
		for (int i = 0; i < num.length; i++) {
			int randomValue = (int) (Math.random() * rangeOfNum + 1);
			for (int j = 0; j < num.length; j++) {
				if (num[j] == randomValue) {
					randomValue = (int) (Math.random() * rangeOfNum + 1);
					j = -1;
				}
			}
			// 중복 안된 수 넣기
			num[i] = randomValue;
		}
		return num;
	}

	// 무작위로 빙고 뽑아주는 매서드
	public int[][][] bingo() {

		// 빙고 값 담을 3차원 배열
		int[][][] bingo = new int[NUM_OF_SHEET][SIZE_OF_BINGO][SIZE_OF_BINGO];

		// 중복 확인 및 값 삽입
		allSheet: for (int sheet_index = 0; sheet_index < NUM_OF_SHEET; sheet_index++) {
			// 번호 담긴 1차원 배열 뽑기
			int[] r_array = randomArray(SIZE_OF_BINGO * SIZE_OF_BINGO, RANGE_OF_INT);

			// 기존에 뽑았던 종이를 가져와 비교한다.
			checkSheet: for (int check_index = 0; check_index <= sheet_index; check_index++) {

				// 수 가져오기
				for (int number_index = 0; number_index < r_array.length; number_index++) {

					int row = number_index / SIZE_OF_BINGO;
					int col = number_index % SIZE_OF_BINGO;
					// 다른 값이 있다면
					if (bingo[check_index][row][col] != r_array[number_index]) {

						// 다음 장 넘어가기
						continue checkSheet;
					}

				}
				// 다음 장 넘어가지 않고 끝나면 같은 빙고이므로
				// sheet_index 초기화 및 반복문 탈출
				sheet_index = -1;
				continue allSheet;

			}
			// 비교가 끝나면 집어 넣기
			for (int i = 0; i < r_array.length; i++) {
				int col = i / SIZE_OF_BINGO;
				int row = i % SIZE_OF_BINGO;
				bingo[sheet_index][col][row] = r_array[i];
			}
		}
		return bingo;
	}

	// 입력 받은 값에 해당하는 빙고의 값을 0 매핑해주는 메서드
	static void Mapping(int[][] argArr, int argInputValue) {

		for (int row = 0; row < argArr.length; row++) {// 행
			for (int col = 0; col < argArr.length; col++) {// 열

				// 입력 받은 값과 같은 값이 있다면 0으로 매핑
				if (argArr[row][col] == argInputValue) {
					argArr[row][col] = 0;
					return;
				}
			}
		}
	}

	// 빙고값이 있는 지 확인해주는 매서드
	static boolean IsBingo(int[][] argArr) {

		boolean isBingo = false;

		// 빙고값 체크
		for (int row = 0; row < argArr.length && !isBingo; row++) {

			// 처음엔 대각선 부터 확인
			if (row == 0) {
				int backslash = 0; // 백슬래쉬 방향 빙고
				int slash = 0; // 슬래쉬 방향 빙고

				for (int i = 0; i < argArr.length; i++) {
					backslash += argArr[i][i] == 0 ? COUNT : 0;
					slash += argArr[i][argArr.length - 1 - i] == 0 ? COUNT : 0;
				}

				if (slash == SIZE_OF_BINGO || backslash == SIZE_OF_BINGO) {
					return true;
				}
			}

			int colBingo = 0; // 가로 빙고
			int rowBingo = 0; // 세로 빙고

			for (int col = 0; col < argArr.length; col++) {
				// 0인 값이 나오면 더해줌
				rowBingo += argArr[row][col] == 0 ? COUNT : 0;
				colBingo += argArr[col][row] == 0 ? COUNT : 0;

			}
			// bingo 발생
			if (colBingo == SIZE_OF_BINGO || rowBingo == SIZE_OF_BINGO) {
				return true;
			}
		}

		// 빙고값이 없는 경우
		return false;
	}
// 빙고 뽑아주기
	// 전체 빙고 용지를 담을 2차원 배열

	// 중복되지 않는 빙고 반환

	// 무작위의 값 뽑기

	// if(isIn)
	// 인덱스 나눠서 row, col 조작
	// 이전 배열의 같은 인덱스에 값과 비교

	// 다르다면 다른 빙고가 된다.
	// isIn = false

	// 1차원 배열에 넣어주기
	// 0번째는 해당 유저의 빙고로 설정
	// 1번째는 상대방의 빙고로 설정

// 빙고 게임

	// 값 입력받기

	// 빙고값들이 있는 배열을

	public static void main(String[] args) {

	}
}

// 중간 행인 경우 - 대각선 빙고 확인
//if (row == arr.length / 2) {					
//	slash += arr[col][col] == 0 ? 1 : 0;
//	backslash += arr[arr.length - 1 - col][col] == 0 ? 1 : 0;
//	
//	// 빙고 발생
//	if (slash == SIZE_OF_BINGO || backslash == SIZE_OF_BINGO) {
//		isBingo = true;
//		break;
//	}
//}
//
//
//	// 빙고값을 담을 맴버 변수
//	int[][][] bingo;
//
//	// 중복되지 않는 2차원 배열의 빙고들을 생성
//	bingoAlgorithsm(int numOfUser) {
//		bingo = new int[numOfUser][SIZE_OF_BINGO][SIZE_OF_BINGO];
//
//		// 중복되지 않는 1차원 랜덤 배열을 원소로 가지는 2차원 배열
//		int[][] randomArrays = new int[numOfUser][SIZE_OF_BINGO * SIZE_OF_BINGO];
//
//		for (int randArrIndex = 0; randArrIndex < randomArrays.length; randArrIndex++) {
//			
//			// 중복되지 않는 1차원 배열 뽑기
//			int[] randscala = new int[SIZE_OF_BINGO * SIZE_OF_BINGO];
//			for (int i = 0; i < randscala.length; i++) {
//				int randomValue = (int) (Math.random() * SIZE_OF_BINGO * SIZE_OF_BINGO + 1);
//				// 이전 값과 중복인지 확인
//				for (int j = 0; j < i; j++) {
//					if (randscala[j] == randomValue) {
//						j = -1;
//						randomValue = (int) (Math.random() * SIZE_OF_BINGO * SIZE_OF_BINGO + 1);
//					}
//				}
//				// 랜덤값 담기
//				randscala[i] = randomValue;
//			}
//			// 이전 배열과 중복인지 확인
//			for (int i = 0; i < randomArrays.length; i ++) {
//				for (int )
//			}
//		}
//
//		// 빙고에 값 담기
//	}