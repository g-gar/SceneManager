package com.ggar.scenemanager.test1.service.login;

import javax.security.auth.login.CredentialException;

public interface LoginService {

	boolean isValid(String username, String password) throws CredentialException;

}
