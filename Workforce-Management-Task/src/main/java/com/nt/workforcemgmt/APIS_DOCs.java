package com.nt.workforcemgmt;

public class APIS_DOCs {




/*

=============
 ***APIs*** 
=============	


//1. add task
//========================================================================

	POST : http://localhost:4041/tasks
	
	{
	"title": "Java Full Stack Developer",
	"assignedTo": "Tushar",
	"startDate": "2025-08-07",
	"dueDate": "2025-08-07"
	}

//2. get tasks between two date range
//========================================================================

	GET : http://localhost:4041/tasks?start=2025-06-07&end=2025-08-10


//3. get task by id wise
//========================================================================

	GET : http://localhost:4041/tasks/1


//4. add comment to particular task
//========================================================================

	POST : http://localhost:4041/tasks/1/comments
	
	{
	"author": "Manager",
	"message": "Please prioritize this"
	}


//5. update the task priority
//========================================================================

	PUT : http://localhost:4041/tasks/1/priority
	
	{
	"priority": "HIGH"
	}


//6. get task by priority wise
//========================================================================

	GET : http://localhost:4041/tasks/priority/HIGH


//7. reassign the task to employeer
//========================================================================

	PUT : http://localhost:4041/tasks/1/reassign?newAssignee=Akash



8. get all tasks
//========================================================================

	GET : http://localhost:4041/tasks/all


*/





}
