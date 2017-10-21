package constants;

public class connectionConstants {
	
	public static final int distParam = 3;
	public static final String host = "aws-project-load-balancer-979509742.us-east-2.elb.amazonaws.com";
	//public static final String host ="127.0.0.1";
	public static final int port = 7777;
	
	public static final int cores = 1;
	public static final int timeout = 10; // in sec
	//public static final int iterations = (int) 6.25e6; // this is a good load
	public static final int iterations = (int) 6e6; // this is a good load
	
	public static final int sleepMulitplier = 333;
	
	public static final int sleepMulitplierStart = 800;
	public static final int sleepMulitplierEnd = 100;
	public static final int sleepMulitplierShiftPeriod = 960000; // in millis
	public static long startTime = System.currentTimeMillis();
	
	public static int update_interval = 5000; // in millis
	public static int limit = 10;
	
	
}
