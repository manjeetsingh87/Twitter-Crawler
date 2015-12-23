/**
 * 
 */
package crawl;

import java.util.ArrayList;
import java.util.List;

import pojo.Tweet;
import twitter4j.HashtagEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.URLEntity;
import utils.DateUtility;

/**
 * @author MANJEET
 *
 */
public class TweetsCrawler {

	/**
	 * @param args
	 */
	
	public List<Tweet> getTwitterData(Twitter twitterInstance, String lang, String queryName, String since) {
		List<Tweet> tweetsDataList = new ArrayList<Tweet>();
		try {
			List<Status> tweetsList = getTweets(twitterInstance, lang, queryName, since);
 			for(Status status : tweetsList) {
 				tweetsDataList.add(setTweetDataToPojo(status));
			}
 			//TweetsList list = new TweetsList();
 			//list.setTweetsList(tweetsDataList);
		} catch (TwitterException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tweetsDataList;
	}
	
	private List<Status> getTweets(Twitter twitterInstance, String lang, String queryName, String since) throws TwitterException{
		List<Status> queryRslt = new ArrayList<Status>();
		try {
			Query query = new Query();
			query.setLang(lang);
			query.setQuery(queryName);
			if(null != since || since != "")
				query.setSince(since);
			//query.setUntil("2015-09-17");
			query.setCount(500);
			QueryResult rslt = twitterInstance.search(query);
			queryRslt = rslt.getTweets();
		} catch (Exception e) {
			throw new TwitterException("Encountered an exception while fetching tweets for query: "+e);
		}
		return queryRslt;
	}
	
	private Tweet setTweetDataToPojo(Status tweetData) {
		Tweet tweetObj = new Tweet();
		tweetObj.setId(tweetData.getId());
		tweetObj.setLang(tweetData.getLang());
		setText(tweetData, tweetData.getText(), tweetData.getLang(), tweetObj);
		URLEntity[] entities = tweetData.getURLEntities();
		if(null != entities) {
			List<String> entitiesList = new ArrayList<String>();
			for(int i=0; i<entities.length; i++) {
				entitiesList.add(entities[i].getExpandedURL());
			}
			tweetObj.setTweet_urls(entitiesList);
		} else
			tweetObj.setTweet_urls(null);
		
		HashtagEntity[] hashTags = tweetData.getHashtagEntities();
		List<String> hashTagList = new ArrayList<String>();
		for(HashtagEntity entity : hashTags) {
			hashTagList.add(entity.getText());
		}
		tweetObj.setTweet_hashtags(hashTagList);
		tweetObj.setCreated_at(DateUtility.formatToSolrDate(tweetData.getCreatedAt()));
		return tweetObj;
	}
	
	private void setText(Status data, String text, String lang, Tweet obj) {
		if(lang.equalsIgnoreCase("en"))
			obj.setText_en(data.getText());
		else if(lang.equalsIgnoreCase("de"))
			obj.setText_de(data.getText());
		else if(lang.equalsIgnoreCase("ru"))
			obj.setText_ru(data.getText());
	}
}