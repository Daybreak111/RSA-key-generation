package RSA_Key;

import java.math.*; 
import java.util.Random; 
import java.util.Scanner; 
import java.util.logging.Logger;  

public class Main {
	
	static String strClassName = Main.class.getName();  
    static Logger logger = Logger.getLogger(strClassName);  

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		//Initialization
		Scanner in = new Scanner(System.in); 
		System.out.println("Please set the bit of random prime: ");
		int length = in.nextInt();
		if(length<3) {
			throw new Exception("Length must be bigger than 3");
		}
		logger.info("Length of prime: " + length);
		
		//Generate prime p&q
		Random random = new Random(); 
		BigInteger p = RSA_Common.Generate_Prime(length,random);
		BigInteger q = RSA_Common.Generate_Prime(length,random);
		while(p.equals(q)) {
			logger.info("generate again");
			q = RSA_Common.Generate_Prime(length,random);
		}
		logger.info("Value of p: " + p);
		logger.info("Value of q: " + q);
		
		//calculate n
		BigInteger n = p.multiply(q);
		
		//calculate phi
		BigInteger phi = (p.subtract(new BigInteger("1"))).multiply(q.subtract(new BigInteger("1")));
		logger.info("Value of phi: " + phi);
		
		//generate e
		BigInteger e = RSA_Common.Generate_Prime(length,random);
		logger.info("Value of e: " + e);
		
		//generate d
		BigInteger d;
		
		//calculate d
		BigInteger[] result = RSA_Common.ex_gcd(phi, e);
		d = result[2];
		logger.info("Value of d: " + d);
		
		//verify gcd of e and phi is 1
		BigInteger gcd_e_phi = RSA_Common.gcd(phi, e);
		logger.info("Value of gcd_e_phi: " + gcd_e_phi);
		
		//verify RSA key
		boolean verification = RSA_Common.Verify_Key(n, e, d);
		logger.info("Verify result: " + verification);
		
		System.out.printf("Value of p: %d,\nValue of q: %d\nValue of n: %d\nValue of e: %d\nValue of d: %d\n",p,q,n,e,d);
	}
}
