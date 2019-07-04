import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class XMLParsing {

	public static void main(String[] args) {
		// 웹에서 문자열 다운로드 받기
		String xml = null;
		try {
			// 다운로드 받을 주소를 문자열로 만들기
			// 한글이 있었으면 UTF-8인코딩해서 만들어야 합니다.
			String addr = "http://www.hani.co.kr/rss/";
			// 문자열로 된 주소를 URL로 변경
			URL url = new URL(addr);
			// 연결 객체를 만들고 옵션을 설정
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setUseCaches(false);
			con.setConnectTimeout(30000);

			// 문자열을 읽어오기 위한 객체 생
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			// 다운로드 받기
			StringBuilder sb = new StringBuilder();
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				sb.append(line + "\n");
			}
			// 데이터를 옮기고 외부 연결을 정리
			xml = sb.toString();
			br.close();
			con.disconnect();

			//System.out.printf("%s\n", xml);

		} catch (Exception e) {
			System.out.printf("download exception: %s\n", e.getMessage());
			e.printStackTrace();
		}

		// 2.XML 데이터 파싱
		if (xml != null) {
			try {
				// DOM Parsing을 수행할 객체 만들기
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = factory.newDocumentBuilder();
				// 읽어온 문자열을 Stream으로 변환
				InputStream is = new ByteArrayInputStream(xml.getBytes());
				Document document = documentBuilder.parse(is);
				// 루트 찾아오기
				Element root = document.getDocumentElement();

				// wf 태그의 모든 내용을 찾아와서 출력
				NodeList items = root.getElementsByTagName("title");

				for (int i = 0; i < items.getLength(); i = i + 1) {
					// 태그 1개 가져오기
					Node item = items.item(i);
					Node text = item.getFirstChild();
					System.out.printf("%s\n", text.getNodeValue());
				}

			} catch (Exception e) {
				System.out.printf("Parsing exception: %s\n", e.getMessage());
				e.printStackTrace();
			}
		}
	}

}
