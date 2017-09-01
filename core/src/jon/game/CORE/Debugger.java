package jon.game.CORE;

import java.util.ArrayList;

public class Debugger {
	public static boolean debugging;
	private static boolean whitelist = false;
	private static ArrayList<Object> list =  new ArrayList<Object>();
	
	private static String level1 = "INFO: ", level2 = "WARNING: ", level3 = "CRITICAL: ";
	
	private static void log_default(int verbose){
		System.out.print("\nTIME: " + System.currentTimeMillis() + "\t|\t");
		switch(verbose){
			case 1:
				System.out.print(level1);
				break;
			case 2:
				System.out.print(level2);
				break;
			case 3:
				System.out.print(level3);
				break;
			default:
				System.out.print(level1);
		}
	}
	
	public static void log(int verbose, String log){
		if(debugging && !whitelist) {
			log_default(verbose);
			System.out.print(log);
		}
	}
	
	public static void log(int verbose, String log, Object object){
		if(debugging && !whitelist) {
			log_default(verbose);
			System.out.print("OBJECT: " + object.toString());
			System.out.print("\t" + log);
		} else if(debugging && whitelist) {
			if(list.contains(object)){
				log_default(verbose);
				System.out.print("OBJECT: " + object.toString());
				System.out.print("\t" + log);
			}
		}
	}
	
	public static void addToList(Object object){
		list.add(object);
	}
	
	public static void remFromList(Object object){
		list.remove(object);
	}
	
	public static void mute(){
		whitelist = true;
	}
	
	public static void unmute(){
		whitelist = false;
	}
	
}
