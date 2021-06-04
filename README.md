# WHPUAA Website Mono Repo

---

[![Front End CI](https://github.com/whpu-aa/website/actions/workflows/front-end-ci.yaml/badge.svg)](https://github.com/whpu-aa/website/actions/workflows/front-end-ci.yaml)
[![Back End CI](https://github.com/whpu-aa/website/actions/workflows/back-end-ci.yaml/badge.svg)](https://github.com/whpu-aa/website/actions/workflows/back-end-ci.yaml)

## How to start dev server? 如何启动开发服务器

### step 1

Open a terminal and run commands below, then backend is started.

打开一个终端然后执行下面的命令，然后后端就会启动。

```Bash
$ cd BackEnd # move to backend directory
$ ./gradlew bootRun # build and run backend
```

Do not close the terminal or backend would stop.

请不要关闭这个终端，否则后端将关闭。

### step 2

Open your favourite IDE and open a terminal tab and run following commands.

打开你最爱的 IDE 并打开一个终端页面再执行下面的命令。

```Bash
$ cd FrontEnd # move to frontend directory
$ npm ci # install packages
$ npm run serve # start frontend dev server
```

Front end dev server will automatically proxy api request from backend.

前端开发服务器会自动从后端代理 API 请求。

### step 3

Open browser and direct to `http://localhost:3000` and enjoy your development.

打开浏览器并转到`http://localhost:3000`，开始享受开发之旅！
