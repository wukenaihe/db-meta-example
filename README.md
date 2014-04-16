db-meta-example
===============
Relational database metadata crawl library

The following databases are currently supported:
<ul>
  <li>Mysql</li>
  <li>Oracle</ii>
  <li>Sql server</li>
</ul>

Dependency
==============
<ul>
  <li><b>logback-classic</b></li>
  <li>ojdbc6(option)</ii>
  <li>mysql-connector-java(option)</ii>
  <li>jtds(option)</ii>
  <li>c3p0(option)</ii>
  <li>...</ii>
</ul>


Use with Maven
==============
Pom.xml
```xml
<repositories>
		<repository>
			<id>clojars</id>
			<name>Clojars repository</name>
			<url>https://clojars.org/repo</url>
		</repository>
</repositories>

<dependencies>
		<dependency>
			<groupId>org.clojars.xumh</groupId>
			<artifactId>db-meta</artifactId>
			<version>0.0.1-Release</version>
		</dependency>
</dependencies>
```
You can also download the jar and install it by yourself. [here](https://github.com/wukenaihe/db-meta/tree/db-meta-repo)


Example Usage
==============
<h3>Use without Spring</h3>

###
	public static void main(String[] args) {
	  //create a datasource
		MysqlDataSource datasource = new MysqlDataSource();
		datasource.setServerName("localhost");
		datasource.setPort(3306);
		datasource.setDatabaseName("dctest");
		datasource.setUser("root");
		datasource.setPassword("123456");
		
		//instance the metaloader
		MetaLoader metaLoader=new MetaLoaderImpl(datasource);
		Set<String> tableNames=metaLoader.getTableNames();
		System.out.println(tableNames);
		
		//get a table
		Table table=metaLoader.getTable("des");
		PrintUtils.printTable(table);
		
		//get procedures
		 Map<String, Procedure> procedures=metaLoader.getProcedures();
	}
###


<h3>Use with Spring</h3>

sprint.xml

```xml
<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"
		destroy-method="close">
		<!--mysql com.mysql.jdbc.Driver jdbc:mysql://172.18.110.3:3306/dcmeta -->
		<!--oracle oracle.jdbc.driver.OracleDriver jdbc:oracle:thin:@192.168.2.11:1521:ORCL -->
		<!-- sql server net.sourceforge.jtds.jdbc.Driver jdbc:jtds:sqlserver://127.0.0.1:1433;databaseName=zhgs -->
		<property name="driverClassName">
			<value>com.mysql.jdbc.Driver</value>
		</property>
		<property name="url">
			<value>jdbc:mysql://172.18.110.3:3306/dxmeta</value>
		</property>
		<property name="username">
			<value>root</value>
		</property>
		<property name="password">
			<value>123456</value>
		</property>
		<property name="initialSize">
			<value>0</value>
		</property>
		<property name="maxActive">
			<value>4</value>
		</property>
		<property name="maxIdle">
			<value>4</value>
		</property>
	</bean>
	<bean id="metaLoader" class="com.cgs.db.meta.core.MetaLoaderImpl">
		<property name="dataSource" ref="dataSource"></property>
	</bean>
```

SprintTest.java

###
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		MetaLoader metaLoader = (MetaLoader) context.getBean("metaLoader");

		Set<String> tableNames = metaLoader.getTableNames();
		System.out.println(tableNames);

		Schema schema = metaLoader.getSchema();
		Map<String, Table> tables = schema.getTables();

		Set<String> keys = tables.keySet();
		for (String string : keys) {
			Table t = tables.get(string);
			PrintUtils.printTable(t);
		}
	}
###
