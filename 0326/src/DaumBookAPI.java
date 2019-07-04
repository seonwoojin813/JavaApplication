import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

public class DaumBookAPI {

	public static void main(String[] args) {
		//책제목만 읽어오기 
		String json = null;
		
		//1.다음 오픈 API의 책 검색 데이터 가져오기
		try {
			//url에 한글이 있을 때는 인코딩 설정
			String query = URLEncoder.encode("보노보노", "utf8");
			
			//다운로드 받을 주소 만들기
			String addr = 
					"https://dapi.kakao.com/v3/search/book?size=3&sort=latest&target=title&query="+query;
			//URL 만들기 
			URL url = new URL(addr);
			//URLConnection 만들기 - 연결
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			//연결 옵션 설정
			con.setUseCaches(false); //이전 데이터를 저장해두고 사용하는 옵션
			con.setConnectTimeout(30000); // 30초 동안 접속을 시도
			//헤더 설정
			con.addRequestProperty("Authorization","KakaoAK abde210bcf1482975aa094c742715e88");
			
			//문자열을 읽어올 스트림을 생성
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			//줄단위로 읽어온 문자열을 저장
			StringBuilder sb = new StringBuilder();
			while(true) {
				//한 줄 읽기
				String line = br.readLine();
				//읽은게 없으면 중단
				if(line == null)
					break;
				//읽은 데이터가 있으면 sb에 추가
				sb.append(line);				
			}
			
			//전체 데이터 출력 
			//System.out.printf("%s\n", sb);
			
			//데이터를 전부 읽었으면 String으로 변환
			json = sb.toString();
			
			br.close();
			con.disconnect();
			
		}catch(Exception e) {
			System.out.printf("%s\n", e.getMessage());
			e.printStackTrace();
		}
		
		if(json != null) {
			JSONObject obj = new JSONObject(json);
			JSONArray documents = obj.getJSONArray("documents");
			for(int i=0; i<documents.length(); i=i+1){
				JSONObject ob = documents.getJSONObject(i);
				System.out.printf("%s\n", ob.getString("title"));
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
