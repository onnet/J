
package ru.iam;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Query {

    @SerializedName("search")
    private List<Search> mSearch;
    @SerializedName("searchinfo")
    private Searchinfo mSearchinfo;

    public List<Search> getSearch() {
        return mSearch;
    }

    public void setSearch(List<Search> search) {
        mSearch = search;
    }

    public Searchinfo getSearchinfo() {
        return mSearchinfo;
    }

    public void setSearchinfo(Searchinfo searchinfo) {
        mSearchinfo = searchinfo;
    }

}
