<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View All Members</title>
<link rel="stylesheet" href="css/table.css">

    <script type="text/javascript">
        function confirmDelete(memberId) {
            var result = window.confirm("Are you sure you want to delete this item?");
            if (result) {
                 window.location.href="DeleteMember?memberId="+memberId;
            } else {
                 window.alert("Member delete Failed!");
            }
        }
    </script>
    
</head>
<body>
        
  	 <div class="wrapper">
          <h1 style="text-align: center">View All Members</h1>
           <c:if test="${not empty message}">
      	<p id="responseMessage" style="color: ${formReset ? 'green' : 'red'}; font-weight: bold; text-align: center;">
          	${message}
      	</p>
  	</c:if>
          <table>
              <caption>Members data</caption>
              <thead>
                  <tr>
                      <th>Id</th>
                      <th>Name</th>
                      <th>Email</th>
                      <th>Mobile</th>
                      <th>Gender</th>
                      <th>Address</th>
                      <th>Actions</th>
                  </tr>
              </thead>
               
              <tbody>
              <c:forEach var="member" items="${members}">
      			 <tr>
                      <td data-cell="ID">${member.getMemberId()}</td>
                      <td data-cell="Title">${member.getName()}</td>
                      <td data-cell="Author">${member.getEmail()}</td>
                      <td data-cell="Category">${member.getMobile()}</td>
                      <td data-cell="Status">${member.getGender()}</td>
                      <td data-cell="Availability">${member.getAddress()}</td>
                      <td data-cell="Actions"> 
                      <button id="edit" name="edit" onclick="location.href='MemberUpdateServlet?methodforward=post&memberId=${member.getMemberId()}'">Edit</button>
                      <button id="delete" name="delete" onclick="confirmDelete(${member.getMemberId()})">Delete</button>
                      </td>
                  </tr>
  			</c:forEach>
                   
              </tbody>
          </table>
      </div>
       
</body>
</html>