package com.cgs.db;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import com.cgs.db.meta.core.MetaLoader;
import com.cgs.db.meta.core.MetaLoaderImpl;
import com.cgs.db.meta.core.SchemaInfoLevel;
import com.cgs.db.meta.schema.Procedure;
import com.cgs.db.meta.schema.Schema;
import com.cgs.db.meta.schema.SchemaInfo;
import com.cgs.db.meta.schema.Table;
import com.cgs.db.util.PrintUtils;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Test {
	
	public static void main(String[] args) {
		MysqlDataSource datasource = new MysqlDataSource();
		datasource.setServerName("localhost");
		datasource.setPort(3306);
		datasource.setDatabaseName("dctest");
		datasource.setUser("root");
		datasource.setPassword("123456");
		
		MetaLoader metaLoader=new MetaLoaderImpl(datasource);
		Set<String> tableNames=metaLoader.getTableNames();
		System.out.println(tableNames);
		
		
		Table table=metaLoader.getTable("des");
		PrintUtils.printTable(table);
		
		 Map<String, Procedure> procedures=metaLoader.getProcedures();
//		 System.out.println(procedures);
	}
}
