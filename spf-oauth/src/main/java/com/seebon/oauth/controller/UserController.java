package com.seebon.oauth.controller;

import com.seebon.oauth.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String hello() {
        return "hello,oauth";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Principal user(Principal principal) {
        return principal;
    }

    @GetMapping(value = "/resource/findByUser/{username}")
    public List<String> findResourcesByUsername(@PathVariable("username") String username) {
        return resourceService.findResourceByUsername(username);
    }

    @GetMapping(value = "/resource/findByRole/{role}")
    public List<String> findResourcesByRole(@PathVariable("role") String role) {
        return resourceService.findResourceByRole(role);
    }
}