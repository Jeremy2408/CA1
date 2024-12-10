package com.testproject.hello;

import org.springframework.web.bind.annotation.*;

import com.testproject.ParserJSON;
import com.testproject.ParserXML;

@RestController
@RequestMapping("/parse")
public class ParserController {

    @PostMapping("/xml")
    public String parseXML() {
        try {
            ParserXML.main(new String[]{});
            return "XML parsed successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to parse XML.";
        }
    }

    @PostMapping("/json")
    public String parseJSON() {
        try {
            ParserJSON.main(new String[]{});
            return "JSON parsed successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to parse JSON.";
        }
    }
}
