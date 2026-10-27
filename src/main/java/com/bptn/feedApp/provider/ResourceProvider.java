package com.bptn.feedApp.provider;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import com.bptn.feedApp.provider.factory.YamlPropertySourceFactory;
import org.springframework.beans.factory.annotation.Value;

@Component
@PropertySource(value = "classpath:config.yml", factory = YamlPropertySourceFactory.class)
public class ResourceProvider {
		
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	@Value("${jwt.expiration}")
	private long jwtExpiration;
	
	@Value("${jwt.issuer}")
	private String jwtIssuer;
	    
	@Value("${jwt.audience}")
	private String jwtAudience;
	
	@Value("${jwt.prefix}")
	private String jwtPrefix;
	
	@Value("${jwt.excluded.urls}")
	private String[] jwtExcludedUrls;
	
	@Value("${client.url}")
	private String clientUrl;
	    
	@Value("${client.email.verify.param}")
	private String clientVerifyParam;
	
	@Value("${client.email.verify.expiration}")
	private long clientVerifyExpiration;
	    
	@Value("${client.email.reset.param}")
	private String clientResetParam;
	
	@Value("${client.email.reset.expiration}")
	private long clientResetExpiration;
	
	@Value("${h2.server.params}")
	private String[] h2ServerParams;
	
	public String getJwtSecret() {
		return jwtSecret;
	}
	
	public void setJwtSecret(String jwtSecret) {
		this.jwtSecret = jwtSecret;
	}
	
	public long getJwtExpiration() {
		return jwtExpiration;
	}
	
	public void setJwtExpiration(long jwtExpiration) {
		this.jwtExpiration = jwtExpiration;
	}
	
	public String getJwtIssuer() {
		return jwtIssuer;
	}
	
	public void setJwtIssuer(String jwtIssuer) {
		this.jwtIssuer = jwtIssuer;
	}
	
	public String getJwtAudience() {
		return jwtAudience;
	}
	
	public void setJwtAudience(String jwtAudience) {
		this.jwtAudience = jwtAudience;
	}
	
	public String getJwtPrefix() {
		return jwtPrefix;
	}
	
	public void setJwtPrefix(String jwtPrefix) {
		this.jwtPrefix = jwtPrefix;
	}
	
	public String[] getJwtExcludedUrls() {
		return jwtExcludedUrls;
	}
	
	public void setJwtExcludedUrls(String[] jwtExcludedUrls) {
		this.jwtExcludedUrls = jwtExcludedUrls;
	}
	
	public String getClientUrl() {
		return clientUrl;
	}
	
	public void setClientUrl(String clientUrl) {
		this.clientUrl = clientUrl;
	}
	
	public String getClientVerifyParam() {
		return clientVerifyParam;
	}
	
	public void setClientVerifyParam(String clientVerifyParam) {
		this.clientVerifyParam = clientVerifyParam;
	}
	
	public long getClientVerifyExpiration() {
		return clientVerifyExpiration;
	}
	
	public void setClientVerifyExpiration(long clientVerifyExpiration) {
		this.clientVerifyExpiration = clientVerifyExpiration;
	}
	
	public String getClientResetParam() {
		return clientResetParam;
	}
	
	public void setClientResetParam(String clientResetParam) {
		this.clientResetParam = clientResetParam;
	}
	
	public long getClientResetExpiration() {
		return clientResetExpiration;
	}
	
	public void setClientResetExpiration(long clientResetExpiration) {
		this.clientResetExpiration = clientResetExpiration;
	}
	
	public String[] getH2ServerParams() {
		return h2ServerParams;
	}
	
	public void setH2ServerParams(String[] h2ServerParams) {
		this.h2ServerParams = h2ServerParams;
	}
	

}
