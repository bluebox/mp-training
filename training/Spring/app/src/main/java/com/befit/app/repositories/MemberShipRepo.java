package com.befit.app.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.befit.app.beans.MemberShip;

@Repository
public class MemberShipRepo {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public MemberShipRepo(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Transactional
	public boolean addMembership(MemberShip memberShip) {
		String sql = "insert into memberShip(memberShipName,cost,startDate,endDate,status) values(:memberShipName,:cost,:startDate,:endDate,:status)";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("memberShipName", memberShip.getMembershipName());
		params.addValue("cost", memberShip.getCost());
		params.addValue("startDate", memberShip.getStartDate());
		params.addValue("endDate", memberShip.getEndDate());
		params.addValue("status", memberShip.getStatus());

		try {
			namedParameterJdbcTemplate.update(sql, params);

			int val = namedParameterJdbcTemplate.queryForObject("select max(id) from membership",
					new MapSqlParameterSource(), Integer.class);
			String activities = "insert into activities (id,activity) values (:id,:activity)";
			for (int i = 0; i < memberShip.getActivites().size(); i++) {
				
				MapSqlParameterSource tempParams = new MapSqlParameterSource();
				tempParams.addValue("val", val);
				tempParams.addValue("activity", memberShip.getActivites().get(i));
				namedParameterJdbcTemplate.update(activities, tempParams);
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return false;
		}
	}
	
	public List<MemberShip> viewAll(){
		String sql = "Select memberShipName,cost,startDate,endDate,status from memberShip";
		try {
			List<MemberShip> memberShips = namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource(),new BeanPropertyRowMapper(MemberShip.class));
			String activites = "Select activity from activities where id=:id";
			for(int i=0;i < memberShips.size();i++) {
				MapSqlParameterSource params = new MapSqlParameterSource();
				params.addValue("id", memberShips.get(i).getId());
				memberShips.get(i).setActivites(namedParameterJdbcTemplate.queryForList(activites, params,String.class));
			}
			return memberShips;
		}catch(Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}
	}
	
	public List<MemberShip> findMemberShipsByJoinDateBetween(Date startDate, Date endDate) {
		try {
			String sql = "Select memberShipName,cost,startDate,endDate,status from memberShip WHERE joinDate BETWEEN :start AND :end";
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("start", startDate);
			params.addValue("end", endDate);
			List<MemberShip> memberShips = namedParameterJdbcTemplate.query(sql,params,new BeanPropertyRowMapper(MemberShip.class));
			String activites = "Select activity from activities where id=:id";
			for(int i=0;i < memberShips.size();i++) {
				MapSqlParameterSource tempParams = new MapSqlParameterSource();
				tempParams.addValue("id", memberShips.get(i).getId());
				memberShips.get(i).setActivites(namedParameterJdbcTemplate.queryForList(activites, tempParams,String.class));
			}
			return memberShips;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
