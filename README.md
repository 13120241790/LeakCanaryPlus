# LeakCanaryPlus
基于 LeakCanary 能力增强，包装一层插件。Based on LeakCanary capability enhancements, pack a layer of plugins



包装 LeakCanary 内存泄漏的检测能力，提供对飞书/钉钉开发群中的内存泄漏引用链以及设备信息等通过机器人的 API 发送到群内。



优先保证公司项目能用，所以工程环境公司项目保证一致，需要降版本



```groovy
minSdkVersion=21
targetSdkVersion=21
compileSdkVersion=26
buildToolsVersion=28.0.3

//root gradle 
 classpath 'com.android.tools.build:gradle:3.1.2'

//gradle-wrapper.properties
distributionUrl=https\://services.gradle.org/distributions/gradle-4.8.1-bin.zip
```

