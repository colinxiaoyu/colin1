
1. 将 image 文件打包到drawable下，可以正常引用
react-native bundle --platform android --dev false --entry-file index/index.js --bundle-output app/src/main/assets/index.android.bundle --assets-dest app/src/main/res

2. 将 image 文件打包到assets/drawable下，不能正常引用，需要修改image获取图片资源的源码
   react-native bundle --platform android --dev false --entry-file index.android.js --bundle-output app/src/main/assets/index.android.bundle --assets-dest app/src/main/assets

3. codepush将所有文件打包到 bundle中，image不能正常使用，需要修改image获取图片资源的源码
  react-native bundle --entry-file index.android.js --bundle-output ./bundle/index.android.bundle --platform android --assets-dest ./bundle --dev false

4. http://blog.sina.com.cn/s/blog_1513a1c920102xy7o.html

Production │ 4W944LFwGCJwptFUoBG2FqQqpFAz71f76e94-2011-4f81-a856-8394b47369c3
Staging    │ ZxK1KJTFtLajFeeBjYTK0_CBZLJD71f76e94-2011-4f81-a856-8394b47369c3

code-push app add androidApp android react-native


Error:(36, 0) Cannot set the value of read-only property 'outputFile' for ApkVariantOutputImpl_Decorated{apkData=Main{type=MAIN, fullName=debug, filters=[]}} of type com.android.build.gradle.internal.api.ApkVariantOutputImpl.
<a href="openFile:/Users/puxiang/Desktop/android-react-native/app/build.gradle">Open File</a>
