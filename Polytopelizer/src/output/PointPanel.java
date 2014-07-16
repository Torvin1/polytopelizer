package output;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class PointPanel extends JPanel {
    
    public PointPanel(Object[][] rowData, Object[] columnNames){
        TableModel model = new DefaultTableModel(rowData, columnNames) {
            public Class getColumnClass(int column) {
              Class returnValue;
              if ((column >= 0) && (column < getColumnCount())) {
                returnValue = getValueAt(0, column).getClass();
              } else {
                returnValue = Object.class;
              }
              return returnValue;
            }
        };
        
        JTable table = new JTable(model);
        
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        
        JScrollPane scrollPane = new JScrollPane(table);
        table.setRowSorter(sorter);
        
        this.add(scrollPane);
    }
}
