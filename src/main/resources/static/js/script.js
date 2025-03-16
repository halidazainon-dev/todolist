$(document).ready(function() {
    // To delete each task 
    $(".delete-task-btn").click(function() {
        let taskId = $(this).data("task-id");

        $.ajax({
            url: "/delete/" + taskId,   
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

    // To edit each task
    $(".edit-task-btn").click(function() {
        let taskId = $(this).data("task-id");
        let taskDescription = $(this).data("task-description");

        $("#edit-task-id").val(taskId);
        $("#edit-task-desc").val(taskDescription);

        $("#editTaskModal").modal("show"); // Open modal
    });
    
    // To update each task
    $("#save-task-btn").click(function() {
        let taskId = $("#edit-task-id").val();
        let newDescription = $("#edit-task-desc").val().trim();

        if (newDescription === "") {
            alert("Task description cannot be empty!");
            return;
        }

        let formData = new FormData();
        formData.append("id", taskId);
        formData.append("description", newDescription);

        $.ajax({
            url: "/update",
            type: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function(response) {
                console.log(response);
                alert("Task updated!");
                location.reload();  
            },
            error: function(xhr, status, error) {
                console.error("Error updating task:", error);
                alert("Failed to update task. Please try again.");
            }
        });
    });

    // To delete all tasks
    $("#delete-all-btn").click(function() {
        if (confirm("Are you sure you want to delete all tasks?")) {
            $.ajax({
                url: "/deleteAll",
                type: "POST",
                success: function(response) {
                    console.log(response);
                    alert("All tasks deleted!");
                    location.reload();  
                },
                error: function(xhr, status, error) {
                    console.error("Error deleting tasks:", error);
                    alert("Failed to delete tasks. Please try again.");
                }
            });
        }
    });

    // To update task status
    $(".task-item input[type='checkbox']").on('click', function() {
        var taskId = $(this).closest('.task-item').data('task-id');
        var completed = $(this).is(':checked');

        $.ajax({
            type: 'POST',
            url: '/updateTaskStatus',
            data: {
                id: taskId,
                completed: completed
            },
            success: function(response) {
                console.log(response);
            },
            error: function(xhr, status, error) {
                console.error("Error updating task status:", error);
            }
        });

    }); 

});