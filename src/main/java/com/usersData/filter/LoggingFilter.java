package com.usersData.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    Logger logger = Logger.getLogger(String.valueOf(LoggingFilter.class));
    FileHandler fh;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper contentCachingResponseWrapper = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();

        filterChain.doFilter(contentCachingRequestWrapper, contentCachingResponseWrapper);

        long timeTaken = System.currentTimeMillis() - startTime;

//        String requestBody = getStringValue(contentCachingRequestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
        String responseBody = getStringValue(contentCachingResponseWrapper.getContentAsByteArray(), response.getCharacterEncoding());

        try {

            // This block configure the logger with handler and formatter
            fh = new FileHandler("C:/Projektek/jsondb/logfile.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            logger.log(Level.INFO,"Method: {0}",request.getMethod());
            logger.log(Level.INFO,"RequestURI: {0}",request.getRequestURI());
            logger.log(Level.INFO,"Response Code: {0}",response.getStatus());
            logger.log(Level.INFO,"Response Body: {0}",responseBody);
            logger.log(Level.INFO,"Time Taken: {0}",timeTaken);

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }

        contentCachingResponseWrapper.copyBodyToResponse();


    }

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
        try {
            return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
