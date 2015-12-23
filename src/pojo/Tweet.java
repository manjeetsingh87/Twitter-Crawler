/**
 * 
 */
package pojo;

import java.util.List;

/**
 * @author MANJEET
 *
 */
public class Tweet /*implements Serializable*/ {
	
	//private static final long serialVersionUID = 1L;
	private long id;
	private String text_en;
	private String text_ru;
	private String text_de;
	private String lang;
	private List<String> tweet_urls;
	private List<String> tweet_hashtags;
	private String created_at;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the text_en
	 */
	public String getText_en() {
		return text_en;
	}
	/**
	 * @param text_en the text_en to set
	 */
	public void setText_en(String text_en) {
		this.text_en = text_en;
	}
	
	/**
	 * @return the text_ru
	 */
	public String getText_ru() {
		return text_ru;
	}
	/**
	 * @param text_ru the text_ru to set
	 */
	public void setText_ru(String text_ru) {
		this.text_ru = text_ru;
	}
	
	/**
	 * @return the text_de
	 */
	public String getText_de() {
		return text_de;
	}
	/**
	 * @param text_de the text_de to set
	 */
	public void setText_de(String text_de) {
		this.text_de = text_de;
	}
	
	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}
	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}
	
	/**
	 * @return the tweet_urls
	 */
	public List<String> getTweet_urls() {
		return tweet_urls;
	}
	/**
	 * @param tweet_urls the tweet_urls to set
	 */
	public void setTweet_urls(List<String> tweet_urls) {
		this.tweet_urls = tweet_urls;
	}
	
	/**
	 * @return the tweet_hashtags
	 */
	public List<String> getTweet_hashtags() {
		return tweet_hashtags;
	}
	/**
	 * @param tweet_hashtags the tweet_hashtags to set
	 */
	public void setTweet_hashtags(List<String> tweet_hashtags) {
		this.tweet_hashtags = tweet_hashtags;
	}
	
	/**
	 * @return the created_at
	 */
	public String getCreated_at() {
		return created_at;
	}
	/**
	 * @param created_at the created_at to set
	 */
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
}