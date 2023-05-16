package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ResolvableType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private static String authorizationRequestBaseUri = "oauth2/authorization";
    Map<String, String> oauth2AuthenticationUrls = new HashMap<>();
    private final ClientRegistrationRepository clientRegistrationRepository;

    private final UserService userService;

    @GetMapping("/oauth/login/{userId}")
    public String getLoginPage(@PathVariable UUID userId, Model model) {
        Iterable<ClientRegistration> clientRegistrations = null;
        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository)
                .as(Iterable.class);
        if (type != ResolvableType.NONE &&
                ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
        }

        clientRegistrations.forEach(registration ->
                oauth2AuthenticationUrls.put(registration.getClientName(),
                        authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
        model.addAttribute("urls", oauth2AuthenticationUrls);

        User user = userService.findUserById(userId);
        model.addAttribute("user", user);
        System.out.println(user.toString());
        return "login";
    }
}