
public class DataSet {
	//원본 csv파일에서 데이터를 읽어와 저장하는 형식Class
	
	private String borough; //자치구
	private double dedicatedArea; //전용면적(m2)
	private int transactionAmount; //거래금액(만원)
	private String contractYear; //계약년
	private String contractMonth ; // 계약월
	private double unitAmount; //단위면적당 금액
	

	public DataSet() {
		this.setBorough("");
		this.setDedicatedArea(0.0);
		this.setTransactionAmount(0);
		this.setContractYear("");
		this.setContractMonth("");
		this.setUnitAmount(0.0);
	}

	public DataSet(String borough, double dedicatedArea, int transactionAmount, String contractYear, String contractMonth) {
		this.setBorough(borough);
		this.setDedicatedArea(dedicatedArea);
		this.setTransactionAmount(transactionAmount);
		this.setContractYear(contractYear);
		this.setContractMonth(contractMonth);
		this.setUnitAmount(transactionAmount / dedicatedArea);
	}

	public String getBorough() {
		return borough;
	}

	public void setBorough(String borough) {
		this.borough = borough;
	}

	public double getDedicatedArea() {
		return dedicatedArea;
	}

	public void setDedicatedArea(double dedicatedArea) {
		this.dedicatedArea = dedicatedArea;
	}

	public int getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(int transactionAmount) {
		this.transactionAmount = transactionAmount;
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

	public double getUnitAmount() {
		return unitAmount;
	}

	public void setUnitAmount(double unitAmount) {
		
		this.unitAmount = unitAmount;
	}


}
