<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Books</title>
</head>
<body>
	<table>
		<tr>
            <th>
                Book ID
            </th>
            <rh>
                Title
            </rh>
            <th>
                Author
            </th>
            <th>
                Category
            </th>
            <th>
                Status
            </th>
            <th>
                Availability
            </th>
        </tr>
        <c:forEach var="i" items="${data}">
            <tr>
                <td>
                    <c:out value="${b.getBookId()}"/>
                </td>
                <td>
                    <c:out value="${b.getTitle()}"/>
                </td>
                <td>
                    <c:out value="${b.getAuthor()}"/>
                </td>
                <td>
                    <c:out value="${b.getCategory()}"/>
                </td>
                <td>
                    <c:out value="${Status.valueOf(b.getStatus()).getStatus()}"/>
                </td>
                <td>
                    <c:out value="${b.getAvailability().toString().charAt(0)}"/>
                </td>
            </tr>
	    </c:forEach>
	</table>
</body>
</html>