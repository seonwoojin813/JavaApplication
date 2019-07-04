package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import domain.Sawon;
import service.EventHandler;

public class MainUI extends JFrame {
	// 레이블 6개
	public JLabel lblName, lblPhone, lblEmail, lblBirth, lblAddr, lblIdx;
	//입력받을 텍스트필드 5개 
	public JTextField txtName, txtPhone, txtEmail, txtBirth, txtAddr;
	//버튼9개
	public JButton btnNext, btnPrev, btnLast, btnFirst,btnInsert, btnUpdate, btnDelete, btnSearch, btnClear;
	//패널4개
	public JPanel pCenter, pSouth, pTop, pMid;
	
	//데이터베이스에서 읽어온 데이터를 저장할 List
	public List<Sawon> list;
	//현재 출력 중인 데이터의 인덱스를 저장할 변수
	public int idx;
	//현재 리스트의 데이터를 화면에 출력해주는 메소드
	public void display() {
		//List로 데이터를 가져오면 데이터가 없으면 size = 0
		if(list.size()==0) {
			JOptionPane.showMessageDialog(null,"there is no data");
			txtName.setText("");
			txtEmail.setText("");
			txtAddr.setText("");
			txtBirth.setText("");
			txtPhone.setText("");
		}else {
			//데이터가 있으면 idx번째 데이터를 가져와서 출력 
			Sawon sawon = list.get(idx);
			txtName.setText(sawon.getName());
			txtEmail.setText(sawon.getEmail());
			txtAddr.setText(sawon.getAddr());
			txtBirth.setText(sawon.getBirth().toString());
			txtPhone.setText(sawon.getPhone());
			lblIdx.setText(idx+1+"");
		}
	}	
	
	public MainUI() {
		pCenter = new JPanel();
		pCenter.setLayout(new BorderLayout());
		
		pTop = new JPanel();
		//5행2열의 레이아웃 설정
		pTop.setLayout(new GridLayout(5,2,3,4));
		pTop.setBorder(new TitledBorder("현재데이터 - 새로고침 F5"));
		
		lblName = new JLabel("name", JLabel.RIGHT);
		pTop.add(lblName);
		txtName = new JTextField();
		pTop.add(txtName);
		
		lblPhone = new JLabel("phone", JLabel.RIGHT);
		pTop.add(lblPhone);
		txtPhone = new JTextField();
		pTop.add(txtPhone);
		
		lblEmail = new JLabel("e-mail", JLabel.RIGHT);
		pTop.add(lblEmail);
		txtEmail = new JTextField();
		pTop.add(txtEmail);
		
		lblBirth = new JLabel("birth", JLabel.RIGHT);
		pTop.add(lblBirth);
		txtBirth = new JTextField();
		pTop.add(txtBirth);
		
		lblAddr = new JLabel("address", JLabel.RIGHT);
		pTop.add(lblAddr);
		txtAddr = new JTextField();
		pTop.add(txtAddr);
		
		pCenter.add("Center",pTop);
		
		pMid = new JPanel();
		//1행5열
		
		pMid.setLayout(new GridLayout(1,5,4,4));
		pMid.setBorder(new TitledBorder("Search"));
		
		btnFirst = new JButton("First");
		pMid.add(btnFirst);
		
		btnPrev = new JButton("Previous");
		pMid.add(btnPrev);
		
		lblIdx = new JLabel("Index", JLabel.CENTER);
		pMid.add(lblIdx);
		
		btnNext = new JButton("Next");
		pMid.add(btnNext);
		
		btnLast = new JButton("Last");
		pMid.add(btnLast);
		
		
		pCenter.add("South", pMid);
		
		this.add("Center", pCenter);
		
		
		pSouth = new JPanel();
		
		btnInsert = new JButton("insert");
		pSouth.add(btnInsert);
		btnUpdate = new JButton("update");
		pSouth.add(btnUpdate);
		btnDelete = new JButton("delete");
		pSouth.add(btnDelete);
		btnSearch = new JButton("search");
		pSouth.add(btnSearch);
		btnClear = new JButton("clear");
		pSouth.add(btnClear);
		
		pSouth.setBorder(new TitledBorder("Data work"));
		this.add("South",pSouth);

		// 종료 버튼을 눌렀을 때 자바 프로그램을 종료할 수 있도록 해주는 설
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("사원관리");
		setSize(500, 300);
		setLocation(200, 200);
		setResizable(false);
		//EventHandler handler = new EventHandler(this);
		//setVisible앞에 EventHandler를 적용하면 모든 데이터를 읽어온 후 창이 뜨기 때문에 화면이 보이기까지가 시간이 오래 걸립니다.
		//화면이 늦게 뜰 경우 유저들은 잘못됬다고 생각하여 종료할 가능성이 있기 때문에 화면을 먼저 띄운후 데이터를 가져오는 아래 방식을 요즘은 선호합니다.
		setVisible(true);
		
		//이벤트 처리를 위한 객체 생성
		//자신의 참조를 넘겨서 EventHandler 클래스 안에서 이 객체에 대한 조작이 가능하도록 합니다.
		EventHandler handler = new EventHandler(this);
		
		//버튼 4개의 이벤트 처리를 handler 객체 하나가 처리-이벤트 라우팅 
		btnNext.addActionListener(handler);
		btnPrev.addActionListener(handler);
		btnLast.addActionListener(handler);
		btnFirst.addActionListener(handler);
		
		//데이터 작업 버튼의 클릭 이벤트 처리를 handler 객체에 위임
		btnInsert.addActionListener(handler);
		btnUpdate.addActionListener(handler);
		btnDelete.addActionListener(handler);
		btnSearch.addActionListener(handler);
		btnClear.addActionListener(handler);
		
		//프레임에 키보드 이벤트가 발생했을 때 이벤트를 처리할 객체 설정
		this.addKeyListener(handler);
		txtName.addKeyListener(handler);

		
	}

}
