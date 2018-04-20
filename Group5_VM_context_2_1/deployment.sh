#Installing Oracle JDK
echo "VM Setup - Installing JAVA JDK...."
mkdir /usr/lib/jvm
echo "VM Setup - Extracting JDK installation..."
tar -zxf /mnt/jdk-8u161-linux-x64.tar.gz -C /usr/lib/jvm/

echo "VM Setup - Changing system references to the just created Java directory..."
update-alternatives --install /usr/bin/java java /usr/lib/jvm/jdk1.8.0_161/bin/java 100
update-alternatives --install /usr/bin/javac javac /usr/lib/jvm/jdk1.8.0_161/bin/javac 100
export JAVA_HOME="/usr/lib/jvm/jdk1.8.0_161"

echo "VM Setup - Checking if Java was installed correctly (expected: build 1.8.0_161)..."
java -version

#Installing Apache Tomcat
echo "VM Setup - Installing Apache Tomcat"
groupadd tomcat
mkdir /opt/tomcat
useradd -g tomcat -d /opt/tomcat -s /bin/nologin tomcat

echo "VM Setup - Extracting and moving files to the target location..."
tar -zxvf /mnt/apache-tomcat-8.5.30.tar.gz
mv apache-tomcat-8.5.30/* /opt/tomcat/

chown -R tomcat:tomcat /opt/tomcat/

#Opening port 8080 for Tomcat
echo "VM Setup - Opening port (8080) to receive requests on the Apache Server..."
ufw allow 8080

#Starting Apache Tomcat
echo "VM Setup - Starting Apache Tomcat"
cd /opt/tomcat/bin/
sh startup.sh

#Deploying the application
echo "VM Setup - Deploying the Application"
mv /mnt/Restul-UrlShortner.war /opt/tomcat/webapps/Restul-UrlShortner.war
