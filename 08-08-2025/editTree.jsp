<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head><title>Edit Tree</title></head>
<body>
    <h2>Add/Edit Tree</h2>
    <form:form method="POST" modelAttribute="tree" action="${tree.id == 0 ? 'save' : 'update'}">
        <form:hidden path="id"/>
        <form:input path="name" placeholder="Name"/>
        <form:input path="type" placeholder="Type"/>
        <form:input path="age" placeholder="Age"/>
        <input type="submit" value="Submit"/>
    </form:form>
</body>
</html>