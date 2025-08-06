<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TagLib Examples</title>
</head>
<body>
	<c:set var="land" value="Paradise"/>
	<c:out value="${land}" />
	<s:V>
		Created
	</s:V>
</body>
</html>