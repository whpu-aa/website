# 错误

所有除身份验证相关的客户端错误返回`400`，以及如下 body

```ts
interface ErrorInfo {
  code: number;
  message: string;
}
```

ErrorCode `100000`，通用错误代码，用于请求格式不对。

# 验证

用户验证根据以下流程：

1. 检查 `access_token` query parameter，如果有，使用值作为 token.
2. 否则，检查 `Authorize` Header，如果有，且以`Bearer `开头，则将后面的内容作为 token.
3. 否则，视为未登陆状态。
4. 如果有 token，检查 token 是否合法，以及对应的用户信息是否存在。如果 ok，则进入已验证状态，否则返回`401 Unauthorized`.

## post `/api/token/create`

创建一个 token.

Request

```ts
interface CreateTokenRequest {
  username: string;
  password: string;
  expireAfter?: number; // 以天为单位，默认1天
}
```

Response `200`

```ts
interface CreateTokenResponse {
  token: string;
  user: User;
}
```

Response `400`

ErrorCode `100101`，用户名或密码不正确。

## post `/api/token/verify`

检查一个 token 是否有效。

Request

```ts
interface VerifyTokenRequest {
  token: string;
}
```

Response `200`

```ts
interface VerifyTokenResponse {
  user: User;
}
```

Response `400`

ErrorCode `100102`，token 无效。
ErrorCode `100103`，token 过期了。

## post `/api/token/revoke`

撤销一个 token.

需要验证。

Request

```ts
interface RevokeTokenRequest {
  token: string;
}
```

Response `200`

Response `401`

Response `403`

## post `/api/token/revokeAll`

撤销所有 token.

需要验证。

Response `200`

Response `401`

# 用户

管理员权限对应 permission 为`UserManagement`.

## interface `User`

```ts
const kPermissionList = ["UserManagement", "NewsManagement"];

interface User {
  id: number; // 唯一id
  username: string; // 用户名
  permission: string[]; // 拥有的权限。
  name: string; // 名字
  description: string; // 描述
  details: Record<string, string>; // 其他的信息，一个string map。
  avatarUrl: string; // 头像链接，即 /api/users/:id/avatar
}
```

## get `/api/users`

获取用户列表。

Query Params

```ts
interface GetUserListQueryParams {
  page?: number; // 从0开始的页面号，默认0
  numberPerPage?: number; // 每页多少个用户，默认20
}
```

Response `200`

```ts
interface GetUserListResponse {
  totalCount: number;
  items: User[];
}
```

## get `/api/users/:id`

获取一个用户。

Response `200`

```ts
type GetUserResponse = User;
```

## post `/api/users`

创建一个用户。

需要验证。仅管理员可操作。

```ts
interface PostUserRequest {
  username: string; // 用户名
  name: string; // 名字
  password: string; // 密码
  permission: string[]; // 拥有的权限。
  description?: string; // 描述，不填就是空字符串。
  details?: Record<string, string>; // 其他的信息，一个string map。不填就是空map。
}
```

Response `200`

```ts
type PostUserResponse = User;
```

Response `400`

ErrorCode `100201` 用户名已存在

Response `401`

Response `403`

## patch `/api/users/:id`

修改一个用户。

需要验证。

```ts
interface PatchUserRequest {
  username?: string; // 用户名，仅管理员可设置
  name?: string; // 名字，仅管理员可设置
  password?: string; // 密码，仅管理员可设置
  permission?: string[]; // 权限，仅管理员可设置
  description?: string; // 描述。
  details?: Record<string, string>; // 其他的信息，一个string map。设为null来删除某个字段。
}
```

Response `200`

```ts
type PatchUserResponse = User;
```

Response `400`

ErrorCode `100201` 用户名已存在

ErrorCode `100203` 不能修改根用户的 permission

Response `401`

Response `403`

## delete `/api/users/:id`

删除一个新闻。

需要验证。仅管理员可操作。

Response `200`

Response `400`

ErrorCode `100204` 不能删除根用户。

## get `/api/users/:id/avatar`

获取用户的 avatar.

Response `200`

一个图片，有 ContentType，ETag，Last-Modified，Cache-Control.

## put `/api/users/:id/avatar`

