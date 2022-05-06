package Parsing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CombineOne {
//(파싱)서울시 아파트 매매가 데이터(2017_2021)
	static ArrayList<String> aptlist = new ArrayList<String>();
	static ArrayList<String> rentList = new ArrayList<String>();
	static ArrayList<String> birthList = new ArrayList<String>();
	public static String[] str;

	public static void main(String[] args) {
		try {
			BufferedReader br2 = new BufferedReader(new FileReader("C:\\Users\\whKim\\Desktop\\PythonProject\\Seoul-Apt\\data\\플젝\\(형식맞춘)서울시 출생 데이터(2017~2021).csv"));			
			BufferedReader br3 = new BufferedReader(new FileReader("C:\\Users\\whKim\\Desktop\\PythonProject\\Seoul-Apt\\data\\플젝\\서울시 아파트 매매가 데이터(2017_2021).csv"));
			BufferedReader br4 = new BufferedReader(new FileReader("C:\\Users\\whKim\\Desktop\\PythonProject\\Seoul-Apt\\data\\플젝\\서울시 아파트 전세가 데이터(2017_2021).csv"));
			
			String aptline;
			//아파트매매가 리스트에 넣어두기
			while ((aptline = br3.readLine()) != null) {
				aptlist.add(aptline);				
			}
			
			//전세가 쪼개서 매매가 뒤에 붙이기
			String rentLine;
			while ((rentLine = br4.readLine()) != null) {
				str = rentLine.split(",");
				String samePart = str[0]+","+str[1];
					
				for (int i = 0; i < aptlist.size(); i++) {
					if(aptlist.get(i).contains(samePart)) {
						rentList.add(aptlist.get(i)+","+str[2]);
					} else {
						continue;
					}
				}				
			}
			
			
			//출생 + 매매가 파일을 합치기
			String category = "자치구, 일자, 단위면적(㎡)당 매매가(만원),단위면적(㎡)당 전세가(만원), 출생자 수";
			birthList.add(category);
			String birth;
			while ((birth = br2.readLine()) != null) {
				str = birth.split(",");
				String samePart = str[0]+","+str[1];
					
				for (int i = 0; i < rentList.size(); i++) {
					if(rentList.get(i).contains(samePart)) {
						birthList.add(rentList.get(i)+","+str[2]);
					} else {
						continue;
					}
				}				
			}
			
			for (int i = 0; i < birthList.size(); i++) {
				System.out.println(birthList.get(i));
			}
					
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//파일쓰기 (3개의 파일 합친거)
		File csv = new File("C:\\Users\\whKim\\Desktop\\PythonProject\\Seoul-Apt\\data\\플젝\\(combined)매매가+출생(2017~2021).csv");
        BufferedWriter bw = null; 
		try {
			bw = new BufferedWriter(new FileWriter(csv, true));
			for (int i = 0; i < birthList.size(); i++) {
				bw.write(birthList.get(i));
				bw.newLine();
			}
			
			
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.flush(); 
                    bw.close(); 
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

	}

}
