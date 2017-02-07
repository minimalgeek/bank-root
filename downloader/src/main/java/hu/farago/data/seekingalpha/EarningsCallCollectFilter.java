package hu.farago.data.seekingalpha;

import org.jsoup.nodes.Document;

public class EarningsCallCollectFilter {
	public String index;
	public Document document;
	public int count;

	public EarningsCallCollectFilter(int count) {
		this.count = count;
	}

	public EarningsCallCollectFilter(String index) {
		this.index = index;
	}
	
	public EarningsCallCollectFilter(String index, int count) {
		this.index = index;
		this.count = count;
	}
}