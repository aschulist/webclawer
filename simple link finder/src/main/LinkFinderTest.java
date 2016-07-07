package main;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

public class LinkFinderTest {

	@Test
	public void test() {
		LinkFinder lF = new LinkFinder();
		InputStream in = null;
		try {
			in = new FileInputStream(new File("C:/Users/Anthony Schulist/openSource/simple link finder/src/files/results"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line;
		ArrayList<String> page = new ArrayList<String>();
		Iterator<String> results = null;
		try {
			while((line = br.readLine()) != null){
				page.add(line);
			}
			results = page.iterator();
		} catch (IOException e) {
			e.printStackTrace();
		}
		InputStream in2 = null;
		try {
			in2 = new FileInputStream(new File("C:/Users/Anthony Schulist/openSource/simple link finder/src/files/neumont.edu"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lF.processPage(in2);
		Iterator<String> links = lF.getLinks();
		while(links.hasNext() && results.hasNext()){
			String linkLine = links.next();
			String resultsLine = results.next();
			boolean test = linkLine.equals(resultsLine);
			assertTrue(test);
		}
	}

}
