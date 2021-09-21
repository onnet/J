
package ru.iam;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Search {

    @SerializedName("ns")
    private Long mNs;
    @SerializedName("pageid")
    private Long mPageid;
    @SerializedName("size")
    private Long mSize;
    @SerializedName("snippet")
    private String mSnippet;
    @SerializedName("timestamp")
    private String mTimestamp;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("wordcount")
    private Long mWordcount;

    public Long getNs() {
        return mNs;
    }

    public void setNs(Long ns) {
        mNs = ns;
    }

    public Long getPageid() {
        return mPageid;
    }

    public void setPageid(Long pageid) {
        mPageid = pageid;
    }

    public Long getSize() {
        return mSize;
    }

    public void setSize(Long size) {
        mSize = size;
    }

    public String getSnippet() {
        return mSnippet;
    }

    public void setSnippet(String snippet) {
        mSnippet = snippet;
    }

    public String getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(String timestamp) {
        mTimestamp = timestamp;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Long getWordcount() {
        return mWordcount;
    }

    public void setWordcount(Long wordcount) {
        mWordcount = wordcount;
    }

}
