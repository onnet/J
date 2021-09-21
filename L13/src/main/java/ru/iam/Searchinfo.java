
package ru.iam;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Searchinfo {

    @SerializedName("totalhits")
    private Long mTotalhits;

    public Long getTotalhits() {
        return mTotalhits;
    }

    public void setTotalhits(Long totalhits) {
        mTotalhits = totalhits;
    }

}
