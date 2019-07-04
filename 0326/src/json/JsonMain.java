package json;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonMain {

	public static void main(String[] args) {
		String str = "[200,300,400,500]";

		// 배열 문자열이므로 JSONArray로 변환
		JSONArray ar = new JSONArray(str);
		// 배열은 반복문을 이용해서 모든 데이터 접근
		for (int i = 0; i < ar.length(); i = i + 1) {
			Integer imsi = ar.getInt(i);
			System.out.printf("%s\n", imsi);
		}

		System.out.printf("==========================================\n");

		String obj = "{\"name\":\"park\",\"num\":1}";

		JSONObject object = new JSONObject(obj);
		String name = object.getString("name");
		Integer num = object.getInt("num");

		System.out.printf("name:%s\n", name);
		System.out.printf("num:%s\n", num);

	}

}
