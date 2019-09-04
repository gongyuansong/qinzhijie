package com.qzj;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@MapperScan("com.qzj.dao")
@SpringBootApplication(scanBasePackages = { "com.qzj.*", "com.qzj.common.aop" })
public class QinZhiJieStarter {
	@Value("${system-params.port}")
	private int prot;

	@Value("${server.port}")
	private int serverprot;

	public static void main(String[] args) {
		SpringApplication.run(QinZhiJieStarter.class, args);
	}

	/**HTTP跳转HTTPS **/
/*	 @Bean public Connector connector() { Connector connector = new
	 Connector("org.apache.coyote.http11.Http11NioProtocol");
	 connector.setScheme("http"); connector.setPort(prot);
	 connector.setSecure(false); connector.setRedirectPort(serverprot); return
	 connector; }
	 
	 @Bean public TomcatServletWebServerFactory
	 tomcatServletWebServerFactory(Connector connector) {
	 TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
	 
	 @Override protected void postProcessContext(Context context) {
	 SecurityConstraint securityConstraint = new SecurityConstraint();
	 securityConstraint.setUserConstraint("CONFIDENTIAL"); SecurityCollection
	 collection = new SecurityCollection(); collection.addPattern("/*");
	 securityConstraint.addCollection(collection);
	 context.addConstraint(securityConstraint); } };
	 tomcat.addAdditionalTomcatConnectors(connector); return tomcat; }*/
}
