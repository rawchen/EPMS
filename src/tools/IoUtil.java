package tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * IO工具类
 * @author 22219
 *
 */
public class IoUtil {

	public static void exportTable(JTable table, File file) throws IOException {
		TableModel model = table.getModel();// 得到Jtable的Model
		FileWriter out = new FileWriter(file);

		for (int i = 0; i < model.getColumnCount(); i++) {
			out.write(model.getColumnName(i) + "\t");
		}
		out.write("\n");
		for (int i = 0; i < model.getRowCount(); i++) {
			for (int j = 0; j < model.getColumnCount(); j++) {
				 if(model.getValueAt(i,j).toString()!=null&&!model.getValueAt(i,j).toString().equals(""))
				 {
				 out.write(model.getValueAt(i,j).toString()+"\t");
				 }else{
				 out.write("null"+"\t");
				 }
//				out.write(model.getValueAt(i, j).toString() + "\t");
			}
			out.write("\n");
		}
		out.close();
		System.out.println(file);
	}
}
