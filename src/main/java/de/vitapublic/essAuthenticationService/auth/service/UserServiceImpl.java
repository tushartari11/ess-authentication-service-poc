package de.vitapublic.essAuthenticationService.auth.service;

import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import de.vitapublic.essAuthenticationService.auth.model.User;
import de.vitapublic.essAuthenticationService.config.RestConfig;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	RestConfig restConfig;

	@Override
	public void registerUser(User user) {

		Map<String, User> userMap = new HashMap<>();
		userMap.put("user", user);

		try {
			String restUrl = restConfig.getRegisterUrl();
			SSLContext sslContext = restConfig.createSSLContext();

			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

			HttpHeaders headers = restConfig.configureHttpHeaders();

			HttpEntity<Map<String, User>> entity = new HttpEntity<Map<String, User>>(userMap, headers);

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> status = restTemplate.postForEntity(restUrl, entity, String.class);
			System.out.println("status : " + status);

		} catch (RestClientException ex) {
			ex.printStackTrace();
			System.out.println("message : " + ex.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println("registered user :" + user);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	private Map<String, User> getUserMap(User inputUser) {
		Map<String, User> userMap = new HashMap<>();
		User user = new User();
		user.setUsername(inputUser.getUsername());
		user.setRealm(inputUser.getRealm());
		user.setPassword(inputUser.getPassword());
		user.setPasswordConfirm(inputUser.getPasswordConfirm());
		if (!CollectionUtils.isEmpty(inputUser.getClaims())) {
			user.setClaims(inputUser.getClaims());
		}
		if (!CollectionUtils.isEmpty(inputUser.getProperties())) {
			user.setProperties(inputUser.getProperties());
		}
		userMap.put("user", user);
		return userMap;
	}
	
}
