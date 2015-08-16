package com.ethercamp.feedreporter.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class FeedController {


    @RequestMapping(value = "/vmtrace/{txHash}/compressed", method = GET)
    @ResponseBody
    public String getCompressedVMTrace(@PathVariable String txHash) {
        return null;
    }



}
