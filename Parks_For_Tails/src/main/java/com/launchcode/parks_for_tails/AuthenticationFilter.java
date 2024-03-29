//package com.launchcode.parks_for_tails;
//
//import com.launchcode.parks_for_tails.data.UserRepository;
//import com.launchcode.parks_for_tails.models.User;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import com.launchcode.parks_for_tails.controllers.UserController;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.HandlerInterceptor;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//
//public class AuthenticationFilter implements HandlerInterceptor{
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    UserController userController;
//
//    //may need to add images to whitelist
//    // Allow certain pages and static resources to be seen by the public (not logged in)
//    private static final List<String> whitelist = Arrays.asList("/api", "/home", "/register", "/login", "/css", "/logout", "/api/register", "/api/login" );
//
//    // Check all pages and static resources against blacklist
//    private static boolean isWhitelisted(String path) {
//        for (String pathRoot : whitelist) {
//            if (path.equals("/") || path.startsWith(pathRoot)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response,
//                             Object handler) throws IOException {
//
//        // Don't require sign-in for whitelisted pages
//        if (isWhitelisted(request.getRequestURI())) {
//            // Early return to allow request to go through
//            return true;
//        }
//
//        HttpSession session = request.getSession();
//        User user = UserController.getUserFromSession(session);
//
//        // The user is logged in
//        if (user != null) {
//            return true;
//        }
//
//        // The user is NOT logged in
//        //response.sendRedirect("/login");
//        return false;
//    }
//}
