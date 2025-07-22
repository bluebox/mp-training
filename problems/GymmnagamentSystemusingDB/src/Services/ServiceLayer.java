package Services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.DataBaseConnector;

public class ServiceLayer {
	DataBaseConnector dbconnector;

        public static void main(String [] args){
	dbconnector=new DataBaseConnector();
	}

	
	public void addMemeber(String name,int age) throws ClassNotFoundException, SQLException {
		dbconnector.addMember(name, age);
	}
	
	public void addPlan(String name, Double fee, int durationInMonths) throws ClassNotFoundException, SQLException {
		 dbconnector.addPlan(name,fee,durationInMonths);
	}

	public int findmember(String name, int memeberShipId) throws ClassNotFoundException, SQLException {
		return dbconnector.findMember(memeberShipId);
		
	}
	
	public void removeMemberPlan(int Id) throws ClassNotFoundException, SQLException {
		dbconnector.removePlan(Id);
		
	}

	public List<Plan> getPlans() throws ClassNotFoundException, SQLException {
		return dbconnector.showPlans();
	
	}

	public void setPlan(int memeberId,int planId) throws ClassNotFoundException, SQLException {
	dbconnector.updatePlan(memeberId,planId);
	
	}

}
