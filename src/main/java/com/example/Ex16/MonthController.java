package com.example.Ex16;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class MonthController {

    @GetMapping("/month")
    public Month getMonth(HttpServletRequest request){

        return (Month) request.getAttribute("month");
    }



}