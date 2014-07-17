package Input;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

import Datastructures.ApollNetwork;
import Geometry.PointDecimal;

public class Fileio {

    
    public static ApollNetwork fileImport(File file) throws SecurityException,InvalidFileException {
        ApollNetwork aN;
        // check ob file existier und leserechte
        file.exists();
        // check welche Art von Datei vorhanden 
        String tmp = new String("");
        String[] tmp_list;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine() && tmp.equals("")) {
            tmp = scanner.nextLine();
            }
            switch (tmp) {
            case "#Points2D":
                
                System.out.println("Es handelt sich um eine Point2D Datei :");
                ArrayList<PointDecimal> p = new ArrayList<PointDecimal>();
                for (int i = 0 ; i<3; i++) {
                    tmp = scanner.nextLine();
                    tmp_list = tmp.split("\\t");
                    p.add(new PointDecimal(new BigDecimal(tmp_list[0]),new BigDecimal(tmp_list[1])));
                }
                aN = new ApollNetwork(p.get(0),p.get(1),p.get(2));
        //restliche Punkte auslesen
                while (scanner.hasNextLine()) {
                    tmp = scanner.nextLine();
                    tmp_list = tmp.split("\\t");
                    aN.addNode(new PointDecimal(new BigDecimal(tmp_list[0]),
                            new BigDecimal(tmp_list[1])));

                }
                scanner.close();
                return aN;
            case "#Tree":
                System.out.println("");
                scanner.close();
                aN = new ApollNetwork(new PointDecimal(new BigDecimal(0),new BigDecimal(0)),
                                        new PointDecimal(new BigDecimal(1), new BigDecimal(0)),
                                                new PointDecimal(new BigDecimal(0), new BigDecimal(1)));
                return aN;
            default:
                scanner.close();
                throw new InvalidFileException();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        
     
      return null;
           
    }
}
