package com.ggar.scenemanager.test1.service.login;

import javax.security.auth.login.CredentialException;
import java.util.LinkedHashMap;
import java.util.Map;

public class BasicLoginService implements LoginService {

	private final Map<String, String> credentials = new LinkedHashMap<String, String>(){{
		put("user1", "password1");
		put("user2", "password2");
	}};

	@Override
	public boolean isValid(String username, String password) throws CredentialException {
		return credentials.keySet().stream()
			.filter(e -> username.equals(e))
			.map(e -> credentials.get(e).equals(password))
			.findAny()
			.orElseThrow(CredentialException::new);
	}
}
