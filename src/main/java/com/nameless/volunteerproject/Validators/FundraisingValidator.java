package com.nameless.volunteerproject.Validators;
import com.nameless.volunteerproject.models.FormErorr;
import com.nameless.volunteerproject.models.Fundraising;

import java.util.ArrayList;

public class FundraisingValidator {

    public static ArrayList<FormErorr> IsValid(Fundraising obj) {
        ArrayList<FormErorr> errors = new ArrayList<FormErorr>();

        if (obj.getWhom() == null || obj.getWhom().trim().isEmpty()) {
            errors.add(new FormErorr("Whom","Empty field"));
        }
        if(obj.getSum() == null)
        {
            errors.add(new FormErorr("Sum","Empty field"));
        }
        if ( obj.getSum() <= 0) {
            errors.add(new FormErorr("Whom","Non positive sum"));
        }
        if (obj.getFundraisingName() == null || obj.getFundraisingName().trim().isEmpty()) {
            errors.add(new FormErorr("FundraisingName","Empty field "));
        }

        if (obj.getCardNumber() == null || obj.getCardNumber().trim().isEmpty() ) {
            errors.add(new FormErorr("CardNumber","Empty field "));

        }
        if(obj.getCardNumber().matches("\\d{16,19}")) {
            errors.add(new FormErorr("CardNumber","Not Valid CardNumber "));

        }
        if (obj.getDescription() == null || obj.getDescription().trim().isEmpty()) {
            errors.add(new FormErorr("CardNumber","Empty field "));
        }

        if (obj.getSocialType() == null) {
            errors.add(new FormErorr("SocialType","Empty field "));
        }
        return errors;
    }
}
