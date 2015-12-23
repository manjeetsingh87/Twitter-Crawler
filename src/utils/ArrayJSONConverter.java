package utils;

import java.util.List;
import pojo.Tweet;
import twitter4j.JSONArray;

public class ArrayJSONConverter {
	
	public static JSONArray getJSON(List<Tweet> tweetsDataList) {
		return new JSONArray(tweetsDataList);
	}
}