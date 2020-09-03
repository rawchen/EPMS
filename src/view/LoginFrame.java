package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

import model.TbPerson;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import dao.PersonDao;
import tools.PwEncryption;
import tools.StringUtil;
import tools.TxtExport;

/**
 * 登陆窗体类，为用户第一窗体
 * @author 22219
 *
 */
public class LoginFrame extends JFrame {
	
	/**
	 * 串行版本标识serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	public static JTextField userNameTxt;
	private JPasswordField passwordTxt;
	public static String time;
	public static String userId;

	/**
	 * 登陆窗体类的构造函数
	 */
	public LoginFrame() {
		
		this.setBounds(0, 0, 500, 400);
		this.setLocationRelativeTo(null);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/images/storage_128px.png")));
		setTitle("登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("企业人事管理系统");
		lblNewLabel.setBounds(96, 29, 300, 48);
		lblNewLabel.setFont(new Font("方正粗黑宋简体", Font.BOLD, 27));
		lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/hrm.png")));
		
		JLabel lblNewLabel_1 = new JLabel("用户名");
		lblNewLabel_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(19, 126, 80, 18);
		lblNewLabel_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/userName.png")));
		
		JLabel lblNewLabel_2 = new JLabel("密码");
		lblNewLabel_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(34, 185, 65, 18);
		lblNewLabel_2.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/password.png")));
		
		userNameTxt = new JTextField();
		userNameTxt.setHorizontalAlignment(SwingConstants.CENTER);
		userNameTxt.setBounds(96, 110, 308, 46);
		userNameTxt.setFont(new Font("微软雅黑", Font.BOLD, 20));
		userNameTxt.setToolTipText("输入用户名");
		userNameTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setHorizontalAlignment(SwingConstants.CENTER);
		passwordTxt.setBounds(96, 169, 308, 46);
		passwordTxt.setFont(new Font("微软雅黑", Font.BOLD, 20));
		passwordTxt.setToolTipText("输入密码");
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		btnNewButton.setBounds(97, 252, 90, 40);
		this.getRootPane().setDefaultButton(btnNewButton);
		btnNewButton.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/login.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e);
			}
		});
		
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		btnNewButton_1.setBounds(201, 252, 90, 40);
		btnNewButton_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/reset.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		
		
		JButton btnNewButton_2 = new JButton("注/改");
		btnNewButton_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		btnNewButton_2.setBounds(305, 252, 90, 40);
		btnNewButton_2.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/add.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enrollValueActionPerformed(e);
			}
		});
		
		
		contentPane.add(lblNewLabel);
		contentPane.add(lblNewLabel_1);
		contentPane.add(lblNewLabel_2);
		contentPane.add(passwordTxt);
		contentPane.add(userNameTxt);
		contentPane.add(btnNewButton);
		contentPane.add(btnNewButton_1);
		contentPane.add(btnNewButton_2);
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				if (JOptionPane.showConfirmDialog(null, "确认退出?","提示",JOptionPane.YES_NO_OPTION)==0) {
					System.exit(0);
				}
			}
		});

		//bg------------------------------------------------
		JLabel lbBg = new JLabel(new ImageIcon(this.getClass().getResource("/images/timg.png")));
		lbBg.setBounds(0, 0, 500, 400);
		this.getContentPane().add(lbBg);
		//bg------------------------------------------------
		
		//sj------------------------------------------------
		JLabel timeLabel = new JLabel();
		timeLabel.setBounds(260, 319, 220, 18);
		contentPane.add(timeLabel);

		TimerTask task = new TimerTask() {
			public void run() {
				String sdate=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
				timeLabel.setText(sdate);
			}
		};
		Timer t = new Timer();
		t.scheduleAtFixedRate(task, new Date(), 1000);
		//sj-------------------------------------------------
	}
	
	/**
	 * 登陆事件处理
	 * @param e
	 */
	private void loginActionPerformed(ActionEvent e) {
		String userName = this.userNameTxt.getText();
		String password = new String(this.passwordTxt.getPassword());
		if(StringUtil.isEmpty(userName)){
			JOptionPane.showMessageDialog(this, "用户名不能为空！");
			return;
		}
		if(StringUtil.isEmpty(password)){
			JOptionPane.showMessageDialog(this, "密码不能为空！");
			return;
		}
		
		TbPerson person = new TbPerson(userName,PwEncryption.encrypt(password, "key"));
//		TbPerson person = new TbPerson(userName,password);
		TbPerson personNew = PersonDao.login(person);
		if(personNew!=null){
			JOptionPane.showMessageDialog(this, "登陆成功！");
			MainFrame mainFrame =  new MainFrame(userNameTxt.getText());
			mainFrame.frame.setVisible(true);
			
			//导出登陆日志（用户id+时间）
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			time = df.format(new Date());
			System.out.println("用户Id："+userNameTxt.getText()+"\t登陆时间："+time);
			
			try {
				TxtExport.creatTxtFile("Enterprise Personnel Management System");
				TxtExport.writeTxtFile("员工Id："+userNameTxt.getText()+"\t登陆时间："+time);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			
			dispose();//销毁窗体
		}else{
			JOptionPane.showMessageDialog(this, "用户名或密码错误！");
		}
	}

	/**
	 * 重置事件处理
	 * @param e
	 */
	private void resetValueActionPerformed(ActionEvent evt) {
		this.userNameTxt.setText("");
		this.passwordTxt.setText("");
		userNameTxt.requestFocus();
		
	}
	
	/**
	 * 注册事件处理
	 * @param e
	 */
	private void enrollValueActionPerformed(ActionEvent e) {
		
		String str = JOptionPane.showInputDialog(this, "输入超级管理员密码", "KEY",2);
		TbPerson p = PersonDao.findPersonByRecordNumber("T00001");
		if(str==null){
			return;
		}else if ("".equals(str)) {
			JOptionPane.showMessageDialog(this, "请至少输入一个字符", "", 1);
		}else if (p.getPassword().equals(PwEncryption.encrypt(str, "key"))) {
//		}else if (str.equals(p.getPassword())) {
			this.setVisible(false);
			dispose();
			EnrolmentFrame enrollFrame = new EnrolmentFrame();
			enrollFrame.setVisible(true);
		}else{
			JOptionPane.showMessageDialog(this, "密码错误", "", 0);
		}
		
	}
	
	/**
	 * 登陆窗口
	 */
	public static void main(String[] args) {
	}
		
}
