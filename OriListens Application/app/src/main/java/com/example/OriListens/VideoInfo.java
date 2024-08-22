package com.example.OriListens;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.example.OriListens.R;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class VideoInfo {
    String videoUrl ;//= "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/BVJkf8IuRjE?si=48fGgIZyGMZ81Dyo&amp;start=150\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
    Matcher m;
    public VideoInfo(String videoUrl) {
        this.videoUrl = videoUrl;
        Log.d("Tag Nishaaaaaaaaa", "flag2: "+videoUrl);
        String videoIdPattern = "embed/(.*?)['\'?&]";
        Pattern r = Pattern.compile(videoIdPattern);
        m= r.matcher(videoUrl);
    }
    String thumbnailUrl;
    String videoId = null;
    String url;
    private static final String TAG = "YouTubeTitleRetriever";

    public String getThumbnailUrl() {
        Log.d("Tag Nishaaaaaaaaa", "flag4"+thumbnailUrl);
        return thumbnailUrl;
    }

    public String getUrl() {
        return url;
    }

    // Build the thumbnail URL
    public void createThumbnailUrl(){
        Log.d("Tag Nishaaaaaaaaa", "flag3");
          if (m.find()) {
             videoId = m.group(1);
          }
        if(videoId!=null)this.thumbnailUrl = "https://img.youtube.com/vi/" + videoId + "/hqdefault.jpg";
        Log.d("Tag Nishaaaaaaaaa", "the th url is "+thumbnailUrl);
    }
    public void createUrl() {
        Log.d("Tag Nishaaaaaaaaa", "flag3");
        if (m.find()) {
            videoId = m.group(1);
        }
        if (videoId != null) this.url = "https://www.youtube.com/watch?v=" + videoId;
        Log.d("Tag Nishaaaaaaaaa", "the th url is " + thumbnailUrl);

    }

    }

