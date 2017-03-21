decoration测试
=====

#### view 的 `setSystemUiVisibility(int)` :
##### `SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN`和`SYSTEM_UI_FLAG_FULLSCREEN`的区别：
 * 无layout会自动去除actionbar，有layout会显示actionbar

#### SYSTEM_UI_FLAG_IMMERSIVE和SYSTEM_UI_FLAG_IMMERSIVE_STICKY区别：

- 两者在当activity获取点击事件都不会使系统decoration显示出来，不设置此两个时则会显示出来
- 前者设置后系统decoration不会消失，当下滑动时出来的是透明的decoration
- 后者设置后系统decoration会延时消失，当下滑动时出来的是半透明的decoration
#### 小结
使用 `SYSTEM_UI_FLAG_IMMERSIVE` + `handler` 来处理延时消失，让用户体验可以更沉浸
[](app\src\main\res\drawable\immersivebefore4_4.jpg)