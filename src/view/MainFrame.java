package view;

import java.awt.EventQueue;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Toolkit;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import dao.AccountDao;
import dao.DeptDao;
import dao.DutyDao;
import dao.PersonDao;
import dao.NativePlaceDao;
import dao.RoleDao;
import dao.TimecardDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javafx.stage.FileChooser;
import model.TbAccount;
import model.TbDept;
import model.TbDuty;
import model.TbNativePlace;
import model.TbPerson;
import model.TbRole;
import model.TbTimecard;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import tools.IoUtil;

public class MainFrame {

	public static JFrame frame;
	private JTextField nameOrIdField;
	private JTable table;
	private DefaultTableModel model;
	private DefaultTableModel model2;
	private DefaultTableModel model3;
	private JComboBox<String> deptComboBox;
	private JMenuItem lookMenItem;
	private String recordNumber;
	private JLabel statusLabel;
	private String userId;
	private JTextField explainTextField;
	private JTextField timecardTimeTextField;
	
	private JComboBox timecardTypeComboBox;
	private JComboBox personIdComboBox;
	private JComboBox ratifierIdComboBox;
	private JTable table_1;
	private JTextField textField;
	private JTable table_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame(LoginFrame.userNameTxt.getText());
					window.frame.setVisible(true);
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					Dimension frameSize = window.frame.getSize();
					if (frameSize.height > screenSize.height) {
						frameSize.height = screenSize.height;
					}
					if (frameSize.width > screenSize.width) {
						frameSize.width = screenSize.width;
					}
					window.frame.setLocation((screenSize.width - frameSize.width) / 2,
							(screenSize.height - frameSize.height) / 2);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
	}

	public MainFrame(String uid) {
		userId = uid;
		initialize();
		
		
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("企业人事管理系统");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				MainFrame.class.getResource("/images/storage_128px.png")));
		frame.setBounds(100, 100, 1600, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 关闭窗口监听
        frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
 		frame.addWindowListener(new WindowAdapter() {
 			public void windowClosing(WindowEvent e) {
 				if (JOptionPane.showConfirmDialog(null, "确认退出?", "提示",
 						JOptionPane.YES_NO_OPTION) == 0) {
 					System.exit(0);
 				}
 			}
 		});
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu menu = new JMenu("文件");
		menu.setIcon(new ImageIcon(MainFrame.class
				.getResource("/images/base.png")));
		menuBar.add(menu);
		
		
		
		JMenuItem menuItem = new JMenuItem("注销");
		menuItem.setMnemonic(KeyEvent.VK_A);
		menuItem.setIcon(new ImageIcon(MainFrame.class
				.getResource("/images/password.png")));
		menuItem.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "确认注销?", "提示",
						JOptionPane.YES_NO_OPTION) == 0) {
					frame.dispose();
					LoginFrame frame2 = new LoginFrame();
					frame2.setVisible(true);
					
				}
			}
		});
		
		JMenuItem menuItem_1 = new JMenuItem("退出");
		menuItem_1.setMnemonic(KeyEvent.VK_Q);
		menuItem_1.setIcon(new ImageIcon(MainFrame.class
				.getResource("/images/exit.png")));
		menuItem_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		menu.add(menuItem_1);
		menuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				Event.CTRL_MASK));
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "确认退出?", "提示",
						JOptionPane.YES_NO_OPTION) == 0) {
					System.exit(0);
				}
			}
		});
		
		
		JMenu viewMenu = new JMenu("主题");
		menuBar.add(viewMenu);
		viewMenu.setIcon(new ImageIcon(MainFrame.class
				.getResource("/images/view.png")));
		ButtonGroup group = new ButtonGroup();
		JRadioButtonMenuItem v1 = new JRadioButtonMenuItem("Metal", true);
		v1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		v1.setIcon(new ImageIcon(MainFrame.class.getResource("/images/edit.png")));
		viewMenu.add(v1);
		v1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager
							.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					SwingUtilities.updateComponentTreeUI(frame);
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
				}
			}
		});
		JRadioButtonMenuItem v2 = new JRadioButtonMenuItem("Motif");
		v2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		v2.setIcon(new ImageIcon(MainFrame.class.getResource("/images/edit.png")));
		viewMenu.add(v2);
		v2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager
							.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
					SwingUtilities.updateComponentTreeUI(frame);
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
				}
			}
		});
		JRadioButtonMenuItem v3 = new JRadioButtonMenuItem("Windows");
		v3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		v3.setIcon(new ImageIcon(MainFrame.class.getResource("/images/edit.png")));
		viewMenu.add(v3);
		v3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager
							.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					SwingUtilities.updateComponentTreeUI(frame);
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
				}
			}
		});
		JRadioButtonMenuItem v4 = new JRadioButtonMenuItem("Nimbus");
		v4.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		v4.setIcon(new ImageIcon(MainFrame.class.getResource("/images/edit.png")));
		viewMenu.add(v4);
		v4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager
							.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					SwingUtilities.updateComponentTreeUI(frame);
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
				}
			}
		});
		JRadioButtonMenuItem v5 = new JRadioButtonMenuItem("beautyeye");
		v5.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		v5.setIcon(new ImageIcon(MainFrame.class.getResource("/images/edit.png")));
		viewMenu.add(v5);
		v5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper
							.launchBeautyEyeLNF();
					UIManager.put("RootPane.setupButtonVisible", false);
					SwingUtilities.updateComponentTreeUI(frame);
				} catch (Exception e1) {
				}
			}
		});
		group.add(v1);
		group.add(v2);
		group.add(v3);
		group.add(v4);
		group.add(v5);
		viewMenu.add(v1);
		viewMenu.add(v2);
		viewMenu.add(v3);
		viewMenu.add(v4);
		viewMenu.add(v5);
		
		
		JMenu menu_3 = new JMenu("关于");
		menu_3.setIcon(new ImageIcon(MainFrame.class
				.getResource("/images/about.png")));
		menuBar.add(menu_3);
		
		JMenuItem menuItem_5 = new JMenuItem("帮助");
		menuItem_5.setMnemonic(KeyEvent.VK_F1);
		menuItem_5.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		menuItem_5.setIcon(new ImageIcon(MainFrame.class
				.getResource("/images/me.png")));
		menu_3.add(menuItem_5);
		menuItem_5.setMnemonic(KeyEvent.VK_F1);
		menuItem_5.setAccelerator(KeyStroke
				.getKeyStroke(KeyEvent.VK_F1, 0));
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "人事管理系统：\n"
						+ "在企业中，人事管理工作是非常重要的一项工作，它负责整个企业的日常人事安排，\n"
						+ "人员的人事管理等。高效的人事管理可以提高企业的市场竞争力，使企业具有更强的凝\n" + "聚力和活力。"
						+ "", "关于", 1);
			}
		});
		
		JPanel panel6 = new JPanel();
		panel6.setLayout(new BorderLayout());

		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(panel6,BorderLayout.CENTER);
		panel6.add(tabbedPane,BorderLayout.CENTER);
		
		
		JToolBar toolBar = new JToolBar();
		panel6.add(toolBar, BorderLayout.NORTH);

		JButton btnNewButton_2 = new JButton("导出");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dealExportbtn();
			}
			
		});
		btnNewButton_2.setIcon(new ImageIcon(MainFrame.class.getResource("/images/saveHS.png")));
		toolBar.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("打印");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(MainFrame.class.getResource("/images/PrintHS.png")));
		toolBar.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("打卡");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				dealTimeCard();
			}
		});
		btnNewButton_4.setIcon(new ImageIcon(MainFrame.class.getResource("/images/TimeCard.png")));
		toolBar.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("计算器");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new tools.JCalculator();
			}
		});
		btnNewButton_5.setIcon(new ImageIcon(MainFrame.class.getResource("/images/CalculatorHS.png")));
		toolBar.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("记事本");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new tools.JNotepad();
			}
		});
		btnNewButton_6.setIcon(new ImageIcon(MainFrame.class.getResource("/images/edit.png")));
		toolBar.add(btnNewButton_6);
		
		//状态栏
		statusLabel = new JLabel("");
		statusLabel.setBorder(BorderFactory.createEmptyBorder(5,30,5,5));
		statusLabel.setFont(new Font("微软雅黑", Font.PLAIN,15));
		toolBar.add(statusLabel);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date());
		TbPerson tPerson = PersonDao.findPersonByRecordNumber(userId);
		int personRoleId = tPerson.getRoleId();
		TbRole r =  RoleDao.findRoleById(personRoleId);
		String personRole = r.getName();
		statusLabel.setText("员工ID："+userId+"      "+"用户权限："+personRole+"      "+"登陆时间："+time);
		//tabbedPane, BorderLayout.CENTER
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("员工管理", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.NORTH);
		
		JButton addPersonButton = new JButton("增加");
		addPersonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPersonButton_Do();
			}
		});
		panel_3.add(addPersonButton);
		
		JButton delPersonButton = new JButton("删除");
		delPersonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delPersonButton_Do();
			}
		});
		panel_3.add(delPersonButton);
		
		JButton updatePersonButton = new JButton("修改");
		updatePersonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePersonButton_Do();
			}
		});
		panel_3.add(updatePersonButton);
		
		JButton refreshPersonButton = new JButton("刷新");
		refreshPersonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshPersonButton_Do();
			}
		});
		panel_3.add(refreshPersonButton);
		
		JLabel label = new JLabel("      行高");
		panel_3.add(label);
		
		JSlider rowHeightSlider = new JSlider(JSlider.HORIZONTAL, 5, 100, 40);
		rowHeightSlider.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		        int height = ((JSlider)e.getSource()).getValue();
		        table.setRowHeight(height);
		        table.repaint();
		    }
	    });
		panel_3.add(rowHeightSlider);
		
		JLabel lblid = new JLabel("                   姓名或ID：");
		panel_3.add(lblid);
		
		nameOrIdField = new JTextField();
		panel_3.add(nameOrIdField);
		nameOrIdField.setColumns(10);
		
		JButton searchButton = new JButton("搜索");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchButton_Do();
			}
		});
		panel_3.add(searchButton);
		
		JLabel label_1 = new JLabel("       部门：");
		panel_3.add(label_1);
		
		deptComboBox = new JComboBox();
		panel_3.add(deptComboBox);
		deptComboBox.addItem("-全体人员-");
		List<TbDept> depts = DeptDao.findAllDept();
		
        for (int i = 1; i < depts.size(); i++) {
        	TbDept d = depts.get(i);
        	deptComboBox.addItem(d.getName());
		}
		
		
		deptComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
            	deptComboBox_itemStateChanged_Do(e); 
            }
        });
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(){public boolean isCellEditable(int row, int column) { return false; }};
		table.setRowHeight(40);
		table.setBorder(BorderFactory.createEtchedBorder());
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//表数据居中
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, cr);
		
		//设置表头居中显示
		((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
		//添加单列排序功能
		table.setAutoCreateRowSorter(true);
		model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(new String[] {"ID","职工号","部门名","职务","姓名","性别","出生日期","身份证号","婚姻状况","籍贯","是否党员","学历","专业","外语","外语水平","状态","权限"});
		scrollPane.setViewportView(table);
		
		List<TbPerson> persons = PersonDao.findAllPerson();
		for (TbPerson p:persons) {
			TbNativePlace np = NativePlaceDao.findNativePlaceById(p.getNativePlaceId());
			TbDept dept = DeptDao.findDeptById(p.getDeptId());
			TbDuty duty = DutyDao.findDutyById(p.getDutyId());
			TbRole role = RoleDao.findRoleById(p.getRoleId());
			
			String birthday;
			try {
				birthday = new SimpleDateFormat("yyyy-MM-dd").format(p.getBirthday());
			} catch (NullPointerException e1) {
				System.out.println("日期格式转换出错");
				e1.printStackTrace();
				birthday = "";
			}
			model.addRow(new String[] {String.valueOf(p.getId()),p.getRecordNumber(),dept.getName(),duty.getName(),p.getName(),p.getSex(),birthday,p.getIdCard(),p.getMarriaged(),String.valueOf(np.getName()),p.getPartyMember(),p.getSchoolAge(),p.getSpecialty(),p.getForeignLanguage(),p.getGrade(),p.getState(),role.getName()});
		}
		
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("微软雅黑",Font.PLAIN,15));
        header.setPreferredSize(new Dimension(header.getWidth(),25));
        
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("部门管理", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6, BorderLayout.NORTH);
		
		JButton button = new JButton("增加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDeptButton_Do();
			}
		});
		panel_6.add(button);
		
		JButton button_1 = new JButton("删除");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delDeptButton_Do();
			}
		});
		panel_6.add(button_1);
		
		JButton button_2 = new JButton("修改");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateDeptButton_Do();
			}
		});
		panel_6.add(button_2);
		
		JButton button_3 = new JButton("刷新");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshDeptButton_Do();
			}
		});
		panel_6.add(button_3);
		
		JLabel label_6 = new JLabel("      行高");
		panel_6.add(label_6);
		
		JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 5, 100, 40);
		slider.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		        int height = ((JSlider)e.getSource()).getValue();
		        table_1.setRowHeight(height);
		        table_1.repaint();
		    }
	    });
		panel_6.add(slider);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);
		
		table_1 = new JTable() {public boolean isCellEditable(int row, int column) {return false;}};
		table_1.setRowHeight(40);
		table_1.setBorder(BorderFactory.createEtchedBorder());
		table_1.setAutoCreateRowSorter(true);
		table_1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//表数据居中
		DefaultTableCellRenderer cr2 = new DefaultTableCellRenderer();
		cr2.setHorizontalAlignment(JLabel.CENTER);
		table_1.setDefaultRenderer(Object.class, cr2);
		
		//设置表头居中显示
		((DefaultTableCellRenderer)table_1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
		//添加单列排序功能
		table_1.setAutoCreateRowSorter(true);
		model2 = (DefaultTableModel) table_1.getModel();
		model2.setColumnIdentifiers(new String[] {"ID","部门名"});
		scrollPane_1.setViewportView(table_1);
		
		List<TbDept> deptss = DeptDao.findAllDeptExceptZero();
		for (TbDept dept:deptss) {
			model2.addRow(new String[] {String.valueOf(dept.getId()),dept.getName()});
		}
		
		JTableHeader header2 = table_1.getTableHeader();
		header2.setFont(new Font("微软雅黑",Font.PLAIN,15));
        header2.setPreferredSize(new Dimension(header2.getWidth(),25));
		
		
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("考勤登记", null, panel_2, null);
		panel_2.setLayout(null);
		
		JButton doTimecardButton = new JButton("执行");
		doTimecardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTimecard_action();
			}

		});
		doTimecardButton.setBounds(428, 434, 266, 44);
		panel_2.add(doTimecardButton);
		
		timecardTypeComboBox = new JComboBox();
		timecardTypeComboBox.setBounds(428, 62, 266, 44);
		timecardTypeComboBox.setToolTipText("");
		List<TbAccount> accounts = AccountDao.findAllAccount();
		for (TbAccount a:accounts) {
			timecardTypeComboBox.addItem(a.getName());
		}
		panel_2.add(timecardTypeComboBox);
		
		explainTextField = new JTextField();
		explainTextField.setBounds(430, 286, 797, 117);
		panel_2.add(explainTextField);
		explainTextField.setColumns(10);
		
		JLabel label_2 = new JLabel("考勤类型：");
		label_2.setBounds(319, 77, 99, 18);
		panel_2.add(label_2);
		
		JLabel label_3 = new JLabel("说明：");
		label_3.setBounds(342, 301, 72, 18);
		panel_2.add(label_3);
		
		JLabel label_4 = new JLabel("时间：");
		label_4.setBounds(873, 75, 72, 18);
		panel_2.add(label_4);
		
		timecardTimeTextField = new JTextField();
		timecardTimeTextField.setBounds(961, 62, 266, 44);
		
		timecardTimeTextField.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		panel_2.add(timecardTimeTextField);
		timecardTimeTextField.setColumns(10);
		
		JLabel label_5 = new JLabel("审批人工号：");
		label_5.setBounds(838, 179, 113, 18);
		panel_2.add(label_5);
		
		JLabel lblid_1 = new JLabel("员工编号：");
		lblid_1.setBounds(319, 179, 101, 18);
		panel_2.add(lblid_1);
		
		personIdComboBox = new JComboBox();
		personIdComboBox.setBounds(428, 166, 266, 44);
		List<TbPerson> personss = PersonDao.findAllPerson();
		for (TbPerson p:personss) {
			personIdComboBox.addItem(p.getRecordNumber());
		}
		panel_2.add(personIdComboBox);
		
		ratifierIdComboBox = new JComboBox();
		ratifierIdComboBox.setBounds(961, 166, 266, 44);
		for (TbPerson p:personss) {
			ratifierIdComboBox.addItem(p.getRecordNumber());
		}
		panel_2.add(ratifierIdComboBox);
		
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("工资管理", null, panel_5, null);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, BorderLayout.NORTH);
		
		JButton button_7 = new JButton("刷新");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model3.setRowCount(0);
				List<TbPerson> personssss = PersonDao.findAllPerson();
				for (TbPerson p:personssss) {
					TbDept dept = DeptDao.findDeptById(p.getDeptId());
					int a1 = TimecardDao.findTimecardCountByPersonId(1,p.getRecordNumber());
					int a2 = TimecardDao.findTimecardCountByPersonId(2,p.getRecordNumber());
					int a3 = TimecardDao.findTimecardCountByPersonId(3,p.getRecordNumber());
					int a4 = TimecardDao.findTimecardCountByPersonId(4,p.getRecordNumber());
					String money1 = String.valueOf(a1 * Integer.valueOf(AccountDao.findAccountMoneyById(1)));
					String money2 = String.valueOf(a2 * Integer.valueOf(AccountDao.findAccountMoneyById(2)));
					String money3 = String.valueOf(a3 * Integer.valueOf(AccountDao.findAccountMoneyById(3)));
					String money4 = String.valueOf(a4 * Integer.valueOf(AccountDao.findAccountMoneyById(4)));
					int count = 3000 + Integer.parseInt(money1) - Integer.parseInt(money2) - Integer.parseInt(money3) + Integer.parseInt(money4);
					String count1 = String.valueOf(count);
					model3.addRow(new String[] {p.getRecordNumber(),p.getName(),dept.getName(),"3000",money1,money4,money2,money3,count1});
				}
			}
		});
		panel_7.add(button_7);
		
		JLabel label_7 = new JLabel("      行高");
		panel_7.add(label_7);
		
		JSlider slider_1 = new JSlider(SwingConstants.HORIZONTAL, 5, 100, 40);
		slider_1.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		        int height = ((JSlider)e.getSource()).getValue();
		        table_2.setRowHeight(height);
		        table_2.repaint();
		    }
	    });
		panel_7.add(slider_1);
		
		JLabel label_8 = new JLabel("                   姓名或ID：");
		panel_7.add(label_8);
		
		textField = new JTextField();
		textField.setColumns(10);
		panel_7.add(textField);
		
		JButton button_8 = new JButton("搜索");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//判断nameOrIdField只包含字母和数字就根据recordNumber查找
				Boolean isLetterDigit = textField.getText().matches("^[a-z0-9A-Z]+$");
				if(isLetterDigit==true) {
					model3.setRowCount(0);
					List<TbPerson> personss = PersonDao.likePersonByRecordNumber(textField.getText().trim());
					for (TbPerson p:personss) {
						TbDept dept = DeptDao.findDeptById(p.getDeptId());
						int a1 = TimecardDao.findTimecardCountByPersonId(1,p.getRecordNumber());
						int a2 = TimecardDao.findTimecardCountByPersonId(2,p.getRecordNumber());
						int a3 = TimecardDao.findTimecardCountByPersonId(3,p.getRecordNumber());
						int a4 = TimecardDao.findTimecardCountByPersonId(4,p.getRecordNumber());
						String money1 = String.valueOf(a1 * Integer.valueOf(AccountDao.findAccountMoneyById(1)));
						String money2 = String.valueOf(a2 * Integer.valueOf(AccountDao.findAccountMoneyById(2)));
						String money3 = String.valueOf(a3 * Integer.valueOf(AccountDao.findAccountMoneyById(3)));
						String money4 = String.valueOf(a4 * Integer.valueOf(AccountDao.findAccountMoneyById(4)));
						int count = 3000 + Integer.parseInt(money1) - Integer.parseInt(money2) - Integer.parseInt(money3) + Integer.parseInt(money4);
						String count1 = String.valueOf(count);
						model3.addRow(new String[] {p.getRecordNumber(),p.getName(),dept.getName(),"3000",money1,money4,money2,money3,count1});
					}
				}
			}
		});
		panel_7.add(button_8);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_5.add(scrollPane_2, BorderLayout.CENTER);
		
		table_2 = new JTable() {public boolean isCellEditable(int row, int column) {return false;}};
		table_2.setRowHeight(40);
		table_2.setBorder(BorderFactory.createEtchedBorder());
		table_2.setAutoCreateRowSorter(true);
		scrollPane_2.setViewportView(table_2);
		
		table_2.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//表数据居中
		DefaultTableCellRenderer cr3 = new DefaultTableCellRenderer();
		cr3.setHorizontalAlignment(JLabel.CENTER);
		table_2.setDefaultRenderer(Object.class, cr3);
		
		//设置表头居中显示
		((DefaultTableCellRenderer)table_2.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
		//添加单列排序功能
		table_2.setAutoCreateRowSorter(true);
		model3 = (DefaultTableModel) table_2.getModel();
		model3.setColumnIdentifiers(new String[] {"员工编号","姓名","部门","基本工资","加班","值班","迟到","早退","总工资"});
		
		List<TbPerson> personssss = PersonDao.findAllPerson();
		for (TbPerson p:personssss) {
			TbDept dept = DeptDao.findDeptById(p.getDeptId());
			int a1 = TimecardDao.findTimecardCountByPersonId(1,p.getRecordNumber());
			int a2 = TimecardDao.findTimecardCountByPersonId(2,p.getRecordNumber());
			int a3 = TimecardDao.findTimecardCountByPersonId(3,p.getRecordNumber());
			int a4 = TimecardDao.findTimecardCountByPersonId(4,p.getRecordNumber());
			String money1 = String.valueOf(a1 * Integer.valueOf(AccountDao.findAccountMoneyById(1)));
			String money2 = String.valueOf(a2 * Integer.valueOf(AccountDao.findAccountMoneyById(2)));
			String money3 = String.valueOf(a3 * Integer.valueOf(AccountDao.findAccountMoneyById(3)));
			String money4 = String.valueOf(a4 * Integer.valueOf(AccountDao.findAccountMoneyById(4)));
			int count = 3000 + Integer.parseInt(money1) - Integer.parseInt(money2) - Integer.parseInt(money3) + Integer.parseInt(money4);
			String count1 = String.valueOf(count);
			model3.addRow(new String[] {p.getRecordNumber(),p.getName(),dept.getName(),"3000",money1,money4,money2,money3,count1});
		}
		
		JTableHeader header3 = table_2.getTableHeader();
		header3.setFont(new Font("微软雅黑",Font.PLAIN,15));
        header3.setPreferredSize(new Dimension(header3.getWidth(),25));
        
        
        
        
        
//		panel_5.add(table_2, BorderLayout.WEST);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("系统管理", null, panel_4, null);
		
		JPopupMenu m_popupMenu = new JPopupMenu();
        JMenuItem refreshMenItem = new JMenuItem("  刷新  ");
        refreshMenItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/refresh.png")));
        refreshMenItem.setMnemonic(KeyEvent.VK_F5);
        refreshMenItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        refreshMenItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	refreshPersonButton_Do();
            } 
        });
        lookMenItem = new JMenuItem("  查看  ");
        lookMenItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/ActualSizeHS.png")));
        lookMenItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	PersonInfoFrame pl = new PersonInfoFrame(recordNumber);
            	pl.setVisible(true);
            }
        });
        
        JMenuItem deleteMenItem = new JMenuItem("  删除  ");
        deleteMenItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/delete.png")));
        deleteMenItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	delPersonButton_Do();
            } 
        });
        
        m_popupMenu.add(refreshMenItem);
        m_popupMenu.add(lookMenItem);
        m_popupMenu.add(deleteMenItem);
        
        
        table.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
		        if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
		        	int focusedRowIndex = table.rowAtPoint(e.getPoint());
		        	if (focusedRowIndex == -1) {
		        		return;
		            }
		            //将表格所选项设为当前右键点击的行
		            table.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
		            int row = table.getSelectedRow();
		            recordNumber = (String)table.getValueAt(row,1);
		            //弹出菜单
		            m_popupMenu.show(table, e.getX(), e.getY());
		        }
		        if(e.getClickCount()==2 && e.getButton() == MouseEvent.BUTTON1){
		        	int focusedRowIndex = table.rowAtPoint(e.getPoint());
		        	if (focusedRowIndex == -1) {
		        		return;
		            }
		        	table.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
		        	int row = table.getSelectedRow();
		            recordNumber = (String)table.getValueAt(row,1);
		            PersonInfoFrame pl = new PersonInfoFrame(recordNumber);
	            	pl.setVisible(true);
		            
		        }
			}
		});	
	}
	
	private void addPersonButton_Do() {
		AddPersonInfoFrame addPersonInfoFrame = new AddPersonInfoFrame();
		addPersonInfoFrame.setVisible(true);
	}
	
	private void addDeptButton_Do() {
		String inputValue = JOptionPane.showInputDialog("请输入部门名");
		TbDept d = DeptDao.findDeptByName(inputValue.trim());
		if(d!=null){
			JOptionPane.showMessageDialog(null, "已存在该部门！");
		}else{
			TbDept d2 = new TbDept();
			d2.setName(inputValue);
			DeptDao.addDept(d2);
			JOptionPane.showMessageDialog(null, "添加成功！");
			refreshDeptButton_Do();
		}
	}
	
	private void delPersonButton_Do() {
		int row = table.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(null, "请先从表格中选中一行员工信息！");
		}else {
			recordNumber = (String)table.getValueAt(row,1);
			int value = JOptionPane.showConfirmDialog(null,"你确认删除选中员工吗？","",JOptionPane.YES_NO_OPTION);
			if (value==JOptionPane.YES_OPTION) {
				PersonDao.deletePersonByrecordNumber(recordNumber);
				refreshPersonButton_Do();
			}else {
			}			
		}
		
	}
	
	private void delDeptButton_Do() {
		int row = table_1.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(null, "请先从表格中选中一行部门信息！");
		}else {
			int id = Integer.parseInt((String)table_1.getValueAt(row,0));
			int value = JOptionPane.showConfirmDialog(null,"你确认删除选中部门吗？","",JOptionPane.YES_NO_OPTION);
			if (value==JOptionPane.YES_OPTION) {
				DeptDao.deleteDeptById(id);
				refreshDeptButton_Do();
			}else {
			}			
		}
		
	}
	
	private void updatePersonButton_Do() {
		int row = table.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(null, "请先从表格中选中一行员工信息！");
		}else {
			recordNumber = (String)table.getValueAt(row,1);
			PersonInfoFrame pl = new PersonInfoFrame(recordNumber);
			pl.setVisible(true);
		}
	}
	
	private void updateDeptButton_Do() {
		int row = table_1.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(null, "请先从表格中选中一行员工信息！");
		}else {
			String input = JOptionPane.showInputDialog("请输入修改后的部门名");
			if (input == null) {

			} else {
				int id = Integer.parseInt((String)table_1.getValueAt(row,0));
				DeptDao.updateDept(id,input);
				JOptionPane.showMessageDialog(null, "修改成功！");
				refreshDeptButton_Do();
			}

		}
	}
	
	private void refreshPersonButton_Do() {
		deptComboBox.setSelectedIndex(0);
		model.setRowCount(0);
        List<TbPerson> personss = PersonDao.findAllPerson();
        for (TbPerson p:personss) {
			TbNativePlace np = NativePlaceDao.findNativePlaceById(p.getNativePlaceId());
			TbDept dept = DeptDao.findDeptById(p.getDeptId());
			TbDuty duty = DutyDao.findDutyById(p.getDutyId());
			TbRole role = RoleDao.findRoleById(p.getRoleId());
			String birthday;
			try {
				birthday = new SimpleDateFormat("yyyy-MM-dd").format(p.getBirthday());
			} catch (Exception e) {
				System.out.println("日期格式转换出错");
				birthday = "";
				e.printStackTrace();
			}
			model.addRow(new String[] {String.valueOf(p.getId()),p.getRecordNumber(),dept.getName(),duty.getName(),p.getName(),p.getSex(),birthday,p.getIdCard(),p.getMarriaged(),String.valueOf(np.getName()),p.getPartyMember(),p.getSchoolAge(),p.getSpecialty(),p.getForeignLanguage(),p.getGrade(),p.getState(),role.getName()});
        }
	}
	
	private void searchButton_Do() {
		//判断nameOrIdField只包含字母和数字就根据recordNumber查找
		Boolean isLetterDigit = nameOrIdField.getText().matches("^[a-z0-9A-Z]+$");
		if(isLetterDigit==true) {
			model.setRowCount(0);
			List<TbPerson> personss = PersonDao.likePersonByRecordNumber(nameOrIdField.getText().trim());
			for (TbPerson p:personss) {
				TbNativePlace np = NativePlaceDao.findNativePlaceById(p.getNativePlaceId());
				TbDept dept = DeptDao.findDeptById(p.getDeptId());
				TbDuty duty = DutyDao.findDutyById(p.getDutyId());
				TbRole role = RoleDao.findRoleById(p.getRoleId());
				String birthday;
				try {
					birthday = new SimpleDateFormat("yyyy-MM-dd").format(p.getBirthday());
				} catch (Exception e) {
					System.out.println("日期格式转换出错");
					birthday = "";
					e.printStackTrace();
				}
				model.addRow(new String[] {String.valueOf(p.getId()),p.getRecordNumber(),dept.getName(),duty.getName(),p.getName(),p.getSex(),birthday,p.getIdCard(),p.getMarriaged(),String.valueOf(np.getName()),p.getPartyMember(),p.getSchoolAge(),p.getSpecialty(),p.getForeignLanguage(),p.getGrade(),p.getState(),role.getName()});	
			}
		}else {
			model.setRowCount(0);
			List<TbPerson> personss = PersonDao.likePersonByName(nameOrIdField.getText().trim());
			for (TbPerson p:personss) {
				TbNativePlace np = NativePlaceDao.findNativePlaceById(p.getNativePlaceId());
				TbDept dept = DeptDao.findDeptById(p.getDeptId());
				TbDuty duty = DutyDao.findDutyById(p.getDutyId());
				TbRole role = RoleDao.findRoleById(p.getRoleId());
				String birthday;
				try {
					birthday = new SimpleDateFormat("yyyy-MM-dd").format(p.getBirthday());
				} catch (Exception e) {
					System.out.println("日期格式转换出错");
					birthday = "";
					e.printStackTrace();
				}
				model.addRow(new String[] {String.valueOf(p.getId()),p.getRecordNumber(),dept.getName(),duty.getName(),p.getName(),p.getSex(),birthday,p.getIdCard(),p.getMarriaged(),String.valueOf(np.getName()),p.getPartyMember(),p.getSchoolAge(),p.getSpecialty(),p.getForeignLanguage(),p.getGrade(),p.getState(),role.getName()});
			}
		}
	}
	
	private void deptComboBox_itemStateChanged_Do(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
            System.out.println(e.getItem());
            //如果deptComboBox.selectvalue为-全体人员-那么查找所有人
            if("-全体人员-".equals(e.getItem())) {
            	model.setRowCount(0);
                List<TbPerson> personss = PersonDao.findAllPerson();
                for (TbPerson p:personss) {
					TbNativePlace np = NativePlaceDao.findNativePlaceById(p.getNativePlaceId());
					TbDept dept = DeptDao.findDeptById(p.getDeptId());
					TbDuty duty = DutyDao.findDutyById(p.getDutyId());
					TbRole role = RoleDao.findRoleById(p.getRoleId());
					String birthday;
					try {
						birthday = new SimpleDateFormat("yyyy-MM-dd").format(p.getBirthday());
					} catch (Exception e2) {
						System.out.println("日期格式转换出错");
						birthday = "";
						e2.printStackTrace();
					}
					model.addRow(new String[] {String.valueOf(p.getId()),p.getRecordNumber(),dept.getName(),duty.getName(),p.getName(),p.getSex(),birthday,p.getIdCard(),p.getMarriaged(),String.valueOf(np.getName()),p.getPartyMember(),p.getSchoolAge(),p.getSpecialty(),p.getForeignLanguage(),p.getGrade(),p.getState(),role.getName()});	
				}
            }else {
            	model.setRowCount(0);
                List<TbPerson> personss = PersonDao.findPersonByDeptId(deptComboBox.getSelectedIndex());
                for (TbPerson p:personss) {
					TbNativePlace np = NativePlaceDao.findNativePlaceById(p.getNativePlaceId());
					TbDept dept = DeptDao.findDeptById(p.getDeptId());
					TbDuty duty = DutyDao.findDutyById(p.getDutyId());
					TbRole role = RoleDao.findRoleById(p.getRoleId());
					model.addRow(new String[] {String.valueOf(p.getId()),p.getRecordNumber(),dept.getName(),duty.getName(),p.getName(),p.getSex(),new SimpleDateFormat("yyyy-MM-dd").format(p.getBirthday()),p.getIdCard(),p.getMarriaged(),String.valueOf(np.getName()),p.getPartyMember(),p.getSchoolAge(),p.getSpecialty(),p.getForeignLanguage(),p.getGrade(),p.getState(),role.getName()});	
				}
            }
            
        }
	}
	
	private void refreshDeptButton_Do() {
		model2.setRowCount(0);
		List<TbDept> deptss = DeptDao.findAllDeptExceptZero();
		for (TbDept dept:deptss) {
			model2.addRow(new String[] {String.valueOf(dept.getId()),dept.getName()});
		}
	}
	
	
	//考勤登记
	private void addTimecard_action() {
		int type = timecardTypeComboBox.getSelectedIndex()+1;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date timecardDate;
		try {
			timecardDate = sdf.parse(timecardTimeTextField.getText());
			String personId = (String) personIdComboBox.getSelectedItem();
			String raId = (String) ratifierIdComboBox.getSelectedItem();
			String explain = explainTextField.getText();
			System.out.println(type +" "+timecardDate+" "+personId+" "+raId+" "+explain);
			TbTimecard tc = new TbTimecard(type,personId,timecardDate,raId,explain);
			TimecardDao.addTimecard(tc);
			JOptionPane.showMessageDialog(null, "执行成功！");
			
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "日期格式出错");
			e.printStackTrace();
		}	
	}
	
	//导出
	protected void dealExportbtn() {
//		try {
//			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//			String time = df.format(new Date());
//			String str = JOptionPane.showInputDialog(null, "输入导出路径(不包含文件名)，路径之间用\\\\标识：", "KEY",2);
//			if(str==null){
//				return;
//			}else{
//				tools.IoUtil.exportTable(table,new File(str+"EmployeeTable-"+time+".xls"));
//			    JOptionPane.showMessageDialog(null, "成功导出文件："+str+"EmployeeTable-"+time+".xls");
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//			JOptionPane.showMessageDialog(null, "导出表格文件出错！");
//		}

		try {

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			fileChooser.showSaveDialog(frame);//显示保存对话框
			String fi = fileChooser.getSelectedFile().getAbsolutePath()+".xls";
			System.out.println(fi);
//			FileWriter out = new FileWriter(fi);//新建输出
			BufferedWriter writer = new BufferedWriter (new OutputStreamWriter(new FileOutputStream (fi,true),"GBK"));
			for (int i = 0; i < table.getColumnCount(); i++) {
				writer.write(table.getColumnName(i) + "\t");
			}
			writer.write("\n");
			for (int i = 0; i < table.getRowCount(); i++) {
				for (int j = 0; j < table.getColumnCount(); j++) {
					try {
						writer.write(table.getValueAt(i, j).toString() + "\t");
//						System.out.println(table.getValueAt(i, j).toString());
					} catch (NullPointerException e) {
						writer.write(""+"\t");
						e.printStackTrace();
					}
				}
				writer.write("\n");
			}
			writer.close();
			JOptionPane.showMessageDialog(null, "文件导出成功");
		} catch (Exception ee) {
			ee.printStackTrace();
		}

	}
}
