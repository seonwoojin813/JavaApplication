import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author a503_07
 * 네이버 tv에서 프로그램 제목을 가져오는 클래스 
 */

public class HTMLParsing {
	
	/**
	 * 
	 * @param args
	 * html 파싱을 수행해주는 메소드 
	 */

	public static void main(String[] args) {
		//다운로드 받을 문자열을 저장할 변수
		String html = null;
		try {
			//문자열을 다운로드 받을 URL 생성 
			String addr = "https://tv.naver.com/r/";
			URL url = new URL(addr);
			
			//URL 연결 객체 생성
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setConnectTimeout(30000);
			con.setUseCaches(false);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			//데이터 다운로드 받기 
			StringBuilder sb = new StringBuilder();
			while(true) {
				String line = br.readLine();
				if(line == null) {
					break;
				}
				sb.append(line+"\n");
			}
			html = sb.toString();
			br.close();
			con.disconnect();
			
			//System.out.printf("%s\n", html);
			
		}catch(Exception e) {
			System.out.printf("download exception: %s\n",e.getMessage());
			e.printStackTrace();
		}
		
		if(html != null) {
			
			//html을 메모리에 펼쳐내
			Document document = Jsoup.parse(html);
			
			//tit 클래스의 모든 내용 가져오기
			Elements tit = document.getElementsByClass("tit");
			//확인 System.out.printf("%s\n", tit);
			
			for(int i=0; i<tit.size(); i=i+1) {
				//데이터를 하나씩 가져오기
				Element element = tit.get(i);
				
				
				//span 태그의 내용을 전부 가져오기 
				Elements span = element.getElementsByTag("span");
				//확인 System.out.printf("%s\n",span);
				for(int j=0; j<span.size(); j=j+1) {
					Element x = span.get(j);
					System.out.printf("%s\n", x.text());
				}
				
				
				Elements tooltip = document.getElementsByTag("tooltip");
				//확인 System.out.printf("%s\n",span);
				for(int k=0; k<tooltip.size(); k=k+1) {
					Element x = tooltip.get(k);
					System.out.printf("%s\n", x.text());
				}
				
				
			}
			
			
		}

	}

}
