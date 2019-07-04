import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class MessageController {

	public static void main(String[] args) {
		//DAO클래스의 인스턴스를 생성
		MessageDAO dao = MessageDAOImpl.getInstance();
		//System.out.printf("%s\n", dao);
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.printf("choose a menu(1.insert 2.printAll 3.5paging 4.more 5.paging 6.update 7.delete 100:close):");		
			String menu = sc.nextLine();
			
			//이름과 내용, 날짜를 저장할 임시 변수 선
			String name = null;
			String content = null;
			Date regdate = null;
			
			switch(menu) {
			case "1":
				System.out.printf("write a name:");
				name=sc.nextLine();
				System.out.printf("write contents:");
				content=sc.nextLine();
				Calendar cal = new GregorianCalendar();
				regdate = new Date(cal.getTimeInMillis());
				
				MessageVO vo = new MessageVO();
				vo.setName(name);
				vo.setContent(content);
				vo.setRegdate(regdate);
				
				int result = dao.insertMessage(vo);
				
				if(result>0) {
					System.out.printf("Succed\n");
				}else {
					System.out.printf("Failed\n");
				}
				System.out.printf("Press Enter\n");
				sc.nextLine();				
				break;
			
			case "2":
				List<MessageVO> list1 = dao.getAllMessageList();
				for(MessageVO temp : list1) {
					System.out.printf("%s\n", temp);				
				}

				System.out.printf("Press Enter\n");
				sc.nextLine();
				break;
				
			case "3":
				List<MessageVO> list2 = dao.fiveList();
				for(MessageVO temp : list2) {
					System.out.printf("%s\n", temp);				
				}

				System.out.printf("Press Enter\n");
				sc.nextLine();
				break;
				
			case "4":
				List<MessageVO> list3 = dao.modList();
				for(MessageVO temp : list3) {
					System.out.printf("%s\n", temp);				
				}

				System.out.printf("Press Enter\n");
				sc.nextLine();
				break;
				
			case "5":
				//페이지 번호와 데이터 개수 입력받기
				System.out.printf("page: ");
				int page = sc.nextInt();
				System.out.printf("cnt: ");
				int cnt = sc.nextInt();
				
				//파라미터 만들기
				Map<String, Object> map = new HashMap<String, Object> ();
				map.put("page", page);
				map.put("cnt", cnt);
				
				//데이터 가져오기
				List<MessageVO> list = dao.pageList(map);
				if(list.size() == 0) {
					System.out.printf("there is no date\n");
				}else {
					for(MessageVO vo1 : list) {
						System.out.printf("%s\n", vo1);
					}
				}
				System.out.printf("Press Enter\n");
				sc.nextLine();
				break;
				
			case "6":
				System.out.printf("write a number to update data: ");
				int updatenum = sc.nextInt();
				//데이터가 존재하는지 확인 
				MessageVO vo2 = dao.detailMessage(updatenum);
				//데이터가 없는 경우
				if(vo2 == null) {
					System.out.printf("number error\n");
				}
				//데이터가 존재하는 경우
				else {
					sc.nextLine();
					System.out.printf("update name: \n");
					String n = sc.nextLine();
					System.out.printf("update content: \n");
					String c = sc.nextLine();
					
					vo2.setName(n);
					vo2.setContent(c);
					int r = dao.updateMessage(vo2);
					if(r >= 0 ) {
						JOptionPane.showMessageDialog(null, "Succeed");
					}else {
						JOptionPane.showMessageDialog(null, "Failed");
					}
				}
				
				System.out.printf("Press Enter\n");
				sc.nextLine();				
				break;
			
			case "7":
				System.out.printf("write a number to delete data: ");
				int deletenum = sc.nextInt();
				
				MessageVO vo3 = dao.detailMessage(deletenum);
				if(vo3 == null) {
					System.out.printf("Number error\n");
				}else {
					System.out.printf("really? (delete-1, close-2): \n");
					int m = sc.nextInt();
					if(m ==1 ) {
						int r = dao.deleteMessage(deletenum);
						if(r >=0) {
							JOptionPane.showMessageDialog(null,"Succeed");
						}else {
							JOptionPane.showMessageDialog(null,"Failed");
						}
					}
				}
				
				System.out.printf("Press Enter\n");
				sc.nextLine();				
				break;
								
			case "100":
				System.out.printf("close the program.\n");
				sc.close();
				System.exit(0);
			}
		}

	}

}
