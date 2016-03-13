//
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.OutputStreamWriter;
//import java.util.Map;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import net.sf.json.JSONObject;
//
//import org.apache.commons.collections.map.LinkedMap;
//
//import cc.baozheng.kit.HttpURLConnectionKit;
//
//public class Main {
//	
//	private static final String URL = "http://101.200.130.48:8080/sql?sql="
//			+ "select+phonenum,usernick,ydb_sex,ydb_province,ydb_grade,ydb_age,ydb_blood,ydb_earn,ydb_zhiye,ydb_prefer,ydb_consume,content+"
//			+ "from++ydb_example_ads+where+ydbpartion=%2720151111%27";
//	
//	
//	private static String[] province = {"北京","天津","上海","重庆","河北","河南","云南","辽宁","黑龙江","湖南","安徽","山东","新疆","江苏","浙江","江西","湖北","广西","甘肃","山西","内蒙古","陕西","吉林","福建","贵州","广东","青海","西藏","四川","宁夏","海南","台湾","香港","澳门"};
//	private static String[] sex = {"男","女"};
//	private static String[] job = {"销售","房地产","金融","物流","IT","咨询","网络游戏","医药","教育","传媒"};
//	private static String[] earn = {"1000以下","1000至3000","3000至8000","8000至15000","15000至30000","30000至60000","60000至120000","120000以上"};
//	private static String[] blood = {"O","A","B","AB","其他"};
//	private static String[] age = {"10岁以下","10到20岁","20到30岁","30到40岁","40到50岁","其他"};
//	
////	private static Map<String, String> map = new LinkedMap();
//	
//	private static int total = 528245;
//	
//	public static void main(String[] args) {
//		//and  ydb_sex='男' and ydb_province='北京' and ydb_age='10岁以下' and ydb_blood='O' and ydb_zhiye='房地产' and ydb_earn='1000至3000'  limit 0,50
//		xc();
//	}
//	private static void xc() {
//		
//		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
//		
//		
//		boolean flag = false;
//		String buf = "";
//		for(String p:province){
//			for(String s:sex){
//				for(String j:job){
//					for(String e:earn){
//						for(String n:blood){
//							for(String a:age){
//								buf += "and+ydb_sex=%27"+s+"%27";
//								buf += "and+ydb_province=%27"+p+"%27";
//								buf += "and+ydb_age=%27"+a+"%27";
//								buf += "and+ydb_blood=%27"+n+"%27";
//								buf += "and+ydb_zhiye=%27"+j+"%27";
//								buf += "and+ydb_earn=%27"+e+"%27";
//								
//								final String fileName = s+"-"+p+"-"+a+"-"+n+"-"+j+"-"+e;
//								
//								final String url = URL+buf+"+++limit+0,10000";
//								buf = "";
//								try {
//									if("男-北京-20到30岁-A-网络游戏-3000至8000".equalsIgnoreCase(fileName)){
//										flag = true;
//									}
//									if(flag){
////										map.put(fileName, url);
//										
//										fixedThreadPool.execute(new Runnable() {
//											public void run() {
//												try {
////													Map<String, String> 本次需要执行的所有的sql
//													spider(fileName, url);
//												} catch (Exception e3) {
//													e3.printStackTrace();
//												}
//											}
//										});
//										
////										spider(fileName, url);
//										/*Thread t1 = new Thread(new Runnable() {
//											public void run() {
//												spider(fileName, url);
//											}
//										});
//										t1.start();*/
//									}
//								} catch (Exception e2) {
//									System.err.println("此SQL爬取出错："+url);
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		
//	}
//
//	private static void spider(String fileName, String url) {
//		try {
//			
//			String result = HttpURLConnectionKit.get(url);
//			JSONObject jsonObject = JSONObject.fromObject(result);
//			Ydn ydn = (Ydn)JSONObject.toBean(jsonObject, Ydn.class);
//			if(ydn!=null){
//				if(ydn.getCount()>0){
//					if(ydn.getCount()<=10000){
//						total+=ydn.getCount();
//						try {
//							File file = new File("C:/ydn/"+fileName+".txt");
//							OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
//							out.write(result);
//							out.close();
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//						System.out.println("本次爬取："+ydn.getCount()+"条数据， 一共爬取： "+total+" 条用户数据");
//					}else{
//						System.out.println("此SQL超过了10000条："+url);
//					}
//				}
//			}
//		} catch (Exception e) {
//		}
//	}
//}
