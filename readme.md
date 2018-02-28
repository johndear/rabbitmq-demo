# RabbitMQ交换机Exchange类型介绍
	http://blog.csdn.net/rainday0310/article/details/22082503

## 生产者与消费者对应关系：
* Direct Exchange(一对一)：    
  处理路由键。发送到与该交换机绑定的精确匹配队列: Channel.queueBind(queue, exchange, routingKey)  
  <img src="http://dl.iteye.com/upload/attachment/264104/0ec0f465-49c6-361c-ae2b-dd951a6ed1a9.png" width = "450" height = "270" alt="图片名称" align=center />  
  
* Fanout Exchange(一对多)：  
  不处理路由键。发送到与该交换机绑定的所有队列: Channel.queueBind(queue, exchange, null)    
  <img src="http://dl.iteye.com/upload/attachment/264106/0bbdcd3d-9fc6-3107-b7e0-db67c174d46a.png" width = "450" height = "270" alt="图片名称" align=center />  
  
* Topic Exchange(一对一/一对多)：   
  处理路由键。发送到与该交换机绑定的模式匹配队列: Channel.queueBind(queue, exchange, routingKey)  
  <img src="http://dl.iteye.com/upload/attachment/264108/11171ab4-af07-3ff6-bdf6-d1febda679c3.png" width = "450" height = "270" alt="图片名称" align=center />  
  
* Headers Exchange(还没有仔细研究)：  
  不同于上面三种Exchange，它是根据Message的一些头部信息来分发过滤Message，忽略routing key的属性，如果Queue中Header信息和message消息的头信息相匹配，那么这条消息就匹配上了。

