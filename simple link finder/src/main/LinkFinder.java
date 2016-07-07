package main;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkFinder {

	Iterator<String> page;
	Iterator<String> results;
	ArrayList<String> al;
	
	public static void main(String[] args) {
		LinkFinder lF = new LinkFinder();
		InputStream in = null;
		try {
			in = new FileInputStream(new File("C:/Users/Anthony Schulist/openSource/simple link finder/src/files/neumont.edu"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lF.processPage(in);
		lF.results = lF.getLinks();
	}
	
	public void processPage(InputStream in) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line;
		ArrayList<String> pageAL = new ArrayList<String>();
		try {
			while((line = br.readLine()) != null){
				pageAL.add(line);
			}
			this.page = pageAL.iterator();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String pattern = "<\\s*[Aa]\\s+[Hh][Rr][Ee][Ff]\\s*=\\s*\"([^\"]+)\"";
		Pattern p = Pattern.compile(pattern);
		al = new ArrayList<String>();
		String line2;
		for(;;){
			if(!page.hasNext()){
				break;
			}
			line2 = page.next();
			Matcher m = p.matcher(line2);
			boolean matches = m.find();
			while(matches){
				al.add(m.group(1));
				matches = m.find();
			}
		}
    }

    public Iterator<String> getLinks() {
		Iterator<String> iter = al.listIterator();
		
    	return iter;
    }
}
