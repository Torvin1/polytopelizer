package output;

import interfaces.StackedPolytope;
import interfaces.StackedPolytopeDec;

import java.io.*;

public class data {
	 
	public static void writeData(StackedPolytope p, boolean first){
		File archive = new File("./data.txt");
 
		try {
 
			if (!archive.exists()) {
				archive.createNewFile();
			}
 
			
			FileWriter fw = new FileWriter(archive, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			if(first){
				for(int i=0;i<3;i++){
					bw.write( p.getPoints()[i].x() +" "+ p.getPoints()[i].y() + " " + p.getPoints()[i].z());
					bw.newLine();
				}
				bw.write( p.getPoints()[0].x() +" "+ p.getPoints()[0].y() + " " + p.getPoints()[0].z());
				bw.newLine();
				//bw.write("#");
				bw.newLine();
				bw.newLine();
			}

			if(p.isBoundary()){
				for(int i=0;i<3;i++){
					bw.write( p.getPoints()[i].x() +" "+ p.getPoints()[i].y() + " " + p.getPoints()[i].z());
					bw.newLine();
				}	
					bw.write( p.getPoints()[0].x() +" "+ p.getPoints()[0].y() + " " + p.getPoints()[0].z());
					bw.newLine();
					//bw.write("#");
					bw.newLine();
					bw.newLine();
			 }else{
				 for(int i = 0; i < p.smallerPolytopes().length; i++){
					 bw.close();
					 fw.close();
					 writeData(p.smallerPolytopes()[i], false);
			     }
			 }
			 
			bw.close();
			fw.close();
 
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	} 
}