package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Toolkit;
import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

import model.TbPerson;
import dao.PersonDao;
import tools.PwEncryption;
import tools.StringUtil;
import view.LoginFrame;

/**
 * 注册窗体类
 * @author 22219
 *
 */
public class EnrolmentFrame extends JFrame {

	/**
	 * 串行版本标识serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	private JTextField userNameTxt;
	private JPasswordField passwordTxt;
	private JPasswordField passwordTxtEnter;
	private JLabel label_1;
	private JButton btnNewButton_2;

	/**
	 * Create the frame.
	 */
	public EnrolmentFrame() {
		
		this.setBounds(0, 0, 500, 400);
		this.setLocationRelativeTo(null);
		setResizable(false);
		
		setTitle("\u7528\u6237\u6CE8\u518C/\u4FEE\u6539");
		setIconImage(Toolkit.getDefaultToolkit().getImage(EnrolmentFrame.class.getResource("/images/storage_128px.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnrollfrm = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblEnrollfrm.setIcon(new ImageIcon(EnrolmentFrame.class.getResource("/images/userName.png")));
		lblEnrollfrm.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblEnrollfrm.setBounds(14, 62, 99, 35);
		contentPane.add(lblEnrollfrm);
		
		JButton btnNewButton = new JButton("\u6CE8\u518C");
		btnNewButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		btnNewButton.setIcon(new ImageIcon(EnrolmentFrame.class.getResource("/images/modify.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signinValueActionPerformed(e);
			}
		});
		
		JLabel label = new JLabel("\u5BC6\u7801\uFF1A");
		label.setIcon(new ImageIcon(EnrolmentFrame.class.getResource("/images/password.png")));
		label.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		label.setBounds(28, 128, 90, 25);
		contentPane.add(label);
		btnNewButton.setBounds(95, 252, 90, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u8FD4\u56DE");
		btnNewButton_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnValueActionPerformed(e);
			}
		});
		
		btnNewButton_2 = new JButton("\u4FEE\u6539");
		btnNewButton_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editValueActionPerformed(e);
			}
		});
		
		btnNewButton_2.setIcon(new ImageIcon(EnrolmentFrame.class.getResource("/images/edit.png")));
		btnNewButton_2.setBounds(203, 252, 90, 40);
		contentPane.add(btnNewButton_2);
		btnNewButton_1.setBounds(307, 252, 90, 40);
		contentPane.add(btnNewButton_1);
		
		userNameTxt = new JTextField();
		userNameTxt.setHorizontalAlignment(SwingConstants.CENTER);
		userNameTxt.setFont(new Font("微软雅黑", Font.BOLD, 20));
		userNameTxt.setToolTipText("\u8BF7\u8F93\u5165\u8981\u6CE8\u518C\u6216\u4FEE\u6539\u7684\u8D26\u53F7");
		userNameTxt.setBounds(93, 51, 303, 50);
		contentPane.add(userNameTxt);
		userNameTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setHorizontalAlignment(SwingConstants.CENTER);
		passwordTxt.setToolTipText("\u8F93\u5165\u5BC6\u7801");
		passwordTxt.setFont(new Font("微软雅黑", Font.BOLD, 20));
		passwordTxt.setBounds(93, 114, 303, 50);
		contentPane.add(passwordTxt);
		
		passwordTxtEnter = new JPasswordField();
		passwordTxtEnter.setHorizontalAlignment(SwingConstants.CENTER);
		passwordTxtEnter.setToolTipText("\u786E\u8BA4\u5BC6\u7801");
		passwordTxtEnter.setFont(new Font("微软雅黑", Font.BOLD, 20));
		passwordTxtEnter.setBounds(94, 177, 302, 50);
		contentPane.add(passwordTxtEnter);
		
		label_1 = new JLabel("\u518D\u6B21\u786E\u8BA4\uFF1A");
		label_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		label_1.setBounds(18, 192, 84, 18);
		contentPane.add(label_1);
		
		JLabel lbBg = new JLabel(new ImageIcon(this.getClass().getResource("/images/timg.png")));
		lbBg.setBounds(0, 0, 500, 400);
		this.getContentPane().add(lbBg);
	}
	
	/**
	 * 返回事件处理
	 * @param e
	 */
	private void returnValueActionPerformed(ActionEvent e) {
		dispose();
		LoginFrame frame = new LoginFrame();
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				if (JOptionPane.showConfirmDialog(frame, "Are you sure to leave this page?","Warning",JOptionPane.YES_NO_OPTION)==0) {
					System.exit(0);
				}
				else
					frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			}
		});
		
	}
	
	/**
	 * 注册事件处理
	 * @param e
	 * @throws Exception 
	 */
	private void signinValueActionPerformed(ActionEvent e) {
		TbPerson p = new TbPerson();
		String userName = this.userNameTxt.getText();
		String password = new String(this.passwordTxt.getPassword());
		String passwordEnter = new String(this.passwordTxtEnter.getPassword());
		
		if(StringUtil.isEmpty(userName)||StringUtil.isEmpty(password)||StringUtil.isEmpty(passwordEnter)){
			JOptionPane.showMessageDialog(this, "请输入完整注册信息！");
			return;
		}else if (StringUtil.getChineseCount(userName)>=1) {
			JOptionPane.showMessageDialog(this, "请勿输入中文！");
			return;
		}else if (StringUtil.getStringLength(userName)>20|StringUtil.getStringLength(userName)<4) {
			JOptionPane.showMessageDialog(this, "用户名需大于等于4位且小于等于20位！");
			return;
		}else if (StringUtil.getStringLength(password)<6) {
			JOptionPane.showMessageDialog(this, "密码需大于等于六位！");
			return;
		}else if (!password.equals(passwordEnter)) {
			JOptionPane.showMessageDialog(this, "密码确认错误！");
			return;
		}else {
			p = PersonDao.findPersonByRecordNumber(userName);//从库中查询指定员工id的记录，若不存在，则返回null
			if(p!=null){
				JOptionPane.showMessageDialog(this, "已存在该用户！");
			}else {
				TbPerson person = new TbPerson();
				person.setRecordNumber(userName);
				person.setPassword(PwEncryption.encrypt(passwordEnter, "key"));
				PersonDao.addPerson(person);
				JOptionPane.showMessageDialog(this, "注册成功");
				
			}
		}
	}
	
	/**
	 * 修改事件处理
	 * @param e
	 */
	private void editValueActionPerformed(ActionEvent e) {
		TbPerson p = new TbPerson();
		String userName = this.userNameTxt.getText();
		String password = new String(this.passwordTxt.getPassword());
		String passwordEnter = new String(this.passwordTxtEnter.getPassword());
		
		if(StringUtil.isEmpty(userName)||StringUtil.isEmpty(password)||StringUtil.isEmpty(passwordEnter)){
			JOptionPane.showMessageDialog(this, "请输入完整修改信息！");
			return;
		}else if (StringUtil.getChineseCount(userName)>=1) {
			JOptionPane.showMessageDialog(this, "请勿输入中文！");
			return;
		}else if (StringUtil.getStringLength(userName)>20|StringUtil.getStringLength(userName)<4) {
			JOptionPane.showMessageDialog(this, "用户名需大于等于4位且小于等于20位！");
			return;
		}else if (StringUtil.getStringLength(password)<6) {
			JOptionPane.showMessageDialog(this, "密码需大于等于六位！");
			return;
		}else if (!password.equals(passwordEnter)) {
			JOptionPane.showMessageDialog(this, "密码确认错误！");
			return;
		}else {
			p = PersonDao.findPersonByRecordNumber(userName);//从库中查询指定id的记录，若不存在，则返回null		
			if(p!=null){
				if(p.getRecordNumber().equals("T00001")){
					JOptionPane.showMessageDialog(this, "不可修改！");
				}else{
					TbPerson person = new TbPerson();
					person.setRecordNumber(userName);
					person.setPassword(PwEncryption.encrypt(passwordEnter, "key"));
					PersonDao.updatePersonPassword(person);
					JOptionPane.showMessageDialog(this, "修改成功！");
				}
			}else {
				JOptionPane.showMessageDialog(this, "不存在该用户！");
			}
		}
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EnrolmentFrame frame = new EnrolmentFrame();
		frame.setVisible(true);
	}
}
