package com.android.frameworktool.network

import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by jichaopeng
 * 2020/3/18
 */
class NetWorkManager {
    companion object {
        private var netWorkManager: Retrofit? = null
        fun getNetworkManager(host: String): Retrofit {
            return if (netWorkManager == null) {
                NetWorkManager().initNetWorkManager(host)
            } else {
                netWorkManager!!
            }
        }
    }

    fun initNetWorkManager(baseUrl: String): Retrofit {
        val okHttpClient = OkHttpClient().newBuilder().apply {
            connectTimeout(20, TimeUnit.SECONDS)
            readTimeout(20, TimeUnit.SECONDS)
            writeTimeout(20, TimeUnit.SECONDS)
        }.build()
        /**
         * 创建Retrofit实例时需要通过Retrofit.Builder,并调用baseUrl方法设置URL。
         * Retrofit2的baseUlr 必须以 /（斜线）结束，不然会抛出一个IllegalArgumentException
         */
        return Retrofit.Builder()
            .baseUrl(baseUrl) // 设置 网络请求 Url
            .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(okHttpClient)
            .build()
    }
}