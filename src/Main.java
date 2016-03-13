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
//	private static String[] province = {"����","���","�Ϻ�","����","�ӱ�","����","����","����","������","����","����","ɽ��","�½�","����","�㽭","����","����","����","����","ɽ��","���ɹ�","����","����","����","����","�㶫","�ຣ","����","�Ĵ�","����","����","̨��","���","����"};
//	private static String[] sex = {"��","Ů"};
//	private static String[] job = {"����","���ز�","����","����","IT","��ѯ","������Ϸ","ҽҩ","����","��ý"};
//	private static String[] earn = {"1000����","1000��3000","3000��8000","8000��15000","15000��30000","30000��60000","60000��120000","120000����"};
//	private static String[] blood = {"O","A","B","AB","����"};
//	private static String[] age = {"10������","10��20��","20��30��","30��40��","40��50��","����"};
//	
////	private static Map<String, String> map = new LinkedMap();
//	
//	private static int total = 528245;
//	
//	public static void main(String[] args) {
//		//and  ydb_sex='��' and ydb_province='����' and ydb_age='10������' and ydb_blood='O' and ydb_zhiye='���ز�' and ydb_earn='1000��3000'  limit 0,50
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
//									if("��-����-20��30��-A-������Ϸ-3000��8000".equalsIgnoreCase(fileName)){
//										flag = true;
//									}
//									if(flag){
////										map.put(fileName, url);
//										
//										fixedThreadPool.execute(new Runnable() {
//											public void run() {
//												try {
////													Map<String, String> ������Ҫִ�е����е�sql
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
//									System.err.println("��SQL��ȡ����"+url);
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
//						System.out.println("������ȡ��"+ydn.getCount()+"�����ݣ� һ����ȡ�� "+total+" ���û�����");
//					}else{
//						System.out.println("��SQL������10000����"+url);
//					}
//				}
//			}
//		} catch (Exception e) {
//		}
//	}
//}
