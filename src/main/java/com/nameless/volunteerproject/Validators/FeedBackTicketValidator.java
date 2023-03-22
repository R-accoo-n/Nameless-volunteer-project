package com.nameless.volunteerproject.Validators;

import com.nameless.volunteerproject.models.FeedbackTicket;
import com.nameless.volunteerproject.models.FormErorr;

import java.util.ArrayList;

public class FeedBackTicketValidator {
    public static ArrayList<FormErorr> IsValid(FeedbackTicket obj) {
        ArrayList<FormErorr> errors = new ArrayList<FormErorr>();

        if (obj.getRate() < 1 || obj.getRate() > 5) {
            errors.add(new FormErorr("Rate", "Rate should be between 1 and 5"));
        }

        if (obj.getDescription() == null || obj.getDescription().trim().isEmpty()) {
            errors.add(new FormErorr("Description", "Empty field"));
        }
        return errors;
    }
}
