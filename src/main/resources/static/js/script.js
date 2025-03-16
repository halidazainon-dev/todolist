$(document).ready(function() {
    // To delete each task 
    $(".delete-task-btn").click(function() {
        let taskId = $(this).data("task-id");

        $.ajax({
            url: "${pageContext.request.contextPath}/delete/" + taskId,   
            type: "DELETE",
            success: function(response) {
                console.log(response);
                location.reload(); 
            },
            error: function(xhr, status, error) {
                console.error("Error deleting task:", error);
                alert("Failed to delete task. Please try again.");
            }
        });
    });

});