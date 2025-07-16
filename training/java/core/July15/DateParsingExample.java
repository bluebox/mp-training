package July15;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class DateParsingExample {
	public static void main(String[] args) {
		String dateStr = "20250231"; // 31st Feb 2025

		DateTimeFormatter yyyyFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		DateTimeFormatter uuuuFormatter = DateTimeFormatter.ofPattern("uuuuMMdd");

		System.out.println("Parsed (default) using 'yyyyMMdd': " + yyyyFormatter.parse(dateStr));
		System.out.println("Parsed (default) using 'uuuuMMdd': " + uuuuFormatter.parse(dateStr));
		System.out.println("Parsed LENIENT using 'yyyyMMdd': "
				+ yyyyFormatter.withResolverStyle(ResolverStyle.LENIENT).parse(dateStr));
		System.out.println("Parsed LENIENT using 'uuuuMMdd': "
				+ uuuuFormatter.withResolverStyle(ResolverStyle.LENIENT).parse(dateStr));
		System.out.println("Parsed STRICT using 'yyyyMMdd': "
				+ yyyyFormatter.withResolverStyle(ResolverStyle.STRICT).parse(dateStr));
		System.out.println("Parsed STRICT using 'uuuuMMdd': "
				+ uuuuFormatter.withResolverStyle(ResolverStyle.STRICT).parse(dateStr));
	}
}
