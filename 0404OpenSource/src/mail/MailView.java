package mail;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;


public class MailView extends JFrame {
	public MailView() {
		//화면 상단에 배치할 Panel
		JPanel northPanel = new JPanel (new GridLayout(5,2,3,3)); //균등한 배치 
		JLabel lblSender = new JLabel("sender:", JLabel.RIGHT);
		JLabel lblName = new JLabel("홍길동");
		northPanel.add(lblSender);
		northPanel.add(lblName);
		
		JLabel lblToName = new JLabel("Reciever : ", JLabel.RIGHT);
		JTextField txtReceive = new JTextField(30);
		northPanel.add(lblToName);
		northPanel.add(txtReceive);
		
		JLabel lblSubject= new JLabel("title : ", JLabel.RIGHT);
		JTextField txtSubject = new JTextField(30);
		northPanel.add(lblSubject);
		northPanel.add(txtSubject);
		
		JLabel lblFile = new JLabel("",JLabel.RIGHT);
		northPanel.add(lblFile);
		JButton btnFile = new JButton("attached file");
		northPanel.add(btnFile);
		
		northPanel.add(new JLabel());
		
		JTextArea txtFiles = new JTextArea(2,30);
		//txtFiles에 스크롤 바를 사용할 수 있도록
		//ScrollPane을 생성하고 그 위에 txtFiles를 배치
		JScrollPane sp = new JScrollPane(txtFiles);
		northPanel.add(sp);
		
		btnFile.addActionListener(e -> {
			//파일 선택 대화상자를 출력해서 선택한 파일 이름을 텍스트 에어리어에 출력
			JFileChooser fc = new JFileChooser();
			fc.setMultiSelectionEnabled(true);
			//대화상자를 출력해서 결과 받기
			int result = fc.showOpenDialog(null);
			//확인을 눌렀다면 
			if(result == JFileChooser.APPROVE_OPTION) {
				//선택한 파일 전부 가져오기
				File [] f = fc.getSelectedFiles();
				for(File file : f) {
					//txtFiles에 기록된 내용이 없으면 
					if(txtFiles.getText().length() == 0) {
						txtFiles.setText(file.getPath());
					}
					//기록된 내용이 있다면 뒤에 추가
					else {
						String origin = txtFiles.getText();
						txtFiles.setText(origin+","+file.getPath());
					}
				}
			}
			
		});
		//안드로이드 스튜디오 코드 최적화 - 람다식 
		
		this.add("North",northPanel);
		
		//줄 수와 행 수 
		JTextArea content = new JTextArea(20,20);
		JScrollPane p = new JScrollPane(content);
		this.add("Center",content);
		
		JButton button = new JButton("send mail");
		this.add("South", button);
		
		button.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String receive = txtReceive.getText().trim();
				String subject = txtSubject.getText().trim();
				String message = content.getText().trim();
				
				if(receive.length() == 0) {
					JOptionPane.showMessageDialog(null,"receiver is not null");
				}
				if(subject.length() == 0) {
					subject = "none";
				}
				if(message.length() ==0) {
					message = "none";
				}
				
				//메일 보내기 객체 생성
				MultiPartEmail email = new MultiPartEmail();
				
				//보내는 메일 서버 설정
				email.setHostName("smtp.naver.com");
				
				//포트번호 설정
				email.setSmtpPort(587);
				//계정설정
				email.setAuthentication("ggangpae3","Opopop1!");
				try {
					//보안설정
					email.setSSL(true);
					email.setTLS(true);
					//인코딩 설정
					email.setCharset("utf-8");
					
					//첨부파일을 추가
					if(txtFiles.getText().length() != 0 ) {
						String [] fileNames = txtFiles.getText().split(",");
						for(String imsi : fileNames) {
							EmailAttachment attach = new EmailAttachment();
							attach.setName("");
							attach.setPath(imsi);
							//파일경로 email에 첨부
							email.attach(attach);
						}
					}
					
					//받는 사람 설정
					email.addTo(receive);
					//보내는 사람 설정
					email.setFrom("ggangpae3@naver.com","홍길동","utf-8");
					//제목설정
					email.setSubject(subject);
					//메시지 설정
					email.setMsg(message);
					
					//메일 보내기
					email.send();
					
					JOptionPane.showMessageDialog(null,"Sending email succeed");
					
				}catch(Exception e1) {
					System.out.printf("Seding email failed : %s\n",e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		
		setTitle("Send mail");
		setLocation(100,100);
		setSize(400,600);
		setResizable(false); //화면의 크기를 변경하지 못하게 설정 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

}
