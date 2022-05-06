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

public class DataAnalysis2 {

	public static void main(String[] args) throws IOException {

		/*
		 * ���� �����
		 */
		ArrayList<DataSet> dataList = new ArrayList<>(); // csv���Ͽ��� �о�� �����͸� �����ϴ� ArrayList
		HashSet<String> BoroughSet = new HashSet<>(); // ��ġ���� �ߺ����� ���� HashSet
		HashSet<String> ContractYearSet = new HashSet<>(); // ���⵵�� �ߺ����� ���� HashSet
		HashSet<String> ContractMonthSet = new HashSet<>(); // ������ �ߺ����� ���� HashSet
		ArrayList<SimpleDataSet> simpleList = new ArrayList<>(); // ���� ���⿡ ����� ��ົ�� �����ϴ� ArrayList

		File file = new File("C:\\Users\\user\\eclipse-workspace\\BirthMarriageProject\\csvFile\\����Ʈ ���� (2017~2021).csv");
		BufferedReader reader = null; // BufferedReader ��ü ����

		try {
			reader = new BufferedReader(new FileReader(file)); // ��ü�� �ν��Ͻ� ����
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("�������� ������ �������� �ʽ��ϴ�.");
			e.printStackTrace();
		}

		String line; // ���پ� �о�� String��ü
		String tempBorough = ""; // �ӽ÷� ��ġ���� �����ϴ� ����
		double tempDedicatedArea = 0.0; // �ӽ÷� ��������� �����ϴ� ����
		int tempTransactionAmount = 0; // �ӽ÷� �ŷ��ݾ��� �����ϴ� ����
		String tempContractYear = ""; // �ӽ÷� �ŷ��⵵�� �����ϴ� ����
		String temmpContractMonth = ""; // �ӽ÷� �ŷ����� �����ϴ� ����
		int lineCounter = 0; // ���° ������ ���� ����

		/*
		 * ���Ͽ��� �����͸� �о�ͼ� ArrayList�� �����ϴ� �κ�
		 */
		System.out.println("csv������ �о���� �ֽ��ϴ�. ���ϸ�:(" + file.getName() + ")"); // �о�� ���ϸ��� �����.
		while ((line = reader.readLine()) != null) { // �о�� Line�� ���� ������ ����
			if (lineCounter != 0) { // header�� �ִ� ù���� �����ϰ� ..

				String[] tempLine = line.split("\""); // ū����ǥ�� �������� ���ڿ��� ����.

				if (tempLine.length == 5) { //�������� �ּҿ� ū����ǥ�� �ִ� ���
					String[] tempLine2 = tempLine[2].split(",");
						String[] tempLine3 = tempLine[0].split(" ");
						tempBorough = tempLine3[1];
						tempDedicatedArea = Double.parseDouble(tempLine2[2]);
						tempContractYear = tempLine2[3].substring(0,4);
						temmpContractMonth = tempLine2[3].substring(4, tempLine2[3].length());
						tempTransactionAmount = Integer.parseInt(tempLine[3].replace(",", ""));	
						
				} else if (tempLine.length == 3) { //������ �Ǵ� �ּҿ� ū����ǥ�� �ִ� ���
					String[] tempLine2 = tempLine[0].split(",");
					if(tempLine2.length == 4) { //�ּҿ� ū����ǥ�� �ִ°��
						String[] tempLine3 = tempLine2[0].split(" ");
						tempBorough = tempLine3[1];
						String[] tempLine4 = tempLine2[2].split(",");
						tempDedicatedArea = Double.parseDouble(tempLine4[2]);
						tempContractYear = tempLine4[3].substring(0,4);
						temmpContractMonth = tempLine4[3].substring(4,tempLine4[3].length());
						tempTransactionAmount = Integer.parseInt(tempLine4[5]);
					}else { // �������� ū����ǥ�� �ִ� ���
						String[] tempLine3 = tempLine2[0].split(" ");
						tempBorough = tempLine3[1];
						tempDedicatedArea = Double.parseDouble(tempLine2[6]);
						tempContractYear = tempLine2[7].substring(0,4);
						temmpContractMonth = tempLine2[7].substring(4,tempLine2[7].length());
						tempTransactionAmount = Integer.parseInt(tempLine[1].replace(",", ""));	
					}

				}else if (tempLine.length == 1) { // ū����ǥ�� ���� ���
					String[] tempLine2 = tempLine[0].split(",");
					String[] tempLine3 = tempLine2[0].split(" ");
					tempBorough = tempLine3[1];
					tempDedicatedArea = Double.parseDouble(tempLine2[6]);
					tempContractYear = tempLine2[7].substring(0,4);
					temmpContractMonth = tempLine2[7].substring(4,tempLine2[7].length());
					tempTransactionAmount = Integer.parseInt(tempLine2[9]);
					
					
				}
				dataList.add(new DataSet(tempBorough, tempDedicatedArea, tempTransactionAmount, tempContractYear,
						temmpContractMonth));
				// ArrayList�� DataSet��ü�� �����Ͽ� �ӽ������ �����͸� ���� �߰���.
			}
			lineCounter++; // ���� ����
		}
		System.out.println("csv������ ��� �а� dataList�� �����Ͽ����ϴ�.");

		/*
		 * �ߺ� ���Ÿ� ���Ͽ� ��ġ��, �ŷ��⵵, �ŷ����� HashSet�� �����Ͽ� �����ϴ� �κ�.
		 */

		System.out.println("��ġ��, �ŷ��⵵, �ŷ��� ������ HashSet�� �������Դϴ�.");
		for (int i = 0; i < dataList.size(); i++) { // dataList�� ũ�⸸ŭ ����
			BoroughSet.add(dataList.get(i).getBorough()); // ��ġ���� ��� HashSet�� add��.
			ContractYearSet.add(dataList.get(i).getContractYear()); // �ŷ��⵵�� ��� HashSet�� add��.
			ContractMonthSet.add(dataList.get(i).getContractMonth()); // �ŷ����� ��� HashSet�� add��.
		}
		System.out.println("��ġ��, �ŷ��⵵, �ŷ��� ������ HashSet�� ��� �����߽��ϴ�.");

		/*
		 * HashSet�� ��� ������ �����ϱ� ���� List�� ��� Sort�ϴ� �κ�.
		 */
		List BoroughList = new ArrayList(BoroughSet); // ��ġ���� ��� List
		List ContractYearList = new ArrayList(ContractYearSet); // �ŷ��⵵�� ��� List
		List ContractMonthList = new ArrayList(ContractMonthSet); // �ŷ����� ��� List

		// ����

		System.out.println("��ġ��, �ŷ��⵵, �ŷ��� ������ �������Դϴ�.");
		Collections.sort(BoroughList);
		Collections.sort(ContractYearList);
		Collections.sort(ContractMonthList);
		System.out.println("��ġ��, �ŷ��⵵, �ŷ��� ������ ��� �����߽��ϴ�.");

		/*
		 * ������ List 3���� ������ �ϳ��� ArrayList�� ��� �κ�.
		 */
		System.out.println("������ ��ġ��, �ŷ��⵵, �ŷ��� ������ SimpleList�� �������Դϴ�.");
		for (int i = 0; i < BoroughList.size(); i++) {
			for (int j = 0; j < ContractYearList.size(); j++) {
				for (int k = 0; k < ContractMonthList.size(); k++) {
					simpleList.add(new SimpleDataSet(BoroughList.get(i).toString(), ContractYearList.get(j).toString(),
							ContractMonthList.get(k).toString()));
				}
			}
		}
		System.out.println("������ ��ġ��, �ŷ��⵵, �ŷ��� ������ SimpleList�� ��� �����߽��ϴ�.");

		/*
		 * SimpleList�� dataList�� ������ ���ϴ� �κ�.
		 */
		// SimpleList�� ���� ��ġ��,�ŷ��⵵,�ŷ����� dataList�� �ִ� ������ ��ġ�Ҷ�, ���������� �ݾ��� �ջ��ϰ�, ������
		// 1������Ŵ.

		System.out.println("��ġ��, �ŷ��⵵, �ŷ����� �������� �� ����������� ������Դϴ�.");
		for (int i = 0; i < dataList.size(); i++) {
			for (int j = 0; j < simpleList.size(); j++) {
				if (dataList.get(i).getBorough().equals(simpleList.get(j).getBorough())
						&& dataList.get(i).getContractYear().equals(simpleList.get(j).getContractYear())
						&& dataList.get(i).getContractMonth().equals(simpleList.get(j).getContractMonth())) {
					simpleList.get(j).setTotalsum(simpleList.get(j).getTotalsum() + dataList.get(i).getUnitAmount());
					simpleList.get(j).setCount(simpleList.get(j).getCount() + 1);
				}
			}
		}

		// ��ձݾ��� ����Ͽ� simpleList�� ������.
		for (int i = 0; i < simpleList.size(); i++) {
			simpleList.get(i).setAvg(simpleList.get(i).getTotalsum() / simpleList.get(i).getCount());
		}
		System.out.println("��ġ��, �ŷ��⵵, �ŷ����� �������� �� ����������� ��� �����Ͽ����ϴ�.");

		/*
		 * ���Ͽ� ArrayList�� ������ ���� �κ�
		 */

		System.out.println("���Ͽ� simpleList�� ������ �޾� �������Դϴ�.");
		FileWriter fw = new FileWriter(
				"C:\\Users\\user\\eclipse-workspace\\BirthMarriageProject\\csvFile\\����� ����Ʈ ������ ������(2017_2021).csv",
				true); // FileWriter��ü ������ ���������Ͽ� �ν��Ͻ� ����
		BufferedWriter bw = new BufferedWriter(fw); // BufferedWriter��ü ���� �� �ν��Ͻ� ����
		String header = "��ġ��,�������,��������(��)�� ������(����)\n"; // ���� header�κп� ����� ���ڿ�
		bw.write(header); // header�κ� ���ۿ� write
		String resultLine = ""; // �� Line�� �� ������ ��� ���ڿ�

		for (int i = 0; i < simpleList.size(); i++) { // simpleList�� ũ�⸸ŭ ����
			// ��ġ��, �ŷ��⵵, �ŷ���, ��ձݾ��� ���ڿ��� ����.
			resultLine = simpleList.get(i).getBorough() + "," + simpleList.get(i).getContractYear() + "-" + simpleList.get(i).getContractMonth()
					+ "," + (int)(simpleList.get(i).getAvg()) + "\n";
			bw.write(resultLine); // ���ڿ� ���ۿ� write
		}
		bw.flush(); // ���۸� flush��.
		bw.close(); // ���۾��� ����
		fw.close(); // ���Ͼ��� ����
		System.out.println("���Ͽ� simpleList�� ������ �޾� ��� �����Ͽ����ϴ�.");
	}
}
