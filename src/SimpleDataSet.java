
public class SimpleDataSet {
	//파일 쓰기에 사용하는 간략히 정리된 형식의 Class
	
	private String borough; //자치구
	private String contractYear; //계약년
	private String contractMonth ; // 계약월
	private double totalsum; // 단위면적당 금액 합계
	private int count; // 개수
	private double avg; // 평균

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
