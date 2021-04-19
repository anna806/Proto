import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void main (String args[]) {
		ArrayList<String> ourfiles = new ArrayList<String>(); 
		ArrayList<String> testfiles = new ArrayList<String>();
		ArrayList<String> errors = new ArrayList<String>(); 

		try {
		      File myObj = new File("Output\\"+ args[0]+".json");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        ourfiles.add(myReader.nextLine());
		      }
		      myReader.close();
		      myObj = new File("OutputTest\\"+ args[0]+"_test.json");
		      Scanner myReader2 = new Scanner(myObj);
		      while (myReader2.hasNextLine()) {
		        testfiles.add(myReader2.nextLine());
		      }
		      myReader2.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		boolean failed = false;
		for(int i = 0; i< ourfiles.size(); i++) {
			testfiles.set(i, testfiles.get(i).trim());
			ourfiles.set(i, ourfiles.get(i).trim());
			if(!ourfiles.get(i).equals(testfiles.get(i))) {
				errors.add(testfiles.get(i));
				errors.add(ourfiles.get(i));
				failed = true;
			}
		}
		
		if(failed) {
			try {
			      FileWriter myWriter = new FileWriter("errors.txt");
			      for(int i = 0; i< errors.size(); i++) {
						myWriter.write(errors.get(i));
					}
			      myWriter.close();
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
			System.exit(1);
		}
		else {
			System.exit(0);
		}
	}
}
