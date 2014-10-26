package Input;

import interfaces.ApollonianNetwork;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

import schnyder.SchnyderAlgorithm;
import schnyder.TernaryTreeImpl;
import schnyder.Triangle;
import Datastructures.ApollNetwork;
import Geometry.PointDecimal;

public class Fileio {

    /**
     * binary file holds a triple tree coded in bits function imports it to an
     * apollean network
     * 
     * @param sc
     *            \\ scanner to the file to read from
     * @return
     */

/*    public static ApollonianNetwork binaryTreeFileToaN(Scanner sc)
            throws InvalidFileException {
        // return variables
        TernaryTreeImpl ternarytree = new TernaryTreeImpl();
        SchnyderAlgorithm sch = new SchnyderAlgorithm();
        // unfinished triangles
        ArrayList<Triangle> stack = new ArrayList<Triangle>();
        // working variables
        String tmp = new String("");
        Triangle tmp_tri;
        // line counter
        int i = 1;
        // checking for next line
        while (sc.hasNextLine()) {
            tmp = sc.nextInt();
            switch (tmp) {
            // has point in it
            case "TRUE":
                tmp_tri = stack.remove(stack.size() - 1);
                ternarytree.addNode(tmp_tri);
                Triangle[] tmp_inner_tri = tmp_tri.getSmallerTriangle();
                for (int j = 2; j >= 0; j--) {
                    stack.add(tmp_inner_tri[j]);
                }
                break;
            // no points for that face
            case "FALSE":
                stack.remove(stack.size());
                break;
            // wrong formatting or invalid tree description
            default:
                System.err.printf("Invalid file format at line %i.\n", i);
                throw new InvalidFileException();
            }
            // line counter
            i++;
        }
        return sch.impl(ternarytree);

    }
*/
    
    /**
     * file holds a triple tree coded in boolean function imports it and returns
     * a minimized
     * 
     * @param sc
     *            \\ scanner to the file to read from
     * @return
     */
    public static ApollonianNetwork booleanTreeFileToaN(Scanner sc)
            throws InvalidFileException {
        // return variables
        TernaryTreeImpl ternarytree = new TernaryTreeImpl();
        SchnyderAlgorithm sch = new SchnyderAlgorithm();
        // unfinished triangles
        ArrayList<Triangle> stack = new ArrayList<Triangle>();
        // working variables
        String tmp = new String("");
        Triangle tmp_tri;
        // line counter
        int i = 1;
        // checking for next line
        while (sc.hasNextLine()) {
            tmp = sc.nextLine();
            switch (tmp) {
            // has point in it
            case "TRUE":
                tmp_tri = stack.remove(stack.size() - 1);
                ternarytree.addNode(tmp_tri);
                Triangle[] tmp_inner_tri = tmp_tri.getSmallerTriangle();
                for (int j = 2; j >= 0; j--) {
                    stack.add(tmp_inner_tri[j]);
                }
                break;
            // no points for that face
            case "FALSE":
                stack.remove(stack.size());
                break;
            // wrong formatting or invalid tree description
            default:
                System.err.printf("Invalid file format at line %i.\n", i);
                throw new InvalidFileException();
            }
            // line counter
            i++;
        }
        // warn that there are unchecket triangles
        // file is incomplete
        if (!stack.isEmpty()) {
            System.out.printf("Warning stack still contains %i triangles.\n",
                    stack.size());
        }
        return sch.impl(ternarytree);
    }

    /**
     * file gives points which keep a graph function imports the graph directly
     * 
     * @param sc
     *            \\ scanner to read from the file
     * @return Apollean Network
     */
    public static ApollonianNetwork Points2DFileToaN(Scanner sc)
            throws InvalidFileException {

        ApollNetwork aN;
        String tmp = new String("");
        ArrayList<PointDecimal> p = new ArrayList<PointDecimal>();
        String[] tmp_list;
        for (int i = 0; i < 3; i++) {
            tmp = sc.nextLine();
            tmp_list = tmp.split("\\t");
            p.add(new PointDecimal(new BigDecimal(tmp_list[0]), new BigDecimal(
                    tmp_list[1])));
        }
        aN = new ApollNetwork(p.get(0), p.get(1), p.get(2));
        // restliche Punkte auslesen
        while (sc.hasNextLine()) {
            tmp = sc.nextLine();
            tmp_list = tmp.split("\\t");
            aN.addNode(new PointDecimal(new BigDecimal(tmp_list[0]),
                    new BigDecimal(tmp_list[1])));
        }
        sc.close();
        return aN;
    }

    /**
     * 
     * @param file
     * @return
     * @throws SecurityException
     * @throws InvalidFileException
     */
    public static ApollNetwork fileImport(File file) throws SecurityException,
            InvalidFileException {
        // check ob file existiert und leserechte
        file.exists();
        // check welche Art von Datei vorhanden
        String tmp = new String("");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine() && tmp.equals("")) {
                tmp = scanner.nextLine();
            }
            switch (tmp) {
            case "#Points2D":
                System.out.println("Es handelt sich um eine Point2D Datei :");
                return (ApollNetwork) Points2DFileToaN(scanner);
            case "#Boolean Tree":
                System.out
                        .println("Es handelt sich um eine Boolean Tree Datei :");
                return (ApollNetwork) booleanTreeFileToaN(scanner);
//            case "#Binary Tree":
//                System.out
//                        .println("Es handelt sich um eine Binary Tree Datei Datei :");
//                return binaryTreeFileToaN(scanner);
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
