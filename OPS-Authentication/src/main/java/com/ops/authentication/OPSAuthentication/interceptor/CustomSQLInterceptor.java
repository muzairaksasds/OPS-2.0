//package com.ops.authentication.OPSAuthentication.interceptor;
//
//import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;
//import org.hibernate.resource.jdbc.spi.PhysicalConnectionHandlingMode;
//import org.hibernate.resource.jdbc.spi.StatementInspector;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class CustomSQLInterceptor implements StatementInspector {
//
//    private static final String SQL_LOG_FILE = "classpath:sql_log.txt";
//
//    @Override
//    public String inspect(String sql) {
//        if (sql.trim().startsWith("/*")) {
//            // Statements generated by Hibernate, use basic formatting
//        }
//
//        System.out.println("ppppppppppp" + sql);
//
//        try (PrintWriter writer = new PrintWriter(new FileWriter(SQL_LOG_FILE, true))) {
//            writer.println(sql);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return sql.trim();
//    }
//
////    @Override
////    public PhysicalConnectionHandlingMode getPhysicalConnectionHandlingMode() {
////        return PhysicalConnectionHandlingMode.DEFAULT;
////    }
//}