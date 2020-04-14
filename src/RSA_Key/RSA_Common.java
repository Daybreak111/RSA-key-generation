package RSA_Key;

import java.math.*; 
import java.util.Random;
import java.util.logging.Logger; 

public class RSA_Common {
	
	static String strClassName = Main.class.getName();  
    static Logger logger = Logger.getLogger(strClassName);  

	public static BigInteger Generate_Prime(int length, Random random){
		BigInteger biginteger = BigInteger.probablePrime(length, random); 
		return biginteger;
	}
	
	public static BigInteger[] ex_gcd(BigInteger a,BigInteger b){  
        BigInteger ans;  
        BigInteger[] result=new BigInteger[3];  
        if(b.equals(new BigInteger("0")))  
        {  
            result[0]=a;  
            result[1]=new BigInteger("1");  
            result[2]=new BigInteger("0"); 
            return result;  
        } 
        BigInteger [] temp=ex_gcd(b,a.mod(b));  
        ans = temp[0];  
        result[0]=ans;  
        result[1]=temp[2];  
        result[2]=temp[1].subtract(temp[2].multiply(a.divide(b)));
        return result;  
    }
	
	public static BigInteger gcd(BigInteger a,BigInteger b){  
		if(b.equals(new BigInteger("0"))) {
			return a;
		}
		return gcd(b,a.mod(b));
    }
	
	public static boolean Verify_Key(BigInteger n, BigInteger e, BigInteger d){
		BigInteger m = new BigInteger("123456789");
		BigInteger r = m.modPow(e, n);
		BigInteger m2 = r.modPow(d, n);
		logger.info("Value of m: " + m);
		logger.info("Value of m2: " + m2);
		if(m.equals(m2)) {
			return true;
		}
		return false;
	}
}
