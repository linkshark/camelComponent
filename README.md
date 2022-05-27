# camelComponent

camel自研组件
扩展JDBC组件 实现SPLIT,分组查询,输出结构等功能操作

1.实现jdbc子查询,关联查询的功能,示例demo为

        from("jetty:http://0.0.0.0:8888/s").id("selfRoute")
                .autoStartup(true).convertBodyTo(String.class, "UTF-8")
                .to("sharkjdbc:{\"queryName\": \"father\",\"dataSourceId\": \"datasource\",\"sql\": \"select * from test where id = ${body}\",\"child\": [{\"queryName\": \"son\",\"dataSourceId\": \"datasource\",\"sql\": \"select * from grade where name = ${parent.name}\",\"child\": [{\"queryName\": \"grandSon\",\"dataSourceId\": \"datasource\",\"sql\": \"select * from grade where name = ${parent.name}\"}]}]}")
                .to("log:ss");
                
               
              
