import java.util.Scanner;
public class yumm {
	public static void main (String[] args) {
		System.out.println("Welcome to FracCalc");
		System.out.println("Type expressions with fractions, and I will evaluate them.");		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String in = scan.nextLine();
		@SuppressWarnings("unused")
		boolean neg = false;
		while (!in.equals("quit")) {
			String string1 = in.substring(0, in.indexOf(" "));
			String string2 = in.substring(in.lastIndexOf(" ") + 1);
			String operator = in.substring(in.indexOf(" ") + 1, in.lastIndexOf(" "));
			String simplified1 = simplify(string1);
			String simplified2 = simplify(string2);
			String num1 = simplified1.substring(0, simplified1.indexOf("/"));
			String num2 = simplified2.substring(0, simplified2.indexOf("/"));
			String den1 = simplified1.substring(simplified1.lastIndexOf("/")+1);
			String den2 = simplified2.substring(simplified2.lastIndexOf("/")+1);
		    if (operator.equals("+")) {
		    	int densum = Integer.parseInt(den1) * Integer.parseInt(den2);
		    	int numfix1 = Integer.parseInt(num1) * Integer.parseInt(den2);
				int numfix2 = Integer.parseInt(num2) * Integer.parseInt(den1);
				int numsum = numfix1 + numfix2;
				int gcd = gcd(numsum, densum);
			    numsum = numsum / gcd;
			    densum = densum / gcd;
			    mixedfraction(numsum, densum);
		    }
			if (operator.equals("-")) {
				int densum = Integer.parseInt(den1) * Integer.parseInt(den2);
				int numfix1 = Integer.parseInt(num1) * Integer.parseInt(den2);
				int numfix2 = Integer.parseInt(num2) * Integer.parseInt(den1);
				int numsum = numfix1 - numfix2;
				int gcd = gcd(numsum, densum);
			    numsum = numsum / gcd;
			    densum = densum / gcd;
			    if(densum < 0){
			    	densum = Math.abs(densum);
			    	numsum = -numsum;
			    }
			    mixedfraction(numsum, densum);
			}
			if (operator.equals("*")) {
				int densum = Integer.parseInt(den1) * Integer.parseInt(den2);
				int numsum = Integer.parseInt(num1) * Integer.parseInt(num2);
				int gcd = gcd(numsum, densum);
			    numsum = numsum / gcd;
			    densum = densum / gcd;
			    mixedfraction(numsum, densum);
			}
			if (operator.equals("/")) {
				int densum = Integer.parseInt(den1) * Integer.parseInt(num2);
				int numsum = Integer.parseInt(num1) * Integer.parseInt(den2);
				int gcd = gcd(numsum, densum);
			    numsum = numsum / gcd;
			    densum = densum / gcd;
			    mixedfraction(numsum, densum);
			}
			in = scan.nextLine();
		}
		System.out.println("Thanks for running FracCalc!");
	}
	
	
	public static int gcd(int a, int b){
		while(b != 0){
			int t = b;
			b = a % b;
			a = t;
		}
		return a;
	}
	
	public static void mixedfraction(int numsum, int densum) {
		boolean check = false;
		if (numsum < 0){ 
			check = true;
			numsum = Math.abs(numsum);
					
		}
		if (numsum > densum && numsum % densum == 0){
			if (check){
				System.out.println("-" + numsum / densum);
			}
			else{
				System.out.println(numsum / densum);
			}
		}
		else if (densum == 1){
			if (check){
				System.out.println("-" + numsum);
			}
			else{
				System.out.println(numsum);
			}
		}
		else if (numsum < densum && numsum > 0){	
			if(check){
				System.out.println("-" + numsum + "/" + densum);
			}
			else{
				System.out.println(numsum + "/" + densum);
			}
		}
		else if (numsum < densum && numsum < 0){
			if(check){
				System.out.println("-" + numsum + "/" + densum);
			}
			else{
				System.out.println(numsum + "/" + densum);
			}
		}
		else if (numsum < 0){
				if(check){
					System.out.println("-" + -numsum + "/" + -densum);
				}
			else{
				System.out.println(-numsum + "/" + -densum);
			}
		}
		else if (numsum == densum){
			if(check){
				System.out.println("-1");
			}
			else{
				System.out.println("1");
			}
		}
		else if (numsum > densum){
			int whole = numsum / densum;
			int numfin = numsum % densum;
			if(check){
				System.out.println("-" + whole + "_" + numfin + "/" + densum);
			}
			else{
				System.out.println(whole + "_" + numfin + "/" + densum);
			}
		}
	}
	
	public static String simplify(String s){
		if (s.contains("_") && s.contains("-")) {
			int underscore = s.indexOf("_");
			int slash = s.indexOf("/");
			String wholeStr = s.substring(1, underscore);
			String numeratorStr = s.substring(underscore + 1,slash);
			String denominatorStr = s.substring(slash + 1);
			int whole = Integer.valueOf(wholeStr);
			int numerator = Integer.valueOf(numeratorStr);
			int denominator = Integer.valueOf(denominatorStr);
			int newNumerator = whole * denominator + numerator;
			String negfraction = "-" + newNumerator + "/" + denominator;
			return negfraction;
		} 
		else if (s.contains("_")) {
			int underscore = s.indexOf("_");
			int slash = s.indexOf("/");
			String wholeStr = s.substring(0, underscore);
			String numeratorStr = s.substring(underscore + 1,slash);
			String denominatorStr = s.substring(slash + 1);
			int whole = Integer.valueOf(wholeStr);
			int numerator = Integer.valueOf(numeratorStr);
			int denominator = Integer.valueOf(denominatorStr);
			int newNumerator = whole * denominator + numerator;
			String fraction =  newNumerator + "/" + denominator;
			return fraction;
		}
		else if (s.contains("/")) {
			return s;
		} 
		else {
			String newWhole = s + "/1";
			return newWhole;
		}
	}
}