package org.example.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @GetMapping("/")
    @ResponseBody
    public String getIndex() {
        return """
                <html>
                <head>
                    <title>Start page</title>
                    <link href="/resources/style.css" rel="stylesheet">
                </head>
                <body>
                    <h1>Start page</h1>
                    <p>Content.</p>
                </body></html>""";
    }

    @GetMapping("/other")
    @ResponseBody
    public String getOther() {
        return """
                <html>
                <head>
                    <title>Other page</title>
                    <link href="/resources/style.css" rel="stylesheet">
                </head>
                <body>
                    <h1>Other page</h1>
                    <p>Content.</p>
                </body></html>""";
    }

}
