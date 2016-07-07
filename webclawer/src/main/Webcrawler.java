package main;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import interfaces.VisitAction;

public class Webcrawler implements VisitAction{

	static String STARTURL = "http://shalladay-iis1.student.neumont.edu/";
	ArrayList<String> beenVisited;
	ArrayList<String> toVisit;
	String startingURL;
	
	public static void main(String[] args) {
		Webcrawler crawler = new Webcrawler(STARTURL);
	}
	
	public Webcrawler(String startingURL) {
		// TODO Auto-generated constructor stub
		this.startingURL = startingURL;
		toVisit.add(startingURL);
	}
	
	public void crawl(){
		while(!toVisit.isEmpty()){
			visitURL();
			
		}
	}
	
	public void visitURL(){
		Iterator<String> childrenUrl = visitAction(null);
	}

	@Override
	public Iterator<String> visitAction(URL url) {
		// TODO Auto-generated method stub
		LinkFinder lf = new LinkFinder();
		try {
			lf.processPage(url.openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<String> links = lf.getLinks();
		return links;
	}
}
