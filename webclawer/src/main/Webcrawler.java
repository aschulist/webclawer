package main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import interfaces.VisitAction;

public class Webcrawler implements VisitAction{

	static String STARTURL = "http://shalladay-iis1.student.neumont.edu";
	ArrayList<String> beenVisited;
	ArrayList<String> toVisit;
	String startingURL;
	
	public static void main(String[] args) {
		Webcrawler crawler = new Webcrawler(STARTURL);
		crawler.crawl();
		for(String url: crawler.beenVisited){
			System.out.println(url);
		}
	}
	
	public Webcrawler(String startingURL) {
		// TODO Auto-generated constructor stub
		this.startingURL = startingURL;
		toVisit = new ArrayList<String>();
		beenVisited = new ArrayList<String>();
		toVisit.add(this.startingURL);
	}
	
	public void crawl(){
		while(!toVisit.isEmpty()){
			visitURL();
		}
	}
	
	public void visitURL(){
		Iterator<String> childrenUrl = null;
		try {
			childrenUrl = visitAction(new URL(toVisit.get(0)));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		beenVisited.add(toVisit.get(0));
		toVisit.remove(0);
		while(childrenUrl.hasNext()){
			String childLink = startingURL + childrenUrl.next();
			if(!beenVisited.contains(childLink)){
				if(!toVisit.isEmpty()){
					if(!toVisit.contains(childLink)){
						toVisit.add(childLink);
					}
				} else {
					toVisit.add(childLink);
				}
			}
		}
	}

	@Override
	public Iterator<String> visitAction(URL url) {
		// TODO Auto-generated method stub
		LinkFinder lf = new LinkFinder();
		try {
			lf.processPage(url.openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		Iterator<String> links = lf.getLinks();
		return links;
	}
}
