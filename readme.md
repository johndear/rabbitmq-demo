# RabbitMQ交换机Exchange类型介绍
	http://blog.csdn.net/rainday0310/article/details/22082503

生产者与消费者对应关系：
	Direct Exchange(一对一)： 
	处理路由键。发送到与该交换机绑定的精确匹配队列: Channel.queueBind(queue, exchange, routingKey)
	
	Fanout Exchange(一对多)：
 	不处理路由键。发送到与该交换机绑定的所有队列: Channel.queueBind(queue, exchange, null)
	<img src="./xxx.png" width = "300" height = "200" alt="图片名称" align=center />
	
	Topic Exchange(一对一/一对多)： 
	处理路由键。发送到与该交换机绑定的模式匹配队列: Channel.queueBind(queue, exchange, routingKey)
	
	Headers Exchange(还没有仔细研究)：
	不同于上面三种Exchange，它是根据Message的一些头部信息来分发过滤Message，忽略routing key的属性，如果Queue中Header信息和message消息的头信息相匹配，那么这条消息就匹配上了。
