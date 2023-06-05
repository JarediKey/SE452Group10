/**
 * In this package, contains security services and implementations.
 * @author Jared Wang
 *
 * =====Implementation=====
 * 1. Authentication:
 *      I have implemented user authentication using the functionalities provided by Spring Security.
 *      Users can log in by providing their username and password.
 * 2. User Registration:
 *      I have implemented a user registration feature, allowing users to register by providing their username, email, role, and password.
 * 3. User Role and Permission Management:
 *      I have managed user roles and permissions based on their assigned roles (admin, teacher, student).
 *      Each role has specific permissions within the system, and users are redirected to the appropriate page based on their role.
 * 4. Database Integration:
 *      My code integrates with a database using repositories such as AdminRepo, TeacherRepo, and StudentRepo to store user information.
 * 5. Password Encryption:
 *      I have applied password encryption using the PasswordEncoder to ensure the security of user passwords.
 * 6. Security Configuration:
 *      By configuring the WebSecurityConfig class, I have set up security configurations for the application,
 *      including access control, protection against cross-site scripting, and frame attacks.
 * 7. User Details Loading:
 *      I have implemented the UserDetailsService interface to load user details during user login for authentication and authorization purposes.
 * 8. Login Success Handling:
 *      Through the custom AuthenticationSuccessHandler,I have implemented logic to handle successful user logins
 *      and redirect them to different pages based on their role.
 *
 * =====Lessons Learned=====
 * 1. Created a configuration class (WebSecurityConfig) with @Configuration and @EnableMethodSecurity annotations to enable and configure Spring Security.
 * 2. Implemented the UserDetailsService interface in the UserDetailsServiceImpl class to load user details based on the provided username.
 * 3. Configured a DaoAuthenticationProvider bean in the WebSecurityConfig class to handle authentication using the user details service and password encoder.
 * 4. Defined an AuthenticationManager bean in the WebSecurityConfig class to manage the authentication process.
 * 5. Implemented a custom AuthenticationSuccessHandler (LoginAuthenticationSuccessHandler) to handle successful authentication and redirect the user to the appropriate page based on their role.
 * 6. Created a SignupRequest class to represent the data required for user registration.
 * 7. Implemented a RESTful controller (RegistrationService) with a /signup endpoint to handle user registration.
 * 8. Implemented validation logic in the /signup endpoint to check if the username and email are already taken before saving the user to the respective repositories (AdminRepo, TeacherRepo, StudentRepo).
 * 9. Encoded the user's password using the provided PasswordEncoder and saved the user to the appropriate repository based on their role.
 * 10.Configured the security filters and permissions in the filterChain method of the WebSecurityConfig class, allowing access to specific URLs and setting up protection against cross-site scripting and frame attacks.
 */
package com.group10.se452_g10.security;