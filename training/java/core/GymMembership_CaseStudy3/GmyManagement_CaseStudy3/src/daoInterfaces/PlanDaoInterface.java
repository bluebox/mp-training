package daoInterfaces;
import java.sql.SQLException;
import java.util.List;

import model.MembershipPlan;
public interface PlanDaoInterface {
List<MembershipPlan> getAllPlans() throws SQLException;
}
