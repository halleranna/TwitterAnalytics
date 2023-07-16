/**
 *
 * @author Anna Haller
 */

import edu.iue.HashtagCounter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Arrays;

public class TwitterAnalytics {

    public static String[] tweetsFromFile(String fileName) {
        try {
           String fileContents = new String(Files.readAllBytes(Paths.get(fileName)));
           return fileContents.split("\n");
        } 
        catch(IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }
  
    public static String normalizeTweet(String tweet) {
        String normalizedTweet = "";
        
        for (int i = 0; i < tweet.length(); i++) {
            char currChar = tweet.charAt(i);
            if(currChar == '#') {
                normalizedTweet = normalizedTweet + currChar;
            } else if(currChar == ' ') {
                normalizedTweet = normalizedTweet + currChar;
            } else if(currChar >= 'A' && currChar <= 'z') {
                normalizedTweet = normalizedTweet + currChar;
            }
        }
        
        String normalizedTweetUpper = normalizedTweet.toUpperCase();
        
        return normalizedTweetUpper;
    }
    
    public static int[] buildHistogram(String[] tweets){
        int[] histogram = new int[10];
        
        for(String tweet : tweets) {
            HashtagCounter currentTweet = new HashtagCounter(tweet);
            int count = currentTweet.countHashtags();
            histogram[count]++;
        }
        
        return histogram;
    }
  
  
  
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String corpusFileName;
        String[] tweets;
        
        System.out.print("Please enter the name of a text file containing 1 tweet per line: ");
        corpusFileName = in.nextLine();
        tweets = tweetsFromFile(corpusFileName);
        
        if (tweets != null) { 
            for (int i = 0; i < tweets.length; i++) {
                System.out.printf("The original tweet is: %s\n", tweets[i]);
                tweets[i] = normalizeTweet(tweets[i]);
                System.out.printf("The normalized tweet is: %s\n", tweets[i]);
            }
            
            System.out.println("Time to print the histogram:");
            
            for(int i = 0; i < buildHistogram(tweets).length; i++){
                System.out.printf("There are %d tweet(s) containing %d hashtags. \n", buildHistogram(tweets)[i], i);
            }
        }
        
        
    }
    
}
