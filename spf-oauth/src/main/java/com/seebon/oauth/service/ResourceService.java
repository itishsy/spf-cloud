package com.seebon.oauth.service;

import java.util.List;

public interface ResourceService {

    List<String> findResourceByUsername(String username);

    List<String> findResourceByRole(String role);

}
