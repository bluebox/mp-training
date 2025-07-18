package com.app.controller;

import com.app.model.Feedback;
import com.app.service.EventRegistrationService;
import com.app.service.FeedbackService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/pdf")
@CrossOrigin("*")
public class PdfController {

    @Autowired
    private FeedbackService feedbackService;
    
    @Autowired EventRegistrationService eventService;

    @GetMapping("/feedback")
    public String generateFeedbackPdf(@RequestParam int eventId) throws Exception {
        List<Feedback> feedbacks = feedbackService.getAllFeedbackofEvent(eventId);

        String filePath = "/home/developer/Downloads/feedback_event_" + eventId + ".pdf";

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        Paragraph title = new Paragraph("Event Feedback Report - Event ID: " + eventId, font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{1, 5, 2});

        PdfPCell h1 = new PdfPCell(new Phrase("User ID"));
        PdfPCell h2 = new PdfPCell(new Phrase("Feedback"));
        PdfPCell h3 = new PdfPCell(new Phrase("Rating"));
        table.addCell(h1);
        table.addCell(h2);
        table.addCell(h3);

        for (Feedback f : feedbacks) {
            table.addCell(String.valueOf(f.getUserId()));
            table.addCell(f.getFeedback());
            table.addCell(String.valueOf(f.getRating()));
        }

        document.add(table);
        document.close();

        return "PDF saved successfully at: " + filePath;
    }
    
}



