package com.nameless.volunteerproject.Validators;

import com.nameless.volunteerproject.models.FormErorr;
import com.nameless.volunteerproject.models.FundraisingRequest;

import java.util.ArrayList;

public class FundraisingRequestValidator {
    public static ArrayList<FormErorr> IsValid(FundraisingRequest obj) {
        ArrayList<FormErorr> errors = new ArrayList<FormErorr>();

        if (obj.getMilitaryId() == null ) {
            errors.add(new FormErorr("MilitaryId","Empty field"));
        }

        if (obj.getRequestName() == null || obj.getRequestName().trim().isEmpty()) {
            errors.add(new FormErorr("RequestName","Empty field"));
        }

        if (obj.getWhom() == null || obj.getWhom().trim().isEmpty()) {
            errors.add(new FormErorr("Whom","Empty field"));
        }

        if (obj.getDescription() == null || obj.getDescription().trim().isEmpty()) {
            errors.add(new FormErorr("Description","Empty field"));
        }
        return errors;
    }
}
