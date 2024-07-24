package com.banking.UserService.config;//package com.example.ApiGateway.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.regex.Pattern;
//
//import static java.util.Arrays.asList;
//import static java.util.Collections.singletonList;
//import static java.util.stream.Collectors.toList;
//import static org.springframework.http.HttpMethod.*;
//
//@Configuration
//public class WebSecurityConfig extends CorsConfiguration {
//
//    private static final List<String> DEFAULT_ALLOWED_METHODS = asList(
//            GET, POST, PUT, PATCH, DELETE).parallelStream()
//            .map(Enum::name)
//            .collect(toList());
//
//    private static final List<String> DEFAULT_ALLOWED_HEADERS = singletonList(ALL);
//    private final List<Pattern> allowedOriginsRegex = new ArrayList<>();
//
//    public WebSecurityConfig() {
//        setDefault();
//    }
//
//    public WebSecurityConfig(CorsConfiguration other) {
//        super(other);
//        addDefaultOrigins();
//    }
//
//    private void setDefault() {
//        addDefaultOrigins();
//        setAllowedMethods(DEFAULT_ALLOWED_METHODS);
//        setAllowedHeaders(DEFAULT_ALLOWED_HEADERS);
//        setAllowCredentials(true);
//    }
//
//    private void addDefaultOrigins() {
//        addAllowedOrigin("^http://localhost(?::\\d{1,5})$)");
//    }
//
//    public  void addAllowedOrigin(String origin) {
//        super.addAllowedOrigin(origin);
//        allowedOriginsRegex.add(Pattern.compile(origin));
//    }
//
//    public  String checkOrigin(String requestOrigin){
//        String result = super.checkOrigin(requestOrigin);
//        return result != null ? result : checkOriginWithRegularExpression(requestOrigin);
//    }
//
//    private String checkOriginWithRegularExpression(String requestOrigin){
//        return allowedOriginsRegex.parallelStream()
//                .filter(pattern -> pattern.matcher(requestOrigin).matches())
//                .map(pattern -> requestOrigin)
//                .findFirst()
//                .orElse(null);
//    }
//
//    @Override
//    public CorsConfiguration combine(CorsConfiguration other) {
//        return new WebSecurityConfig(super.combine(other));
//    }
//}