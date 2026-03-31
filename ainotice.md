# **AI使用与个人贡献说明**
项目：宿舍报修管理系统
AI使用点（技术栈）：
后端：**JAVA IDEA,Spring boot,Mybatis-plus,JWT鉴权协议**
前端：**Vue3,vite,vue-router,axios(可能会有漏补充)**

使用范围：
1. **项目搭建：Maven搭建配置，完成mybatis-plus框架搭建；对题目的业务逻辑与项目层的解析与搭建**
2. **后端开发：common层的搭建，dto层搭建，mapper的实现；
   controller，service模板的示范。**
3. **前端开发：提供Vue的组件代码，解释前后端交互的底层逻辑原理。页面的基本布局设计。辅助接口对接**
4. **问题排查：根据报错分析问题所在，定向问题修改**

**自主实现内容：**
1.理解业务逻辑，代码并仿写某些文件（如studentcontroller，后端)；检查console并定位问题，验证修复方案（前端）
2.调试运行，反馈bug并修改
3.了解各技术栈的基本概念，学习基础用法

人与AI协作案例：
在实现前端管理员页面查看时，在后端正常情况下迟迟弄不明白页面空白无报修记录的异常。根据AI提示解决问题方向，前端的路由文件index.js中路径：
component: () => import('../views/admin/AdminOrderList.vue')（已更改）配置异常，修复后前端页面正常显示报修信息


学习心得：
1.**学会独立思考：在发现问题时，经AI提示，学会去定位并解决问题，而非之前AI的传声者。**
2.**学会自主学习：在一些实在由AI代劳的代码中，也会去理解并根据所掌握的知识印证。不会一带而过。**
3.主要参考ai建议
项目结构 分层架构（Controller/Service/Mapper）完全采纳
认证方案 JWT Token + 拦截器 完全采纳
前端框架 Vue 3 + Element Plus 完全采纳
跨域解决 WebConfig 配置 CORS 完全采纳
