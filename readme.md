# RabbitMQ交换机Exchange类型介绍
	http://blog.csdn.net/rainday0310/article/details/22082503

## 生产者与消费者对应关系：
* Default Exchange
  这种是特殊的Direct Exchange，是rabbitmq内部默认的一个交换机。该交换机的name是空字符串，所有queue都默认binding 到该交换机上。所有binding到该交换机上的queue，routing-key都和queue的name一样。  
  
* Direct Exchange(一对一)：    
  处理路由键。发送到与该交换机绑定的精确匹配队列: Channel.queueBind(queue, exchange, routingKey)  
  <img src="http://dl.iteye.com/upload/attachment/264104/0ec0f465-49c6-361c-ae2b-dd951a6ed1a9.png" width = "450" height = "270" alt="图片名称" align=center />  
  
* Fanout Exchange(一对多)：  
  扇形交换机，不处理路由键。发送到与该交换机绑定的所有队列: Channel.queueBind(queue, exchange, null)    
  <img src="http://dl.iteye.com/upload/attachment/264106/0bbdcd3d-9fc6-3107-b7e0-db67c174d46a.png" width = "450" height = "270" alt="图片名称" align=center />  
  
* Topic Exchange(一对一/一对多)：   
  通配符交换机，处理路由键，发送到与该交换机绑定的模式匹配队列: Channel.queueBind(queue, exchange, routingKey) 
  exchange会把消息发送到一个或者多个满足通配符规则的routing-key的queue。其中_ 表号匹配一个word，#匹配多个word和路径，路径之间通过.隔开。如：
  满足a._.c的routing-key有a.hello.c；
  满足#.hello的routing-key有a.b.c.helo。
  <img src="http://dl.iteye.com/upload/attachment/264108/11171ab4-af07-3ff6-bdf6-d1febda679c3.png" width = "450" height = "270" alt="图片名称" align=center />  
  
* Headers Exchange(还没有仔细研究)：  
  设置header attribute参数类型的交换机。
  不同于上面三种Exchange，它是根据Message的一些头部信息来分发过滤Message，忽略routing key的属性，如果Queue中Header信息和message消息的头信息相匹配，那么这条消息就匹配上了。

