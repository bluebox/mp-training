package com.EventManagement.EMS_Backend.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EventManagement.EMS_Backend.DAO.ReportDAO;
import com.EventManagement.EMS_Backend.Model.ModelFeedback;
import com.EventManagement.EMS_Backend.Model.ModelAverageRating;
import com.EventManagement.EMS_Backend.Model.ModelEventReport;
import com.EventManagement.EMS_Backend.Model.ModelCancelledReport;

@Service
public class ReportService {

    @Autowired
    private ReportDAO reportDAO;

    public List<ModelFeedback> getAllFeedbacks() {
        return reportDAO.viewfeedback();
    }

    public List<ModelAverageRating> getAverageRatings() {
        return reportDAO.avgrating();
    }

    public List<ModelEventReport> getEventWiseReport() {
        return reportDAO.eventwisereport();
    }

    public List<ModelCancelledReport> getCancelledRegistrations() {
        return reportDAO.cancelledreport();
    }
}
