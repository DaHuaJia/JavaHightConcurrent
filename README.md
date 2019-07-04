## Java高并发与多线程
#### synchronized 关键字
> demo1主要是关于demo1的例子  
> 1. synchronized是锁定的是在堆内存中的一个特定的对象，而不是栈内存中关于对象的引用。  
> 2. synchronized(this) 和 synchronized(object)  
> 3. 同步方法和非同步方法
> 4. 死锁、同步锁、可重入锁
> 5. 脏读 和 模拟死锁
> 6. volatile 关键字，使一个变量在多个线程之间可见
> 7. 可见性 和 原子性
> 8. AtomXXX 类，AtomXXX类本身的方法都是原子性的，但不能保证多个方法连续调用是原子性的
> 9. sycnchronized优化，避免将锁定的对象的引用变成另外一个对象，避免使用String做为锁对象

#### 自定义高并发容器
> demo2 主要是一些关于高并发容器的编程题
> 1. 当修改锁对象的某个属性时，锁不会改变
> 2. 自定义高并发容器，通过List等 作为容器的体，通过synchronized 确保容器的并发性
> 3. Latch(门栓)的用法、wait 和 notify的用法，启动多个线程
> 4. reentrantLock 的用法， tryLock 和 公平锁

#### 线程休眠与唤醒
> demo3 主要是线程的休眠与唤醒，以及线程局部变量
> 1. wait、notify、notifyAll 和 LinkList 的用法
> 2. tryLock、unlock、lock 的用法
> 3. 线程安全的单例模式
> 4. ThreadLocal 线程局部变量的用法
> 5. Vector 和 Queue 的用法，卖火车票问题

#### Java高并发容器
> demo4 主要是Java 自带的高并发容器
> 1. **DelayQueue** 该容器中的每一个元素都需要在加入后等待一段时间后才能被允许取出
> 2. **ConcurrentHashMap** 高并发Map，所有的操作都是带锁的，锁定的Map中的字段，细化锁，效率相对更高
> 3. **CopyOnWriteArrayList** 写时复制容器，写时效率低，读时效率高，适合少写多读的需求
> 4. **Collections.synchronizedList** 给List加锁，传入一个普通的List参数，输出一个加锁的List
> 5. **ConcurrentQueue** 是一种使用比较多的高并发容器，高并发队列的分类(内部加锁式，阻塞式)
> 6. **LinkedBlockingQueue** 阻塞式高并发队列容器
> 7. **ArrayBlockingQueue** 阻塞式高并发数组容器
> 8. **LinkedTransferQueue** 适用于更高并发的高并发容器，容器插入的对象不保存在容器中，而是直接等待消费者线程的处理
> 9. **synchronousQueue** 是一种特殊的TransferQueue高并发容器，该容器插入一个对象时，必须要有一个线程使用take方法取出

#### Java 线程池
> demo5 主要是Java多线程和线程池
> 1. **Executor** 线程池的顶级接口，Runnable 和 Callable 的区别
> 2. **ThreadPool** 的用法，shutdown() 及一些知识点
> 3. **Future** 获取任务执行完成的返回值，阻塞方法
> 4. **ExecutorService** 并行计算，Callable
> 5. **CacheThreadPool** 初始化的线程池没有固定大小，每增加一个任务，就判断有没有一个空闲线程，没有就创建线程，线程空闲时间操作一定时间就会自动销毁
> 6. **SingleThreadPool** 该线程池只有一个线程，有利于任务的顺序执行
> 7. **SchedulePool** 用于执行定时任务的线程池
> 8. **WorkStealingPool** 该线程池里的线程可以窃取其他线程的任务，该线程池中的线程属于后台线程(守护线程，精灵线程)
> 9. **ForkJoinPool** 任务分叉线程池
> 10. **ThreadExecutorPool**