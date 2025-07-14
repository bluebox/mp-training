package com.app.controller;

import com.app.enums.Gender;
import com.app.enums.Status;
import com.app.model.Feedback;
import com.app.model.User;
import com.app.service.EventRegistrationService;
import com.app.service.FeedbackService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
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
    public String generateFeedbackPdf(@RequestParam int event_id) throws Exception {
        List<Feedback> feedbacks = feedbackService.getAllFeedbackofEvent(event_id);

        String filePath = "/home/developer/Downloads/feedback_event_" + event_id + ".pdf";

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        Paragraph title = new Paragraph("Event Feedback Report - Event ID: " + event_id, font);
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
            table.addCell(String.valueOf(f.getUser_id()));
            table.addCell(f.getFeedback());
            table.addCell(String.valueOf(f.getRating()));
        }

        document.add(table);
        document.close();

        return "PDF saved successfully at: " + filePath;
    }
    
    @GetMapping("/attended")
    public String generateAttendancePdf(@RequestParam int event_id) throws FileNotFoundException, DocumentException
    {
    	 String filePath = "/home/developer/Downloads/feedback_event_" + event_id + ".pdf";
         List<User> users = eventService.eventAttendents(event_id);


         Document document = new Document();
         PdfWriter.getInstance(document, new FileOutputStream(filePath));
         document.open();

         Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
         font.setSize(18);
         Paragraph title = new Paragraph("Event Absenties Report - Event ID: " + event_id, font);
         title.setAlignment(Element.ALIGN_CENTER);
         document.add(title);
         document.add(Chunk.NEWLINE);

         PdfPTable table = new PdfPTable(7);
         table.setWidthPercentage(100);
         table.setWidths(new int[]{2,4,4,5,3,3,4});

         PdfPCell h1 = new PdfPCell(new Phrase("User ID"));
         PdfPCell h2 = new PdfPCell(new Phrase("Name"));
         PdfPCell h3 = new PdfPCell(new Phrase("Mobile Number"));
         PdfPCell h4 = new PdfPCell(new Phrase("Email"));
         PdfPCell h5 = new PdfPCell(new Phrase("Role"));
         PdfPCell h6 = new PdfPCell(new Phrase("Gender"));
         PdfPCell h7 = new PdfPCell(new Phrase("Department"));
         table.addCell(h1);
         table.addCell(h2);
         table.addCell(h3);
         table.addCell(h4);
         table.addCell(h5);
         table.addCell(h6);
         table.addCell(h7);
         

         for (User u : users) {
             table.addCell(String.valueOf(u.getUser_id()));
             table.addCell(u.getName());
             table.addCell(u.getPhn_number());

             table.addCell(u.getEmail());
             table.addCell(u.getRole());
             table.addCell(String.valueOf(u.getGender()));
             table.addCell(u.getDept());
             
             
         }

         document.add(table);
         document.close();

         return "PDF saved successfully at: " + filePath;
         
    }
    
    @GetMapping("/absenties")
    public String generateAbsentiesPdf(@RequestParam int event_id) throws FileNotFoundException, DocumentException
    {
        String filePath = "/home/developer/Downloads/feedback_event_" + event_id + ".pdf";
        List<User> users = eventService.eventAbsenties(event_id);


        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        Paragraph title = new Paragraph("Event Absenties Report - Event ID: " + event_id, font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{2,4,4,5,3,3,4});

        PdfPCell h1 = new PdfPCell(new Phrase("User ID"));
        PdfPCell h2 = new PdfPCell(new Phrase("Name"));
        PdfPCell h3 = new PdfPCell(new Phrase("Mobile Number"));
        PdfPCell h4 = new PdfPCell(new Phrase("Email"));
        PdfPCell h5 = new PdfPCell(new Phrase("Role"));
        PdfPCell h6 = new PdfPCell(new Phrase("Gender"));
        PdfPCell h7 = new PdfPCell(new Phrase("Department"));
        table.addCell(h1);
        table.addCell(h2);
        table.addCell(h3);
        table.addCell(h4);
        table.addCell(h5);
        table.addCell(h6);
        table.addCell(h7);
        

        for (User u : users) {
            table.addCell(String.valueOf(u.getUser_id()));
            table.addCell(u.getName());
            table.addCell(u.getPhn_number());

            table.addCell(u.getEmail());
            table.addCell(u.getRole());
            table.addCell(String.valueOf(u.getGender()));
            table.addCell(u.getDept());
            
            
        }

        document.add(table);
        document.close();

        return "PDF saved successfully at: " + filePath;
        
    }
    
    @GetMapping("/cancelled")
    public String generateCancelledPdf(@RequestParam int event_id) throws FileNotFoundException, DocumentException
    {

    	 String filePath = "/home/developer/Downloads/feedback_event_" + event_id + ".pdf";
         List<User> users = eventService.noOfUsersCancelledEventRegistration(event_id);


         Document document = new Document();
         PdfWriter.getInstance(document, new FileOutputStream(filePath));
         document.open();

         Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
         font.setSize(18);
         Paragraph title = new Paragraph("Event Absenties Report - Event ID: " + event_id, font);
         title.setAlignment(Element.ALIGN_CENTER);
         document.add(title);
         document.add(Chunk.NEWLINE);

         PdfPTable table = new PdfPTable(7);
         table.setWidthPercentage(100);
         table.setWidths(new int[]{2,4,4,5,3,3,4});

         PdfPCell h1 = new PdfPCell(new Phrase("User ID"));
         PdfPCell h2 = new PdfPCell(new Phrase("Name"));
         PdfPCell h3 = new PdfPCell(new Phrase("Mobile Number"));
         PdfPCell h4 = new PdfPCell(new Phrase("Email"));
         PdfPCell h5 = new PdfPCell(new Phrase("Role"));
         PdfPCell h6 = new PdfPCell(new Phrase("Gender"));
         PdfPCell h7 = new PdfPCell(new Phrase("Department"));
         table.addCell(h1);
         table.addCell(h2);
         table.addCell(h3);
         table.addCell(h4);
         table.addCell(h5);
         table.addCell(h6);
         table.addCell(h7);
         

         for (User u : users) {
             table.addCell(String.valueOf(u.getUser_id()));
             table.addCell(u.getName());
             table.addCell(u.getPhn_number());

             table.addCell(u.getEmail());
             table.addCell(u.getRole());
             table.addCell(String.valueOf(u.getGender()));
             table.addCell(u.getDept());
             
             
         }

         document.add(table);
         document.close();

         return "PDF saved successfully at: " + filePath;
         
    }
}
