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
//(�Ľ�)����� ����Ʈ �ŸŰ� ������(2017_2021)
	static ArrayList<String> aptlist = new ArrayList<String>();
	static ArrayList<String> rentList = new ArrayList<String>();
	static ArrayList<String> birthList = new ArrayList<String>();
	public static String[] str;

	public static void main(String[] args) {
		try {
			BufferedReader br2 = new BufferedReader(new FileReader("C:\\Users\\whKim\\Desktop\\PythonProject\\Seoul-Apt\\data\\����\\(���ĸ���)����� ��� ������(2017~2021).csv"));			
			BufferedReader br3 = new BufferedReader(new FileReader("C:\\Users\\whKim\\Desktop\\PythonProject\\Seoul-Apt\\data\\����\\����� ����Ʈ �ŸŰ� ������(2017_2021).csv"));
			BufferedReader br4 = new BufferedReader(new FileReader("C:\\Users\\whKim\\Desktop\\PythonProject\\Seoul-Apt\\data\\����\\����� ����Ʈ ������ ������(2017_2021).csv"));
			
			String aptline;
			//����Ʈ�ŸŰ� ����Ʈ�� �־�α�
			while ((aptline = br3.readLine()) != null) {
				aptlist.add(aptline);				
			}
			
			//������ �ɰ��� �ŸŰ� �ڿ� ���̱�
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
			
			
			//��� + �ŸŰ� ������ ��ġ��
			String category = "��ġ��, ����, ��������(��)�� �ŸŰ�(����),��������(��)�� ������(����), ����� ��";
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
		
		//���Ͼ��� (3���� ���� ��ģ��)
		File csv = new File("C:\\Users\\whKim\\Desktop\\PythonProject\\Seoul-Apt\\data\\����\\(combined)�ŸŰ�+���(2017~2021).csv");
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
