package Services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.DataBaseConnector;

public class ServiceLayer {
  
	
	public static void addMemeber(String name,int age) throws ClassNotFoundException, SQLException {
		DataBaseConnector.addMember(name, age);
		return;
	}
	
	public static void addPlan(String name, Double fee, int durationInMonths) throws ClassNotFoundException, SQLException {
		 DataBaseConnector.addPlan(name,fee,durationInMonths);
		return;
	}

	public static int findmember(String name, int memeberShipId) throws ClassNotFoundException, SQLException {
		return DataBaseConnector.findMember(memeberShipId);
		
	}
	
	public static void removeMemberPlan(int Id) throws ClassNotFoundException, SQLException {
		DataBaseConnector.removePlan(Id);
		return;
	}

	public static void getPlans() throws ClassNotFoundException, SQLException {
		DataBaseConnector.showPlans();
		return ;
	}

	public static void setPlan(int memeberId,int planId) throws ClassNotFoundException, SQLException {
	DataBaseConnector.updatePlan(memeberId,planId);
	return;
	}

}
