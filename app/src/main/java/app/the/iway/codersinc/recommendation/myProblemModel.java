package app.the.iway.codersinc.recommendation;

/**
 * Created by IWAY on 28-11-2016.
 */
public class myProblemModel {
    private String title, tag,code;

    public myProblemModel() {
    }

    public myProblemModel(String title, String tag,String code) {
        this.title = title;
        this.tag = tag;
        this.code = code;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
