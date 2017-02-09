package hu.farago.data.utils;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import com.google.common.collect.Lists;

public class URLUtils {

	public static final String UTF_8 = "UTF-8";

	private static final Random RAND = new Random();
	private static final List<String> USER_AGENT_CHOICES = Lists.newArrayList(
	                      "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:23.0) Gecko/20100101 Firefox/23.0",
	                      "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.62 Safari/537.36",
	                      "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0)",
	                      "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36",
	                      "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36",
	                      "Mozilla/5.0 (X11; Linux x86_64; rv:24.0) Gecko/20140205 Firefox/24.0 Iceweasel/24.3.0",
	                      "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:28.0) Gecko/20100101 Firefox/28.0",
	                      "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:28.0) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2"
	);

	public static String getHTMLContentOfURL_OLD(String url) throws IOException, SAXException, TikaException {
		String rawText = IOUtils.toString(new URL(url), UTF_8);
		return rawText;
	}
	
	public static Document getDocumentContentOfURL(String url) throws IOException {
		String agent = USER_AGENT_CHOICES.get(RAND.nextInt(USER_AGENT_CHOICES.size()));
		Connection connection = Jsoup.connect(url).userAgent(agent).timeout(10000).ignoreContentType(true);
        return connection.get();
	}
	
	public static String getHTMLContentOfURL(String url) throws IOException {
		return getDocumentContentOfURL(url).toString();
	}

	public static String getContentOfURL(String url) throws IOException, SAXException, TikaException {
		ContentHandler handler = new BodyContentHandler(-1);
		String rawText = getHTMLContentOfURL_OLD(url);
		new HtmlParser().parse(IOUtils.toInputStream(rawText), handler, new Metadata(), new ParseContext());
		String plainText = StringUtils.normalizeSpace(handler.toString());
		return plainText;
	}
	
	public static String getContentOfHTMLContent(String content) throws IOException, SAXException, TikaException {
		ContentHandler handler = new BodyContentHandler(-1);
		new HtmlParser().parse(IOUtils.toInputStream(content), handler, new Metadata(), new ParseContext());
		String plainText = StringUtils.normalizeSpace(handler.toString());
		return plainText;
	}

}