上传一个用户 avatar。

需要验证，自己能上传自己的，管理员能上传所有人的。

Request

一个图片，得有 ContentType，图片得是方的（长宽一样高，前端裁剪！）

Response `200`

```ts
interface PutAvatarResponse {
  etag: string;
}
```

Response `400`

Response `401`

Response `403`

## delete `/api/users/:id/avatar`

删除一个用户的头像，即恢复默认。

需要验证，自己能删除自己的，管理员能删除所有人的。

Response `200`

Response `401`

Response `403`

## post `/api/userop/changepassword`

修改密码。

需要验证。

Request

```ts
interface ChangePasswordRequest {
  oldPassword: string;
  newPassword: string;
}
```

Response `200`

Response `401`

ErrorCode `100202` 旧密码不对。

# 新闻

管理员权限对应 permission 为`NewsManagement`.

## interface `News`

```ts
interface NewsDigest {
  id: number; // 唯一id
  title: string; // 标题
  time: string; // 时间字符串，ISO 8601标准。
  author: string; // 作者
  description: string; // 简短的描述
  labels: string[]; // 标签列表
  url: string; // 链接，即 /api/news/:id
  thumbnailImageUrl?: string; // 图片链接，可为空（鉴于不是所有的新闻都有图）
}

interface ImageDigest {
  etag: string; // 对应Etag
  contentType: string; // 对应ContentType
  url: string; // 图片的链接
}

interface News extends NewsDigest {
  content: string; // Markdown格式的内容
  imageList: ImageDigest[]; // 图片列表
}
```

## get `/api/news`

获取新闻概要列表。

Query Params

```ts
interface GetNewsListQueryParams {
  page?: number; // 从0开始的页面号，默认0
  numberPerPage?: number; // 每页多少条新闻，默认20
}
```

Response `200`

```ts
interface GetNewsListResponse {
  totalCount: number;
  items: NewsDigest[];
}
```

## get `/api/news/:id`

获取一个新闻的详细信息。

Response `200`

```ts
type GetNewsResponse = News;
```

## post `/api/news`

创建一个新闻。

需要验证。

```ts
interface PostNewsRequest {
  title: string; // 标题
  time?: string; // 时间字符串，ISO 8601标准。默认为服务器时间。
  author?: string; // 作者，默认为验证的用户的name。
  description?: string; // 简短的描述。默认为文章的开头一部分。
  labels?: string[]; // 标签列表。为空表示空数组。
  content: string; // Markdown 格式的内容。
  thumbImageIndex?: number; // 选第几张图作为缩略图。为空表示没有缩略图。
  imageList: {
    data: string; // base64格式的数据。
    contentType: string; // 图片的Mime格式。
  }[];
}
```

`content`中可以通过`![...]({image_index})`来引用图片。比如`![hahaha](0)`表示引用第 1 张图片。

Response `200`

```ts
type PostNewsResponse = News;
```

Response `400`

ErrorCode `100301` content 引用了不存在的 image

ErrorCode `100302` thumb 引用了不存在的 image

Response `401`

Response `403`

## patch `/api/news/:id`

修改一个新闻。

需要验证。

```ts
// 空field表示不修改，图片一次只能替换整个列表，暂时没有设计修改单张图片的API，后期再加吧。
interface PatchNewsRequest {
  title?: string; // 标题
  time?: string; // 时间字符串，ISO 8601标准。默认为服务器时间。
  author?: string; // 作者，默认为验证的用户的name。
  description?: string; // 简短的描述。默认为文章的开头一部分。
  labels?: string[]; // 标签列表。
  content?: string; // Markdown 格式的内容。
  thumbImageIndex?: number; // 选第几张图作为缩略图。为空表示没有缩略图。
  imageList?: {
    data: string; // base64格式的数据。
    contentType: string; // 图片的Mime格式。
  }[];
}
```

Response `200`

```ts
type PatchNewsResponse = News;
```

Response `400`

ErrorCode `100301` content 引用了不存在的 image

ErrorCode `100302` thumb 引用了不存在的 image

Response `401`

Response `403`

## delete `/api/news/:id`

删除一个新闻。

需要验证。

Response `200`

Response `401`

Response `403`
