+### prerequisites
+The following items should be installed in your system:
+* Maven 3 (http://www.sonatype.com/books/mvnref-book/reference/installation.html)
+* git command line tool (https://help.github.com/articles/set-up-git)
+* Eclipse with the m2e plugin (m2e is installed by default when using the STS (http://www.springsource.org/sts) distribution of Eclipse)
+
+Note: when m2e is available, there is an m2 icon in Help -> About dialog.
+If m2e is not there, just follow the install process here: http://eclipse.org/m2e/download/
+
+```
+1) Inside Eclipse
+```

File --> New --> Spring Legacy --> Simple Spring Web Maven

+File -> Import -> Maven -> Existing Maven project

<mvc:resources mapping="/resources/**"
               location="/, classpath:/WEB-INF/public-resources/"
               cache-period="10000" />
Put the resources under: src/main/webapp/images/logo.png and then access them via /resources/images/logo.png.

In the war they will be then located at images/logo.png. 
So the first location (/) form mvc:resources will pick them up.

The second location (classpath:/WEB-INF/public-resources/) in mvc:resources 
(looks like you used some roo based template) can be to expose resources 
(for example js-files) form jars, if they are located in the directory WEB-INF/public-resources in the jar.
