# 新闻

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
type GetNewsListResponse = NewsDigest[];
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
type PostNewsResponse = News;
```

## delete `/api/news/:id`

删除一个新闻。

需要验证。

Response `200`
