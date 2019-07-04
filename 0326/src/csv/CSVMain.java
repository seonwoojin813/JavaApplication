package csv;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

public class CSVMain {

	public static void main(String[] args) {
		//유효성 검사할 내용을 생성
		CellProcessor [] processor = new CellProcessor[] {
				new NotNull (),
				new ParseInt(new NotNull()),
				new ParseDate("yyyy/mm/dd"),
				new NotNull(),
				new Optional()
		};
		
		//파일에서 읽어온 내용을 저장할 리스트를 생성
		List<Employee> list = new ArrayList < Employee >();
		
		//읽어올 파일의 경로 만들기
		Path path = Paths.get("./employee.csv");
		
		//파일을 읽어서 저장
		try(ICsvBeanReader beanReader = new CsvBeanReader(
				Files.newBufferedReader(path),
				CsvPreference.STANDARD_PREFERENCE)){
			
			//첫번째 줄이 컬럼의 이름일 때 컬럼이름을 읽어오기
			String [] headers = beanReader.getHeader(true);
			//한 줄씩 읽어서 List에 저장
			Employee employee = null;
			while((employee = beanReader.read(
					Employee.class, headers, processor)) != null){
						list.add(employee);
					}
			//데이터 출력
			for(String header : headers) {
				System.out.printf("%s\t",header);
			}
			System.out.println();
			
			list.stream().forEach(System.out::println);
			
		}catch(Exception e) {
			System.out.printf("%s\n", e.getMessage());
			e.printStackTrace();
		}
		
		//기록할 파일의 경로를 생성
		path = Paths.get("./empoyeecopy.csv");
		//헤더 만들기
		String [] header = {"name","age","birth","email","note"};
		//유효성 검사 조건 만들기
		CellProcessor [] processor1 = new CellProcessor[] {
				new NotNull(),
				new ParseInt(new NotNull()),
				new NotNull(),
				new NotNull(),
				new Optional()
		};
		//데이터 기록하기 
		try(ICsvBeanWriter beanWriter = new CsvBeanWriter(
				Files.newBufferedWriter(path),
				CsvPreference.STANDARD_PREFERENCE)){
			//제목을 기록
			beanWriter.writeHeader(header);
			//데이터 기록
			for(Employee employee : list) {
				beanWriter.write(employee, header, processor1);
			}
			
		}catch(Exception e) {
			System.out.printf("%s\n", e.getMessage());
			e.printStackTrace();
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}

}
