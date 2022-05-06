
public class SimpleDataSet {
	//���� ���⿡ ����ϴ� ������ ������ ������ Class
	
	private String borough; //��ġ��
	private String contractYear; //����
	private String contractMonth ; // ����
	private double totalsum; // ���������� �ݾ� �հ�
	private int count; // ����
	private double avg; // ���

	public SimpleDataSet() {
		this.setBorough("");
		this.setContractYear("");
		this.setContractMonth("");
		this.setTotalsum(0.0);
		this.setCount(0);
		this.setAvg(0.0);
	}

	public SimpleDataSet(String borough, String contractYear, String contractMonth) {
		this.setBorough(borough);
		this.setContractYear(contractYear);
		this.setContractMonth(contractMonth);
	}

	public String getBorough() {
		return borough;
	}

	public void setBorough(String borough) {
		this.borough = borough;
	}

	public String getContractYear() {
		return contractYear;
	}

	public void setContractYear(String contractYear) {
		this.contractYear = contractYear;
	}

	public String getContractMonth() {
		return contractMonth;
	}

	public void setContractMonth(String contractMonth) {
		this.contractMonth = contractMonth;
	}

	public double getTotalsum() {
		return totalsum;
	}

	public void setTotalsum(double totalsum) {
		this.totalsum = totalsum;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}
}
