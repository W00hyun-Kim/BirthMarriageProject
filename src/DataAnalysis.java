import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class DataAnalysis {

	public static void main(String[] args) throws IOException {
		
		
		/*
		 * 변수 선언부
		 */
		ArrayList<DataSet> dataList = new ArrayList<>(); //csv파일에서 읽어온 데이터를 저장하는 ArrayList
		HashSet<String> BoroughSet = new HashSet<>(); //자치구를 중복없이 담을 HashSet
		HashSet<String> ContractYearSet = new HashSet<>(); //계약년도를 중복없이 담을 HashSet
		HashSet<String> ContractMonthSet = new HashSet<>(); //계약월을 중복없이 담을 HashSet
		ArrayList<SimpleDataSet> simpleList = new ArrayList<>(); //파일 쓰기에 사용할 요약본을 저장하는 ArrayList

		File file = new File("C:\\Users\\user\\eclipse-workspace\\BirthMarriageProject\\csvFile\\아파트 매매가 (2017~2021).csv");
		BufferedReader reader = null; // BufferedReader 객체 생성
		
		try {
			reader = new BufferedReader(new FileReader(file)); //객체에 인스턴스 연결
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("폴더내에 파일이 존재하지 않습니다.");
			e.printStackTrace();
		}

		String line; //한줄씩 읽어올 String객체
		String tempBorough = ""; //임시로 자치구를 저장하는 변수
		double tempDedicatedArea = 0.0; //임시로 전용면적을 저장하는 변수
		int tempTransactionAmount = 0; //임시로 거래금액을 저장하는 변수
		String tempContractYear = ""; // 임시로 거래년도를 저장하는 변수
		String temmpContractMonth = ""; //임시로 거래월을 저장하는 변수
		int lineCounter = 0; //몇번째 줄인지 세는 변수
		
		
		/*
		 * 파일에서 데이터를 읽어와서 ArrayList에 저장하는 부분
		*/
		System.out.println("csv파일을 읽어오고 있습니다. 파일명:(" + file.getName() + ")");  //읽어올 파일명을 출력함.
		while ((line = reader.readLine()) != null) {  //읽어올 Line이 없을 때까지 루프
			if (lineCounter != 0) { //header가 있는 첫줄을 제외하고 ..

				String[] tempLine = line.split("\""); //큰따옴표를 기준으로 문자열을 나눔.

				if (tempLine.length == 5) { // 단지명에 ","가 적혀있는 경우
					tempLine[3] = tempLine[3].replace(",", ""); //거래금액에 있는 단위구분자를 제거함.
					tempTransactionAmount = Integer.parseInt(tempLine[3]); //거래금액을 임시로 저장함.
					String[] tempLine2 = tempLine[0].split(","); // tempLine배열의 첫번째 문자열을 ","단위로 문자열을 나눔.
					String[] tempLine3 = tempLine2[0].split(" "); //  tempLine2배열의 첫번째 문자열을 " "단위로 문자열을 나눔.
					tempBorough = tempLine3[1]; // 자치구정보를 임시로 저장함.
					String[] tempLine4 = tempLine[2].split(","); //tempLine배열의 3번째 문자열을 ","단위로 문자열을 나눔.
					tempDedicatedArea = Double.parseDouble(tempLine4[1]); //전용면적을 double형으로 임시 저장함.
					tempContractYear = tempLine4[2].substring(0, 4); // 거래년도를 임시 저장함.
					temmpContractMonth = tempLine4[2].substring(4, tempLine4[2].length()); //거래월을 임시 저장함.

				} else if (tempLine.length == 3) {// 단지명에 ","가 없는 경우
					tempLine[1] = tempLine[1].replace(",", ""); //거래금액에 있는 단위구분자를 제거함.
					tempTransactionAmount = Integer.parseInt(tempLine[1]); //거래금액을 임시로 저장함.
					String[] tempLine2 = tempLine[0].split(","); // tempLine배열의 첫번째 문자열을 ","단위로 문자열을 나눔.
					String[] tempLine3 = tempLine2[0].split(" ");  //  tempLine2배열의 첫번째 문자열을 " "단위로 문자열을 나눔.
					tempBorough = tempLine3[1]; // 자치구정보를 임시로 저장함.
					tempDedicatedArea = Double.parseDouble(tempLine2[5]);  //전용면적을 double형으로 임시 저장함.
					tempContractYear = tempLine2[6].substring(0, 4); // 거래년도를 임시 저장함.
					temmpContractMonth = tempLine2[6].substring(4, tempLine2[6].length()); //거래월을 임시 저장함.
				}
				dataList.add(new DataSet(tempBorough, tempDedicatedArea, tempTransactionAmount, tempContractYear,
						temmpContractMonth));
				//ArrayList에 DataSet객체를 생성하여 임시저장된 데이터를 토대로 추가함.
			}
			lineCounter++; //한줄 증가
		}
		System.out.println("csv파일을 모두 읽고 dataList에 저장하였습니다.");
		
		
		
		/*
		 * 중복 제거를 위하여 자치구, 거래년도, 거래월을 HashSet을 생성하여 저장하는 부분.
		 */
		
		System.out.println("자치구, 거래년도, 거래월 정보를 HashSet에 저장중입니다.");
		for (int i = 0; i < dataList.size(); i++) { // dataList의 크기만큼 루프
			BoroughSet.add(dataList.get(i).getBorough()); //자치구를 담는 HashSet에 add함.
			ContractYearSet.add(dataList.get(i).getContractYear()); //거래년도를 담는 HashSet에 add함.
			ContractMonthSet.add(dataList.get(i).getContractMonth()); //거래월을 담는 HashSet에 add함.
		}
		System.out.println("자치구, 거래년도, 거래월 정보를 HashSet에 모두 저장했습니다.");
		
		
		/*
		 * HashSet에 담긴 내용을 정렬하기 위해 List에 담고 Sort하는 부분.
		 */
		List BoroughList = new ArrayList(BoroughSet); //자치구를 담는 List
		List ContractYearList = new ArrayList(ContractYearSet); //거래년도를 담는 List
		List ContractMonthList = new ArrayList(ContractMonthSet); //거래월을 담는 List
		
		//정렬
		
		System.out.println("자치구, 거래년도, 거래월 정보를 정렬중입니다.");
		Collections.sort(BoroughList); 
		Collections.sort(ContractYearList);
		Collections.sort(ContractMonthList);
		System.out.println("자치구, 거래년도, 거래월 정보를 모두 정렬했습니다.");
		
		
		/*
		 * 정렬한 List 3개의 정보를 하나의 ArrayList에 담는 부분.
		 */
		System.out.println("정렬한 자치구, 거래년도, 거래월 정보를 SimpleList에 저장중입니다.");
		for (int i = 0; i < BoroughList.size(); i++) {
			for (int j = 0; j < ContractYearList.size(); j++) {
				for (int k = 0; k < ContractMonthList.size(); k++) {
					simpleList.add(new SimpleDataSet(BoroughList.get(i).toString(),ContractYearList.get(j).toString(),ContractMonthList.get(k).toString()));
				}
			}
		}
		System.out.println("정렬한 자치구, 거래년도, 거래월 정보를 SimpleList에 모두 저장했습니다.");
		
		/*
		 * SimpleList와 dataList의 정보를 비교하는 부분.
		 */
		 // SimpleList에 담은 자치구,거래년도,거래월이 dataList에 있는 정보와 일치할때, 단위면적당 금액을 합산하고, 개수를 1증가시킴. 
		
		System.out.println("자치구, 거래년도, 거래월을 기준으로 한 평균매매가를 계산중입니다.");
		for(int i = 0; i < dataList.size(); i++) {
			for(int j = 0; j < simpleList.size(); j++) {
				if(dataList.get(i).getBorough().equals(simpleList.get(j).getBorough()) && dataList.get(i).getContractYear().equals(simpleList.get(j).getContractYear()) && dataList.get(i).getContractMonth().equals(simpleList.get(j).getContractMonth())) {
					simpleList.get(j).setTotalsum(simpleList.get(j).getTotalsum() + dataList.get(i).getUnitAmount());
					simpleList.get(j).setCount(simpleList.get(j).getCount()+1);
				}
			}
		}
		
		//평균금액을 계산하여 simpleList에 저장함.
		for(int i = 0; i < simpleList.size(); i++) {
			simpleList.get(i).setAvg(simpleList.get(i).getTotalsum() / simpleList.get(i).getCount());
		}
		System.out.println("자치구, 거래년도, 거래월을 기준으로 한 평균매매가를 모두 저장하였습니다.");
		
		
		
		/*
		 * 파일에 ArrayList의 정보를 쓰는 부분
		 */
		
		
		System.out.println("파일에 simpleList의 정보를 받아 저장중입니다.");
		FileWriter fw = new FileWriter("C:\\Users\\user\\eclipse-workspace\\BirthMarriageProject\\csvFile\\서울시 아파트 매매가 데이터(2017_2021).csv", true); // FileWriter객체 생성후 파일지정하여 인스턴스 연결
		BufferedWriter bw = new BufferedWriter(fw); //BufferedWriter객체 생성 후 인스턴스 연결
		String header = "자치구,계약일자,단위면적(㎡)당 매매가(만원)\n"; //파일 header부분에 사용할 문자열
		bw.write(header); //header부분 버퍼에 write
		String resultLine = ""; //각 Line에 들어갈 내용을 담는 문자열
		
		for(int i = 0; i < simpleList.size(); i++) { //simpleList의 크기만큼 루프
			//자치구, 거래년도, 거래월, 평균금액을 문자열에 담음.
			resultLine = simpleList.get(i).getBorough()+","+simpleList.get(i).getContractYear()+"-"+simpleList.get(i).getContractMonth()+","+(int)(simpleList.get(i).getAvg())+"\n";
			bw.write(resultLine); //문자열 버퍼에 write
		}
		bw.flush(); // 버퍼를 flush함.
		bw.close(); // 버퍼쓰기 종료
		fw.close(); //파일쓰기 종료
		System.out.println("파일에 simpleList의 정보를 받아 모두 저장하였습니다.");
	}
}
