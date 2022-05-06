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
	static ArrayList<String> apt_marriage = new ArrayList<String>();
	static ArrayList<String> apt_marriage_birth = new ArrayList<String>();
	public static String[] str;

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\whKim\\Desktop\\PythonProject\\Seoul-Apt\\data\\플젝\\(형식맞춘)서울시 혼인 데이터(2017~2021).csv"));
			BufferedReader br2 = new BufferedReader(new FileReader("C:\\Users\\whKim\\Desktop\\PythonProject\\Seoul-Apt\\data\\플젝\\(형식맞춘)서울시 출생 데이터(2017~2021).csv"));			
			BufferedReader br3 = new BufferedReader(new FileReader("C:\\Users\\whKim\\Desktop\\PythonProject\\Seoul-Apt\\data\\플젝\\(파싱)서울시 아파트 매매가 데이터(2017_2021).csv"));
			String aptline;
			while ((aptline = br3.readLine()) != null) {
				aptlist.add(aptline);				
			}
			//혼인과 매매가를 파일 합치기
			String marriageline;
			while ((marriageline = br.readLine()) != null) {
				str = marriageline.split(",");
				String samePart = str[0]+","+str[1]+","+str[2];
					
				for (int i = 0; i < aptlist.size(); i++) {
					if(aptlist.get(i).contains(samePart)) {
						apt_marriage.add(aptlist.get(i)+","+str[3]);
					} else {
						continue;
					}
				}				
			}
			//출생과 혼인+매매가 파일을 합치기
			String category = "자치구, 매매일자, 매매일자(월), 단위면적(㎡)당 매매가(만원), 혼인 건수, 출생자 수";
			apt_marriage_birth.add(category);
			String birth;
			while ((birth = br2.readLine()) != null) {
				str = birth.split(",");
				String samePart = str[0]+","+str[1]+","+str[2];
					
				for (int i = 0; i < apt_marriage.size(); i++) {
					if(apt_marriage.get(i).contains(samePart)) {
						apt_marriage_birth.add(apt_marriage.get(i)+","+str[3]);
					} else {
						continue;
					}
				}				
			}
			
			for (int i = 0; i < apt_marriage_birth.size(); i++) {
				System.out.println(apt_marriage_birth.get(i));
			}
					
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//파일쓰기 (3개의 파일 합친거)
		File csv = new File("C:\\Users\\whKim\\Desktop\\PythonProject\\Seoul-Apt\\data\\플젝\\(combined)매매가+혼인+출생(2017~2021).csv");
        BufferedWriter bw = null; 
		try {
			bw = new BufferedWriter(new FileWriter(csv, true));
			for (int i = 0; i < apt_marriage_birth.size(); i++) {
				bw.write(apt_marriage_birth.get(i));
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
