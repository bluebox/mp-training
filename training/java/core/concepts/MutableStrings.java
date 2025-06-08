package concepts;
public class MutableStrings {
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		System.out.println(sb.capacity());
		sb = new StringBuffer("Sourya");
		System.out.println(sb.capacity());
		System.out.println(sb.length());
		sb.append(" Chandra");
		System.out.println(sb);
		sb.deleteCharAt(12);
		sb.deleteCharAt(12);
		System.out.println(sb);
		sb.insert(12,"u");
		System.out.println(sb);
		sb.delete(6, 13);
		System.out.println(sb);
		sb.setLength(22);
		System.out.println(sb.length());
		sb.setCharAt(4, 'i');
		System.out.println(sb);
		sb.reverse();
		System.out.println(sb);
		sb.reverse();
		String strb=sb.toString();
		System.out.println(strb);
		
		StringBuilder sbd = new StringBuilder();
		System.out.println(sbd.capacity());
		sbd = new StringBuilder("Mourya");
		System.out.println(sbd.capacity());
		System.out.println(sbd.length());
		sbd.append(" Chandra");
		System.out.println(sbd);
		sbd.deleteCharAt(12);
		sbd.deleteCharAt(12);
		System.out.println(sbd);
		sbd.insert(12,"u");
		System.out.println(sbd);
		sbd.delete(6, 13);
		System.out.println(sbd);
		sbd.setLength(22);
		System.out.println(sbd.length());
		sbd.setCharAt(4, 'i');
		System.out.println(sbd);
		sbd.reverse();
		System.out.println(sbd);
		sbd.reverse();
		String strbd=sbd.toString();
		System.out.println(strbd);
		}
}
