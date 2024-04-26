# halo-plugin-TinyTale

此插件，没有任何实质性功能仅为 同名小程序提供配置信息。

# TinyTale 微信小程序 
`TinyTale` 是一款基于 `Halo` 博客系统的微信小程序，可以展示博客文章、图库、瞬间等内容。简洁、美观、易用，是你的博客的最佳搭档。
## 详情
[TinyTale Halo微信小程序内测发布](https://www.jiewen.run/archives/TinyTale)
## 预览
![image](doc/preview/1.jpg)  
![image](doc/preview/2.jpg))  
![image](doc/preview/3.jpg)
![image](doc/preview/4.png)
## 文档
[TinyTale](https://www.jiewen.run/docs/TinyTale)
## 技术栈
- [uni-app](https://uniapp.dcloud.io/)
- [TailwindCSS](https://tailwindcss.com/)
- [Pinia](https://pinia.vuejs.org/)
- [Tuniao UI Uniapp V3](https://vue3.tuniaokj.com/)
- [iconify](https://icon-sets.iconify.design/)
- [Vue 3](https://github.com/vuejs/core), [Vite](https://github.com/vitejs/vite), [Yarn](https://github.com/yarnpkg/yarn) - 快且稳定
-
## 特性
- [x] 文章列表
- [x] 文章详情
- [x] 分类列表
- [x] 图库展示
- [x] 瞬间展示
- [x] 评论展示
- [x] 发布图库
- [x] 发布瞬间
- [x] [配套插件](https://github.com/jiewenhuang/halo-plugin-TinyTale)

## Next
- [ ] 优化文章样式
- [ ] 优化代码结构
- [ ] ......

## 开发环境

插件开发的详细文档请查阅：<https://docs.halo.run/developer-guide/plugin/introduction>

所需环境：

1. Java 17
2. Node 18
3. pnpm 8
4. Docker (可选)

克隆项目：

```bash
git clone git@github.com:halo-sigs/plugin-starter.git

# 或者当你 fork 之后

git clone git@github.com:{your_github_id}/plugin-starter.git
```

```bash
cd path/to/plugin-starter
```

### 运行方式 1（推荐）

> 此方式需要本地安装 Docker

```bash
# macOS / Linux
./gradlew pnpmInstall

# Windows
./gradlew.bat pnpmInstall
```

```bash
# macOS / Linux
./gradlew haloServer

# Windows
./gradlew.bat haloServer
```

执行此命令后，会自动创建一个 Halo 的 Docker 容器并加载当前的插件，更多文档可查阅：<https://docs.halo.run/developer-guide/plugin/basics/devtools>

### 运行方式 2

> 此方式需要使用源码运行 Halo

编译插件：

```bash
# macOS / Linux
./gradlew build

# Windows
./gradlew.bat build
```

修改 Halo 配置文件：

```yaml
halo:
  plugin:
    runtime-mode: development
    fixedPluginPath:
      - "/path/to/plugin-starter"
```

最后重启 Halo 项目即可。
