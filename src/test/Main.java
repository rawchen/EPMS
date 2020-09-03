package test;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import view.LoginFrame;

import javax.swing.*;
import java.awt.*;

public class Main {
	public static void main(String[] args) {
		try{
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		}catch(Exception e){}
		UIManager.put("RootPane.setupButtonVisible", false);
	
		/** UIManager中UI字体相关的key */
		String[] DEFAULT_FONT  = new String[]{
		    "Table.font","TableHeader.font","CheckBox.font","Tree.font","Viewport.font","ProgressBar.font"
		    ,"RadioButtonMenuItem.font"
		    ,"ToolBar.font"
		    ,"ColorChooser.font"
		    ,"ToggleButton.font"
		    ,"Panel.font"
		    ,"TextArea.font"
		    ,"Menu.font"
		    ,"TableHeader.font"
		    ,"TextField.font"
		    ,"OptionPane.font"
		    ,"MenuBar.font"
		    ,"Button.font"
		    ,"Label.font"
		    ,"PasswordField.font"
		    ,"ScrollPane.font"
		    ,"MenuItem.font"
		    ,"ToolTip.font"
		    ,"List.font"
		    ,"EditorPane.font"
		    ,"Table.font"
		    ,"TabbedPane.font"
		    ,"RadioButton.font"
		    ,"CheckBoxMenuItem.font"
		    ,"TextPane.font"
		    ,"PopupMenu.font"
		    ,"TitledBorder.font"
		    ,"ComboBox.font"
		};
		for (int i = 0; i < DEFAULT_FONT.length; i++){
		    UIManager.put(DEFAULT_FONT[i],new Font("Dialog", Font.PLAIN,15));
		}
	
		LoginFrame frame = new LoginFrame();
		frame.setVisible(true);	
	}

}
