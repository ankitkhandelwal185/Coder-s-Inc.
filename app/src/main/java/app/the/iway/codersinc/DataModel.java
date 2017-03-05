package app.the.iway.codersinc;

/**
 * Created by IWAY on 19-09-2016.
 */
public class DataModel {

    String name;
    String starttime;
    String endtime;
    String sitename;
    String startdate;
    String enddate;
    String link;
    String type;

    String contestid;
    String index;
    String tags;
    String url;

    public DataModel(String sitename , String name, String startdate, String starttime,String enddate, String endtime,String link,String type)//, int id_)//, int image)
    {
        this.sitename=sitename;
        this.name = name;
        this.starttime = starttime;
        this.endtime = endtime;
        this.startdate=startdate;;
        this.enddate=enddate;
        this.link=link;
        this.type=type;
    }
    public DataModel(String name, String contestid ,String index, String tags, String url ){
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

    public String getName() {
        return name;
    }
    public String getStarttime() { return starttime; }
    public String getEndtime(){ return  endtime;}
    public String getStartdate(){ return startdate; }
    public String getEnddate() { return enddate; }
    public String getSitename() { return  sitename; }
    public String getLink() { return link; }
    public String getType() { return type; }
}