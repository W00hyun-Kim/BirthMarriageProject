
public class SimpleDataSet {
	//���� ���⿡ ����ϴ� ������ ������ ������ Class
	
	public String borough; //��ġ��
	public String contractYear; //����
	public String contractMonth ; // ����
	public double totalsum; // ���������� �Ÿž� �հ�
	public int count; // ����
	public double avg; // ���

	public SimpleDataSet() {
		this.borough = "";
		this.contractYear = "";
		this.contractMonth  = "";
		this.totalsum = 0.0;
		this.count = 0;
		this.avg = 0.0;
	}

	public SimpleDataSet(String borough, String contractYear, String contractMonth) {
		this.borough = borough;
		this.contractYear = contractYear;
		this.contractMonth = contractMonth;
	}
}
