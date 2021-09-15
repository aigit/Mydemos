###性能调优
字符串池
```html
StringTable 串池的大小调优 如果字符串常量池比较大,
可通过-XX:StringTableSize=(1009,...)
```
- - - 
禁用手动GC
```java
-XX:DisableExplicitGC(会影响直接内存的自动回收)
``` 
---
垃圾回收  
 - 可达性分析
    ```html
    dump文件 jmap -dump:format=b,live,file=x.bin pid
    -XX:+PrintGCDetails -verbose:gc
    ```
 - jvm参数
   ```html
   堆初始大小 -Xms
   堆最大大小 -Xmx 或 -XX:MaxHeapSize=size
   新生代大小 -Xmn 或 (-XX:NewSize=size + -XX:MaxNewSize=size )
   幸存区比例（动态） -XX:InitialSurvivorRatio=ratio 和 -XX:+UseAdaptiveSizePolicy
   幸存区比例 -XX:SurvivorRatio=ratio
   晋升阈值 -XX:MaxTenuringThreshold=threshold
   晋升详情 -XX:+PrintTenuringDistribution
   GC详情 -XX:+PrintGCDetails -verbose:gc
   FullGC 前 MinorGC -XX:+ScavengeBeforeFullGC
   ```
 - 垃圾回收器
   ```html
   - 串行回收:单线程  -XX:+UseSerialGC=Serial+SerialOld
   - 并行回收:吞吐量优先 -XX:+UseParallelGC/-XX:+UseParallelOldGC
   - CMS:响应时间优先
   ![img.png]
   
     
   ```