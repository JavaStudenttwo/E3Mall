# 一.项目简介
宜立方商城是一个综合性的B2C平台，架构模仿京东商城、天猫商城，该平台主要提供两种服务：一，买家可以在商城浏览商品、下订单，以及参加各种活动。二，管理员，运营可以在平台后台管理系统中管理商品、订单、会员等
宜立方商城项目使用Java的SSM框架处理后端业务，同时使用了Dubbo框架，是一个分布式支持高并发的商城项目。数据库选择MySQL集群，缓存使用Redis集群，使用Solr框架实现商品搜索
项目GitHub地址：
# 二.项目架构及涉及的主要技术
## 项目架构
如下图所示，整个项目（e3-parent）由多个子项目（e3-manager等）聚合而成，每个子项目各是一个独立可部署的JavaWeb项目（可以放到Tomcat中运行），各项目通过Dubbo实现项目间服务调用，使得整个项目可以实现分布式部署。这种架构是分布式Java项目常采用的架构模型
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181228200929662.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2VhZ2xldW5pdmVyc2l0eWV5ZQ==,size_16,color_FFFFFF,t_70)
下图是商城运作的逻辑模型，整个项目分为三个层次，表现层，服务层和持久层，每个层次各有几个工程来实现相关功能。表现层主要负责展示服务层提供的信息，包括图片，数据等，服务层则主要负责处理业务逻辑，持久层负责存储数据。其中，需要程序员关注的主要是表现层和服务层。
表现层分为五个系统，每个系统各实现不同的功能，为各个系统分别建立一个子项目来实现其功能。如商城门户系统，对应上图的e3-portal-web项目，主要负责商城页面的展示；搜索系统，对应上图的e3-search-web项目，主要负责前台搜索数据的提起及搜索结果的展示。服务层各系统同理，各对应一个子项目。
部署时每个子项目放到一个服务器中运行，这种部署方式就是分布式部署。
![在这里插入图片描述](https://img-blog.csdnimg.cn/2018122819595899.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2VhZ2xldW5pdmVyc2l0eWV5ZQ==,size_16,color_FFFFFF,t_70)
## 项目使用的主要技术

 - Dubbo：实现项目间服务调用，是分布式项目必须使用的框架，和它实现同一功能的另一框架是SpringCloud
 - Solr：使用Luncene作为内核的搜索框架
 - Redis：非关系型数据库，在该项目中用于商品数据缓存
 - SSM框架：处理业务逻辑
 - MyCat：存储数据
 - Nginx：负载均衡处理
# 三.项目功能
## 后台管理系统
