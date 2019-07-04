import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MessageDAOImpl implements MessageDAO {
	//생성자는 private으로
	private MessageDAOImpl() {}
	
	//자신의 타입으로 static 변수를 선언
	private static MessageDAO messageDAO;
	
	//인스턴스를 만들어서 리턴하는 static 메소드를 생성
	public static MessageDAO getInstance() {
		if(messageDAO == null) {
			messageDAO = new MessageDAOImpl();
		}
		return messageDAO;
	}

	@Override
	public int insertMessage(MessageVO vo) {
		int result = -1;
		try(Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1","user06","user06");
			PreparedStatement pstmt = con.prepareStatement(
					"insert into Message(num, content, name, regdate) values(messages.nextval,? , ?, ?)");){
			
			//물음표에 데이터 바인
			pstmt.setString(1, vo.getContent());
			pstmt.setString(2, vo.getName());
			
			pstmt.setDate(3, vo.getRegdate());
			
			result = pstmt.executeUpdate();
			
				
			}catch(Exception e) {
				System.out.printf("%s\n", e.getMessage());
				e.printStackTrace();
			}	
		return result;
	}
	
	@Override
	public List<MessageVO> getAllMessageList(){
		List<MessageVO> list = new ArrayList<MessageVO>();
		try(Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1","user06","user06");
			PreparedStatement pstmt = con.prepareStatement(
					//데이터에는 행과 열이 없기 때문에 반드시 여러개를 가져올 때는 sort 해주어야 합니다.
					"select * from message order by regdate desc");){
			
			//select구문을 수행하고 그 결과를 rs에 저
			ResultSet rs = pstmt.executeQuery();
			//rs를 한 줄 씩 이동
			while(rs.next()) {
				MessageVO vo = new MessageVO();
				
				//읽은 데이터를 vo에 저장				
				//변수명을 동일하게 맞추면 편함
				vo.setNum(rs.getInt("num"));
				vo.setName(rs.getString("name"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getDate("regdate"));
				
				//리스트에 추가
				list.add(vo);
			}
			
	
			}catch(Exception e) {
				System.out.printf("%s\n", e.getMessage());
				e.printStackTrace();
			}	
		return list;
	}
	
	@Override
	public List<MessageVO> fiveList(){
		List<MessageVO> list = new ArrayList<MessageVO>();
		try(Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1","user06","user06");
			PreparedStatement pstmt = con.prepareStatement(
					//데이터에는 행과 열이 없기 때문에 반드시 여러개를 가져올 때는 sort 해주어야 합니다.
					"select * from (select rownum rnum, num, name, content, regdate from (select * from message order by num desc)) where rnum <= 5 ");){
			
			//select구문을 수행하고 그 결과를 rs에 저장 
			ResultSet rs = pstmt.executeQuery();
			//rs를 한 줄 씩 이동
			while(rs.next()) {
				MessageVO vo = new MessageVO();
				
				//읽은 데이터를 vo에 저장				
				//변수명을 동일하게 맞추면 편함
				vo.setNum(rs.getInt("num"));
				vo.setName(rs.getString("name"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getDate("regdate"));
				
				//리스트에 추가
				list.add(vo);
			}
			
	
			}catch(Exception e) {
				System.out.printf("%s\n", e.getMessage());
				e.printStackTrace();
			}	
		return list;
	}
	
	@Override
	public List<MessageVO> modList(){
		List<MessageVO> list = new ArrayList<MessageVO>();
		try(Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1","user06","user06");
			PreparedStatement pstmt = con.prepareStatement(
					//데이터에는 행과 열이 없기 때문에 반드시 여러개를 가져올 때는 sort 해주어야 합니다.
					"select * from (select rownum rnum, num, name, content, regdate from (select * from message order by num desc)) where rnum > 5 ");){
			
			//select구문을 수행하고 그 결과를 rs에 저장 
			ResultSet rs = pstmt.executeQuery();
			//rs를 한 줄 씩 이동
			while(rs.next()) {
				MessageVO vo = new MessageVO();
				
				//읽은 데이터를 vo에 저장				
				//변수명을 동일하게 맞추면 편함
				vo.setNum(rs.getInt("num"));
				vo.setName(rs.getString("name"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getDate("regdate"));
				
				//리스트에 추가
				list.add(vo);
			}
			
	
			}catch(Exception e) {
				System.out.printf("%s\n", e.getMessage());
				e.printStackTrace();
			}	
		return list;
	}
	
	@Override
	public List<MessageVO> pageList(Map<String, Object>map){
		List<MessageVO> list = new ArrayList<MessageVO>();
		try(Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1","user06","user06");
			PreparedStatement pstmt = con.prepareStatement(
					//데이터에는 행과 열이 없기 때문에 반드시 여러개를 가져올 때는 sort 해주어야 합니다.
					"select * from (select rownum rnum, num, name, content, regdate from (select * from message order by num desc)) where rnum >= ? and rnum <= ? ");){
			int page = (Integer)map.get("page");
			int cnt = (Integer)map.get("cnt");
			
			pstmt.setInt(1, cnt*(page-1)+1);
			pstmt.setInt(2, cnt*(page));
			
			//select구문을 수행하고 그 결과를 rs에 저장 
			ResultSet rs = pstmt.executeQuery();
			//rs를 한 줄 씩 이동
			while(rs.next()) {
				MessageVO vo = new MessageVO();
				
				//읽은 데이터를 vo에 저장				
				//변수명을 동일하게 맞추면 편함
				vo.setNum(rs.getInt("num"));
				vo.setName(rs.getString("name"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getDate("regdate"));
				
				//리스트에 추가
				list.add(vo);
			}
			
	
			}catch(Exception e) {
				System.out.printf("%s\n", e.getMessage());
				e.printStackTrace();
			}	
		return list;
	}

	@Override
	public MessageVO detailMessage(int num) {
		MessageVO vo = null;
		//num에 해당하는 데이터가 없을 때는 null을 리
		try(Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1","user06","user06");
			PreparedStatement pstmt = con.prepareStatement(
					//num을 이용해서 데이터를 찾아오는 SQL
					"select * from message where num = ?");){
			pstmt.setInt(1, num);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new MessageVO();
				vo.setNum(rs.getInt("num"));
				vo.setName(rs.getString("name"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getDate("regdate"));
			}
			rs.close();
	
			}catch(Exception e) {
				System.out.printf("%s\n", e.getMessage());
				e.printStackTrace();
			}	
		
		return vo;
	}

	@Override
	public int updateMessage(MessageVO vo) {
		int result = -1;
		try(Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1","user06","user06");
			PreparedStatement pstmt = con.prepareStatement(
					//num을 이용해서 데이터를 찾아오는 SQL
					"update message set name=?, content=?, regdate=sysdate where num = ?");){
			//물음표에 데이터 바인딩
			pstmt.setString(2, vo.getContent());
			pstmt.setString(1,  vo.getName());
			pstmt.setInt(3, vo.getNum());
			
			//sql 실행
			result = pstmt.executeUpdate();
			
			}catch(Exception e) {
				System.out.printf("%s\n", e.getMessage());
				e.printStackTrace();
			}	
		return result;
	}
	
	@Override
	public int deleteMessage(int num) {
		int result = -1;
		try(Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1","user06","user06");
			PreparedStatement pstmt = con.prepareStatement(
					//num을 이용해서 데이터를 찾아오는 SQL
					"delete from Message where num = ?");){
			
			pstmt.setInt(1, num);
			
			//sql 실행
			result = pstmt.executeUpdate();
			
			}catch(Exception e) {
				System.out.printf("%s\n", e.getMessage());
				e.printStackTrace();
			}	
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
