package tools;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.undo.UndoManager;
import java.awt.event.*;
import java.io.*;

public class JNotepad extends JFrame implements ActionListener {

	JMenuBar menubar = new JMenuBar();
	JMenu file = new JMenu("文件(F)");
	JMenu edit = new JMenu("编辑(E)");
	JMenu format = new JMenu("格式(O)");
	JMenu help = new JMenu("帮助(H)");
	JMenuItem create = new JMenuItem("新建");
	JMenuItem open = new JMenuItem("打开...");
	JMenuItem save = new JMenuItem("保存");
	JMenuItem saveAs = new JMenuItem("另存为...");
	JMenuItem exit = new JMenuItem("退出");
	JMenuItem undo = new JMenuItem("撤销");
	JMenuItem cut = new JMenuItem("剪切");
	JMenuItem copy = new JMenuItem("复制");
	JMenuItem paste = new JMenuItem("粘贴");
	JMenuItem findRep = new JMenuItem("查找替换");
	JMenuItem selectAll = new JMenuItem("全选");
	JMenuItem font = new JMenuItem("字体");
	JMenuItem about = new JMenuItem("关于");
	JMenuItem cut2 = new JMenuItem("剪切(X)");

	JMenuItem copy2 = new JMenuItem("复制(C)");
	JMenuItem paste2 = new JMenuItem("粘贴(V)");
	JMenuItem selectAll2 = new JMenuItem("全选(A)");
	public static JTextArea textarea = new JTextArea();
	UndoManager um = new UndoManager();
	JScrollPane scroll = new JScrollPane(textarea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JPopupMenu popup = new JPopupMenu();
	String pathSelect;

	// 获取屏幕尺寸
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public JNotepad() {

		// 此处定义键盘快捷键
		// MenuBar
		file.setMnemonic(KeyEvent.VK_F);
		edit.setMnemonic(KeyEvent.VK_E);
		format.setMnemonic(KeyEvent.VK_O);
		help.setMnemonic(KeyEvent.VK_H);
		// MenuItem
		create.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		findRep.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

		// 事件监听者
		save.addActionListener(this);
		create.addActionListener(this);
		open.addActionListener(this);
		saveAs.addActionListener(this);
		exit.addActionListener(this);
		undo.addActionListener(this);
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		selectAll.addActionListener(this);
		font.addActionListener(this);
		about.addActionListener(this);
		cut2.addActionListener(this);
		copy2.addActionListener(this);
		paste2.addActionListener(this);
		selectAll2.addActionListener(this);
		findRep.addActionListener(this);
		// 设置撤销文本的管理器
		textarea.getDocument().addUndoableEditListener(um);
		textarea.setFont(Format.font);
		// 文件
		file.add(create);
		file.add(open);
		file.add(save);
		file.add(saveAs);
		file.addSeparator();
		file.add(exit);

		// 编辑
		edit.add(undo);
		edit.addSeparator();
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.addSeparator();
		edit.add(findRep);
		edit.addSeparator();
		edit.add(selectAll);

		// 格式
		format.add(font);

		// 帮助
		help.add(about);

		// 菜单栏
		menubar.add(file);
		menubar.add(edit);
		menubar.add(format);
		menubar.add(help);

		// 右键菜单
		popup.add(cut2);
		popup.add(copy2);
		popup.add(paste2);
		popup.addSeparator();
		popup.add(selectAll2);

		// 添加到文本域容器
		textarea.add(popup);

		// 匿名内部类监听器右键动作
		textarea.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					popup.show(textarea, e.getX(), e.getY());
				}
			}
		});

		// 边界布局
		this.add(menubar, BorderLayout.NORTH);
		this.add(scroll, BorderLayout.CENTER);
		this.setTitle("记事本");
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon(this.getClass().getResource("/images/edit.png")).getImage());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);

	}

	// 重写actionPerformed
	@Override
	public void actionPerformed(ActionEvent e) {
		// Event对象发生源
		if (e.getSource() == open) {

			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("文本文档(*.txt)", "txt");
			chooser.setFileFilter(filter);
			chooser.setDialogTitle("文件打开");
			chooser.showOpenDialog(null);
			chooser.setVisible(true);

			try {
				pathSelect = chooser.getSelectedFile().getPath();
				FileReader wjl = new FileReader(pathSelect);
				BufferedReader hcl = new BufferedReader(wjl);
				String s = "", zfc = "";
				while ((s = hcl.readLine()) != null) {
					zfc += (s + "\n");
				}
				textarea.setText(zfc);

			} catch (Exception e1) {
			}
		}

		if (e.getSource() == saveAs) {// 另存为

			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("文本文档(*.txt)", "txt");
			chooser.setFileFilter(filter);
			chooser.setDialogTitle("另存为");
			chooser.showSaveDialog(null);
			chooser.setVisible(true);

			PrintStream ps;
			try {
				String select = chooser.getSelectedFile().getPath();
				ps = new PrintStream(select);
				System.setOut(ps);
				System.out.println(this.textarea.getText());

			} catch (Exception e1) {
			}
		}

		if (e.getSource() == save && (pathSelect == null)) {// 保存
			JFileChooser chooser = new JFileChooser();

			chooser.setDialogTitle("保存");
			chooser.showSaveDialog(null);
			chooser.setVisible(true);

			PrintStream ps;
			try {
				pathSelect = chooser.getSelectedFile().getPath();
				ps = new PrintStream(pathSelect);
				System.setOut(ps);
				System.out.println(this.textarea.getText());

			} catch (Exception e1) {
			}
		} else if (e.getSource() == save && !(pathSelect == null)) {
			PrintStream ps;
			try {
				ps = new PrintStream(pathSelect);
				System.setOut(ps);
				System.out.println(this.textarea.getText());
			} catch (FileNotFoundException e1) {
			}
		}

		if (e.getSource() == create) {
			textarea.setText("");
			pathSelect = null;
		}

		if (e.getSource() == exit) {
			System.exit(0);
		}

		if (e.getSource() == undo) {
			if (um.canUndo()) {
				um.undo();
			}
		}

		if (e.getSource() == cut || e.getSource() == cut2) {
			textarea.cut();
		} else if (e.getSource() == copy || e.getSource() == copy2) {
			textarea.copy();
		} else if (e.getSource() == paste || e.getSource() == paste2) {
			textarea.paste();
		} else if (e.getSource() == findRep) {
			new FindAndReplace(textarea);
		}

		else if (e.getSource() == selectAll || e.getSource() == selectAll2) {
			textarea.selectAll();
		}
		if (e.getSource() == font) {
			new Format(textarea);
		}
		if (e.getSource() == about) {
			new About();
		}

	}

	public static void main(String[] args) {
		new JNotepad();
	}

}

