package com.example.Ex16;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@Component
public class MonthInterceptor implements HandlerInterceptor {

    private List<Month> months;

    public MonthInterceptor() {
       months = new ArrayList<>(Arrays.asList(
                new Month(1, "January", "Gennaio", "Januar"),
                new Month(2, "February", "Febbraio", "Februar"),
                new Month(3, "March", "Marzo", "Marz"),
                new Month(4, "April", "Aprile", "April"),
                new Month(5, "May", "Maggio", "Mai"),
                new Month(6, "June", "Giugno", "Juni")));
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String monthNumberStr = request.getHeader("monthNumber");

        if (monthNumberStr == null || monthNumberStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "monthNumber header mancante o vuoto");
            return false;
        }

        int monthNumber = Integer.parseInt(monthNumberStr);
        Month month = months.stream()
                .filter(m -> m.getMonthNumber() == monthNumber)
                .findFirst()
                .orElse(new Month(monthNumber, "nope", "nope", "nope"));

        request.setAttribute("month", month);
        return true;
    }
}