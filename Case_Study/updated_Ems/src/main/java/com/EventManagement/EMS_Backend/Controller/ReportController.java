package com.EventManagement.EMS_Backend.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.EventManagement.EMS_Backend.Model.ModelFeedback;
import com.EventManagement.EMS_Backend.Model.ModelAverageRating;
import com.EventManagement.EMS_Backend.Model.ModelEventReport;
import com.EventManagement.EMS_Backend.Model.ModelCancelledReport;
import com.EventManagement.EMS_Backend.Service.ReportService;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/feedbacks")
    public List<ModelFeedback> viewFeedbacks() {
        return reportService.getAllFeedbacks();
    }

    @GetMapping("/average-ratings")
    public List<ModelAverageRating> viewAverageRatings() {
        return reportService.getAverageRatings();
    }

    @GetMapping("/event-wise")
    public List<ModelEventReport> viewEventWiseReport() {
        return reportService.getEventWiseReport();
    }

    @GetMapping("/cancelled")
    public List<ModelCancelledReport> viewCancelledRegistrations() {
        return reportService.getCancelledRegistrations();
    }
}
