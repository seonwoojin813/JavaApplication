import java.util.List;
import java.util.Map;

public interface MessageDAO {
	
	//데이터를 삽입하는 메소드
	public int insertMessage(MessageVO vo);
	
	//전체 데이터를 가져오는 메소드
	//한개 가져올 때는 List가 필요없음
	public List<MessageVO>getAllMessageList();
	
	public List<MessageVO>fiveList();
	
	public List<MessageVO> modList();
	
	public List<MessageVO> pageList(Map<String, Object> map);
	
	//기본키를 가지고 데이터 1개를 찾아오는 메소드-상세보기
	public MessageVO detailMessage(int num);
	
	//데이터를 수정하는 메소드
	public int updateMessage(MessageVO vo);
	
	//데이터를 삭제하는 메소드
	public int deleteMessage(int num);

}
