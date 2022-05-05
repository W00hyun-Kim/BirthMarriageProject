
public class DataSet {
	//���� csv���Ͽ��� �����͸� �о�� �����ϴ� ����Class
	
	private String borough; //��ġ��
	private double dedicatedArea; //�������(m2)
	private int transactionAmount; //�ŷ��ݾ�(����)
	private String contractYear; //����
	private String contractMonth ; // ����
	private double unitAmount; //���������� �ݾ�
	

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
