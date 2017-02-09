package hu.farago.data.utils;

import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.junit.Test;
import org.xml.sax.SAXException;

public class URLUtilsTest {

	private static final String URL = "http://csb.stanford.edu/class/public/pages/sykes_webdesign/05_simple.html";
	
	@Test
	public void compareOldAndNewResult() throws IOException, SAXException, TikaException {
		String index1 = URLUtils.getHTMLContentOfURL_OLD(URL).replaceAll("\\s+", "");
		String index2 = URLUtils.getHTMLContentOfURL(URL).replaceAll("\\s+", "");
		
		System.out.println(index1);
		System.out.println("\n===================================\n");
		System.out.println(index2);
	}
	
}
