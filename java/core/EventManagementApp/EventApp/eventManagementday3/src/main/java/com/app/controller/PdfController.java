package com.app.controller;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.model.Feedback;
import com.app.model.User;
import com.app.service.EventRegistrationService;
import com.app.service.FeedbackService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/pdf")
@Slf4j
public class PdfController {

    private final FeedbackService feedbackService;
    private final EventRegistrationService eventService;
    
    public PdfController(FeedbackService feedbackService,
                         EventRegistrationService eventService) {
        this.feedbackService = feedbackService;
        this.eventService = eventService;
    }


    @GetMapping("/feedback")
    public ResponseEntity<byte[]> feedbackPdf(@RequestParam int eventId) throws Exception {
        log.info("Event ID : "+ eventId);
        List<Feedback> rows = feedbackService.getAllFeedbackofEvent(eventId);
        log.info("rows : "+ rows.size());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document doc = new Document(PageSize.A4.rotate(), 36, 36, 36, 36);
        PdfWriter.getInstance(doc, baos);
        doc.open();

        addTitle(doc, "Event Feedback Report - Event ID: " + eventId);

        PdfPTable table = new PdfPTable(3);
        table.setWidths(new int[]{1, 6, 1});
        addHeader(table, "User ID", "Feedback", "Rating");

        for (Feedback f : rows) {
            table.addCell(String.valueOf(f.getUserId()));
            table.addCell(f.getFeedback());
            table.addCell(String.valueOf(f.getRating()));
        }

        doc.add(table);
        doc.close();

        return buildPdfResponse(baos, "feedback_event_" + eventId + ".pdf");
    }

    @GetMapping("/registered")
    public ResponseEntity<byte[]> registeredPdf(@RequestParam int eventId) throws Exception {
        return usersPdf("pdf/registered", eventId, "R",
                "Registered Users - Event ID: " + eventId,
                "registered_event_" + eventId + ".pdf");
    }

    @GetMapping("/attended")
    public ResponseEntity<byte[]> attendedPdf(@RequestParam int eventId) throws Exception {
        return usersPdf("pdf/attended", eventId, "A",
                "Attended Users - Event ID: " + eventId,
                "attended_event_" + eventId + ".pdf");
    }

    @GetMapping("/absentees")
    public ResponseEntity<byte[]> absenteesPdf(@RequestParam int eventId) throws Exception {
        return usersPdf("pdf/absentees", eventId, "N",
                "Absentees - Event ID: " + eventId,
                "absentees_event_" + eventId + ".pdf");
    }

    @GetMapping("/cancelled")
    public ResponseEntity<byte[]> cancelledPdf(@RequestParam int eventId) throws Exception {
        return usersPdf("pdf/cancelled", eventId, "C",
                "Cancelled Users - Event ID: " + eventId,
                "cancelled_event_" + eventId + ".pdf");
    }

    private ResponseEntity<byte[]> usersPdf(String tag,
                                            int eventId,
                                            String status,
                                            String title,
                                            String filename) throws Exception {
        log.info("{} evt={}", tag, eventId);
        List<User> users = eventService.getUsersByRegistrationStatus(eventId, status);
        log.info("rows : "+ users.size());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document doc = new Document(PageSize.A4.rotate(), 36, 36, 36, 36);
        PdfWriter.getInstance(doc, baos);
        doc.open();

        addTitle(doc, title);

        PdfPTable table = new PdfPTable(3);
        table.setWidths(new int[]{1, 3, 4});
        addHeader(table, "User ID", "Name", "Email");

        for (User u : users) {
            table.addCell(String.valueOf(u.getUserId()));
            table.addCell(u.getName());
            table.addCell(u.getEmail());
        }

        doc.add(table);
        doc.close();

        return buildPdfResponse(baos, filename);
    }

    private void addTitle(Document document, String text) throws DocumentException {
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph title = new Paragraph(text, font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);
    }

    private void addHeader(PdfPTable table, String... headers) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        for (String h : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(h, font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
    }

    private ResponseEntity<byte[]> buildPdfResponse(ByteArrayOutputStream baos, String filename) {
        byte[] bytes = baos.toByteArray();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .contentLength(bytes.length)
                .body(bytes);
    }
}
