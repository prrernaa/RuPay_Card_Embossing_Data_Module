package com.card.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String handleError(HttpServletRequest request, Model model) {
        // Get the status code of the error
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == 404) {
            // Get the requested URL causing the 404 error
            String requestedUri = (String) request.getAttribute("javax.servlet.error.request_uri");
            if (requestedUri != null) {
                // Set the error message with the requested endpoint
                model.addAttribute("errorMessage", "404 Page Not Found: " + requestedUri);
            } else {
                model.addAttribute("errorMessage", "404 Page Not Found");
            }
            // Return the custom error page
            return "errorPage";
        }
        // Handle other errors here if needed
        return "error";
    }

    public String getErrorPath() {
        return PATH;
    }
}
 //CustomErrorController