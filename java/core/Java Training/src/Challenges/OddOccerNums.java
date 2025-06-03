package challanges;

public class OddOccerNums {
	
	public static String getBinaryVal(int xorVal) {
		return (Integer.toBinaryString(xorVal));
	}
	
	public static int getSetBit(String bin) {
		int n=bin.length();
//		System.out.println("len : "+n);
		int setBitIdx = 0 ;
		for(int i=(bin.length()-1);i>=0;i--) {
			if(bin.charAt(i)=='1') {
				setBitIdx=  i;
				break;
			}
		}
		return n-setBitIdx;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ar= {2,3,2,3,3,5,5,5};
		int xorVal=0;
		for(int i=0;i<ar.length;i++) {
			xorVal^=ar[i];
		}
		System.out.println("After xor on all : "+xorVal);
		String bin=getBinaryVal(xorVal);
		System.out.println("xor value is : "+bin);
		int setBitIdx=getSetBit(bin);
		System.out.println("The "+xorVal+" first right most set bit at : "+ setBitIdx);
		int var1=0,var2=0;
		for(int ele:ar) {
			String eleBinary=getBinaryVal(ele);
			int eleSetBit=getSetBit(eleBinary);
			System.out.println("element = "+ele+" | binaryForm = "+eleBinary+" | right most set bit = "+eleSetBit);
			if(eleSetBit==setBitIdx) {
				var1^=ele;
			}
			else {
				var2^=ele;
			}
		}
		System.out.println("var1 = "+var1+" and var2 = "+var2);
	}

}
