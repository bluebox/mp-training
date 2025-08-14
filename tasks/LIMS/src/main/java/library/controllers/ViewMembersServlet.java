package library.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.exception.LibraryException;
import library.model.Member;
import library.service.MemberServiceImpl;
import library.service.interfaces.MemberService;

@WebServlet("/viewMembers") 
public class ViewMembersServlet extends HttpServlet {

    private MemberService memberService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.memberService = new MemberServiceImpl();
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadMembers(request);
        request.getRequestDispatcher("/ViewMembersScreen.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.removeAttribute("message");
        request.removeAttribute("messageType");

        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            request.setAttribute("message", "No action specified.");
            request.setAttribute("messageType", "error");
        } else if (action.equals("refreshMembers")) {
//            loadMembers(request);
        } else if (action.startsWith("deleteMember:")) {
            try {
                int memberId = Integer.parseInt(action.split(":")[1]);
                handleDeleteMember(memberId, request);
            } catch (NumberFormatException e) {
                request.setAttribute("message", "Invalid Member ID format for deletion.");
                request.setAttribute("messageType", "error");
                e.printStackTrace();
            }
        } else if (action.equals("deleteSelectedMembers")) {
            handleDeleteSelectedMembers(request);
        } else {
            request.setAttribute("message", "Unknown action: " + action);
            request.setAttribute("messageType", "error");
        }

        loadMembers(request);
        request.getRequestDispatcher("/ViewMembersScreen.jsp").forward(request, response);
    }

    private void loadMembers(HttpServletRequest request) {
        try {
            List<Member> members = memberService.getAllMembers(); 
            List<Map<String, Object>> displayMembers = new ArrayList<>();

            if (members != null && !members.isEmpty()) {
                for (Member member : members) {
                    Map<String, Object> rowData = new HashMap<>();
                    rowData.put("memberID", member.getMemberID());
                    rowData.put("name", member.getName());
                    rowData.put("email", member.getEmail());
                    rowData.put("phoneNumber", member.getPhoneNumber());
                    rowData.put("gender", member.getGender().toString());
                    rowData.put("address", member.getAddress());
                    displayMembers.add(rowData);
                }
            } else {
                request.setAttribute("message", "No members found in the database.");
                request.setAttribute("messageType", "info"); 
            }
            request.setAttribute("displayMembers", displayMembers);
            if (request.getAttribute("message") == null || request.getAttribute("messageType").equals("success")) {
                 request.setAttribute("message", ""); 
            }

        } catch (LibraryException e) {
            request.setAttribute("message", " " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        } catch (Exception e) {
            request.setAttribute("message", "An unexpected error occurred while loading members: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        }
    }

    private void handleDeleteMember(int memberId, HttpServletRequest request) {
        try {
            memberService.deleteMembers(Arrays.asList(memberId)); 
            request.setAttribute("message", "Member with id "+ memberId+" deleted successfully");
            request.setAttribute("messageType", "success");
        } catch (LibraryException e) {
            request.setAttribute("message", "" + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        } catch (Exception e) {
            request.setAttribute("message", "An  error occurred while deleting member: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        }
    }

    private void handleDeleteSelectedMembers(HttpServletRequest request) {
        String[] selectedIds = request.getParameterValues("selectedMemberIds");

        if (selectedIds == null || selectedIds.length == 0) {
            request.setAttribute("message", "No members selected for deletion.");
            request.setAttribute("messageType", "error");
            return;
        }

        List<Integer> idsToDelete = Arrays.stream(selectedIds)
                                            .map(Integer::parseInt)
                                            .collect(Collectors.toList());

        try {
            memberService.deleteMembers(idsToDelete); 
            request.setAttribute("message", "Successfully deleted " + idsToDelete.size() + " members.");
            request.setAttribute("messageType", "success");
        } catch (LibraryException e) {
            request.setAttribute("message", "Error deleting selected members: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        } catch (Exception e) {
            request.setAttribute("message", "An error occurred while deleting selected members: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        }
    }
}