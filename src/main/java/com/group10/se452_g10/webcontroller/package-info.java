package com.group10.se452_g10.webcontroller;

/**
 * In this package, contains web services and implementations.
 * @author Ayyub Jose, Jared Wang
 *
 * ================Jared Wang================Start================
 * ----------Function implementation----------
 *  1. Add login page for 3 different roles and provide the button of operations which the role is authorized after login.
 *  2. For authority(ADMID)
 *      -2.1 Add list course/student/teacher page with operation update(jump to edit page) and delete(delete item in page).
 *      -2.2.Add craete/edit course page.
 *      -2.3.Add create/edit page of student/teacher.(save with encode password, edit page will never show previous password.)
 *  3. For authority(STUDENT, TEACHER)
 *      -3.1 Add course search page and search result. (Type keyword in search-page, jump to search-result show the reulst list)
 *
 * ---------Lessons Learned----------
 *  1. Using annotations to configure and define Web Controllers in Spring Boot:
 *      @Controller: Marks a class as a controller.
 *      @RequestMapping: Specifies the prefix of the controller's request path.
 *  2. Dependency injection and working with repositories:
 *      @Autowired: Injects dependencies into the controller, such as database repositories.
 *  3. Handling requests:
 *      @GetMapping: Handles GET requests and specifies the request path.
 *      @PostMapping: Handles POST requests and specifies the request path.
 *      @PathVariable: Retrieves variable values from the request path.
 *  4. Using Model and HttpSession:
 *      Model: Passes data to views using the model.addAttribute() method.
 *      HttpSession: Accesses and manages session data.
 * 5. Authorization control:
 *      @PreAuthorize: Restricts access to methods based on user permissions.
 * 6. View redirection:
 *      Returns the view name as a string, and Spring Boot resolves it to the corresponding view path.
 *      Uses the redirect: prefix to implement redirection to a specified path.
 * ================Jared Wang================End================
 *
 */