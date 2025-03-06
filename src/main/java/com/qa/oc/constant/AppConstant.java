package com.qa.oc.constant;

import java.util.Arrays;
import java.util.List;

public class AppConstant {
	
	public static final String LOGIN_PAGE_TITLE="Account Login";
	public static final String LOGIN_URL_FRACTION_VALUE = "route=account/login";
	public static final List<String> ACCOUNTS_PAGE_HEADERS_LIST = Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
	public static final String QA_CONFIG_FILE_PATH="./src/test/resources/config/qa.config.properties";
	public static final String DEV_CONFIG_FILE_PATH="./src/test/resources/config/dev.config.properties";
	public static final String STG_CONFIG_FILE_PATH="./src/test/resources/config/stg.config.properties";
	public static final String UAT_CONFIG_FILE_PATH="./src/test/resources/config/uat.config.properties";	

}
