package output;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.WindowConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


@SuppressWarnings("serial")
public class PointFrame extends JFrame {

    public PointFrame(Object[][] rowData) {
        super("Points");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 350));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        
        

        TableModel model = new PointTable(rowData);

        JTable table = new JTable(model);
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        table.setRowSorter(sorter);
        
        JScrollPane scrollPane = new JScrollPane(table);
        
        add(scrollPane, BorderLayout.CENTER);
        setSize(500,350);
        this.setVisible(true);
        
    }
    
    private class PointTable extends AbstractTableModel {

        private String[] columnNames = { "Point", "x-coordinate",
                "y-coordinate", "z-coordinate" };

        private Object[][] data;

        PointTable(Object[][] rowData) {
            this.data = rowData;
        }

        public int getColumnCount() {
            return 4;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public int getRowCount() {
            return data.length;
        }

        @SuppressWarnings({ "rawtypes", "unchecked" })
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            return false;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }

    }

    
}
