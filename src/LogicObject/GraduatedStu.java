package LogicObject;

public class GraduatedStu extends Borrower {
	GraduatedStu(String a,String b,String c,String d,boolean e,String f,String major,String director){
		super(a,b,c,d,e,f);
		this.major=major;
		this.director=director;
	}
	private String major;	//����
	private String director;//��ʦ
	public String getMajor()
	{
		return major;
	}
	public void setMajor(String Major)
	{
		major=Major;
	}
	public String getDirector()
	{
		return director;
	}
	public void setDirector(String Director)
	{
		director=Director;
	}
	public String GetAllInfo()
	{
		String info=super.GetAllInfo()+
					"רҵ:"+major+"\n"+
					"��ʦ:"+director;
		return info;
	}
}