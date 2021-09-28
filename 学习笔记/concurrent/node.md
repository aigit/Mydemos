####并发编程笔记 
- Synchronized
![img.png](img.png)
  ![img_1.png](img_1.png)
  
- 禁用偏向锁
```html
对象的hashcode()方法会禁用偏向锁;
wait()、notify()
```
- 锁消除
```html
 java -Eliminate -jar... JIT自动解释优化
```
- 虚假唤醒
```html
解决方案
    - while循环
```