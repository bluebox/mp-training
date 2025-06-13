package Jdbc.JDBCConnection.productsChallenge;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.hibernate.boot.cfgxml.internal.ConfigLoader;

public class Main {

	public static void main(String[] args) {
		ChallengeProducts.main(args);
		ChallengeProductDetails.main(args);
		JoinQuery.getCountAndTotCost(1);
	}
}
