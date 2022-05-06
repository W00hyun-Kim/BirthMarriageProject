package Parsing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class Birth {
	static ArrayList<String> list = new ArrayList<String>();

	public static String[] str;

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\whKim\\Desktop\\PythonProject\\Seoul-Apt\\data\\플젝\\서울시 출생 데이터(2017~2021).csv"));
			String line;
			String tmp;
			while ((line = br.readLine()) != null) {
				str = line.split(",");				//행정구가 먼저오게 자리를 바꿔줌
				tmp = str[0];
				str[0] = str[1];
				str[1] = tmp;
				
				//1~9월 앞에 0 붙이기
				if(str[2].length()==2) {
					str[2] = "0"+str[2];
				}
				
				String change = "";
				for (int i = 0; i < str.length; i++) {
					if(i==str.length-1) {
						change = change + str[i];
					} else {
						change = change + str[i] + ",";						
					}
				}
				list.add(change);
			}
			
			//sorting
			Collections.sort(list);
			
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		//형식 맞춘거 다시 저장하기
		File csv = new File("C:\\Users\\whKim\\Desktop\\PythonProject\\Seoul-Apt\\data\\플젝\\(형식맞춘)서울시 출생 데이터(2017~2021).csv");
        BufferedWriter bw = null; 
		try {
			bw = new BufferedWriter(new FileWriter(csv, true));
			
			for (int i = 0; i < list.size(); i++) {
				bw.write(list.get(i));
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
