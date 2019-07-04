package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Sawon;

//싱글톤 패턴으로 디자인-Spring에서는 직접 구현하지 않아도 
public class SawonDAOImpl implements SawonDAO {
	//싱글톤인 경우와 팩토리메소드 패턴을 사용할때 외부에서 객체를 생성하지 못하도록 생성자를 private
		String url = null;
		String id = null;
		String pw = null;
		private SawonDAOImpl() {
			try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("./db.txt"))))){
				String line = br.readLine();
				String [] ar = line.split(" ");
				url = String.format("jdbc:mysql://%s:%s/%s?useUnicode=yes&characterEncoding=UTF-8",ar[0],ar[1],ar[2]);
					id = ar[3];
				pw = ar[4];
				
			}catch(Exception e) {
				System.out.printf("read exception: %s\n", e.getMessage());
				e.printStackTrace();
			}
		}
		
		//인스턴스의 참조를 저장할 static 변수를 선언
		//인터페이스 자료형으로 생성
		//클라이언트나 다른 개발자는 클래스에 관심이 없고 요구하는 내용이 있는지만 확인하면 되므로 인터페이스 타입으로 선언
		
		//다른 클래스를 상속받거나 인터페이스를 구현한 클래스의 참조는 상위 클래스 타입의 변수나 인터페이스 타입의 변수에 대입할 수 있다.
		private static SawonDAO dao;
		
		//인스턴스를 생성해서 리턴하는 static 메소드
		//리턴타입은 자신의 자료형
		//매개변수는 없음
		//이름은 Java는 get으로 시작 MS는 shared로 시작
		public static SawonDAO getInstance() {
			if(dao == null) {
				dao = new SawonDAOImpl();
			}
			return dao;
		}
		
		@Override
		public int insertSawon(Sawon sawon) {
			//리턴할 데이터를 생성
			int result = -1;
			
			//데이터베이스 연동-프레임워크를 사용하면 구조가 간단
			//jdv1.7부터는 try-with-resource 사용
			//close를 호출하기 위해서 finally를 작성하지 않음
			try(Connection con = DriverManager.getConnection(url,id,pw);
					PreparedStatement pstmt = con.prepareStatement("insert into sawon(name,phone,addr,birth,email) values(?,?,?,?,?)");){
				pstmt.setString(1, sawon.getName());
				pstmt.setString(2, sawon.getPhone());
				pstmt.setString(3, sawon.getAddr());
				pstmt.setDate(4,  sawon.getBirth());
				pstmt.setString(5,  sawon.getEmail());		
				
				//SQL을 실행하고 그 결과를 result에 저장
				result = pstmt.executeUpdate();
				
				
			}catch(Exception e) {
				System.out.printf("insert error: %s\n", e.getMessage());
				e.printStackTrace();
			}
			return result;
		}
		
		@Override
		public List<Sawon> getAllSawon() {
			List<Sawon> list = new ArrayList<Sawon>();
			try(Connection con = DriverManager.getConnection(url,id,pw);
					PreparedStatement pstmt = con.prepareStatement("select * from sawon");){
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					Sawon sawon = new Sawon();
					//프레임워크에서는 이름을 맞추거나 매핑을 해주면 이 작업도 자동화 해줍니다.
					sawon.setNum(rs.getInt("num"));
					sawon.setName(rs.getString("name"));
					sawon.setPhone(rs.getString("phone"));
					sawon.setAddr(rs.getString("addr"));
					sawon.setEmail(rs.getString("email"));
					sawon.setBirth(rs.getDate("birth"));
				
					list.add(sawon);
					
				}
								
			}catch(Exception e) {
				System.out.printf("get all data error: %s\n", e.getMessage());
				e.printStackTrace();
			}
			
			return list;
		}

		@Override
		public List<Sawon> getNameSawon(String name) {
			List<Sawon> list = new ArrayList<Sawon>();
			try(Connection con = DriverManager.getConnection(url,id,pw);
					PreparedStatement pstmt = con.prepareStatement("select * from sawon where name like ? or phone like ?");){
				
				//pstmt.setString(1, "%"+name+"%");
				//또는 하나의 문자열을 만들어주는 메소드를 호출
				String param = String.format("%s%s%s","%",name,"%");
				pstmt.setString(1, param);
				pstmt.setString(2, param);
				
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Sawon sawon = new Sawon();
				
				sawon.setNum(rs.getInt("num"));
				sawon.setName(rs.getString("name"));
				sawon.setPhone(rs.getString("phone"));
				sawon.setAddr(rs.getString("addr"));
				sawon.setEmail(rs.getString("email"));
				sawon.setBirth(rs.getDate("birth"));
				
				list.add(sawon);
				
				}
				rs.close();				
												
			}catch(Exception e) {
				System.out.printf("get name data error: %s\n", e.getMessage());
				e.printStackTrace();
			}
			return list;
		}

		@Override
		public int updateSawon(Sawon sawon) {
			int result = -1;
			try(Connection con = DriverManager.getConnection(url,id,pw);
					PreparedStatement pstmt = con.prepareStatement("update sawon set name = ?, phone = ?, addr=?, birth=?, email=? where num = ?");){
				pstmt.setString(1, sawon.getName());
				pstmt.setString(2, sawon.getPhone());
				pstmt.setString(3, sawon.getAddr());
				pstmt.setDate(4,  sawon.getBirth());
				pstmt.setString(5,  sawon.getEmail());
				pstmt.setInt(6,  sawon.getNum());
				
				//SQL을 실행하고 그 결과를 result에 저장
				result = pstmt.executeUpdate();				
				
			}catch(Exception e) {
				System.out.printf("update error: %s\n", e.getMessage());
				e.printStackTrace();
			}
			return result;
		}

		@Override
		public int deleteSawon(int num) {
			int result = -1;
			try(Connection con = DriverManager.getConnection(url,id,pw);
					PreparedStatement pstmt = con.prepareStatement("delete from sawon where num =?");){
				pstmt.setInt(1, num);
				//SQL을 실행하고 그 결과를 result에 저장
				result = pstmt.executeUpdate();				
				
			}catch(Exception e) {
				System.out.printf("delete error: %s\n", e.getMessage());
				e.printStackTrace();
			}
			return result;
		}
	
}