class FindAndReplace extends JDialog implements ActionListener {// 查找和替换
	JLabel findLabel = new JLabel("查找内容：");
	JLabel repLabel = new JLabel("    替换为：");
	JTextField findTf = new JTextField(8);
	JTextField repTf = new JTextField(8);
	JButton findBtn = new JButton("查找");
	JButton repBtn = new JButton("替换");
	JPanel findPn = new JPanel();
	JPanel repPn = new JPanel();
	JTextArea textarea;

	String text;
	boolean flg = false;
	int len;
	int start = 0;
	int k = 0;

	public FindAndReplace(JTextArea textarea) {

		this.textarea = textarea;

		findPn.add(findLabel);
		findPn.add(findTf);
		findPn.add(findBtn);
		repPn.add(repLabel);
		repPn.add(repTf);
		repPn.add(repBtn);
		this.add(findPn);
		this.add(repPn);

		findBtn.addActionListener(this);
		repBtn.addActionListener(this);

		this.setTitle("查找和替换");
		this.setLayout(new GridLayout(2, 1));
		// this.setBounds(400, 200, 300, 140);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		String findText = findTf.getText();
		String repText = repTf.getText();
		text = textarea.getText();
		if (e.getSource() == findBtn) {
			findBtn.setLabel("下一个");
			if (findText != null) {
				len = findText.length();
				start = text.indexOf(findText, k);
				k = start + len;
				textarea.select(start, start + len);
				flg = true;
				if (start == -1) {
					JOptionPane.showMessageDialog(null, "已到文件尾部！", "提示", JOptionPane.INFORMATION_MESSAGE);
					start = 0;
					k = 0;
					flg = false;
				}
			}
		} else if (e.getSource() == repBtn) {
			if (flg) {
				textarea.replaceRange(repText, start, start + len);
				flg = false;
			}
		}
	}
}

