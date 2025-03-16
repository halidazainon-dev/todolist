<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todo List App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-md-8 col-lg-6">
                <div class="card shadow">
                    <div class="card-header bg-primary text-white">
                        <h1 class="h4 mb-0 text-center">Todo List App</h1>
                    </div>
                    <div class="card-body">
                        
                        <form action="${pageContext.request.contextPath}/add" method="post">
                            <div class="input-group mb-3">
                                <input type="text" name="description" class="form-control" placeholder="Add a new task">
                                <button type="submit" class="btn btn-primary">Add</button>
                            </div>
                        </form>
                        
                        <c:if test="${not empty tasks}">
                            <ul class="list-group">
                                <c:forEach items="${tasks}" var="task">
                                    <li class="list-group-item d-flex justify-content-between align-items-center">
                                        <span class="task-item"  data-task-id="${task.id}">
                                            <input type="checkbox" ${task.completed ? 'checked' : ''}>
                                            ${task.description}
                                        </span>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-primary edit-task-btn" data-task-id="${task.id}" data-task-description="${task.description}">
                                                <i class="bi bi-pencil"></i>
                                            </button>
                                            <button type="button" class="btn btn-sm btn-danger delete-task-btn" data-task-id="${task.id}">
                                                <i class="bi bi-trash"></i>
                                            </button>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:if>
                        
                        <c:if test="${empty tasks}">
                            <div class="alert alert-info text-center">
                                Add a task!
                            </div>
                        </c:if>
                      
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>

