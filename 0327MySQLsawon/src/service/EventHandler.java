package service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import dao.SawonDAO;
import dao.SawonDAOImpl;
import domain.Sawon;
import view.MainUI;

public class EventHandler implements ActionListener, KeyListener {
	
	//MainUI에 대한 조작을 위한 변수
	private MainUI ui;
	//DAO 메소드호출을 위한 참조형 변수
	private SawonDAO dao;
	
	public EventHandler(MainUI ui) {
		//ui는 넘겨받았고(DI-의존성주입)
		//dao는 직접 생성
		this.ui = ui;
		dao = SawonDAOImpl.getInstance();
		
		//전체 데이터를 가져오기
		ui.list = dao.getAllSawon();
		//인덱스는 0으로 초기
		ui.idx = 0;
		//데이터를 출력
		ui.display();
		
	}
	
	//버튼을 누르거나 텍스트 필드에서 Enter를 눌렀을 때 호출되는 메소드
	//이벤트를 처리하는 메소드는 이벤트가 발생한 객체를 알아야 하기 때문에 매개변수가 있습니다.
	//프로그래밍 언어에서 이벤트 처리를 할 때는 이벤트 처리 메소드의 매개변수에 대해서 정확하게 알아둘 필요가 있습니다.
	@Override
	public void actionPerformed(ActionEvent e) {
		//JOptionPane.showMessageDialog(null, e.getSource().toString());
		//JOptionPane.showMessageDialog(null,  e.getActionCommand());
		
		//누른 버튼의 문자열을 이용해서 분기문 작성
		switch(e.getActionCommand().trim()) {
		case "Previous":
			//인덱스가 가장 첫번째 데이터에 있으면 이전으로 이동할 경우 IndexOutOfBoundsExceptio 예외가 발생합니다.
			if(ui.idx == 0) {
				JOptionPane.showMessageDialog(null, "First data");
				return;
				//이때 ui.idx=ui.list.size()(마지막으로 회전) 로 만들어도 됩니다.
			}
			ui.idx = ui.idx-1;
			ui.display();
			break;
		case "Next":
			//인덱스가 가장 마지막 데이터에 있으면 다음으로 이동할 경우 IndexOutOfBoundsExceptio 예외가 발생합니다.
			if(ui.idx == ui.list.size()-1) {
				JOptionPane.showMessageDialog(null, "Last data");
				return;
				//이때 ui.idx=-1(처음으로 회전) 로 만들어도 됩니다.
			}
			ui.idx = ui.idx+1;
			ui.display();
			break;
		case "First":
			ui.idx =0;
			ui.display();
			break;
		case "Last":
			ui.idx = ui.list.size()-1;
			//ui.list.size()가 0부터 시작하기 때문에 -1
			ui.display();
			break;
		case "insert":
			//문자열의 내용을 비교할 때는 equal 메소드 이용
			//==는 참조형의 경우 참조가 같은지 비교 
			if(ui.btnClear.getText().equals("clear")) {
				JOptionPane.showMessageDialog(null, "Clear data first!");
				return;
			}
			//입력한 내용을 가져오기-공백을 제거하고 가져오기
			String name = ui.txtName.getText().trim();
			String email = ui.txtEmail.getText().trim();
			String phone = ui.txtPhone.getText().trim();
			String addr = ui.txtAddr.getText().trim();
			String birth = ui.txtBirth.getText().trim();
			
			//필수입력 유효성 검사
			if(name.length() == 0) {
				JOptionPane.showMessageDialog(null, "이름은 필수입력입니다.");
				ui.txtName.setFocusable(true);
				return;
			}
			if(email.length() == 0) {
				JOptionPane.showMessageDialog(null, "이메일 필수입력입니다.");
				ui.txtEmail.setFocusable(true);
				return;
			}
			if(phone.length() == 0) {
				JOptionPane.showMessageDialog(null, "핸드폰 필수입력입니다.");
				ui.txtPhone.setFocusable(true);
				return;
			}
			if(birth.length() != 8) {
				JOptionPane.showMessageDialog(null, "생일은 8자리로 입력하세요(예.1111-02-03).");
				ui.txtBirth.setFocusable(true);
				return;
			}
			
			//길이검사 - length()를 사용
			if(phone.length() != 11 ) {
				JOptionPane.showMessageDialog(null, "전화번호를 확인하세요.");
				ui.txtPhone.setFocusable(true);
				return;
			}
			//숫자만으로 구성되었는지 확인
			for(int i =0; i<phone.length(); i=i+1) {
				char ch = phone.charAt(i);
				if(ch < '0' || ch >'9') {
					JOptionPane.showMessageDialog(null, "전화번호는 숫자만 입력하세요");
					ui.txtPhone.setFocusable(true);
					return;					
				}
			}
			for(int i =0; i<birth.length(); i=i+1) {
				char ch = birth.charAt(i);
				if(ch < '0' || ch >'9') {
					JOptionPane.showMessageDialog(null, "생일은 숫자만 입력하세요");
					ui.txtBirth.setFocusable(true);
					return;					
				}
			}
			
			//숫자 8자리를 가지고 java.sql.Date 객체를 생성
			//값의 범위가 있는 데이터를 직접 입력하도록 하면 유효성 검사가 복잡해집니다.
			//때문에 값의 범위가 정해진 데이터는 ComboBox나 List를 사용하는 것이 효율적입니다.
			String year = birth.substring(0,4);
			String month = birth.substring(4,6);
			String day = birth.substring(6);
			
			if ((Integer.parseInt(month)) > 11) {
				JOptionPane.showMessageDialog(null, "Insert only 1~12");
				return;
			}
			
			Calendar cal = new GregorianCalendar(
					Integer.parseInt(year),
					Integer.parseInt(month)-1,
					Integer.parseInt(day));
			Date birthday = new Date(cal.getTimeInMillis());
			//데이터 삽입을 위해서 입력한 데이터들을 하나의 객체로 생성
			Sawon sawon = new Sawon();
			sawon.setName(name);
			sawon.setEmail(email);
			sawon.setPhone(phone);
			sawon.setBirth(birthday);
			sawon.setAddr(addr);
			
			int r = dao.insertSawon(sawon);
			if( r > 0) {
				JOptionPane.showMessageDialog(null, "insert Succeed");
				//전체 데이터를 가져오고 다시 출력
				ui.list = dao.getAllSawon();
				ui.idx = 0;
				ui.display();
				
				//버튼의 상태를 초기상태로 되돌리기
				ui.btnClear.setText("clear");
				ui.btnUpdate.setEnabled(true);
				ui.btnDelete.setEnabled(true);
				ui.btnSearch.setEnabled(true);
				
			}
			break;
			
		case "update":
			String nameU = ui.txtName.getText().trim();
			String emailU = ui.txtEmail.getText().trim();
			String phoneU = ui.txtPhone.getText().trim();
			String addrU = ui.txtAddr.getText().trim();
			String birthU = ui.txtBirth.getText().trim();
			
			if(nameU.length() < 1 || emailU.length() < 1 || phoneU.length() < 1) {
				JOptionPane.showMessageDialog(null, "이름, 이메일, 전화번호는 필수 입력입니다.");
				return;
			}
			//전화번호는 숫자 10~11	자리
			if(phoneU.length()<10 || phoneU.length() > 11) {
				JOptionPane.showMessageDialog(null,"전화번호는 숫자 10~11바리입니다.(예.00011112222)");
				return;
			}
			//전화번호는 모두 숫자로 구성
			for(int i = 0; i<phoneU.length(); i=i+1) {
				char ch = phoneU.charAt(i);
				if(ch<'0' || ch>'9') {
					JOptionPane.showMessageDialog(null,  "전화번호는 숫자로만 입력해야 합니다.");
					return;
				}
			}
			//생년월일은 숫자 8자리
			if(birthU.length() != 8) {
				JOptionPane.showMessageDialog(null,"생년월은 숫자 8자리입니다.(예.11110101)");
				return;
			}
			//생년월일은 모두 숫자로 구성
			for(int i = 0; i<birthU.length(); i=i+1) {
				char ch = birthU.charAt(i);
				if(ch<'0' || ch>'9') {
					JOptionPane.showMessageDialog(null,  "생년월일 숫자로만 입력해야 합니다.");
					return;
				}
			}
			//생년월일 8자리 숫자를 가지고 sql.Date 객체 만들기
			int y = Integer.parseInt(birthU.substring(0,4));
			int m = Integer.parseInt(birthU.substring(4,6));
			int d = Integer.parseInt(birthU.substring(6));
			
			//현재 시간을 객체로 생성
			Calendar now = new GregorianCalendar();
			if(y < 1900 || y > now.get(Calendar.YEAR)) {
				JOptionPane.showMessageDialog(null, "년도는 1900년도부터" + now.get(Calendar.YEAR)+"까지만 입력하세요");
				return;
			}
			if(m < 1 || m > 12) {
				JOptionPane.showMessageDialog(null, "월은 1월부터 12월까지만 입력하세요");
				return;
			}
			if(d < 1 || d >31)	{				
				JOptionPane.showMessageDialog(null, "일은 1일부터 31일까지만 입력하세요");
				return;
			}
			int re = JOptionPane.showConfirmDialog(null, "data update", "confirm",JOptionPane.YES_NO_OPTION);
			if(re == JOptionPane.YES_OPTION) {
				cal=new GregorianCalendar(y,m-1,d);
				Date birthdayU = new Date(cal.getTimeInMillis());
				//현재 출력 중인 데이터의 사원번호 찾아오기
				int updateNum = ui.list.get(ui.idx).getNum();
				
				Sawon sawonU = new Sawon();
				sawonU.setNum(updateNum);
				sawonU.setName(nameU);
				sawonU.setEmail(emailU);
				sawonU.setPhone(phoneU);
				sawonU.setBirth(birthdayU);
				sawonU.setAddr(addrU);
				
				int update = dao.updateSawon(sawonU);
				if(update >= 0) {
					JOptionPane.showMessageDialog(null, "update succeed");
					ui.list = dao.getAllSawon();
					ui.display();
				}else {
						JOptionPane.showMessageDialog(null, "update failed");
						ui.display();
				}
				
			}
			break;
			
		case "delete":
			//정말로 지울 것인지 확인
			int deleteInt = JOptionPane.showConfirmDialog(null, "confirm","delete",JOptionPane.YES_NO_OPTION);
			//예를 눌렀다면
			if(deleteInt == JOptionPane.YES_OPTION) {
				//현재 출력 중인 데이터의 사원번호를 찾아옵니다.
				int num = ui.list.get(ui.idx).getNum();
				//데이터베이스에서 삭제
				int deleteResult = dao.deleteSawon(num);
				if(deleteResult > 0) {
					//이전에는 데이터베이스에서 데이터를 다시 불러옴
					//ui.list = dao.getAllSawon();
					//혼자 사용하는 DB에서 원본 데이터베이스에서 작업을 하고 현재 프로그램이 가지고 있는 곳에서도 작업을 수행하고 다시 출력
					ui.list.remove(ui.idx);
					//데이터를 다시 출력, 그냥 데이터를 다시 출력하면 마지막 데이터를 삭제했을때 예외 발생 
					//따라서 ui.idx = 0;(처음화면으로 출력) 또는 ui.idx = ui.idx-1;(idx 이전 데이터로 출력)
					if(ui.idx > ui.list.size()-1) {
						ui.idx = ui.idx-1; //마지막 데이터를 지울경우에 마지막 이전 데이터 출
					}
					ui.display();
				}
			}
			break;
			
		case "search":
			//한 줄의 텍스트를 입력받을 대화상자를 출력해서 텍스트를 입력받기
			String word = JOptionPane.showInputDialog("write a name or phone for searching");
			if(word != null ) {
				//검색을 입력하고 확인을 누른다면 
				//단어를 이용해서 검색 
				ui.list = dao.getNameSawon(word);
				ui.idx =0;
				ui.display();
			}
			break;
			
		case "clear":
			//모든 텍스트 필드의 내용을 빈칸으로 만들기
			ui.txtName.setText("");
			ui.txtEmail.setText("");
			ui.txtBirth.setText("");
			ui.txtPhone.setText("");
			ui.txtAddr.setText("");
			
			//삽입상태가 되도록 삽입 이외의 버튼은 전부 비활성화
			ui.btnUpdate.setEnabled(false); //비활성
			//또는 ui.btnUpdate.setVisible(false) 화면에서 제거
			//화면이 고정(pSouth.setLayout(new GridLayout(행,열,세로공백,가로공백))되어있지 않으면 가운데 정렬이 됩니다
			ui.btnDelete.setEnabled(false);
			ui.btnSearch.setEnabled(false);
			ui.btnInsert.setEnabled(false);
			
			//지움 버튼의 타이틀을 이전 상태로 변경 			
			ui.btnClear.setText("cancel");
			//이미지로 지정하는 방법 - 이미지의 경로(윈도우:cmd, 맥:터미널)
			ImageIcon icon = new ImageIcon("/Users/a503_07/Desktop/giphy.gif");
			ui.btnClear.setIcon(icon);
		
			break;

		case "cancel":
			ui.btnInsert.setEnabled(true);
			ui.btnUpdate.setEnabled(true);
			ui.btnDelete.setEnabled(true);
			ui.btnSearch.setEnabled(true);
			ui.btnClear.setText("clear");
			ui.btnClear.setIcon(null);
			ui.display();
			break;
			

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		//문자키를 눌렀을 때 호출되는 메소드 
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		//키보드를 눌렀을 때 호출되는 메소드
		
		//search가 완료된 화면에서 f5를 누르면 새로고침
		if(e.getKeyCode() == KeyEvent.VK_F5) {
			ui.list = dao.getAllSawon();
			ui.idx = 0;
			ui.display();
		}
		//Esc키를 누르면 화면 종
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {			
					System.exit(0);
									
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//키보드에서 손을 뗄 때 호출되는 메소드
		
	}


}
