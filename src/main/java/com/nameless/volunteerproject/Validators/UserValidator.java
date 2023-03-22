package com.nameless.volunteerproject.Validators;

import com.nameless.volunteerproject.models.FormErorr;
import com.nameless.volunteerproject.models.User;

import java.util.ArrayList;

public class UserValidator {
    public static ArrayList<FormErorr> IsValid(User obj) {
        ArrayList<FormErorr> errors = new ArrayList<FormErorr>();

        if (obj.getRole() == null) {
            errors.add(new FormErorr("Role", "Empty field"));
        }

        if (obj.getSurname() == null || obj.getSurname().trim().isEmpty()) {
            errors.add(new FormErorr("Surname", "Empty field"));
        }

        if (obj.getName() == null || obj.getName().trim().isEmpty()) {
            errors.add(new FormErorr("Name", "Empty field"));
        }

        if (obj.getFatherName() == null || obj.getFatherName().trim().isEmpty()) {
            errors.add(new FormErorr("FatherName", "Empty field"));
        }

        if (obj.getEmail() == null || obj.getEmail().trim().isEmpty()) {
            errors.add(new FormErorr("Email", "Empty field"));
        }

        if (obj.getPhoneNumber() == null || obj.getPhoneNumber().trim().isEmpty()) {
            errors.add(new FormErorr("PhoneNumber", "Empty field"));
        }

        if (obj.getUserName() == null || obj.getUserName().trim().isEmpty()) {
            errors.add(new FormErorr("UserName", "Empty field"));
        }

        if (obj.getPassword() == null || obj.getPassword().trim().isEmpty()) {
            errors.add(new FormErorr("Password", "Empty field"));
        }
        if (!obj.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))
        {
            errors.add(new FormErorr("Email", "Not valid email"));
        }
        if (!obj.getPhoneNumber().matches("^(?:0|\\(?\\+?380\\)?\\s?)[67]\\d{8}$"))
        {
            errors.add(new FormErorr("PhoneNumber", "Not valid Phone Number"));
        }

        return errors;
    }
}
