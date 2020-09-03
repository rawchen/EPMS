package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;

import model.TbPerson;

public class PersonInfoFrame2 extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField birthdayField;
	private JTextField idCardField;
	private JTextField schoolAgeField;
	private JTextField specialtyField;
	private JTextField foreignLanguageField;
	private JTextField gradeField;
	private JTextField stateField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonInfoFrame frame = new PersonInfoFrame();
					frame.setVisible(true);
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					Dimension frameSize = frame.getSize();
					if (frameSize.height > screenSize.height) {
						frameSize.height = screenSize.height;
					}
					if (frameSize.width > screenSize.width) {
						frameSize.width = screenSize.width;
					}
					frame.setLocation((screenSize.width - frameSize.width) / 2,
							(screenSize.height - frameSize.height) / 2);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PersonInfoFrame2(String recordNumber) {
		System.out.println("recordNumber"+recordNumber);
		
	}

	public PersonInfoFrame2() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 896, 611);
		setTitle("查看员工信息");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblt = new JLabel("员工编号：");
		lblt.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblt.setBounds(36, 32, 98, 41);
		contentPane.add(lblt);
		
		JLabel recordNumberLabel = new JLabel("T0000");
		recordNumberLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		recordNumberLabel.setBounds(140, 32, 138, 41);
		contentPane.add(recordNumberLabel);
		
		JLabel photoLabel = new JLabel("New label");
		photoLabel.setBounds(665, 65, 120, 166);
		contentPane.add(photoLabel);
		photoLabel.setHorizontalAlignment(SwingConstants.CENTER);// 设置照片或文字居中显示
		photoLabel.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));// 设置边框
		photoLabel.setPreferredSize(new Dimension(120, 166));// 设置显示照片的大小
		
		JPanel deptFramePanel = new JPanel();
		deptFramePanel.setBorder(new TitledBorder(null, "所属部门",
								TitledBorder.DEFAULT_JUSTIFICATION,
								TitledBorder.DEFAULT_POSITION, null, null));
		deptFramePanel.setBounds(36, 86, 242, 70);
		contentPane.add(deptFramePanel);
		
		JComboBox deptComboBox = new JComboBox();
		deptFramePanel.add(deptComboBox);
		deptComboBox.setToolTipText("");
		
		JPanel dutyFramePanel = new JPanel();
		dutyFramePanel.setBorder(new TitledBorder(null, "职位",
										TitledBorder.DEFAULT_JUSTIFICATION,
										TitledBorder.DEFAULT_POSITION, null, null));
		dutyFramePanel.setBounds(36, 169, 242, 70);
		contentPane.add(dutyFramePanel);
		
		JComboBox dutyComboBox = new JComboBox();
		dutyFramePanel.add(dutyComboBox);
		
		JLabel nameLabel = new JLabel("姓名：");
		nameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		nameLabel.setBounds(67, 291, 56, 18);
		contentPane.add(nameLabel);
		
		JPanel sexFramePanel = new JPanel();
		sexFramePanel.setBounds(302, 32, 242, 70);
		sexFramePanel.setBorder(new TitledBorder(null, "性别",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		contentPane.add(sexFramePanel);
		
		
		
		JRadioButton nanRadioButton = new JRadioButton("男");
		sexFramePanel.add(nanRadioButton);
		
		JRadioButton nvRadioButton = new JRadioButton("女");
		sexFramePanel.add(nvRadioButton);
		
		nameField = new JTextField();
		nameField.setBounds(122, 286, 156, 30);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		JLabel birthdayLabel = new JLabel("出生日期：");
		birthdayLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		birthdayLabel.setBounds(36, 340, 88, 18);
		contentPane.add(birthdayLabel);
		
		birthdayField = new JTextField();
		birthdayField.setBounds(122, 336, 156, 30);
		contentPane.add(birthdayField);
		birthdayField.setColumns(10);
		
		JLabel idCardLabel = new JLabel("身份证：");
		idCardLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		idCardLabel.setBounds(51, 391, 64, 18);
		contentPane.add(idCardLabel);
		
		idCardField = new JTextField();
		idCardField.setBounds(122, 386, 156, 30);
		contentPane.add(idCardField);
		idCardField.setColumns(10);
		
		JPanel marriagedFramePanel = new JPanel();
		marriagedFramePanel.setBorder(new TitledBorder(null, "婚姻状况",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, null, null));
		marriagedFramePanel.setBounds(302, 109, 242, 66);
		contentPane.add(marriagedFramePanel);
		
		JRadioButton marrRadioButton = new JRadioButton("未婚");
		marriagedFramePanel.add(marrRadioButton);
		
		JRadioButton noMarrRadioButton = new JRadioButton("已婚");
		marriagedFramePanel.add(noMarrRadioButton);
		
		JPanel nativePlaceFramePanel = new JPanel();
		nativePlaceFramePanel.setBorder(new TitledBorder(null, "籍贯",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, null, null));
		nativePlaceFramePanel.setBounds(36, 429, 242, 70);
		contentPane.add(nativePlaceFramePanel);
		
		JComboBox nativePlaceComboBox = new JComboBox();
		nativePlaceFramePanel.add(nativePlaceComboBox);
		nativePlaceComboBox.addItem("福建省");
		
		JPanel partyMemberFramePanel = new JPanel();
		partyMemberFramePanel.setBorder(new TitledBorder(null, "是否党员",
								TitledBorder.DEFAULT_JUSTIFICATION,
								TitledBorder.DEFAULT_POSITION, null, null));
		partyMemberFramePanel.setBounds(302, 188, 242, 66);
		contentPane.add(partyMemberFramePanel);
		
		JRadioButton partMemberRadioButton = new JRadioButton("党员");
		partyMemberFramePanel.add(partMemberRadioButton);
		
		JRadioButton noPartMemberRadioButton = new JRadioButton("非党员");
		partyMemberFramePanel.add(noPartMemberRadioButton);
		
		JLabel schoolAgeLabel = new JLabel("学历：");
		schoolAgeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		schoolAgeLabel.setBounds(329, 304, 56, 18);
		contentPane.add(schoolAgeLabel);
		
		schoolAgeField = new JTextField();
		schoolAgeField.setBounds(388, 298, 156, 30);
		contentPane.add(schoolAgeField);
		schoolAgeField.setColumns(10);
		
		JLabel specialtyLabel = new JLabel("专业：");
		specialtyLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		specialtyLabel.setBounds(329, 365, 56, 18);
		contentPane.add(specialtyLabel);
		
		specialtyField = new JTextField();
		specialtyField.setBounds(388, 360, 156, 30);
		contentPane.add(specialtyField);
		specialtyField.setColumns(10);
		
		JLabel foreignLanguageLabel = new JLabel("外语：");
		foreignLanguageLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		foreignLanguageLabel.setBounds(329, 424, 56, 18);
		contentPane.add(foreignLanguageLabel);
		
		foreignLanguageField = new JTextField();
		foreignLanguageField.setBounds(388, 418, 156, 30);
		contentPane.add(foreignLanguageField);
		foreignLanguageField.setColumns(10);
		
		JLabel gradeLabel = new JLabel("外语水平：");
		gradeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		gradeLabel.setBounds(298, 476, 98, 18);
		contentPane.add(gradeLabel);
		
		gradeField = new JTextField();
		gradeField.setBounds(388, 469, 156, 30);
		contentPane.add(gradeField);
		gradeField.setColumns(10);
		
		JLabel stateLabel = new JLabel("员工状态：");
		stateLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		stateLabel.setBounds(601, 322, 98, 18);
		contentPane.add(stateLabel);
		
		stateField = new JTextField();
		stateField.setBounds(699, 317, 123, 30);
		contentPane.add(stateField);
		stateField.setColumns(10);
		
		JButton saveButton = new JButton("保存");
		saveButton.setBounds(586, 459, 113, 41);
		contentPane.add(saveButton);
		
		JButton exitButton = new JButton("退出");
		exitButton.setBounds(709, 459, 113, 41);
		contentPane.add(exitButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "权限",
										TitledBorder.DEFAULT_JUSTIFICATION,
										TitledBorder.DEFAULT_POSITION, null, null));
		panel.setBounds(586, 366, 236, 66);
		contentPane.add(panel);
		
		JComboBox comboBox = new JComboBox();
		panel.add(comboBox);
		TbPerson t = null;
		if (t == null || t.getPhoto() == null) {// 新建档案或未上传照片
			photoLabel.setText("双击添加照片");// 显示文字提示
		} else {
		}
	}
}
