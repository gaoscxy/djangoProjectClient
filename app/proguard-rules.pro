# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\gaos\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile

-optimizationpasses 5            # 指定代码的压缩级别
-dontusemixedcaseclassnames# 是否使用大小写混合
-dontpreverify# 混淆时是否做预校验
-verbose# 混淆时是否记录日志

#不混淆需要根据manifest来识别的类
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference


# 保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclasseswithmembers class * {# 保持自定义控件类不被混淆
public <init>(android.content.Context, android.util.AttributeSet);}
-keepclasseswithmembers class * {# 保持自定义控件类不被混淆
public <init>(android.content.Context, android.util.AttributeSet, int);}
-keepclassmembers class * extends android.app.Activity{# 保持自定义控件类不被混淆
public void *(android.view.View);}
-keepclassmembers enum * {# 保持枚举 enum 类不被混淆
public static **[] values();
public static ** valueOf(java.lang.String);}


-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

-keepattributes Signature

 -keepnames class * implements android.os.Parcelable {
     public static final ** CREATOR;
 }
# 保留Serializable序列化的类不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keepattributes Signature
-keep public class com.faxuan.law.R$*{
    public static final int *;
}

 -keep class * extends java.lang.annotation.Annotation{*;}

 -keep class com.gaos.book.model.** {*;}
 -keep class butterknife.** { *; }
 -dontwarn butterknife.internal.**
 -keep class **$$ViewBinder { *; }

 -keepclasseswithmembernames class * {
     @butterknife.* <fields>;
 }

 -keepclasseswithmembernames class * {
     @butterknife.* <methods>;
 }


 -keep public class [com.gaos.book].R$*{
 public static final int *;
 }

 -dontnote retrofit2.Platform
 -dontwarn retrofit2.Platform$Java8
 -keepattributes Signature
 -keepattributes Exceptions

 -dontwarn okhttp3.**
 -dontwarn okio.**
 -dontwarn javax.annotation.**

-keepattributes Exceptions,InnerClasses

-keepattributes Signature

-keepclassmembers class ** {
    public void onEvent*(**);
    }

-keepclassmembers class ** {
    public void onEventMainThread*(**);
    }
#
##华为推送，混淆配置，begin
#-ignorewarnings
#-keepattributes *Annotation*
#-keepattributes Exceptions
#-keepattributes InnerClasses
#-keepattributes Signature
#-keepattributes SourceFile,LineNumberTable
#-keep class com.hianalytics.android.**{*;}
#-keep class com.huawei.updatesdk.**{*;}
#-keep class com.huawei.hms.**{*;}
#
#-keep class com.huawei.gamebox.plugin.gameservice.**{*;}
#
#-keep public class com.huawei.android.hms.agent.** extends android.app.Activity { public *; protected *; }
#-keep interface com.huawei.android.hms.agent.common.INoProguard {*;}
#-keep class * extends com.huawei.android.hms.agent.common.INoProguard {*;}
#华为推送，混淆配置，end

#bugly混淆过滤配置，begin
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
#bugly混淆过滤配置，end

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
# glide 的混淆代码
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}