// 字体格式
class Format extends JDialog implements ActionListener {

	public static int style = 0; // 全局变量类型，默认值为0
	public static int size = 16; // 全局变量字体大小，默认值为16
	public static Font font = new Font("新宋体", style, size); // 全局变量字体，默认值为新宋体

	JPanel pn = new JPanel();
	JPanel okCelPn = new JPanel();
	JPanel fontPn = new JPanel();
	JPanel ptPn = new JPanel();
	JLabel fontLabel = new JLabel("字体:   ");
	JLabel fontStyleLabel = new JLabel("    字形:   ");
	JLabel ptLabel = new JLabel("       磅值:   ");
	JButton ok = new JButton("确定");
	JButton cancel = new JButton("取消");
	GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();// 获取系统中可用的字体的名字
	String[] fontName = e.getAvailableFontFamilyNames();// 获取系统中可用的字体的名字
	String[] fontType = { "常规", "倾斜", "粗体", "粗偏斜体" };
	JList fontList = new JList(fontName);
	JList fontTypeList = new JList(fontType);
	JScrollPane fontScroll = new JScrollPane(fontList);
	JScrollPane fontTypeScroll = new JScrollPane(fontTypeList);

	JTextArea textarea;
	SpinnerModel spinnerModel = new SpinnerNumberModel(size, // initial value
			0, // min
			100, // max
			2 // Step
	);
	JSpinner spinner = new JSpinner(spinnerModel);

	public Format(JTextArea textarea) {
		this.textarea = textarea;
		ok.addActionListener(this);
		cancel.addActionListener(this);

		pn.setLayout(new GridLayout(2, 1));
		pn.add(fontPn);
		pn.add(ptPn);

		fontPn.add(fontLabel);
		fontPn.add(fontScroll);
		fontPn.add(fontStyleLabel);
		fontPn.add(fontTypeScroll);

		ptPn.add(ptLabel);
		ptPn.add(spinner);

		fontList.setVisibleRowCount(5);
		fontList.setFixedCellWidth(60);
		fontList.setSelectedIndex(50);
		fontList.setSelectedValue(font.getFontName(), true);

		fontTypeList.setVisibleRowCount(5);
		fontTypeList.setSelectedIndex(style);
		okCelPn.add(ok);
		okCelPn.add(cancel);

		okCelPn.setLayout(new FlowLayout(FlowLayout.RIGHT));

		this.add(pn, BorderLayout.CENTER);
		this.add(okCelPn, BorderLayout.SOUTH);

		this.setTitle("字体");
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok) {
			System.out.println(fontList.getSelectedValue());
			style = this.type();
			size = Integer.parseInt(spinner.getValue().toString());
			font = new Font((String) fontList.getSelectedValue(), style, size);
			textarea.setFont(font);
			this.dispose();
			System.out.println(type());
		} else if (e.getSource() == cancel) {
			this.dispose();
		}
	}

	private int type() {
		if (fontTypeList.getSelectedValue().equals("倾斜")) {
			return 1;
		} else if (fontTypeList.getSelectedValue().equals("粗体")) {
			return 2;
		} else if (fontTypeList.getSelectedValue().equals("粗偏斜体")) {
			return 3;
		} else
			return 0;
	}

}

class About extends JDialog {// 关于窗口

	About() {
		JOptionPane.showMessageDialog(null, "            作者：csq          版本：v1.0\n\n            联系：yoyling.com", "关于",
				JOptionPane.PLAIN_MESSAGE);
	}
}