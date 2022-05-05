
public class SimpleDataSet {
	//파일 쓰기에 사용하는 간략히 정리된 형식의 Class
	
	public String borough; //자치구
	public String contractYear; //계약년
	public String contractMonth ; // 계약월
	public double totalsum; // 단위면적당 매매액 합계
	public int count; // 개수
	public double avg; // 평균

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
