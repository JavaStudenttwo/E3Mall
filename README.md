# 一.项目简介
宜立方商城是一个综合性的B2C平台，架构模仿京东商城、天猫商城，该平台主要提供两种服务：一，买家可以在商城浏览商品、下订单，以及参加各种活动。二，管理员，运营可以在平台后台管理系统中管理商品、订单、会员等
宜立方商城项目使用Java的SSM框架处理后端业务，同时使用了Dubbo框架，是一个分布式支持高并发的商城项目。数据库选择MySQL集群，缓存使用Redis集群，使用Solr框架实现商品搜索
项目GitHub地址：https://github.com/JavaStudenttwo/E3Mall
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
## 1.后台管理
给管理员使用的商品管理系统，前端使用EasyUI框架，简化开发
后台管理主要涉及表现层的后台管理系统及服务层的后台管理服务，分别对应子项目e3-manager和e3-manager-web，e3-manager-web项目中包含了后台管理系统的页面，和Controller层，e3-manager项目包含了service层和dao层及其他库文件，e3-manager-web中的Controller层调用e3-manager中的服务来处理后台管理中的业务逻辑
后台管理系统页面：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181228215400664.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2VhZ2xldW5pdmVyc2l0eWV5ZQ==,size_16,color_FFFFFF,t_70)
整个商城所有商品的数据查询显示：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181228221353637.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2VhZ2xldW5pdmVyc2l0eWV5ZQ==,size_16,color_FFFFFF,t_70)
商品添加：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181228221403264.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2VhZ2xldW5pdmVyc2l0eWV5ZQ==,size_16,color_FFFFFF,t_70)
商品类别查询：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181228221432707.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2VhZ2xldW5pdmVyc2l0eWV5ZQ==,size_16,color_FFFFFF,t_70)
商品类别管理：添加删除修改商品类别
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181228221418474.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2VhZ2xldW5pdmVyc2l0eWV5ZQ==,size_16,color_FFFFFF,t_70)
## 2.商城门户
展示商城首页，商品首页包含各种商品的图片信息及特殊活动（如双十一）入口
主要由两个子项目实现e3-portal-web和e2-content，e3-content商品内容工程要将所有首页需要展示（并非所有商品）的商品缓存到Redis数据库中，提高首页打开的速度

![在这里插入图片描述](https://img-blog.csdnimg.cn/20181228222440769.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2VhZ2xldW5pdmVyc2l0eWV5ZQ==,size_16,color_FFFFFF,t_70)
## 3.商品搜索
根据关键字搜索并展示商品
先由e3-search子项目使用Solr框架，完成对所有商品的信息提取，建立一个Solr索引库，然后由e3-search-web子项目根据关键字查询相关数据并展示商品详情页面
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181228225325823.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2VhZ2xldW5pdmVyc2l0eWV5ZQ==,size_16,color_FFFFFF,t_70)
