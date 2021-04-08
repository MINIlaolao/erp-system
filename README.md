### 进度
- [ ] - [x] Brand
  - [x] add
  - [x] update
  - [x] remove
  - [x] get
  - [x] list
- [ ] Vendor
  - [ ] add
  - [ ] update
  - [ ] remove
  - [ ] get
  - [ ] list


### 变量类型规范

不可变长的数组使用 Array 



### Controller 规范

方法和接口一致，接口以SnakeCase命名，方法以CamelCase命名

#### 新增

```java
// POST /api/{package}/{module}/add
@PostMapping("/add")
public VO add(@requestBody DTO requestBody) {}
```



#### 更新

```java
// POST /api/{package}/{module}/update?id={id}
@PostMapping("/update")
public boolean update(Integer id, @RequestBody DTO requestBody) {}
```



#### 移除
本质上是更新了 `removed_at` 和 `removed_by`
```java
// POST /api/{package}/{module}/remove?id={id}
@PostMapping("/remove")
public boolean remove(Integer id) {}
```



#### 删除
该接口必须得有权限，通常不对外开放，所有删除操作都通过移除进行
```java
// POST /api/{package}/{module}/delete?id={id}
@PostMapping("/delete")
public boolean delete(Inerger id) {}
```



#### 获取单条

```java
// GET /api/{package}/{module}?id={id}
@GetMapping("")
public VO get(Inerger id) {}
```



#### 获取多条

```java
// GET /api/{package}/{module}/list?page={page}&size={size}
@GetMapping("/list")
public VO[] list(Integer page, Integer size) {}
```


### Service 规范
方法名要体现出该方法是做什么用的。有整数参数的话，应使用 int 而不是 Integer
#### 