#!/bin/sh


# Project root dir. You should change to this dir to run this script
PROJ_DIR=/usr/<more path>/testprojects/BrightPlan/BrightPlan_B/
export PROJ_DIR

# Project log dir
LOG_DIR=$PROJ_DIR/testing/log/
CONF_DIR=$PROJ_DIR/testing/config/
OUTPUT_DIR=$PROJ_DIR/testing/output/
INPUT_DIR=$PROJ_DIR/testing/input/
LOG_FILE=$LOG_DIR/Log_BrightPlan_B_`date +%Y%m%d%H%M%S`.log
REPORT_FILE=$OUTPUT_DIR/Rpt_BrightPlan_B_`date +%Y%m%d%H%M%S`.html

# uncomment line below if you want to use your location of chromedriver
# DRIVER_LOCATION_PARAM="-Dselenium.driver.location=/usr/<more path>/chromedriver"
DRIVER_LOCATION_PARAM=""

# Set classpath
CP=bin:\
lib/cglib-nodep-3.2.4.jar:\
lib/commons-codec-1.10.jar:\
lib/commons-exec-1.3.jar:\
lib/commons-io-2.5.jar:\
lib/commons-lang3-3.5.jar:\
lib/commons-logging-1.2.jar:\
lib/cssparser-0.9.21.jar:\
lib/gson-2.8.0.jar:\
lib/guava-21.0.jar:\
lib/hamcrest-core-1.3.jar:\
lib/hamcrest-library-1.3.jar:\
lib/htmlunit-2.24.jar:\
lib/htmlunit-core-js-2.23.jar:\
lib/htmlunit-driver-2.24.jar:\
lib/httpclient-4.5.2.jar:\
lib/httpcore-4.4.4.jar:\
lib/httpmime-4.5.2.jar:\
lib/javax.servlet-api-3.1.0.jar:\
lib/jetty-io-9.4.1.v20170120.jar:\
lib/jetty-util-9.4.1.v20170120.jar:\
lib/jna-4.1.0.jar:\
lib/jna-platform-4.1.0.jar:\
lib/junit-4.12.jar:\
lib/neko-htmlunit-2.24.jar:\
lib/phantomjsdriver-1.4.0.jar:\
lib/sac-1.3.jar:\
lib/serializer-2.7.2.jar:\
lib/websocket-api-9.2.20.v20161216.jar:\
lib/websocket-client-9.2.20.v20161216.jar:\
lib/websocket-common-9.2.20.v20161216.jar:\
lib/xalan-2.7.2.jar:\
lib/xercesImpl-2.11.0.jar:\
lib/xml-apis-1.4.01.jar:\
lib/client-combined-3.3.1-nodeps.jar

export CP

# Assuming Java is in your system path

java -cp $CP \
-DconfigFile=$CONF_DIR/BPlan_RunInfo.properties \
-DUseInputFilePath=$INPUT_DIR \
-DUseOutputFilePath=$OUTPUT_DIR \
-DUseErrorFilePath==$OUTPUT_DIR $DRIVER_LOCATION_PARAM \
org.junit.runner.JUnitCore com.brightplan.automation.projects.yelp.YELP001_DisplaySearchDetails_JUnit > $LOG_FILE 

