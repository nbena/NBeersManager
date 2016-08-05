package org.nbena.beersmanager.exe.cmd;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.function.Function;

import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Fermentation;
import org.nbena.beersmanager.coreclasses.Style;
import org.nbena.beersmanager.exe.Utils;
import org.nbena.beersmanager.help.Configuration;
import org.nbena.beersmanager.help.Configurator;
import org.nbena.beersmanager.help.DerbyInterface;

public class CMD {
	/*
	
	private Configuration config;
	private DerbyInterface db;
	private Scanner sc;
	
	private static final String wait="I'm waiting for action";
	private static final String cmd="> ";
	



	public CMD(String dbPath, String dbPass, File fileConfigPath) throws ClassNotFoundException, SQLException {

		sc=new Scanner(System.in);
		db=new DerbyInterface();
		db.connect(dbPath, dbPass);
		LinkedList<Style> styles=new LinkedList<Style>();
		styles=db.getAllStyles();
		Utils.printStylesComplete(styles, System.out);
		
		loop("--help");
	}
	
	public void loop(String read){
		while(read.equals("--quit")==false){
			if(read.equals("--help")){
				printMenu();
			}
			else if(read.equals("--addbeer")){
				
			}
			else if(read.equals("--addbrewery")){
				
			}
			else if(read.equals("--addstyle")){
				
			}
			else if(read.equals("--changebeer")){
				
			}
			else if(read.equals("--changebrewery")){
				
			}
			else if(read.equals("--changestyle")){
				
			}
			else if(read.equals("--delbeer")){
				
			}
			else if(read.equals("--delbrewery")){
				
			}
			else if(read.equals("--delstyle")){
				
			}
			else if(read.equals("--viewbeers")){
				
			}
			else if(read.equals("--viewbreweries")){
				
			}
			else if(read.equals("--viewstyles")){
				
			}
			else if(read.equals("--viewbeers-specific")){
				
			}
			else if(read.equals("--viewbreweries-specific")){
				
			}
			else if(read.equals("--viewstyles-specifc")){
				
			}
		}
		System.exit(0);
	}
	
	public void printMenu(){
		String read=null;
		System.out.println("Beers-Manager help: version 1.0 (c) NB 2016");
		System.out.println("Commands: ");
		System.out.println("--addbeer                       to add a beer");
		System.out.println("--addstyle                      to add a beer style");
		System.out.println("--addbrewery                    to add a brewery");
		System.out.println("--changebeer                    to change a saved beer");
		System.out.println("--changebrewery                 to change a saved brewery");
		System.out.println("--changestyle                   to change a saved style");
		System.out.println("--delbeer                       to delete a save beer");
		System.out.println("--delbrewery                    to delete a save brewery");
		System.out.println("--delstyle                      to delete a save style");
		System.out.println("--viewbeers                     to view all the beers");
		System.out.println("--viewbreweries                 to view all the breweries");
		System.out.println("--viewstyles                    to view all the beer styles");
		System.out.println("--viewbeers-specific            to view beers with specific query");
		System.out.println("--viewbreweries-specific        to view breweryes with specific query");
		System.out.println("--viewstyle-specfic             to view styles with specific query");
		System.out.println("--help                          to view this help menu");
		System.out.println("--quit                          to quit the program");
		System.out.println();
		System.out.print(cmd);
		read=sc.nextLine();
		loop(read);
	}
	
	public void addStyle(){
		String read, styleMainName, styleSubcategory, description, fermentation=null;
		System.out.print("Insert the style main name:");
		styleMainName=sc.nextLine();
		while(styleMainName.matches("a*b")==false){
			System.out.println("Not a valid name. Insert it: ");
			styleMainName=sc.nextLine();
		}
		System.out.print("Insert the style sub category namee, if it is not exists please type 'AAA'.: ");
		styleSubcategory=sc.nextLine();
		while(styleMainName.matches("a*b")==false){
			System.out.println("Not a valid name. Insert it: ");
			styleMainName=sc.nextLine();
		}
		if(styleSubcategory.equals("AAA"))
			styleSubcategory=DerbyInterface.NO_SUBCATEGORY;
		System.out.print("Insert H(igh) or L(ow) for the fermentation: ");
		fermentation=sc.nextLine();
		while(fermentation.matches("(H|L)!")==false){
			System.out.println("Not a valid fermentation. You can choose between H and L");
			fermentation=sc.nextLine();
		}
		System.out.println("Insert a description: ");
		description=sc.nextLine();
		while(description.matches("(a*b)")==false){
			System.out.println("Not a valid fermentation. You can choose between H and L");
			description=sc.nextLine();
		}
		Style style=new Style(styleMainName, styleSubcategory, description, fermentation.equals("H") ? Fermentation.HIGH : Fermentation.LOW);
		try {
			db.insertNewStyleComplete(style, true);
			System.out.println("Style inserted correctly.");
		} catch (SQLException e) {
			System.out.println("Error: ");
			e.printStackTrace();
		}
		System.out.println(cmd);
		read=sc.nextLine();
		loop(read);
	}
	
	public static void main(String args[]) throws Exception{
		if(args.length!=1){
			throw new Exception("Must have password as second param.");
		}
		String dbPath=new File(".").getAbsolutePath().concat(File.separator+"Database");
		File configFile=new File(new File(".").getAbsolutePath().concat(File.pathSeparator+"config"+File.pathSeparator+"config.json"));
		CMD cmd;
		if(configFile.exists()==false){
			System.out.println("[WARNING]: config file not found");
			cmd=new CMD(dbPath, args[0], configFile);
		}
		
	}
*/
}
