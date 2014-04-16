package com.cgs.db;

import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cgs.db.meta.core.MetaLoader;
import com.cgs.db.meta.schema.Schema;
import com.cgs.db.meta.schema.Table;
import com.cgs.db.util.PrintUtils;
import com.cgs.db.util.Utility;

public class SpringTest {
	
	public static void main(String[] args) {
		 ApplicationContext context = 
	             new ClassPathXmlApplicationContext("config.xml");
		 MetaLoader metaLoader=(MetaLoader) context.getBean("metaLoader");
		 
		 Set<String> tableNames=metaLoader.getTableNames();
		 System.out.println(tableNames);
		 
		 Schema schema=metaLoader.getSchema();
		 Map<String,Table> tables=schema.getTables();
		 
		 Set<String> keys=tables.keySet();
		 for (String string : keys) {
			Table t=tables.get(string);
			PrintUtils.printTable(t);
		}
		 
		 
		 
	}
}
