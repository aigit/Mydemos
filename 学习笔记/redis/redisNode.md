- 五种数据结构
```html
string: 
    embstr(44字节以内)
    raw(超过44字节)
    int: 8字节64位long型
List: 
    zipList(少量数据)
    quickList(数据量大)
hash: 字典
set: hash
zset: 
    ziplist(数据量少)
    hash+跳表(数据量大)
```