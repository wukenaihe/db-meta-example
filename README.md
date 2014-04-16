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

<h3>Use without Spring</h3>

###
public class Test {
	
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
}
###
