package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import model.TbDept;
import model.TbDuty;
import model.TbNativePlace;
import model.TbPerson;
import model.TbRole;
import dao.DeptDao;
import dao.DutyDao;
import dao.NativePlaceDao;
import dao.PersonDao;
import dao.RoleDao;

public class PersonInfoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField birthdayField;
	private JTextField idCardField;
	private JTextField schoolAgeField;
	private JTextField specialtyField;
	private JTextField foreignLanguageField;
	private JTextField gradeField;
	private JTextField stateField;
	private JTextField roleField;
	private File file;
	private int selectPhotoFlag = 0;

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

	public PersonInfoFrame() {
	}
	
	public PersonInfoFrame(String recordNumber) {
		//初始化时通过前页面传过来的recordNumber查询到这个TbPerson对象，然后初始化表单。
		TbPerson p = PersonDao.queryRecordByNum(recordNumber);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 896, 611);
		setTitle("查看/修改员工信息");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblt = new JLabel("员工编号：");
		lblt.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblt.setBounds(36, 32, 98, 41);
		contentPane.add(lblt);
		
		JLabel recordNumberLabel = new JLabel();
		recordNumberLabel.setText(recordNumber);
		recordNumberLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		recordNumberLabel.setBounds(140, 32, 138, 41);
		contentPane.add(recordNumberLabel);
		
		JLabel photoLabel = new JLabel();
		photoLabel.setBounds(665, 65, 120, 166);
		contentPane.add(photoLabel);
		photoLabel.setHorizontalAlignment(SwingConstants.CENTER);// 设置照片或文字居中显示
		photoLabel.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));// 设置边框
		photoLabel.setPreferredSize(new Dimension(120, 166));// 设置显示照片的大小
		photoLabel.addMouseListener(new MouseAdapter() {// 添加鼠标监听器
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {// 判断是否为双击
					JFileChooser fileChooser = new JFileChooser();// 创建文件选取对话框
					fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
						
						@Override
						public String getDescription() {
							return "图像文件（.jpg;.gif）";
						}
						
						@Override
						public boolean accept(File file) {
							if (file.isDirectory())// 为文件夹则返回true
								return true;
							String fileName = file.getName()
									.toLowerCase();
							if (fileName.endsWith(".jpg") || fileName.endsWith(".gif"))// 为JPG或JIF格式文件则返回true
								return true;
							return false;// 否则返回false，即不显示在文件选取对话框中
						}
					});
					int i = fileChooser.showOpenDialog(getParent());// 弹出文件选取对话框并接收用户的处理信息
					if (i == fileChooser.APPROVE_OPTION) {// 用户选取了照片
						file = fileChooser.getSelectedFile();// 获得用户选取的文件对象
						selectPhotoFlag = 1;
						if (file != null) {
							ImageIcon icon = new ImageIcon(file
									.getAbsolutePath());// 创建照片对象
							photoLabel.setText(null);// 取消提示文字
							photoLabel.setIcon(icon);// 显示照片
						}
					}
				}
			}
		});
		
		JPanel deptFramePanel = new JPanel();
		deptFramePanel.setBorder(new TitledBorder(null, "所属部门",


								TitledBorder.DEFAULT_JUSTIFICATION,


								TitledBorder.DEFAULT_POSITION, null, null));
		deptFramePanel.setBounds(36, 86, 242, 70);
		contentPane.add(deptFramePanel);
		
		JComboBox deptComboBox = new JComboBox();
		deptFramePanel.add(deptComboBox);
		deptComboBox.setToolTipText("");
		List<TbDept> depts = DeptDao.findAllDept();
		for (TbDept d:depts) {
			deptComboBox.addItem(d.getName());
		}
		
		JPanel dutyFramePanel = new JPanel();
		dutyFramePanel.setBorder(new TitledBorder(null, "职位",TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		dutyFramePanel.setBounds(36, 169, 242, 70);
		contentPane.add(dutyFramePanel);
		
		JComboBox dutyComboBox = new JComboBox();
		List<TbDuty> dutys = DutyDao.findAllDuty();
		for (TbDuty d:dutys) {
			dutyComboBox.addItem(d.getName());
		}
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
		
		ButtonGroup sexGroup=new ButtonGroup();
		sexGroup.add(nanRadioButton);
		sexGroup.add(nvRadioButton);
		
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
		
		JRadioButton marrRadioButton = new JRadioButton("已婚");
		marriagedFramePanel.add(marrRadioButton);
		
		JRadioButton noMarrRadioButton = new JRadioButton("未婚");
		marriagedFramePanel.add(noMarrRadioButton);
		
		ButtonGroup marriagedGroup=new ButtonGroup();
		marriagedGroup.add(marrRadioButton);
		marriagedGroup.add(noMarrRadioButton);
		
		JPanel nativePlaceFramePanel = new JPanel();
		nativePlaceFramePanel.setBorder(new TitledBorder(null, "籍贯",TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, null, null));
		nativePlaceFramePanel.setBounds(36, 429, 242, 70);
		contentPane.add(nativePlaceFramePanel);
		
		JComboBox nativePlaceComboBox = new JComboBox();
		nativePlaceFramePanel.add(nativePlaceComboBox);
		List<TbNativePlace> nativePlaces = NativePlaceDao.findAllNativePlace();
		for (TbNativePlace np:nativePlaces) {
			nativePlaceComboBox.addItem(np.getName());
		}
		
		JPanel partyMemberFramePanel = new JPanel();
		partyMemberFramePanel.setBorder(new TitledBorder(null, "是否党员",


								TitledBorder.DEFAULT_JUSTIFICATION,


								TitledBorder.DEFAULT_POSITION, null, null));
		partyMemberFramePanel.setBounds(302, 188, 242, 66);
		contentPane.add(partyMemberFramePanel);
		
		JRadioButton partyMemberRadioButton = new JRadioButton("是");
		partyMemberFramePanel.add(partyMemberRadioButton);
		
		JRadioButton noPartyMemberRadioButton = new JRadioButton("否");
		partyMemberFramePanel.add(noPartyMemberRadioButton);
		
		ButtonGroup partyMemberGroup=new ButtonGroup();
		partyMemberGroup.add(partyMemberRadioButton);
		partyMemberGroup.add(noPartyMemberRadioButton);
		
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
		
		JPanel roleFramePanel = new JPanel();
		roleFramePanel.setBorder(new TitledBorder(null, "权限",TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		roleFramePanel.setBounds(586, 366, 236, 66);
		contentPane.add(roleFramePanel);
		
		JComboBox roleComboBox = new JComboBox();
		roleFramePanel.add(roleComboBox);
		List<TbRole> roles = RoleDao.findAllRole();
		for (TbRole r:roles) {
			roleComboBox.addItem(r.getName());
		}
		
		JButton saveButton = new JButton("保存");
		saveButton.setBounds(586, 459, 113, 41);
		contentPane.add(saveButton);
		
		
		JButton exitButton = new JButton("退出");
		exitButton.setBounds(709, 459, 113, 41);
		contentPane.add(exitButton);
		
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//创建新的TbPerson表对象，按逻辑判断所有格式是否正确，set放数据，执行update，成功则提示
				TbPerson person = new TbPerson();
				int finish = 1;
				person.setRecordNumber(recordNumber);
	
				//所属部门 职位 籍贯 权限
				person.setDeptId(deptComboBox.getSelectedIndex());
				person.setDutyId(dutyComboBox.getSelectedIndex());
				person.setNativePlaceId(nativePlaceComboBox.getSelectedIndex());
				person.setRoleId(roleComboBox.getSelectedIndex());
				
				//姓名 出生日期 身份证 学历 专业 外语 外语水平 状态
				person.setName(nameField.getText());
				
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date birthdayDate = null;
		        try{  
		            birthdayDate = formatter.parse(birthdayField.getText());  
		            System.out.print("格式正确！");
		        }catch(Exception e3){
		        	JOptionPane.showMessageDialog(null, "日期格式错误！正确例子：yyyy-mm-dd");
		        	finish = 0;
		        }
				person.setBirthday(birthdayDate);
				
				person.setIdCard(idCardField.getText());
				person.setSchoolAge(schoolAgeField.getText());
				person.setSpecialty(specialtyField.getText());
				person.setForeignLanguage(foreignLanguageField.getText());
				person.setGrade(gradeField.getText());
				person.setState(stateField.getText());
				//性别 婚姻 党员
				if(nanRadioButton.isSelected()) {
					person.setSex("男");
				}else if(nvRadioButton.isSelected()) {
					person.setSex("女");
				}else {
				}
				
				if(marrRadioButton.isSelected()) {
					person.setMarriaged("已婚");
				}else if(noMarrRadioButton.isSelected()) {
					person.setMarriaged("未婚");
				}else {
				}
				
				if(partyMemberRadioButton.isSelected()) {
					person.setPartyMember("是");
				}else if(noPartyMemberRadioButton.isSelected()) {
					person.setPartyMember("否");
				}else {
				}

				// 保存图片
				if (photoLabel.getIcon() != null) {// 数据库图片已存在或者刚完成添加照片
					if(selectPhotoFlag == 1) {//如果用户双击添加了图片
						try {
							//暂不上传，需要获取该图后缀.jpg拼接person名
							String newPhotoName = recordNumber+""+file.getName().substring(file.getName().lastIndexOf("."));
							//在服务器资源目录创建此文件
							File file2 =new File(this.getClass().getResource("/").getPath()+"personnel_photo");
							if  (!file2 .exists()  && !file2 .isDirectory()) {       
							    file2 .mkdir();
							}
							File photo = new File(this.getClass().getResource("/").getPath()+"personnel_photo/"+newPhotoName);
							person.setPhoto(newPhotoName);// 将新的图片名称保存到person对象中待上传
							if (!photo.exists()) {// 如果文件不存在则创建文件
								photo.createNewFile();
							}
							if(photo.exists()) {//因为是双击添加图，如果存在，就删除重新创建
								photo.delete();
							}
							
							InputStream inStream = new FileInputStream(file);// 创建输入流对象
							OutputStream outStream = new FileOutputStream(photo);// 创建输出流对象
							int readBytes = 0; // 读取字节数
							byte[] buffer = new byte[1024]; // 定义缓存数组
							while ((readBytes = inStream.read(buffer, 0, 1024)) != -1) { // 从输入流读取数据到缓存数组中
								outStream.write(buffer, 0, readBytes); // 将缓存数组中的数据输出到输出流
							}
							outStream.close();// 关闭输出流对象
							inStream.close();// 关闭输入流对象
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}else {//没有选择上传图片也有icon说明数据库已经存在
						//要更新信息则需要查数据库找到图片再传入一次图片信息
						TbPerson tbPerson = new TbPerson();
						tbPerson = PersonDao.findPersonByRecordNumber(recordNumber);
						person.setPhoto(tbPerson.getPhoto());
					}
					
				}
				if(finish == 1) {
					//执行update
					PersonDao.updatePerson(person);
					JOptionPane.showMessageDialog(null, "保存成功！");
				}
			}
		});
		
		
		if (p == null || p.getPhoto() == null) {// 新建档案或未上传照片
			photoLabel.setText("双击添加照片");// 显示文字提示
		} else {
			// 修改档案并且已上传照片
			File file2 =new File(this.getClass().getResource("/").getPath()+"personnel_photo");
			if  (!file2 .exists()  && !file2 .isDirectory()) {       
			    file2 .mkdir();
			}
			String photo = this.getClass().getResource("/").getPath()+"personnel_photo/"+p.getPhoto();// 组织员工照片的存放路径
			System.out.println("加载时照片位置："+photo);

			Image img = Toolkit.getDefaultToolkit().createImage(photo); 

			photoLabel.setIcon(new ImageIcon(img));// 创建照片对象并显示


		}
		deptComboBox.setSelectedIndex(p.getDeptId());
		dutyComboBox.setSelectedIndex(p.getDutyId());
		nativePlaceComboBox.setSelectedIndex(p.getNativePlaceId());
		roleComboBox.setSelectedIndex(p.getRoleId());
		nameField.setText(p.getName());
		String birthday="";
		try {
			birthday = new SimpleDateFormat("yyyy-MM-dd").format(p.getBirthday());
		} catch (Exception e1) {
			e1.printStackTrace();
			birthday = "";
		}
		birthdayField.setText(birthday);
		
		idCardField.setText(p.getIdCard());
		schoolAgeField.setText(p.getSchoolAge());
		specialtyField.setText(p.getSpecialty());
		foreignLanguageField.setText(p.getForeignLanguage());
		gradeField.setText(p.getGrade());
		stateField.setText(p.getState());
		String sex = p.getSex();
		String marriaged = p.getMarriaged();
		String partyMember = p.getPartyMember();
		if("男".equals(sex)) {
			nanRadioButton.setSelected(true);
		}else if("女".equals(sex)) {
			nvRadioButton.setSelected(true);
		}else{
		}
		
		if("已婚".equals(marriaged)) {
			marrRadioButton.setSelected(true);
		}else if("未婚".equals(marriaged)) {
			noMarrRadioButton.setSelected(true);
		}else{
		}
		
		if("是".equals(partyMember)) {
			partyMemberRadioButton.setSelected(true);
		}else if("否".equals(partyMember)) {
			noPartyMemberRadioButton.setSelected(true);
		}else{
		}
	}
}
