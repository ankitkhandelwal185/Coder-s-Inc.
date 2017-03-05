package app.the.iway.codersinc.Contests;

import android.util.Log;

/**
 * Created by Shashank on 25-Sep-16.
 */


 public class Contact {

 //private variables
 String Name;
 String username;
 String password;
 String Site;
 String Begindate;
 String Enddate;
 String Begin;
 String End;
 String Type;
 String Link;
 String Sitename;
 String res;
 String Handle;
 String name;
 String contestid;
 String index;
 String tags;
 String url;
 byte[] Image;
 String Username;
 String Accuracy;
    String Myp;

 // Empty constructor
 public Contact(){

 }
 // constructor
 public Contact(String Name, String Type ,String Site, String Begindate, String Begin,String Enddate, String End,String Link ){
 this.Name = Name;
 this.Type=Type;
 this.Site=Site;
 this.Begindate=Begindate;

 this.Begin = Begin;
 this.Enddate=Enddate;
 this.End = End;
 this.Link=Link;

 }
 public Contact (String Username,byte[] Image,String res,String Sitename){

  this.Username=Username;
  this.Image=Image;
  this.res=res;
  this.Sitename=Sitename;


 }

 public Contact(String name, String contestid ,String index, String tags, String url ){
  this.name = name;
  this.contestid=contestid;
  this.index=index;
  this.tags=tags;
  this.url=url;

  this.Begin = Begin;
  this.Enddate=Enddate;
  this.End = End;
  this.Link=Link;

 }

 // constructor
 public Contact(String Sitename,String res){
 this.Sitename=Sitename;
 this.res=res;
 this.Begin=Begin;
 this.End=End;
 }




 public Contact(String Sitename, String res, String Handle){
  this.Sitename=Sitename;
  this.res=res;
  this.Handle=Handle;
  //Log.e("myprob","wala");
  this.End=End;
 }
 // getting ID
 public String getName(){
 return this.Name;
 }

 // setting id
 public void setName(String Name){
 this.Name = Name;
 }

 // getting name
 public String getBegin(){
 return this.Begin;
 }

 // setting name0
 public void setBegin(String Begin){
 this.Begin = Begin;
 }

 // getting phone number
 public String getEnd(){
 return this.End;
 }

 // setting phone number
 public void setEnd(String End){
 this.End = End;
 }
 public String getType(){
 return this.Type;
 }
 public String setType(String Type){
 this.Type =Type;
 return Type;
 }
 public String getSite(){
 return this.Site;
 }
 public void setSite(String Site){
 this.Site=Site;
 }
 public String getBegindate(){
 return this.Begindate;
 }
 public void setBegindate(String Begindate){
 this.Begindate=Begindate;
 }
 public  String getEnddate(){
 return this.Enddate;

 }
 public void setEnddate(String Enddate){
 this.Enddate=Enddate;
 }
 public  String getLink(){
 return  this.Link;
 }
 public  void setLink(String Link){
 this.Link=Link;
 }
 public  void setSitename(String Sitename){
 this.Sitename=Sitename;
 }
 public String getSitename(){
 return this.Sitename;
 }
 public  void  setRes(String res){
 this.res=res;
 }
 public  String getRes(){
 return  this.res;
 }
 public  void setHandle(String Handle){ this.Handle=Handle;}
 public String  getHandle(){
  return this.Handle;
 }
 public  void setUrl(String url){this.url=url;}
 public  String getUrl(){
  return this.url;
 }
 public void setContestid(String contestid){this.contestid=contestid;}
 public String getContestid(){
  return this.contestid;
 }
 public void setIndex(String index)
 {
  this.index=index;
 }
 public String getIndex(){
  return this.index;
 }
 public void setTags(String tags){
  this.tags=tags;
 }
 public String getTags(){
  return  this.tags;
 }
 public void setname(String name){ this.name=name;}
 public String getname(){ return  this.name;}
 public void setImage(byte[] Image){ this.Image=Image;}
 public  byte[] getImage(){return this.Image;}
 public  void setUsername(String Username){ this.Username=Username;}
 public  String getUsername(){return  this.Username;}
 public void setAccuracy(String Accuracy){this.Accuracy=Accuracy;}
 public String getAccuracy(){return this.Accuracy;}
    public  void setMyp(String Myp){this.Myp=Myp;}
    public String getMyp(){return  this.Myp;}


 }
