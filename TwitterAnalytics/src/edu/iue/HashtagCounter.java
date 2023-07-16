
package edu.iue;

/**
 *
 * @author Anna Haller
 */


public class HashtagCounter {
    private String tweetText = "";
    
    public HashtagCounter(String tweetText) {
        this.tweetText = tweetText;
    }
    
    public int countNumberSigns() {
        int count = 0;
        for (int i = 0; i < tweetText.length(); i++) {
            if (tweetText.charAt(i) == '#') {
                count++;
            }
        }
        return count;
    }
    
    public int countHashtags() {
        int count = 0;
        for (int i = 0; i < tweetText.length() - 1; i++) {
            if (tweetText.charAt(i) == '#') { // is a number sign
                if (tweetText.charAt(i+1) != ' ') { // nonwhitespace after
                    if (i == 0) { // first character of String
                        count++;
                    } else {
                        if (tweetText.charAt(i-1) == ' ') { // follows a space
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
