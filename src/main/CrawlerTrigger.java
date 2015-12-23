package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import pojo.Tweet;
import twitter4j.Twitter;
import twitterfactory.Twitter4jFactory;
import utils.ArrayJSONConverter;
import utils.DateUtility;
import utils.FileWriter;
import utils.PropertiesFileReader;
import crawl.TweetsCrawler;

public class CrawlerTrigger {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try {
			Twitter twitterInstance = getTwitterInstance();
			List<Tweet> tweetsDataList = new ArrayList<Tweet>();
			Map<String, String> queryParamsMap = PropertiesFileReader.getProperties();
			List<String> languages = getQueryLanguage(queryParamsMap);
			String since = queryParamsMap.get("SINCE");
			FileWriter writer = new FileWriter();
			JSONObject json = new JSONObject();
			for(String language : languages) {
				List<String> criteria = getQuerySearchCriteria(queryParamsMap);
				if(!criteria.isEmpty())
					tweetsDataList = getTwitterData(twitterInstance, language, criteria, since);
				System.out.println(tweetsDataList.size()+"---"+language);
				json.put("data", ArrayJSONConverter.getJSON(tweetsDataList));
				writer.writeDataToFile(json, language, DateUtility.getCurrentTimeStamp());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static List<Tweet> getTwitterData(Twitter twitterInstance, String language, List<String> criteria, String since) {
		TweetsCrawler crawler = new TweetsCrawler();
		List<Tweet> tweetsDataList = new ArrayList<Tweet>();
		for(String criterion : criteria) {
			tweetsDataList.addAll(crawler.getTwitterData(twitterInstance, language, criterion, since));
		}
		return tweetsDataList;
	}
	
	private static Twitter getTwitterInstance() {
		return Twitter4jFactory.getInstance();
	}
	
	private static List<String> getQueryLanguage(Map<String, String> queryParamsMap) {
		String[] lang = queryParamsMap.get("LANGUAGE").split(",");
		List<String> languages = new ArrayList<String>();
		for(int i=0; i<lang.length; i++) {
			languages.add(lang[i]);
		}
		return languages;
	}
	
	private static List<String> getQuerySearchCriteria(Map<String, String> queryParamsMap) {
		String[] queryCriteria = queryParamsMap.get("QUERY").split(",");
		List<String> criteria = new ArrayList<String>();
		for(int i=0; i<queryCriteria.length; i++) {
			criteria.add(queryCriteria[i]);
		}
		return criteria;
	}
}