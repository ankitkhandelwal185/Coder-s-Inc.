package app.the.iway.codersinc.recommendation;

/**
 * Created by IWAY on 28-11-2016.
 */
public class allProblemModel {
    private String title, tag;
    String contestid;
    String index;
    String tags;
    String url;
    String name;

    public allProblemModel() {
    }

    public allProblemModel(String title, String tag) {
        this.title = title;
        this.tag = tag;

    }
    public allProblemModel(String name, String contestid, String index, String tags, String url){
        this.name = name;
        this.contestid=contestid;
        this.index=index;
        this.tags=tags;
        this.url=url;

        //  this.Begin = Begin;
        //this.Enddate=Enddate;
        //this.End = End;
        //this.Link=Link;

    }


    public String getTitle() {
        return title;
    }
    public String getUrl(){
        return url;
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
}