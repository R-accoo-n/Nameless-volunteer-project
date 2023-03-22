package com.nameless.volunteerproject.Validators;

import com.nameless.volunteerproject.models.FormErorr;
import com.nameless.volunteerproject.models.SupportTicket;

import java.util.ArrayList;

public class SupportTicketValidator {
    public static ArrayList<FormErorr> IsValid(SupportTicket obj) {
        ArrayList<FormErorr> errors = new ArrayList<FormErorr>();

        if (obj.getDescription() == null || obj.getDescription().trim().isEmpty()) {
            errors.add(new FormErorr("Description","Empty field"));
        }

        if (obj.getProblemType() == null || obj.getProblemType().trim().isEmpty()) {
            errors.add(new FormErorr("ProblemType","Empty field"));
        }

        if (obj.getProblemSubtype() == null || obj.getProblemSubtype().trim().isEmpty()) {
            errors.add(new FormErorr("ProblemSubtype","Empty field"));
        }

        return errors;
    }
}
