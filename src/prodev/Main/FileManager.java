package prodev.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JMenuItem;

import prodev.GraphicsInterface.MainFrame;
import prodev.GraphicsInterface.RightTopPanel;

public class FileManager {
	
	static File loadingFile = null;
	static File savingFile = null;
	
	public void load(String name) throws IOException{
		URL source = getClass().getResource("savedFiles/" + name);
    	InputStreamReader streamReader
                = new InputStreamReader(source.openStream(), Charset.forName("UTF-8")); //Otwieramy readera
        BufferedReader bufferedReader = new BufferedReader(streamReader); //Buforujemy radera
        String line = bufferedReader.readLine();
        int ii=0;
        while (line!= null){ 
        	String results[] = null;
        	results = line.split(" ");
        	RightTopPanel.elements.get(ii).valueField.setText(results[0]);
        	RightTopPanel.elements.get(ii).powerField.setText(results[1]);
        	if(ii != 4)
        		RightTopPanel.elements.get(ii).checkBox.setSelected(true);
            line = bufferedReader.readLine();
            ii++;
        }
    }
	
	public void save() throws IOException{
		OutputStream outPutStream = new FileOutputStream(savingFile);
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outPutStream));
		for(int ii=0;ii<5;ii++){
			String value = RightTopPanel.elements.get(ii).valueField.getText();
			String power = RightTopPanel.elements.get(ii).powerField.getText();
			bufferedWriter.write(value + " " + power);
		}
		bufferedWriter.close();
    }
	
	public void loadFilesNames(){
        String line = null;
        try{
        	InputStream stream = this.getClass().getResourceAsStream("savedFiles/");
            Scanner src= new Scanner(stream);
            while(src.hasNext()){
                line = src.next();
                final String fileName = line.toString();
                JMenuItem loadItem = new JMenuItem(line.toString());
                loadItem.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				try {
							Main.file.load(fileName);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
        			}
        		});
                MainFrame.load.add(loadItem);
            }
        }catch(Exception e){
            
        }
        
	}
	
	
	public FileManager() {
		
	}
	
}