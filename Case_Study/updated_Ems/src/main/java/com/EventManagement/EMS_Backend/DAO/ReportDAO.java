package com.EventManagement.EMS_Backend.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.EventManagement.EMS_Backend.Model.ModelFeedback;
import com.EventManagement.EMS_Backend.Model.ModelAverageRating;
import com.EventManagement.EMS_Backend.Model.ModelEventReport;
import com.EventManagement.EMS_Backend.Model.ModelCancelledReport;

@Repository
public class ReportDAO {


    @Autowired
    private JdbcTemplate jdbctemplate;

    public List<ModelFeedback> viewfeedback() {
        String sql = "SELECT f.eventid, f.userid, f.description AS feedback, f.rating FROM feedback f ORDER BY f.eventid";
        return jdbctemplate.query(sql,(rs,rownum)->{
        	ModelFeedback feedback = new ModelFeedback();
            feedback.setEventId(rs.getInt("eventid"));
            feedback.setUserId(rs.getString("userid"));
            feedback.setDescription(rs.getString("feedback"));
            feedback.setRating(rs.getInt("rating"));
            return feedback;

        });
    }

    public List<ModelAverageRating> avgrating() {
        String sql = "SELECT f.eventid, COUNT(f.id) AS total_feedbacks, ROUND(AVG(f.rating), 2) AS avg_rating FROM feedback f GROUP BY f.eventid ORDER BY avg_rating DESC";
        return jdbctemplate.query(sql, (rs,rownum)->{
        	 ModelAverageRating rating = new ModelAverageRating();
             rating.setEventId(rs.getInt("eventid"));
             rating.setTotalFeedbacks(rs.getInt("total_feedbacks"));
             rating.setAvgRating(rs.getDouble("avg_rating"));
             return rating;
        });
    }

    public List<ModelEventReport> eventwisereport() {
        String sql = "SELECT e.eventid, e.eventname, COUNT(r.userid) AS total_registrations FROM registration r JOIN event e ON r.eventid = e.eventid WHERE r.status = 'REGISTERED' GROUP BY e.eventid, e.eventname ORDER BY total_registrations DESC";
        return jdbctemplate.query(sql, (rs,rownum)->{
        	 ModelEventReport report = new ModelEventReport();
             report.setEventId(rs.getInt("eventid"));
             report.setEventName(rs.getString("eventname"));
             report.setTotalRegistrations(rs.getInt("total_registrations"));
             return report;
        });
    }

    public List<ModelCancelledReport> cancelledreport() {
        String sql = "SELECT r.userid, e.eventid, e.eventname, r.status, r.modified_at FROM registration r JOIN event e ON r.eventid = e.eventid WHERE r.status = 'cancelled' ORDER BY r.modified_at DESC";
        return jdbctemplate.query(sql, (rs,rownu)->{
        	   ModelCancelledReport report = new ModelCancelledReport();
               report.setUserId(rs.getString("userid"));
               report.setEventId(rs.getInt("eventid"));
               report.setEventName(rs.getString("eventname"));
               report.setStatus(rs.getString("status"));
               report.setModifiedAt(rs.getTimestamp("modified_at"));
               return report;
        });
    }

}

