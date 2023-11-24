package com.micro.training.msagreement.rest;

import com.micro.training.msagreement.rest.models.Person;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/play")
public class PlaygroundController {

    // @RequestMapping(method = RequestMethod.GET,path = "/test1")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/test1")
    public String test1() {
        return "Test 1 OK";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/test2")
    public String test2() {
        return "Test 2 OK";
    }

    // /play/test2/osman/static/ali
    @GetMapping("/test2/{abc}/static/{xyz}")
    public String test2(@PathVariable("abc") String testData1,
                        @PathVariable("xyz") String testData2) {
        return "Test 2 OK : " + testData1 + " " + testData2;
    }

    // /play/test3?abc=osman&xyz=ali
    @GetMapping("/test3")
    public String test3(@RequestParam("abc") String testData1,
                        @RequestParam("xyz") String testData2) {
        return "Test 3 OK : " + testData1 + " " + testData2;
    }

    // /play/test4/osman?sur=yaycioglu
    @GetMapping("/test4/{name}")
    public String test4(@PathVariable String name,
                        @RequestParam String sur) {
        return "Test 4 OK : " + name + " " + sur;
    }

    // /play/test5/osman;sur=ali
    @GetMapping("/test5/{name}")
    public String test5(@PathVariable String name,
                        @MatrixVariable String sur) {
        return "Test 5 OK : " + name + " " + sur;
    }

    @PostMapping("/test6")
    public String test6(@RequestBody Person personParam) {
        return "Test 5 OK : " + personParam;
    }


}
